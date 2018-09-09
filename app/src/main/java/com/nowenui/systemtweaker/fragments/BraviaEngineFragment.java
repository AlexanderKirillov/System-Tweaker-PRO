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

public class BraviaEngineFragment extends Fragment {

    AlertDialog.Builder dialogBuilder;
    AlertDialog alertDialog;
    private boolean isClicked;

    public static BraviaEngineFragment newInstance(Bundle bundle) {
        BraviaEngineFragment Bravia = new BraviaEngineFragment();

        if (bundle != null) {
            Bravia.setArguments(bundle);
        }

        return Bravia;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bravia_engine, parent, false);
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {

        final FabButton installbravia = view.findViewById(R.id.braviaimp);
        final Button deletebravia = view.findViewById(R.id.deletebravia);
        final AnimatedProgressButton animateinstallbutton = new AnimatedProgressButton(installbravia, getActivity());

        if ((Build.VERSION.SDK_INT >= 19)) {
            if (new File("/system/bin/sony_xreality").exists()) {
                installbravia.setEnabled(false);
                installbravia.setColor(Color.parseColor("#828282"));
                deletebravia.setEnabled(true);
                deletebravia.setBackgroundColor(Color.parseColor("#e0434b"));
                deletebravia.setTextColor(Color.WHITE);

                //////////////////////////////////////////
                ////// Deleting Bravia........ ///////////
                //////////////////////////////////////////
                deletebravia.setOnClickListener(new View.OnClickListener() {
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
                            @SuppressLint("SdCardPath") Command deletebraviacommand = new Command(0,
                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /data",
                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /data", "mount -o rw,remount /data",
                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /data",
                                    "rm -r /data/data/com.nowenui.systemtweaker/files/Bravia",
                                    "mkdir /data/data/com.nowenui.systemtweaker/files/Bravia",
                                    "/data/data/com.nowenui.systemtweaker/files/busybox unzip /data/data/com.nowenui.systemtweaker/files/Bravia.zip -d /data/data/com.nowenui.systemtweaker/files/Bravia",
                                    "chmod 777 /data/data/com.nowenui.systemtweaker/files/Bravia/delete",
                                    "/data/data/com.nowenui.systemtweaker/files/Bravia/delete",
                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /data",
                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system",
                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /data", "mount -o ro,remount /data",
                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /data");
                            try {
                                RootTools.getShell(true).add(deletebraviacommand);
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
                                    }, 10000);

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
                                    }, 10000);

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
                                    }, 10000);

                                }
                                deletebravia.setEnabled(false);
                                deletebravia.setBackgroundResource(R.drawable.roundbuttonfuck);
                                deletebravia.setTextColor(Color.WHITE);
                                installbravia.setEnabled(true);
                                installbravia.setColor(Color.parseColor("#116062"));
                            } catch (IOException | RootDeniedException | TimeoutException ex) {
                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                            }
                        }
                    }
                });
            } else {
                installbravia.setEnabled(true);
                installbravia.setColor(Color.parseColor("#116062"));
                deletebravia.setEnabled(false);
                deletebravia.setBackgroundResource(R.drawable.roundbuttonfuck);
                deletebravia.setTextColor(Color.WHITE);
            }
        } else {
            installbravia.setEnabled(false);
            installbravia.setColor(Color.parseColor("#828282"));
            deletebravia.setEnabled(false);
            deletebravia.setBackgroundResource(R.drawable.roundbuttonfuck);
        }

        ///////////////////////////////////////////////////
        ////// Installing Bravia Engine........ ///////////
        ///////////////////////////////////////////////////
        installbravia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isClicked) {
                    return;
                }
                animateinstallbutton.startDeterminate();
                isClicked = true;
                v.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        isClicked = false;
                    }
                }, 1000);

                if (RootTools.isAccessGiven()) {
                    Command installbraviacommand = new Command(0,
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /data",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /data", "mount -o rw,remount /data",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /data",
                            "rm -r /data/data/com.nowenui.systemtweaker/files/Bravia",
                            "mkdir /data/data/com.nowenui.systemtweaker/files/Bravia",
                            "/data/data/com.nowenui.systemtweaker/files/busybox unzip /data/data/com.nowenui.systemtweaker/files/Bravia.zip -d /data/data/com.nowenui.systemtweaker/files/Bravia",
                            "chmod 777 /data/data/com.nowenui.systemtweaker/files/Bravia/install",
                            "/data/data/com.nowenui.systemtweaker/files/Bravia/install",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /data",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /data", "mount -o ro,remount /data",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /data");
                    try {
                        RootTools.getShell(true).add(installbraviacommand);
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
                                                    } catch (Exception ex) {
                                                        new SnackBar.Builder(getActivity()).withMessage("ROOT NEEDED!").withBackgroundColorId(R.color.resultbad).show();
                                                    }
                                                }
                                            })
                                            .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.dismiss();
                                                    installbravia.setProgress(0);
                                                }
                                            })
                                            .setIcon(R.drawable.warning)
                                            .show();
                                }
                            }, 10000);

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
                                                    } catch (Exception ex) {
                                                        new SnackBar.Builder(getActivity()).withMessage("ROOT NEEDED!").withBackgroundColorId(R.color.resultbad).show();
                                                    }
                                                }
                                            })
                                            .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.dismiss();
                                                    installbravia.setProgress(0);
                                                }
                                            })
                                            .setIcon(R.drawable.warning)
                                            .show();
                                }
                            }, 10000);

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
                                                    } catch (Exception ex) {
                                                        new SnackBar.Builder(getActivity()).withMessage("ROOT NEEDED!").withBackgroundColorId(R.color.resultbad).show();
                                                    }
                                                }
                                            })
                                            .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.dismiss();
                                                    installbravia.setProgress(0);
                                                }
                                            })
                                            .setIcon(R.drawable.warning)
                                            .show();
                                }
                            }, 10000);

                        }
                        installbravia.setEnabled(false);
                        installbravia.setColor(Color.parseColor("#828282"));
                        deletebravia.setEnabled(true);
                        deletebravia.setBackgroundColor(Color.parseColor("#e0434b"));
                        deletebravia.setTextColor(Color.WHITE);
                    } catch (IOException | RootDeniedException | TimeoutException ex) {
                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                    }
                }
            }
        });
    }
}
