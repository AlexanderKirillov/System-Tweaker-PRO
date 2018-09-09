package com.nowenui.systemtweaker.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.github.mrengineer13.snackbar.SnackBar;
import com.nowenui.systemtweaker.HelperClass;
import com.nowenui.systemtweaker.R;
import com.nowenui.systemtweaker.ThemeUtility;
import com.stericson.RootShell.exceptions.RootDeniedException;
import com.stericson.RootShell.execution.Command;
import com.stericson.RootTools.RootTools;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeoutException;

public class EntropyGeneratorFragment extends Fragment {
    String string;
    private boolean isClicked;

    public static EntropyGeneratorFragment newInstance(Bundle bundle) {
        EntropyGeneratorFragment Entropy = new EntropyGeneratorFragment();

        if (bundle != null) {
            Entropy.setArguments(bundle);
        }

        return Entropy;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.entropy_generator, parent, false);
    }


    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        setRetainInstance(true);

        File rngdfile = new File("/system/bin/rngd");
        if (RootTools.isAccessGiven()) {
            Command getrgnd = new Command(0,
                    "chmod 777 /data/rngd.pid");
            try {
                RootTools.getShell(true).add(getrgnd);
            } catch (IOException | RootDeniedException | TimeoutException ex) {

            }
        }

        if (rngdfile.exists()) {
            new ViewsUpdate(getActivity(), view).execute();
        } else {
            TextView entropystatus = view.findViewById(R.id.entropystatus);
            entropystatus.setText("");

            Button startentropy = view.findViewById(R.id.startentropy);
            startentropy.setBackgroundResource(R.drawable.roundbuttonfuck);
            startentropy.setEnabled(false);
            startentropy.setTextColor(Color.WHITE);

            Button stopentropy = view.findViewById(R.id.stopentropy);
            stopentropy.setBackgroundResource(R.drawable.roundbuttonfuck);
            stopentropy.setEnabled(false);
            stopentropy.setTextColor(Color.WHITE);


        }

        final TextView entropy_pull = view.findViewById(R.id.entropy_pull);
        entropy_pull.setBackgroundResource(R.drawable.roundbuttongood);
        entropy_pull.setTextColor(Color.WHITE);

        ////////////////////////////////////////
        ////// Get Entropy pool size ///////////
        ////////////////////////////////////////
        if (getActivity() != null) {
            Timer timer = new Timer();
            long delay = 0;
            long period = 1000;
            if (getActivity() != null) {
                timer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        if (getActivity() != null) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    entropy_pull.setText(HelperClass.getEntropyPull());
                                }
                            });
                        }
                    }
                }, delay, period);
            }
        }

        //////////////////////////////
        ////// Entropy button ////////
        //////////////////////////////
        final Button entropy = view.findViewById(R.id.entropy);
        entropy.setBackgroundResource(R.drawable.roundbuttoncal);
        entropy.setTextColor(Color.WHITE);
        if (rngdfile.exists()) {
            final Resources res = getResources();
            entropy.setEnabled(false);
            entropy.setBackgroundResource(R.drawable.roundbuttonfuck);
            entropy.setText(res.getString(R.string.fstrimlibok));
        } else {
            entropy.setOnClickListener(new View.OnClickListener() {
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

                    ///////////////////////////////////////
                    ////// Entropy start script ///////////
                    ///////////////////////////////////////
                    final CheckBox bootenropyservice = view.findViewById(R.id.bootenropyservice);
                    if (new File("/system/etc/init.d/entropy_start").exists()) {
                        bootenropyservice.setChecked(true);
                    } else {
                        bootenropyservice.setChecked(false);
                    }
                    if (HelperClass.isInitdSupport() == 0) {
                        bootenropyservice.setEnabled(false);
                    } else {
                        bootenropyservice.setEnabled(true);
                    }
                    bootenropyservice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                                                     @Override
                                                                     public void onCheckedChanged(CompoundButton buttonView,
                                                                                                  boolean isChecked) {
                                                                         if (isChecked) {
                                                                             if (RootTools.isAccessGiven()) {
                                                                                 @SuppressLint("SdCardPath") Command enropyinitdcommand = new Command(0,
                                                                                         "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                                         "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                                         "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                                         "cp /data/data/com.nowenui.systemtweaker/files/entropy_start /system/etc/init.d/",
                                                                                         "chmod 777 /system/etc/init.d/entropy_start",
                                                                                         "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                                         "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                                         "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system");
                                                                                 try {
                                                                                     RootTools.getShell(true).add(enropyinitdcommand);
                                                                                     new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.enable)).withBackgroundColorId(R.color.resultgood).show();
                                                                                 } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                                                     new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                                                                                 }
                                                                             } else {
                                                                                 new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                                                                             }
                                                                         } else {
                                                                             if (RootTools.isAccessGiven()) {
                                                                                 @SuppressLint("SdCardPath") Command deleteinitdentropy = new Command(0,
                                                                                         "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                                         "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                                         "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                                         "rm -f /system/etc/init.d/entropy_start",
                                                                                         "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                                         "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                                         "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                                                                 );
                                                                                 try {
                                                                                     RootTools.getShell(true).add(deleteinitdentropy);
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

                    entropy.setEnabled(false);
                    entropy.setBackgroundResource(R.drawable.roundbuttonfuck);
                    final Resources res = getResources();
                    entropy.setText(res.getString(R.string.fstrimlibok));

                    final Button startentropy = view.findViewById(R.id.startentropy);
                    startentropy.setBackgroundResource(R.drawable.roundbuttoncal);
                    startentropy.setEnabled(true);
                    startentropy.setTextColor(Color.WHITE);

                    final Button stopentropy = view.findViewById(R.id.stopentropy);
                    stopentropy.setBackgroundResource(R.drawable.roundbuttoncal);
                    stopentropy.setEnabled(true);
                    stopentropy.setTextColor(Color.WHITE);

                    new ViewsUpdate(getActivity(), view).execute();

                    /////////////////////////////////
                    ////// Delete Entropy ///////////
                    /////////////////////////////////
                    final Button delete = view.findViewById(R.id.delete);
                    delete.setEnabled(true);
                    delete.setBackgroundColor(Color.parseColor("#e0434b"));
                    delete.setOnClickListener(new View.OnClickListener() {
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

                            bootenropyservice.setEnabled(false);
                            if (new File("system/etc/init.d/entropy_start").exists()) {
                                bootenropyservice.setChecked(true);
                            } else {
                                bootenropyservice.setChecked(false);
                            }

                            delete.setEnabled(false);
                            entropy.setEnabled(true);
                            entropy.setBackgroundResource(R.drawable.roundbuttoncal);

                            startentropy.setBackgroundResource(R.drawable.roundbuttonfuck);
                            startentropy.setEnabled(false);
                            startentropy.setTextColor(Color.WHITE);
                            stopentropy.setBackgroundResource(R.drawable.roundbuttonfuck);
                            stopentropy.setEnabled(false);
                            stopentropy.setTextColor(Color.WHITE);
                            delete.setBackgroundResource(R.drawable.roundbuttonfuck);

                            if (RootTools.isAccessGiven()) {
                                @SuppressLint("SdCardPath") Command deletentropycommand = new Command(0,
                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                        "rm -f /system/bin/rngd",
                                        "rm -f  /system/bin/entro",
                                        "rm -f  /system/bin/entropy_enabler",
                                        "rm -f  /system/bin/entropy_disable",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system");
                                try {
                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.entropydele)).withBackgroundColorId(R.color.resultgood).show();
                                    RootTools.getShell(true).add(deletentropycommand);
                                    if (ThemeUtility.getTheme(getActivity().getApplicationContext()) == 1) {
                                        new AlertDialog.Builder(v.getContext())
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
                                } catch (IOException | RootDeniedException | TimeoutException ex) {
                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                                }
                            } else {
                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                            }
                        }
                    });

                    if (RootTools.isAccessGiven()) {
                        @SuppressLint("SdCardPath") Command installentropycommand = new Command(0,
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                "cp /data/data/com.nowenui.systemtweaker/files/rngd /system/bin/",
                                "chmod 755 /system/bin/rngd",
                                "cp /data/data/com.nowenui.systemtweaker/files/entro /system/bin/",
                                "chmod 755 /system/bin/entro",
                                "cp /data/data/com.nowenui.systemtweaker/files/entropy_enabler /system/bin/",
                                "chmod 777 /system/bin/entropy_enabler",
                                "cp /data/data/com.nowenui.systemtweaker/files/entropy_disable /system/bin/",
                                "chmod 777 /system/bin/entropy_disable",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system");
                        try {
                            RootTools.getShell(true).add(installentropycommand);
                            new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.fileinstall)).withBackgroundColorId(R.color.resultgood).show();
                        } catch (IOException | RootDeniedException | TimeoutException ex) {
                            new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                        }
                    } else {
                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                    }
                }
            });
        }

        ///////////////////////////////////////
        ////// Entropy start script ///////////
        ///////////////////////////////////////
        final CheckBox bootenropyservice = view.findViewById(R.id.bootenropyservice);
        if (!(rngdfile.exists())) {
            bootenropyservice.setEnabled(false);
        } else {
            if (HelperClass.isInitdSupport() == 0) {
                bootenropyservice.setEnabled(false);
            } else {
                bootenropyservice.setEnabled(true);
            }
        }
        if (new File("/system/etc/init.d/entropy_start").exists()) {
            bootenropyservice.setChecked(true);
        } else {
            bootenropyservice.setChecked(false);
        }
        bootenropyservice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


                                                         @Override
                                                         public void onCheckedChanged(CompoundButton buttonView,
                                                                                      boolean isChecked) {
                                                             if (isChecked) {
                                                                 if (RootTools.isAccessGiven()) {
                                                                     @SuppressLint("SdCardPath") Command installentropyinitdcommand = new Command(0,
                                                                             "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                             "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                             "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                             "cp /data/data/com.nowenui.systemtweaker/files/entropy_start /system/etc/init.d/",
                                                                             "chmod 777 /system/etc/init.d/entropy_start",
                                                                             "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                             "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                             "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system");
                                                                     try {
                                                                         RootTools.getShell(true).add(installentropyinitdcommand);
                                                                         new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.enable)).withBackgroundColorId(R.color.resultgood).show();
                                                                     } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                                         new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                                                                     }
                                                                 } else {
                                                                     new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                                                                 }
                                                             } else {
                                                                 if (RootTools.isAccessGiven()) {
                                                                     @SuppressLint("SdCardPath") Command deleteentropyinitdcommand = new Command(0,
                                                                             "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                             "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                             "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                             "rm -f /system/etc/init.d/entropy_start",
                                                                             "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                             "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                             "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                                                     );
                                                                     try {
                                                                         RootTools.getShell(true).add(deleteentropyinitdcommand);
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

        /////////////////////////////////
        ////// Delete Entropy ///////////
        /////////////////////////////////
        final Button delete = view.findViewById(R.id.delete);
        delete.setTextColor(Color.WHITE);
        if (rngdfile.exists()) {
            delete.setEnabled(true);
            delete.setBackgroundColor(Color.parseColor("#e0434b"));
            delete.setOnClickListener(new View.OnClickListener() {
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

                    bootenropyservice.setEnabled(false);
                    if (new File("/system/etc/init.d/entropy_start").exists()) {
                        bootenropyservice.setChecked(true);
                    } else {
                        bootenropyservice.setChecked(false);
                    }
                    delete.setEnabled(false);
                    entropy.setEnabled(true);
                    entropy.setBackgroundResource(R.drawable.roundbuttoncal);
                    final Resources res = getResources();
                    entropy.setText(res.getString(R.string.installfstrim));

                    Button startentropy = view.findViewById(R.id.startentropy);
                    startentropy.setBackgroundResource(R.drawable.roundbuttonfuck);
                    startentropy.setEnabled(false);
                    startentropy.setTextColor(Color.WHITE);

                    Button stopentropy = view.findViewById(R.id.stopentropy);
                    stopentropy.setBackgroundResource(R.drawable.roundbuttonfuck);
                    stopentropy.setEnabled(false);
                    stopentropy.setTextColor(Color.WHITE);
                    delete.setBackgroundResource(R.drawable.roundbuttonfuck);

                    if (RootTools.isAccessGiven()) {
                        @SuppressLint("SdCardPath") Command command1 = new Command(0,
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                "rm -f /system/bin/rngd",
                                "rm -f  /system/bin/entro",
                                "rm -f  /system/bin/entropy_enabler",
                                "rm -f  /system/bin/entropy_disable",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system");
                        try {
                            new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.entropydele)).withBackgroundColorId(R.color.resultgood).show();
                            RootTools.getShell(true).add(command1);
                            if (ThemeUtility.getTheme(getActivity().getApplicationContext()) == 1) {
                                new AlertDialog.Builder(v.getContext())
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

                        } catch (IOException | RootDeniedException | TimeoutException ex) {
                            new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                        }
                    } else {
                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                    }

                }
            });

        } else {
            delete.setBackgroundResource(R.drawable.roundbuttonfuck);
            delete.setEnabled(false);

        }
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onSaveInstanceState(@NonNull final Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    public class Wrapper {
        public String oneperem;
        public boolean check;
        public boolean sucess;
    }

    public class ViewsUpdate extends AsyncTask<String, Void, Wrapper> {

        public Button startentropy, stopentropy;
        public TextView entropystatus;
        private Context mContext;
        private View rootView;


        public ViewsUpdate(Context context, View rootView) {
            this.mContext = context;
            this.rootView = rootView;
        }

        @Override
        public Wrapper doInBackground(String... args) {
            final Wrapper w = new Wrapper();

            final CountDownLatch latch = new CountDownLatch(1);

            w.oneperem = HelperClass.checkIfEntropyRunning();

            File file = new File("/data/rngd.pid");

            StringBuilder text = new StringBuilder();

            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;

                while ((line = br.readLine()) != null) {
                    text.append(line);
                }
                br.close();
            } catch (IOException ignored) {
            }
            if ((text.toString().isEmpty()) && (w.oneperem.isEmpty())) {
                w.check = false;
            } else {
                w.check = (new File("/proc/" + text.toString().trim() + "/cmdline").exists()) || (w.oneperem != null && !w.oneperem.isEmpty());
            }
            latch.countDown();

            return w;
        }

        @Override
        public void onPostExecute(Wrapper w) {

            final Resources res = getActivity().getApplicationContext().getResources();

            if (w.check) {
                entropystatus = rootView.findViewById(R.id.entropystatus);
                entropystatus.setText(res.getString(R.string.enableservice));
                entropystatus.setBackgroundResource(R.drawable.roundbuttongood);
                startentropy = rootView.findViewById(R.id.startentropy);
                startentropy.setBackgroundResource(R.drawable.roundbuttonfuck);
                startentropy.setEnabled(false);
                startentropy.setTextColor(Color.WHITE);

                stopentropy = rootView.findViewById(R.id.stopentropy);
                stopentropy.setBackgroundResource(R.drawable.roundbuttoncal);
                stopentropy.setEnabled(true);
                stopentropy.setTextColor(Color.WHITE);
                stopentropy.setOnClickListener(new View.OnClickListener() {
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
                        stopentropy.setEnabled(false);

                        entropystatus = rootView.findViewById(R.id.entropystatus);
                        entropystatus.setText(res.getString(R.string.disabledserivce));
                        entropystatus.setBackgroundResource(R.drawable.roundbuttonbad);

                        stopentropy = rootView.findViewById(R.id.stopentropy);
                        stopentropy.setBackgroundResource(R.drawable.roundbuttonfuck);
                        stopentropy.setTextColor(Color.WHITE);
                        startentropy = rootView.findViewById(R.id.startentropy);
                        startentropy.setBackgroundResource(R.drawable.roundbuttoncal);
                        startentropy.setEnabled(true);
                        startentropy.setTextColor(Color.WHITE);

                        startentropy.setOnClickListener(new View.OnClickListener() {
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
                                startentropy.setEnabled(false);

                                entropystatus.setText(res.getString(R.string.enableservice));
                                entropystatus.setBackgroundResource(R.drawable.roundbuttongood);
                                startentropy = rootView.findViewById(R.id.startentropy);
                                startentropy.setBackgroundResource(R.drawable.roundbuttonfuck);
                                startentropy.setTextColor(Color.WHITE);

                                stopentropy = rootView.findViewById(R.id.stopentropy);
                                stopentropy.setBackgroundResource(R.drawable.roundbuttoncal);
                                stopentropy.setEnabled(true);
                                stopentropy.setTextColor(Color.WHITE);

                                new startEntropy(getActivity(), getView()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                            }
                        });
                        new stopEntropy(getActivity(), getView()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    }

                });
            }
            if (!w.check) {
                entropystatus = rootView.findViewById(R.id.entropystatus);
                entropystatus.setText(res.getString(R.string.disabledserivce));
                entropystatus.setBackgroundResource(R.drawable.roundbuttonbad);

                stopentropy = rootView.findViewById(R.id.stopentropy);
                stopentropy.setBackgroundResource(R.drawable.roundbuttonfuck);
                stopentropy.setEnabled(false);
                stopentropy.setTextColor(Color.WHITE);
                startentropy = rootView.findViewById(R.id.startentropy);
                startentropy.setBackgroundResource(R.drawable.roundbuttoncal);
                startentropy.setEnabled(true);
                startentropy.setTextColor(Color.WHITE);
                startentropy.setOnClickListener(new View.OnClickListener() {
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
                        startentropy.setEnabled(false);

                        entropystatus.setText(res.getString(R.string.enableservice));
                        entropystatus.setBackgroundResource(R.drawable.roundbuttongood);
                        startentropy = rootView.findViewById(R.id.startentropy);
                        startentropy.setBackgroundResource(R.drawable.roundbuttonfuck);
                        startentropy.setTextColor(Color.WHITE);

                        stopentropy = rootView.findViewById(R.id.stopentropy);
                        stopentropy.setBackgroundResource(R.drawable.roundbuttoncal);
                        stopentropy.setEnabled(true);
                        stopentropy.setTextColor(Color.WHITE);

                        ///////////////////////////////////////
                        ////// STOP ENTROPY SERVICE ///////////
                        ///////////////////////////////////////
                        stopentropy.setOnClickListener(new View.OnClickListener() {
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
                                stopentropy.setEnabled(false);

                                entropystatus = rootView.findViewById(R.id.entropystatus);
                                entropystatus.setText(res.getString(R.string.disabledserivce));
                                entropystatus.setBackgroundResource(R.drawable.roundbuttonbad);

                                stopentropy = rootView.findViewById(R.id.stopentropy);
                                stopentropy.setBackgroundResource(R.drawable.roundbuttonfuck);
                                stopentropy.setTextColor(Color.WHITE);
                                startentropy = rootView.findViewById(R.id.startentropy);
                                startentropy.setBackgroundResource(R.drawable.roundbuttoncal);
                                startentropy.setEnabled(true);
                                startentropy.setTextColor(Color.WHITE);

                                startentropy.setOnClickListener(new View.OnClickListener() {
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
                                        startentropy.setEnabled(false);

                                        entropystatus.setText(res.getString(R.string.enableservice));
                                        entropystatus.setBackgroundResource(R.drawable.roundbuttongood);
                                        startentropy = rootView.findViewById(R.id.startentropy);
                                        startentropy.setBackgroundResource(R.drawable.roundbuttonfuck);
                                        startentropy.setTextColor(Color.WHITE);

                                        stopentropy = rootView.findViewById(R.id.stopentropy);
                                        stopentropy.setBackgroundResource(R.drawable.roundbuttoncal);
                                        stopentropy.setEnabled(true);
                                        stopentropy.setTextColor(Color.WHITE);

                                        new startEntropy(getActivity(), getView()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                                    }

                                });

                                new stopEntropy(getActivity(), getView()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                            }

                        });

                        new startEntropy(getActivity(), getView()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    }

                });


            }
        }
    }

    public class stopEntropy extends AsyncTask<String, Void, Wrapper> {

        private Context mContext;
        private View rootView;

        public stopEntropy(Context context, View rootView) {
            this.mContext = context;
            this.rootView = rootView;
        }

        @Override
        public Wrapper doInBackground(String... args) {
            final Wrapper w1 = new Wrapper();

            final CountDownLatch latch3 = new CountDownLatch(1);

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
                outputStream.writeBytes("entropy_disable\n");
                outputStream.flush();
                outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system\n");
                outputStream.flush();
                outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system\n");
                outputStream.flush();
                outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system\n");
                outputStream.flush();
                w1.sucess = true;
                outputStream.writeBytes("Exit\n");
                outputStream.flush();
                latch3.countDown();
            } catch (IOException ignored) {
                w1.sucess = false;
            }

            return w1;
        }

        @Override
        public void onPostExecute(Wrapper w1) {

            if (w1.sucess) {
                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.stopedservice)).withBackgroundColorId(R.color.resultgood).show();
            } else {
                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
            }

        }
    }

    public class startEntropy extends AsyncTask<String, Void, Wrapper> {

        private Context mContext;
        private View rootView;

        public startEntropy(Context context, View rootView) {
            this.mContext = context;
            this.rootView = rootView;
        }

        @Override
        public Wrapper doInBackground(String... args) {
            final Wrapper w2 = new Wrapper();
            final CountDownLatch latch2 = new CountDownLatch(1);
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
                outputStream.writeBytes("entropy_enabler\n");
                outputStream.flush();
                outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system\n");
                outputStream.flush();
                outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system\n");
                outputStream.flush();
                outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system\n");
                outputStream.flush();
                w2.sucess = true;
                outputStream.writeBytes("Exit\n");
                outputStream.flush();
                latch2.countDown();

            } catch (IOException ignored) {
                w2.sucess = false;
            }
            return w2;
        }

        @Override
        public void onPostExecute(Wrapper w2) {

            if (w2.sucess) {
                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.startedservice)).withBackgroundColorId(R.color.resultgood).show();
            } else {
                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
            }

        }
    }

}