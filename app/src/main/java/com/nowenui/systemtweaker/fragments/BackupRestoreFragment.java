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
import android.widget.Button;
import android.widget.TextView;

import com.github.mrengineer13.snackbar.SnackBar;
import com.nowenui.systemtweaker.HelperClass;
import com.nowenui.systemtweaker.R;
import com.nowenui.systemtweaker.ThemeUtility;
import com.stericson.RootShell.exceptions.RootDeniedException;
import com.stericson.RootShell.execution.Command;
import com.stericson.RootTools.RootTools;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeoutException;

public class BackupRestoreFragment extends Fragment {

    private TextView timebuildpropbackup, timeinitdbackup;
    private boolean isClicked;

    public static BackupRestoreFragment newInstance(Bundle bundle) {
        BackupRestoreFragment BackupRestore = new BackupRestoreFragment();

        if (bundle != null) {
            BackupRestore.setArguments(bundle);
        }

        return BackupRestore;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.backup_and_restore, parent, false);
    }

    public void UpdateTimeBackup() {
        final String headerlastbackup = getResources().getString(R.string.lastbackup);
        Thread t = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(500);
                        if (getActivity() != null) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");

                                    final File buildpropbackup = new File("/sdcard/SystemTweaker/backups/buildprop.backup");
                                    if (timebuildpropbackup != null) {
                                        timebuildpropbackup.setText(headerlastbackup + " " + sdf.format(buildpropbackup.lastModified()));
                                    }


                                    final File initdbackup = new File("/sdcard/SystemTweaker/backups/init.d");
                                    if (timeinitdbackup != null) {
                                        timeinitdbackup.setText(headerlastbackup + " " + sdf.format(initdbackup.lastModified()));
                                    }
                                }
                            });
                        }
                    }
                } catch (InterruptedException ignored) {
                }
            }
        };
        t.start();
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {

        final Button backup_build = view.findViewById(R.id.backup_build);
        final Button repair_build = view.findViewById(R.id.repair_build);
        final Button backup_init = view.findViewById(R.id.backup_init);
        final Button repair_init = view.findViewById(R.id.repair_init);
        final Button dalvickclear = view.findViewById(R.id.dalvickclear);
        final Button cacheclear = view.findViewById(R.id.cacheclear);
        timebuildpropbackup = view.findViewById(R.id.timebuildpropbackup);
        timeinitdbackup = view.findViewById(R.id.timeinitdbackup);

        UpdateTimeBackup();

        /////////////////////////////////////////
        ////// Backup build.prop file ///////////
        /////////////////////////////////////////
        backup_build.setBackgroundResource(R.drawable.roundbuttoncal);
        backup_build.setTextColor(Color.WHITE);
        backup_build.setTextSize(15);
        backup_build.setOnClickListener(new View.OnClickListener() {
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
                    @SuppressLint("SdCardPath") Command buildpropbackupcommand = new Command(0,
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                            "mkdir /sdcard/SystemTweaker",
                            "mkdir /sdcard/SystemTweaker/backups",
                            "cp /system/build.prop /sdcard/SystemTweaker/backups/buildprop.backup",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system");
                    try {
                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.backupsucc)).withBackgroundColorId(R.color.resultgood).show();
                        RootTools.getShell(true).add(buildpropbackupcommand);
                    } catch (IOException | RootDeniedException | TimeoutException ex) {
                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                    }
                } else {
                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                }

                UpdateTimeBackup();
                repair_build.setBackgroundResource(R.drawable.roundbuttoncal);
                repair_build.setEnabled(true);
            }
        });


        /////////////////////////////////////////
        ////// Backup init.d folder /////////////
        /////////////////////////////////////////
        if (HelperClass.isInitdSupport() == 0) {
            backup_init.setEnabled(false);
            backup_init.setBackgroundResource(R.drawable.roundbuttonfuck);
        } else {
            backup_init.setEnabled(true);
            backup_init.setBackgroundResource(R.drawable.roundbuttoncal);
        }

        backup_init.setTextColor(Color.WHITE);
        backup_init.setTextSize(15);
        backup_init.setOnClickListener(new View.OnClickListener() {
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
                    @SuppressLint("SdCardPath") Command backupinitdcommmand = new Command(0,
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                            "rm -r /sdcard/SystemTweaker/backups/init.d",
                            "mkdir /sdcard/SystemTweaker",
                            "mkdir /sdcard/SystemTweaker/backups",
                            "mkdir /sdcard/SystemTweaker/backups/init.d",
                            "cp /system/etc/init.d/* /sdcard/SystemTweaker/backups/init.d",
                            "dos2unix /sdcard/SystemTweaker/backups/init.d/*",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system");
                    try {
                        RootTools.getShell(true).add(backupinitdcommmand);
                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.backupsucc)).withBackgroundColorId(R.color.resultgood).show();
                    } catch (IOException | RootDeniedException | TimeoutException ex) {
                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                    }
                } else {
                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                }

                UpdateTimeBackup();
                repair_init.setBackgroundResource(R.drawable.roundbuttoncal);
                repair_init.setEnabled(true);
            }
        });

        /////////////////////////////////////
        ////// Build.prop restore ///////////
        /////////////////////////////////////
        repair_build.setTextSize(15);
        repair_build.setTextColor(Color.WHITE);

        File backupbuildpropfile = new File("/sdcard/SystemTweaker/backups/buildprop.backup");
        if (backupbuildpropfile.exists()) {
            repair_build.setBackgroundResource(R.drawable.roundbuttoncal);
            repair_build.setOnClickListener(new View.OnClickListener() {
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
                        @SuppressLint("SdCardPath") Command repairbuildpropcommand = new Command(0,
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                "rm /system/build.prop",
                                "cp /sdcard/SystemTweaker/backups/buildprop.backup /system/build.prop",
                                "chmod 644 /system/build.prop",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system");
                        try {
                            new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.vosst)).withBackgroundColorId(R.color.resultgood).show();
                            RootTools.getShell(true).add(repairbuildpropcommand);
                            RebootOption();
                        } catch (IOException | RootDeniedException | TimeoutException ex) {
                            new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                        }

                    } else {
                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                    }
                }
            });
        } else {
            repair_build.setEnabled(false);
            repair_build.setBackgroundResource(R.drawable.roundbuttonfuck);
        }

        /////////////////////////////////
        ////// Init.d restore ///////////
        /////////////////////////////////
        repair_init.setTextColor(Color.WHITE);
        repair_init.setTextSize(15);

        File initdpath = new File("/sdcard/SystemTweaker/backups/init.d");
        if ((initdpath.exists() && initdpath.isDirectory()) && (HelperClass.isInitdSupport() == 1)) {
            repair_init.setBackgroundResource(R.drawable.roundbuttoncal);
            repair_init.setOnClickListener(new View.OnClickListener() {
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
                        @SuppressLint("SdCardPath") Command repairinitdcommand = new Command(0,
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                "rm /system/etc/init.d/*",
                                "cp /sdcard/SystemTweaker/backups/init.d/* /system/etc/init.d/",
                                "chmod 755 /system/etc/init.d",
                                "chmod 777 /system/etc/init.d/*",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                        );
                        try {
                            new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.vosst)).withBackgroundColorId(R.color.resultgood).show();
                            RootTools.getShell(true).add(repairinitdcommand);
                            RebootOption();
                        } catch (IOException | RootDeniedException | TimeoutException ex) {
                            new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                        }
                    } else {
                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                    }
                }
            });
        } else {
            repair_init.setEnabled(false);
            repair_init.setBackgroundResource(R.drawable.roundbuttonfuck);
        }


        /////////////////////////////////////
        ////// Clear Dalvik-cache ///////////
        /////////////////////////////////////
        dalvickclear.setBackgroundResource(R.drawable.roundbuttoncal);
        dalvickclear.setTextColor(Color.WHITE);
        dalvickclear.setTextSize(15);
        dalvickclear.setOnClickListener(new View.OnClickListener() {
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
                    @SuppressLint("SdCardPath") Command cleardalvik = new Command(0,
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                            "rm -rf /data/dalvik-cache",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                    );
                    try {
                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.ok)).withBackgroundColorId(R.color.resultgood).show();
                        RootTools.getShell(true).add(cleardalvik);
                        RebootOption();
                    } catch (IOException | RootDeniedException | TimeoutException ex) {
                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                    }
                } else {
                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                }
            }
        });

        /////////////////////////////////////
        ////// Clear cache button ///////////
        /////////////////////////////////////
        cacheclear.setBackgroundResource(R.drawable.roundbuttoncal);
        cacheclear.setTextColor(Color.WHITE);
        cacheclear.setTextSize(15);
        cacheclear.setOnClickListener(new View.OnClickListener() {
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
                    Command clearcache = new Command(0,
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                            "rm -rf /cache",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                    );
                    try {
                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.ok)).withBackgroundColorId(R.color.resultgood).show();
                        RootTools.getShell(true).add(clearcache);
                        RebootOption();
                    } catch (IOException | RootDeniedException | TimeoutException ex) {
                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                    }
                } else {
                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                }
            }
        });
    }

    public void RebootOption() {
        if (ThemeUtility.getTheme(getActivity().getApplicationContext()) == 1) {
            new AlertDialog.Builder(getView().getContext())
                    .setTitle(R.string.reboot)
                    .setMessage(R.string.rebootdialog)
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            if (RootTools.isAccessGiven()) {
                                Command command1 = new Command(0,
                                        "reboot");
                                try {
                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.reboot)).withBackgroundColorId(R.color.resultgood).show();
                                    RootTools.getShell(true).add(command1);
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
        if (ThemeUtility.getTheme(getActivity().getApplicationContext()) == 2) {
            new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark))
                    .setTitle(R.string.reboot)
                    .setMessage(R.string.rebootdialog)
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            if (RootTools.isAccessGiven()) {
                                Command command1 = new Command(0,
                                        "reboot");
                                try {
                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.reboot)).withBackgroundColorId(R.color.resultgood).show();
                                    RootTools.getShell(true).add(command1);
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
        if (ThemeUtility.getTheme(getActivity().getApplicationContext()) == 3) {
            new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack))
                    .setTitle(R.string.reboot)
                    .setMessage(R.string.rebootdialog)
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            if (RootTools.isAccessGiven()) {
                                Command command1 = new Command(0,
                                        "reboot");
                                try {
                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.reboot)).withBackgroundColorId(R.color.resultgood).show();
                                    RootTools.getShell(true).add(command1);
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
    }
}