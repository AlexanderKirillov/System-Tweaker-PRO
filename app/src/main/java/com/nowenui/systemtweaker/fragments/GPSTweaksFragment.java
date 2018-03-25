package com.nowenui.systemtweaker.fragments;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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
import com.nowenui.systemtweaker.R;
import com.nowenui.systemtweaker.Utility;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;

public class GPSTweaksFragment extends Fragment {

    public static GPSTweaksFragment newInstance(Bundle bundle) {
        GPSTweaksFragment GPSTweak = new GPSTweaksFragment();

        if (bundle != null) {
            GPSTweak.setArguments(bundle);
        }

        return GPSTweak;
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

    @SuppressLint("RestrictedApi")
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


    public int standart() {
        File f = new File("/system/etc/SystemTweaker");
        if ((f.exists()) && (f.isDirectory())) {
            return 1;
        }
        return 0;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gpstweaks, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        ////////////////////////////
        ////// GPS TWEAK ///////////
        ////////////////////////////

        final Button ruexecute = view.findViewById(R.id.ru);
        final Button ukexecute = view.findViewById(R.id.uk);
        final Button beexecute = view.findViewById(R.id.be);
        final Button kzexecute = view.findViewById(R.id.kz);
        final Button slovexecute = view.findViewById(R.id.slov);
        final Button canexecute = view.findViewById(R.id.can);
        final Button gerexecute = view.findViewById(R.id.ger);
        final Button morrocoexecute = view.findViewById(R.id.morrocco);
        final Button unitedkexecute = view.findViewById(R.id.unitedk);
        final Button iranexecute = view.findViewById(R.id.iran);
        final Button indiaexecute = view.findViewById(R.id.india);
        final Button indonesiaexecute = view.findViewById(R.id.indonesia);
        final Button standartexecute = view.findViewById(R.id.standart);

        if (standart() == 0) {
            standartexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.oksmall), null, null, null);
        }

        /////////////////////////
        ////// Russia ///////////
        /////////////////////////

        String russia = "/etc/SystemTweaker/russia";
        String russia1 = "/system/etc/SystemTweaker/russia";

