package com.nowenui.systemtweaker.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.github.mrengineer13.snackbar.SnackBar;
import com.nowenui.systemtweaker.R;
import com.nowenui.systemtweaker.Utility;
import com.stericson.RootShell.exceptions.RootDeniedException;
import com.stericson.RootShell.execution.Command;
import com.stericson.RootTools.RootTools;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeoutException;

public class EntropyFragment extends Fragment {
    String string;
    private boolean isClicked;

    public static EntropyFragment newInstance(Bundle bundle) {
        EntropyFragment Entropy = new EntropyFragment();

        if (bundle != null) {
            Entropy.setArguments(bundle);
        }

        return Entropy;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_user:
                if (Utility.getTheme(getActivity().getApplicationContext()) == 1) {
                    new android.app.AlertDialog.Builder(this.getContext())
                            .setTitle(R.string.reboot)
                            .setMessage(R.string.rebootactionbar)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    try {
                                        Process proc = Runtime.getRuntime().exec(new String[]{"su", "-c", "reboot"});
                                        proc.waitFor();
                                    } catch (Exception ex) {
                                        new SnackBar.Builder(getActivity()).withMessage("ROOT NEEDED!").withBackgroundColorId(R.color.textview1bad).show();
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
                if (Utility.getTheme(getActivity().getApplicationContext()) == 2) {
                    new android.app.AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark))
                            .setTitle(R.string.reboot)
                            .setMessage(R.string.rebootactionbar)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    try {
                                        Process proc = Runtime.getRuntime().exec(new String[]{"su", "-c", "reboot"});
                                        proc.waitFor();
                                    } catch (Exception ex) {
                                        new SnackBar.Builder(getActivity()).withMessage("ROOT NEEDED!").withBackgroundColorId(R.color.textview1bad).show();
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
                if (Utility.getTheme(getActivity().getApplicationContext()) == 3) {
                    new android.app.AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack))
                            .setTitle(R.string.reboot)
                            .setMessage(R.string.rebootactionbar)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    try {
                                        Process proc = Runtime.getRuntime().exec(new String[]{"su", "-c", "reboot"});
                                        proc.waitFor();
                                    } catch (Exception ex) {
                                        new SnackBar.Builder(getActivity()).withMessage("ROOT NEEDED!").withBackgroundColorId(R.color.textview1bad).show();
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
        return super.onOptionsItemSelected(item);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_entropy, parent, false);
    }


    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        setRetainInstance(true);

        if (RootTools.isAccessGiven()) {

            Command command1 = new Command(0,
                    "chmod 777 /data/rngd.pid");
            try {
                RootTools.getShell(true).add(command1);
            } catch (IOException | RootDeniedException | TimeoutException ex) {
                ex.printStackTrace();
            }
        }

        File f5 = new File("/system/bin/rngd");
        if (f5.exists()) {
            new third(getActivity(), view).execute();
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
                                    entropy_pull.setText(pull());
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
        File f = new File("/system/bin/rngd");
        if (f.exists()) {
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

                    final CheckBox checkbox26 = view.findViewById(R.id.checkBox26);
                    checkbox26.setEnabled(true);
                    String check19 = "/etc/init.d/entropy_start";
                    String check19a = "/system/etc/init.d/entropy_start";
                    if (new File(Environment.getRootDirectory() + check19).exists() || new File(check19a).exists() || new File(Environment.getRootDirectory() + check19a).exists()) {
                        checkbox26.setChecked(true);
                    } else {
                        checkbox26.setChecked(false);
                    }
                    checkbox26.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


                                                              @Override
                                                              public void onCheckedChanged(CompoundButton buttonView,
                                                                                           boolean isChecked) {
                                                                  if (isChecked) {


                                                                      if (RootTools.isAccessGiven()) {
                                                                          Command command1 = new Command(0,
                                                                                  "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                                  "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                                  "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                                  "cp /data/data/com.nowenui.systemtweaker/files/entropy_start /system/etc/init.d/",
                                                                                  "chmod 777 /system/etc/init.d/entropy_start",
                                                                                  "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                                  "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                                  "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system");
                                                                          try {
                                                                              RootTools.getShell(true).add(command1);
                                                                              new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.enable)).withBackgroundColorId(R.color.textview1good).show();
                                                                          } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                                              ex.printStackTrace();
                                                                              new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                                                                          }
                                                                      } else {
                                                                          new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.textview1bad).show();
                                                                      }

                                                                  } else {

                                                                      if (RootTools.isAccessGiven()) {
                                                                          Command command1 = new Command(0,
                                                                                  "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                                  "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                                  "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                                  "rm -f /system/etc/init.d/entropy_start",
                                                                                  "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                                  "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                                  "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                                                          );
                                                                          try {
                                                                              RootTools.getShell(true).add(command1);
                                                                              new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.disable)).withBackgroundColorId(R.color.textview1good).show();
                                                                          } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                                              ex.printStackTrace();
                                                                              new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                                                                          }
                                                                      } else {
                                                                          new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.textview1bad).show();
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

                    new third(getActivity(), view).execute();

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

                            checkbox26.setEnabled(false);
                            String check19 = "/etc/init.d/entropy_start";
                            String check19a = "/system/etc/init.d/entropy_start";
                            if (new File(Environment.getRootDirectory() + check19).exists() || new File(check19a).exists() || new File(Environment.getRootDirectory() + check19a).exists()) {
                                checkbox26.setChecked(true);
                            } else {
                                checkbox26.setChecked(false);
                            }

                            delete.setEnabled(false);
                            entropy.setEnabled(true);
                            entropy.setBackgroundResource(R.drawable.roundbuttoncal);
                            final Resources res = getResources();
                            entropy.setText(res.getString(R.string.installfstrim));

                            startentropy.setBackgroundResource(R.drawable.roundbuttonfuck);
                            startentropy.setEnabled(false);
                            startentropy.setTextColor(Color.WHITE);
                            stopentropy.setBackgroundResource(R.drawable.roundbuttonfuck);
                            stopentropy.setEnabled(false);
                            stopentropy.setTextColor(Color.WHITE);
                            delete.setBackgroundResource(R.drawable.roundbuttonfuck);


                            if (RootTools.isAccessGiven()) {
                                Command command1 = new Command(0,
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
                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.entropydele)).withBackgroundColorId(R.color.textview1good).show();
                                    RootTools.getShell(true).add(command1);
                                    if (Utility.getTheme(getActivity().getApplicationContext()) == 1) {
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
                                                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.reboot)).withBackgroundColorId(R.color.textview1good).show();
                                                            } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                                ex.printStackTrace();
                                                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                                                            }
                                                        } else {
                                                            new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.erroroot)).withBackgroundColorId(R.color.textview1bad).show();
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
                                    if (Utility.getTheme(getActivity().getApplicationContext()) == 2) {
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
                                                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.reboot)).withBackgroundColorId(R.color.textview1good).show();
                                                            } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                                ex.printStackTrace();
                                                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                                                            }
                                                        } else {
                                                            new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.erroroot)).withBackgroundColorId(R.color.textview1bad).show();
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
                                    if (Utility.getTheme(getActivity().getApplicationContext()) == 3) {
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
                                                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.reboot)).withBackgroundColorId(R.color.textview1good).show();
                                                            } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                                ex.printStackTrace();
                                                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                                                            }
                                                        } else {
                                                            new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.erroroot)).withBackgroundColorId(R.color.textview1bad).show();
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
                                    ex.printStackTrace();
                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                                }
                            } else {
                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.textview1bad).show();
                            }
                        }
                    });


                    if (RootTools.isAccessGiven()) {
                        Command command1 = new Command(0,
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
                            RootTools.getShell(true).add(command1);
                            new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.fileinstall)).withBackgroundColorId(R.color.textview1good).show();
                        } catch (IOException | RootDeniedException | TimeoutException ex) {
                            ex.printStackTrace();
                            new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                        }
                    } else {
                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.textview1bad).show();
                    }

                }
            });
        }

        ///////////////////////////////////////
        ////// Entropy start script ///////////
        ///////////////////////////////////////
        final CheckBox checkbox26 = view.findViewById(R.id.checkBox26);
        if (!(f.exists())) {
            checkbox26.setEnabled(false);
        }
        String check19 = "/etc/init.d/entropy_start";
        String check19a = "/system/etc/init.d/entropy_start";
        if (new File(Environment.getRootDirectory() + check19).exists() || new File(check19a).exists() || new File(Environment.getRootDirectory() + check19a).exists()) {
            checkbox26.setChecked(true);
        } else {
            checkbox26.setChecked(false);
        }
        checkbox26.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


                                                  @Override
                                                  public void onCheckedChanged(CompoundButton buttonView,
                                                                               boolean isChecked) {
                                                      if (isChecked) {


                                                          if (RootTools.isAccessGiven()) {
                                                              Command command1 = new Command(0,
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                      "cp /data/data/com.nowenui.systemtweaker/files/entropy_start /system/etc/init.d/",
                                                                      "chmod 777 /system/etc/init.d/entropy_start",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system");
                                                              try {
                                                                  RootTools.getShell(true).add(command1);
                                                                  new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.enable)).withBackgroundColorId(R.color.textview1good).show();
                                                              } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                                  ex.printStackTrace();
                                                                  new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                                                              }
                                                          } else {
                                                              new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.textview1bad).show();
                                                          }

                                                      } else {

                                                          if (RootTools.isAccessGiven()) {
                                                              Command command1 = new Command(0,
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                      "rm -f /system/etc/init.d/entropy_start",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                                              );
                                                              try {
                                                                  RootTools.getShell(true).add(command1);
                                                                  new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.disable)).withBackgroundColorId(R.color.textview1good).show();
                                                              } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                                  ex.printStackTrace();
                                                                  new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                                                              }
                                                          } else {
                                                              new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.textview1bad).show();
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
        if (f.exists()) {
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

                    checkbox26.setEnabled(false);
                    String check19 = "/etc/init.d/entropy_start";
                    String check19a = "/system/etc/init.d/entropy_start";
                    if (new File(Environment.getRootDirectory() + check19).exists() || new File(check19a).exists() || new File(Environment.getRootDirectory() + check19a).exists()) {
                        checkbox26.setChecked(true);
                    } else {
                        checkbox26.setChecked(false);
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

                        Command command1 = new Command(0,
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
                            new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.entropydele)).withBackgroundColorId(R.color.textview1good).show();
                            RootTools.getShell(true).add(command1);
                            if (Utility.getTheme(getActivity().getApplicationContext()) == 1) {
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
                                                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.reboot)).withBackgroundColorId(R.color.textview1good).show();
                                                    } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                        ex.printStackTrace();
                                                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                                                    }
                                                } else {
                                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.erroroot)).withBackgroundColorId(R.color.textview1bad).show();
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
                            if (Utility.getTheme(getActivity().getApplicationContext()) == 2) {
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
                                                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.reboot)).withBackgroundColorId(R.color.textview1good).show();
                                                    } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                        ex.printStackTrace();
                                                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                                                    }
                                                } else {
                                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.erroroot)).withBackgroundColorId(R.color.textview1bad).show();
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
                            if (Utility.getTheme(getActivity().getApplicationContext()) == 3) {
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
                                                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.reboot)).withBackgroundColorId(R.color.textview1good).show();
                                                    } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                        ex.printStackTrace();
                                                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                                                    }
                                                } else {
                                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.erroroot)).withBackgroundColorId(R.color.textview1bad).show();
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
                            ex.printStackTrace();
                            new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                        }
                    } else {
                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.textview1bad).show();
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

    ///////////////////////////////////////////
    ////// Check if entropy running ///////////
    //////////////////////////////////////////
    public String runAsRoot() {

        try {
            Process p = Runtime.getRuntime().exec(new String[]{"su", "-c", "pgrep rngd"});
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));
            int read;
            char[] buffer = new char[4096];
            StringBuffer output = new StringBuffer();
            while ((read = reader.read(buffer)) > 0) {
                output.append(buffer, 0, read);
            }
            reader.close();

            p.waitFor();

            return output.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /////////////////////////////////////
    ////// Check entropy pull ///////////
    /////////////////////////////////////
    public String pull() {

        try {
            Process process = Runtime.getRuntime().exec("cat /proc/sys/kernel/random/entropy_avail");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
            int read;
            char[] buffer = new char[4096];
            StringBuffer output = new StringBuffer();
            while ((read = reader.read(buffer)) > 0) {
                output.append(buffer, 0, read);
            }
            reader.close();

            process.waitFor();

            return output.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public class Wrapper {
        public String oneperem;
        public boolean check;
        public boolean sucess;
    }

    public class third extends AsyncTask<String, Void, Wrapper> {

        public Button startentropy, stopentropy;
        public TextView entropystatus;
        private Context mContext;
        private View rootView;


        public third(Context context, View rootView) {
            this.mContext = context;
            this.rootView = rootView;
        }

        @Override
        public Wrapper doInBackground(String... args) {
            final Wrapper w = new Wrapper();

            final CountDownLatch latch = new CountDownLatch(1);

            w.oneperem = runAsRoot();

            File file = new File("/data/rngd.pid");

            StringBuilder text = new StringBuilder();

            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;

                while ((line = br.readLine()) != null) {
                    text.append(line);
                }
                br.close();
            } catch (IOException e) {
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

                                new start(getActivity(), getView()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                            }
                        });
                        new stop(getActivity(), getView()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
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

                                        new start(getActivity(), getView()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                                    }

                                });

                                new stop(getActivity(), getView()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                            }

                        });

                        new start(getActivity(), getView()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                    }

                });


            }
        }
    }

    public class stop extends AsyncTask<String, Void, Wrapper> {

        private Context mContext;
        private View rootView;

        public stop(Context context, View rootView) {
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
                outputStream.writeBytes("exit\n");
                outputStream.flush();
                latch3.countDown();
            } catch (IOException e) {
                w1.sucess = false;
            }

            return w1;
        }

        @Override
        public void onPostExecute(Wrapper w1) {

            if (w1.sucess) {
                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.stopedservice)).withBackgroundColorId(R.color.textview1good).show();
            } else {
                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
            }

        }
    }

    public class start extends AsyncTask<String, Void, Wrapper> {

        private Context mContext;
        private View rootView;

        public start(Context context, View rootView) {
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
                outputStream.writeBytes("exit\n");
                outputStream.flush();
                latch2.countDown();

            } catch (IOException e) {
                w2.sucess = false;
            }
            return w2;
        }

        @Override
        public void onPostExecute(Wrapper w2) {

            if (w2.sucess) {
                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.startedservice)).withBackgroundColorId(R.color.textview1good).show();
            } else {
                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
            }

        }
    }

}