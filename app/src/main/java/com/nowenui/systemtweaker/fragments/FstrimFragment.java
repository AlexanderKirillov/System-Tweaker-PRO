package com.nowenui.systemtweaker.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
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

public class FstrimFragment extends Fragment {

    public String one, two, three, four, sysonly, dataonly, cacheonly;
    private boolean isClicked;

    public static FstrimFragment newInstance(Bundle bundle) {
        FstrimFragment Fstrim = new FstrimFragment();

        if (bundle != null) {
            Fstrim.setArguments(bundle);
        }

        return Fstrim;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fstrim, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        Button fstrimbutton = view.findViewById(R.id.fstrimbutton);
        final CheckBox system = view.findViewById(R.id.system);
        final CheckBox data = view.findViewById(R.id.data);
        final CheckBox cache = view.findViewById(R.id.cache);

        one = null;
        two = null;
        three = null;
        four = null;
        sysonly = null;
        dataonly = null;
        cacheonly = null;

        ////////////////////////////////
        ////// FSTRIM option ///////////
        ////////////////////////////////
        fstrimbutton.setBackgroundResource(R.drawable.roundbuttoncal);
        fstrimbutton.setTextColor(Color.WHITE);
        fstrimbutton.setTextSize(20);
        fstrimbutton.setEnabled(true);
        fstrimbutton.setOnClickListener(new View.OnClickListener() {
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
                ////// System FSTRIM option ///////////
                ///////////////////////////////////////
                if (system.isChecked() & !data.isChecked() & !cache.isChecked()) {
                    if (RootTools.isAccessGiven()) {
                        @SuppressLint("SdCardPath") Command mountcommand = new Command(0,
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system");
                        @SuppressLint("SdCardPath") Command fstrimcommand = new Command(0,
                                "/data/data/com.nowenui.systemtweaker/files/busybox fstrim -v /system") {
                            @Override
                            public void commandOutput(int id, final String line) {
                                super.commandOutput(id, line);
                                sysonly += line + "\n";
                            }
                        };
                        try {
                            RootTools.getShell(true).add(mountcommand);
                            RootTools.getShell(true).add(fstrimcommand);
                            if (ThemeUtility.getTheme(getActivity().getApplicationContext()) == 1) {
                                final ProgressDialog dialog = new ProgressDialog(getActivity(), R.style.AppCompatAlertDialogStyle);
                                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                dialog.setTitle("FSTRIM");
                                dialog.setCancelable(false);
                                dialog.setMessage(getContext().getResources().getString(R.string.speedmessage));
                                dialog.setIndeterminate(false);
                                dialog.show();

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    public void run() {
                                        dialog.dismiss();
                                        new SnackBar.Builder(getActivity()).withMessage("FSTRIM /system... OK!").withBackgroundColorId(R.color.resultgood).show();
                                        if (sysonly != null) {
                                            final Handler handler = new Handler();
                                            handler.postDelayed(new Runnable() {
                                                public void run() {
                                                    StringBuilder sb = new StringBuilder(sysonly.trim());
                                                    sb.delete(0, 4);
                                                    final AlertDialog.Builder db = new AlertDialog.Builder(
                                                            getContext());
                                                    db.setMessage(sb);
                                                    db.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface d, int arg1) {
                                                            sysonly = null;
                                                            d.cancel();
                                                        }

                                                    });
                                                    db.show();
                                                }
                                            }, 0);
                                        }

                                    }
                                }, 4000);
                            }
                            if (ThemeUtility.getTheme(getActivity().getApplicationContext()) == 2) {
                                final ProgressDialog dialog = new ProgressDialog(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark));
                                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                dialog.setTitle("FSTRIM");
                                dialog.setCancelable(false);
                                dialog.setMessage(getContext().getResources().getString(R.string.speedmessage));
                                dialog.setIndeterminate(false);
                                dialog.show();

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    public void run() {
                                        dialog.dismiss();
                                        new SnackBar.Builder(getActivity()).withMessage("FSTRIM /system... OK!").withBackgroundColorId(R.color.resultgood).show();
                                        if (sysonly != null) {
                                            final Handler handler = new Handler();
                                            handler.postDelayed(new Runnable() {
                                                public void run() {
                                                    StringBuilder sb = new StringBuilder(sysonly.trim());
                                                    sb.delete(0, 4);
                                                    final AlertDialog.Builder db = new AlertDialog.Builder(
                                                            new ContextThemeWrapper(getContext(), R.style.AlertDialogDark));
                                                    db.setMessage(sb);
                                                    db.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface d, int arg1) {
                                                            sysonly = null;
                                                            d.cancel();
                                                        }

                                                    });
                                                    db.show();
                                                }
                                            }, 0);
                                        }
                                    }
                                }, 4000);
                            }
                            if (ThemeUtility.getTheme(getActivity().getApplicationContext()) == 3) {
                                final ProgressDialog dialog = new ProgressDialog(new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack));
                                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                dialog.setTitle("FSTRIM");
                                dialog.setCancelable(false);
                                dialog.setMessage(getContext().getResources().getString(R.string.speedmessage));
                                dialog.setIndeterminate(false);
                                dialog.show();

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    public void run() {
                                        dialog.dismiss();
                                        new SnackBar.Builder(getActivity()).withMessage("FSTRIM /system... OK!").withBackgroundColorId(R.color.resultgood).show();
                                        if (sysonly != null) {
                                            final Handler handler = new Handler();
                                            handler.postDelayed(new Runnable() {
                                                public void run() {
                                                    StringBuilder sb = new StringBuilder(sysonly.trim());
                                                    sb.delete(0, 4);
                                                    final AlertDialog.Builder db = new AlertDialog.Builder(
                                                            new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack));
                                                    db.setMessage(sb);
                                                    db.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface d, int arg1) {
                                                            sysonly = null;
                                                            d.cancel();
                                                        }

                                                    });
                                                    db.show();
                                                }
                                            }, 0);
                                        }
                                    }
                                }, 4000);
                            }

                        } catch (IOException | RootDeniedException | TimeoutException ex) {
                            new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                        }
                    } else {
                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                    }

                    /////////////////////////////////////
                    ////// Data FSTRIM option ///////////
                    /////////////////////////////////////
                } else if (data.isChecked() & !system.isChecked() & !cache.isChecked()) {

                    if (RootTools.isAccessGiven()) {
                        @SuppressLint("SdCardPath") Command mountcommand = new Command(0,
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount data",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /data", "mount -o rw,remount /data",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /data",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount data",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /data", "mount -o ro,remount /data",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /data");
                        @SuppressLint("SdCardPath") Command fstrimcommand = new Command(0,
                                "/data/data/com.nowenui.systemtweaker/files/busybox fstrim -v /data") {
                            @Override
                            public void commandOutput(int id, final String line) {
                                super.commandOutput(id, line);
                                dataonly += line + "\n";
                            }
                        };
                        try {
                            RootTools.getShell(true).add(mountcommand);
                            RootTools.getShell(true).add(fstrimcommand);
                            if (ThemeUtility.getTheme(getActivity().getApplicationContext()) == 1) {
                                final ProgressDialog dialog = new ProgressDialog(getActivity(), R.style.AppCompatAlertDialogStyle);
                                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                dialog.setTitle("FSTRIM");
                                dialog.setCancelable(false);
                                dialog.setMessage(getContext().getResources().getString(R.string.speedmessage));
                                dialog.setIndeterminate(false);
                                dialog.show();

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    public void run() {
                                        dialog.dismiss();
                                        new SnackBar.Builder(getActivity()).withMessage("FSTRIM /data... OK!").withBackgroundColorId(R.color.resultgood).show();
                                        if (dataonly != null) {
                                            final Handler handler = new Handler();
                                            handler.postDelayed(new Runnable() {
                                                public void run() {
                                                    StringBuilder sb = new StringBuilder(dataonly.trim());
                                                    sb.delete(0, 4);
                                                    final AlertDialog.Builder db = new AlertDialog.Builder(
                                                            getContext());
                                                    db.setMessage(sb);
                                                    db.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface d, int arg1) {
                                                            dataonly = null;
                                                            d.cancel();
                                                        }

                                                    });
                                                    db.show();
                                                }
                                            }, 0);
                                        }
                                    }
                                }, 4000);
                            }
                            if (ThemeUtility.getTheme(getActivity().getApplicationContext()) == 2) {
                                final ProgressDialog dialog = new ProgressDialog(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark));
                                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                dialog.setTitle("FSTRIM");
                                dialog.setCancelable(false);
                                dialog.setMessage(getContext().getResources().getString(R.string.speedmessage));
                                dialog.setIndeterminate(false);
                                dialog.show();

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    public void run() {
                                        dialog.dismiss();
                                        new SnackBar.Builder(getActivity()).withMessage("FSTRIM /data... OK!").withBackgroundColorId(R.color.resultgood).show();
                                        if (dataonly != null) {
                                            final Handler handler = new Handler();
                                            handler.postDelayed(new Runnable() {
                                                public void run() {
                                                    StringBuilder sb = new StringBuilder(dataonly.trim());
                                                    sb.delete(0, 4);
                                                    final AlertDialog.Builder db = new AlertDialog.Builder(
                                                            new ContextThemeWrapper(getContext(), R.style.AlertDialogDark));
                                                    db.setMessage(sb);
                                                    db.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface d, int arg1) {
                                                            dataonly = null;
                                                            d.cancel();
                                                        }

                                                    });
                                                    db.show();
                                                }
                                            }, 0);
                                        }
                                    }
                                }, 4000);
                            }
                            if (ThemeUtility.getTheme(getActivity().getApplicationContext()) == 3) {
                                final ProgressDialog dialog = new ProgressDialog(new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack));
                                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                dialog.setTitle("FSTRIM");
                                dialog.setCancelable(false);
                                dialog.setMessage(getContext().getResources().getString(R.string.speedmessage));
                                dialog.setIndeterminate(false);
                                dialog.show();

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    public void run() {
                                        dialog.dismiss();
                                        new SnackBar.Builder(getActivity()).withMessage("FSTRIM /data... OK!").withBackgroundColorId(R.color.resultgood).show();
                                        if (sysonly != null) {
                                            final Handler handler = new Handler();
                                            handler.postDelayed(new Runnable() {
                                                public void run() {
                                                    StringBuilder sb = new StringBuilder(dataonly.trim());
                                                    sb.delete(0, 4);
                                                    final AlertDialog.Builder db = new AlertDialog.Builder(
                                                            new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack));
                                                    db.setMessage(sb);
                                                    db.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface d, int arg1) {
                                                            dataonly = null;
                                                            d.cancel();
                                                        }

                                                    });
                                                    db.show();
                                                }
                                            }, 0);
                                        }
                                    }
                                }, 4000);
                            }
                        } catch (IOException | RootDeniedException | TimeoutException ex) {
                            new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                        }
                    } else {
                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                    }

                    //////////////////////////////////////
                    ////// Cache FSTRIM option ///////////
                    //////////////////////////////////////
                } else if (cache.isChecked() & !data.isChecked() & !system.isChecked()) {
                    if (RootTools.isAccessGiven()) {
                        @SuppressLint("SdCardPath") Command mountcommand = new Command(0,
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount cache",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /cache",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /cache",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount cache",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /cache",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /cache");
                        @SuppressLint("SdCardPath") Command fstrimcommand = new Command(0,
                                "/data/data/com.nowenui.systemtweaker/files/busybox fstrim -v /cache") {
                            @Override
                            public void commandOutput(int id, final String line) {
                                super.commandOutput(id, line);
                                cacheonly += line + "\n";
                            }
                        };
                        try {
                            RootTools.getShell(true).add(mountcommand);
                            RootTools.getShell(true).add(fstrimcommand);
                            if (ThemeUtility.getTheme(getActivity().getApplicationContext()) == 1) {
                                final ProgressDialog dialog = new ProgressDialog(getActivity(), R.style.AppCompatAlertDialogStyle);
                                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                dialog.setTitle("FSTRIM");
                                dialog.setCancelable(false);
                                dialog.setMessage(getContext().getResources().getString(R.string.speedmessage));
                                dialog.setIndeterminate(false);
                                dialog.show();

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    public void run() {
                                        dialog.dismiss();
                                        new SnackBar.Builder(getActivity()).withMessage("FSTRIM /cache... OK!").withBackgroundColorId(R.color.resultgood).show();
                                        if (cacheonly != null) {
                                            final Handler handler = new Handler();
                                            handler.postDelayed(new Runnable() {
                                                public void run() {
                                                    StringBuilder sb = new StringBuilder(cacheonly.trim());
                                                    sb.delete(0, 4);
                                                    final AlertDialog.Builder db = new AlertDialog.Builder(
                                                            getContext());
                                                    db.setMessage(sb);
                                                    db.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface d, int arg1) {
                                                            cacheonly = null;
                                                            d.cancel();
                                                        }

                                                    });
                                                    db.show();
                                                }
                                            }, 0);
                                        }
                                    }
                                }, 4000);
                            }
                            if (ThemeUtility.getTheme(getActivity().getApplicationContext()) == 2) {
                                final ProgressDialog dialog = new ProgressDialog(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark));
                                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                dialog.setTitle("FSTRIM");
                                dialog.setCancelable(false);
                                dialog.setMessage(getContext().getResources().getString(R.string.speedmessage));
                                dialog.setIndeterminate(false);
                                dialog.show();

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    public void run() {
                                        dialog.dismiss();
                                        new SnackBar.Builder(getActivity()).withMessage("FSTRIM /cache... OK!").withBackgroundColorId(R.color.resultgood).show();
                                        if (cacheonly != null) {
                                            final Handler handler = new Handler();
                                            handler.postDelayed(new Runnable() {
                                                public void run() {
                                                    StringBuilder sb = new StringBuilder(cacheonly.trim());
                                                    sb.delete(0, 4);
                                                    final AlertDialog.Builder db = new AlertDialog.Builder(
                                                            new ContextThemeWrapper(getContext(), R.style.AlertDialogDark));
                                                    db.setMessage(sb);
                                                    db.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface d, int arg1) {
                                                            cacheonly = null;
                                                            d.cancel();
                                                        }

                                                    });
                                                    db.show();
                                                }
                                            }, 0);
                                        }
                                    }
                                }, 4000);
                            }
                            if (ThemeUtility.getTheme(getActivity().getApplicationContext()) == 3) {
                                final ProgressDialog dialog = new ProgressDialog(new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack));
                                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                dialog.setTitle("FSTRIM");
                                dialog.setCancelable(false);
                                dialog.setMessage(getContext().getResources().getString(R.string.speedmessage));
                                dialog.setIndeterminate(false);
                                dialog.show();

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    public void run() {
                                        dialog.dismiss();
                                        new SnackBar.Builder(getActivity()).withMessage("FSTRIM /cache... OK!").withBackgroundColorId(R.color.resultgood).show();
                                        if (cacheonly != null) {
                                            final Handler handler = new Handler();
                                            handler.postDelayed(new Runnable() {
                                                public void run() {
                                                    StringBuilder sb = new StringBuilder(cacheonly.trim());
                                                    sb.delete(0, 4);
                                                    final AlertDialog.Builder db = new AlertDialog.Builder(
                                                            new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack));
                                                    db.setMessage(sb);
                                                    db.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface d, int arg1) {
                                                            cacheonly = null;
                                                            d.cancel();
                                                        }

                                                    });
                                                    db.show();
                                                }
                                            }, 0);
                                        }
                                    }
                                }, 4000);
                            }
                        } catch (IOException | RootDeniedException | TimeoutException ex) {
                            new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                        }
                    } else {
                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                    }

                    ///////////////////////////////////////////////
                    ////// System & Data  FSTRIM option ///////////
                    ///////////////////////////////////////////////
                } else if (system.isChecked() & data.isChecked() & !cache.isChecked()) {
                    if (RootTools.isAccessGiven()) {
                        @SuppressLint("SdCardPath") Command mountcommand = new Command(0,
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount data",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /data", "mount -o rw,remount /data",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /data",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /data", "mount -o ro,remount /data",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /data",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount data");
                        @SuppressLint("SdCardPath") Command fstrimcommand = new Command(0,
                                "/data/data/com.nowenui.systemtweaker/files/busybox fstrim -v /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox fstrim -v /data") {
                            @Override
                            public void commandOutput(int id, final String line) {
                                super.commandOutput(id, line);
                                one += line + "\n";
                            }
                        };
                        try {
                            RootTools.getShell(true).add(mountcommand);
                            RootTools.getShell(true).add(fstrimcommand);
                            if (ThemeUtility.getTheme(getActivity().getApplicationContext()) == 1) {
                                final ProgressDialog dialog = new ProgressDialog(getActivity(), R.style.AppCompatAlertDialogStyle);
                                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                dialog.setTitle("FSTRIM");
                                dialog.setCancelable(false);
                                dialog.setMessage(getContext().getResources().getString(R.string.speedmessage));
                                dialog.setIndeterminate(false);
                                dialog.show();

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    public void run() {
                                        dialog.dismiss();
                                        new SnackBar.Builder(getActivity()).withMessage("FSTRIM /data & /system... OK!").withBackgroundColorId(R.color.resultgood).show();
                                        if (one != null) {
                                            final Handler handler = new Handler();
                                            handler.postDelayed(new Runnable() {
                                                public void run() {
                                                    StringBuilder sb = new StringBuilder(one.trim());
                                                    sb.delete(0, 4);
                                                    final AlertDialog.Builder db = new AlertDialog.Builder(
                                                            getContext());
                                                    db.setMessage(sb);
                                                    db.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface d, int arg1) {
                                                            one = null;
                                                            d.cancel();
                                                        }

                                                    });
                                                    db.show();
                                                }
                                            }, 0);
                                        }
                                    }
                                }, 4000);
                            }
                            if (ThemeUtility.getTheme(getActivity().getApplicationContext()) == 2) {
                                final ProgressDialog dialog = new ProgressDialog(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark));
                                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                dialog.setTitle("FSTRIM");
                                dialog.setCancelable(false);
                                dialog.setMessage(getContext().getResources().getString(R.string.speedmessage));
                                dialog.setIndeterminate(false);
                                dialog.show();

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    public void run() {
                                        dialog.dismiss();
                                        new SnackBar.Builder(getActivity()).withMessage("FSTRIM /data & /system... OK!").withBackgroundColorId(R.color.resultgood).show();
                                        if (one != null) {
                                            final Handler handler = new Handler();
                                            handler.postDelayed(new Runnable() {
                                                public void run() {
                                                    StringBuilder sb = new StringBuilder(one.trim());
                                                    sb.delete(0, 4);
                                                    final AlertDialog.Builder db = new AlertDialog.Builder(
                                                            new ContextThemeWrapper(getContext(), R.style.AlertDialogDark));
                                                    db.setMessage(sb);
                                                    db.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface d, int arg1) {
                                                            one = null;
                                                            d.cancel();
                                                        }

                                                    });
                                                    db.show();
                                                }
                                            }, 0);
                                        }
                                    }
                                }, 4000);
                            }
                            if (ThemeUtility.getTheme(getActivity().getApplicationContext()) == 3) {
                                final ProgressDialog dialog = new ProgressDialog(new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack));
                                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                dialog.setTitle("FSTRIM");
                                dialog.setCancelable(false);
                                dialog.setMessage(getContext().getResources().getString(R.string.speedmessage));
                                dialog.setIndeterminate(false);
                                dialog.show();

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    public void run() {
                                        dialog.dismiss();
                                        new SnackBar.Builder(getActivity()).withMessage("FSTRIM /data & /system... OK!").withBackgroundColorId(R.color.resultgood).show();
                                        if (one != null) {
                                            final Handler handler = new Handler();
                                            handler.postDelayed(new Runnable() {
                                                public void run() {
                                                    StringBuilder sb = new StringBuilder(one.trim());
                                                    sb.delete(0, 4);
                                                    final AlertDialog.Builder db = new AlertDialog.Builder(
                                                            new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack));
                                                    db.setMessage(sb);
                                                    db.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface d, int arg1) {
                                                            one = null;
                                                            d.cancel();
                                                        }

                                                    });
                                                    db.show();
                                                }
                                            }, 0);
                                        }
                                    }
                                }, 4000);
                            }
                        } catch (IOException | RootDeniedException | TimeoutException ex) {
                            new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                        }
                    } else {
                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                    }

                    ///////////////////////////////////////////////
                    ////// System & Cache FSTRIM option ///////////
                    ///////////////////////////////////////////////
                } else if (system.isChecked() & cache.isChecked() & !data.isChecked()) {
                    if (RootTools.isAccessGiven()) {
                        @SuppressLint("SdCardPath") Command mountcommand = new Command(0,
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount cache",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /cache",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /cache",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount cache",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /cache",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /cache");
                        Command fstrimcommand = new Command(0,
                                "/data/data/com.nowenui.systemtweaker/files/busybox fstrim -v /system ",
                                "/data/data/com.nowenui.systemtweaker/files/busybox fstrim -v /cache") {
                            @Override
                            public void commandOutput(int id, String line) {
                                super.commandOutput(id, line);
                                two += line + "\n";
                            }
                        };
                        try {
                            RootTools.getShell(true).add(mountcommand);
                            RootTools.getShell(true).add(fstrimcommand);
                            if (ThemeUtility.getTheme(getActivity().getApplicationContext()) == 1) {
                                final ProgressDialog dialog = new ProgressDialog(getActivity(), R.style.AppCompatAlertDialogStyle);
                                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                dialog.setTitle("FSTRIM");
                                dialog.setCancelable(false);
                                dialog.setMessage(getContext().getResources().getString(R.string.speedmessage));
                                dialog.setIndeterminate(false);
                                dialog.show();

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    public void run() {
                                        dialog.dismiss();
                                        new SnackBar.Builder(getActivity()).withMessage("FSTRIM /system & /cache... OK!").withBackgroundColorId(R.color.resultgood).show();
                                        if (two != null) {
                                            final Handler handler = new Handler();
                                            handler.postDelayed(new Runnable() {
                                                public void run() {
                                                    StringBuilder sb = new StringBuilder(two.trim());
                                                    sb.delete(0, 4);
                                                    final AlertDialog.Builder db = new AlertDialog.Builder(
                                                            getContext());
                                                    db.setMessage(sb);
                                                    db.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface d, int arg1) {
                                                            two = null;
                                                            d.cancel();
                                                        }

                                                    });
                                                    db.show();
                                                }
                                            }, 0);
                                        }
                                    }
                                }, 4000);
                            }
                            if (ThemeUtility.getTheme(getActivity().getApplicationContext()) == 2) {
                                final ProgressDialog dialog = new ProgressDialog(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark));
                                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                dialog.setTitle("FSTRIM");
                                dialog.setCancelable(false);
                                dialog.setMessage(getContext().getResources().getString(R.string.speedmessage));
                                dialog.setIndeterminate(false);
                                dialog.show();

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    public void run() {
                                        dialog.dismiss();
                                        new SnackBar.Builder(getActivity()).withMessage("FSTRIM /system & /cache... OK!").withBackgroundColorId(R.color.resultgood).show();
                                        if (two != null) {
                                            final Handler handler = new Handler();
                                            handler.postDelayed(new Runnable() {
                                                public void run() {
                                                    StringBuilder sb = new StringBuilder(two.trim());
                                                    sb.delete(0, 4);
                                                    final AlertDialog.Builder db = new AlertDialog.Builder(
                                                            new ContextThemeWrapper(getContext(), R.style.AlertDialogDark));
                                                    db.setMessage(sb);
                                                    db.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface d, int arg1) {
                                                            two = null;
                                                            d.cancel();
                                                        }

                                                    });
                                                    db.show();
                                                }
                                            }, 0);
                                        }
                                    }
                                }, 4000);
                            }
                            if (ThemeUtility.getTheme(getActivity().getApplicationContext()) == 3) {
                                final ProgressDialog dialog = new ProgressDialog(new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack));
                                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                dialog.setTitle("FSTRIM");
                                dialog.setCancelable(false);
                                dialog.setMessage(getContext().getResources().getString(R.string.speedmessage));
                                dialog.setIndeterminate(false);
                                dialog.show();

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    public void run() {
                                        dialog.dismiss();
                                        new SnackBar.Builder(getActivity()).withMessage("FSTRIM /system & /cache... OK!").withBackgroundColorId(R.color.resultgood).show();
                                        if (two != null) {
                                            final Handler handler = new Handler();
                                            handler.postDelayed(new Runnable() {
                                                public void run() {
                                                    StringBuilder sb = new StringBuilder(two.trim());
                                                    sb.delete(0, 4);
                                                    final AlertDialog.Builder db = new AlertDialog.Builder(
                                                            new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack));
                                                    db.setMessage(sb);
                                                    db.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface d, int arg1) {
                                                            two = null;
                                                            d.cancel();
                                                        }

                                                    });
                                                    db.show();
                                                }
                                            }, 0);
                                        }
                                    }
                                }, 4000);
                            }
                        } catch (IOException | RootDeniedException | TimeoutException ex) {
                            new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                        }
                    } else {
                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                    }

                    /////////////////////////////////////////////
                    ////// Data & Cache FSTRIM option ///////////
                    /////////////////////////////////////////////
                } else if (data.isChecked() & cache.isChecked() & !system.isChecked()) {
                    if (RootTools.isAccessGiven()) {
                        @SuppressLint("SdCardPath") Command mountcommand = new Command(0,
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount data",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount cache",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /data", "mount -o rw,remount /data",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /data",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /cache",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /cache",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount data",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount cache",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /data", "mount -o ro,remount /data",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /data",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /cache",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /cache");
                        @SuppressLint("SdCardPath") Command fstrimcommand = new Command(0,
                                "/data/data/com.nowenui.systemtweaker/files/busybox fstrim -v /data",
                                "/data/data/com.nowenui.systemtweaker/files/busybox fstrim -v /cache") {
                            @Override
                            public void commandOutput(int id, String line) {
                                super.commandOutput(id, line);
                                three += line + "\n";
                            }
                        };
                        try {
                            RootTools.getShell(true).add(mountcommand);
                            RootTools.getShell(true).add(fstrimcommand);
                            if (ThemeUtility.getTheme(getActivity().getApplicationContext()) == 1) {
                                final ProgressDialog dialog = new ProgressDialog(getActivity(), R.style.AppCompatAlertDialogStyle);
                                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                dialog.setTitle("FSTRIM");
                                dialog.setCancelable(false);
                                dialog.setMessage(getContext().getResources().getString(R.string.speedmessage));
                                dialog.setIndeterminate(false);
                                dialog.show();

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    public void run() {
                                        dialog.dismiss();
                                        new SnackBar.Builder(getActivity()).withMessage("FSTRIM /data & /cache... OK!").withBackgroundColorId(R.color.resultgood).show();
                                        if (three != null) {
                                            final Handler handler = new Handler();
                                            handler.postDelayed(new Runnable() {
                                                public void run() {
                                                    StringBuilder sb = new StringBuilder(three.trim());
                                                    sb.delete(0, 4);
                                                    final AlertDialog.Builder db = new AlertDialog.Builder(
                                                            getContext());
                                                    db.setMessage(sb);
                                                    db.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface d, int arg1) {
                                                            three = null;
                                                            d.cancel();
                                                        }

                                                    });
                                                    db.show();
                                                }
                                            }, 0);
                                        }
                                    }
                                }, 4000);
                            }
                            if (ThemeUtility.getTheme(getActivity().getApplicationContext()) == 2) {
                                final ProgressDialog dialog = new ProgressDialog(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark));
                                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                dialog.setTitle("FSTRIM");
                                dialog.setCancelable(false);
                                dialog.setMessage(getContext().getResources().getString(R.string.speedmessage));
                                dialog.setIndeterminate(false);
                                dialog.show();

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    public void run() {
                                        dialog.dismiss();
                                        new SnackBar.Builder(getActivity()).withMessage("FSTRIM /data & /cache... OK!").withBackgroundColorId(R.color.resultgood).show();
                                        if (three != null) {
                                            final Handler handler = new Handler();
                                            handler.postDelayed(new Runnable() {
                                                public void run() {
                                                    StringBuilder sb = new StringBuilder(three.trim());
                                                    sb.delete(0, 4);
                                                    final AlertDialog.Builder db = new AlertDialog.Builder(
                                                            new ContextThemeWrapper(getContext(), R.style.AlertDialogDark));
                                                    db.setMessage(sb);
                                                    db.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface d, int arg1) {
                                                            three = null;
                                                            d.cancel();
                                                        }

                                                    });
                                                    db.show();
                                                }
                                            }, 0);
                                        }
                                    }
                                }, 4000);
                            }
                            if (ThemeUtility.getTheme(getActivity().getApplicationContext()) == 3) {
                                final ProgressDialog dialog = new ProgressDialog(new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack));
                                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                dialog.setTitle("FSTRIM");
                                dialog.setCancelable(false);
                                dialog.setMessage(getContext().getResources().getString(R.string.speedmessage));
                                dialog.setIndeterminate(false);
                                dialog.show();

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    public void run() {
                                        dialog.dismiss();
                                        new SnackBar.Builder(getActivity()).withMessage("FSTRIM /data & /cache... OK!").withBackgroundColorId(R.color.resultgood).show();
                                        if (three != null) {
                                            final Handler handler = new Handler();
                                            handler.postDelayed(new Runnable() {
                                                public void run() {
                                                    StringBuilder sb = new StringBuilder(three.trim());
                                                    sb.delete(0, 4);
                                                    final AlertDialog.Builder db = new AlertDialog.Builder(
                                                            new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack));
                                                    db.setMessage(sb);
                                                    db.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface d, int arg1) {
                                                            three = null;
                                                            d.cancel();
                                                        }

                                                    });
                                                    db.show();
                                                }
                                            }, 0);
                                        }
                                    }
                                }, 4000);
                            }
                        } catch (IOException | RootDeniedException | TimeoutException ex) {
                            new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                        }
                    } else {
                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                    }

                    //////////////////////////////////////////////////////
                    ////// System & Data & Cache FSTRIM option ///////////
                    /////////////////////////////////////////////////////
                } else if (system.isChecked() & data.isChecked() & cache.isChecked()) {
                    if (RootTools.isAccessGiven()) {
                        @SuppressLint("SdCardPath") Command mountcommand = new Command(0,
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount data",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount cache",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /data", "mount -o rw,remount /data",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /data",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /cache",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /cache",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount data",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount cache",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /data", "mount -o ro,remount /data",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /data",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /cache",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /cache");
                        @SuppressLint("SdCardPath") Command fstrimcommand = new Command(1,
                                "/data/data/com.nowenui.systemtweaker/files/busybox fstrim -v /data",
                                "/data/data/com.nowenui.systemtweaker/files/busybox fstrim -v /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox fstrim -v /cache") {
                            @Override
                            public void commandOutput(int id, final String line) {
                                super.commandOutput(id, line);
                                four += line + "\n";
                            }
                        };
                        try {
                            RootTools.getShell(true).add(mountcommand);
                            RootTools.getShell(true).add(fstrimcommand);
                            if (ThemeUtility.getTheme(getActivity().getApplicationContext()) == 1) {
                                final ProgressDialog dialog = new ProgressDialog(getActivity(), R.style.AppCompatAlertDialogStyle);
                                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                dialog.setTitle("FSTRIM");
                                dialog.setCancelable(false);
                                dialog.setMessage(getContext().getResources().getString(R.string.speedmessage));
                                dialog.setIndeterminate(false);
                                dialog.show();

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    public void run() {
                                        dialog.dismiss();
                                        new SnackBar.Builder(getActivity()).withMessage("FSTRIM /cache & /system & /data... OK!").withBackgroundColorId(R.color.resultgood).show();
                                        if (four != null) {
                                            final Handler handler = new Handler();
                                            handler.postDelayed(new Runnable() {
                                                public void run() {
                                                    StringBuilder sb = new StringBuilder(four.trim());
                                                    sb.delete(0, 4);
                                                    final AlertDialog.Builder db = new AlertDialog.Builder(
                                                            getContext());
                                                    db.setMessage(sb);
                                                    db.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface d, int arg1) {
                                                            four = null;
                                                            d.cancel();
                                                        }

                                                    });
                                                    db.show();
                                                }
                                            }, 0);
                                        }
                                    }
                                }, 4000);
                            }
                            if (ThemeUtility.getTheme(getActivity().getApplicationContext()) == 2) {
                                final ProgressDialog dialog = new ProgressDialog(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark));
                                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                dialog.setTitle("FSTRIM");
                                dialog.setCancelable(false);
                                dialog.setMessage(getContext().getResources().getString(R.string.speedmessage));
                                dialog.setIndeterminate(false);
                                dialog.show();

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    public void run() {
                                        dialog.dismiss();
                                        new SnackBar.Builder(getActivity()).withMessage("FSTRIM /cache & /system & /data... OK!").withBackgroundColorId(R.color.resultgood).show();
                                        if (four != null) {
                                            final Handler handler = new Handler();
                                            handler.postDelayed(new Runnable() {
                                                public void run() {
                                                    StringBuilder sb = new StringBuilder(four.trim());
                                                    sb.delete(0, 4);
                                                    final AlertDialog.Builder db = new AlertDialog.Builder(
                                                            new ContextThemeWrapper(getContext(), R.style.AlertDialogDark));
                                                    db.setMessage(sb);
                                                    db.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface d, int arg1) {
                                                            four = null;
                                                            d.cancel();
                                                        }

                                                    });
                                                    db.show();
                                                }
                                            }, 0);
                                        }
                                    }
                                }, 4000);
                            }
                            if (ThemeUtility.getTheme(getActivity().getApplicationContext()) == 3) {
                                final ProgressDialog dialog = new ProgressDialog(new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack));
                                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                dialog.setTitle("FSTRIM");
                                dialog.setCancelable(false);
                                dialog.setMessage(getContext().getResources().getString(R.string.speedmessage));
                                dialog.setIndeterminate(false);
                                dialog.show();

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    public void run() {
                                        dialog.dismiss();
                                        new SnackBar.Builder(getActivity()).withMessage("FSTRIM /cache & /system & /data... OK!").withBackgroundColorId(R.color.resultgood).show();
                                        if (four != null) {
                                            final Handler handler = new Handler();
                                            handler.postDelayed(new Runnable() {
                                                public void run() {
                                                    StringBuilder sb = new StringBuilder(four.trim());
                                                    sb.delete(0, 4);
                                                    final AlertDialog.Builder db = new AlertDialog.Builder(
                                                            new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack));
                                                    db.setMessage(sb);
                                                    db.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface d, int arg1) {
                                                            four = null;
                                                            d.cancel();
                                                        }

                                                    });
                                                    db.show();
                                                }
                                            }, 0);
                                        }
                                    }
                                }, 4000);
                            }
                        } catch (IOException | RootDeniedException | TimeoutException ex) {
                            new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                        }
                    } else {
                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                    }

                }
            }
        });

        /////////////////////////////////////////////////////
        ////// FSTRIM after booting device option ///////////
        ////////////////////////////////////////////////////
        CheckBox bootfstrim = view.findViewById(R.id.bootfstrim);
        if (HelperClass.isInitdSupport() == 0) {
            bootfstrim.setEnabled(false);
        } else {
            bootfstrim.setEnabled(true);
        }
        if (new File("/system/etc/init.d/70fstrim").exists()) {
            bootfstrim.setChecked(true);
        } else {
            bootfstrim.setChecked(false);
        }
        bootfstrim.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


                                                  @Override
                                                  public void onCheckedChanged(CompoundButton buttonView,
                                                                               boolean isChecked) {
                                                      if (isChecked) {
                                                          if (RootTools.isAccessGiven()) {
                                                              @SuppressLint("SdCardPath") Command fstriminitdinstallcommand = new Command(0,
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                      "cp /data/data/com.nowenui.systemtweaker/files/70fstrim /system/etc/init.d/70fstrim",
                                                                      "chmod 777 /system/etc/init.d/70fstrim",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system");
                                                              try {
                                                                  RootTools.getShell(true).add(fstriminitdinstallcommand);
                                                                  new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.ok)).withBackgroundColorId(R.color.resultgood).show();
                                                              } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                                  new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                                                              }
                                                          } else {
                                                              new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                                                          }
                                                      } else {
                                                          if (RootTools.isAccessGiven()) {
                                                              Command fstriminitddeletecommand = new Command(0,
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                      "rm -f /system/etc/init.d/70fstrim",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                                              );
                                                              try {
                                                                  RootTools.getShell(true).add(fstriminitddeletecommand);
                                                                  new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.delete)).withBackgroundColorId(R.color.resultgood).show();
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
}