        if (new File(Environment.getRootDirectory() + russia).exists() || new File(russia1).exists() || new File(Environment.getRootDirectory() + russia1).exists()) {
            ruexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.oksmall), null, null, null);
        }

        ///////////////////////////////
        ////// Morocco ////////////////
        ///////////////////////////////
        String mor = "/etc/SystemTweaker/mor";
        String mor1 = "/system/etc/SystemTweaker/mor";

        if (new File(Environment.getRootDirectory() + mor).exists() || new File(mor1).exists() || new File(Environment.getRootDirectory() + mor1).exists()) {
            morrocoexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.oksmall), null, null, null);
        }

        /////////////////////////////
        ////// Kazakhstan ///////////
        /////////////////////////////
        String kz = "/etc/SystemTweaker/kz";
        String kz1 = "/system/etc/SystemTweaker/kz";

        if (new File(Environment.getRootDirectory() + kz).exists() || new File(kz1).exists() || new File(Environment.getRootDirectory() + kz1).exists()) {
            kzexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.oksmall), null, null, null);

        }

        /////////////////////////
        ////// Ukraine //////////
        /////////////////////////
        String ukraine = "/etc/SystemTweaker/ukraine";
        String ukraine1 = "/system/etc/SystemTweaker/ukraine";
        if (new File(Environment.getRootDirectory() + ukraine).exists() || new File(ukraine1).exists() || new File(Environment.getRootDirectory() + ukraine1).exists()) {
            ukexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.oksmall), null, null, null);
            ukexecute.setEnabled(false);
        }

        ///////////////////////////
        ////// Slovakia ///////////
        ///////////////////////////
        String slovakia = "/etc/SystemTweaker/slovakia";
        String slovakia1 = "/system/etc/SystemTweaker/slovakia";
        if (new File(Environment.getRootDirectory() + slovakia).exists() || new File(slovakia1).exists() || new File(Environment.getRootDirectory() + slovakia1).exists()) {
            slovexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.oksmall), null, null, null);
        }

        /////////////////////////
        ////// Canada ///////////
        /////////////////////////
        String canada = "/etc/SystemTweaker/canada";
        String canada1 = "/system/etc/SystemTweaker/canada";
        if (new File(Environment.getRootDirectory() + canada).exists() || new File(canada1).exists() || new File(Environment.getRootDirectory() + canada1).exists()) {
            canexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.oksmall), null, null, null);
        }


        //////////////////////////
        ////// Germany ///////////
        //////////////////////////
        String germany = "/etc/SystemTweaker/germany";
        String germany1 = "/system/etc/SystemTweaker/germany";
        if (new File(Environment.getRootDirectory() + germany).exists() || new File(germany1).exists() || new File(Environment.getRootDirectory() + germany1).exists()) {
            gerexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.oksmall), null, null, null);
        }

        //////////////////////////
        ////// Belarus ///////////
        //////////////////////////
        String belarus = "/etc/SystemTweaker/belarus";
        String belarus1 = "/system/etc/SystemTweaker/belarus";
        if (new File(Environment.getRootDirectory() + belarus).exists() || new File(belarus1).exists() || new File(Environment.getRootDirectory() + belarus1).exists()) {
            beexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.oksmall), null, null, null);
        }

        /////////////////////
        ////// UK ///////////
        /////////////////////
        String uk = "/etc/SystemTweaker/uk";
        String uk1 = "/system/etc/SystemTweaker/uk";
        if (new File(Environment.getRootDirectory() + uk).exists() || new File(uk1).exists() || new File(Environment.getRootDirectory() + uk1).exists()) {
            unitedkexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.oksmall), null, null, null);
        }

        ///////////////////////////////////////
        ////// Iran/Iraq/Azerbaijan ///////////
        ///////////////////////////////////////
        String az = "/etc/SystemTweaker/az";
        String az1 = "/system/etc/SystemTweaker/az";
        if (new File(Environment.getRootDirectory() + az).exists() || new File(az1).exists() || new File(Environment.getRootDirectory() + az1).exists()) {
            iranexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.oksmall), null, null, null);
        }

        ///////////////////////////////////////
        ////// India //////////////////////////
        ///////////////////////////////////////
        String india = "/etc/SystemTweaker/india";
        String india1 = "/system/etc/SystemTweaker/india";

        if (new File(Environment.getRootDirectory() + india).exists() || new File(india1).exists() || new File(Environment.getRootDirectory() + india1).exists()) {
            indiaexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.oksmall), null, null, null);
        }

        ///////////////////////////////////////
        ////// Indonesia //////////////////////
        ///////////////////////////////////////
        String indonesia = "/etc/SystemTweaker/indonesia";
        String indonesia1 = "/system/etc/SystemTweaker/indonesia";

        if (new File(Environment.getRootDirectory() + indonesia).exists() || new File(indonesia1).exists() || new File(Environment.getRootDirectory() + indonesia1).exists()) {
            indonesiaexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.oksmall), null, null, null);
        }

        standartexecute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                standartexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.oksmall), null, null, null);
                indiaexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                indonesiaexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                ruexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                ukexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                beexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                kzexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                slovexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                canexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                gerexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                morrocoexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                unitedkexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                iranexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                indiaexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
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
                    outputStream.writeBytes("rm -rf /system/etc/SystemTweaker\n");
                    outputStream.flush();
                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system\n");
                    outputStream.flush();
                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system\n");
                    outputStream.flush();
                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system\n");
                    outputStream.flush();
                    outputStream.writeBytes("exit\n");
                    outputStream.flush();

                    if (Utility.getTheme(getActivity().getApplicationContext()) == 1) {

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
                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.sucessreboot)).withBackgroundColorId(R.color.textview1good).show();
                            }
                        }, 4000);
                    }
                    if (Utility.getTheme(getActivity().getApplicationContext()) == 2) {

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
                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.sucessreboot)).withBackgroundColorId(R.color.textview1good).show();
                            }
                        }, 4000);
                    }
                    if (Utility.getTheme(getActivity().getApplicationContext()) == 3) {

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
                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.sucessreboot)).withBackgroundColorId(R.color.textview1good).show();
                            }
                        }, 4000);
                    }
                } catch (IOException e) {
                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                }
            }
        });

        ruexecute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ruexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.oksmall), null, null, null);
                indiaexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                standartexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                indonesiaexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                ukexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                beexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                kzexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                slovexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                canexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                gerexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                morrocoexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                unitedkexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                iranexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
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
                    outputStream.writeBytes("rm -f /system/etc/gps.conf\n");
                    outputStream.flush();
                    outputStream.writeBytes("rm -f /system/etc/SystemTweaker/*\n");
                    outputStream.flush();
                    outputStream.writeBytes("cp /data/data/com.nowenui.systemtweaker/files/russia.conf /system/etc/gps.conf\n");
                    outputStream.flush();
                    outputStream.writeBytes("chmod 644 /system/etc/gps.conf\n");
                    outputStream.flush();
                    outputStream.writeBytes("mkdir /system/etc/SystemTweaker\n");
                    outputStream.flush();
                    outputStream.writeBytes("chmod 755 /system/etc/SystemTweaker\n");
                    outputStream.flush();
                    outputStream.writeBytes("echo \"EXCUTED\" > /system/etc/SystemTweaker/russia\n");
                    outputStream.flush();
                    outputStream.writeBytes("chmod 777 /system/etc/SystemTweaker/russia\n");
                    outputStream.flush();
                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system\n");
                    outputStream.flush();
                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system\n");
                    outputStream.flush();
                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system\n");
                    outputStream.flush();
                    outputStream.writeBytes("exit\n");
                    outputStream.flush();
                    if (Utility.getTheme(getActivity().getApplicationContext()) == 1) {

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
                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.sucessreboot)).withBackgroundColorId(R.color.textview1good).show();
                            }
                        }, 4000);
                    }
                    if (Utility.getTheme(getActivity().getApplicationContext()) == 2) {

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
                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.sucessreboot)).withBackgroundColorId(R.color.textview1good).show();
                            }
                        }, 4000);
                    }
                    if (Utility.getTheme(getActivity().getApplicationContext()) == 3) {

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
                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.sucessreboot)).withBackgroundColorId(R.color.textview1good).show();
                            }
                        }, 4000);
                    }
                } catch (IOException e) {
                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                }
            }
        });

        beexecute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.oksmall), null, null, null);
                indiaexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                indonesiaexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                ruexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                ukexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                standartexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                kzexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                slovexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                canexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                gerexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                morrocoexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                unitedkexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                iranexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
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
                    outputStream.writeBytes("rm -f /system/etc/gps.conf\n");
                    outputStream.flush();
                    outputStream.writeBytes("rm -f /system/etc/SystemTweaker/*\n");
                    outputStream.flush();
                    outputStream.writeBytes("cp /data/data/com.nowenui.systemtweaker/files/belarus.conf /system/etc/gps.conf\n");
                    outputStream.flush();
                    outputStream.writeBytes("chmod 644 /system/etc/gps.conf\n");
                    outputStream.flush();
                    outputStream.writeBytes("mkdir /system/etc/SystemTweaker\n");
                    outputStream.flush();
                    outputStream.writeBytes("chmod 755 /system/etc/SystemTweaker\n");
                    outputStream.flush();
                    outputStream.writeBytes("echo \"EXCUTED\" > /system/etc/SystemTweaker/belarus\n");
                    outputStream.flush();
                    outputStream.writeBytes("chmod 777 /system/etc/SystemTweaker/belarus\n");
                    outputStream.flush();
                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system\n");
                    outputStream.flush();
                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system\n");
                    outputStream.flush();
                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system\n");
                    outputStream.flush();
                    outputStream.writeBytes("exit\n");
                    outputStream.flush();
                    if (Utility.getTheme(getActivity().getApplicationContext()) == 1) {

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
                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.sucessreboot)).withBackgroundColorId(R.color.textview1good).show();
                            }
                        }, 4000);
                    }
                    if (Utility.getTheme(getActivity().getApplicationContext()) == 2) {

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
                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.sucessreboot)).withBackgroundColorId(R.color.textview1good).show();
                            }
                        }, 4000);
                    }
                    if (Utility.getTheme(getActivity().getApplicationContext()) == 3) {

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
                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.sucessreboot)).withBackgroundColorId(R.color.textview1good).show();
                            }
                        }, 4000);
                    }
                } catch (IOException e) {
                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                }
            }
        });

        kzexecute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kzexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.oksmall), null, null, null);
                indiaexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                indonesiaexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                ruexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                ukexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                beexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                standartexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                slovexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                canexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                gerexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                morrocoexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                unitedkexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                iranexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
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
                    outputStream.writeBytes("rm -f /system/etc/gps.conf\n");
                    outputStream.flush();
                    outputStream.writeBytes("rm -f /system/etc/SystemTweaker/*\n");
                    outputStream.flush();
                    outputStream.writeBytes("cp /data/data/com.nowenui.systemtweaker/files/kazahstan.conf /system/etc/gps.conf\n");
                    outputStream.flush();
                    outputStream.writeBytes("chmod 644 /system/etc/gps.conf\n");
                    outputStream.flush();
                    outputStream.writeBytes("mkdir /system/etc/SystemTweaker\n");
                    outputStream.flush();
                    outputStream.writeBytes("chmod 755 /system/etc/SystemTweaker\n");
                    outputStream.flush();
                    outputStream.writeBytes("echo \"EXCUTED\" > /system/etc/SystemTweaker/kz\n");
                    outputStream.flush();
                    outputStream.writeBytes("chmod 777 /system/etc/SystemTweaker/kz\n");
                    outputStream.flush();
                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system\n");
                    outputStream.flush();
                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system\n");
                    outputStream.flush();
                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system\n");
                    outputStream.flush();
                    outputStream.writeBytes("exit\n");
                    outputStream.flush();
                    if (Utility.getTheme(getActivity().getApplicationContext()) == 1) {

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
                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.sucessreboot)).withBackgroundColorId(R.color.textview1good).show();
                            }
                        }, 4000);
                    }
                    if (Utility.getTheme(getActivity().getApplicationContext()) == 2) {

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
                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.sucessreboot)).withBackgroundColorId(R.color.textview1good).show();
                            }
                        }, 4000);
                    }
                    if (Utility.getTheme(getActivity().getApplicationContext()) == 3) {

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
                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.sucessreboot)).withBackgroundColorId(R.color.textview1good).show();
                            }
                        }, 4000);
                    }
                } catch (IOException e) {
                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                }
            }
        });

        slovexecute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slovexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.oksmall), null, null, null);
                indiaexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                indonesiaexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                ruexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                ukexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                beexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                kzexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                standartexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                canexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                gerexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                morrocoexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                unitedkexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                iranexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
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
                    outputStream.writeBytes("rm -f /system/etc/gps.conf\n");
                    outputStream.flush();
                    outputStream.writeBytes("rm -f /system/etc/SystemTweaker/*\n");
                    outputStream.flush();
                    outputStream.writeBytes("cp /data/data/com.nowenui.systemtweaker/files/slovakia.conf /system/etc/gps.conf\n");
                    outputStream.flush();
                    outputStream.writeBytes("chmod 644 /system/etc/gps.conf\n");
                    outputStream.flush();
                    outputStream.writeBytes("mkdir /system/etc/SystemTweaker\n");
                    outputStream.flush();
                    outputStream.writeBytes("chmod 755 /system/etc/SystemTweaker\n");
                    outputStream.flush();
                    outputStream.writeBytes("echo \"EXCUTED\" > /system/etc/SystemTweaker/slovakia\n");
                    outputStream.flush();
                    outputStream.writeBytes("chmod 777 /system/etc/SystemTweaker/slovakia\n");
                    outputStream.flush();
                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system\n");
                    outputStream.flush();
                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system\n");
                    outputStream.flush();
                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system\n");
                    outputStream.flush();
                    outputStream.writeBytes("exit\n");
                    outputStream.flush();
                    if (Utility.getTheme(getActivity().getApplicationContext()) == 1) {

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
                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.sucessreboot)).withBackgroundColorId(R.color.textview1good).show();
                            }
                        }, 4000);
                    }
                    if (Utility.getTheme(getActivity().getApplicationContext()) == 2) {

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
                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.sucessreboot)).withBackgroundColorId(R.color.textview1good).show();
                            }
                        }, 4000);
                    }
                    if (Utility.getTheme(getActivity().getApplicationContext()) == 3) {

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
                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.sucessreboot)).withBackgroundColorId(R.color.textview1good).show();
                            }
                        }, 4000);
                    }
                } catch (IOException e) {
                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                }
            }
        });

        canexecute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.oksmall), null, null, null);
                indiaexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                indonesiaexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                ruexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                ukexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                beexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                kzexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                slovexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                standartexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                gerexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                morrocoexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                unitedkexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                iranexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
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
                    outputStream.writeBytes("rm -f /system/etc/gps.conf\n");
                    outputStream.flush();
                    outputStream.writeBytes("rm -f /system/etc/SystemTweaker/*\n");
                    outputStream.flush();
                    outputStream.writeBytes("cp /data/data/com.nowenui.systemtweaker/files/canada.conf /system/etc/gps.conf\n");
                    outputStream.flush();
                    outputStream.writeBytes("chmod 644 /system/etc/gps.conf\n");
                    outputStream.flush();
                    outputStream.writeBytes("mkdir /system/etc/SystemTweaker\n");
                    outputStream.flush();
                    outputStream.writeBytes("chmod 755 /system/etc/SystemTweaker\n");
                    outputStream.flush();
                    outputStream.writeBytes("echo \"EXCUTED\" > /system/etc/SystemTweaker/canada\n");
                    outputStream.flush();
                    outputStream.writeBytes("chmod 777 /system/etc/SystemTweaker/canada\n");
                    outputStream.flush();
                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system\n");
                    outputStream.flush();
                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system\n");
                    outputStream.flush();
                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system\n");
                    outputStream.flush();
                    outputStream.writeBytes("exit\n");
                    outputStream.flush();
                    if (Utility.getTheme(getActivity().getApplicationContext()) == 1) {

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
                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.sucessreboot)).withBackgroundColorId(R.color.textview1good).show();
                            }
                        }, 4000);
                    }
                    if (Utility.getTheme(getActivity().getApplicationContext()) == 2) {

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
                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.sucessreboot)).withBackgroundColorId(R.color.textview1good).show();
                            }
                        }, 4000);
                    }
                    if (Utility.getTheme(getActivity().getApplicationContext()) == 3) {

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
                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.sucessreboot)).withBackgroundColorId(R.color.textview1good).show();
                            }
                        }, 4000);
                    }
                } catch (IOException e) {
                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                }
            }
        });

        ukexecute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ukexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.oksmall), null, null, null);
                indiaexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                ruexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                indonesiaexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                standartexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                beexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                kzexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                slovexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                canexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                gerexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                morrocoexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                unitedkexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                iranexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
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
                    outputStream.writeBytes("rm -f /system/etc/gps.conf\n");
                    outputStream.flush();
                    outputStream.writeBytes("rm -f /system/etc/SystemTweaker/*\n");
                    outputStream.flush();
                    outputStream.writeBytes("cp /data/data/com.nowenui.systemtweaker/files/ukraine.conf /system/etc/gps.conf\n");
                    outputStream.flush();
                    outputStream.writeBytes("chmod 644 /system/etc/gps.conf\n");
                    outputStream.flush();
                    outputStream.writeBytes("mkdir /system/etc/SystemTweaker\n");
                    outputStream.flush();
                    outputStream.writeBytes("chmod 755 /system/etc/SystemTweaker\n");
                    outputStream.flush();
                    outputStream.writeBytes("echo \"EXCUTED\" > /system/etc/SystemTweaker/ukraine\n");
                    outputStream.flush();
                    outputStream.writeBytes("chmod 777 /system/etc/SystemTweaker/ukraine\n");
                    outputStream.flush();
                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system\n");
                    outputStream.flush();
                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system\n");
                    outputStream.flush();
                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system\n");
                    outputStream.flush();
                    outputStream.writeBytes("exit\n");
                    outputStream.flush();
                    if (Utility.getTheme(getActivity().getApplicationContext()) == 1) {

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
                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.sucessreboot)).withBackgroundColorId(R.color.textview1good).show();
                            }
                        }, 4000);
                    }
                    if (Utility.getTheme(getActivity().getApplicationContext()) == 2) {

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
                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.sucessreboot)).withBackgroundColorId(R.color.textview1good).show();
                            }
                        }, 4000);
                    }
                    if (Utility.getTheme(getActivity().getApplicationContext()) == 3) {

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
                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.sucessreboot)).withBackgroundColorId(R.color.textview1good).show();
                            }
                        }, 4000);
                    }
                } catch (IOException e) {
                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                }
            }
        });

        gerexecute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gerexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.oksmall), null, null, null);
                indiaexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                ruexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                indonesiaexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                ukexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                beexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                kzexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                slovexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                canexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                standartexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                morrocoexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                unitedkexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                iranexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
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
                    outputStream.writeBytes("rm -f /system/etc/gps.conf\n");
                    outputStream.flush();
                    outputStream.writeBytes("rm -f /system/etc/SystemTweaker/*\n");
                    outputStream.flush();
                    outputStream.writeBytes("cp /data/data/com.nowenui.systemtweaker/files/germany.conf /system/etc/gps.conf\n");
                    outputStream.flush();
                    outputStream.writeBytes("chmod 644 /system/etc/gps.conf\n");
                    outputStream.flush();
                    outputStream.writeBytes("mkdir /system/etc/SystemTweaker\n");
                    outputStream.flush();
                    outputStream.writeBytes("chmod 755 /system/etc/SystemTweaker\n");
                    outputStream.flush();
                    outputStream.writeBytes("echo \"EXCUTED\" > /system/etc/SystemTweaker/germany\n");
                    outputStream.flush();
                    outputStream.writeBytes("chmod 777 /system/etc/SystemTweaker/germany\n");
                    outputStream.flush();
                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system\n");
                    outputStream.flush();
                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system\n");
                    outputStream.flush();
                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system\n");
                    outputStream.flush();
                    outputStream.writeBytes("exit\n");
                    outputStream.flush();
                    if (Utility.getTheme(getActivity().getApplicationContext()) == 1) {

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
                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.sucessreboot)).withBackgroundColorId(R.color.textview1good).show();
                            }
                        }, 4000);
                    }
                    if (Utility.getTheme(getActivity().getApplicationContext()) == 2) {

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
                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.sucessreboot)).withBackgroundColorId(R.color.textview1good).show();
                            }
                        }, 4000);
                    }
                    if (Utility.getTheme(getActivity().getApplicationContext()) == 3) {

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
                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.sucessreboot)).withBackgroundColorId(R.color.textview1good).show();
                            }
                        }, 4000);
                    }
                } catch (IOException e) {
                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                }
            }
        });

        morrocoexecute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                morrocoexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.oksmall), null, null, null);
                indiaexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                indonesiaexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                ruexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                ukexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                beexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                kzexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                slovexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                canexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                gerexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                standartexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                unitedkexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                iranexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
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
                    outputStream.writeBytes("rm -f /system/etc/gps.conf\n");
                    outputStream.flush();
                    outputStream.writeBytes("rm -f /system/etc/SystemTweaker/*\n");
                    outputStream.flush();
                    outputStream.writeBytes("cp /data/data/com.nowenui.systemtweaker/files/morokko.conf /system/etc/gps.conf\n");
                    outputStream.flush();
                    outputStream.writeBytes("chmod 644 /system/etc/gps.conf\n");
                    outputStream.flush();
                    outputStream.writeBytes("mkdir /system/etc/SystemTweaker\n");
                    outputStream.flush();
                    outputStream.writeBytes("chmod 755 /system/etc/SystemTweaker\n");
                    outputStream.flush();
                    outputStream.writeBytes("echo \"EXCUTED\" > /system/etc/SystemTweaker/mor\n");
                    outputStream.flush();
                    outputStream.writeBytes("chmod 777 /system/etc/SystemTweaker/mor\n");
                    outputStream.flush();
                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system\n");
                    outputStream.flush();
                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system\n");
                    outputStream.flush();
                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system\n");
                    outputStream.flush();
                    outputStream.writeBytes("exit\n");
                    outputStream.flush();
                    if (Utility.getTheme(getActivity().getApplicationContext()) == 1) {

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
                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.sucessreboot)).withBackgroundColorId(R.color.textview1good).show();
                            }
                        }, 4000);
                    }
                    if (Utility.getTheme(getActivity().getApplicationContext()) == 2) {

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
                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.sucessreboot)).withBackgroundColorId(R.color.textview1good).show();
                            }
                        }, 4000);
                    }
                    if (Utility.getTheme(getActivity().getApplicationContext()) == 3) {

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
                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.sucessreboot)).withBackgroundColorId(R.color.textview1good).show();
                            }
                        }, 4000);
                    }
                } catch (IOException e) {
                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                }
            }
        });

        unitedkexecute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unitedkexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.oksmall), null, null, null);
                indiaexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                ruexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                indonesiaexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                ukexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                beexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                kzexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                slovexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                canexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                gerexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                morrocoexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                standartexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                iranexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
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
                    outputStream.writeBytes("rm -f /system/etc/gps.conf\n");
                    outputStream.flush();
                    outputStream.writeBytes("rm -f /system/etc/SystemTweaker/*\n");
                    outputStream.flush();
                    outputStream.writeBytes("cp /data/data/com.nowenui.systemtweaker/files/britain.conf /system/etc/gps.conf\n");
                    outputStream.flush();
                    outputStream.writeBytes("chmod 644 /system/etc/gps.conf\n");
                    outputStream.flush();
                    outputStream.writeBytes("mkdir /system/etc/SystemTweaker\n");
                    outputStream.flush();
                    outputStream.writeBytes("chmod 755 /system/etc/SystemTweaker\n");
                    outputStream.flush();
                    outputStream.writeBytes("echo \"EXCUTED\" > /system/etc/SystemTweaker/uk\n");
                    outputStream.flush();
                    outputStream.writeBytes("chmod 777 /system/etc/SystemTweaker/uk\n");
                    outputStream.flush();
                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system\n");
                    outputStream.flush();
                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system\n");
                    outputStream.flush();
                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system\n");
                    outputStream.flush();
                    outputStream.writeBytes("exit\n");
                    outputStream.flush();
                    if (Utility.getTheme(getActivity().getApplicationContext()) == 1) {

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
                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.sucessreboot)).withBackgroundColorId(R.color.textview1good).show();
                            }
                        }, 4000);
                    }
                    if (Utility.getTheme(getActivity().getApplicationContext()) == 2) {

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
                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.sucessreboot)).withBackgroundColorId(R.color.textview1good).show();
                            }
                        }, 4000);
                    }
                    if (Utility.getTheme(getActivity().getApplicationContext()) == 3) {

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
                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.sucessreboot)).withBackgroundColorId(R.color.textview1good).show();
                            }
                        }, 4000);
                    }
                } catch (IOException e) {
                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                }
            }
        });

        iranexecute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iranexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.oksmall), null, null, null);
                indiaexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                indonesiaexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                ruexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                ukexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                beexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                kzexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                slovexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                canexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                gerexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                morrocoexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                unitedkexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                standartexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
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
                    outputStream.writeBytes("rm -f /system/etc/gps.conf\n");
                    outputStream.flush();
                    outputStream.writeBytes("rm -f /system/etc/SystemTweaker/*\n");
                    outputStream.flush();
                    outputStream.writeBytes("cp /data/data/com.nowenui.systemtweaker/files/az.conf /system/etc/gps.conf\n");
                    outputStream.flush();
                    outputStream.writeBytes("chmod 644 /system/etc/gps.conf\n");
                    outputStream.flush();
                    outputStream.writeBytes("mkdir /system/etc/SystemTweaker\n");
                    outputStream.flush();
                    outputStream.writeBytes("chmod 755 /system/etc/SystemTweaker\n");
                    outputStream.flush();
                    outputStream.writeBytes("echo \"EXCUTED\" > /system/etc/SystemTweaker/az\n");
                    outputStream.flush();
                    outputStream.writeBytes("chmod 777 /system/etc/SystemTweaker/az\n");
                    outputStream.flush();
                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system\n");
                    outputStream.flush();
                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system\n");
                    outputStream.flush();
                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system\n");
                    outputStream.flush();
                    outputStream.writeBytes("exit\n");
                    outputStream.flush();
                    if (Utility.getTheme(getActivity().getApplicationContext()) == 1) {

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
                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.sucessreboot)).withBackgroundColorId(R.color.textview1good).show();
                            }
                        }, 4000);
                    }
                    if (Utility.getTheme(getActivity().getApplicationContext()) == 2) {

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
                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.sucessreboot)).withBackgroundColorId(R.color.textview1good).show();
                            }
                        }, 4000);
                    }
                    if (Utility.getTheme(getActivity().getApplicationContext()) == 3) {

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
                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.sucessreboot)).withBackgroundColorId(R.color.textview1good).show();
                            }
                        }, 4000);
                    }
                } catch (IOException e) {
                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                }
            }
        });

        indiaexecute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                indiaexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.oksmall), null, null, null);
                indonesiaexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                beexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                ruexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                ukexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                standartexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                kzexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                slovexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                canexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                gerexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                morrocoexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                unitedkexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                iranexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
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
                    outputStream.writeBytes("rm -f /system/etc/gps.conf\n");
                    outputStream.flush();
                    outputStream.writeBytes("rm -f /system/etc/SystemTweaker/*\n");
                    outputStream.flush();
                    outputStream.writeBytes("cp /data/data/com.nowenui.systemtweaker/files/india.conf /system/etc/gps.conf\n");
                    outputStream.flush();
                    outputStream.writeBytes("chmod 644 /system/etc/gps.conf\n");
                    outputStream.flush();
                    outputStream.writeBytes("mkdir /system/etc/SystemTweaker\n");
                    outputStream.flush();
                    outputStream.writeBytes("chmod 755 /system/etc/SystemTweaker\n");
                    outputStream.flush();
                    outputStream.writeBytes("echo \"EXCUTED\" > /system/etc/SystemTweaker/india\n");
                    outputStream.flush();
                    outputStream.writeBytes("chmod 777 /system/etc/SystemTweaker/india\n");
                    outputStream.flush();
                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system\n");
                    outputStream.flush();
                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system\n");
                    outputStream.flush();
                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system\n");
                    outputStream.flush();
                    outputStream.writeBytes("exit\n");
                    outputStream.flush();
                    if (Utility.getTheme(getActivity().getApplicationContext()) == 1) {

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
                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.sucessreboot)).withBackgroundColorId(R.color.textview1good).show();
                            }
                        }, 4000);
                    }
                    if (Utility.getTheme(getActivity().getApplicationContext()) == 2) {

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
                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.sucessreboot)).withBackgroundColorId(R.color.textview1good).show();
                            }
                        }, 4000);
                    }
                    if (Utility.getTheme(getActivity().getApplicationContext()) == 3) {

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
                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.sucessreboot)).withBackgroundColorId(R.color.textview1good).show();
                            }
                        }, 4000);
                    }
                } catch (IOException e) {
                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                }
            }
        });

        indonesiaexecute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                indonesiaexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.oksmall), null, null, null);
                indiaexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                beexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                ruexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                ukexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                standartexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                kzexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                slovexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                canexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                gerexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                morrocoexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                unitedkexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
                iranexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notok), null, null, null);
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
                    outputStream.writeBytes("rm -f /system/etc/gps.conf\n");
                    outputStream.flush();
                    outputStream.writeBytes("rm -f /system/etc/SystemTweaker/*\n");
                    outputStream.flush();
                    outputStream.writeBytes("cp /data/data/com.nowenui.systemtweaker/files/indonesia.conf /system/etc/gps.conf\n");
                    outputStream.flush();
                    outputStream.writeBytes("chmod 644 /system/etc/gps.conf\n");
                    outputStream.flush();
                    outputStream.writeBytes("mkdir /system/etc/SystemTweaker\n");
                    outputStream.flush();
                    outputStream.writeBytes("chmod 755 /system/etc/SystemTweaker\n");
                    outputStream.flush();
                    outputStream.writeBytes("echo \"EXCUTED\" > /system/etc/SystemTweaker/indonesia\n");
                    outputStream.flush();
                    outputStream.writeBytes("chmod 777 /system/etc/SystemTweaker/indonesia\n");
                    outputStream.flush();
                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system\n");
                    outputStream.flush();
                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system\n");
                    outputStream.flush();
                    outputStream.writeBytes("/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system\n");
                    outputStream.flush();
                    outputStream.writeBytes("exit\n");
                    outputStream.flush();
                    if (Utility.getTheme(getActivity().getApplicationContext()) == 1) {

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
                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.sucessreboot)).withBackgroundColorId(R.color.textview1good).show();
                            }
                        }, 4000);
                    }
                    if (Utility.getTheme(getActivity().getApplicationContext()) == 2) {

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
                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.sucessreboot)).withBackgroundColorId(R.color.textview1good).show();
                            }
                        }, 4000);
                    }
                    if (Utility.getTheme(getActivity().getApplicationContext()) == 3) {

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
                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.sucessreboot)).withBackgroundColorId(R.color.textview1good).show();
                            }
                        }, 4000);
                    }
                } catch (IOException e) {
                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                }
            }
        });

    }


}