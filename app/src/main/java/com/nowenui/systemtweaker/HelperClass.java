package com.nowenui.systemtweaker;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Environment;
import android.os.StatFs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

public class HelperClass {
    private static final String PRE_PATH = "/proc/sys/net/ipv4/";
    public static Scanner file_scanner;
    private final Context context;

    public HelperClass(Context context) {
        this.context = context;
    }

    ////////////////////////////////
    ////// Check init.d support ////
    ////////////////////////////////
    public static int isInitdSupport() {
        File initd = new File("/system/etc/init.d");
        if ((initd.exists()) && (initd.isDirectory())) {
            return 1;
        }
        return 0;
    }

    /////////////////////////////////
    ////// Check SELinux Support ////
    /////////////////////////////////
    public static int isSELinuxPresent() {
        File f = new File("/system/bin/getenforce");
        if ((f.exists())) {
            return 1;
        }
        return 0;
    }

    public static int LCDCheck() {
        File f = new File("/sys/class/lcd/panel/power_reduce");
        if ((f.exists())) {
            return 1;
        }
        return 0;
    }

    public static int BoostCheck() {
        File f = new File("/sys/module/msm_performance/parameters/touchboost");
        File f2 = new File("/sys/module/cpu_boost/parameters/boost_ms");
        if ((f.exists()) && (f2.exists())) {
            return 1;
        }
        return 0;
    }

    ///////////////////////////////////////////
    ////// TCP modes read ////////////////////
    //////////////////////////////////////////
    public static String[] readAvailableTCP() throws Exception {
        String file_path = PRE_PATH + "tcp_available_congestion_control";
        File scalingAvailableFrequenciesFile = new File(file_path);
        ArrayList<String> availableFrequencies = new ArrayList<String>();
        file_scanner = new Scanner(scalingAvailableFrequenciesFile);
        while (file_scanner.hasNext()) {
            availableFrequencies.add(file_scanner.next());
        }
        String[] availableFrequenciesArray = new String[availableFrequencies.size()];
        availableFrequenciesArray = availableFrequencies.toArray(availableFrequenciesArray);

        return availableFrequenciesArray;
    }

    ///////////////////////////////////////////
    ////// Check if Entropy running ///////////
    //////////////////////////////////////////
    public static String checkIfEntropyRunning() {

        try {
            Process p = Runtime.getRuntime().exec(new String[]{"su", "-c", "pgrep rngd"});
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));
            int read;
            char[] buffer = new char[4096];
            StringBuffer output = new StringBuffer();
            while ((read = reader.read(buffer)) > 0) {
                output.append(buffer, 0, read);
            }
            reader.close();

            p.waitFor();

