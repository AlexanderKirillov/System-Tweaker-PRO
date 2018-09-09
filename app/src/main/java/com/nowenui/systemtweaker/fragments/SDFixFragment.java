package com.nowenui.systemtweaker.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
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
import com.nowenui.systemtweaker.R;
import com.nowenui.systemtweaker.ThemeUtility;
import com.stericson.RootShell.exceptions.RootDeniedException;
import com.stericson.RootShell.execution.Command;
import com.stericson.RootTools.RootTools;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class SDFixFragment extends Fragment {

    private boolean isClicked;

    public static SDFixFragment newInstance(Bundle bundle) {
        SDFixFragment SDFix = new SDFixFragment();

        if (bundle != null) {
            SDFix.setArguments(bundle);
        }

        return SDFix;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sdfix, parent, false);

    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {

        if (Build.VERSION.SDK_INT >= 23) {
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (RootTools.isAccessGiven()) {
                        Command executedcommand = new Command(0, "/data/data/com.nowenui.systemtweaker/files/busybox sed -n '1p' /data/excuted") {
                            @Override
                            public void commandOutput(int id, String line) {
                                super.commandOutput(id, line);
                                if (line.contains("EXCUTED")) {
                                    Button sdfix = view.findViewById(R.id.sdfix);
                                    sdfix.setEnabled(false);
                                    sdfix.setText(R.string.sdalready);
                                    sdfix.setBackgroundResource(R.drawable.roundbuttonfuck);
                                    sdfix.setTextSize(22);
                                    sdfix.setTextColor(Color.WHITE);
                                    final Button perereg = view.findViewById(R.id.pereregistrate);
                                    perereg.setBackgroundResource(R.drawable.roundbuttoncal);
                                    perereg.setTextSize(18);
                                    perereg.setTextColor(Color.WHITE);

                                    //////////////////////////////////////////
                                    ////// Re-registration button ////////////
                                    /////////////////////////////////////////
                                    perereg.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(final View v) {
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

                                            perereg.setEnabled(false);
                                            perereg.setBackgroundResource(R.drawable.roundbuttonfuck);

                                            if (RootTools.isAccessGiven()) {
                                                @SuppressLint("SdCardPath") Command processsdfixcommand = new Command(0,
                                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /data",
                                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i 's~<perms><item name=\"android.permission.WRITE_MEDIA_STORAGE\" granted=\"true\" flags=\"0\" />~<perms>~g' /data/system/packages.xml\n",
                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i 's~<perms>~<perms><item name=\"android.permission.WRITE_MEDIA_STORAGE\" granted=\"true\" flags=\"0\" />~g' /data/system/packages.xml\n",
                                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /data",
                                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                                );
                                                try {
                                                    RootTools.getShell(true).add(processsdfixcommand);
                                                    RebootDialog();
                                                } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                                                }
                                            } else {
                                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                                            }
                                        }
                                    });

                                } else {
                                    Button perereg = view.findViewById(R.id.pereregistrate);
                                    perereg.setEnabled(false);
                                    perereg.setBackgroundResource(R.drawable.roundbuttonfuck);
                                    perereg.setTextSize(22);
                                    perereg.setTextColor(Color.WHITE);

                                    final Button sdfix = view.findViewById(R.id.sdfix);
                                    sdfix.setBackgroundResource(R.drawable.roundbuttoncal);
                                    sdfix.setTextSize(22);
                                    sdfix.setTextColor(Color.WHITE);

                                    ////////////////////////////////////
                                    ////// SDFix Button ///////////////
                                    ///////////////////////////////////

                                    sdfix.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(final View v) {
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

                                            final Button perereg = view.findViewById(R.id.pereregistrate);
                                            perereg.setBackgroundResource(R.drawable.roundbuttoncal);
                                            perereg.setTextSize(18);
                                            perereg.setEnabled(true);
                                            perereg.setTextColor(Color.WHITE);

                                            final Button sdfix = view.findViewById(R.id.sdfix);
                                            sdfix.setBackgroundResource(R.drawable.roundbuttonfuck);
                                            sdfix.setTextSize(22);
                                            sdfix.setEnabled(false);
                                            sdfix.setTextColor(Color.WHITE);

                                            /////////////////////////////////////////
                                            ////// Re-registration apps ////////////
                                            ////////////////////////////////////////
                                            perereg.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(final View v) {
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

                                                    perereg.setEnabled(false);
                                                    perereg.setBackgroundResource(R.drawable.roundbuttonfuck);

                                                    if (RootTools.isAccessGiven()) {
                                                        @SuppressLint("SdCardPath") Command pereregcommand = new Command(0,
                                                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /data",
                                                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                "/data/data/com.nowenui.systemtweaker/files/busybox sed -i 's~<perms><item name=\"android.permission.WRITE_MEDIA_STORAGE\" granted=\"true\" flags=\"0\" />~<perms>~g' /data/system/packages.xml\n",
                                                                "/data/data/com.nowenui.systemtweaker/files/busybox sed -i 's~<perms>~<perms><item name=\"android.permission.WRITE_MEDIA_STORAGE\" granted=\"true\" flags=\"0\" />~g' /data/system/packages.xml\n",
                                                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /data",
                                                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                                        );
                                                        try {
                                                            RootTools.getShell(true).add(pereregcommand);
                                                            RebootDialog();
                                                        } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                            new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                                                        }
                                                    } else {
                                                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                                                    }
                                                }
                                            });

                                            if (RootTools.isAccessGiven()) {
                                                @SuppressLint("SdCardPath") Command sdfixcommand = new Command(0,
                                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /data",
                                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /data", "mount -o rw,remount /data",
                                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /data",
                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/<permission name=\"android.permission.WRITE_MEDIA_STORAGE\" >/a <group gid=\"media_rw\" />' /system/etc/permissions/platform.xml",
                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/<permission name=\"android.permission.WRITE_EXTERNAL_STORAGE\" >/a <group gid=\"media_rw\" />' /system/etc/permissions/platform.xml",
                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/<permission name=\"android.permission.WRITE_MEDIA_STORAGE\">/a <group gid=\"media_rw\" />' /system/etc/permissions/platform.xml",
                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/<permission name=\"android.permission.WRITE_EXTERNAL_STORAGE\">/a <group gid=\"media_rw\" />' /system/etc/permissions/platform.xml",
                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i 's~<perms>~<perms><item name=\"android.permission.WRITE_MEDIA_STORAGE\" granted=\"true\" flags=\"0\" />~g' /data/system/packages.xml",
                                                        "echo \"EXCUTED\" > /data/excuted",
                                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /data",
                                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system",
                                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /data", "mount -o ro,remount /data",
                                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw=o /data"
                                                );
                                                try {
                                                    RootTools.getShell(true).add(sdfixcommand);
                                                    RebootDialog();
                                                } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                                                }
                                            } else {
                                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                                            }
                                        }
                                    });
                                }
                            }
                        };

                        try {
                            RootTools.getShell(true).add(executedcommand);
                        } catch (IOException | RootDeniedException | TimeoutException ex) {
                            new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                        }
                    } else {
                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                    }

                }
            }, 800);
        } else {
            final Handler handler1 = new Handler();
            handler1.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Button perereg = view.findViewById(R.id.pereregistrate);
                    perereg.setEnabled(false);
                    perereg.setBackgroundResource(R.drawable.roundbuttonfuck);
                    perereg.setTextSize(22);
                    perereg.setTextColor(Color.WHITE);

                    if (RootTools.isAccessGiven()) {
                        Command command2 = new Command(0, "sed -n '1p' /system/etc/excuted") {
                            @Override
                            public void commandOutput(int id, String line) {
                                super.commandOutput(id, line);
                                if (line.contains("EXCUTED")) {
                                    Button sdfix = view.findViewById(R.id.sdfix);
                                    sdfix.setEnabled(false);
                                    sdfix.setText(R.string.sdalready);
                                    sdfix.setBackgroundResource(R.drawable.roundbuttonfuck);
                                    sdfix.setTextSize(22);
                                    sdfix.setTextColor(Color.WHITE);
                                } else {
                                    final Button sdfix = view.findViewById(R.id.sdfix);
                                    sdfix.setBackgroundResource(R.drawable.roundbuttoncal);
                                    sdfix.setTextSize(22);
                                    sdfix.setTextColor(Color.WHITE);

                                    ////////////////////////////////////
                                    ////// SD FIX BUTTON  /////////////
                                    ///////////////////////////////////

                                    sdfix.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(final View v) {
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
                                            sdfix.setEnabled(false);

                                            if (RootTools.isAccessGiven()) {
                                                @SuppressLint("SdCardPath") Command command1 = new Command(0,
                                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/<permission name=\"android.permission.WRITE_MEDIA_STORAGE\" >/a <group gid=\"media_rw\" />' /system/etc/permissions/platform.xml",
                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/<permission name=\"android.permission.WRITE_EXTERNAL_STORAGE\" >/a <group gid=\"media_rw\" />' /system/etc/permissions/platform.xml",
                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/<permission name=\"android.permission.WRITE_MEDIA_STORAGE\">/a <group gid=\"media_rw\" />' /system/etc/permissions/platform.xml",
                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/<permission name=\"android.permission.WRITE_EXTERNAL_STORAGE\">/a <group gid=\"media_rw\" />' /system/etc/permissions/platform.xml",
                                                        "echo \"EXCUTED\" > /system/etc/excuted",
                                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                                );
                                                try {
                                                    RootTools.getShell(true).add(command1);
                                                    RebootDialog();
                                                } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                                                }
                                            } else {
                                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                                            }
                                        }

                                    });
                                }
                            }
                        };
                        try {
                            RootTools.getShell(true).add(command2);
                        } catch (IOException | RootDeniedException | TimeoutException ex) {
                            new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                        }
                    } else {
                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                    }

                }
            }, 800);
        }

        //////////////////////////////////////////////////
        ////// Information about fix (button) ////////////
        /////////////////////////////////////////////////
        Button aboutfix = view.findViewById(R.id.aboutfix);
        aboutfix.setBackgroundResource(R.drawable.roundbuttoninfo);
        aboutfix.setTextSize(18);
        aboutfix.setTextColor(Color.WHITE);

        aboutfix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ThemeUtility.getTheme(getActivity().getApplicationContext()) == 1) {
                    new android.app.AlertDialog.Builder(getContext())
                            .setTitle(R.string.sdfix8)
                            .setMessage(R.string.sdfix9)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(R.drawable.info)
                            .show();
                }
                if (ThemeUtility.getTheme(getActivity().getApplicationContext()) == 2) {
                    new android.app.AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark))
                            .setTitle(R.string.sdfix8)
                            .setMessage(R.string.sdfix9)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(R.drawable.info)
                            .show();
                }
                if (ThemeUtility.getTheme(getActivity().getApplicationContext()) == 3) {
                    new android.app.AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack))
                            .setTitle(R.string.sdfix8)
                            .setMessage(R.string.sdfix9)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(R.drawable.info)
                            .show();
                }

            }
        });

    }

    public void RebootDialog() {
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
                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.ok)).withBackgroundColorId(R.color.resultgood).show();
                    new AlertDialog.Builder(getView().getContext())
                            .setTitle(R.string.reboot)
                            .setMessage(R.string.rebootdialog)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                    if (RootTools.isAccessGiven()) {
                                        Command command1 = new Command(0,
                                                "reboot");
                                        try {
                                            RootTools.getShell(true).add(command1);
                                            new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.reboot)).withBackgroundColorId(R.color.resultgood).show();
                                        } catch (IOException | RootDeniedException | TimeoutException ex) {

                                            new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                                        }
                                    } else {
                                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.erroroot)).withBackgroundColorId(R.color.resultbad).show();
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
                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.ok)).withBackgroundColorId(R.color.resultgood).show();
                    new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark))
                            .setTitle(R.string.reboot)
                            .setMessage(R.string.rebootdialog)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                    if (RootTools.isAccessGiven()) {
                                        Command command1 = new Command(0,
                                                "reboot");
                                        try {
                                            RootTools.getShell(true).add(command1);
                                            new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.reboot)).withBackgroundColorId(R.color.resultgood).show();
                                        } catch (IOException | RootDeniedException | TimeoutException ex) {

                                            new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                                        }
                                    } else {
                                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.erroroot)).withBackgroundColorId(R.color.resultbad).show();
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
                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.ok)).withBackgroundColorId(R.color.resultgood).show();
                    new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack))
                            .setTitle(R.string.reboot)
                            .setMessage(R.string.rebootdialog)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                    if (RootTools.isAccessGiven()) {
                                        Command command1 = new Command(0,
                                                "reboot");
                                        try {
                                            RootTools.getShell(true).add(command1);
                                            new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.reboot)).withBackgroundColorId(R.color.resultgood).show();
                                        } catch (IOException | RootDeniedException | TimeoutException ex) {

                                            new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                                        }
                                    } else {
                                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.erroroot)).withBackgroundColorId(R.color.resultbad).show();
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
            }, 4000);

        }
    }
}