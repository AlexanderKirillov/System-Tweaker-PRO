package com.nowenui.systemtweaker.fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.github.mrengineer13.snackbar.SnackBar;
import com.nowenui.systemtweaker.ProgressHelper;
import com.nowenui.systemtweaker.R;
import com.nowenui.systemtweaker.Utility;
import com.stericson.RootShell.exceptions.RootDeniedException;
import com.stericson.RootShell.execution.Command;
import com.stericson.RootTools.RootTools;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

import mbanje.kurt.fabbutton.FabButton;

public class BraviaFragment extends Fragment {

    AlertDialog.Builder dialogBuilder;
    AlertDialog alertDialog;
    private boolean isClicked;

    public static BraviaFragment newInstance(Bundle bundle) {
        BraviaFragment Bravia = new BraviaFragment();

        if (bundle != null) {
            Bravia.setArguments(bundle);
        }

        return Bravia;
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
                    new AlertDialog.Builder(this.getContext())
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
                    new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark))
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
                    new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack))
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

        return inflater.inflate(R.layout.fragment_bravia, parent, false);
    }


    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {

        final FabButton installbravia = view.findViewById(R.id.braviaimp);
        final ProgressHelper helper2 = new ProgressHelper(installbravia, getActivity());

        if ((Build.VERSION.SDK_INT >= 19)) {
            String check10 = "/bin/sony_xreality";
            String check10a = "/system/bin/sony_xreality";
            if (new File(Environment.getRootDirectory() + check10).exists() || new File(check10a).exists() || new File(Environment.getRootDirectory() + check10a).exists()) {
                installbravia.setEnabled(false);
                installbravia.setColor(Color.parseColor("#828282"));

                final Button deletemusic = view.findViewById(R.id.deletemusic);
                deletemusic.setEnabled(true);
                deletemusic.setBackgroundColor(Color.parseColor("#e0434b"));
                deletemusic.setTextColor(Color.WHITE);

                //////////////////////////////////////////
                ////// Deleting Bravia........ ///////////
                //////////////////////////////////////////
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
                            Command command5 = new Command(0,
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
                                RootTools.getShell(true).add(command5);
                                if (Utility.getTheme(getActivity().getApplicationContext()) == 1) {
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
                                            new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.delmusic)).withBackgroundColorId(R.color.textview1good).show();
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
                                    }, 10000);

                                }
                                if (Utility.getTheme(getActivity().getApplicationContext()) == 2) {
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
                                            new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.delmusic)).withBackgroundColorId(R.color.textview1good).show();
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
                                    }, 10000);

                                }
                                if (Utility.getTheme(getActivity().getApplicationContext()) == 3) {
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
                                            new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.delmusic)).withBackgroundColorId(R.color.textview1good).show();
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
                                    }, 10000);

                                }
                                deletemusic.setEnabled(false);
                                deletemusic.setBackgroundResource(R.drawable.roundbuttonfuck);
                                deletemusic.setTextColor(Color.WHITE);

                                installbravia.setEnabled(true);
                                installbravia.setColor(Color.parseColor("#116062"));
                            } catch (IOException | RootDeniedException | TimeoutException ex) {
                                ex.printStackTrace();
                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                            }
                        }
                    }
                });

            } else {
                installbravia.setEnabled(true);
                installbravia.setColor(Color.parseColor("#116062"));
                Button deletemusic = view.findViewById(R.id.deletemusic);
                deletemusic.setEnabled(false);
                deletemusic.setBackgroundResource(R.drawable.roundbuttonfuck);
                deletemusic.setTextColor(Color.WHITE);
            }
        } else {
            installbravia.setEnabled(false);
            installbravia.setColor(Color.parseColor("#828282"));
            final Button deletemusic = view.findViewById(R.id.deletemusic);
            deletemusic.setEnabled(false);
            deletemusic.setBackgroundResource(R.drawable.roundbuttonfuck);
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
                helper2.startDeterminate();
                isClicked = true;
                v.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        isClicked = false;
                    }
                }, 1000);


                if (RootTools.isAccessGiven()) {
                    Command command5 = new Command(0,
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
                        RootTools.getShell(true).add(command5);
                        if (Utility.getTheme(getActivity().getApplicationContext()) == 1) {
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
                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.okmusic)).withBackgroundColorId(R.color.textview1good).show();
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
                                                        new SnackBar.Builder(getActivity()).withMessage("ROOT NEEDED!").withBackgroundColorId(R.color.textview1bad).show();
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
                        if (Utility.getTheme(getActivity().getApplicationContext()) == 2) {
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
                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.okmusic)).withBackgroundColorId(R.color.textview1good).show();
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
                                                        new SnackBar.Builder(getActivity()).withMessage("ROOT NEEDED!").withBackgroundColorId(R.color.textview1bad).show();
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
                        if (Utility.getTheme(getActivity().getApplicationContext()) == 3) {
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
                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.okmusic)).withBackgroundColorId(R.color.textview1good).show();
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
                                                        new SnackBar.Builder(getActivity()).withMessage("ROOT NEEDED!").withBackgroundColorId(R.color.textview1bad).show();
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
                        final Button deletemusic = view.findViewById(R.id.deletemusic);
                        deletemusic.setEnabled(true);
                        deletemusic.setBackgroundColor(Color.parseColor("#e0434b"));
                        deletemusic.setTextColor(Color.WHITE);

                    } catch (IOException | RootDeniedException | TimeoutException ex) {
                        ex.printStackTrace();
                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                    }
                }
            }
        });

    }
}
