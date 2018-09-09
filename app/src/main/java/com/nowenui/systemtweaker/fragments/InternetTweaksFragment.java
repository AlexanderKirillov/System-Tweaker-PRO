package com.nowenui.systemtweaker.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.github.mrengineer13.snackbar.SnackBar;
import com.nowenui.systemtweaker.HelperClass;
import com.nowenui.systemtweaker.R;
import com.nowenui.systemtweaker.ThemeUtility;
import com.onebit.spinner2.Spinner2;
import com.stericson.RootShell.exceptions.RootDeniedException;
import com.stericson.RootShell.execution.Command;
import com.stericson.RootTools.RootTools;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class InternetTweaksFragment extends Fragment {

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.internet_tweaks, parent, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        //////////////////////////////////////////////////////////////////////////////////////////////
        ////// Internet Tweaks.///////////////////////////////////////////////////////////////////////
        /// Improving WiFi/3G/2G/4G tweaks, increasing download speed, improving stability ///////////
        //////////////////////////////////////////////////////////////////////////////////////////////
        CheckBox internetbasetweak = view.findViewById(R.id.internetbasetweak);
        if (HelperClass.BuildPropText().toString().contains("net.tcp.buffersize.default=4096,87380,256960,4096,16384,256960")
                && HelperClass.BuildPropText().toString().contains("net.tcp.buffersize.wifi=4096,87380,256960,4096,16384,256960")
                && HelperClass.BuildPropText().toString().contains("net.tcp.buffersize.umts=4096,87380,256960,4096,16384,256960")
                && HelperClass.BuildPropText().toString().contains("net.tcp.buffersize.gprs=4096,87380,256960,4096,16384,256960")
                && HelperClass.BuildPropText().toString().contains("net.tcp.buffersize.edge=4096,87380,256960,4096,16384,256960")
                && HelperClass.BuildPropText().toString().contains("net.tcp.buffersize.hspa=6144,87380,524288,6144,163 84,262144")
                && HelperClass.BuildPropText().toString().contains("net.tcp.buffersize.lte=524288,1048576,2097152,5242 88,1048576,2097152")
                && HelperClass.BuildPropText().toString().contains("net.tcp.buffersize.hsdpa=6144,87380,1048576,6144,8 7380,1048576")
                && HelperClass.BuildPropText().toString().contains("net.tcp.buffersize.evdo_b=6144,87380,1048576,6144, 87380,1048576")) {
            internetbasetweak.setChecked(true);
        } else {
            internetbasetweak.setChecked(false);
        }
        internetbasetweak.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                AboutTweak(R.string.inet1);
                return true;
            }
        });
        internetbasetweak.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


                                                         @Override
                                                         public void onCheckedChanged(CompoundButton buttonView,
                                                                                      boolean isChecked) {
                                                             if (isChecked) {
                                                                 if (RootTools.isAccessGiven()) {
                                                                     @SuppressLint("SdCardPath") Command installtweak = new Command(0,
                                                                             "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                             "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                             "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                             "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/net.tcp.buffersize.default/d' /system/build.prop",
                                                                             "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/net.tcp.buffersize.WiFi/d' /system/build.prop",
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
                                                                         RootTools.getShell(true).add(installtweak);
                                                                         new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakenabled)).withBackgroundColorId(R.color.resultgood).show();
                                                                     } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                                         new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                                                                     }
                                                                 } else {
                                                                     new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                                                                 }
                                                             } else {
                                                                 if (RootTools.isAccessGiven()) {
                                                                     @SuppressLint("SdCardPath") Command deletetweak = new Command(0,
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
                                                                         RootTools.getShell(true).add(deletetweak);
                                                                         new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakdisabled)).withBackgroundColorId(R.color.resultgood).show();
                                                                     } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                                         new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                                                                     }
                                                                 } else {
                                                                     new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                                                                 }
                                                             }
                                                         }
                                                     }
        );

        ///////////////////////////////////
        ////// TCP mode changer ///////////
        ///////////////////////////////////

        final CheckBox tcpboot = view.findViewById(R.id.tcpboot);
        if (HelperClass.isInitdSupport() == 0) {
            tcpboot.setEnabled(false);
        } else {
            tcpboot.setEnabled(true);
        }
        TextView tcpmodeheader = view.findViewById(R.id.tcpmodeheader);
        tcpmodeheader.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                AboutTweak(R.string.inet7);
                return true;
            }
        });
        try {
            final Spinner2 tcpspinner = view.findViewById(R.id.tcpspinner);
            final String[] tcpmodes = HelperClass.readAvailableTCP();
            ArrayAdapter<String> TCPAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item,
                    tcpmodes);
            TCPAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            tcpspinner.setAdapter(TCPAdapter, false);
            final int selectedTCPMode = TCPAdapter.getPosition(HelperClass.TCPmode().toString());
            tcpspinner.post(new Runnable() {
                @Override
                public void run() {
                    tcpspinner.setSelection(false, selectedTCPMode);

                }
            });
            tcpspinner.post(new Runnable() {
                @Override
                public void run() {
                    tcpspinner.setOnItemSelectedSpinner2Listener(new Spinner2.OnItemSelectedSpinner2Listener() {
                        @SuppressLint("SdCardPath")
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            TextView tcptextview = (TextView) tcpspinner.getSelectedView();
                            String text = tcptextview.getText().toString();
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
                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.ok)).withBackgroundColorId(R.color.resultgood).show();
                            } catch (IOException e) {
                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
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
                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                                }
                            }
                        }
                    });
                }
            });
        } catch (Exception e) {
        }

        /////////////////////////////////////////////
        ////// Change TCP Mode after boot ///////////
        /////////////////////////////////////////////
        if (new File("/system/etc/init.d/12tcp").exists()) {
            tcpboot.setChecked(true);
        } else {
            tcpboot.setChecked(false);
        }
        if (HelperClass.isInitdSupport() == 0) {
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
                                                                   @SuppressLint("SdCardPath") Command installtcpboot = new Command(0,
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                           "cp /data/data/com.nowenui.systemtweaker/files/12tcp /system/etc/init.d/",
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox echo '$busybox echo \"" + HelperClass.TCPmode() + "\" > /proc/sys/net/ipv4/tcp_congestion_control' >> /system/etc/init.d/12tcp",
                                                                           "chmod 777 /system/etc/init.d/12tcp",
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system");
                                                                   try {
                                                                       RootTools.getShell(true).add(installtcpboot);
                                                                       new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.ok)).withBackgroundColorId(R.color.resultgood).show();
                                                                   } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                                       new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                                                                   }
                                                               } else {
                                                                   new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                                                               }
                                                           } else {
                                                               if (RootTools.isAccessGiven()) {
                                                                   @SuppressLint("SdCardPath") Command deletetcpboot = new Command(0,
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                           "rm -f /system/etc/init.d/12tcp",
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                                                   );
                                                                   try {
                                                                       RootTools.getShell(true).add(deletetcpboot);
                                                                       new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.disable)).withBackgroundColorId(R.color.resultgood).show();
                                                                   } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                                       new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                                                                   }
                                                               } else {
                                                                   new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                                                               }
                                                           }
                                                       }
                                                   }
                );
            }
        });

        ////////////////////////////
        ////// TCP Tweak ///////////
        ////////////////////////////
        CheckBox tcpinettweak = view.findViewById(R.id.tcpinettweak);
        if (HelperClass.BuildPropText().toString().contains("default_secure_redirects=0")
                && HelperClass.BuildPropText().toString().contains("default_accept_redirects=0")
                && HelperClass.BuildPropText().toString().contains("default_accept_source_route=0")
                && HelperClass.BuildPropText().toString().contains("all_secure_redirects=0")
                && HelperClass.BuildPropText().toString().contains("all_accept_redirects=0")
                && HelperClass.BuildPropText().toString().contains("all_accept_source_route=0")
                && HelperClass.BuildPropText().toString().contains("ip_forward=0")
                && HelperClass.BuildPropText().toString().contains("ip_dynaddr=0")
                && HelperClass.BuildPropText().toString().contains("ip_no_pmtu_disc=0")
                && HelperClass.BuildPropText().toString().contains("tcp_ecn=2")
                && HelperClass.BuildPropText().toString().contains("tcp_timestamps=1")
                && HelperClass.BuildPropText().toString().contains("tcp_low_latency=0")
                && HelperClass.BuildPropText().toString().contains("tcp_tw_reuse=1")
                && HelperClass.BuildPropText().toString().contains("tcp_fack=1")
                && HelperClass.BuildPropText().toString().contains("tcp_sack=1")
                && HelperClass.BuildPropText().toString().contains("tcp_dsack=1")
                && HelperClass.BuildPropText().toString().contains("tcp_rfc1337=1")
                && HelperClass.BuildPropText().toString().contains("tcp_tw_recycle=1")
                && HelperClass.BuildPropText().toString().contains("tcp_window_scaling=1")
                && HelperClass.BuildPropText().toString().contains("tcp_moderate_rcvbuf=1")
                && HelperClass.BuildPropText().toString().contains("tcp_no_metrics_save=1")
                && HelperClass.BuildPropText().toString().contains("tcp_synack_retries=2")
                && HelperClass.BuildPropText().toString().contains("tcp_syn_retries=2")
                && HelperClass.BuildPropText().toString().contains("tcp_keepalive_probes=5")
                && HelperClass.BuildPropText().toString().contains("tcp_keepalive_intvl=30")
                && HelperClass.BuildPropText().toString().contains("tcp_fin_timeout=30")) {
            tcpinettweak.setChecked(true);
        } else {
            tcpinettweak.setChecked(false);
        }
        tcpinettweak.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                AboutTweak(R.string.tcptweakfulllinfo);
                return true;
            }
        });
        tcpinettweak.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


                                                    @Override
                                                    public void onCheckedChanged(CompoundButton buttonView,
                                                                                 boolean isChecked) {
                                                        if (isChecked) {
                                                            if (RootTools.isAccessGiven()) {
                                                                @SuppressLint("SdCardPath") Command installtweak = new Command(0,
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
                                                                    RootTools.getShell(true).add(installtweak);
                                                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakenabled)).withBackgroundColorId(R.color.resultgood).show();
                                                                } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                                                                }
                                                            } else {
                                                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                                                            }
                                                        } else {
                                                            if (RootTools.isAccessGiven()) {
                                                                @SuppressLint("SdCardPath") Command deletetweak = new Command(0,
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
                                                                    RootTools.getShell(true).add(deletetweak);
                                                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakdisabled)).withBackgroundColorId(R.color.resultgood).show();
                                                                } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                                                                }
                                                            } else {
                                                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                                                            }
                                                        }
                                                    }
                                                }
        );

        ///////////////////////////////////////////////////////////////////
        ////// Improving Internet speed ///////////////////////////////////
        ////// Changing values for the better /////////////////////////////
        ///////////////////////////////////////////////////////////////////
        CheckBox inetspeedtweak = view.findViewById(R.id.inetspeedtweak);
        if ((new File("/system/etc/init.d/05InternetTweak").exists()
                && HelperClass.BuildPropText().toString().contains("ro.ril.hsxpa=2")
                && HelperClass.BuildPropText().toString().contains("ro.ril.hep=1")
                && HelperClass.BuildPropText().toString().contains("ro.ril.enable.dtm=1")
                && HelperClass.BuildPropText().toString().contains("ro.ril.hsdpa.category=10")
                && HelperClass.BuildPropText().toString().contains("ro.ril.enable.a53=1")
                && HelperClass.BuildPropText().toString().contains("ro.ril.enable.3g.prefix=1")
                && HelperClass.BuildPropText().toString().contains("ro.ril.gprsclass=10")
                && HelperClass.BuildPropText().toString().contains("ro.ril.hsupa.category=7")
                && HelperClass.BuildPropText().toString().contains("ro.ril.hsdpa.category=10")
                && HelperClass.BuildPropText().toString().contains("ro.ril.enable.a52=1")
                && HelperClass.BuildPropText().toString().contains("ro.ril.set.mtu1472=1")
                && HelperClass.BuildPropText().toString().contains("persist.cust.tel.eons=1"))) {
            inetspeedtweak.setChecked(true);
        } else {
            inetspeedtweak.setChecked(false);
        }
        if (HelperClass.isInitdSupport() == 0) {
            inetspeedtweak.setEnabled(false);
        } else {
            inetspeedtweak.setEnabled(true);
        }
        inetspeedtweak.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                AboutTweak(R.string.inet2);
                return true;
            }
        });
        inetspeedtweak.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


                                                      @Override
                                                      public void onCheckedChanged(CompoundButton buttonView,
                                                                                   boolean isChecked) {
                                                          if (isChecked) {
                                                              if (RootTools.isAccessGiven()) {
                                                                  @SuppressLint("SdCardPath") Command installtweak = new Command(0,
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
                                                                      RootTools.getShell(true).add(installtweak);
                                                                      new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakenabled)).withBackgroundColorId(R.color.resultgood).show();
                                                                  } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                                      new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                                                                  }
                                                              } else {
                                                                  new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                                                              }
                                                          } else {
                                                              if (RootTools.isAccessGiven()) {
                                                                  @SuppressLint("SdCardPath") Command deletetweak = new Command(0,
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
                                                                      RootTools.getShell(true).add(deletetweak);
                                                                      new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakdisabled)).withBackgroundColorId(R.color.resultgood).show();
                                                                  } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                                      new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                                                                  }
                                                              } else {
                                                                  new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                                                              }
                                                          }
                                                      }
                                                  }
        );

        ////////////////////////////////////////////////////////////////////////////////
        ////// Improve 3G stability using compression. /////////////////////////////////
        ////// May load processor. It may not be supported by all devices.  ////////////
        ////////////////////////////////////////////////////////////////////////////////
        final CheckBox ppptweak = view.findViewById(R.id.ppptweak);
        if (new File("/system/etc/ppp/options").exists()) {
            ppptweak.setChecked(true);
        } else {
            ppptweak.setChecked(false);
        }
        ppptweak.post(new Runnable() {
            @Override
            public void run() {
                ppptweak.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


                                                        @Override
                                                        public void onCheckedChanged(CompoundButton buttonView,
                                                                                     boolean isChecked) {
                                                            if (isChecked) {
                                                                if (RootTools.isAccessGiven()) {
                                                                    @SuppressLint("SdCardPath") Command installtweak = new Command(0,
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
                                                                        RootTools.getShell(true).add(installtweak);
                                                                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.ok)).withBackgroundColorId(R.color.resultgood).show();
                                                                        if (ThemeUtility.getTheme(getActivity().getApplicationContext()) == 1) {
                                                                            new AlertDialog.Builder(getContext())
                                                                                    .setTitle(R.string.reboot)
                                                                                    .setMessage(R.string.rebootdialog)
                                                                                    .setCancelable(false)
                                                                                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                                                                        public void onClick(DialogInterface dialog, int which) {
                                                                                            try {
                                                                                                Process proc = Runtime.getRuntime().exec(new String[]{"su", "-c", "reboot"});
                                                                                                proc.waitFor();
                                                                                            } catch (Exception ignored) {
                                                                                                new SnackBar.Builder(getActivity()).withMessage("ROOT NEEDED!").withBackgroundColorId(R.color.resultbad).show();
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
                                                                        if (ThemeUtility.getTheme(getActivity().getApplicationContext()) == 2) {
                                                                            new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark))
                                                                                    .setTitle(R.string.reboot)
                                                                                    .setMessage(R.string.rebootdialog)
                                                                                    .setCancelable(false)
                                                                                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                                                                        public void onClick(DialogInterface dialog, int which) {
                                                                                            try {
                                                                                                Process proc = Runtime.getRuntime().exec(new String[]{"su", "-c", "reboot"});
                                                                                                proc.waitFor();
                                                                                            } catch (Exception ignored) {
                                                                                                new SnackBar.Builder(getActivity()).withMessage("ROOT NEEDED!").withBackgroundColorId(R.color.resultbad).show();
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
                                                                        if (ThemeUtility.getTheme(getActivity().getApplicationContext()) == 3) {
                                                                            new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack))
                                                                                    .setTitle(R.string.reboot)
                                                                                    .setMessage(R.string.rebootdialog)
                                                                                    .setCancelable(false)
                                                                                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                                                                        public void onClick(DialogInterface dialog, int which) {
                                                                                            try {
                                                                                                Process proc = Runtime.getRuntime().exec(new String[]{"su", "-c", "reboot"});
                                                                                                proc.waitFor();
                                                                                            } catch (Exception ignored) {
                                                                                                new SnackBar.Builder(getActivity()).withMessage("ROOT NEEDED!").withBackgroundColorId(R.color.resultbad).show();
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

                                                                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                                                                    }
                                                                } else {
                                                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                                                                }
                                                            } else {
                                                                if (RootTools.isAccessGiven()) {
                                                                    @SuppressLint("SdCardPath") Command deletetweak = new Command(0,
                                                                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                            "rm -f /system/etc/ppp/options",
                                                                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                                                    );
                                                                    try {
                                                                        RootTools.getShell(true).add(deletetweak);
                                                                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.disable)).withBackgroundColorId(R.color.resultgood).show();
                                                                    } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                                                                    }
                                                                } else {
                                                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                                                                }
                                                            }
                                                        }
                                                    }
                );
            }
        });

        ////////////////////////////
        ////// DNS Tweak ///////////
        ////////////////////////////
        final CheckBox dnstweak = view.findViewById(R.id.dnstweak);
        if (HelperClass.BuildPropText().toString().contains("net.dns1=8.8.8.8")
                && HelperClass.BuildPropText().toString().contains("net.dns2=8.8.4.4")
                && HelperClass.BuildPropText().toString().contains("net.rmnet0.dns1=8.8.8.8")
                && HelperClass.BuildPropText().toString().contains("net.rmnet0.dns2=8.8.4.4")
                && HelperClass.BuildPropText().toString().contains("net.ppp0.dns1=8.8.8.8")
                && HelperClass.BuildPropText().toString().contains("net.ppp0.dns2=8.8.4.4")
                && HelperClass.BuildPropText().toString().contains("net.wlan0.dns1=8.8.8.8")
                && HelperClass.BuildPropText().toString().contains("net.wlan0.dns2=8.8.4.4")
                && HelperClass.BuildPropText().toString().contains("net.eth0.dns1=8.8.8.8")
                && HelperClass.BuildPropText().toString().contains("net.eth0.dns2=8.8.4.4")
                && HelperClass.BuildPropText().toString().contains("net.gprs.dns1=8.8.8.8")
                && HelperClass.BuildPropText().toString().contains("net.gprs.dns2=8.8.4.4")) {
            dnstweak.setChecked(true);
        } else {
            dnstweak.setChecked(false);
        }
        dnstweak.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                AboutTweak(R.string.inet3);
                return true;
            }
        });

        dnstweak.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


                                                @Override
                                                public void onCheckedChanged(CompoundButton buttonView,
                                                                             boolean isChecked) {
                                                    if (isChecked) {
                                                        if (RootTools.isAccessGiven()) {
                                                            @SuppressLint("SdCardPath") Command installtweak = new Command(0,
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
                                                                RootTools.getShell(true).add(installtweak);
                                                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakenabled)).withBackgroundColorId(R.color.resultgood).show();
                                                            } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                                                            }
                                                        } else {
                                                            new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                                                        }
                                                    } else {
                                                        if (RootTools.isAccessGiven()) {
                                                            @SuppressLint("SdCardPath") Command deletetweak = new Command(0,
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
                                                                RootTools.getShell(true).add(deletetweak);
                                                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakdisabled)).withBackgroundColorId(R.color.resultgood).show();
                                                            } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                                                            }
                                                        } else {
                                                            new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                                                        }
                                                    }
                                                }
                                            }
        );

        /////////////////////////////////////////////
        ////// Fast Dormancy Enable option///////////
        /////////////////////////////////////////////
        CheckBox fastdormancytweak = view.findViewById(R.id.fastdormancytweak);
        if (HelperClass.BuildPropText().toString().contains("ro.config.hw_fast_dormancy=1")) {
            fastdormancytweak.setChecked(true);
        } else {
            fastdormancytweak.setChecked(false);
        }
        fastdormancytweak.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                AboutTweak(R.string.inet4);
                return true;
            }
        });
        fastdormancytweak.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


                                                         @Override
                                                         public void onCheckedChanged(CompoundButton buttonView,
                                                                                      boolean isChecked) {
                                                             if (isChecked) {
                                                                 if (RootTools.isAccessGiven()) {
                                                                     @SuppressLint("SdCardPath") Command installtweak = new Command(0,
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
                                                                         RootTools.getShell(true).add(installtweak);
                                                                         new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakenabled)).withBackgroundColorId(R.color.resultgood).show();
                                                                     } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                                         new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                                                                     }
                                                                 } else {
                                                                     new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                                                                 }
                                                             } else {
                                                                 if (RootTools.isAccessGiven()) {
                                                                     @SuppressLint("SdCardPath") Command deletetweak = new Command(0,
                                                                             "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                             "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                             "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                             "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.config.hw_fast_dormancy/d' /system/build.prop",
                                                                             "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                             "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                             "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                                                     );
                                                                     try {
                                                                         RootTools.getShell(true).add(deletetweak);
                                                                         new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakdisabled)).withBackgroundColorId(R.color.resultgood).show();
                                                                     } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                                         new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                                                                     }
                                                                 } else {
                                                                     new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                                                                 }
                                                             }
                                                         }
                                                     }
        );

        //////////////////////////////////////
        ////// IPv4 support option ///////////
        /////////////////////////////////////
        CheckBox ipv4tweak = view.findViewById(R.id.ipv4tweak);
        if (HelperClass.BuildPropText().toString().contains("persist.telephony.support.ipv4=1")) {
            ipv4tweak.setChecked(true);
        } else {
            ipv4tweak.setChecked(false);
        }
        ipv4tweak.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                AboutTweak(R.string.inet6);
                return true;
            }
        });
        ipv4tweak.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


                                                 @Override
                                                 public void onCheckedChanged(CompoundButton buttonView,
                                                                              boolean isChecked) {
                                                     if (isChecked) {
                                                         if (RootTools.isAccessGiven()) {
                                                             @SuppressLint("SdCardPath") Command installtweak = new Command(0,
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
                                                                 RootTools.getShell(true).add(installtweak);
                                                                 new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakenabled)).withBackgroundColorId(R.color.resultgood).show();
                                                             } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                                 new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                                                             }
                                                         } else {
                                                             new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                                                         }
                                                     } else {
                                                         if (RootTools.isAccessGiven()) {
                                                             @SuppressLint("SdCardPath") Command deletetweak = new Command(0,
                                                                     "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                     "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                     "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                     "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/persist.telephony.support.ipv4/d' /system/build.prop",
                                                                     "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                     "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                     "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                                             );
                                                             try {
                                                                 RootTools.getShell(true).add(deletetweak);
                                                                 new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakdisabled)).withBackgroundColorId(R.color.resultgood).show();
                                                             } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                                 new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                                                             }
                                                         } else {
                                                             new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                                                         }
                                                     }
                                                 }
                                             }
        );

        //////////////////////////////////////
        ////// IPv6 Support option ///////////
        //////////////////////////////////////
        CheckBox ipv6tweak = view.findViewById(R.id.ipv6tweak);
        if (HelperClass.BuildPropText().toString().contains("persist.telephony.support.ipv6=1")) {
            ipv6tweak.setChecked(true);
        } else {
            ipv6tweak.setChecked(false);
        }
        ipv6tweak.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                AboutTweak(R.string.inet5);
                return true;
            }
        });
        ipv6tweak.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


                                                 @Override
                                                 public void onCheckedChanged(CompoundButton buttonView,
                                                                              boolean isChecked) {
                                                     if (isChecked) {
                                                         if (RootTools.isAccessGiven()) {
                                                             @SuppressLint("SdCardPath") Command installtweak = new Command(0,
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
                                                                 RootTools.getShell(true).add(installtweak);
                                                                 new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakenabled)).withBackgroundColorId(R.color.resultgood).show();
                                                             } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                                 new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                                                             }
                                                         } else {
                                                             new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                                                         }
                                                     } else {
                                                         if (RootTools.isAccessGiven()) {
                                                             @SuppressLint("SdCardPath") Command deletetweak = new Command(0,
                                                                     "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                     "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                     "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                     "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/persist.telephony.support.ipv6/d' /system/build.prop",
                                                                     "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                     "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                     "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                                             );
                                                             try {
                                                                 RootTools.getShell(true).add(deletetweak);
                                                                 new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakdisabled)).withBackgroundColorId(R.color.resultgood).show();
                                                             } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                                 new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                                                             }
                                                         } else {
                                                             new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                                                         }
                                                     }
                                                 }
                                             }
        );

    }

    public void AboutTweak(int tweaktext) {
        if (ThemeUtility.getTheme(getActivity().getApplicationContext()) == 1) {
            new AlertDialog.Builder(getContext())
                    .setTitle(R.string.tweakabout)
                    .setMessage(tweaktext)
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .setIcon(R.drawable.warning)
                    .show();
        }
        if (ThemeUtility.getTheme(getActivity().getApplicationContext()) == 2) {
            new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark))
                    .setTitle(R.string.tweakabout)
                    .setMessage(tweaktext)
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .setIcon(R.drawable.warning)
                    .show();
        }
        if (ThemeUtility.getTheme(getActivity().getApplicationContext()) == 3) {
            new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack))
                    .setTitle(R.string.tweakabout)
                    .setMessage(tweaktext)
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .setIcon(R.drawable.warning)
                    .show();
        }
    }
}