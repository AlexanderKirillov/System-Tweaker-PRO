package com.nowenui.systemtweaker.fragments;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
import java.util.concurrent.TimeoutException;

public class DPIChangerFragment extends Fragment {
    String dpi = "";
    private boolean isClicked;

    public static DPIChangerFragment newInstance(Bundle bundle) {
        DPIChangerFragment messagesFragment = new DPIChangerFragment();

        if (bundle != null) {
            messagesFragment.setArguments(bundle);
        }

        return messagesFragment;
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
        return inflater.inflate(R.layout.fragment_dpi_changer, parent, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {


        final TextView statedpi = view.findViewById(R.id.statedpi);
        if (getActivity() == null)
            return;
        if (Utility.getTheme(getActivity().getApplicationContext()) == 1) {

            final ProgressDialog dialog = new ProgressDialog(getActivity(), R.style.AppCompatAlertDialogStyle);
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setMessage(getContext().getResources().getString(R.string.pleasewait));
            dialog.setIndeterminate(false);
            dialog.setCancelable(false);
            dialog.show();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    dialog.dismiss();
                        }
            }, 1500);
        }
        if (Utility.getTheme(getActivity().getApplicationContext()) == 2) {

            final ProgressDialog dialog = new ProgressDialog(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark));
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setMessage(getContext().getResources().getString(R.string.pleasewait));
            dialog.setIndeterminate(false);
            dialog.setCancelable(false);
            dialog.show();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    dialog.dismiss();
                        }
            }, 1500);
        }
        if (Utility.getTheme(getActivity().getApplicationContext()) == 3) {

            final ProgressDialog dialog = new ProgressDialog(new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack));
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setMessage(getContext().getResources().getString(R.string.pleasewait));
            dialog.setIndeterminate(false);
            dialog.setCancelable(false);
            dialog.show();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    dialog.dismiss();
                         }
            }, 1500);
        }

        Thread t = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        if (getActivity() != null) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    File file = new File("/system/build.prop");
                                    if (file.exists())
                                        try {
                                            BufferedReader br = new BufferedReader(new FileReader(file));
                                            for (String line = br.readLine(); line != null; line = br.readLine()) {
                                                if (line.contains("ro.sf.lcd_density")) {
                                                    dpi = line.substring(line.indexOf("=") + 1, line.length());
                                                }
                                            }
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    statedpi.setText(getActivity().getApplicationContext().getResources().getString(R.string.dpi4) + dpi);
                                }
                            });
                        }
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        t.start();


        final Spinner dpichanger = view.findViewById(R.id.dpichanger);

        ArrayAdapter<?> adapter =
                ArrayAdapter.createFromResource(getActivity(), R.array.dpilist, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dpichanger.setAdapter(adapter);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        dpichanger.setSelection(prefs.getInt("spinnerSelection", 0), false);

        dpichanger.post(new Runnable() {
            @Override
            public void run() {
                dpichanger.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, final View view, int position, long id) {

                        String[] choose = getResources().getStringArray(R.array.dpilist);
                        int selectedPosition = dpichanger.getSelectedItemPosition();
                        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putInt("spinnerSelection", selectedPosition);
                        editor.apply();


                        if (Utility.getTheme(getActivity().getApplicationContext()) == 1) {
                            if (choose[position].contains("120DPI")) {
                                new AlertDialog.Builder(getContext())
                                        .setTitle(R.string.dpi5)
                                        .setMessage(R.string.dpi120)
                                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
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
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.sf.lcd_density/d' /system/build.prop\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("echo \"ro.sf.lcd_density=120\" >> /system/build.prop\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("exit\n");
                                                    outputStream.flush();
                                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.ok)).withBackgroundColorId(R.color.textview1good).show();
                                                    new android.support.v7.app.AlertDialog.Builder(view.getContext())
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
                                                } catch (Exception ex) {
                                                    new SnackBar.Builder(getActivity()).withMessage("ROOT NEEDED! | ROOT НЕОБХОДИМ!").withBackgroundColorId(R.color.textview1bad).show();
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
                            if (choose[position].contains("160DPI")) {
                                new AlertDialog.Builder(getContext())
                                        .setTitle(R.string.dpi5)
                                        .setMessage(R.string.dpi160)
                                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
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
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.sf.lcd_density/d' /system/build.prop\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("echo \"ro.sf.lcd_density=160\" >> /system/build.prop\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("exit\n");
                                                    outputStream.flush();
                                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.ok)).withBackgroundColorId(R.color.textview1good).show();
                                                    new android.support.v7.app.AlertDialog.Builder(view.getContext())
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
                                                } catch (Exception ex) {
                                                    new SnackBar.Builder(getActivity()).withMessage("ROOT NEEDED! | ROOT НЕОБХОДИМ!").withBackgroundColorId(R.color.textview1bad).show();
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
                            if (choose[position].contains("240DPI")) {
                                new AlertDialog.Builder(getContext())
                                        .setTitle(R.string.dpi5)
                                        .setMessage(R.string.dpi240)
                                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
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
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.sf.lcd_density/d' /system/build.prop\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("echo \"ro.sf.lcd_density=240\" >> /system/build.prop\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("exit\n");
                                                    outputStream.flush();
                                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.ok)).withBackgroundColorId(R.color.textview1good).show();
                                                    new android.support.v7.app.AlertDialog.Builder(view.getContext())
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
                                                } catch (Exception ex) {
                                                    new SnackBar.Builder(getActivity()).withMessage("ROOT NEEDED! | ROOT НЕОБХОДИМ!").withBackgroundColorId(R.color.textview1bad).show();
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
                            if (choose[position].contains("320DPI")) {
                                new AlertDialog.Builder(getContext())
                                        .setTitle(R.string.dpi5)
                                        .setMessage(R.string.dpi320)
                                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
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
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.sf.lcd_density/d' /system/build.prop\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("echo \"ro.sf.lcd_density=320\" >> /system/build.prop\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("exit\n");
                                                    outputStream.flush();
                                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.ok)).withBackgroundColorId(R.color.textview1good).show();
                                                    new android.support.v7.app.AlertDialog.Builder(view.getContext())
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
                                                } catch (Exception ex) {
                                                    new SnackBar.Builder(getActivity()).withMessage("ROOT NEEDED! | ROOT НЕОБХОДИМ!").withBackgroundColorId(R.color.textview1bad).show();
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
                            if (choose[position].contains("360DPI")) {
                                new AlertDialog.Builder(getContext())
                                        .setTitle(R.string.dpi5)
                                        .setMessage(R.string.dpi360)
                                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
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
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.sf.lcd_density/d' /system/build.prop\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("echo \"ro.sf.lcd_density=360\" >> /system/build.prop\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("exit\n");
                                                    outputStream.flush();
                                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.ok)).withBackgroundColorId(R.color.textview1good).show();
                                                    new android.support.v7.app.AlertDialog.Builder(view.getContext())
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
                                                } catch (Exception ex) {
                                                    new SnackBar.Builder(getActivity()).withMessage("ROOT NEEDED! | ROOT НЕОБХОДИМ!").withBackgroundColorId(R.color.textview1bad).show();
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
                            if (choose[position].contains("390DPI")) {
                                new AlertDialog.Builder(getContext())
                                        .setTitle(R.string.dpi5)
                                        .setMessage(R.string.dpi390)
                                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
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
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.sf.lcd_density/d' /system/build.prop\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("echo \"ro.sf.lcd_density=390\" >> /system/build.prop\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("exit\n");
                                                    outputStream.flush();
                                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.ok)).withBackgroundColorId(R.color.textview1good).show();
                                                    new android.support.v7.app.AlertDialog.Builder(view.getContext())
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
                                                } catch (Exception ex) {
                                                    new SnackBar.Builder(getActivity()).withMessage("ROOT NEEDED! | ROOT НЕОБХОДИМ!").withBackgroundColorId(R.color.textview1bad).show();
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
                            if (choose[position].contains("420DPI")) {
                                new AlertDialog.Builder(getContext())
                                        .setTitle(R.string.dpi5)
                                        .setMessage(R.string.dpi420)
                                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
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
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.sf.lcd_density/d' /system/build.prop\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("echo \"ro.sf.lcd_density=420\" >> /system/build.prop\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("exit\n");
                                                    outputStream.flush();
                                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.ok)).withBackgroundColorId(R.color.textview1good).show();
                                                    new android.support.v7.app.AlertDialog.Builder(view.getContext())
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
                                                } catch (Exception ex) {
                                                    new SnackBar.Builder(getActivity()).withMessage("ROOT NEEDED! | ROOT НЕОБХОДИМ!").withBackgroundColorId(R.color.textview1bad).show();
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
                            if (choose[position].contains("350DPI")) {
                                new AlertDialog.Builder(getContext())
                                        .setTitle(R.string.dpi5)
                                        .setMessage(R.string.dpi350)
                                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
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
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.sf.lcd_density/d' /system/build.prop\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("echo \"ro.sf.lcd_density=350\" >> /system/build.prop\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("exit\n");
                                                    outputStream.flush();
                                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.ok)).withBackgroundColorId(R.color.textview1good).show();
                                                    new android.support.v7.app.AlertDialog.Builder(view.getContext())
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
                                                } catch (Exception ex) {
                                                    new SnackBar.Builder(getActivity()).withMessage("ROOT NEEDED! | ROOT НЕОБХОДИМ!").withBackgroundColorId(R.color.textview1bad).show();
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
                            if (choose[position].contains("480DPI")) {
                                new AlertDialog.Builder(getContext())
                                        .setTitle(R.string.dpi5)
                                        .setMessage(R.string.dpi480)
                                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
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
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.sf.lcd_density/d' /system/build.prop\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("echo \"ro.sf.lcd_density=480\" >> /system/build.prop\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("exit\n");
                                                    outputStream.flush();
                                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.ok)).withBackgroundColorId(R.color.textview1good).show();
                                                    new android.support.v7.app.AlertDialog.Builder(view.getContext())
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
                                                } catch (Exception ex) {
                                                    new SnackBar.Builder(getActivity()).withMessage("ROOT NEEDED! | ROOT НЕОБХОДИМ!").withBackgroundColorId(R.color.textview1bad).show();
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
                            if (choose[position].contains("540DPI")) {
                                new AlertDialog.Builder(getContext())
                                        .setTitle(R.string.dpi5)
                                        .setMessage(R.string.dpi540)
                                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
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
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.sf.lcd_density/d' /system/build.prop\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("echo \"ro.sf.lcd_density=540\" >> /system/build.prop\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("exit\n");
                                                    outputStream.flush();
                                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.ok)).withBackgroundColorId(R.color.textview1good).show();
                                                    new android.support.v7.app.AlertDialog.Builder(view.getContext())
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
                                                } catch (Exception ex) {
                                                    new SnackBar.Builder(getActivity()).withMessage("ROOT NEEDED! | ROOT НЕОБХОДИМ!").withBackgroundColorId(R.color.textview1bad).show();
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
                            if (choose[position].contains("640DPI")) {
                                new AlertDialog.Builder(getContext())
                                        .setTitle(R.string.dpi5)
                                        .setMessage(R.string.dpi640)
                                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
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
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.sf.lcd_density/d' /system/build.prop\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("echo \"ro.sf.lcd_density=640\" >> /system/build.prop\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("exit\n");
                                                    outputStream.flush();
                                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.ok)).withBackgroundColorId(R.color.textview1good).show();
                                                    new android.support.v7.app.AlertDialog.Builder(view.getContext())
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
                                                } catch (Exception ex) {
                                                    new SnackBar.Builder(getActivity()).withMessage("ROOT NEEDED! | ROOT НЕОБХОДИМ!").withBackgroundColorId(R.color.textview1bad).show();
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
                            if (choose[position].contains("Custom") || choose[position].contains("свое значение")) {
                                final View viewdpi = getActivity().getLayoutInflater().inflate(R.layout.dpi_dialog, null);
                                Button dpichange = viewdpi.findViewById(R.id.dialogdpiok);
                                dpichange.setBackgroundResource(R.drawable.roundbuttoncal);
                                dpichange.setTextColor(Color.WHITE);
                                dpichange.setTextSize(22);
                                dpichange.setOnClickListener(new View.OnClickListener() {
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
                                        EditText editdpi = viewdpi.findViewById(R.id.dpieditt);
                                        final String dpivalue = editdpi.getText().toString();
                                        new AlertDialog.Builder(getContext())
                                                .setTitle(R.string.dpi5)
                                                .setMessage(getResources().getString(R.string.customdpi) + dpivalue + " ?")
                                                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int which) {
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
                                                            outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.sf.lcd_density/d' /system/build.prop\n");
                                                            outputStream.flush();
                                                            outputStream.writeBytes("echo \"ro.sf.lcd_density=" + dpivalue + "\" >> /system/build.prop\n");
                                                            outputStream.flush();
                                                            outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system\n");
                                                            outputStream.flush();
                                                            outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system\n");
                                                            outputStream.flush();
                                                            outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system\n");
                                                            outputStream.flush();
                                                            outputStream.writeBytes("exit\n");
                                                            outputStream.flush();
                                                            new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.ok)).withBackgroundColorId(R.color.textview1good).show();
                                                            new android.support.v7.app.AlertDialog.Builder(view.getContext())
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
                                                        } catch (Exception ex) {
                                                            new SnackBar.Builder(getActivity()).withMessage("ROOT NEEDED! | ROOT НЕОБХОДИМ!").withBackgroundColorId(R.color.textview1bad).show();
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
                                });
                                AlertDialog dialog = new AlertDialog.Builder(getContext()).create();
                                dialog.setView(viewdpi);
                                dialog.setIcon(R.drawable.warning);
                                dialog.setTitle(R.string.dpi8);
                                dialog.show();
                            }
                        }
                        if (Utility.getTheme(getActivity().getApplicationContext()) == 2) {
                            if (choose[position].contains("120DPI")) {
                                new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark))
                                        .setTitle(R.string.dpi5)
                                        .setMessage(R.string.dpi120)
                                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
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
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.sf.lcd_density/d' /system/build.prop\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("echo \"ro.sf.lcd_density=120\" >> /system/build.prop\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("exit\n");
                                                    outputStream.flush();
                                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.ok)).withBackgroundColorId(R.color.textview1good).show();
                                                    new android.support.v7.app.AlertDialog.Builder(new ContextThemeWrapper(getActivity().getApplicationContext(), R.style.AlertDialogDark))
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
                                                } catch (Exception ex) {
                                                    new SnackBar.Builder(getActivity()).withMessage("ROOT NEEDED! | ROOT НЕОБХОДИМ!").withBackgroundColorId(R.color.textview1bad).show();
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
                            if (choose[position].contains("160DPI")) {
                                new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark))
                                        .setTitle(R.string.dpi5)
                                        .setMessage(R.string.dpi160)
                                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
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
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.sf.lcd_density/d' /system/build.prop\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("echo \"ro.sf.lcd_density=160\" >> /system/build.prop\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("exit\n");
                                                    outputStream.flush();
                                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.ok)).withBackgroundColorId(R.color.textview1good).show();
                                                    new android.support.v7.app.AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark))
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
                                                } catch (Exception ex) {
                                                    new SnackBar.Builder(getActivity()).withMessage("ROOT NEEDED! | ROOT НЕОБХОДИМ!").withBackgroundColorId(R.color.textview1bad).show();
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
                            if (choose[position].contains("240DPI")) {
                                new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark))
                                        .setTitle(R.string.dpi5)
                                        .setMessage(R.string.dpi240)
                                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
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
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.sf.lcd_density/d' /system/build.prop\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("echo \"ro.sf.lcd_density=240\" >> /system/build.prop\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("exit\n");
                                                    outputStream.flush();
                                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.ok)).withBackgroundColorId(R.color.textview1good).show();
                                                    new android.support.v7.app.AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark))
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
                                                } catch (Exception ex) {
                                                    new SnackBar.Builder(getActivity()).withMessage("ROOT NEEDED! | ROOT НЕОБХОДИМ!").withBackgroundColorId(R.color.textview1bad).show();
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
                            if (choose[position].contains("320DPI")) {
                                new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark))
                                        .setTitle(R.string.dpi5)
                                        .setMessage(R.string.dpi320)
                                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
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
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.sf.lcd_density/d' /system/build.prop\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("echo \"ro.sf.lcd_density=320\" >> /system/build.prop\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("exit\n");
                                                    outputStream.flush();
                                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.ok)).withBackgroundColorId(R.color.textview1good).show();
                                                    new android.support.v7.app.AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark))
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
                                                } catch (Exception ex) {
                                                    new SnackBar.Builder(getActivity()).withMessage("ROOT NEEDED! | ROOT НЕОБХОДИМ!").withBackgroundColorId(R.color.textview1bad).show();
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
                            if (choose[position].contains("360DPI")) {
                                new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark))
                                        .setTitle(R.string.dpi5)
                                        .setMessage(R.string.dpi360)
                                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
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
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.sf.lcd_density/d' /system/build.prop\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("echo \"ro.sf.lcd_density=360\" >> /system/build.prop\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("exit\n");
                                                    outputStream.flush();
                                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.ok)).withBackgroundColorId(R.color.textview1good).show();
                                                    new android.support.v7.app.AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark))
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
                                                } catch (Exception ex) {
                                                    new SnackBar.Builder(getActivity()).withMessage("ROOT NEEDED! | ROOT НЕОБХОДИМ!").withBackgroundColorId(R.color.textview1bad).show();
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
                            if (choose[position].contains("390DPI")) {
                                new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark))
                                        .setTitle(R.string.dpi5)
                                        .setMessage(R.string.dpi390)
                                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
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
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.sf.lcd_density/d' /system/build.prop\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("echo \"ro.sf.lcd_density=390\" >> /system/build.prop\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("exit\n");
                                                    outputStream.flush();
                                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.ok)).withBackgroundColorId(R.color.textview1good).show();
                                                    new android.support.v7.app.AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark))
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
                                                } catch (Exception ex) {
                                                    new SnackBar.Builder(getActivity()).withMessage("ROOT NEEDED! | ROOT НЕОБХОДИМ!").withBackgroundColorId(R.color.textview1bad).show();
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
                            if (choose[position].contains("420DPI")) {
                                new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark))
                                        .setTitle(R.string.dpi5)
                                        .setMessage(R.string.dpi420)
                                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
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
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.sf.lcd_density/d' /system/build.prop\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("echo \"ro.sf.lcd_density=420\" >> /system/build.prop\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("exit\n");
                                                    outputStream.flush();
                                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.ok)).withBackgroundColorId(R.color.textview1good).show();
                                                    new android.support.v7.app.AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark))
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
                                                } catch (Exception ex) {
                                                    new SnackBar.Builder(getActivity()).withMessage("ROOT NEEDED! | ROOT НЕОБХОДИМ!").withBackgroundColorId(R.color.textview1bad).show();
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
                            if (choose[position].contains("350DPI")) {
                                new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark))
                                        .setTitle(R.string.dpi5)
                                        .setMessage(R.string.dpi350)
                                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
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
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.sf.lcd_density/d' /system/build.prop\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("echo \"ro.sf.lcd_density=350\" >> /system/build.prop\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("exit\n");
                                                    outputStream.flush();
                                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.ok)).withBackgroundColorId(R.color.textview1good).show();
                                                    new android.support.v7.app.AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark))
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
                                                } catch (Exception ex) {
                                                    new SnackBar.Builder(getActivity()).withMessage("ROOT NEEDED! | ROOT НЕОБХОДИМ!").withBackgroundColorId(R.color.textview1bad).show();
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
                            if (choose[position].contains("480DPI")) {
                                new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark))
                                        .setTitle(R.string.dpi5)
                                        .setMessage(R.string.dpi480)
                                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
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
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.sf.lcd_density/d' /system/build.prop\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("echo \"ro.sf.lcd_density=480\" >> /system/build.prop\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("exit\n");
                                                    outputStream.flush();
                                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.ok)).withBackgroundColorId(R.color.textview1good).show();
                                                    new android.support.v7.app.AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark))
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
                                                } catch (Exception ex) {
                                                    new SnackBar.Builder(getActivity()).withMessage("ROOT NEEDED! | ROOT НЕОБХОДИМ!").withBackgroundColorId(R.color.textview1bad).show();
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
                            if (choose[position].contains("540DPI")) {
                                new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark))
                                        .setTitle(R.string.dpi5)
                                        .setMessage(R.string.dpi540)
                                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
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
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.sf.lcd_density/d' /system/build.prop\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("echo \"ro.sf.lcd_density=540\" >> /system/build.prop\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("exit\n");
                                                    outputStream.flush();
                                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.ok)).withBackgroundColorId(R.color.textview1good).show();
                                                    new android.support.v7.app.AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark))
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
                                                } catch (Exception ex) {
                                                    new SnackBar.Builder(getActivity()).withMessage("ROOT NEEDED! | ROOT НЕОБХОДИМ!").withBackgroundColorId(R.color.textview1bad).show();
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
                            if (choose[position].contains("640DPI")) {
                                new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark))
                                        .setTitle(R.string.dpi5)
                                        .setMessage(R.string.dpi640)
                                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
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
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.sf.lcd_density/d' /system/build.prop\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("echo \"ro.sf.lcd_density=640\" >> /system/build.prop\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("exit\n");
                                                    outputStream.flush();
                                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.ok)).withBackgroundColorId(R.color.textview1good).show();
                                                    new android.support.v7.app.AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark))
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
                                                } catch (Exception ex) {
                                                    new SnackBar.Builder(getActivity()).withMessage("ROOT NEEDED! | ROOT НЕОБХОДИМ!").withBackgroundColorId(R.color.textview1bad).show();
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
                            if (choose[position].contains("Custom") || choose[position].contains("свое значение")) {
                                final View viewdpi = getActivity().getLayoutInflater().inflate(R.layout.dpi_dialog, null);
                                Button dpichange = viewdpi.findViewById(R.id.dialogdpiok);
                                dpichange.setBackgroundResource(R.drawable.roundbuttoncal);
                                dpichange.setTextColor(Color.WHITE);
                                dpichange.setTextSize(22);
                                dpichange.setOnClickListener(new View.OnClickListener() {
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
                                        EditText editdpi = viewdpi.findViewById(R.id.dpieditt);
                                        final String dpivalue = editdpi.getText().toString();
                                        new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark))
                                                .setTitle(R.string.dpi5)
                                                .setMessage(getResources().getString(R.string.customdpi) + dpivalue + " ?")
                                                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int which) {
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
                                                            outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.sf.lcd_density/d' /system/build.prop\n");
                                                            outputStream.flush();
                                                            outputStream.writeBytes("echo \"ro.sf.lcd_density=" + dpivalue + "\" >> /system/build.prop\n");
                                                            outputStream.flush();
                                                            outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system\n");
                                                            outputStream.flush();
                                                            outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system\n");
                                                            outputStream.flush();
                                                            outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system\n");
                                                            outputStream.flush();
                                                            outputStream.writeBytes("exit\n");
                                                            outputStream.flush();
                                                            new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.ok)).withBackgroundColorId(R.color.textview1good).show();
                                                            new android.support.v7.app.AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark))
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
                                                        } catch (Exception ex) {
                                                            new SnackBar.Builder(getActivity()).withMessage("ROOT NEEDED! | ROOT НЕОБХОДИМ!").withBackgroundColorId(R.color.textview1bad).show();
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
                                });
                                AlertDialog dialog = new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark)).create();
                                dialog.setView(viewdpi);
                                dialog.setIcon(R.drawable.warning);
                                dialog.setTitle(R.string.dpi8);
                                dialog.show();
                            }
                        }
                        if (Utility.getTheme(getActivity().getApplicationContext()) == 3) {
                            if (choose[position].contains("120DPI")) {
                                new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack))
                                        .setTitle(R.string.dpi5)
                                        .setMessage(R.string.dpi120)
                                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
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
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.sf.lcd_density/d' /system/build.prop\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("echo \"ro.sf.lcd_density=120\" >> /system/build.prop\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("exit\n");
                                                    outputStream.flush();
                                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.ok)).withBackgroundColorId(R.color.textview1good).show();
                                                    new android.support.v7.app.AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack))
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
                                                } catch (Exception ex) {
                                                    new SnackBar.Builder(getActivity()).withMessage("ROOT NEEDED! | ROOT НЕОБХОДИМ!").withBackgroundColorId(R.color.textview1bad).show();
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
                            if (choose[position].contains("160DPI")) {
                                new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack))
                                        .setTitle(R.string.dpi5)
                                        .setMessage(R.string.dpi160)
                                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
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
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.sf.lcd_density/d' /system/build.prop\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("echo \"ro.sf.lcd_density=160\" >> /system/build.prop\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("exit\n");
                                                    outputStream.flush();
                                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.ok)).withBackgroundColorId(R.color.textview1good).show();
                                                    new android.support.v7.app.AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack))
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
                                                } catch (Exception ex) {
                                                    new SnackBar.Builder(getActivity()).withMessage("ROOT NEEDED! | ROOT НЕОБХОДИМ!").withBackgroundColorId(R.color.textview1bad).show();
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
                            if (choose[position].contains("240DPI")) {
                                new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack))
                                        .setTitle(R.string.dpi5)
                                        .setMessage(R.string.dpi240)
                                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
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
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.sf.lcd_density/d' /system/build.prop\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("echo \"ro.sf.lcd_density=240\" >> /system/build.prop\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("exit\n");
                                                    outputStream.flush();
                                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.ok)).withBackgroundColorId(R.color.textview1good).show();
                                                    new android.support.v7.app.AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack))
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
                                                } catch (Exception ex) {
                                                    new SnackBar.Builder(getActivity()).withMessage("ROOT NEEDED! | ROOT НЕОБХОДИМ!").withBackgroundColorId(R.color.textview1bad).show();
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
                            if (choose[position].contains("320DPI")) {
                                new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack))
                                        .setTitle(R.string.dpi5)
                                        .setMessage(R.string.dpi320)
                                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
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
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.sf.lcd_density/d' /system/build.prop\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("echo \"ro.sf.lcd_density=320\" >> /system/build.prop\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("exit\n");
                                                    outputStream.flush();
                                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.ok)).withBackgroundColorId(R.color.textview1good).show();
                                                    new android.support.v7.app.AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack))
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
                                                } catch (Exception ex) {
                                                    new SnackBar.Builder(getActivity()).withMessage("ROOT NEEDED! | ROOT НЕОБХОДИМ!").withBackgroundColorId(R.color.textview1bad).show();
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
                            if (choose[position].contains("360DPI")) {
                                new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack))
                                        .setTitle(R.string.dpi5)
                                        .setMessage(R.string.dpi360)
                                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
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
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.sf.lcd_density/d' /system/build.prop\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("echo \"ro.sf.lcd_density=360\" >> /system/build.prop\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("exit\n");
                                                    outputStream.flush();
                                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.ok)).withBackgroundColorId(R.color.textview1good).show();
                                                    new android.support.v7.app.AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack))
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
                                                } catch (Exception ex) {
                                                    new SnackBar.Builder(getActivity()).withMessage("ROOT NEEDED! | ROOT НЕОБХОДИМ!").withBackgroundColorId(R.color.textview1bad).show();
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
                            if (choose[position].contains("390DPI")) {
                                new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack))
                                        .setTitle(R.string.dpi5)
                                        .setMessage(R.string.dpi390)
                                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
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
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.sf.lcd_density/d' /system/build.prop\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("echo \"ro.sf.lcd_density=390\" >> /system/build.prop\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("exit\n");
                                                    outputStream.flush();
                                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.ok)).withBackgroundColorId(R.color.textview1good).show();
                                                    new android.support.v7.app.AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack))
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
                                                } catch (Exception ex) {
                                                    new SnackBar.Builder(getActivity()).withMessage("ROOT NEEDED! | ROOT НЕОБХОДИМ!").withBackgroundColorId(R.color.textview1bad).show();
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
                            if (choose[position].contains("420DPI")) {
                                new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack))
                                        .setTitle(R.string.dpi5)
                                        .setMessage(R.string.dpi420)
                                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
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
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.sf.lcd_density/d' /system/build.prop\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("echo \"ro.sf.lcd_density=420\" >> /system/build.prop\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("exit\n");
                                                    outputStream.flush();
                                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.ok)).withBackgroundColorId(R.color.textview1good).show();
                                                    new android.support.v7.app.AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack))
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
                                                } catch (Exception ex) {
                                                    new SnackBar.Builder(getActivity()).withMessage("ROOT NEEDED! | ROOT НЕОБХОДИМ!").withBackgroundColorId(R.color.textview1bad).show();
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
                            if (choose[position].contains("350DPI")) {
                                new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack))
                                        .setTitle(R.string.dpi5)
                                        .setMessage(R.string.dpi350)
                                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
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
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.sf.lcd_density/d' /system/build.prop\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("echo \"ro.sf.lcd_density=350\" >> /system/build.prop\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("exit\n");
                                                    outputStream.flush();
                                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.ok)).withBackgroundColorId(R.color.textview1good).show();
                                                    new android.support.v7.app.AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack))
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
                                                } catch (Exception ex) {
                                                    new SnackBar.Builder(getActivity()).withMessage("ROOT NEEDED! | ROOT НЕОБХОДИМ!").withBackgroundColorId(R.color.textview1bad).show();
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
                            if (choose[position].contains("480DPI")) {
                                new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack))
                                        .setTitle(R.string.dpi5)
                                        .setMessage(R.string.dpi480)
                                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
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
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.sf.lcd_density/d' /system/build.prop\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("echo \"ro.sf.lcd_density=480\" >> /system/build.prop\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("exit\n");
                                                    outputStream.flush();
                                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.ok)).withBackgroundColorId(R.color.textview1good).show();
                                                    new android.support.v7.app.AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack))
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
                                                } catch (Exception ex) {
                                                    new SnackBar.Builder(getActivity()).withMessage("ROOT NEEDED! | ROOT НЕОБХОДИМ!").withBackgroundColorId(R.color.textview1bad).show();
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
                            if (choose[position].contains("540DPI")) {
                                new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack))
                                        .setTitle(R.string.dpi5)
                                        .setMessage(R.string.dpi540)
                                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
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
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.sf.lcd_density/d' /system/build.prop\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("echo \"ro.sf.lcd_density=540\" >> /system/build.prop\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("exit\n");
                                                    outputStream.flush();
                                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.ok)).withBackgroundColorId(R.color.textview1good).show();
                                                    new android.support.v7.app.AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack))
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
                                                } catch (Exception ex) {
                                                    new SnackBar.Builder(getActivity()).withMessage("ROOT NEEDED! | ROOT НЕОБХОДИМ!").withBackgroundColorId(R.color.textview1bad).show();
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
                            if (choose[position].contains("640DPI")) {
                                new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack))
                                        .setTitle(R.string.dpi5)
                                        .setMessage(R.string.dpi640)
                                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
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
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.sf.lcd_density/d' /system/build.prop\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("echo \"ro.sf.lcd_density=640\" >> /system/build.prop\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system\n");
                                                    outputStream.flush();
                                                    outputStream.writeBytes("exit\n");
                                                    outputStream.flush();
                                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.ok)).withBackgroundColorId(R.color.textview1good).show();
                                                    new android.support.v7.app.AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack))
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
                                                } catch (Exception ex) {
                                                    new SnackBar.Builder(getActivity()).withMessage("ROOT NEEDED! | ROOT НЕОБХОДИМ!").withBackgroundColorId(R.color.textview1bad).show();
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
                            if (choose[position].contains("Custom") || choose[position].contains("свое значение")) {
                                final View viewdpi = getActivity().getLayoutInflater().inflate(R.layout.dpi_dialog, null);
                                Button dpichange = viewdpi.findViewById(R.id.dialogdpiok);
                                dpichange.setBackgroundResource(R.drawable.roundbuttoncal);
                                dpichange.setTextColor(Color.WHITE);
                                dpichange.setTextSize(22);
                                dpichange.setOnClickListener(new View.OnClickListener() {
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
                                        EditText editdpi = viewdpi.findViewById(R.id.dpieditt);
                                        final String dpivalue = editdpi.getText().toString();
                                        new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack))
                                                .setTitle(R.string.dpi5)
                                                .setMessage(getResources().getString(R.string.customdpi) + dpivalue + " ?")
                                                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int which) {
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
                                                            outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.sf.lcd_density/d' /system/build.prop\n");
                                                            outputStream.flush();
                                                            outputStream.writeBytes("echo \"ro.sf.lcd_density=" + dpivalue + "\" >> /system/build.prop\n");
                                                            outputStream.flush();
                                                            outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system\n");
                                                            outputStream.flush();
                                                            outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system\n");
                                                            outputStream.flush();
                                                            outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system\n");
                                                            outputStream.flush();
                                                            outputStream.writeBytes("exit\n");
                                                            outputStream.flush();
                                                            new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.ok)).withBackgroundColorId(R.color.textview1good).show();
                                                            new android.support.v7.app.AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack))
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
                                                        } catch (Exception ex) {
                                                            new SnackBar.Builder(getActivity()).withMessage("ROOT NEEDED! | ROOT НЕОБХОДИМ!").withBackgroundColorId(R.color.textview1bad).show();
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
                                });
                                AlertDialog dialog = new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack)).create();
                                dialog.setView(viewdpi);
                                dialog.setIcon(R.drawable.warning);
                                dialog.setTitle(R.string.dpi8);
                                dialog.show();
                            }
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
            }
        });

    }

}