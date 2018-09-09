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

public class VariosTweaksFragment extends Fragment {

    public static VariosTweaksFragment newInstance(Bundle bundle) {
        VariosTweaksFragment VariosTweaks = new VariosTweaksFragment();

        if (bundle != null) {
            VariosTweaks.setArguments(bundle);
        }

        return VariosTweaks;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.various_tweaks, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        ////////////////////////////////////
        ////// Quick Boot Tweak ////////////
        ///////////////////////////////////
        CheckBox quickboottweak = view.findViewById(R.id.quickboottweak);
        if (HelperClass.isInitdSupport() == 0) {
            quickboottweak.setEnabled(false);
        } else {
            quickboottweak.setEnabled(true);
        }
        if (new File("/system/etc/init.d/quick_power").exists()) {
            quickboottweak.setChecked(true);
        } else {
            quickboottweak.setChecked(false);
        }
        quickboottweak.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                AboutTweak(R.string.al3);
                return true;
            }
        });
        quickboottweak.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


                                                      @Override
                                                      public void onCheckedChanged(CompoundButton buttonView,
                                                                                   boolean isChecked) {
                                                          if (isChecked) {
                                                              if (RootTools.isAccessGiven()) {
                                                                  @SuppressLint("SdCardPath") Command installtweak = new Command(0,
                                                                          "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                          "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                          "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                          "cp /data/data/com.nowenui.systemtweaker/files/quick_power /system/etc/init.d/",
                                                                          "chmod 777 /system/etc/init.d/quick_power",
                                                                          "/system/etc/init.d/quick_power",
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
                                                                          "rm -f /system/etc/init.d/quick_power",
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

        ///////////////////////////////////////////////
        ////// Disable bootanimation tweak ////////////
        //////////////////////////////////////////////
        CheckBox disablebootanimtweak = view.findViewById(R.id.disablebootanimtweak);
        if (HelperClass.BuildPropText().toString().contains("debug.sf.nobootanimation=1")) {
            disablebootanimtweak.setChecked(true);
        } else {
            disablebootanimtweak.setChecked(false);
        }
        disablebootanimtweak.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                AboutTweak(R.string.al1);
                return true;
            }
        });
        disablebootanimtweak.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


                                                            @Override
                                                            public void onCheckedChanged(CompoundButton buttonView,
                                                                                         boolean isChecked) {
                                                                if (isChecked) {
                                                                    if (RootTools.isAccessGiven()) {
                                                                        @SuppressLint("SdCardPath") Command installtweak = new Command(0,
                                                                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                                "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/debug.sf.nobootanimation/d' /system/build.prop",
                                                                                "echo \"debug.sf.nobootanimation=1\" >> /system/build.prop",
                                                                                "setprop debug.sf.nobootanimation 1",
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
                                                                                "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/debug.sf.nobootanimation/d' /system/build.prop",
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

        ////////////////////////////////////
        ////// ADB Notify Tweak ////////////
        ///////////////////////////////////
        CheckBox adbnotifytweak = view.findViewById(R.id.adbnotifytweak);
        if (HelperClass.BuildPropText().toString().contains("persist.adb.notify=0")) {
            adbnotifytweak.setChecked(true);
        } else {
            adbnotifytweak.setChecked(false);
        }
        adbnotifytweak.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                AboutTweak(R.string.al2);
                return true;
            }
        });
        adbnotifytweak.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


                                                      @Override
                                                      public void onCheckedChanged(CompoundButton buttonView,
                                                                                   boolean isChecked) {
                                                          if (isChecked) {
                                                              if (RootTools.isAccessGiven()) {
                                                                  @SuppressLint("SdCardPath") Command installtweak = new Command(0,
                                                                          "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                          "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                          "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                          "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/persist.adb.notify/d' /system/build.prop",
                                                                          "echo \"persist.adb.notify=0\" >> /system/build.prop",
                                                                          "setprop persist.adb.notify 0",
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
                                                                          "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/persist.adb.notify/d' /system/build.prop",
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


        /////////////////////////////////
        ////// Overlay Tweak ////////////
        /////////////////////////////////
        CheckBox appnalozenietweak = view.findViewById(R.id.appnalozenietweak);
        if (HelperClass.isInitdSupport() == 0) {
            appnalozenietweak.setEnabled(false);
        } else {
            appnalozenietweak.setEnabled(true);
        }
        if (new File("/system/etc/init.d/09FixOverlays").exists()) {
            appnalozenietweak.setChecked(true);
        } else {
            appnalozenietweak.setChecked(false);
        }
        appnalozenietweak.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                AboutTweak(R.string.appnaloz);
                return true;
            }
        });
        appnalozenietweak.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


                                                         @Override
                                                         public void onCheckedChanged(CompoundButton buttonView,
                                                                                      boolean isChecked) {
                                                             if (isChecked) {
                                                                 if (RootTools.isAccessGiven()) {
                                                                     @SuppressLint("SdCardPath") Command installtweak = new Command(0,
                                                                             "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                             "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                             "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                             "cp /data/data/com.nowenui.systemtweaker/files/09FixOverlays /system/etc/init.d/",
                                                                             "chmod 777 /system/etc/init.d/09FixOverlays",
                                                                             "/system/etc/init.d/09FixOverlays",
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
                                                                             "rm -f /system/etc/init.d/09FixOverlays",
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