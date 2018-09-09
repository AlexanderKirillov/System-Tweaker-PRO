package com.nowenui.systemtweaker.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.github.mrengineer13.snackbar.SnackBar;
import com.nowenui.systemtweaker.AnimatedProgressButton;
import com.nowenui.systemtweaker.R;
import com.nowenui.systemtweaker.ThemeUtility;
import com.stericson.RootShell.exceptions.RootDeniedException;
import com.stericson.RootShell.execution.Command;
import com.stericson.RootTools.RootTools;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

import mbanje.kurt.fabbutton.FabButton;

public class MusicFragment extends Fragment {

    AlertDialog.Builder dialogBuilder;
    AlertDialog alertDialog;
    private boolean isClicked;

    public static MusicFragment newInstance(Bundle bundle) {
        MusicFragment MusicUtility = new MusicFragment();

        if (bundle != null) {
            MusicUtility.setArguments(bundle);
        }
        return MusicUtility;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.music_improver, parent, false);
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        final FabButton installmusicbutton = view.findViewById(R.id.musicimp);
        final AnimatedProgressButton animateInstallButton = new AnimatedProgressButton(installmusicbutton, getActivity());

        if ((Build.VERSION.SDK_INT >= 19)) {
            if (new File("/system/etc/image_ibeats.bin").exists()) {
                installmusicbutton.setEnabled(false);
                installmusicbutton.setColor(Color.parseColor("#828282"));

                ////////////////////////////////////////
                ////// Delete libs button /////////////
                ///////////////////////////////////////
                final Button deletemusic = view.findViewById(R.id.deletemusic);
                deletemusic.setEnabled(true);
                deletemusic.setBackgroundColor(Color.parseColor("#e0434b"));
                deletemusic.setTextColor(Color.WHITE);
                deletemusic.setOnClickListener(new View.OnClickListener() {
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
                            @SuppressLint("SdCardPath") Command removemusicsystemcommand = new Command(0,
                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /data",
                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /data", "mount -o rw,remount /data",
                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /data",
                                    "rm -f /system/bin/aplay",
                                    "rm -f /system/bin/asound",
                                    "rm -f /system/etc/image_beatbox_bt.bin",
                                    "rm -f /system/etc/image_beats_speaker.bin",
                                    "rm -f /system/etc/image_beats_wireless_bt.bin",
                                    "rm -f /system/etc/image_gec.bin",
                                    "rm -f /system/etc/image_gec_2vol.bin",
                                    "rm -f /system/etc/image_gec_bt.bin",
                                    "rm -f /system/etc/image_htc_earbud.bin",
                                    "rm -f /system/etc/image_htc_earbud_2vol.bin",
                                    "rm -f /system/etc/image_htc_midtier.bin",
                                    "rm -f /system/etc/image_htc_midtier_2vol.bin",
                                    "rm -f /system/etc/image_ibeats.bin",
                                    "rm -f /system/etc/image_ibeats_2vol.bin",
                                    "rm -f /system/etc/image_ibeats_solo.bin",
                                    "rm -f /system/etc/image_ibeats_solo_2vol.bin",
                                    "rm -f /system/etc/image_ibeats_solo_v2.bin",
                                    "rm -f /system/etc/image_ibeats_solo_v2_2vol.bin",
                                    "rm -f /system/etc/image_ibeats_v2.bin",
                                    "rm -f /system/etc/image_ibeats_v2_2vol.bin",
                                    "rm -r /system/etc/tfa",
                                    "rm -r /system/etc/soundimage",
                                    "cp /sdcard/SystemTweaker/libsrsfx.so /system/lib/soundfx/",
                                    "chmod 644 /system/lib/soundfx/libsrsfx.so",
                                    "cp /sdcard/SystemTweaker/audio_effects.conf /system/etc/",
                                    "chmod 644 /system/etc/audio_effects.conf",
                                    "cp /sdcard/SystemTweaker/audio_effects.conf /system/vendor/etc/",
                                    "chmod 777 /system/vendor/etc/audio_effects.conf",
                                    "cp /sdcard/SystemTweaker/default.xml /system/customize/ACC/",
                                    "chmod 777 /system/customize/ACC/default.xml",
                                    "cp /sdcard/SystemTweaker/AudioBTID.csv /system/etc/",
                                    "chmod 777 /system/etc/AudioBTID.csv",
                                    "cp /sdcard/SystemTweaker/AudioBTIDnew.csv /system/etc/",
                                    "chmod 777 /system/etc/AudioBTIDnew.csv",
                                    "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.audio.hifi/d' /system/build.prop",
                                    "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/persist.audio.hifi/d' /system/build.prop",
                                    "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/persist.audio.hifi.volume/d' /system/build.prop",
                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /data",
                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system",
                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /data", "mount -o ro,remount /data",
                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /data");
                            try {
                                RootTools.getShell(true).add(removemusicsystemcommand);

                                if (ThemeUtility.getTheme(getActivity().getApplicationContext()) == 1) {
                                    final ProgressDialog dialog = new ProgressDialog(getActivity(), R.style.AppCompatAlertDialogStyle);
                                    dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                    dialog.setTitle(R.string.delee);
                                    dialog.setMessage(getContext().getResources().getString(R.string.delmessage));
                                    dialog.setIndeterminate(false);
                                    dialog.setCancelable(false);
                                    dialog.show();

                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        public void run() {
                                            dialog.dismiss();
                                            new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.deletebraviaormusic)).withBackgroundColorId(R.color.resultgood).show();
                                            new AlertDialog.Builder(getContext())
                                                    .setTitle(R.string.reboot)
                                                    .setMessage(R.string.rebootdialogmusic)
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
                                    }, 6000);

                                }
                                if (ThemeUtility.getTheme(getActivity().getApplicationContext()) == 2) {
                                    final ProgressDialog dialog = new ProgressDialog(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark));
                                    dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                    dialog.setTitle(R.string.delee);
                                    dialog.setMessage(getContext().getResources().getString(R.string.delmessage));
                                    dialog.setIndeterminate(false);
                                    dialog.setCancelable(false);
                                    dialog.show();

                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        public void run() {
                                            dialog.dismiss();
                                            new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.deletebraviaormusic)).withBackgroundColorId(R.color.resultgood).show();
                                            new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark))
                                                    .setTitle(R.string.reboot)
                                                    .setMessage(R.string.rebootdialogmusic)
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
                                    }, 6000);

                                }
                                if (ThemeUtility.getTheme(getActivity().getApplicationContext()) == 3) {
                                    final ProgressDialog dialog = new ProgressDialog(new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack));
                                    dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                    dialog.setTitle(R.string.delee);
                                    dialog.setMessage(getContext().getResources().getString(R.string.delmessage));
                                    dialog.setIndeterminate(false);
                                    dialog.setCancelable(false);
                                    dialog.show();

                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        public void run() {
                                            dialog.dismiss();
                                            new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.deletebraviaormusic)).withBackgroundColorId(R.color.resultgood).show();
                                            new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack))
                                                    .setTitle(R.string.reboot)
                                                    .setMessage(R.string.rebootdialogmusic)
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
                                    }, 6000);

                                }

                                deletemusic.setEnabled(false);
                                deletemusic.setBackgroundResource(R.drawable.roundbuttonfuck);
                                deletemusic.setTextColor(Color.WHITE);

                                installmusicbutton.setEnabled(true);
                                installmusicbutton.setColor(Color.parseColor("#116062"));
                            } catch (IOException | RootDeniedException | TimeoutException ex) {
                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                            }
                        }
                    }
                });

            } else {
                installmusicbutton.setEnabled(true);
                installmusicbutton.setColor(Color.parseColor("#116062"));
                Button deletemusic = view.findViewById(R.id.deletemusic);
                deletemusic.setEnabled(false);
                deletemusic.setBackgroundResource(R.drawable.roundbuttonfuck);
                deletemusic.setTextColor(Color.WHITE);
            }
        } else {
            installmusicbutton.setEnabled(false);
            installmusicbutton.setColor(Color.parseColor("#828282"));
            final Button deletemusic = view.findViewById(R.id.deletemusic);
            deletemusic.setEnabled(false);
            deletemusic.setBackgroundResource(R.drawable.roundbuttonfuck);
        }

        /////////////////////////////////////////////
        ////// Music libs install button ////////////
        ////////////////////////////////////////////
        view.findViewById(R.id.musicimp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isClicked) {
                    return;
                }
                animateInstallButton.startDeterminate();
                isClicked = true;
                v.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        isClicked = false;
                    }
                }, 1000);

                if (RootTools.isAccessGiven()) {
                    @SuppressLint("SdCardPath") Command installMusicSystem = new Command(0,
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /data",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /data", "mount -o rw,remount /data",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /data",
                            "rm -r /data/data/com.nowenui.systemtweaker/files/Music",
                            "mkdir /data/data/com.nowenui.systemtweaker/files/Music",
                            "/data/data/com.nowenui.systemtweaker/files/busybox unzip /data/data/com.nowenui.systemtweaker/files/Music.zip -d /data/data/com.nowenui.systemtweaker/files/Music",
                            "chmod 777 /data/data/com.nowenui.systemtweaker/files/Music/install",
                            "/data/data/com.nowenui.systemtweaker/files/Music/install",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /data",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /data", "mount -o ro,remount /data",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /data");
                    try {
                        RootTools.getShell(true).add(installMusicSystem);
                        if (ThemeUtility.getTheme(getActivity().getApplicationContext()) == 1) {
                            dialogBuilder = new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark));
                            dialogBuilder.setView(null);
                            alertDialog = dialogBuilder.create();
                            alertDialog.setCancelable(false);
                            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            alertDialog.getWindow().setDimAmount(0);
                            alertDialog.show();

                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                public void run() {
                                    alertDialog.dismiss();
                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.okinstallation)).withBackgroundColorId(R.color.resultgood).show();
                                    new AlertDialog.Builder(getContext())
                                            .setTitle(R.string.reboot)
                                            .setMessage(R.string.rebootdialogmusic)
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
                                                    installmusicbutton.setProgress(0);
                                                }
                                            })
                                            .setIcon(R.drawable.warning)
                                            .show();
                                }
                            }, 6000);

                        }
                        if (ThemeUtility.getTheme(getActivity().getApplicationContext()) == 2) {
                            dialogBuilder = new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark));
                            dialogBuilder.setView(null);
                            alertDialog = dialogBuilder.create();
                            alertDialog.setCancelable(false);
                            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            alertDialog.getWindow().setDimAmount(0);
                            alertDialog.show();

                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                public void run() {
                                    alertDialog.dismiss();
                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.okinstallation)).withBackgroundColorId(R.color.resultgood).show();
                                    new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark))
                                            .setTitle(R.string.reboot)
                                            .setMessage(R.string.rebootdialogmusic)
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
                                                    installmusicbutton.setProgress(0);
                                                }
                                            })
                                            .setIcon(R.drawable.warning)
                                            .show();
                                }
                            }, 6000);

                        }
                        if (ThemeUtility.getTheme(getActivity().getApplicationContext()) == 3) {
                            dialogBuilder = new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark));
                            dialogBuilder.setView(null);
                            alertDialog = dialogBuilder.create();
                            alertDialog.setCancelable(false);
                            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            alertDialog.getWindow().setDimAmount(0);
                            alertDialog.show();

                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                public void run() {
                                    alertDialog.dismiss();
                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.okinstallation)).withBackgroundColorId(R.color.resultgood).show();
                                    new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack))
                                            .setTitle(R.string.reboot)
                                            .setMessage(R.string.rebootdialogmusic)
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
                                                    installmusicbutton.setProgress(0);
                                                }
                                            })
                                            .setIcon(R.drawable.warning)
                                            .show();
                                }
                            }, 6000);

                        }
                        installmusicbutton.setEnabled(false);
                        installmusicbutton.setColor(Color.parseColor("#828282"));
                        final Button deletemusic = view.findViewById(R.id.deletemusic);
                        deletemusic.setEnabled(true);
                        deletemusic.setBackgroundColor(Color.parseColor("#e0434b"));
                        deletemusic.setTextColor(Color.WHITE);

                    } catch (IOException | RootDeniedException | TimeoutException ex) {

                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                    }
                }
            }
        });

    }
}
