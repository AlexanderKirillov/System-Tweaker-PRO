package com.nowenui.systemtweaker.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mrengineer13.snackbar.SnackBar;
import com.nowenui.systemtweaker.AnimatedProgressButton;
import com.nowenui.systemtweaker.R;
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.onclick_booster, parent, false);
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {

        ///////////////////////////////////////////////
        ////// OneClick maintenance button ////////////
        //////////////////////////////////////////////

        final FabButton oneclickoptimize = view.findViewById(R.id.oneclickoptimize);
        final AnimatedProgressButton animateprogress = new AnimatedProgressButton(oneclickoptimize, getActivity());

        oneclickoptimize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isClicked) {
                    return;
                }
                animateprogress.startDeterminate();
                isClicked = true;
                v.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        isClicked = false;
                    }
                }, 2000);

                if (RootTools.isAccessGiven()) {
                    @SuppressLint("SdCardPath") Command oneclickoptimizecommand = new Command(0,
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /data",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /data", "mount -o rw,remount /data",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /data",
                            "/data/data/com.nowenui.systemtweaker/files/11sqlite",
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
                        RootTools.getShell(true).add(oneclickoptimizecommand);
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
                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.speedsucc)).withBackgroundColorId(R.color.resultgood).show();
                                new CountDownTimer(3000, 1000) {

                                    public void onTick(long millisUntilFinished) {
                                        oneclickoptimize.setEnabled(false);
                                    }

                                    public void onFinish() {
                                        oneclickoptimize.setEnabled(false);
                                        TextView sucessspeed = view.findViewById(R.id.sucessspeed);
                                        sucessspeed.setVisibility(View.VISIBLE);
                                        oneclickoptimize.setColor(Color.parseColor("#828282"));
                                        oneclickoptimize.setProgress(0);
                                    }
                                }.start();
                            }
                        }, 7000);
                    } catch (IOException | RootDeniedException | TimeoutException ex) {
                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                    }
                }
            }

        });
    }
}