            return output.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /////////////////////////////////////
    ////// Check Entropy pull ///////////
    /////////////////////////////////////
    public static String getEntropyPull() {

        try {
            Process process = Runtime.getRuntime().exec("cat /proc/sys/kernel/random/entropy_avail");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
            int read;
            char[] buffer = new char[4096];
            StringBuffer output = new StringBuffer();
            while ((read = reader.read(buffer)) > 0) {
                output.append(buffer, 0, read);
            }
            reader.close();

            process.waitFor();

            return output.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    ///////////////////////////////////////
    ////// buildprop -> String ////////////
    ///////////////////////////////////////
    public static StringBuilder BuildPropText() {
        File buildpropfile = new File("/system/build.prop");

        StringBuilder buildproptext = new StringBuilder();
        try {
            BufferedReader br2 = new BufferedReader(new FileReader(buildpropfile));
            String line2;

            while ((line2 = br2.readLine()) != null) {
                buildproptext.append(line2);
                buildproptext.append('\n');
            }
            br2.close();
        } catch (IOException ignored) {
        }
        return buildproptext;
    }

    ////////////////////////////////////////////
    ////// Check fastcharge support ////////////
    ////////////////////////////////////////////
    public static int fastcharge() {
        String fast = "force_fast_charge";
        String[] locations = {"/sys/kernel/fast_charge/"};
        for (String location : locations) {
            if (new File(location + fast).exists()) {
                return 1;
            }
        }
        return 0;
    }


    ////////////////////////////////////////////////////
    ////// Check MultiCorePowerSaving support //////////
    ////////////////////////////////////////////////////
    public static int mpcs() {
        String mpcs = "sched_mc_power_savings";
        String[] locations = {"/sys/devices/system/cpu/"};
        for (String location : locations) {
            if (new File(location + mpcs).exists()) {
                return 1;
            }
        }
        return 0;
    }


    /////////////////////////////////////
    ////// Check KSM support ////////////
    /////////////////////////////////////

    public static int ksm() {
        String ksm = "run";
        String[] locations = {"/sys/kernel/mm/ksm/"};
        for (String location : locations) {
            if (new File(location + ksm).exists()) {
                return 1;
            }
        }
        return 0;
    }

    /////////////////////////////////////////////////////////////
    ////// Check ZRAM tweak support  ////////////
    /////////////////////////////////////////////////////////////
    public static int ZRAM() {
        File f = new File("/sys/block/zram0");
        if ((f.exists()) && (f.isDirectory())) {
            return 1;
        }
        return 0;
    }

    /////////////////////////////////////////////////////////////
    ////// Check Sleepers tweak support  ////////////////////////
    /////////////////////////////////////////////////////////////
    public static int SleepersSupportCheck() {
        File f = new File("/sys/kernel/debug/sched_features");
        if (f.exists()) {
            return 1;
        }
        return 0;
    }

    ////////////////////////////////////////////////////////////
    ////// Check Interactive governor tweak support ////////////
    ////////////////////////////////////////////////////////////
    public static int InteractiveTweakSupport() {
        if ((((new File("/dev/cpuctl/cpu.shares").exists() && new File("/dev/cpuctl/cpu.rt_runtime_us").exists() && new File("/dev/cpuctl/cpu.rt_period_us").exists())
                || (new File("/dev/cpuctl/apps/cpu.shares").exists() && new File("/dev/cpuctl/apps/cpu.rt_runtime_us").exists() && new File("/dev/cpuctl/apps/cpu.rt_period_us").exists())) && ((new File("/dev/cpuctl/bg_non_interactive/cpu.shares").exists() && new File("/dev/cpuctl/bg_non_interactive/cpu.rt_runtime_us").exists())
                || (new File("/dev/cpuctl/apps/bg_non_interactive/cpu.shares").exists() && new File("/dev/cpuctl/apps/bg_non_interactive/cpu.rt_runtime_us").exists())) && new File("/dev/cpuctl/cgroup.procs").exists() && new File("/dev/cpuctl/tasks").exists()) && (new File("/sys/devices/system/cpu/cpufreq/interactive/boost").exists())) {
            return 1;
        }
        return 0;
    }

    ////////////////////////////////////////////////////////
    ////// Check Ondemand/Hotplug tweak support ////////////
    ////////////////////////////////////////////////////////
    public static int OndemandOrHotplugTweakSupport() {
        if ((new File("/sys/devices/system/cpu/cpufreq/ondemand/up_threshold").exists() && new File("/sys/devices/system/cpu/cpufreq/ondemand/sampling_rate").exists() && new File("/sys/devices/system/cpu/cpufreq/ondemand/sampling_down_factor").exists() && new File("/sys/devices/system/cpu/cpufreq/ondemand/down_differential").exists())
                || (new File("/sys/devices/system/cpu/cpufreq/hotplug/up_threshold").exists() && new File("/sys/devices/system/cpu/cpufreq/hotplug/sampling_rate").exists() && new File("/sys/devices/system/cpu/cpufreq/hotplug/sampling_down_factor").exists() && new File("/sys/devices/system/cpu/cpufreq/hotplug/down_differential").exists())) {
            return 1;
        }
        return 0;
    }

    ///////////////////////////////////////////////////////////////////////
    ////// /proc/sys/net/ipv4/tcp_congestion_control -> String ////////////
    ///////////////////////////////////////////////////////////////////////
    public static StringBuilder TCPmode() {
        File tcpmodesfile = new File("/proc/sys/net/ipv4/tcp_congestion_control");

        final StringBuilder tcpmodestext = new StringBuilder();

        try {
            String tcp;
            BufferedReader br = new BufferedReader(new FileReader(tcpmodesfile));
            while ((tcp = br.readLine()) != null) {
                tcpmodestext.append(tcp);
            }
            br.close();
        } catch (IOException ignored) {
        }
        return tcpmodestext;
    }

    ///////////////////////////////////
    ////// About processor ///////////
    /////////////////////////////////
    private static String ProcessorInfo() {
        StringBuffer sb = new StringBuffer();
        if (new File("/proc/cpuinfo").exists()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(new File("/proc/cpuinfo")));
                String aLine;
                while ((aLine = br.readLine()) != null) {
                    sb.append(aLine + "\n");
                }
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    ///////////////////////////////
    ////// Get CPU name ///////////
    ///////////////////////////////
    public static synchronized String getCPUName() {
        String CPUName = "";

        String[] lines = ProcessorInfo().split("\n");

        for (int i = 0; i < lines.length; i++) {
            if (lines[i].contains("Hardware\t:")) {
                CPUName = lines[i].replace("Hardware\t: ", "");
                break;
            }
        }
        return CPUName;
    }

    ////////////////////////////////
    ////// Get heap size ///////////
    ////////////////////////////////
    public static String getHeapSize() {
        try {
            Process proc = Runtime.getRuntime().exec("getprop dalvik.vm.heapsize");
            InputStream is = proc.getInputStream();
            String size = getStringFromInputStream(is);
            if (!size.equals("\n"))
                return size;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "?";
    }

    private static String getStringFromInputStream(InputStream is) {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;

        try {
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    ////////////////////////////////////////
    ////// Get RAM size in MB /////////////
    ///////////////////////////////////////
    public long getTotalRAM() {
        long total_memory_in_Mbs = 0;
        if (VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN) {
            MemoryInfo mi = new MemoryInfo();
            ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            activityManager.getMemoryInfo(mi);
            total_memory_in_Mbs = mi.totalMem / 1048576L;
        } else {
            RandomAccessFile reader;
            String load;
            try {
                reader = new RandomAccessFile("/proc/meminfo", "r");
                load = reader.readLine().replaceAll("\\D+", "");
                total_memory_in_Mbs = Integer.parseInt(load) / 1024;
            } catch (IOException ex) {

            }
        }
        return total_memory_in_Mbs;
    }

    ////////////////////////////////////////////
    ////// Get Internal memory size ////////////
    ///////////////////////////////////////////
    public long getTotalInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize;
        long totalBlocks;
        if (VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN_MR2) {
            blockSize = stat.getBlockSizeLong();
            totalBlocks = stat.getBlockCountLong();
        } else {
            blockSize = stat.getBlockSize();
            totalBlocks = stat.getBlockCount();
        }
        return (totalBlocks * blockSize) / (1024 * 1024);
    }

}
