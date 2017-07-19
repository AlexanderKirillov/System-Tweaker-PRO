package com.nowenui.systemtweaker;

import android.content.Context;
import android.os.SystemClock;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SysUtils {
    private final static String cpufreq_sys_dir = "/sys/devices/system/cpu/cpu0/cpufreq/";
    private final static String scaling_min_freq = cpufreq_sys_dir + "scaling_min_freq";
    private final static String cpuinfo_min_freq = cpufreq_sys_dir + "cpuinfo_min_freq";
    private final static String scaling_max_freq = cpufreq_sys_dir + "scaling_max_freq";
    private final static String cpuinfo_max_freq = cpufreq_sys_dir + "cpuinfo_max_freq";
    private final static String scaling_governor = cpufreq_sys_dir + "scaling_governor";
    private final static String scaling_available_freq = cpufreq_sys_dir + "scaling_available_frequencies";
    private final static String scaling_available_governors = cpufreq_sys_dir + "scaling_available_governors";
    private final static String scaling_stats_time_in_state = cpufreq_sys_dir + "stats/time_in_state";

    private final static String ioscheduler = "/sys/block/mmcblk0/queue/scheduler";
    private final static String ioscheduler_mtd = "/sys/block/mtdblock0/queue/scheduler";
    private final static String RE_FAKE_BLKDEV = "(loop|zram|dm-)[0-9]+";

    private final static int BUFFER_SIZE = 2048;

    public static String[] getAvailableFrequencies() {
        String[] frequencies = readStringArray(scaling_available_freq);
        if (frequencies == null) {
            Stats stats = getFrequencyStats(false);
            if (stats != null) {
                frequencies = new String[stats.getFrequencies().size()];
                for (int i = 0; i < stats.getFrequencies().size(); i++) {
                    frequencies[i] = stats.getFrequencies().get(i).getValue();
                }
            } else {
                frequencies = new String[2];
                frequencies[0] = readString(cpuinfo_min_freq);
                frequencies[1] = readString(cpuinfo_max_freq);
                if (frequencies[0] == null || frequencies[1] == null) {
                    frequencies = null;
                }
            }
        }
        if (frequencies != null) {

            Comparator<String> frequencyComparator = new Comparator<String>() {
                @Override
                public int compare(String lhs, String rhs) {
                    Integer lhi = Integer.parseInt(lhs);
                    Integer rhi = Integer.parseInt(rhs);
                    return lhi.compareTo(rhi);
                }
            };
            Arrays.sort(frequencies, frequencyComparator);
        }
        return frequencies;
    }

    public static String[] getAvailableGovernors() {
        return readStringArray(scaling_available_governors);
    }

    public static String[] getAvailableIOSchedulers() {
        String[] schedulers = null;
        String file = ioscheduler;
        if (!(new File(file)).exists()) {
            file = ioscheduler_mtd;
        }
        String[] aux = readStringArray(file);
        if (aux != null) {
            schedulers = new String[aux.length];
            for (int i = 0; i < aux.length; i++) {
                if (aux[i].charAt(0) == '[') {
                    schedulers[i] = aux[i].substring(1, aux[i].length() - 1);
                } else {
                    schedulers[i] = aux[i];
                }
            }
        }
        return schedulers;
    }

    private static String[] readStringArray(String filename) {
        String line = readString(filename);
        if (line != null) {
            return line.split(" ");
        }
        return null;
    }

    public static Frequency getMinFreq() {
        String[] files = {
                scaling_min_freq, cpuinfo_min_freq
        };
        return getXXXFreq(files);
    }

    public static Frequency getMaxFreq() {
        String[] files = {
                scaling_max_freq, cpuinfo_max_freq
        };
        return getXXXFreq(files);
    }


    private static Frequency getXXXFreq(String[] possibleFiles) {
        for (String file : possibleFiles) {
            String s = readString(file);
            if (s != null) {
                return new Frequency(s);
            }
        }
        return null;
    }

    public static String getGovernor() {
        return readString(scaling_governor);
    }

    public static String getIOScheduler() {
        String scheduler = null;
        String file = ioscheduler;
        if (!(new File(file)).exists()) {
            file = ioscheduler_mtd;
        }
        String[] schedulers = readStringArray(file);
        if (schedulers != null) {
            for (String s : schedulers) {
                if (s.charAt(0) == '[') {
                    scheduler = s.substring(1, s.length() - 1);
                    break;
                }
            }
        }
        return scheduler;
    }

    private static String readString(String filename) {
        try {
            File f = new File(filename);
            if (f.exists()) {
                InputStream is = null;
                if (f.canRead()) {
                    is = new FileInputStream(f);
                } else {
                    String[] commands = {
                            "cat " + filename + "\n", "exit\n"
                    };
                    Process p = Runtime.getRuntime().exec("su");
                    DataOutputStream dos = new DataOutputStream(p.getOutputStream());
                    for (String command : commands) {
                        dos.writeBytes(command);
                        dos.flush();
                    }
                    if (p.waitFor() == 0) {
                        is = p.getInputStream();
                    } else {
                        return null;
                    }
                }
                BufferedReader br = new BufferedReader(new InputStreamReader(is), BUFFER_SIZE);
                String line = br.readLine();
                br.close();
                return line;
            } else {
                return null;
            }
        } catch (InterruptedException iex) {
            return null;
        } catch (IOException ioex) {
            return null;
        }
    }

    public static boolean setFrequenciesAndGovernor(String min_freq, String max_freq, String governor,
                                                    String ioscheduler, boolean changePermissions, Context ctx, int resOkMsg, int resFailedMsg) {
        try {
            List<String> commands = new ArrayList<>();

            if (governor != null || min_freq != null || max_freq != null) {
                int cpus = 0;
                while (true) {
                    File f = new File(SysUtils.scaling_min_freq.replace("cpu0", "cpu" + cpus));
                    if (!f.exists()) {
                        break;
                    }
                    cpus++;
                }
                for (int i = 0; i < cpus; i++) {
                    commands.add("chmod 0644 " + SysUtils.scaling_governor.replace("cpu0", "cpu" + i) + "\n");
                    commands.add("chmod 0664 " + SysUtils.scaling_min_freq.replace("cpu0", "cpu" + i) + "\n");
                    commands.add("chmod 0664 " + SysUtils.scaling_max_freq.replace("cpu0", "cpu" + i) + "\n");

                    if (governor != null) {
                        commands.add("echo " + governor + " > " + SysUtils.scaling_governor.replace("cpu0", "cpu" + i) + "\n");
                    }

                    if (min_freq != null && max_freq != null) {
                        String[] available_frequencies = getAvailableFrequencies();
                        String lowest_min_freq = available_frequencies[0];

                        commands.add("echo " + lowest_min_freq + " > " + SysUtils.scaling_min_freq.replace("cpu0", "cpu" + i)
                                + "\n");
                        commands.add("echo " + max_freq + " > " + SysUtils.scaling_max_freq.replace("cpu0", "cpu" + i) + "\n");
                        commands.add("echo " + min_freq + " > " + SysUtils.scaling_min_freq.replace("cpu0", "cpu" + i) + "\n");
                    }
                    if (changePermissions) {
                        commands.add("chmod 0444 " + SysUtils.scaling_governor.replace("cpu0", "cpu" + i) + "\n");
                        commands.add("chmod 0444 " + SysUtils.scaling_min_freq.replace("cpu0", "cpu" + i) + "\n");
                        commands.add("chmod 0444 " + SysUtils.scaling_max_freq.replace("cpu0", "cpu" + i) + "\n");
                    }
                }
            }

            if (ioscheduler != null) {
                File sysBlock = new File("/sys/block");
                if (sysBlock.exists() && sysBlock.isDirectory()) {
                    File[] blockDevices = sysBlock.listFiles();
                    if (blockDevices != null) {
                        for (File blockDevice : blockDevices) {
                            if (blockDevice.isDirectory() && !blockDevice.getName().matches(RE_FAKE_BLKDEV)) {
                                File queueScheduler = new File(blockDevice, "queue/scheduler");
                                if (queueScheduler.exists()) {
                                    commands.add("chmod 0644 " + queueScheduler.getAbsolutePath() + "\n");
                                    commands.add("echo " + ioscheduler + " > " + queueScheduler.getAbsolutePath() + "\n");
                                    if (changePermissions) {
                                        commands.add("chmod 0444 " + queueScheduler.getAbsolutePath() + "\n");
                                    }
                                }
                            }
                        }
                    } else {
                        Toast.makeText(ctx, "ERROR!", Toast.LENGTH_LONG).show();
                    }

                }
            }

            commands.add("exit\n");

            Process p = Runtime.getRuntime().exec("su");
            DataOutputStream dos = new DataOutputStream(p.getOutputStream());
            for (String command : commands) {
                dos.writeBytes(command);
                dos.flush();
            }
            int res = p.waitFor();
            if (res == 0) {
                String mex = ctx.getString(resOkMsg, getMinFreq(), getMaxFreq(), getGovernor(), getIOScheduler());
                Toast.makeText(ctx, mex, Toast.LENGTH_LONG).show();
                return true;
            } else {
                Toast.makeText(ctx, ctx.getString(resFailedMsg), Toast.LENGTH_LONG).show();
                return false;
            }
        } catch (Exception ex) {
            Toast.makeText(ctx, "Error: " + ex.getMessage(), Toast.LENGTH_LONG).show();
            return false;
        }
    }

    public static Stats getFrequencyStats(boolean withDeepSleep) {
        Map<String, Long> times = new HashMap<String, Long>();
        List<Frequency> frequencies = new ArrayList<Frequency>();
        Long totalTime = 0L;
        File f = new File(scaling_stats_time_in_state);
        InputStream is = null;
        if (f.exists()) {
            try {
                if (f.canRead()) {
                    is = new FileInputStream(f);
                } else {
                    String[] commands = {
                            "cat " + f + "\n", "exit\n"
                    };
                    Process p = Runtime.getRuntime().exec("su");
                    DataOutputStream dos = new DataOutputStream(p.getOutputStream());
                    for (String command : commands) {
                        dos.writeBytes(command);
                        dos.flush();
                    }
                    if (p.waitFor() == 0) {
                        is = p.getInputStream();
                    } else {
                        is = p.getErrorStream();
                    }
                }

                BufferedReader br = new BufferedReader(new InputStreamReader(is), BUFFER_SIZE);
                String line = null;
                while ((line = br.readLine()) != null) {
                    String[] aux = line.split(" ");
                    Frequency freq = new Frequency(aux[0]);
                    Long time_in_state = Long.parseLong(aux[1]);
                    totalTime += time_in_state;
                    frequencies.add(freq);
                    times.put(freq.getValue(), time_in_state);
                }
                br.close();
                if (withDeepSleep) {
                    Frequency deepSleep = new Frequency("-1");
                    long uptimeInMillis = SystemClock.uptimeMillis();
                    long elapsed = SystemClock.elapsedRealtime();
                    long deepSleepTime = elapsed - uptimeInMillis;
                    totalTime += deepSleepTime;
                    frequencies.add(deepSleep);
                    times.put(deepSleep.getValue(), deepSleepTime);
                    if (Constants.DEBUG) {
                    }
                }
                return new Stats(frequencies, times, totalTime);
            } catch (IOException ioex) {
                return null;
            } catch (InterruptedException iex) {
                return null;
            }
        } else {
            return null;
        }

    }

    public static boolean hasSysfs() {
        String[] requiredFiles = {
                scaling_governor, scaling_max_freq, scaling_min_freq
        };
        for (String requiredFile : requiredFiles) {
            if (!(new File(requiredFile)).exists()) {
                return false;
            }
        }
        return true;
    }

}