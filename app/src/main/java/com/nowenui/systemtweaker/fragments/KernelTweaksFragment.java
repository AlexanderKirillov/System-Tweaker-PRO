package com.nowenui.systemtweaker.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.github.mrengineer13.snackbar.SnackBar;
import com.nowenui.systemtweaker.HelperClass;
import com.nowenui.systemtweaker.R;
import com.nowenui.systemtweaker.ThemeUtility;
import com.stericson.RootShell.exceptions.RootDeniedException;
import com.stericson.RootShell.execution.Command;
import com.stericson.RootTools.RootTools;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class KernelTweaksFragment extends Fragment {

    public static KernelTweaksFragment newInstance(Bundle bundle) {
        KernelTweaksFragment KernelTweaks = new KernelTweaksFragment();

        if (bundle != null) {
            KernelTweaks.setArguments(bundle);
        }

        return KernelTweaks;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.kernel_tweaks, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        /////////////////////////////////////////////////////////////////////
        ////// MultiCorePowerSaving enable option (if available) ////////////
        /////////////////////////////////////////////////////////////////////
        CheckBox mpcstweak = view.findViewById(R.id.mpcstweak);

        if (HelperClass.mpcs() == 1) {
            if (HelperClass.isInitdSupport() == 0) {
                mpcstweak.setEnabled(false);
            } else {
                mpcstweak.setEnabled(true);
            }
            if (new File("/system/etc/init.d/mpcs").exists()) {
                mpcstweak.setChecked(true);
            } else {
                mpcstweak.setChecked(false);
            }
            mpcstweak.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View arg0) {
                    AboutTweak(R.string.ker11);
                    return true;
                }
            });
            mpcstweak.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


                                                     @Override
                                                     public void onCheckedChanged(CompoundButton buttonView,
                                                                                  boolean isChecked) {
                                                         if (isChecked) {
                                                             if (RootTools.isAccessGiven()) {
                                                                 @SuppressLint("SdCardPath") Command installtweak = new Command(0,
                                                                         "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                         "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                         "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                         "cp /data/data/com.nowenui.systemtweaker/files/mpcs /system/etc/init.d/",
                                                                         "chmod 777 /system/etc/init.d/mpcs",
                                                                         "/system/etc/init.d/mpcs",
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
                                                                         "rm -f /system/etc/init.d/mpcs",
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
        } else {
            mpcstweak.setEnabled(false);
            mpcstweak.setBackgroundResource(R.drawable.roundbuttonfuck);
            mpcstweak.setTextColor(Color.WHITE);
            mpcstweak.setText(R.string.mpcsbad);
        }

        //////////////////////////////////////////////
        ////// Interactive Governor Tweak ////////////
        //////////////////////////////////////////////
        CheckBox interactivetweak = view.findViewById(R.id.interactivetweak);

        if (HelperClass.InteractiveTweakSupport() == 1) {
            if (HelperClass.isInitdSupport() == 0) {
                interactivetweak.setEnabled(false);
            } else {
                interactivetweak.setEnabled(true);
            }
            if (new File("/system/etc/init.d/cpu_optimize").exists()) {
                interactivetweak.setChecked(true);
            } else {
                interactivetweak.setChecked(false);
            }
            interactivetweak.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View arg0) {
                    AboutTweak(R.string.ker8);
                    return true;
                }
            });
            interactivetweak.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                                            @Override
                                                            public void onCheckedChanged(CompoundButton buttonView,
                                                                                         boolean isChecked) {
                                                                if (isChecked) {
                                                                    if (RootTools.isAccessGiven()) {
                                                                        @SuppressLint("SdCardPath") Command installtweak = new Command(0,
                                                                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                                "cp /data/data/com.nowenui.systemtweaker/files/cpu_optimize /system/etc/init.d/",
                                                                                "chmod 777 /system/etc/init.d/cpu_optimize",
                                                                                "/system/etc/init.d/cpu_optimize",
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
                                                                                "rm -f /system/etc/init.d/cpu_optimize",
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
        } else {
            interactivetweak.setEnabled(false);
            interactivetweak.setBackgroundResource(R.drawable.roundbuttonfuck);
            interactivetweak.setTextColor(Color.WHITE);
            interactivetweak.setText(R.string.interactivetweakbad);
        }

        /////////////////////////////////////////////////////
        ////// Ondemand/Hotplug governors tweak ////////////
        ////////////////////////////////////////////////////
        CheckBox ondemandhotplugtweak = view.findViewById(R.id.ondemandhotplugtweak);

        if (HelperClass.OndemandOrHotplugTweakSupport() == 1) {
            if (HelperClass.isInitdSupport() == 0) {
                ondemandhotplugtweak.setEnabled(false);
            } else {
                ondemandhotplugtweak.setEnabled(true);
            }
            if (new File("/system/etc/init.d/ondemand_hotplug").exists()) {
                ondemandhotplugtweak.setChecked(true);
            } else {
                ondemandhotplugtweak.setChecked(false);
            }
            ondemandhotplugtweak.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View arg0) {
                    AboutTweak(R.string.ohopt);
                    return true;
                }
            });
            ondemandhotplugtweak.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


                                                                @Override
                                                                public void onCheckedChanged(CompoundButton buttonView,
                                                                                             boolean isChecked) {
                                                                    if (isChecked) {
                                                                        if (RootTools.isAccessGiven()) {
                                                                            @SuppressLint("SdCardPath") Command installtweak = new Command(0,
                                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                                    "cp /data/data/com.nowenui.systemtweaker/files/ondemand_hotplug /system/etc/init.d/",
                                                                                    "chmod 777 /system/etc/init.d/ondemand_hotplug",
                                                                                    "/system/etc/init.d/ondemand_hotplug",
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
                                                                                    "rm -f /system/etc/init.d/ondemand_hotplug",
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
        } else {
            ondemandhotplugtweak.setEnabled(false);
            ondemandhotplugtweak.setBackgroundResource(R.drawable.roundbuttonfuck);
            ondemandhotplugtweak.setTextColor(Color.WHITE);
            ondemandhotplugtweak.setText(R.string.ondemandorhotplugtweakbad);
        }

        ///////////////////////////////
        ////// SDCard Tweak ///////////
        ////////////////////////////////
        CheckBox sdtweak = view.findViewById(R.id.sdcardtweak);
        if (HelperClass.isInitdSupport() == 0) {
            sdtweak.setEnabled(false);
        } else {
            sdtweak.setEnabled(true);
        }
        if (new File("/system/etc/init.d/sdcard_tweak").exists()) {
            sdtweak.setChecked(true);
        } else {
            sdtweak.setChecked(false);
        }
        sdtweak.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                AboutTweak(R.string.ker1);
                return true;
            }
        });
        sdtweak.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


                                               @Override
                                               public void onCheckedChanged(CompoundButton buttonView,
                                                                            boolean isChecked) {
                                                   if (isChecked) {
                                                       if (RootTools.isAccessGiven()) {
                                                           @SuppressLint("SdCardPath") Command installtweak = new Command(0,
                                                                   "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                   "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                   "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                   "cp /data/data/com.nowenui.systemtweaker/files/sdcard_tweak /system/etc/init.d/",
                                                                   "chmod 777 /system/etc/init.d/sdcard_tweak",
                                                                   "/system/etc/init.d/sdcard_tweak",
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
                                                                   "rm -f /system/etc/init.d/sdcard_tweak",
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

        /////////////////////////////////////////
        ////// Sleepers tweaks //////////////////
        /////////////////////////////////////////
        CheckBox kernelsleepertweak = view.findViewById(R.id.kernelsleepertweak);
        CheckBox normsleepertweak = view.findViewById(R.id.normsleepertweak);

        if (HelperClass.SleepersSupportCheck() == 1) {
            if (HelperClass.isInitdSupport() == 0) {
                kernelsleepertweak.setEnabled(false);
            } else {
                kernelsleepertweak.setEnabled(true);
            }
            if (new File("/system/etc/init.d/kernel_sleeper").exists()) {
                kernelsleepertweak.setChecked(true);
            } else {
                kernelsleepertweak.setChecked(false);
            }
            kernelsleepertweak.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View arg0) {
                    AboutTweak(R.string.ker7);
                    return true;
                }
            });
            kernelsleepertweak.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                                              @Override
                                                              public void onCheckedChanged(CompoundButton buttonView,
                                                                                           boolean isChecked) {
                                                                  if (isChecked) {
                                                                      if (RootTools.isAccessGiven()) {
                                                                          @SuppressLint("SdCardPath") Command installtweak = new Command(0,
                                                                                  "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                                  "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                                  "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                                  "cp /data/data/com.nowenui.systemtweaker/files/kernel_sleeper /system/etc/init.d/",
                                                                                  "chmod 777 /system/etc/init.d/kernel_sleeper",
                                                                                  "/system/etc/init.d/kernel_sleeper",
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
                                                                                  "rm -f /system/etc/init.d/kernel_sleeper",
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
            if (HelperClass.isInitdSupport() == 0) {
                normsleepertweak.setEnabled(false);
            } else {
                normsleepertweak.setEnabled(true);
            }
            if (new File("/system/etc/init.d/11NSTweak").exists()) {
                normsleepertweak.setChecked(true);
            } else {
                normsleepertweak.setChecked(false);
            }
            normsleepertweak.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View arg0) {
                    AboutTweak(R.string.ker13);
                    return true;
                }
            });
            normsleepertweak.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


                                                            @Override
                                                            public void onCheckedChanged(CompoundButton buttonView,
                                                                                         boolean isChecked) {
                                                                if (isChecked) {
                                                                    if (RootTools.isAccessGiven()) {
                                                                        @SuppressLint("SdCardPath") Command installcommand = new Command(0,
                                                                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                                "cp /data/data/com.nowenui.systemtweaker/files/11NSTweak /system/etc/init.d/",
                                                                                "chmod 777 /system/etc/init.d/11NSTweak",
                                                                                "/system/etc/init.d/11NSTweak",
                                                                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system");
                                                                        try {
                                                                            RootTools.getShell(true).add(installcommand);
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
                                                                                "rm -f /system/etc/init.d/11NSTweak",
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

        } else {
            kernelsleepertweak.setEnabled(false);
            kernelsleepertweak.setBackgroundResource(R.drawable.roundbuttonfuck);
            kernelsleepertweak.setTextColor(Color.WHITE);
            kernelsleepertweak.setText(R.string.kernelsleepertweakbad);
            normsleepertweak.setEnabled(false);
            normsleepertweak.setBackgroundResource(R.drawable.roundbuttonfuck);
            normsleepertweak.setTextColor(Color.WHITE);
            normsleepertweak.setText(R.string.normsleepertweakbad);
        }

        ////////////////////////////////////////////
        ////// KSM Enable (if supports) ////////////
        ////////////////////////////////////////////
        CheckBox ksmtweak = view.findViewById(R.id.ksmtweak);

        if (HelperClass.ksm() == 1) {
            if (HelperClass.isInitdSupport() == 0) {
                ksmtweak.setEnabled(false);
            } else {
                ksmtweak.setEnabled(true);
            }
            if (new File("/system/etc/init.d/ksm").exists()) {
                ksmtweak.setChecked(true);
            } else {
                ksmtweak.setChecked(false);
            }
            ksmtweak.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View arg0) {
                    AboutTweak(R.string.ker10);
                    return true;
                }
            });
            ksmtweak.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


                                                    @Override
                                                    public void onCheckedChanged(CompoundButton buttonView,
                                                                                 boolean isChecked) {
                                                        if (isChecked) {
                                                            if (RootTools.isAccessGiven()) {
                                                                @SuppressLint("SdCardPath") Command installtweak = new Command(0,
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                        "cp /data/data/com.nowenui.systemtweaker/files/ksm /system/etc/init.d/",
                                                                        "chmod 777 /system/etc/init.d/ksm",
                                                                        "/system/etc/init.d/ksm",
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
                                                                        "rm -f /system/etc/init.d/ksm",
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
        } else {
            ksmtweak.setEnabled(false);
            ksmtweak.setBackgroundResource(R.drawable.roundbuttonfuck);
            ksmtweak.setTextColor(Color.WHITE);
            ksmtweak.setText(R.string.ksmbad);
        }

        //////////////////////////////////////////////
        ////// FastCharge enabling option ////////////
        //////////////////////////////////////////////
        CheckBox fastchargetweak = view.findViewById(R.id.fastchargetweak);
        if (HelperClass.fastcharge() == 1) {
            if (HelperClass.isInitdSupport() == 0) {
                fastchargetweak.setEnabled(false);
            } else {
                fastchargetweak.setEnabled(true);
            }
            if (new File("/system/etc/init.d/fastcharge").exists()) {
                fastchargetweak.setChecked(true);
            } else {
                fastchargetweak.setChecked(false);
            }
            fastchargetweak.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View arg0) {
                    AboutTweak(R.string.ker12);
                    return true;
                }
            });
            fastchargetweak.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


                                                           @Override
                                                           public void onCheckedChanged(CompoundButton buttonView,
                                                                                        boolean isChecked) {
                                                               if (isChecked) {
                                                                   if (RootTools.isAccessGiven()) {
                                                                       @SuppressLint("SdCardPath") Command installtweak = new Command(0,
                                                                               "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                               "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                               "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                               "cp /data/data/com.nowenui.systemtweaker/files/fastcharge /system/etc/init.d/",
                                                                               "chmod 777 /system/etc/init.d/fastcharge",
                                                                               "/system/etc/init.d/fastcharge",
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
                                                                               "rm -f /system/etc/init.d/fastcharge",
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
        } else {
            fastchargetweak.setEnabled(false);
            fastchargetweak.setBackgroundResource(R.drawable.roundbuttonfuck);
            fastchargetweak.setTextColor(Color.WHITE);
            fastchargetweak.setText(R.string.fastchargebad);
        }


        ////////////////////////////////////////////
        ////// ZRAM Tweak (if supports) ////////////
        ////////////////////////////////////////////
        CheckBox zramtweak = view.findViewById(R.id.zramtweak);
        if (HelperClass.ZRAM() == 1) {

            if (HelperClass.isInitdSupport() == 0) {
                zramtweak.setEnabled(false);
            } else {
                zramtweak.setEnabled(true);
            }
            if (new File("/system/etc/init.d/zram").exists()) {
                zramtweak.setChecked(true);
            } else {
                zramtweak.setChecked(false);
            }
            zramtweak.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View arg0) {
                    AboutTweak(R.string.ker9);
                    return true;
                }
            });
            zramtweak.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


                                                     @Override
                                                     public void onCheckedChanged(CompoundButton buttonView,
                                                                                  boolean isChecked) {
                                                         if (isChecked) {
                                                             if (RootTools.isAccessGiven()) {
                                                                 @SuppressLint("SdCardPath") Command installtweak = new Command(0,
                                                                         "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                         "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                         "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                         "cp /data/data/com.nowenui.systemtweaker/files/zram /system/etc/init.d/",
                                                                         "chmod 777 /system/etc/init.d/zram",
                                                                         "/system/etc/init.d/zram",
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
                                                                         "rm -f /system/etc/init.d/zram",
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
        } else {
            zramtweak.setEnabled(false);
            zramtweak.setBackgroundResource(R.drawable.roundbuttonfuck);
            zramtweak.setTextColor(Color.WHITE);
            zramtweak.setText(R.string.zrambad);
        }
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