package com.nowenui.systemtweaker.fragments;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

public class MediaBoosterFragment extends Fragment {

    private boolean isClicked;

    public static MediaBoosterFragment newInstance(Bundle bundle) {
        MediaBoosterFragment MediaServer = new MediaBoosterFragment();

        if (bundle != null) {
            MediaServer.setArguments(bundle);
        }

        return MediaServer;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.media_booster, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        setRetainInstance(true);

        Button StopMediaServer = view.findViewById(R.id.StopMediaServer);
        Button StartMediaScanner = view.findViewById(R.id.StartMediaScanner);
        Button StopMediaScanner = view.findViewById(R.id.StopMediaScanner);

        ////////////////////////////////////////////
        ////// STOP MEDIA SERVER BUTTON ////////////
        ///////////////////////////////////////////
        StopMediaServer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isClicked) {
                    return;
                }
                isClicked = true;
                v.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        isClicked = false;
                    }
                }, 1000);

                if (RootTools.isAccessGiven()) {
                    @SuppressLint("SdCardPath") Command killmediaserver = new Command(0,
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox killall -9 android.process.media",
                            "/data/data/com.nowenui.systemtweaker/files/busybox killall -9 mediaserver",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                    );
                    try {
                        RootTools.getShell(true).add(killmediaserver);
                        DialogHelper(R.string.sttoppedmediaserver);
                    } catch (IOException | RootDeniedException | TimeoutException ex) {
                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                    }
                } else {
                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                }
            }

        });

        /////////////////////////////////////////////
        ////// STOP MEDIA SCANNER BUTTON ////////////
        /////////////////////////////////////////////
        StopMediaScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isClicked) {
                    return;
                }
                isClicked = true;
                v.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        isClicked = false;
                    }
                }, 1000);

                if (RootTools.isAccessGiven()) {
                    @SuppressLint("SdCardPath") Command killmediascanner = new Command(0,
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                            "pm disable com.android.providers.media/com.android.providers.media.MediaScannerReceiver",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                    );
                    try {
                        RootTools.getShell(true).add(killmediascanner);
                        DialogHelper(R.string.sttoppedmediascanner);
                    } catch (IOException | RootDeniedException | TimeoutException ex) {
                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                    }
                } else {
                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                }
            }

        });

        /////////////////////////////////////////////
        ////// START MEDIA SCANNER BUTTON ///////////
        /////////////////////////////////////////////
        StartMediaScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isClicked) {
                    return;
                }
                isClicked = true;
                v.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        isClicked = false;
                    }
                }, 1000);

                if (RootTools.isAccessGiven()) {
                    Command startmediascannercommand = new Command(0,
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                            "pm enable com.android.providers.media/com.android.providers.media.MediaScannerReceiver",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                    );
                    try {
                        RootTools.getShell(true).add(startmediascannercommand);
                        DialogHelper(R.string.startpedmediaserver);
                    } catch (IOException | RootDeniedException | TimeoutException ex) {
                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                    }
                } else {
                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                }
            }

        });

        /////////////////////////////////////////////////////
        ////// Init.d MediaServer killing script ////////////
        ////////////////////////////////////////////////////
        CheckBox stopmediaserveratboot = view.findViewById(R.id.stopmediaserveratboot);
        if (HelperClass.isInitdSupport() == 0) {
            stopmediaserveratboot.setEnabled(false);
        } else {
            stopmediaserveratboot.setEnabled(true);
        }
        if (new File("/system/etc/init.d/01MediaServelKilling").exists()) {
            stopmediaserveratboot.setChecked(true);
        } else {
            stopmediaserveratboot.setChecked(false);
        }
        stopmediaserveratboot.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


                                                             @Override
                                                             public void onCheckedChanged(CompoundButton buttonView,
                                                                                          boolean isChecked) {
                                                                 if (isChecked) {
                                                                     if (RootTools.isAccessGiven()) {
                                                                         @SuppressLint("SdCardPath") Command installtweak = new Command(0,
                                                                                 "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                                 "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                                 "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                                 "cp /data/data/com.nowenui.systemtweaker/files/01MediaServelKilling /system/etc/init.d/",
                                                                                 "chmod 777 /system/etc/init.d/01MediaServelKilling",
                                                                                 "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                                 "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                                 "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system");
                                                                         try {
                                                                             RootTools.getShell(true).add(installtweak);
                                                                             DialogHelper(R.string.ok);
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
                                                                                 "rm -f /system/etc/init.d/01MediaServelKilling",
                                                                                 "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                                 "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                                 "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                                                         );
                                                                         try {
                                                                             RootTools.getShell(true).add(deletetweak);
                                                                             DialogHelper(R.string.disable);
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

        //////////////////////////////////////////////////////
        ////// Init.d MediaScanner killing script ////////////
        /////////////////////////////////////////////////////
        CheckBox stopmediascanneratboot = view.findViewById(R.id.stopmediascanneratboot);
        if (HelperClass.isInitdSupport() == 0) {
            stopmediascanneratboot.setEnabled(false);
        } else {
            stopmediascanneratboot.setEnabled(true);
        }
        if (new File("/system/etc/init.d/01MediaScannerKilling").exists()) {
            stopmediascanneratboot.setChecked(true);
        } else {
            stopmediascanneratboot.setChecked(false);
        }
        stopmediascanneratboot.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


                                                              @Override
                                                              public void onCheckedChanged(CompoundButton buttonView,
                                                                                           boolean isChecked) {
                                                                  if (isChecked) {
                                                                      if (RootTools.isAccessGiven()) {
                                                                          @SuppressLint("SdCardPath") Command installtweak = new Command(0,
                                                                                  "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                                  "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                                  "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                                  "cp /data/data/com.nowenui.systemtweaker/files/01MediaScannerKilling /system/etc/init.d/01MediaScannerKilling",
                                                                                  "chmod 777 /system/etc/init.d/01MediaScannerKilling",
                                                                                  "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                                  "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                                  "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                                                          );
                                                                          try {
                                                                              RootTools.getShell(true).add(installtweak);
                                                                              DialogHelper(R.string.ok);
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
                                                                                  "rm -f /system/etc/init.d/01MediaScannerKilling",
                                                                                  "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                                  "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                                  "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                                                          );
                                                                          try {
                                                                              RootTools.getShell(true).add(deletetweak);
                                                                              DialogHelper(R.string.disable);
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

    public void DialogHelper(final int DialogText) {
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
                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(DialogText)).withBackgroundColorId(R.color.resultgood).show();
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
                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(DialogText)).withBackgroundColorId(R.color.resultgood).show();
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
                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(DialogText)).withBackgroundColorId(R.color.resultgood).show();
                }
            }, 4000);
        }
    }

}