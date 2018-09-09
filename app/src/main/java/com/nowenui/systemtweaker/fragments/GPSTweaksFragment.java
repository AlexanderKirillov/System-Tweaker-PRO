package com.nowenui.systemtweaker.fragments;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
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

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.gps_booster, parent, false);
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
        final Button bangladeshexecute = view.findViewById(R.id.bandgladesh);
        final Button standartexecute = view.findViewById(R.id.standart);

        File systemtweakerpath = new File("/system/etc/SystemTweaker");

        if (!(systemtweakerpath.exists()) && !(systemtweakerpath.isDirectory())) {
            standartexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.okeysmalllogo), null, null, null);
        }

        /////////////////////////
        ////// Russia ///////////
        /////////////////////////
        if (new File("/system/etc/SystemTweaker/russia").exists()) {
            ruexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.okeysmalllogo), null, null, null);
        }

        ///////////////////////////////
        ////// Morocco ////////////////
        ///////////////////////////////
        if (new File("/system/etc/SystemTweaker/mor").exists()) {
            morrocoexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.okeysmalllogo), null, null, null);
        }

        /////////////////////////////
        ////// Kazakhstan ///////////
        /////////////////////////////
        if (new File("/system/etc/SystemTweaker/kz").exists()) {
            kzexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.okeysmalllogo), null, null, null);
        }

        /////////////////////////
        ////// Ukraine //////////
        /////////////////////////
        if (new File("/system/etc/SystemTweaker/ukraine").exists()) {
            ukexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.okeysmalllogo), null, null, null);
            ukexecute.setEnabled(false);
        }

        ///////////////////////////
        ////// Slovakia ///////////
        ///////////////////////////
        if (new File("/system/etc/SystemTweaker/slovakia").exists()) {
            slovexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.okeysmalllogo), null, null, null);
        }

        /////////////////////////
        ////// Canada ///////////
        /////////////////////////
        if (new File("/system/etc/SystemTweaker/canada").exists()) {
            canexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.okeysmalllogo), null, null, null);
        }

        //////////////////////////
        ////// Germany ///////////
        //////////////////////////
        if (new File("/system/etc/SystemTweaker/germany").exists()) {
            gerexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.okeysmalllogo), null, null, null);
        }

        //////////////////////////
        ////// Belarus ///////////
        //////////////////////////
        if (new File("/system/etc/SystemTweaker/belarus").exists()) {
            beexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.okeysmalllogo), null, null, null);
        }

        /////////////////////
        ////// UK ///////////
        /////////////////////
        if (new File("/system/etc/SystemTweaker/uk").exists()) {
            unitedkexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.okeysmalllogo), null, null, null);
        }

        ///////////////////////////////////////
        ////// Iran/Iraq/Azerbaijan ///////////
        ///////////////////////////////////////
        if (new File("/system/etc/SystemTweaker/iran").exists()) {
            iranexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.okeysmalllogo), null, null, null);
        }

        ///////////////////////////////////////
        ////// India //////////////////////////
        ///////////////////////////////////////
        if (new File("/system/etc/SystemTweaker/india").exists()) {
            indiaexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.okeysmalllogo), null, null, null);
        }

        ///////////////////////////////////////
        ////// Bangladesh /////////////////////
        ///////////////////////////////////////
        if (new File("/system/etc/SystemTweaker/bangladesh").exists()) {
            bangladeshexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.okeysmalllogo), null, null, null);
        }

        ///////////////////////////////////////
        ////// Indonesia //////////////////////
        ///////////////////////////////////////
        if (new File("/system/etc/SystemTweaker/indonesia").exists()) {
            indonesiaexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.okeysmalllogo), null, null, null);
        }

        standartexecute.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SdCardPath")
            @Override
            public void onClick(View v) {
                standartexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.okeysmalllogo), null, null, null);
                indiaexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                indonesiaexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                ruexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                ukexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                beexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                kzexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                slovexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                canexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                gerexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                morrocoexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                unitedkexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                iranexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                indiaexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                bangladeshexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);

                if (RootTools.isAccessGiven()) {
                    Command installgpsfile = new Command(0,
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                            "rm -rf /system/etc/SystemTweaker",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                    );
                    try {
                        RootTools.getShell(true).add(installgpsfile);
                        DialogAfterinstalling();
                    } catch (IOException | RootDeniedException | TimeoutException ex) {
                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                    }
                } else {
                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.erroroot)).withBackgroundColorId(R.color.resultbad).show();
                }
            }
        });

        ruexecute.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SdCardPath")
            @Override
            public void onClick(View v) {
                ruexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.okeysmalllogo), null, null, null);
                indiaexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                standartexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                indonesiaexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                ukexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                beexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                kzexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                slovexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                canexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                gerexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                morrocoexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                unitedkexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                iranexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                bangladeshexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);

                if (RootTools.isAccessGiven()) {
                    Command installgpsfile = new Command(0,
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                            "rm -f /system/etc/gps.conf",
                            "rm -f /system/etc/SystemTweaker/*",
                            "cp /data/data/com.nowenui.systemtweaker/files/russia.conf /system/etc/gps.conf",
                            "chmod 644 /system/etc/gps.conf",
                            "mkdir /system/etc/SystemTweaker",
                            "chmod 755 /system/etc/SystemTweaker",
                            "echo \"EXCUTED\" > /system/etc/SystemTweaker/russia",
                            "chmod 777 /system/etc/SystemTweaker/russia",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                    );
                    try {
                        RootTools.getShell(true).add(installgpsfile);
                        DialogAfterinstalling();
                    } catch (IOException | RootDeniedException | TimeoutException ex) {
                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                    }
                } else {
                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.erroroot)).withBackgroundColorId(R.color.resultbad).show();
                }
            }
        });

        beexecute.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SdCardPath")
            @Override
            public void onClick(View v) {
                beexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.okeysmalllogo), null, null, null);
                indiaexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                indonesiaexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                ruexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                ukexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                standartexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                kzexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                slovexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                canexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                gerexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                morrocoexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                unitedkexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                iranexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                bangladeshexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);

                if (RootTools.isAccessGiven()) {
                    Command installgpsfile = new Command(0,
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                            "rm -f /system/etc/gps.conf",
                            "rm -f /system/etc/SystemTweaker/*",
                            "cp /data/data/com.nowenui.systemtweaker/files/belarus.conf /system/etc/gps.conf",
                            "chmod 644 /system/etc/gps.conf",
                            "mkdir /system/etc/SystemTweaker",
                            "chmod 755 /system/etc/SystemTweaker",
                            "echo \"EXCUTED\" > /system/etc/SystemTweaker/belarus",
                            "chmod 777 /system/etc/SystemTweaker/belarus",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                    );
                    try {
                        RootTools.getShell(true).add(installgpsfile);
                        DialogAfterinstalling();
                    } catch (IOException | RootDeniedException | TimeoutException ex) {
                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                    }
                } else {
                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.erroroot)).withBackgroundColorId(R.color.resultbad).show();
                }
            }
        });

        kzexecute.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SdCardPath")
            @Override
            public void onClick(View v) {
                kzexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.okeysmalllogo), null, null, null);
                indiaexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                indonesiaexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                ruexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                ukexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                beexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                standartexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                slovexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                canexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                gerexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                morrocoexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                unitedkexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                iranexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                bangladeshexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);

                if (RootTools.isAccessGiven()) {
                    Command installgpsfile = new Command(0,
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                            "rm -f /system/etc/gps.conf",
                            "rm -f /system/etc/SystemTweaker/*",
                            "cp /data/data/com.nowenui.systemtweaker/files/kazahstan.conf /system/etc/gps.conf",
                            "chmod 644 /system/etc/gps.conf",
                            "mkdir /system/etc/SystemTweaker",
                            "chmod 755 /system/etc/SystemTweaker",
                            "echo \"EXCUTED\" > /system/etc/SystemTweaker/kz",
                            "chmod 777 /system/etc/SystemTweaker/kz",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                    );
                    try {
                        RootTools.getShell(true).add(installgpsfile);
                        DialogAfterinstalling();
                    } catch (IOException | RootDeniedException | TimeoutException ex) {
                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                    }
                } else {
                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.erroroot)).withBackgroundColorId(R.color.resultbad).show();
                }
            }
        });

        slovexecute.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SdCardPath")
            @Override
            public void onClick(View v) {
                slovexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.okeysmalllogo), null, null, null);
                indiaexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                indonesiaexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                ruexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                ukexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                beexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                kzexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                standartexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                canexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                gerexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                morrocoexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                unitedkexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                iranexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                bangladeshexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);

                if (RootTools.isAccessGiven()) {
                    Command installgpsfile = new Command(0,
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                            "rm -f /system/etc/gps.conf",
                            "rm -f /system/etc/SystemTweaker/*",
                            "cp /data/data/com.nowenui.systemtweaker/files/slovakia.conf /system/etc/gps.conf",
                            "chmod 644 /system/etc/gps.conf",
                            "mkdir /system/etc/SystemTweaker",
                            "chmod 755 /system/etc/SystemTweaker",
                            "echo \"EXCUTED\" > /system/etc/SystemTweaker/slovakia",
                            "chmod 777 /system/etc/SystemTweaker/slovakia",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                    );
                    try {
                        RootTools.getShell(true).add(installgpsfile);
                        DialogAfterinstalling();
                    } catch (IOException | RootDeniedException | TimeoutException ex) {
                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                    }
                } else {
                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.erroroot)).withBackgroundColorId(R.color.resultbad).show();
                }
            }
        });

        canexecute.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SdCardPath")
            @Override
            public void onClick(View v) {
                canexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.okeysmalllogo), null, null, null);
                indiaexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                indonesiaexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                ruexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                ukexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                beexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                kzexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                slovexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                standartexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                gerexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                morrocoexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                unitedkexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                iranexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                bangladeshexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);

                if (RootTools.isAccessGiven()) {
                    Command installgpsfile = new Command(0,
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                            "rm -f /system/etc/gps.conf",
                            "rm -f /system/etc/SystemTweaker/*",
                            "cp /data/data/com.nowenui.systemtweaker/files/canada.conf /system/etc/gps.conf",
                            "chmod 644 /system/etc/gps.conf",
                            "mkdir /system/etc/SystemTweaker",
                            "chmod 755 /system/etc/SystemTweaker",
                            "echo \"EXCUTED\" > /system/etc/SystemTweaker/canada",
                            "chmod 777 /system/etc/SystemTweaker/canada",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                    );
                    try {
                        RootTools.getShell(true).add(installgpsfile);
                        DialogAfterinstalling();
                    } catch (IOException | RootDeniedException | TimeoutException ex) {
                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                    }
                } else {
                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.erroroot)).withBackgroundColorId(R.color.resultbad).show();
                }
            }
        });

        ukexecute.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SdCardPath")
            @Override
            public void onClick(View v) {
                ukexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.okeysmalllogo), null, null, null);
                indiaexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                ruexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                indonesiaexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                standartexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                beexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                kzexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                slovexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                canexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                gerexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                morrocoexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                unitedkexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                iranexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                bangladeshexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);

                if (RootTools.isAccessGiven()) {
                    Command installgpsfile = new Command(0,
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                            "rm -f /system/etc/gps.conf",
                            "rm -f /system/etc/SystemTweaker/*",
                            "cp /data/data/com.nowenui.systemtweaker/files/ukraine.conf /system/etc/gps.conf",
                            "chmod 644 /system/etc/gps.conf",
                            "mkdir /system/etc/SystemTweaker",
                            "chmod 755 /system/etc/SystemTweaker",
                            "echo \"EXCUTED\" > /system/etc/SystemTweaker/ukraine",
                            "chmod 777 /system/etc/SystemTweaker/ukraine",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                    );
                    try {
                        RootTools.getShell(true).add(installgpsfile);
                        DialogAfterinstalling();
                    } catch (IOException | RootDeniedException | TimeoutException ex) {
                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                    }
                } else {
                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.erroroot)).withBackgroundColorId(R.color.resultbad).show();
                }
            }
        });

        gerexecute.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SdCardPath")
            @Override
            public void onClick(View v) {
                gerexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.okeysmalllogo), null, null, null);
                indiaexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                ruexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                indonesiaexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                ukexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                beexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                kzexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                slovexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                canexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                standartexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                morrocoexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                unitedkexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                iranexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                bangladeshexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);

                if (RootTools.isAccessGiven()) {
                    Command installgpsfile = new Command(0,
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                            "rm -f /system/etc/gps.conf",
                            "rm -f /system/etc/SystemTweaker/*",
                            "cp /data/data/com.nowenui.systemtweaker/files/germany.conf /system/etc/gps.conf",
                            "chmod 644 /system/etc/gps.conf",
                            "mkdir /system/etc/SystemTweaker",
                            "chmod 755 /system/etc/SystemTweaker",
                            "echo \"EXCUTED\" > /system/etc/SystemTweaker/germany",
                            "chmod 777 /system/etc/SystemTweaker/germany",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                    );
                    try {
                        RootTools.getShell(true).add(installgpsfile);
                        DialogAfterinstalling();
                    } catch (IOException | RootDeniedException | TimeoutException ex) {
                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                    }
                } else {
                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.erroroot)).withBackgroundColorId(R.color.resultbad).show();
                }
            }
        });

        morrocoexecute.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SdCardPath")
            @Override
            public void onClick(View v) {
                morrocoexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.okeysmalllogo), null, null, null);
                indiaexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                indonesiaexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                ruexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                ukexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                beexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                kzexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                slovexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                canexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                gerexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                standartexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                unitedkexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                iranexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                bangladeshexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);

                if (RootTools.isAccessGiven()) {
                    Command installgpsfile = new Command(0,
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                            "rm -f /system/etc/gps.conf",
                            "rm -f /system/etc/SystemTweaker/*",
                            "cp /data/data/com.nowenui.systemtweaker/files/morokko.conf /system/etc/gps.conf",
                            "chmod 644 /system/etc/gps.conf",
                            "mkdir /system/etc/SystemTweaker",
                            "chmod 755 /system/etc/SystemTweaker",
                            "echo \"EXCUTED\" > /system/etc/SystemTweaker/mor",
                            "chmod 777 /system/etc/SystemTweaker/mor",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                    );
                    try {
                        RootTools.getShell(true).add(installgpsfile);
                        DialogAfterinstalling();
                    } catch (IOException | RootDeniedException | TimeoutException ex) {
                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                    }
                } else {
                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.erroroot)).withBackgroundColorId(R.color.resultbad).show();
                }
            }
        });

        unitedkexecute.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SdCardPath")
            @Override
            public void onClick(View v) {
                unitedkexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.okeysmalllogo), null, null, null);
                indiaexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                ruexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                indonesiaexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                ukexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                beexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                kzexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                slovexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                canexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                gerexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                morrocoexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                standartexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                iranexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                bangladeshexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);

                if (RootTools.isAccessGiven()) {
                    Command installgpsfile = new Command(0,
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                            "rm -f /system/etc/gps.conf",
                            "rm -f /system/etc/SystemTweaker/*",
                            "cp /data/data/com.nowenui.systemtweaker/files/britain.conf /system/etc/gps.conf",
                            "chmod 644 /system/etc/gps.conf",
                            "mkdir /system/etc/SystemTweaker",
                            "chmod 755 /system/etc/SystemTweaker",
                            "echo \"EXCUTED\" > /system/etc/SystemTweaker/uk",
                            "chmod 777 /system/etc/SystemTweaker/uk",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                    );
                    try {
                        RootTools.getShell(true).add(installgpsfile);
                        DialogAfterinstalling();
                    } catch (IOException | RootDeniedException | TimeoutException ex) {
                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                    }
                } else {
                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.erroroot)).withBackgroundColorId(R.color.resultbad).show();
                }
            }
        });

        iranexecute.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SdCardPath")
            @Override
            public void onClick(View v) {
                iranexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.okeysmalllogo), null, null, null);
                indiaexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                indonesiaexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                ruexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                ukexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                beexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                kzexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                slovexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                canexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                gerexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                morrocoexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                unitedkexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                standartexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                bangladeshexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);

                if (RootTools.isAccessGiven()) {
                    Command installgpsfile = new Command(0,
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                            "rm -f /system/etc/gps.conf",
                            "rm -f /system/etc/SystemTweaker/*",
                            "cp /data/data/com.nowenui.systemtweaker/files/iran.conf /system/etc/gps.conf",
                            "chmod 644 /system/etc/gps.conf",
                            "mkdir /system/etc/SystemTweaker",
                            "chmod 755 /system/etc/SystemTweaker",
                            "echo \"EXCUTED\" > /system/etc/SystemTweaker/iran",
                            "chmod 777 /system/etc/SystemTweaker/iran",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                    );
                    try {
                        RootTools.getShell(true).add(installgpsfile);
                        DialogAfterinstalling();
                    } catch (IOException | RootDeniedException | TimeoutException ex) {
                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                    }
                } else {
                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.erroroot)).withBackgroundColorId(R.color.resultbad).show();
                }
            }
        });

        indiaexecute.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SdCardPath")
            @Override
            public void onClick(View v) {
                indiaexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.okeysmalllogo), null, null, null);
                indonesiaexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                beexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                ruexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                ukexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                standartexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                kzexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                slovexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                canexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                gerexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                morrocoexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                unitedkexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                iranexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                bangladeshexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);

                if (RootTools.isAccessGiven()) {
                    Command installgpsfile = new Command(0,
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                            "rm -f /system/etc/gps.conf",
                            "rm -f /system/etc/SystemTweaker/*",
                            "cp /data/data/com.nowenui.systemtweaker/files/india.conf /system/etc/gps.conf",
                            "chmod 644 /system/etc/gps.conf",
                            "mkdir /system/etc/SystemTweaker",
                            "chmod 755 /system/etc/SystemTweaker",
                            "echo \"EXCUTED\" > /system/etc/SystemTweaker/india",
                            "chmod 777 /system/etc/SystemTweaker/india",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                    );
                    try {
                        RootTools.getShell(true).add(installgpsfile);
                        DialogAfterinstalling();
                    } catch (IOException | RootDeniedException | TimeoutException ex) {
                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                    }
                } else {
                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.erroroot)).withBackgroundColorId(R.color.resultbad).show();
                }
            }
        });

        indonesiaexecute.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SdCardPath")
            @Override
            public void onClick(View v) {
                indonesiaexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.okeysmalllogo), null, null, null);
                indiaexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                beexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                ruexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                ukexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                standartexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                kzexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                slovexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                canexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                gerexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                morrocoexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                unitedkexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                iranexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                bangladeshexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);

                if (RootTools.isAccessGiven()) {
                    Command installgpsfile = new Command(0,
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                            "rm -f /system/etc/gps.conf",
                            "rm -f /system/etc/SystemTweaker/*",
                            "cp /data/data/com.nowenui.systemtweaker/files/indonesia.conf /system/etc/gps.conf",
                            "chmod 644 /system/etc/gps.conf",
                            "mkdir /system/etc/SystemTweaker",
                            "chmod 755 /system/etc/SystemTweaker",
                            "echo \"EXCUTED\" > /system/etc/SystemTweaker/indonesia",
                            "chmod 777 /system/etc/SystemTweaker/indonesia",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                    );
                    try {
                        RootTools.getShell(true).add(installgpsfile);
                        DialogAfterinstalling();
                    } catch (IOException | RootDeniedException | TimeoutException ex) {
                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                    }
                } else {
                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.erroroot)).withBackgroundColorId(R.color.resultbad).show();
                }
            }
        });

        bangladeshexecute.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SdCardPath")
            @Override
            public void onClick(View v) {
                bangladeshexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.okeysmalllogo), null, null, null);
                indiaexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                beexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                ruexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                ukexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                standartexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                kzexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                slovexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                canexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                gerexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                morrocoexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                unitedkexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                iranexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);
                indonesiaexecute.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.notgood), null, null, null);

                if (RootTools.isAccessGiven()) {
                    Command installgpsfile = new Command(0,
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                            "rm -f /system/etc/gps.conf",
                            "rm -f /system/etc/SystemTweaker/*",
                            "cp /data/data/com.nowenui.systemtweaker/files/bangladesh.conf /system/etc/gps.conf",
                            "chmod 644 /system/etc/gps.conf",
                            "mkdir /system/etc/SystemTweaker",
                            "chmod 755 /system/etc/SystemTweaker",
                            "echo \"EXCUTED\" > /system/etc/SystemTweaker/bangladesh",
                            "chmod 777 /system/etc/SystemTweaker/bangladesh",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                    );
                    try {
                        RootTools.getShell(true).add(installgpsfile);
                        DialogAfterinstalling();
                    } catch (IOException | RootDeniedException | TimeoutException ex) {
                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                    }
                } else {
                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.erroroot)).withBackgroundColorId(R.color.resultbad).show();
                }
            }
        });
    }

    public void DialogAfterinstalling() {
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
                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.sucessreboot)).withBackgroundColorId(R.color.resultgood).show();
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
                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.sucessreboot)).withBackgroundColorId(R.color.resultgood).show();
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
                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.sucessreboot)).withBackgroundColorId(R.color.resultgood).show();
                }
            }, 4000);
        }
    }


}