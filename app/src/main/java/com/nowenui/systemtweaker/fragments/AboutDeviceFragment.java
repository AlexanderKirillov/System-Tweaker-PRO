package com.nowenui.systemtweaker.fragments;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.github.mrengineer13.snackbar.SnackBar;
import com.jaredrummler.android.device.DeviceName;
import com.nowenui.systemtweaker.HelperClass;
import com.nowenui.systemtweaker.R;
import com.stericson.RootShell.exceptions.RootDeniedException;
import com.stericson.RootShell.execution.Command;
import com.stericson.RootTools.RootTools;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

import github.nisrulz.easydeviceinfo.base.EasyBatteryMod;
import github.nisrulz.easydeviceinfo.base.EasyDeviceMod;
import github.nisrulz.easydeviceinfo.base.EasyDisplayMod;
import github.nisrulz.easydeviceinfo.base.EasyIdMod;

public class AboutDeviceFragment extends Fragment {

    private String BatteryHealth;

    public static AboutDeviceFragment newInstance(Bundle bundle) {
        AboutDeviceFragment AboutDevice = new AboutDeviceFragment();

        if (bundle != null) {
            AboutDevice.setArguments(bundle);
        }

        return AboutDevice;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.about_device, parent, false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        EasyBatteryMod BatteryInfo = new EasyBatteryMod(getContext());
        EasyIdMod IdInfo = new EasyIdMod(getContext());
        EasyDisplayMod DisplayInfo = new EasyDisplayMod(getContext());
        HelperClass MemoryInfo = new HelperClass(getContext());
        EasyDeviceMod DeviceInfo = new EasyDeviceMod(getContext());

        TextView maininfo = view.findViewById(R.id.MainInformation);
        TextView processorinfo = view.findViewById(R.id.ProcessorInformation);
        TextView memoryinfo = view.findViewById(R.id.MemoryInformation);
        TextView batteryinfo = view.findViewById(R.id.BatteryInformation);
        TextView screeninfo = view.findViewById(R.id.ScreenInformation);
        Resources res = getResources();

        ///////////////////////////////////////////////
        ////// Push all information to user ///////////
        ///////////////////////////////////////////////
        maininfo.setText(
                res.getString(R.string.yourdevice) + " " + DeviceName.getDeviceName() + "\n" +
                        res.getString(R.string.model) + " " + android.os.Build.MODEL + "\n" +
                        res.getString(R.string.serial) + " " + android.os.Build.SERIAL + "\n" +
                        res.getString(R.string.cod) + " " + DeviceInfo.getDevice() + "\n" + "\n" +
                        res.getString(R.string.android) + " " + android.os.Build.VERSION.RELEASE + " " + DeviceInfo.getOSCodename() + "\n" +
                        res.getString(R.string.boot) + " " + android.os.Build.BOOTLOADER + " " + "\n" +
                        res.getString(R.string.radio) + " " + DeviceInfo.getRadioVer() + "\n" +
                        res.getString(R.string.rom) + " " + DeviceInfo.getBuildVersionIncremental() + "\n" +
                        res.getString(R.string.language) + " " + DeviceInfo.getLanguage().toUpperCase() + "\n" +
                        "• Android ID: " + IdInfo.getAndroidID() + "\n" +
                        "• PseudoID: " + IdInfo.getPseudoUniqueID() + "\n" +
                        res.getString(R.string.sdk) + " " + DeviceInfo.getBuildVersionSDK() + "\n");

        processorinfo.setText(
                res.getString(R.string.processor) + " " + HelperClass.getCPUName() + "\n" +
                        res.getString(R.string.processortype) + " " + android.os.Build.CPU_ABI + "\n");

        memoryinfo.setText(
                "• RAM: " + MemoryInfo.getTotalRAM() + " Mb" + "\n" +
                        "• ROM: " + MemoryInfo.getTotalInternalMemorySize() + " Mb" + "\n" +
                        "• Heap Size: " + HelperClass.getHeapSize().trim() + "\n");

        batteryinfo.setText(
                res.getString(R.string.batthealth) + " " + BatteryHealth + "\n" +
                        res.getString(R.string.chargerconn) + " " + BatteryInfo.isDeviceCharging() + "\n" +
                        res.getString(R.string.batterytype) + " " + BatteryInfo.getBatteryTechnology() + "\n" +
                        res.getString(R.string.voltage) + " " + BatteryInfo.getBatteryVoltage() + " mV" + "\n" +
                        res.getString(R.string.batterytemp) + " " + BatteryInfo.getBatteryTemperature() + " °C" + "\n" +
                        res.getString(R.string.levelbatt) + " " + BatteryInfo.getBatteryPercentage() + " %");

        screeninfo.setText(
                res.getString(R.string.resolution) + " " + DisplayInfo.getResolution() + "\n" +
                        "• DPI: " + DisplayInfo.getDensity() + "\n" +
                        res.getString(R.string.cll) + " " + Math.round(DisplayInfo.getPhysicalSize() * Math.pow(10, 3)) / Math.pow(10, 3) + "\n" +
                        "• Screen FPS: " + DisplayInfo.getRefreshRate() + "\n" +
                        "• Screen Display ID: " + DeviceInfo.getScreenDisplayID() + "\n");

        final Button buttoncheckinitd = view.findViewById(R.id.buttoncheckinitd);
        final Button buttoncheckinitdremove = view.findViewById(R.id.buttoncheckinitdremove);
        final TextView initdrealstatus = view.findViewById(R.id.initdrealstatus);

        /////////////////////////////////////
        ////// Get battery health ///////////
        /////////////////////////////////////
        @github.nisrulz.easydeviceinfo.base.BatteryHealth int batteryHealth = BatteryInfo.getBatteryHealth();

        switch (batteryHealth) {
            case github.nisrulz.easydeviceinfo.base.BatteryHealth.GOOD:
                BatteryHealth = res.getString(R.string.goodbatterystatus);
                break;
            case github.nisrulz.easydeviceinfo.base.BatteryHealth.HAVING_ISSUES:
                BatteryHealth = res.getString(R.string.problems);
                break;
            default:
                BatteryHealth = res.getString(R.string.problems);
                break;
        }

        ////////////////////////////////////////////
        ////// CHECK INIT.D ////////////////////////
        ///////////////////////////////////////////
        if (HelperClass.isInitdSupport() == 0) {
            buttoncheckinitd.setEnabled(false);
            buttoncheckinitd.setText(R.string.cant);
            buttoncheckinitd.setBackgroundResource(R.drawable.roundbuttonfuck);
            initdrealstatus.setVisibility(View.GONE);
            buttoncheckinitdremove.setVisibility(View.GONE);
        } else {
            buttoncheckinitd.setEnabled(true);
        }

        buttoncheckinitd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttoncheckinitd.setEnabled(false);
                buttoncheckinitd.setVisibility(View.GONE);
                buttoncheckinitdremove.setVisibility(View.VISIBLE);

                if (RootTools.isAccessGiven()) {
                    @SuppressLint("SdCardPath") Command checkinitd = new Command(0,
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                            "rm -f /data/checkinit.log",
                            "cp /data/data/com.nowenui.systemtweaker/files/88checkinit /system/etc/init.d/",
                            "chmod 777 /system/etc/init.d/88checkinit",
                            "reboot",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                    );
                    try {
                        RootTools.getShell(true).add(checkinitd);
                    } catch (IOException | RootDeniedException | TimeoutException ex) {
                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                    }
                } else {
                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                }
            }

        });

        buttoncheckinitdremove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttoncheckinitdremove.setEnabled(false);
                buttoncheckinitdremove.setVisibility(View.GONE);
                buttoncheckinitd.setVisibility(View.VISIBLE);
                if (RootTools.isAccessGiven()) {
                    @SuppressLint("SdCardPath") Command deletecheck = new Command(0,
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                            "rm -f /data/checkinit.log",
                            "rm -f /system/etc/init.d/88checkinit",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                    );
                    try {
                        RootTools.getShell(true).add(deletecheck);
                        initdrealstatus.setVisibility(View.GONE);
                    } catch (IOException | RootDeniedException | TimeoutException ex) {
                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                    }
                } else {
                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                }
            }

        });

        if (new File("/data/checkinit.log").exists()) {
            initdrealstatus.setText("INIT.D WORKING GOOD!");
            initdrealstatus.setTextColor(Color.GREEN);
            initdrealstatus.setTextSize(20);
            buttoncheckinitd.setVisibility(View.GONE);
        } else {
            initdrealstatus.setText(R.string.statusssl);
            initdrealstatus.setTextColor(Color.RED);
            initdrealstatus.setTextSize(20);
            buttoncheckinitdremove.setVisibility(View.GONE);
        }
    }

}