package com.nowenui.systemtweaker.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.github.mrengineer13.snackbar.SnackBar;
import com.nowenui.systemtweaker.R;
import com.nowenui.systemtweaker.Utility;
import com.onebit.spinner2.Spinner2;
import com.stericson.RootShell.exceptions.RootDeniedException;
import com.stericson.RootShell.execution.Command;
import com.stericson.RootTools.RootTools;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class InternetTweaksFragment extends Fragment {

    private static final String PRE_PATH = "/proc/sys/net/ipv4/";
    private Scanner file_scanner;

    public static InternetTweaksFragment newInstance(Bundle bundle) {
        InternetTweaksFragment InternetTweaks = new InternetTweaksFragment();

        if (bundle != null) {
            InternetTweaks.setArguments(bundle);
        }

        return InternetTweaks;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_user:
                if (Utility.getTheme(getActivity().getApplicationContext()) == 1) {
                    new android.app.AlertDialog.Builder(this.getContext())
                            .setTitle(R.string.reboot)
                            .setMessage(R.string.rebootactionbar)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    try {
                                        Process proc = Runtime.getRuntime().exec(new String[]{"su", "-c", "reboot"});
                                        proc.waitFor();
                                    } catch (Exception ex) {
                                        new SnackBar.Builder(getActivity()).withMessage("ROOT NEEDED!").withBackgroundColorId(R.color.textview1bad).show();
                                    }
                                }
                            })
                            .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(R.drawable.warning)
                            .show();
                }
                if (Utility.getTheme(getActivity().getApplicationContext()) == 2) {
                    new android.app.AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark))
                            .setTitle(R.string.reboot)
                            .setMessage(R.string.rebootactionbar)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    try {
                                        Process proc = Runtime.getRuntime().exec(new String[]{"su", "-c", "reboot"});
                                        proc.waitFor();
                                    } catch (Exception ex) {
                                        new SnackBar.Builder(getActivity()).withMessage("ROOT NEEDED!").withBackgroundColorId(R.color.textview1bad).show();
                                    }
                                }
                            })
                            .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(R.drawable.warning)
                            .show();
                }
                if (Utility.getTheme(getActivity().getApplicationContext()) == 3) {
                    new android.app.AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack))
                            .setTitle(R.string.reboot)
                            .setMessage(R.string.rebootactionbar)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    try {
                                        Process proc = Runtime.getRuntime().exec(new String[]{"su", "-c", "reboot"});
                                        proc.waitFor();
                                    } catch (Exception ex) {
                                        new SnackBar.Builder(getActivity()).withMessage("ROOT NEEDED!").withBackgroundColorId(R.color.textview1bad).show();
                                    }
                                }
                            })
                            .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(R.drawable.warning)
                            .show();
                }
        }
        return super.onOptionsItemSelected(item);
    }

    public String[] readAvailablecong() throws Exception {
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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_internettweaks, parent, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {


        ///////////////////////////////////////////////////////////////////////
        ////// /proc/sys/net/ipv4/tcp_congestion_control -> String ////////////
        ///////////////////////////////////////////////////////////////////////
        File file = new File("/proc/sys/net/ipv4/tcp_congestion_control");

        final StringBuilder text5 = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                text5.append(line);
            }
            br.close();
        } catch (IOException e) {
        }

        ////////////////////////////////////////
        ////// build.prop -> String ////////////
        ////////////////////////////////////////

        File file2 = new File("/system/build.prop");

        StringBuilder text2 = new StringBuilder();
        try {
            BufferedReader br2 = new BufferedReader(new FileReader(file2));
            String line2;

            while ((line2 = br2.readLine()) != null) {
                text2.append(line2);
                text2.append('\n');
            }
            br2.close();
        } catch (IOException e) {
        }

        ///////////////////////////////////
        ////// TCP mode changer ///////////
        ///////////////////////////////////
        final CheckBox tcpboot = view.findViewById(R.id.tcpboot);
        final SharedPreferences mSharedPreference = PreferenceManager.getDefaultSharedPreferences(getContext());
        if (mSharedPreference.contains("skipnitd")) {
            tcpboot.setEnabled(false);
        } else {
            tcpboot.setEnabled(true);
        }

        TextView textView40 = view.findViewById(R.id.textView40);

        textView40.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                if (Utility.getTheme(getActivity().getApplicationContext()) == 1) {
                    new AlertDialog.Builder(getContext())
                            .setTitle(R.string.tweakabout)
                            .setMessage(R.string.inet7)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(R.drawable.warning)
                            .show();
                }
                if (Utility.getTheme(getActivity().getApplicationContext()) == 2) {
                    new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark))
                            .setTitle(R.string.tweakabout)
                            .setMessage(R.string.inet7)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(R.drawable.warning)
                            .show();
                }
                if (Utility.getTheme(getActivity().getApplicationContext()) == 3) {
                    new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack))
                            .setTitle(R.string.tweakabout)
                            .setMessage(R.string.inet7)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(R.drawable.warning)
                            .show();
                }

                return true;
            }
        });


        try {
            final Spinner2 ipv4 = view.findViewById(R.id.ipv4);


            final String[] inet = readAvailablecong();

            ArrayAdapter<String> InetAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item,
                    inet);
            InetAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            ipv4.setAdapter(InetAdapter, false);

            final int spinnerPosition4 = InetAdapter.getPosition(text5.toString());
            ipv4.post(new Runnable() {
                @Override
                public void run() {
                    ipv4.setSelection(false, spinnerPosition4);

                }
            });

            ipv4.post(new Runnable() {
                @Override
                public void run() {
                    ipv4.setOnItemSelectedSpinner2Listener(new Spinner2.OnItemSelectedSpinner2Listener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            TextView textView = (TextView) ipv4.getSelectedView();
                            String text = textView.getText().toString();
                            try {
                                Process su = Runtime.getRuntime().exec("su");
                                DataOutputStream outputStream = new DataOutputStream(su.getOutputStream());
                                outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount rootfs\n");
                                outputStream.flush();
                                outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc rootfs\n");
                                outputStream.flush();
                                outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount rootfs\n");
                                outputStream.flush();
                                outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw rootfs\n");
                                outputStream.flush();
                                outputStream.writeBytes("chmod 777 /proc/sys/net/ipv4/tcp_congestion_control\n");
                                outputStream.flush();
                                outputStream.writeBytes("echo \"" + text + "\" >> /proc/sys/net/ipv4/tcp_congestion_control\n");
                                outputStream.flush();
                                outputStream.writeBytes("chmod 644 /proc/sys/net/ipv4/tcp_congestion_control\n");
                                outputStream.flush();
                                outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc rootfs\n");
                                outputStream.flush();
                                outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount rootfs\n");
                                outputStream.flush();
                                outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount rootfs\n");
                                outputStream.flush();
                                outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro rootfs\n");
                                outputStream.flush();
                                outputStream.writeBytes("exit\n");
                                outputStream.flush();
                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.ok)).withBackgroundColorId(R.color.textview1good).show();
                            } catch (IOException e) {
                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                            }
                            if (tcpboot.isChecked()) {
                                try {
                                    Process su = Runtime.getRuntime().exec("su");
                                    DataOutputStream outputStream = new DataOutputStream(su.getOutputStream());
                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system\n");
                                    outputStream.flush();
                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system\n");
                                    outputStream.flush();
                                    outputStream.writeBytes("mount -o rw,remount /system\n");
                                    outputStream.flush();
                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system\n");
                                    outputStream.flush();
                                    outputStream.writeBytes("cp /data/data/com.nowenui.systemtweaker/files/12tcp /system/etc/init.d/\n");
                                    outputStream.flush();
                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox echo '$busybox echo \"" + text + "\" > /proc/sys/net/ipv4/tcp_congestion_control' >> /system/etc/init.d/12tcp\n");
                                    outputStream.flush();
                                    outputStream.writeBytes("chmod 777 /system/etc/init.d/12tcp\n");
                                    outputStream.flush();
                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system\n");
                                    outputStream.flush();
                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system\n");
                                    outputStream.flush();
                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system\n");
                                    outputStream.flush();
                                    outputStream.writeBytes("exit\n");
                                    outputStream.flush();
                                } catch (IOException e) {
                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                                }
                            }

                        }
                    });
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        /////////////////////////////////////////////
        ////// Change TCP Mode after boot ///////////
        /////////////////////////////////////////////
        String check4 = "/etc/init.d/12tcp";
        String check4a = "/system/etc/init.d/12tcp";
        if (new File(Environment.getRootDirectory() + check4).exists() || new File(check4a).exists() || new File(Environment.getRootDirectory() + check4a).exists()) {
            tcpboot.setChecked(true);
        } else {
            tcpboot.setChecked(false);
        }
        if (mSharedPreference.contains("skipnitd")) {
            tcpboot.setEnabled(false);
        } else {
            tcpboot.setEnabled(true);
        }
        tcpboot.post(new Runnable() {
            @Override
            public void run() {
                tcpboot.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


                                                       @Override
                                                       public void onCheckedChanged(CompoundButton buttonView,
                                                                                    boolean isChecked) {

                                                           if (isChecked) {
                                                               if (RootTools.isAccessGiven()) {
                                                                   Command command1 = new Command(0,
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                           "cp /data/data/com.nowenui.systemtweaker/files/12tcp /system/etc/init.d/",
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox echo '$busybox echo \"" + text5 + "\" > /proc/sys/net/ipv4/tcp_congestion_control' >> /system/etc/init.d/12tcp",
                                                                           "chmod 777 /system/etc/init.d/12tcp",
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system");
                                                                   try {
                                                                       RootTools.getShell(true).add(command1);
                                                                       new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.ok)).withBackgroundColorId(R.color.textview1good).show();
                                                                   } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                                       ex.printStackTrace();
                                                                       new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                                                                   }
                                                               } else {
                                                                   new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.textview1bad).show();
                                                               }

                                                           } else {
                                                               if (RootTools.isAccessGiven()) {
                                                                   Command command1 = new Command(0,
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                           "rm -f /system/etc/init.d/12tcp",
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                                                   );
                                                                   try {
                                                                       RootTools.getShell(true).add(command1);
                                                                       new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.disable)).withBackgroundColorId(R.color.textview1good).show();
                                                                   } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                                       ex.printStackTrace();
                                                                       new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                                                                   }
                                                               } else {
                                                                   new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.textview1bad).show();
                                                               }
                                                           }
                                                       }
                                                   }

                );
            }
        });

        //////////////////////////////////////////////////////////////////////////////////////////////
        ////// Internet Tweaks.///////////////////////////////////////////////////////////////////////
        /// Improving WiFi/3G/2G/4G tweaks, increasing download speed, improving stability ///////////
        //////////////////////////////////////////////////////////////////////////////////////////////
        CheckBox checkbox10 = view.findViewById(R.id.checkBox10);
        if (text2.toString().contains("net.tcp.buffersize.default=4096,87380,256960,4096,16384,256960")
                && text2.toString().contains("net.tcp.buffersize.wifi=4096,87380,256960,4096,16384,256960")
                && text2.toString().contains("net.tcp.buffersize.umts=4096,87380,256960,4096,16384,256960")
                && text2.toString().contains("net.tcp.buffersize.gprs=4096,87380,256960,4096,16384,256960")
                && text2.toString().contains("net.tcp.buffersize.edge=4096,87380,256960,4096,16384,256960")
                && text2.toString().contains("net.tcp.buffersize.hspa=6144,87380,524288,6144,163 84,262144")
                && text2.toString().contains("net.tcp.buffersize.lte=524288,1048576,2097152,5242 88,1048576,2097152")
                && text2.toString().contains("net.tcp.buffersize.hsdpa=6144,87380,1048576,6144,8 7380,1048576")
                && text2.toString().contains("net.tcp.buffersize.evdo_b=6144,87380,1048576,6144, 87380,1048576")) {
            checkbox10.setChecked(true);
        } else {
            checkbox10.setChecked(false);
        }
        checkbox10.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                if (Utility.getTheme(getActivity().getApplicationContext()) == 1) {
                    new AlertDialog.Builder(getContext())
                            .setTitle(R.string.tweakabout)
                            .setMessage(R.string.inet1)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(R.drawable.warning)
                            .show();
                }
                if (Utility.getTheme(getActivity().getApplicationContext()) == 2) {
                    new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark))
                            .setTitle(R.string.tweakabout)
                            .setMessage(R.string.inet1)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(R.drawable.warning)
                            .show();
                }
                if (Utility.getTheme(getActivity().getApplicationContext()) == 3) {
                    new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack))
                            .setTitle(R.string.tweakabout)
                            .setMessage(R.string.inet1)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(R.drawable.warning)
                            .show();
                }
                return true;
            }
        });
        checkbox10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


                                                  @Override
                                                  public void onCheckedChanged(CompoundButton buttonView,
                                                                               boolean isChecked) {
                                                      if (isChecked) {


                                                          if (RootTools.isAccessGiven()) {
                                                              Command command1 = new Command(0,
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/net.tcp.buffersize.default/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/net.tcp.buffersize.wifi/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/net.tcp.buffersize.umts/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/net.tcp.buffersize.gprs/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/net.tcp.buffersize.edge/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/net.tcp.buffersize.hspa/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/net.tcp.buffersize.lte/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/net.tcp.buffersize.hsdpa/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/net.tcp.buffersize.evdo_b/d' /system/build.prop",
                                                                      "echo \"net.tcp.buffersize.default=4096,87380,256960,4096,16384,256960\" >> /system/build.prop",
                                                                      "echo \"net.tcp.buffersize.wifi=4096,87380,256960,4096,16384,256960\" >> /system/build.prop",
                                                                      "echo \"net.tcp.buffersize.umts=4096,87380,256960,4096,16384,256960\" >> /system/build.prop",
                                                                      "echo \"net.tcp.buffersize.gprs=4096,87380,256960,4096,16384,256960\" >> /system/build.prop",
                                                                      "echo \"net.tcp.buffersize.edge=4096,87380,256960,4096,16384,256960\" >> /system/build.prop",
                                                                      "echo \"net.tcp.buffersize.hspa=6144,87380,524288,6144,163 84,262144\" >> /system/build.prop",
                                                                      "echo \"net.tcp.buffersize.lte=524288,1048576,2097152,5242 88,1048576,2097152\" >> /system/build.prop",
                                                                      "echo \"net.tcp.buffersize.hsdpa=6144,87380,1048576,6144,8 7380,1048576\" >> /system/build.prop",
                                                                      "echo \"net.tcp.buffersize.evdo_b=6144,87380,1048576,6144, 87380,1048576\" >> /system/build.prop",
                                                                      "setprop net.tcp.buffersize.default 4096,87380,256960,4096,16384,256960",
                                                                      "setprop net.tcp.buffersize.wifi 4096,87380,256960,4096,16384,256960",
                                                                      "setprop net.tcp.buffersize.umts 4096,87380,256960,4096,16384,256960",
                                                                      "setprop net.tcp.buffersize.gprs 4096,87380,256960,4096,16384,256960",
                                                                      "setprop net.tcp.buffersize.edge 4096,87380,256960,4096,16384,256960",
                                                                      "setprop net.tcp.buffersize.hspa 6144,87380,524288,6144,163 84,262144",
                                                                      "setprop net.tcp.buffersize.lte 524288,1048576,2097152,5242 88,1048576,2097152",
                                                                      "setprop net.tcp.buffersize.hsdpa 6144,87380,1048576,6144,8 7380,1048576",
                                                                      "setprop net.tcp.buffersize.evdo_b 6144,87380,1048576,6144, 87380,1048576",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system");
                                                              try {
                                                                  RootTools.getShell(true).add(command1);
                                                                  new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakenabled)).withBackgroundColorId(R.color.textview1good).show();
                                                              } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                                  ex.printStackTrace();
                                                                  new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                                                              }
                                                          } else {
                                                              new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.textview1bad).show();
                                                          }

                                                      } else {

                                                          if (RootTools.isAccessGiven()) {
                                                              Command command1 = new Command(0,
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/net.tcp.buffersize.default/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/net.tcp.buffersize.wifi/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/net.tcp.buffersize.umts/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/net.tcp.buffersize.gprs/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/net.tcp.buffersize.edge/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/net.tcp.buffersize.hspa/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/net.tcp.buffersize.lte/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/net.tcp.buffersize.hsdpa/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/net.tcp.buffersize.evdo_b/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                                              );
                                                              try {
                                                                  RootTools.getShell(true).add(command1);
                                                                  new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakdisabled)).withBackgroundColorId(R.color.textview1good).show();
                                                              } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                                  ex.printStackTrace();
                                                                  new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                                                              }
                                                          } else {
                                                              new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.textview1bad).show();
                                                          }
                                                      }

                                                  }
                                              }

        );

        ////////////////////////////
        ////// TCP Tweak ///////////
        ////////////////////////////
        CheckBox tcpinettweak = view.findViewById(R.id.tcpinettweak);
        if (text2.toString().contains("default_secure_redirects=0")
                && text2.toString().contains("default_accept_redirects=0")
                && text2.toString().contains("default_accept_source_route=0")
                && text2.toString().contains("all_secure_redirects=0")
                && text2.toString().contains("all_accept_redirects=0")
                && text2.toString().contains("all_accept_source_route=0")
                && text2.toString().contains("ip_forward=0")
                && text2.toString().contains("ip_dynaddr=0")
                && text2.toString().contains("ip_no_pmtu_disc=0")
                && text2.toString().contains("tcp_ecn=2")
                && text2.toString().contains("tcp_timestamps=1")
                && text2.toString().contains("tcp_low_latency=0")
                && text2.toString().contains("tcp_tw_reuse=1")
                && text2.toString().contains("tcp_fack=1")
                && text2.toString().contains("tcp_sack=1")
                && text2.toString().contains("tcp_dsack=1")
                && text2.toString().contains("tcp_rfc1337=1")
                && text2.toString().contains("tcp_tw_recycle=1")
                && text2.toString().contains("tcp_window_scaling=1")
                && text2.toString().contains("tcp_moderate_rcvbuf=1")
                && text2.toString().contains("tcp_no_metrics_save=1")
                && text2.toString().contains("tcp_synack_retries=2")
                && text2.toString().contains("tcp_syn_retries=2")
                && text2.toString().contains("tcp_keepalive_probes=5")
                && text2.toString().contains("tcp_keepalive_intvl=30")
                && text2.toString().contains("tcp_fin_timeout=30")) {
            tcpinettweak.setChecked(true);
        } else {
            tcpinettweak.setChecked(false);
        }

        tcpinettweak.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                if (Utility.getTheme(getActivity().getApplicationContext()) == 1) {
                    new AlertDialog.Builder(getContext())
                            .setTitle(R.string.tweakabout)
                            .setMessage(R.string.tcptweakfulllinfo)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(R.drawable.warning)
                            .show();
                }
                if (Utility.getTheme(getActivity().getApplicationContext()) == 2) {
                    new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark))
                            .setTitle(R.string.tweakabout)
                            .setMessage(R.string.tcptweakfulllinfo)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(R.drawable.warning)
                            .show();
                }
                if (Utility.getTheme(getActivity().getApplicationContext()) == 3) {
                    new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack))
                            .setTitle(R.string.tweakabout)
                            .setMessage(R.string.tcptweakfulllinfo)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(R.drawable.warning)
                            .show();
                }

                return true;
            }
        });

        tcpinettweak.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


                                                    @Override
                                                    public void onCheckedChanged(CompoundButton buttonView,
                                                                                 boolean isChecked) {

                                                        if (isChecked) {


                                                            if (RootTools.isAccessGiven()) {
                                                                Command command1 = new Command(0,
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/default_secure_redirects/d' /system/build.prop",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/default_accept_redirects/d' /system/build.prop",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/default_accept_source_route/d' /system/build.prop",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/all_secure_redirects/d' /system/build.prop",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/all_accept_redirects/d' /system/build.prop",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/all_accept_source_route/d' /system/build.prop",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ip_forward/d' /system/build.prop",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ip_dynaddr/d' /system/build.prop",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ip_no_pmtu_disc/d' /system/build.prop",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/tcp_ecn/d' /system/build.prop",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/tcp_timestamps/d' /system/build.prop",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/tcp_low_latency/d' /system/build.prop",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/tcp_tw_reuse/d' /system/build.prop",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/tcp_fack/d' /system/build.prop",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/tcp_sack/d' /system/build.prop",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/tcp_dsack/d' /system/build.prop",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/tcp_rfc1337/d' /system/build.prop",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/tcp_tw_recycle/d' /system/build.prop",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/tcp_window_scaling/d' /system/build.prop",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/tcp_moderate_rcvbuf/d' /system/build.prop",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/tcp_no_metrics_save/d' /system/build.prop",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/tcp_synack_retries/d' /system/build.prop",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/tcp_syn_retries/d' /system/build.prop",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/tcp_keepalive_probes/d' /system/build.prop",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/tcp_keepalive_intvl/d' /system/build.prop",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/tcp_fin_timeout/d' /system/build.prop",
                                                                        "echo \"default_secure_redirects=0\" >> /system/build.prop",
                                                                        "echo \"default_accept_redirects=0\" >> /system/build.prop",
                                                                        "echo \"default_accept_source_route=0\" >> /system/build.prop",
                                                                        "echo \"all_secure_redirects=0\" >> /system/build.prop",
                                                                        "echo \"all_accept_redirects=0\" >> /system/build.prop",
                                                                        "echo \"all_accept_source_route=0\" >> /system/build.prop",
                                                                        "echo \"ip_forward=0\" >> /system/build.prop",
                                                                        "echo \"ip_dynaddr=0\" >> /system/build.prop",
                                                                        "echo \"ip_no_pmtu_disc=0\" >> /system/build.prop",
                                                                        "echo \"tcp_ecn=2\" >> /system/build.prop",
                                                                        "echo \"tcp_timestamps=1\" >> /system/build.prop",
                                                                        "echo \"tcp_low_latency=0\" >> /system/build.prop",
                                                                        "echo \"tcp_tw_reuse=1\" >> /system/build.prop",
                                                                        "echo \"tcp_fack=1\" >> /system/build.prop",
                                                                        "echo \"tcp_sack=1\" >> /system/build.prop",
                                                                        "echo \"tcp_dsack=1\" >> /system/build.prop",
                                                                        "echo \"tcp_rfc1337=1\" >> /system/build.prop",
                                                                        "echo \"tcp_tw_recycle=1\" >> /system/build.prop",
                                                                        "echo \"tcp_window_scaling=1\" >> /system/build.prop",
                                                                        "echo \"tcp_moderate_rcvbuf=1\" >> /system/build.prop",
                                                                        "echo \"tcp_no_metrics_save=1\" >> /system/build.prop",
                                                                        "echo \"tcp_synack_retries=2\" >> /system/build.prop",
                                                                        "echo \"tcp_syn_retries=2\" >> /system/build.prop",
                                                                        "echo \"tcp_keepalive_probes=5\" >> /system/build.prop",
                                                                        "echo \"tcp_keepalive_intvl=30\" >> /system/build.prop",
                                                                        "echo \"tcp_fin_timeout=30\" >> /system/build.prop",
                                                                        "setprop default_secure_redirects 0",
                                                                        "setprop default_accept_redirects 0",
                                                                        "setprop default_accept_source_route 0",
                                                                        "setprop all_secure_redirects 0p",
                                                                        "setprop all_accept_redirects 0",
                                                                        "setprop all_accept_source_route 0",
                                                                        "setprop ip_forward 0",
                                                                        "setprop ip_dynaddr 0",
                                                                        "setprop ip_no_pmtu_disc 0",
                                                                        "setprop tcp_ecn 2",
                                                                        "setprop tcp_timestamps 1",
                                                                        "setprop tcp_low_latency 0",
                                                                        "setprop tcp_tw_reuse 1",
                                                                        "setprop tcp_fack 1",
                                                                        "setprop tcp_sack 1",
                                                                        "setprop tcp_dsack 1",
                                                                        "setprop tcp_rfc1337 1",
                                                                        "setprop tcp_tw_recycle 1",
                                                                        "setprop tcp_window_scaling 1",
                                                                        "setprop tcp_moderate_rcvbuf 1",
                                                                        "setprop tcp_no_metrics_save 1",
                                                                        "setprop tcp_synack_retries 2",
                                                                        "setprop tcp_syn_retries 2",
                                                                        "setprop tcp_keepalive_probes 5",
                                                                        "setprop tcp_keepalive_intvl 30",
                                                                        "setprop tcp_fin_timeout 30",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system");
                                                                try {
                                                                    RootTools.getShell(true).add(command1);
                                                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakenabled)).withBackgroundColorId(R.color.textview1good).show();
                                                                } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                                    ex.printStackTrace();
                                                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                                                                }
                                                            } else {
                                                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.textview1bad).show();
                                                            }

                                                        } else {

                                                            if (RootTools.isAccessGiven()) {
                                                                Command command1 = new Command(0,
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/default_secure_redirects/d' /system/build.prop",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/default_accept_redirects/d' /system/build.prop",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/default_accept_source_route/d' /system/build.prop",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/all_secure_redirects/d' /system/build.prop",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/all_accept_redirects/d' /system/build.prop",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/all_accept_source_route/d' /system/build.prop",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ip_forward/d' /system/build.prop",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ip_dynaddr/d' /system/build.prop",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ip_no_pmtu_disc/d' /system/build.prop",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/tcp_ecn/d' /system/build.prop",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/tcp_timestamps/d' /system/build.prop",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/tcp_low_latency/d' /system/build.prop",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/tcp_tw_reuse/d' /system/build.prop",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/tcp_fack/d' /system/build.prop",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/tcp_sack/d' /system/build.prop",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/tcp_dsack/d' /system/build.prop",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/tcp_rfc1337/d' /system/build.prop",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/tcp_tw_recycle/d' /system/build.prop",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/tcp_window_scaling/d' /system/build.prop",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/tcp_moderate_rcvbuf/d' /system/build.prop",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/tcp_no_metrics_save/d' /system/build.prop",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/tcp_synack_retries/d' /system/build.prop",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/tcp_syn_retries/d' /system/build.prop",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/tcp_keepalive_probes/d' /system/build.prop",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/tcp_keepalive_intvl/d' /system/build.prop",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/tcp_fin_timeout/d' /system/build.prop",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                                                );
                                                                try {
                                                                    RootTools.getShell(true).add(command1);
                                                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakdisabled)).withBackgroundColorId(R.color.textview1good).show();
                                                                } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                                    ex.printStackTrace();
                                                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                                                                }
                                                            } else {
                                                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.textview1bad).show();
                                                            }
                                                        }

                                                    }
                                                }

        );

        ///////////////////////////////////////////////////////////////////
        ////// Improving internet speed ///////////////////////////////////
        ////// Changing the TCP  an other values for the better ///////////
        ///////////////////////////////////////////////////////////////////
        CheckBox checkbox11 = view.findViewById(R.id.checkBox11);
        String check6 = "/etc/init.d/05InternetTweak";
        String check6a = "/system/etc/init.d/05InternetTweak";
        if ((new File(Environment.getRootDirectory() + check6).exists() || new File(check6a).exists() || new File(Environment.getRootDirectory() + check6a).exists()) &&
                text2.toString().contains("ro.ril.hsxpa=2")
                && text2.toString().contains("ro.ril.hep=1")
                && text2.toString().contains("ro.ril.enable.dtm=1")
                && text2.toString().contains("ro.ril.hsdpa.category=10")
                && text2.toString().contains("ro.ril.enable.a53=1")
                && text2.toString().contains("ro.ril.enable.3g.prefix=1")
                && text2.toString().contains("ro.ril.gprsclass=10")
                && text2.toString().contains("ro.ril.hsupa.category=7")
                && text2.toString().contains("ro.ril.hsdpa.category=10") &&
                text2.toString().contains("ro.ril.enable.a52=1")
                && text2.toString().contains("ro.ril.set.mtu1472=1")
                && text2.toString().contains("persist.cust.tel.eons=1")) {
            checkbox11.setChecked(true);
        } else {
            checkbox11.setChecked(false);
        }
        if (mSharedPreference.contains("skipnitd")) {
            checkbox11.setEnabled(false);
        } else {
            checkbox11.setEnabled(true);
        }
        checkbox11.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                if (Utility.getTheme(getActivity().getApplicationContext()) == 1) {
                    new AlertDialog.Builder(getContext())
                            .setTitle(R.string.tweakabout)
                            .setMessage(R.string.inet2)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(R.drawable.warning)
                            .show();
                }
                if (Utility.getTheme(getActivity().getApplicationContext()) == 2) {
                    new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark))
                            .setTitle(R.string.tweakabout)
                            .setMessage(R.string.inet2)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(R.drawable.warning)
                            .show();
                }
                if (Utility.getTheme(getActivity().getApplicationContext()) == 3) {
                    new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack))
                            .setTitle(R.string.tweakabout)
                            .setMessage(R.string.inet2)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(R.drawable.warning)
                            .show();
                }

                return true;
            }
        });
        checkbox11.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


                                                  @Override
                                                  public void onCheckedChanged(CompoundButton buttonView,
                                                                               boolean isChecked) {
                                                      if (isChecked) {
                                                          if (RootTools.isAccessGiven()) {
                                                              Command command1 = new Command(0,
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.ril.hsxpa/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.ril.hep/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.ril.enable.dtm/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.ril.hsdpa.category/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.ril.enable.a53/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.ril.enable.3g.prefix/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.ril.gprsclass/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.ril.hsupa.category/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.ril.hsdpa.category/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.ril.enable.3g.prefix/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.ril.enable.a52/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.ril.set.mtu1472/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/persist.cust.tel.eons/d' /system/build.prop",
                                                                      "echo \"ro.ril.hsxpa=2\" >> /system/build.prop",
                                                                      "echo \"ro.ril.hep=1\" >> /system/build.prop",
                                                                      "echo \"ro.ril.enable.dtm=1\" >> /system/build.prop",
                                                                      "echo \"ro.ril.hsdpa.category=10\" >> /system/build.prop",
                                                                      "echo \"ro.ril.enable.a53=1\" >> /system/build.prop",
                                                                      "echo \"ro.ril.enable.3g.prefix=1\" >> /system/build.prop",
                                                                      "echo \"ro.ril.gprsclass=10\" >> /system/build.prop",
                                                                      "echo \"ro.ril.hsupa.category=7\" >> /system/build.prop",
                                                                      "echo \"ro.ril.hsdpa.category=10\" >> /system/build.prop",
                                                                      "echo \"ro.ril.enable.a52=1\" >> /system/build.prop",
                                                                      "echo \"ro.ril.set.mtu1472=1\" >> /system/build.prop",
                                                                      "echo \"persist.cust.tel.eons=1\" >> /system/build.prop",
                                                                      "setprop ro.ril.hsxpa 2",
                                                                      "setprop ro.ril.hep 1",
                                                                      "setprop ro.ril.enable.dtm 1",
                                                                      "setprop ro.ril.hsdpa.category 10",
                                                                      "setprop ro.ril.enable.a53 1",
                                                                      "setprop ro.ril.enable.3g.prefix 1",
                                                                      "setprop ro.ril.gprsclass 10",
                                                                      "setprop ro.ril.hsupa.category 7",
                                                                      "setprop ro.ril.hsdpa.category 10",
                                                                      "setprop ro.ril.enable.a52 1",
                                                                      "setprop ro.ril.set.mtu1472 1",
                                                                      "setprop persist.cust.tel.eons 1",
                                                                      "cp /data/data/com.nowenui.systemtweaker/files/05InternetTweak /system/etc/init.d/",
                                                                      "chmod 777 /system/etc/init.d/05InternetTweak",
                                                                      "/system/etc/init.d/05InternetTweak",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system");
                                                              try {
                                                                  RootTools.getShell(true).add(command1);
                                                                  new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakenabled)).withBackgroundColorId(R.color.textview1good).show();
                                                              } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                                  ex.printStackTrace();
                                                                  new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                                                              }
                                                          } else {
                                                              new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.textview1bad).show();
                                                          }

                                                      } else {

                                                          if (RootTools.isAccessGiven()) {
                                                              Command command1 = new Command(0,
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.ril.hsxpa/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.ril.hep/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.ril.enable.dtm/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.ril.hsdpa.category/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.ril.enable.a53/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.ril.enable.3g.prefix/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.ril.gprsclass/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.ril.hsupa.category/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.ril.hsdpa.category/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.ril.enable.3g.prefix/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.ril.enable.a52/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.ril.set.mtu1472/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/persist.cust.tel.eons/d' /system/build.prop",
                                                                      "rm -f /system/etc/init.d/05InternetTweak",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                                              );
                                                              try {
                                                                  RootTools.getShell(true).add(command1);
                                                                  new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakdisabled)).withBackgroundColorId(R.color.textview1good).show();
                                                              } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                                  ex.printStackTrace();
                                                                  new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                                                              }
                                                          } else {
                                                              new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.textview1bad).show();
                                                          }
                                                      }

                                                  }
                                              }

        );

        ////////////////////////////
        ////// DNS Tweak ///////////
        ////////////////////////////
        CheckBox checkbox12 = view.findViewById(R.id.checkBox12);
        if (text2.toString().contains("net.dns1=8.8.8.8")
                && text2.toString().contains("net.dns2=8.8.4.4")
                && text2.toString().contains("net.rmnet0.dns1=8.8.8.8")
                && text2.toString().contains("net.rmnet0.dns2=8.8.4.4")
                && text2.toString().contains("net.ppp0.dns1=8.8.8.8")
                && text2.toString().contains("net.ppp0.dns2=8.8.4.4")
                && text2.toString().contains("net.wlan0.dns1=8.8.8.8")
                && text2.toString().contains("net.wlan0.dns2=8.8.4.4")
                && text2.toString().contains("net.eth0.dns1=8.8.8.8")
                && text2.toString().contains("net.eth0.dns2=8.8.4.4")
                && text2.toString().contains("net.gprs.dns1=8.8.8.8")
                && text2.toString().contains("net.gprs.dns2=8.8.4.4")) {
            checkbox12.setChecked(true);
        } else {
            checkbox12.setChecked(false);
        }

        checkbox12.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                if (Utility.getTheme(getActivity().getApplicationContext()) == 1) {
                    new AlertDialog.Builder(getContext())
                            .setTitle(R.string.tweakabout)
                            .setMessage(R.string.inet3)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(R.drawable.warning)
                            .show();
                }
                if (Utility.getTheme(getActivity().getApplicationContext()) == 2) {
                    new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark))
                            .setTitle(R.string.tweakabout)
                            .setMessage(R.string.inet3)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(R.drawable.warning)
                            .show();
                }
                if (Utility.getTheme(getActivity().getApplicationContext()) == 3) {
                    new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack))
                            .setTitle(R.string.tweakabout)
                            .setMessage(R.string.inet3)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(R.drawable.warning)
                            .show();
                }

                return true;
            }
        });

        checkbox12.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


                                                  @Override
                                                  public void onCheckedChanged(CompoundButton buttonView,
                                                                               boolean isChecked) {

                                                      if (isChecked) {

                                                          if (RootTools.isAccessGiven()) {
                                                              Command command1 = new Command(0,
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/net.dns1/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/net.dns2/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/net.rmnet0.dns1/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/net.rmnet0.dns2/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/net.ppp0.dns1/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/net.ppp0.dns2/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/net.wlan0.dns1/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/net.wlan0.dns2/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/net.eth0.dns1/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/net.eth0.dns2/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/net.gprs.dns1/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/net.gprs.dns2/d' /system/build.prop",
                                                                      "echo \"net.dns1=8.8.8.8\" >> /system/build.prop",
                                                                      "echo \"net.dns2=8.8.4.4\" >> /system/build.prop",
                                                                      "echo \"net.rmnet0.dns1=8.8.8.8\" >> /system/build.prop",
                                                                      "echo \"net.rmnet0.dns2=8.8.4.4\" >> /system/build.prop",
                                                                      "echo \"net.ppp0.dns1=8.8.8.8\" >> /system/build.prop",
                                                                      "echo \"net.ppp0.dns2=8.8.4.4\" >> /system/build.prop",
                                                                      "echo \"net.wlan0.dns1=8.8.8.8\" >> /system/build.prop",
                                                                      "echo \"net.wlan0.dns2=8.8.4.4\" >> /system/build.prop",
                                                                      "echo \"net.eth0.dns1=8.8.8.8\" >> /system/build.prop",
                                                                      "echo \"net.eth0.dns2=8.8.4.4\" >> /system/build.prop",
                                                                      "echo \"net.gprs.dns1=8.8.8.8\" >> /system/build.prop",
                                                                      "echo \"net.gprs.dns2=8.8.4.4\" >> /system/build.prop",
                                                                      "setprop net.dns1 8.8.8.8",
                                                                      "setprop net.dns2 8.8.4.4",
                                                                      "setprop net.rmnet0.dns1 8.8.8.8",
                                                                      "setprop net.rmnet0.dns2 8.8.4.4",
                                                                      "setprop net.ppp0.dns1 8.8.8.8",
                                                                      "setprop net.ppp0.dns2 8.8.4.4",
                                                                      "setprop net.wlan0.dns1 8.8.8.8",
                                                                      "setprop net.wlan0.dns2 8.8.4.4",
                                                                      "setprop net.eth0.dns1 8.8.8.8",
                                                                      "setprop net.eth0.dns2 8.8.4.4",
                                                                      "setprop net.gprs.dns1 8.8.8.8",
                                                                      "setprop net.gprs.dns2 8.8.4.4",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system");
                                                              try {
                                                                  RootTools.getShell(true).add(command1);
                                                                  new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakenabled)).withBackgroundColorId(R.color.textview1good).show();
                                                              } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                                  ex.printStackTrace();
                                                                  new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                                                              }
                                                          } else {
                                                              new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.textview1bad).show();
                                                          }

                                                      } else {

                                                          if (RootTools.isAccessGiven()) {
                                                              Command command1 = new Command(0,
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/net.dns1/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/net.dns2/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/net.rmnet0.dns1/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/net.rmnet0.dns2/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/net.ppp0.dns1/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/net.ppp0.dns2/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/net.wlan0.dns1/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/net.wlan0.dns2/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/net.eth0.dns1/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/net.eth0.dns2/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/net.gprs.dns1/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/net.gprs.dns2/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                                              );
                                                              try {
                                                                  RootTools.getShell(true).add(command1);
                                                                  new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakdisabled)).withBackgroundColorId(R.color.textview1good).show();
                                                              } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                                  ex.printStackTrace();
                                                                  new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                                                              }
                                                          } else {
                                                              new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.textview1bad).show();
                                                          }
                                                      }

                                                  }
                                              }

        );

        /////////////////////////////////////////////
        ////// Fast Dormancy Enable option///////////
        /////////////////////////////////////////////
        CheckBox checkbox13 = view.findViewById(R.id.checkBox13);
        if (text2.toString().contains("ro.config.hw_fast_dormancy=1")) {
            checkbox13.setChecked(true);
        } else {
            checkbox13.setChecked(false);
        }

        checkbox13.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                if (Utility.getTheme(getActivity().getApplicationContext()) == 1) {
                    new AlertDialog.Builder(getContext())
                            .setTitle(R.string.tweakabout)
                            .setMessage(R.string.inet4)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(R.drawable.warning)
                            .show();
                }
                if (Utility.getTheme(getActivity().getApplicationContext()) == 2) {
                    new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark))
                            .setTitle(R.string.tweakabout)
                            .setMessage(R.string.inet4)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(R.drawable.warning)
                            .show();
                }
                if (Utility.getTheme(getActivity().getApplicationContext()) == 3) {
                    new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack))
                            .setTitle(R.string.tweakabout)
                            .setMessage(R.string.inet4)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(R.drawable.warning)
                            .show();
                }
                return true;
            }
        });
        checkbox13.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


                                                  @Override
                                                  public void onCheckedChanged(CompoundButton buttonView,
                                                                               boolean isChecked) {
                                                      if (isChecked) {


                                                          if (RootTools.isAccessGiven()) {
                                                              Command command1 = new Command(0,
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.config.hw_fast_dormancy/d' /system/build.prop",
                                                                      "echo \"ro.config.hw_fast_dormancy=1\" >> /system/build.prop",
                                                                      "setprop ro.config.hw_fast_dormancy 1",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system");
                                                              try {
                                                                  RootTools.getShell(true).add(command1);
                                                                  new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakenabled)).withBackgroundColorId(R.color.textview1good).show();
                                                              } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                                  ex.printStackTrace();
                                                                  new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                                                              }
                                                          } else {
                                                              new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.textview1bad).show();
                                                          }


                                                      } else {

                                                          if (RootTools.isAccessGiven()) {
                                                              Command command1 = new Command(0,
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.config.hw_fast_dormancy/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                                              );
                                                              try {
                                                                  RootTools.getShell(true).add(command1);
                                                                  new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakdisabled)).withBackgroundColorId(R.color.textview1good).show();
                                                              } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                                  ex.printStackTrace();
                                                                  new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                                                              }
                                                          } else {
                                                              new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.textview1bad).show();
                                                          }

                                                      }

                                                  }
                                              }

        );

        //////////////////////////////////////
        ////// IPv6 Support option ///////////
        //////////////////////////////////////
        CheckBox checkbox14 = view.findViewById(R.id.checkBox14);
        if (text2.toString().contains("persist.telephony.support.ipv6=1")) {
            checkbox14.setChecked(true);
        } else {
            checkbox14.setChecked(false);
        }
        checkbox14.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                if (Utility.getTheme(getActivity().getApplicationContext()) == 1) {
                    new AlertDialog.Builder(getContext())
                            .setTitle(R.string.tweakabout)
                            .setMessage(R.string.inet5)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(R.drawable.warning)
                            .show();
                }
                if (Utility.getTheme(getActivity().getApplicationContext()) == 2) {
                    new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark))
                            .setTitle(R.string.tweakabout)
                            .setMessage(R.string.inet5)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(R.drawable.warning)
                            .show();
                }
                if (Utility.getTheme(getActivity().getApplicationContext()) == 3) {
                    new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack))
                            .setTitle(R.string.tweakabout)
                            .setMessage(R.string.inet5)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(R.drawable.warning)
                            .show();
                }

                return true;
            }
        });
        checkbox14.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


                                                  @Override
                                                  public void onCheckedChanged(CompoundButton buttonView,
                                                                               boolean isChecked) {

                                                      if (isChecked) {


                                                          if (RootTools.isAccessGiven()) {
                                                              Command command1 = new Command(0,
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/persist.telephony.support.ipv6/d' /system/build.prop",
                                                                      "echo \"persist.telephony.support.ipv6=1\" >> /system/build.prop",
                                                                      "setprop persist.telephony.support.ipv6 1",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system");
                                                              try {
                                                                  RootTools.getShell(true).add(command1);
                                                                  new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakenabled)).withBackgroundColorId(R.color.textview1good).show();
                                                              } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                                  ex.printStackTrace();
                                                                  new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                                                              }
                                                          } else {
                                                              new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.textview1bad).show();
                                                          }

                                                      } else {

                                                          if (RootTools.isAccessGiven()) {
                                                              Command command1 = new Command(0,
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/persist.telephony.support.ipv6/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                                              );
                                                              try {
                                                                  RootTools.getShell(true).add(command1);
                                                                  new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakdisabled)).withBackgroundColorId(R.color.textview1good).show();
                                                              } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                                  ex.printStackTrace();
                                                                  new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                                                              }
                                                          } else {
                                                              new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.textview1bad).show();
                                                          }
                                                      }

                                                  }
                                              }

        );

        //////////////////////////////////////
        ////// IPv4 support option ///////////
        /////////////////////////////////////
        CheckBox checkbox15 = view.findViewById(R.id.checkBox15);
        if (text2.toString().contains("persist.telephony.support.ipv4=1")) {
            checkbox15.setChecked(true);
        } else {
            checkbox15.setChecked(false);
        }
        checkbox15.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                if (Utility.getTheme(getActivity().getApplicationContext()) == 1) {
                    new AlertDialog.Builder(getContext())
                            .setTitle(R.string.tweakabout)
                            .setMessage(R.string.inet6)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(R.drawable.warning)
                            .show();
                }
                if (Utility.getTheme(getActivity().getApplicationContext()) == 2) {
                    new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark))
                            .setTitle(R.string.tweakabout)
                            .setMessage(R.string.inet6)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(R.drawable.warning)
                            .show();
                }
                if (Utility.getTheme(getActivity().getApplicationContext()) == 3) {
                    new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack))
                            .setTitle(R.string.tweakabout)
                            .setMessage(R.string.inet6)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(R.drawable.warning)
                            .show();
                }
                return true;
            }
        });
        checkbox15.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


                                                  @Override
                                                  public void onCheckedChanged(CompoundButton buttonView,
                                                                               boolean isChecked) {
                                                      if (isChecked) {
                                                          if (RootTools.isAccessGiven()) {
                                                              Command command1 = new Command(0,
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/persist.telephony.support.ipv4/d' /system/build.prop",
                                                                      "echo \"persist.telephony.support.ipv4=1\" >> /system/build.prop",
                                                                      "setprop persist.telephony.support.ipv4 1",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system");
                                                              try {
                                                                  RootTools.getShell(true).add(command1);
                                                                  new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakenabled)).withBackgroundColorId(R.color.textview1good).show();
                                                              } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                                  ex.printStackTrace();
                                                                  new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                                                              }
                                                          } else {
                                                              new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.textview1bad).show();
                                                          }


                                                      } else {

                                                          if (RootTools.isAccessGiven()) {
                                                              Command command1 = new Command(0,
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/persist.telephony.support.ipv4/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                                              );
                                                              try {
                                                                  RootTools.getShell(true).add(command1);
                                                                  new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakdisabled)).withBackgroundColorId(R.color.textview1good).show();
                                                              } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                                  ex.printStackTrace();
                                                                  new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                                                              }
                                                          } else {
                                                              new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.textview1bad).show();
                                                          }
                                                      }

                                                  }
                                              }

        );

        ////////////////////////////////////////////////////////////////////////////////
        ////// Improve 3G stability using compression. /////////////////////////////////
        ////// May load processor. It may not be supported by all devices.  ///////////
        ///////////////////////////////////////////////////////////////////////////////
        final CheckBox checkBox20171 = view.findViewById(R.id.checkBox20171);
        String check120171 = "/etc/ppp/options";
        String check20171a = "/system/etc/ppp/options";
        if (new File(Environment.getRootDirectory() + check120171).exists() || new File(check20171a).exists() || new File(Environment.getRootDirectory() + check20171a).exists()) {
            checkBox20171.setChecked(true);
        } else {
            checkBox20171.setChecked(false);
        }
        checkBox20171.post(new Runnable() {
            @Override
            public void run() {
                checkBox20171.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


                                                             @Override
                                                             public void onCheckedChanged(CompoundButton buttonView,
                                                                                          boolean isChecked) {

                                                                 if (isChecked) {


                                                                     if (RootTools.isAccessGiven()) {
                                                                         Command command1 = new Command(0,
                                                                                 "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                                 "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                                 "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                                 "mkdir /system/etc/ppp",
                                                                                 "cp /data/data/com.nowenui.systemtweaker/files/options /system/etc/ppp/",
                                                                                 "chmod 555 /system/etc/ppp/options",
                                                                                 "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                                 "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                                 "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system");
                                                                         try {
                                                                             RootTools.getShell(true).add(command1);
                                                                             new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.ok)).withBackgroundColorId(R.color.textview1good).show();
                                                                             if (Utility.getTheme(getActivity().getApplicationContext()) == 1) {
                                                                                 new AlertDialog.Builder(getContext())
                                                                                         .setTitle(R.string.reboot)
                                                                                         .setMessage(R.string.rebootdialog)
                                                                                         .setCancelable(false)
                                                                                         .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                                                                             public void onClick(DialogInterface dialog, int which) {
                                                                                                 try {
                                                                                                     Process proc = Runtime.getRuntime().exec(new String[]{"su", "-c", "reboot"});
                                                                                                     proc.waitFor();
                                                                                                 } catch (Exception ex) {
                                                                                                     new SnackBar.Builder(getActivity()).withMessage("ROOT NEEDED!").withBackgroundColorId(R.color.textview1bad).show();
                                                                                                 }
                                                                                             }
                                                                                         })
                                                                                         .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                                                                                             public void onClick(DialogInterface dialog, int which) {
                                                                                                 dialog.dismiss();
                                                                                             }
                                                                                         })
                                                                                         .setIcon(R.drawable.warning)
                                                                                         .show();
                                                                             }
                                                                             if (Utility.getTheme(getActivity().getApplicationContext()) == 2) {
                                                                                 new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark))
                                                                                         .setTitle(R.string.reboot)
                                                                                         .setMessage(R.string.rebootdialog)
                                                                                         .setCancelable(false)
                                                                                         .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                                                                             public void onClick(DialogInterface dialog, int which) {
                                                                                                 try {
                                                                                                     Process proc = Runtime.getRuntime().exec(new String[]{"su", "-c", "reboot"});
                                                                                                     proc.waitFor();
                                                                                                 } catch (Exception ex) {
                                                                                                     new SnackBar.Builder(getActivity()).withMessage("ROOT NEEDED!").withBackgroundColorId(R.color.textview1bad).show();
                                                                                                 }
                                                                                             }
                                                                                         })
                                                                                         .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                                                                                             public void onClick(DialogInterface dialog, int which) {
                                                                                                 dialog.dismiss();
                                                                                             }
                                                                                         })
                                                                                         .setIcon(R.drawable.warning)
                                                                                         .show();
                                                                             }
                                                                             if (Utility.getTheme(getActivity().getApplicationContext()) == 3) {
                                                                                 new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack))
                                                                                         .setTitle(R.string.reboot)
                                                                                         .setMessage(R.string.rebootdialog)
                                                                                         .setCancelable(false)
                                                                                         .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                                                                             public void onClick(DialogInterface dialog, int which) {
                                                                                                 try {
                                                                                                     Process proc = Runtime.getRuntime().exec(new String[]{"su", "-c", "reboot"});
                                                                                                     proc.waitFor();
                                                                                                 } catch (Exception ex) {
                                                                                                     new SnackBar.Builder(getActivity()).withMessage("ROOT NEEDED!").withBackgroundColorId(R.color.textview1bad).show();
                                                                                                 }
                                                                                             }
                                                                                         })
                                                                                         .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                                                                                             public void onClick(DialogInterface dialog, int which) {
                                                                                                 dialog.dismiss();
                                                                                             }
                                                                                         })
                                                                                         .setIcon(R.drawable.warning)
                                                                                         .show();
                                                                             }
                                                                         } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                                             ex.printStackTrace();
                                                                             new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                                                                         }
                                                                     } else {
                                                                         new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.textview1bad).show();
                                                                     }
                                                                 } else {

                                                                     if (RootTools.isAccessGiven()) {
                                                                         Command command1 = new Command(0,
                                                                                 "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                                 "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                                 "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                                 "rm -f /system/etc/ppp/options",
                                                                                 "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                                 "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                                 "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                                                         );
                                                                         try {
                                                                             RootTools.getShell(true).add(command1);
                                                                             new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.disable)).withBackgroundColorId(R.color.textview1good).show();
                                                                         } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                                             ex.printStackTrace();
                                                                             new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                                                                         }
                                                                     } else {
                                                                         new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.textview1bad).show();
                                                                     }
                                                                 }
                                                             }
                                                         }

                );
            }
        });
    }


}