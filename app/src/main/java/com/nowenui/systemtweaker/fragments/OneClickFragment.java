package com.nowenui.systemtweaker.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mrengineer13.snackbar.SnackBar;
import com.nowenui.systemtweaker.ProgressHelper;
import com.nowenui.systemtweaker.R;
import com.nowenui.systemtweaker.Utility;
import com.stericson.RootShell.exceptions.RootDeniedException;
import com.stericson.RootShell.execution.Command;
import com.stericson.RootTools.RootTools;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import mbanje.kurt.fabbutton.FabButton;

public class OneClickFragment extends Fragment {

    AlertDialog.Builder dialogBuilder;
    AlertDialog alertDialog;
    private boolean isClicked;

    public static OneClickFragment newInstance(Bundle bundle) {
        OneClickFragment OneClickService = new OneClickFragment();

        if (bundle != null) {
            OneClickService.setArguments(bundle);
        }

        return OneClickService;
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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_onclick, parent, false);
    }


    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {

        TextView whatdo1 = view.findViewById(R.id.whatdo1);
        TextView whatdo2 = view.findViewById(R.id.whatdo2);
        TextView whatdo3 = view.findViewById(R.id.whatdo3);
        TextView whatdo4 = view.findViewById(R.id.whatdo4);
        TextView whatdo5 = view.findViewById(R.id.whatdo5);
        TextView whatdo6 = view.findViewById(R.id.whatdo6);

        whatdo1.setBackgroundResource(R.drawable.roundbuttonmainbefore);
        whatdo1.setTextSize(15);
        whatdo2.setBackgroundResource(R.drawable.roundbuttonmainbefore);
        whatdo2.setTextSize(15);
        whatdo3.setBackgroundResource(R.drawable.roundbuttonmainbefore);
        whatdo3.setTextSize(15);
        whatdo4.setBackgroundResource(R.drawable.roundbuttonmainbefore);
        whatdo4.setTextSize(15);
        whatdo5.setBackgroundResource(R.drawable.roundbuttonmainbefore);
        whatdo5.setTextSize(15);
        whatdo6.setBackgroundResource(R.drawable.roundbuttonmainbefore);
        whatdo6.setTextSize(15);

        ///////////////////////////////////////////////
        ////// OneClick maintenance button ////////////
        //////////////////////////////////////////////

        final FabButton optimize = view.findViewById(R.id.optimize);
        final ProgressHelper helper = new ProgressHelper(optimize, getActivity());

        view.findViewById(R.id.optimize).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isClicked) {
                    return;
                }
                helper.startDeterminate();
                isClicked = true;
                v.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        isClicked = false;
                    }
                }, 2000);

                if (RootTools.isAccessGiven()) {
                    Command command5 = new Command(0,
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /data",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /data", "mount -o rw,remount /data",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /data",
                            "/data/data/com.nowenui.systemtweaker/files/11sqlite",
                            "/data/data/com.nowenui.systemtweaker/files/io_tweak",
                            "/data/data/com.nowenui.systemtweaker/files/11NSTweak",
                            "/data/data/com.nowenui.systemtweaker/files/12VMTweak",
                            "/data/data/com.nowenui.systemtweaker/files/NowenUI",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /data",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /data", "mount -o ro,remount /data",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /data");

                    try {
                        RootTools.getShell(true).add(command5);

                        dialogBuilder = new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark));
                        dialogBuilder.setView(null);
                        alertDialog = dialogBuilder.create();
                        alertDialog.setCancelable(false);
                        alertDialog.show();
                        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        alertDialog.getWindow().setDimAmount(0);

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                alertDialog.dismiss();
                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.speedsucc)).withBackgroundColorId(R.color.textview1good).show();
                                new CountDownTimer(3000, 1000) {

                                    public void onTick(long millisUntilFinished) {
                                        optimize.setEnabled(false);
                                    }

                                    public void onFinish() {
                                        optimize.setEnabled(false);
                                        TextView sucessspeed = view.findViewById(R.id.sucessspeed);
                                        sucessspeed.setVisibility(View.VISIBLE);
                                        optimize.setColor(Color.parseColor("#828282"));
                                        optimize.setProgress(0);
                                    }
                                }.start();
                            }
                        }, 7000);


                    } catch (IOException | RootDeniedException | TimeoutException ex) {
                        ex.printStackTrace();
                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                    }
                }
            }

        });

    }
}
