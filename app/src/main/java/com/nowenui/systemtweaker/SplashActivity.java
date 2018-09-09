package com.nowenui.systemtweaker;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.franmontiel.localechanger.LocaleChanger;
import com.stericson.RootShell.exceptions.RootDeniedException;
import com.stericson.RootShell.execution.Command;
import com.stericson.RootTools.RootTools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.TimeoutException;

public class SplashActivity extends AppCompatActivity {
    public static int checksu, selinuxstatus;
    boolean doubleBackToExitPressedOnce = false;

    ///////////////////////////////////////////////////////////////////////////////////
    ////// Show dialog with error, if SELinux is not Permissive ///////////////////////
    ///////////////////////////////////////////////////////////////////////////////////
    public void BAD_SELINUX_DIALOG() {
        final Dialog BAD_SELINUX = new Dialog(SplashActivity.this);
        BAD_SELINUX.setCancelable(false);
        BAD_SELINUX.setContentView(R.layout.bad_selinux_dialog);
        final SharedPreferences BD = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        Button download_selinux_patcher = BAD_SELINUX.findViewById(R.id.download_selinux_patcher);
        download_selinux_patcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.dropbox.com/s/6b71rnsx78rs48z/SELinux-Perm.zip?dl=0"));
                startActivity(browserIntent);
            }
        });
        Button download_kernel_permissive_patcher = BAD_SELINUX.findViewById(R.id.download_kernel_permissive_patcher);
        download_kernel_permissive_patcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.dropbox.com/s/90npo1p0oc2flmh/kernel_permissive_patcher.zip?dl=0"));
                startActivity(browserIntent);
            }
        });
        Button skip = BAD_SELINUX.findViewById(R.id.skippp);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BAD_SELINUX.dismiss();
                InitD_ShowDialogOrNot();
            }
        });
        Button skipandsave = BAD_SELINUX.findViewById(R.id.skipppandsave);
        skipandsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor BDEditor = BD.edit();
                BDEditor.putString("skip_selinux_check", "skip_selinux_check");
                BDEditor.apply();
                BAD_SELINUX.dismiss();
                InitD_ShowDialogOrNot();
            }
        });
        Button exit = BAD_SELINUX.findViewById(R.id.exitttt);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                SplashActivity.this.finish();
                System.exit(0);
            }
        });
        BAD_SELINUX.show();
    }

    ////////////////////////////////////////////////////////////////////////////////
    ////// Show dialog with error, if init.d isn't supported ///////////////////////
    ////////////////////////////////////////////////////////////////////////////////
    public void NO_INITD_DIALOG() {
        final Dialog NO_INITD = new Dialog(SplashActivity.this);
        NO_INITD.setCancelable(false);
        NO_INITD.setContentView(R.layout.no_initd_support_dialog);
        final SharedPreferences BD = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Button download_initd_patcher = NO_INITD.findViewById(R.id.download_initd_patcher);
        download_initd_patcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.dropbox.com/s/vbhtx4ohs8yyd5o/Init.d_Patcher.zip?dl=0"));
                startActivity(browserIntent);
            }
        });
        Button skip = NO_INITD.findViewById(R.id.skippp);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NO_INITD.dismiss();
                startMainActivity();
            }
        });
        Button skipandsave = NO_INITD.findViewById(R.id.skipppandsave);
        skipandsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor BDEditor = BD.edit();
                BDEditor.putString("forever_skip_initd_check", "forever_skip_initd_check");
                BDEditor.apply();
                NO_INITD.dismiss();
                startMainActivity();
            }
        });
        Button exit = NO_INITD.findViewById(R.id.exitttt);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                SplashActivity.this.finish();
                System.exit(0);
            }
        });

        NO_INITD.show();
    }

    ////////////////////////////////////////////////////////////////////////////
    ////// Show dialog with error, if root not installed ///////////////////////
    ////////////////////////////////////////////////////////////////////////////
    public void NO_ROOT_DIALOG() {
        final Dialog NO_ROOT = new Dialog(SplashActivity.this);
        NO_ROOT.setCancelable(false);
        NO_ROOT.setContentView(R.layout.no_root_support_dialog);
        Button norootexit = NO_ROOT.findViewById(R.id.norootexit);
        norootexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                SplashActivity.this.finish();
                System.exit(0);
            }
        });
        NO_ROOT.show();
    }

    /////////////////////////////////////////////////////////////
    ////// Service void (to prevent repeats in code)  ///////////
    /////////////////////////////////////////////////////////////
    public void InitD_ShowDialogOrNot() {
        final SharedPreferences BD = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if ((BD.contains("forever_skip_initd_check")) || (HelperClass.isInitdSupport() == 1)) {
            startMainActivity();
        } else {
            NO_INITD_DIALOG();
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        newBase = LocaleChanger.configureBaseContext(newBase);
        super.attachBaseContext(newBase);
    }

    private void changeStatusBarColor(String color) {
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor(color));
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        changeStatusBarColor("#394454");
        copyAssets();
        setContentView(R.layout.loading_screen);
        addSUDSupport();

        final SharedPreferences BD = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if (RootTools.isAccessGiven()) {
            checksu = 1;
            @SuppressLint("SdCardPath") Command init_commands = new Command(0,
                    "chmod 777 /data/data/com.nowenui.systemtweaker/files/*",
                    "ln -s /data/data/com.nowenui.systemtweaker/files/busybox /data/data/com.nowenui.systemtweaker/files/ash",
                    "chmod 777 /data/data/com.nowenui.systemtweaker/files/ash",
                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                    "chmod 777 /system/etc/init.d",
                    "chown -R root root /system/etc/init.d",
                    "mkdir /system/SystemTweaker",
                    "chown -R root root /system/SystemTweaker",
                    "cp /data/data/com.nowenui.systemtweaker/files/busybox /system/SystemTweaker",
                    "cp /data/data/com.nowenui.systemtweaker/files/sqlite3 /system/SystemTweaker",
                    "cp /data/data/com.nowenui.systemtweaker/files/zipalign /system/SystemTweaker",
                    "chmod 777 /system/SystemTweaker/busybox",
                    "chmod 777 /system/SystemTweaker/sqlite3",
                    "chmod 777 /system/SystemTweaker/zipalign",
                    "ln -s /system/SystemTweaker/busybox /system/SystemTweaker/ash",
                    "/data/data/com.nowenui.systemtweaker/files/busybox dos2unix /system/etc/init.d/*",
                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system");
            Command checking_selinux_command = new Command(0,
                    "getenforce") {
                @Override
                public void commandOutput(int id, String SELinux) {
                    super.commandOutput(id, SELinux);
                    if (SELinux.contains("Permissive")) {
                        selinuxstatus = 1;
                        InitD_ShowDialogOrNot();
                    } else {
                        selinuxstatus = 0;
                        if ((BD.contains("skip_selinux_check"))) {
                            InitD_ShowDialogOrNot();
                        } else {
                            BAD_SELINUX_DIALOG();
                        }
                    }
                }
            };
            try {
                RootTools.getShell(true).add(init_commands);
                if ((HelperClass.isSELinuxPresent() == 1) && (Build.VERSION.SDK_INT >= 18)) {
                    RootTools.getShell(true).add(checking_selinux_command);
                } else {
                    selinuxstatus = 1;
                    InitD_ShowDialogOrNot();
                }
            } catch (IOException | RootDeniedException | TimeoutException ignored) {
            }
        } else {
            NO_ROOT_DIALOG();
        }

    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            System.exit(0);
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, R.string.close, Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2500);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    ////////////////////////////////////
    ////// Start application ///////////
    ////////////////////////////////////
    private void startMainActivity() {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, 3800);
    }

    ////////////////////////////////
    ////// Add su.d support ////////
    ////////////////////////////////
    public int addSUDSupport() {
        File sud = new File("/system/su.d");
        File initd = new File("/system/etc/init.d");
        if ((!initd.exists()) && (!initd.isDirectory()) && (sud.exists()) && (sud.isDirectory())) {
            if (RootTools.isAccessGiven()) {
                Command command1 = new Command(0,
                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                        "busybox ln -s /system/su.d /system/etc/init.d",
                        "chmod 777 /system/etc/init.d",
                        "chown -R root root /system/etc/init.d",
                        "chmod 777 /system/su.d",
                        "chown -R root root /system/su.d",
                        "/data/data/com.nowenui.systemtweaker/files/busybox dos2unix /system/su.d/*",
                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system");
                try {
                    RootTools.getShell(true).add(command1);
                } catch (IOException | RootDeniedException | TimeoutException ex) {

                }
            }
            return 1;
        }
        return 0;
    }

    ////////////////////////////////
    ////// Unpack assets ///////////
    ////////////////////////////////
    private void copyAssets() {
        AssetManager assetManager = this.getAssets();
        String[] files = null;
        try {
            files = assetManager.list("");
        } catch (IOException ignored) {
        }
        if (files != null) for (String filename : files) {
            InputStream in = null;
            OutputStream out = null;
            try {
                in = assetManager.open(filename);
                File folder = new File("/data/data/com.nowenui.systemtweaker/files/");
                if (!folder.exists()) {
                    folder.mkdir();
                }
                File outFile = new File(folder, filename);
                out = new FileOutputStream(outFile);
                copyFile(in, out);

            } catch (IOException ignored) {
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException ignored) {
                    }
                }
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException ignored) {
                    }
                }
            }
        }

    }

    ////////////////////////////////
    ////////// Copy file ///////////
    ////////////////////////////////
    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
    }
}