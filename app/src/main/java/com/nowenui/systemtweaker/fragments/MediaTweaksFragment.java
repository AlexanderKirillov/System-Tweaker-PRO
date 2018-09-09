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

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MediaTweaksFragment extends Fragment {

    public static MediaTweaksFragment newInstance(Bundle bundle) {
        MediaTweaksFragment MediaTweaks = new MediaTweaksFragment();

        if (bundle != null) {
            MediaTweaks.setArguments(bundle);
        }

        return MediaTweaks;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.media_tweaks, parent, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        //////////////////////////////////////////////////////
        ////// Improving photo quality up to 100% ////////////
        /////////////////////////////////////////////////////
        CheckBox photomaxquality = view.findViewById(R.id.photomaxquality);
        if (HelperClass.BuildPropText().toString().contains("ro.media.enc.jpeg.quality=100")) {
            photomaxquality.setChecked(true);
        } else {
            photomaxquality.setChecked(false);
        }
        photomaxquality.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                AboutTweak(R.string.med1);
                return true;
            }
        });
        photomaxquality.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


                                                       @Override
                                                       public void onCheckedChanged(CompoundButton buttonView,
                                                                                    boolean isChecked) {
                                                           if (isChecked) {
                                                               if (RootTools.isAccessGiven()) {
                                                                   @SuppressLint("SdCardPath") Command installtweak = new Command(0,
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.media.enc.jpeg.quality/d' /system/build.prop",
                                                                           "echo \"ro.media.enc.jpeg.quality=100\" >> /system/build.prop",
                                                                           "setprop ro.media.enc.jpeg.quality 100",
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
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.media.enc.jpeg.quality/d' /system/build.prop",
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

        /////////////////////////////////////////////////////////////////////////////////
        ////// Improving the quality of the photo shoot and video up to 100% ////////////
        ////////////////////////////////////////////////////////////////////////////////
        CheckBox bpsandmemcapmaxquality = view.findViewById(R.id.bpsandmemcapmaxquality);
        if (HelperClass.BuildPropText().toString().contains("ro.media.dec.jpeg.memcap=8000000")
                && HelperClass.BuildPropText().toString().contains("ro.media.enc.hprof.vid.bps=8000000")) {
            bpsandmemcapmaxquality.setChecked(true);
        } else {
            bpsandmemcapmaxquality.setChecked(false);
        }
        bpsandmemcapmaxquality.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                AboutTweak(R.string.med2);
                return true;
            }
        });
        bpsandmemcapmaxquality.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


                                                              @Override
                                                              public void onCheckedChanged(CompoundButton buttonView,
                                                                                           boolean isChecked) {
                                                                  if (isChecked) {
                                                                      if (RootTools.isAccessGiven()) {
                                                                          @SuppressLint("SdCardPath") Command installtweak = new Command(0,
                                                                                  "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                                  "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                                  "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                                  "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.media.dec.jpeg.memcap/d' /system/build.prop",
                                                                                  "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.media.enc.hprof.vid.bps/d' /system/build.prop",
                                                                                  "echo \"ro.media.dec.jpeg.memcap=8000000\" >> /system/build.prop",
                                                                                  "echo \"ro.media.enc.hprof.vid.bps=8000000\" >> /system/build.prop",
                                                                                  "setprop ro.media.dec.jpeg.memcap 8000000",
                                                                                  "setprop ro.media.enc.hprof.vid.bps 8000000",
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
                                                                                  "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.media.dec.jpeg.memcap/d' /system/build.prop",
                                                                                  "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.media.enc.hprof.vid.bps/d' /system/build.prop",
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

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////// Increase the quality of receive and transmit speech in mobile networks by including AMR WIDEBAND functions ////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        CheckBox amrwidebandtweak = view.findViewById(R.id.amrwidebandtweak);
        if (HelperClass.BuildPropText().toString().contains("ro.ril.enable.amr.wideband=1")) {
            amrwidebandtweak.setChecked(true);
        } else {
            amrwidebandtweak.setChecked(false);
        }
        amrwidebandtweak.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                AboutTweak(R.string.med3);
                return true;
            }
        });
        amrwidebandtweak.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


                                                        @Override
                                                        public void onCheckedChanged(CompoundButton buttonView,
                                                                                     boolean isChecked) {
                                                            if (isChecked) {
                                                                if (RootTools.isAccessGiven()) {
                                                                    @SuppressLint("SdCardPath") Command installtweak = new Command(0,
                                                                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                            "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.ril.enable.amr.wideband/d' /system/build.prop",
                                                                            "echo \"ro.ril.enable.amr.wideband=1\" >> /system/build.prop",
                                                                            "setprop ro.ril.enable.amr.wideband 1",
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
                                                                            "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.ril.enable.amr.wideband/d' /system/build.prop",
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
        ////// Improve Video Streaming quality ////////////
        //////////////////////////////////////////////////
        CheckBox stagefrighttweak = view.findViewById(R.id.stagefrighttweak);
        if (HelperClass.BuildPropText().toString().contains("media.stagefright.enable-player=true")
                && HelperClass.BuildPropText().toString().contains("media.stagefright.enable-meta=true")
                && HelperClass.BuildPropText().toString().contains("media.stagefright.enable-scan=true")
                && HelperClass.BuildPropText().toString().contains("media.stagefright.enable-aac=true")
                && HelperClass.BuildPropText().toString().contains("media.stagefright.enable-qcp=true")
                && HelperClass.BuildPropText().toString().contains("media.stagefright.enable-http=true")
                && HelperClass.BuildPropText().toString().contains("media.stagefright.enable-rtsp=true")
                && HelperClass.BuildPropText().toString().contains("media.stagefright.enable-record=false")) {
            stagefrighttweak.setChecked(true);
        } else {
            stagefrighttweak.setChecked(false);
        }
        stagefrighttweak.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                AboutTweak(R.string.med4);
                return true;
            }
        });
        stagefrighttweak.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


                                                        @Override
                                                        public void onCheckedChanged(CompoundButton buttonView,
                                                                                     boolean isChecked) {
                                                            if (isChecked) {
                                                                if (RootTools.isAccessGiven()) {
                                                                    @SuppressLint("SdCardPath") Command installtweak = new Command(0,
                                                                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                            "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/media.stagefright.enable-aac/d' /system/build.prop",
                                                                            "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/media.stagefright.enable-qcp/d' /system/build.prop",
                                                                            "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/media.stagefright.enable-player/d' /system/build.prop",
                                                                            "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/media.stagefright.enable-meta/d' /system/build.prop",
                                                                            "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/media.stagefright.enable-scan/d' /system/build.prop",
                                                                            "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/media.stagefright.enable-http/d' /system/build.prop",
                                                                            "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/media.stagefright.enable-rtsp/d' /system/build.prop",
                                                                            "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/media.stagefright.enable-record/d' /system/build.prop",
                                                                            "echo \"media.stagefright.enable-player=true\" >> /system/build.prop",
                                                                            "echo \"media.stagefright.enable-meta=true\" >> /system/build.prop",
                                                                            "echo \"media.stagefright.enable-scan=true\" >> /system/build.prop",
                                                                            "echo \"media.stagefright.enable-aac=true\" >> /system/build.prop",
                                                                            "echo \"media.stagefright.enable-qcp=true\" >> /system/build.prop",
                                                                            "echo \"media.stagefright.enable-http=true\" >> /system/build.prop",
                                                                            "echo \"media.stagefright.enable-rtsp=true\" >> /system/build.prop",
                                                                            "echo \"media.stagefright.enable-record=false\" >> /system/build.prop",
                                                                            "setprop media.stagefright.enable-player true",
                                                                            "setprop media.stagefright.enable-meta true",
                                                                            "setprop media.stagefright.enable-scan true",
                                                                            "setprop media.stagefright.enable-aac true",
                                                                            "setprop media.stagefright.enable-qcp true",
                                                                            "setprop media.stagefright.enable-http true",
                                                                            "setprop media.stagefright.enable-rtsp true",
                                                                            "setprop media.stagefright.enable-record false",
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
                                                                            "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/media.stagefright.enable-aac/d' /system/build.prop",
                                                                            "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/media.stagefright.enable-qcp/d' /system/build.prop",
                                                                            "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/media.stagefright.enable-player/d' /system/build.prop",
                                                                            "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/media.stagefright.enable-meta/d' /system/build.prop",
                                                                            "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/media.stagefright.enable-scan/d' /system/build.prop",
                                                                            "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/media.stagefright.enable-http/d' /system/build.prop",
                                                                            "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/media.stagefright.enable-rtsp/d' /system/build.prop",
                                                                            "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/media.stagefright.enable-record/d' /system/build.prop",
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
        ////// Improving flashlight quality ////////////
        ///////////////////////////////////////////////
        CheckBox flashlighttweak = view.findViewById(R.id.flashlighttweak);
        if (HelperClass.BuildPropText().toString().contains("ro.media.capture.flash=led")
                && HelperClass.BuildPropText().toString().contains("ro.media.capture.flashMinV=3300000")
                && HelperClass.BuildPropText().toString().contains("ro.media.capture.torchIntensity=65")
                && HelperClass.BuildPropText().toString().contains("ro.media.capture.flashIntensity=100")) {
            flashlighttweak.setChecked(true);
        } else {
            flashlighttweak.setChecked(false);
        }
        flashlighttweak.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                AboutTweak(R.string.med5);
                return true;
            }
        });
        flashlighttweak.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


                                                       @Override
                                                       public void onCheckedChanged(CompoundButton buttonView,
                                                                                    boolean isChecked) {
                                                           if (isChecked) {
                                                               if (RootTools.isAccessGiven()) {
                                                                   @SuppressLint("SdCardPath") Command installtweak = new Command(0,
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.media.capture.flash/d' /system/build.prop",
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.media.capture.flashMinV/d' /system/build.prop",
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.media.capture.torchIntensity/d' /system/build.prop",
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.media.capture.flashIntensity/d' /system/build.prop",
                                                                           "echo \"ro.media.capture.flash=led\" >> /system/build.prop",
                                                                           "echo \"ro.media.capture.flashMinV=3300000\" >> /system/build.prop",
                                                                           "echo \"ro.media.capture.torchIntensity=65\" >> /system/build.prop",
                                                                           "echo \"ro.media.capture.flashIntensity=100\" >> /system/build.prop",
                                                                           "setprop ro.media.capture.flash led",
                                                                           "setprop ro.media.capture.flashMinV 3300000",
                                                                           "setprop ro.media.capture.torchIntensity 65",
                                                                           "setprop ro.media.capture.flashIntensity 100",
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
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.media.capture.flash/d' /system/build.prop",
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.media.capture.flashMinV/d' /system/build.prop",
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.media.capture.torchIntensity/d' /system/build.prop",
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.media.capture.flashIntensity/d' /system/build.prop",
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