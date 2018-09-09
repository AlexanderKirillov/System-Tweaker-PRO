package com.nowenui.systemtweaker.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
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

public class BatteryTweaksFragment extends Fragment {

    public static BatteryTweaksFragment newInstance(Bundle bundle) {
        BatteryTweaksFragment BatteryTweaks = new BatteryTweaksFragment();

        if (bundle != null) {
            BatteryTweaks.setArguments(bundle);
        }

        return BatteryTweaks;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.battery_tweaks, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        ////////////////////////////////
        ////// DynBS........ ///////////
        ////////////////////////////////
        CheckBox dynbs = view.findViewById(R.id.dynbs);
        if (HelperClass.isInitdSupport() == 0) {
            dynbs.setEnabled(false);
        } else {
            dynbs.setEnabled(true);
        }
        if (new File("/system/etc/init.d/99dynbsd").exists()) {
            dynbs.setChecked(true);
        } else {
            dynbs.setChecked(false);
        }
        dynbs.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                AboutTweak(R.string.batterydynbs);
                return true;
            }
        });
        dynbs.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                             @Override
                                             public void onCheckedChanged(CompoundButton buttonView,
                                                                          boolean isChecked) {
                                                 if (isChecked) {
                                                     if (RootTools.isAccessGiven()) {
                                                         @SuppressLint("SdCardPath") Command installtweak = new Command(0,
                                                                 "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                 "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                 "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                 "cp /data/data/com.nowenui.systemtweaker/files/dynbsd /system/xbin/dynbsd",
                                                                 "chmod 755 /system/xbin/dynbsd",
                                                                 "cp /data/data/com.nowenui.systemtweaker/files/99dynbsd /system/etc/init.d/99dynbsd",
                                                                 "chmod 777 /system/etc/init.d/99dynbsd",
                                                                 "/system/etc/init.d/99dynbsd",
                                                                 "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                 "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                 "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                                         );
                                                         try {
                                                             RootTools.getShell(true).add(installtweak);
                                                             TweakInstallingOrDeletingDialog(R.string.tweakenabled);
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
                                                                 "rm -f /system/xbin/dynbsd",
                                                                 "rm -f /system/etc/init.d/99dynbsd",
                                                                 "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                 "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                 "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                                         );
                                                         try {
                                                             RootTools.getShell(true).add(deletetweak);
                                                             TweakInstallingOrDeletingDialog(R.string.tweakdisabled);
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

        ////////////////////////////////
        ////// DOZE ........ ///////////
        ////////////////////////////////
        CheckBox dozetweak = view.findViewById(R.id.dozetweak);
        if (Build.VERSION.SDK_INT >= 24) {
            if (HelperClass.isInitdSupport() == 0) {
                dozetweak.setEnabled(false);
            } else {
                dozetweak.setEnabled(true);
            }
        } else {
            dozetweak.setEnabled(false);
        }
        if (new File("/system/etc/init.d/doze").exists()) {
            dozetweak.setChecked(true);
        } else {
            dozetweak.setChecked(false);
        }
        dozetweak.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                AboutTweak(R.string.doze);
                return true;
            }
        });
        dozetweak.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                                 @Override
                                                 public void onCheckedChanged(CompoundButton buttonView,
                                                                              boolean isChecked) {
                                                     if (isChecked) {
                                                         if (RootTools.isAccessGiven()) {
                                                             @SuppressLint("SdCardPath") Command installtweak = new Command(0,
                                                                     "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                     "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                     "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                     "cp /data/data/com.nowenui.systemtweaker/files/doze /system/etc/init.d/doze",
                                                                     "chmod 777 /system/etc/init.d/doze",
                                                                     "/system/etc/init.d/doze",
                                                                     "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                     "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                     "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                                             );
                                                             try {
                                                                 RootTools.getShell(true).add(installtweak);
                                                                 TweakInstallingOrDeletingDialog(R.string.tweakenabled);
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
                                                                     "rm -f /system/etc/init.d/doze",
                                                                     "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                     "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                     "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                                             );
                                                             try {
                                                                 RootTools.getShell(true).add(deletetweak);
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

        ////////////////////////////////////////////////
        ////// LCD Display tweak........ ///////////////
        ////////////////////////////////////////////////
        CheckBox lcdtweak = view.findViewById(R.id.lcdtweak);
        if (HelperClass.LCDCheck() == 1) {
            if (HelperClass.isInitdSupport() == 0) {
                lcdtweak.setEnabled(false);
            } else {
                lcdtweak.setEnabled(true);
            }
        } else {
            lcdtweak.setEnabled(false);
        }
        if (new File("/system/etc/init.d/lcd").exists()) {
            lcdtweak.setChecked(true);
        } else {
            lcdtweak.setChecked(false);
        }
        lcdtweak.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                AboutTweak(R.string.lcd);
                return true;
            }
        });
        lcdtweak.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                                @Override
                                                public void onCheckedChanged(CompoundButton buttonView,
                                                                             boolean isChecked) {
                                                    if (isChecked) {
                                                        if (RootTools.isAccessGiven()) {
                                                            @SuppressLint("SdCardPath") Command installtweak = new Command(0,
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                    "cp /data/data/com.nowenui.systemtweaker/files/lcd /system/etc/init.d/",
                                                                    "chmod 777 /system/etc/init.d/lcd",
                                                                    "/system/etc/init.d/lcd",
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
                                                                    "rm -f /system/etc/init.d/lcd",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system");
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

        /////////////////////////////////////////////////////////////////////////
        ////////////////// Radio PowerSave Tweak.. //////////////////////////////
        /////////////////////////////////////////////////////////////////////////
        CheckBox radioimprovebattery = view.findViewById(R.id.radioimprovebattery);
        if (HelperClass.BuildPropText().toString().contains("persist.radio.add_power_save=1")) {
            radioimprovebattery.setChecked(true);
        } else {
            radioimprovebattery.setChecked(false);
        }
        radioimprovebattery.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                AboutTweak(R.string.radbattext);
                return true;
            }
        });
        radioimprovebattery.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                                           @Override
                                                           public void onCheckedChanged(CompoundButton buttonView,
                                                                                        boolean isChecked) {
                                                               if (isChecked) {
                                                                   if (RootTools.isAccessGiven()) {
                                                                       @SuppressLint("SdCardPath") Command installtweak = new Command(0,
                                                                               "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                               "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                               "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                               "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/persist.radio.add_power_save/d' /system/build.prop",
                                                                               "echo \"persist.radio.add_power_save=1\" >> /system/build.prop",
                                                                               "setprop persist.radio.add_power_save 1",
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
                                                                               "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/persist.radio.add_power_save/d' /system/build.prop",
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

        ////////////////////////////////////////////////
        ////// Google Services Tweak........ ///////////
        ////////////////////////////////////////////////
        CheckBox gservicestweak = view.findViewById(R.id.gservicestweak);
        if (new File("/system/etc/init.d/05FixGoogleServicedrain").exists()) {
            gservicestweak.setChecked(true);
        } else {
            gservicestweak.setChecked(false);
        }
        if (HelperClass.isInitdSupport() == 0) {
            gservicestweak.setEnabled(false);
        } else {
            gservicestweak.setEnabled(true);
        }

        gservicestweak.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                AboutTweak(R.string.battery1);
                return true;
            }
        });
        gservicestweak.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                                      @Override
                                                      public void onCheckedChanged(CompoundButton buttonView,
                                                                                   boolean isChecked) {
                                                          if (isChecked) {
                                                              if (RootTools.isAccessGiven()) {
                                                                  @SuppressLint("SdCardPath") Command installtweak = new Command(0,
                                                                          "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                          "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                          "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                          "cp /data/data/com.nowenui.systemtweaker/files/05FixGoogleServicedrain /system/etc/init.d/",
                                                                          "chmod 777 /system/etc/init.d/05FixGoogleServicedrain",
                                                                          "/system/etc/init.d/05FixGoogleServicedrain",
                                                                          "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                          "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                          "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system");
                                                                  try {
                                                                      RootTools.getShell(true).add(installtweak);
                                                                      TweakInstallingOrDeletingDialog(R.string.tweakenabled);
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
                                                                          "rm -f /system/etc/init.d/05FixGoogleServicedrain",
                                                                          "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                          "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                          "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system");
                                                                  try {
                                                                      RootTools.getShell(true).add(deletetweak);
                                                                      new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakdisabled)).withBackgroundColorId(R.color.resultgood).show();
                                                                  } catch (IOException | RootDeniedException | TimeoutException ex) {

                                                                  }
                                                              } else {
                                                                  new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                                                              }
                                                          }
                                                      }
                                                  }
        );

        ////////////////////////////////////////////////////////////////
        ////// Improve powersaving. Fixes VM overuse........ ///////////
        ////////////////////////////////////////////////////////////////
        CheckBox improvepowersavingtweak = view.findViewById(R.id.improvepowersavingtweak);
        if (HelperClass.isInitdSupport() == 0) {
            improvepowersavingtweak.setEnabled(false);
        } else {
            improvepowersavingtweak.setEnabled(true);
        }
        if (new File("/system/etc/init.d/00BatteryImprove").exists()) {
            improvepowersavingtweak.setChecked(true);
        } else {
            improvepowersavingtweak.setChecked(false);
        }
        improvepowersavingtweak.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                AboutTweak(R.string.battery2);
                return true;
            }
        });
        improvepowersavingtweak.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                                               @Override
                                                               public void onCheckedChanged(CompoundButton buttonView,
                                                                                            boolean isChecked) {
                                                                   if (isChecked) {
                                                                       if (RootTools.isAccessGiven()) {
                                                                           @SuppressLint("SdCardPath") Command installtweak = new Command(0,
                                                                                   "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                                   "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                                   "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                                   "cp /data/data/com.nowenui.systemtweaker/files/00BatteryImprove /system/etc/init.d/",
                                                                                   "chmod 777 /system/etc/init.d/00BatteryImprove",
                                                                                   "/system/etc/init.d/00BatteryImprove",
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
                                                                                   "rm -f /system/etc/init.d/00BatteryImprove",
                                                                                   "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                                   "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                                   "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system");
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

        ///////////////////////////////////////////
        //////////// Disable boosts ///////////////
        ///////////////////////////////////////////
        CheckBox disableboost = view.findViewById(R.id.boostdis);
        if (HelperClass.BoostCheck() == 1) {
            if (HelperClass.isInitdSupport() == 0) {
                disableboost.setEnabled(false);
            } else {
                disableboost.setEnabled(true);
            }
        } else {
            disableboost.setEnabled(false);
        }
        if (new File("/system/etc/init.d/boostdel").exists()) {
            disableboost.setChecked(true);
        } else {
            disableboost.setChecked(false);
        }
        disableboost.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                                    @Override
                                                    public void onCheckedChanged(CompoundButton buttonView,
                                                                                 boolean isChecked) {
                                                        if (isChecked) {
                                                            if (RootTools.isAccessGiven()) {
                                                                @SuppressLint("SdCardPath") Command installtweak = new Command(0,
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                        "cp /data/data/com.nowenui.systemtweaker/files/boostdel /system/etc/init.d/",
                                                                        "chmod 777 /system/etc/init.d/boostdel",
                                                                        "/system/etc/init.d/boostdel",
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
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox rm -f /system/etc/init.d/boostdel",
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

        ////////////////////////////////////////////////////////////
        ////// Disable multitasking restrictions........ ///////////
        ////////////////////////////////////////////////////////////
        CheckBox managerestructions = view.findViewById(R.id.managerestructions);
        if (HelperClass.BuildPropText().toString().contains("MIN_HIDDEN_APPS=false")
                && HelperClass.BuildPropText().toString().contains("ACTIVITY_INACTIVE_RESET_TIME=false")
                && HelperClass.BuildPropText().toString().contains("MIN_RECENT_TASKS=false")
                && HelperClass.BuildPropText().toString().contains("PROC_START_TIMEOUT=false")
                && HelperClass.BuildPropText().toString().contains("CPU_MIN_CHECK_DURATION=false")
                && HelperClass.BuildPropText().toString().contains("GC_TIMEOUT=false")
                && HelperClass.BuildPropText().toString().contains("SERVICE_TIMEOUT=false")
                && HelperClass.BuildPropText().toString().contains("MIN_CRASH_INTERVAL=false")
                && HelperClass.BuildPropText().toString().contains("ENFORCE_PROCESS_LIMIT=false")) {
            managerestructions.setChecked(true);
        } else {
            managerestructions.setChecked(false);
        }
        managerestructions.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                AboutTweak(R.string.aboutmnogozadrestr);
                return true;
            }
        });
        managerestructions.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                                          @Override
                                                          public void onCheckedChanged(CompoundButton buttonView,
                                                                                       boolean isChecked) {
                                                              if (isChecked) {
                                                                  if (RootTools.isAccessGiven()) {
                                                                      @SuppressLint("SdCardPath") Command installtweak = new Command(0,
                                                                              "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                              "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                              "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                              "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/MIN_HIDDEN_APPS/d' /system/build.prop",
                                                                              "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ACTIVITY_INACTIVE_RESET_TIME/d' /system/build.prop",
                                                                              "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/MIN_RECENT_TASKS/d' /system/build.prop",
                                                                              "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/PROC_START_TIMEOUT/d' /system/build.prop",
                                                                              "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/CPU_MIN_CHECK_DURATION/d' /system/build.prop",
                                                                              "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/GC_TIMEOUT/d' /system/build.prop",
                                                                              "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/SERVICE_TIMEOUT/d' /system/build.prop",
                                                                              "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/MIN_CRASH_INTERVAL/d' /system/build.prop",
                                                                              "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ENFORCE_PROCESS_LIMIT/d' /system/build.prop",
                                                                              "echo \"MIN_HIDDEN_APPS=false\" >> /system/build.prop",
                                                                              "echo \"ACTIVITY_INACTIVE_RESET_TIME=false\" >> /system/build.prop",
                                                                              "echo \"MIN_RECENT_TASKS=false\" >> /system/build.prop",
                                                                              "echo \"PROC_START_TIMEOUT=false\" >> /system/build.prop",
                                                                              "echo \"CPU_MIN_CHECK_DURATION=false\" >> /system/build.prop",
                                                                              "echo \"GC_TIMEOUT=false\" >> /system/build.prop",
                                                                              "echo \"SERVICE_TIMEOUT=false\" >> /system/build.prop",
                                                                              "echo \"MIN_CRASH_INTERVAL=false\" >> /system/build.prop",
                                                                              "echo \"ENFORCE_PROCESS_LIMIT=false\" >> /system/build.prop",
                                                                              "setprop MIN_HIDDEN_APPS false",
                                                                              "setprop ACTIVITY_INACTIVE_RESET_TIME false",
                                                                              "setprop MIN_RECENT_TASKS false",
                                                                              "setprop PROC_START_TIMEOUT false",
                                                                              "setprop CPU_MIN_CHECK_DURATION false",
                                                                              "setprop GC_TIMEOUT false",
                                                                              "setprop SERVICE_TIMEOUT false",
                                                                              "setprop MIN_CRASH_INTERVAL false",
                                                                              "setprop ENFORCE_PROCESS_LIMIT false",
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
                                                                              "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/MIN_HIDDEN_APPS/d' /system/build.prop",
                                                                              "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ACTIVITY_INACTIVE_RESET_TIME/d' /system/build.prop",
                                                                              "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/MIN_RECENT_TASKS/d' /system/build.prop",
                                                                              "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/PROC_START_TIMEOUT/d' /system/build.prop",
                                                                              "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/CPU_MIN_CHECK_DURATION/d' /system/build.prop",
                                                                              "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/GC_TIMEOUT/d' /system/build.prop",
                                                                              "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/SERVICE_TIMEOUT/d' /system/build.prop",
                                                                              "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/MIN_CRASH_INTERVAL/d' /system/build.prop",
                                                                              "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ENFORCE_PROCESS_LIMIT/d' /system/build.prop",
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

        ///////////////////////////////////////////////////
        ////// Wifi Scan Interval tweak........ ///////////
        ///////////////////////////////////////////////////
        CheckBox wifiscantweak = view.findViewById(R.id.wifiscantweak);
        if (HelperClass.BuildPropText().toString().contains("wifi.supplicant_scan_interval=220")) {
            wifiscantweak.setChecked(true);
        } else {
            wifiscantweak.setChecked(false);
        }
        wifiscantweak.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                AboutTweak(R.string.battery3);
                return true;
            }
        });
        wifiscantweak.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                                     @Override
                                                     public void onCheckedChanged(CompoundButton buttonView,
                                                                                  boolean isChecked) {
                                                         if (isChecked) {
                                                             if (RootTools.isAccessGiven()) {
                                                                 @SuppressLint("SdCardPath") Command installtweak = new Command(0,
                                                                         "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                         "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                         "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                         "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/wifi.supplicant_scan_interval/d' /system/build.prop",
                                                                         "echo \"wifi.supplicant_scan_interval=220\" >> /system/build.prop",
                                                                         "setprop wifi.supplicant_scan_interval 220",
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
                                                                         "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/wifi.supplicant_scan_interval/d' /system/build.prop",
                                                                         "echo \"wifi.supplicant_scan_interval=160\" >> /system/build.prop",
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

        ////////////////////////////////////////////////
        ////// Enable Power Collapse........ ///////////
        ////////////////////////////////////////////////
        CheckBox powercollapsetweak = view.findViewById(R.id.powercollapsetweak);
        if (HelperClass.BuildPropText().toString().contains("ro.ril.disable.power.collapse=0")
                && HelperClass.BuildPropText().toString().contains("ro.vold.umsdirtyratio=20")
                && HelperClass.BuildPropText().toString().contains("pm.sleep_mode=1")
                && (new File("/system/etc/init.d/lpm").exists())) {
            powercollapsetweak.setChecked(true);
        } else {
            powercollapsetweak.setChecked(false);
        }
        if (HelperClass.isInitdSupport() == 0) {
            powercollapsetweak.setEnabled(false);
        } else {
            powercollapsetweak.setEnabled(true);
        }
        powercollapsetweak.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                AboutTweak(R.string.battery4);
                return true;
            }
        });
        powercollapsetweak.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                                          @Override
                                                          public void onCheckedChanged(CompoundButton buttonView,
                                                                                       boolean isChecked) {
                                                              if (isChecked) {
                                                                  if (RootTools.isAccessGiven()) {
                                                                      @SuppressLint("SdCardPath") Command installtweak = new Command(0,
                                                                              "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                              "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                              "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                              "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.ril.power.collapse/d' /system/build.prop",
                                                                              "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.ril.disable.power.collapse/d' /system/build.prop",
                                                                              "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/pm.sleep_mode/d' /system/build.prop",
                                                                              "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.vold.umsdirtyratio/d' /system/build.prop",
                                                                              "echo \"ro.ril.disable.power.collapse=0\" >> /system/build.prop",
                                                                              "echo \"ro.vold.umsdirtyratio=20\" >> /system/build.prop",
                                                                              "echo \"pm.sleep_mode=1\" >> /system/build.prop",
                                                                              "setprop ro.ril.disable.power.collapse 0",
                                                                              "setprop ro.vold.umsdirtyratio 20",
                                                                              "setprop pm.sleep_mode 1",
                                                                              "cp /data/data/com.nowenui.systemtweaker/files/lpm /system/etc/init.d/",
                                                                              "chmod 777 /system/etc/init.d/lpm",
                                                                              "/system/etc/init.d/lpm",
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
                                                                              "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.ril.disable.power.collapse/d' /system/build.prop",
                                                                              "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.vold.umsdirtyratio/d' /system/build.prop",
                                                                              "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/pm.sleep_mode/d' /system/build.prop",
                                                                              "/data/data/com.nowenui.systemtweaker/files/busybox rm -f /system/etc/init.d/lpm",
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

        //////////////////////////////////////////////
        ////// Enable Power Supply........ ///////////
        //////////////////////////////////////////////
        CheckBox powersupplytweak = view.findViewById(R.id.powersupplytweak);
        if (HelperClass.BuildPropText().toString().contains("power_supply.wakeup=enable")) {
            powersupplytweak.setChecked(true);
        } else {
            powersupplytweak.setChecked(false);
        }
        powersupplytweak.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                AboutTweak(R.string.battery5);
                return true;
            }
        });
        powersupplytweak.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                                        @Override
                                                        public void onCheckedChanged(CompoundButton buttonView,
                                                                                     boolean isChecked) {
                                                            if (isChecked) {
                                                                if (RootTools.isAccessGiven()) {
                                                                    @SuppressLint("SdCardPath") Command installtweak = new Command(0,
                                                                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                            "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/power_supply.wakeup/d' /system/build.prop",
                                                                            "echo \"power_supply.wakeup=enable\" >> /system/build.prop",
                                                                            "setprop power_supply.wakeup enable",
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
                                                                            "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/power_supply.wakeup/d' /system/build.prop",
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

        /////////////////////////////////////////////////////////////////////////
        ////// Enable better management of sensors during deepsleep.. ///////////
        /////////////////////////////////////////////////////////////////////////
        CheckBox deepsleeptweak = view.findViewById(R.id.deepsleeptweak);
        if (HelperClass.BuildPropText().toString().contains("ro.ril.sensor.sleep.control=1")) {
            deepsleeptweak.setChecked(true);
        } else {
            deepsleeptweak.setChecked(false);
        }
        deepsleeptweak.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                AboutTweak(R.string.battery6);
                return true;
            }
        });
        deepsleeptweak.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                                      @Override
                                                      public void onCheckedChanged(CompoundButton buttonView,
                                                                                   boolean isChecked) {
                                                          if (isChecked) {
                                                              if (RootTools.isAccessGiven()) {
                                                                  @SuppressLint("SdCardPath") Command installtweak = new Command(0,
                                                                          "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                          "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                          "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                          "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.ril.sensor.sleep.control/d' /system/build.prop",
                                                                          "echo \"ro.ril.sensor.sleep.control=1\" >> /system/build.prop",
                                                                          "setprop ro.ril.sensor.sleep.control 1",
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
                                                                          "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.ril.sensor.sleep.control/d' /system/build.prop",
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

        /////////////////////////////////////////////////
        ////// Disable system logging........ ///////////
        /////////////////////////////////////////////////
        CheckBox disablelogging = view.findViewById(R.id.disablelogging);
        if ((HelperClass.BuildPropText().toString().contains("ro.config.nocheckin=1")
                && HelperClass.BuildPropText().toString().contains("profiler.force_disable_err_rpt=1")
                && HelperClass.BuildPropText().toString().contains("debugtool.anrhistory=0")
                && HelperClass.BuildPropText().toString().contains("profiler.debugmonitor=false")
                && HelperClass.BuildPropText().toString().contains("profiler.launch=false")
                && HelperClass.BuildPropText().toString().contains("profiler.hung.dumpdobugreport=false")
                && HelperClass.BuildPropText().toString().contains("persist.android.strictmode=0")
                && HelperClass.BuildPropText().toString().contains("logcat.live=disable")
                && HelperClass.BuildPropText().toString().contains("profiler.force_disable_ulog=1") && (new File("/system/etc/init.d/logs").exists()))) {
            disablelogging.setChecked(true);
        } else {
            disablelogging.setChecked(false);
        }
        if (HelperClass.isInitdSupport() == 0) {
            disablelogging.setEnabled(false);
        } else {
            disablelogging.setEnabled(true);
        }
        disablelogging.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                AboutTweak(R.string.battery8);
                return true;
            }
        });
        disablelogging.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                                      @Override
                                                      public void onCheckedChanged(CompoundButton buttonView,
                                                                                   boolean isChecked) {
                                                          if (isChecked) {
                                                              if (RootTools.isAccessGiven()) {
                                                                  @SuppressLint("SdCardPath") Command installtweak = new Command(0,
                                                                          "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                          "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                          "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                          "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.config.nocheckin/d' /system/build.prop",
                                                                          "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/logcat.live/d' /system/build.prop",
                                                                          "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/profiler.force_disable_err_rpt/d' /system/build.prop",
                                                                          "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/profiler.force_disable_ulog/d' /system/build.prop",
                                                                          "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/debugtool.anrhistory/d' /system/build.prop",
                                                                          "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/profiler.debugmonitor/d' /system/build.prop",
                                                                          "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/profiler.launch/d' /system/build.prop",
                                                                          "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/profiler.hung.dumpdobugreport/d' /system/build.prop",
                                                                          "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/persist.android.strictmode/d' /system/build.prop",
                                                                          "echo \"ro.config.nocheckin=1\" >> /system/build.prop",
                                                                          "echo \"logcat.live=disable\" >> /system/build.prop",
                                                                          "echo \"profiler.force_disable_err_rpt=1\" >> /system/build.prop",
                                                                          "echo \"profiler.force_disable_ulog=1\" >> /system/build.prop",
                                                                          "echo \"debugtool.anrhistory=0\" >> /system/build.prop",
                                                                          "echo \"profiler.debugmonitor=false\" >> /system/build.prop",
                                                                          "echo \"profiler.launch=false\" >> /system/build.prop",
                                                                          "echo \"profiler.hung.dumpdobugreport=false\" >> /system/build.prop",
                                                                          "echo \"persist.android.strictmode=0\" >> /system/build.prop",
                                                                          "setprop ro.config.nocheckin 1",
                                                                          "setprop logcat.live disable",
                                                                          "setprop profiler.force_disable_err_rpt 1",
                                                                          "setprop profiler.force_disable_ulog 1",
                                                                          "setprop debugtool.anrhistory 0",
                                                                          "setprop profiler.debugmonitor false",
                                                                          "setprop profiler.launch false",
                                                                          "setprop profiler.hung.dumpdobugreport false",
                                                                          "setprop persist.android.strictmode 0",
                                                                          "cp /data/data/com.nowenui.systemtweaker/files/logs /system/etc/init.d/",
                                                                          "chmod 777 /system/etc/init.d/logs",
                                                                          "/system/etc/init.d/logs",
                                                                          "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                          "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                          "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system");
                                                                  try {
                                                                      RootTools.getShell(true).add(installtweak);
                                                                      TweakInstallingOrDeletingDialog(R.string.tweakenabled);
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
                                                                          "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.config.nocheckin/d' /system/build.prop",
                                                                          "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/profiler.force_disable_err_rpt/d' /system/build.prop",
                                                                          "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/profiler.force_disable_ulog/d' /system/build.prop",
                                                                          "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/logcat.live/d' /system/build.prop",
                                                                          "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/debugtool.anrhistory/d' /system/build.prop",
                                                                          "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/profiler.debugmonitor/d' /system/build.prop",
                                                                          "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/profiler.launch/d' /system/build.prop",
                                                                          "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/profiler.hung.dumpdobugreport/d' /system/build.prop",
                                                                          "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/persist.android.strictmode/d' /system/build.prop",
                                                                          "rm -f /system/etc/init.d/logs",
                                                                          "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                          "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                          "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                                                  );
                                                                  try {
                                                                      RootTools.getShell(true).add(deletetweak);
                                                                      TweakInstallingOrDeletingDialog(R.string.tweakdisabled);
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

    public void TweakInstallingOrDeletingDialog(final int tweakstatus) {
        if (ThemeUtility.getTheme(getActivity().getApplicationContext()) == 1) {

            final ProgressDialog dialog = new ProgressDialog(getActivity(), R.style.AppCompatAlertDialogStyle);
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setMessage(getContext().getResources().getString(R.string.speedmessage));
            dialog.setIndeterminate(false);
            dialog.setCancelable(false);
            dialog.show();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    dialog.dismiss();
                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(tweakstatus)).withBackgroundColorId(R.color.resultgood).show();
                }
            }, 4000);
        }
        if (ThemeUtility.getTheme(getActivity().getApplicationContext()) == 2) {

            final ProgressDialog dialog = new ProgressDialog(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark));
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setMessage(getContext().getResources().getString(R.string.speedmessage));
            dialog.setIndeterminate(false);
            dialog.setCancelable(false);
            dialog.show();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    dialog.dismiss();
                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(tweakstatus)).withBackgroundColorId(R.color.resultgood).show();
                }
            }, 4000);
        }
        if (ThemeUtility.getTheme(getActivity().getApplicationContext()) == 3) {

            final ProgressDialog dialog = new ProgressDialog(new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack));
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setMessage(getContext().getResources().getString(R.string.speedmessage));
            dialog.setIndeterminate(false);
            dialog.setCancelable(false);
            dialog.show();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    dialog.dismiss();
                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(tweakstatus)).withBackgroundColorId(R.color.resultgood).show();
                }
            }, 4000);
        }
    }
}