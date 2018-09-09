package com.nowenui.systemtweaker.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.github.mrengineer13.snackbar.SnackBar;
import com.nowenui.systemtweaker.HelperClass;
import com.nowenui.systemtweaker.R;
import com.nowenui.systemtweaker.ThemeUtility;
import com.onebit.spinner2.Spinner2;
import com.stericson.RootShell.exceptions.RootDeniedException;
import com.stericson.RootShell.execution.Command;
import com.stericson.RootTools.RootTools;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.TimeoutException;

public class SystemTweaksFragment extends Fragment {

    public Integer heap, grow;
    private boolean isClicked;

    public static SystemTweaksFragment newInstance(Bundle bundle) {
        SystemTweaksFragment SystemTweaks = new SystemTweaksFragment();

        if (bundle != null) {
            SystemTweaks.setArguments(bundle);
        }

        return SystemTweaks;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.system_tweaks, parent, false);
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {

        boolean isLangRU = Locale.getDefault().getLanguage().equals("ru");
        boolean isLangBE = Locale.getDefault().getLanguage().equals("be");
        boolean isLangUK = Locale.getDefault().getLanguage().equals("uk");
        boolean isLangKK = Locale.getDefault().getLanguage().equals("kk");
        boolean isLangKZ = Locale.getDefault().getLanguage().equals("kz");
        HelperClass easyMemoryMod = new HelperClass(getContext());

        //////////////////////////////
        ////// OOM Tweak ////////////
        /////////////////////////////
        TextView oomtweakinfo = view.findViewById(R.id.oomtweakinfo);
        oomtweakinfo.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                AboutTweak(R.string.sys10);
                return true;
            }
        });

        final Spinner2 oomspinner = view.findViewById(R.id.oomspinner);
        if (HelperClass.isInitdSupport() == 0) {
            oomspinner.setEnabled(false);
        } else {
            oomspinner.setEnabled(true);
        }

        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(getActivity(), R.array.oomlist, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        oomspinner.setAdapter(adapter, false);

        if (new File("/system/etc/init.d/oom_enable").exists()) {
            if (isLangRU || isLangBE || isLangUK || isLangKK || isLangKZ) {
                final int enabledpositionru = adapter.getPosition("Включен");
                oomspinner.post(new Runnable() {
                    @Override
                    public void run() {
                        oomspinner.setSelection(false, enabledpositionru);
                    }
                });
            } else {
                final int enabledpositionen = adapter.getPosition("Enabled");
                oomspinner.post(new Runnable() {
                    @Override
                    public void run() {
                        oomspinner.setSelection(false, enabledpositionen);
                    }
                });
            }
        }
        if (new File("/system/etc/init.d/oom_disable").exists()) {
            if (isLangRU || isLangBE || isLangUK || isLangKK || isLangKZ) {
                final int disabledpositionru = adapter.getPosition("Отключен");
                oomspinner.post(new Runnable() {
                    @Override
                    public void run() {
                        oomspinner.setSelection(false, disabledpositionru);
                    }
                });
            } else {
                final int disabledpositionen = adapter.getPosition("Disabled");
                oomspinner.post(new Runnable() {
                    @Override
                    public void run() {
                        oomspinner.setSelection(false, disabledpositionen);
                    }
                });
            }
        }
        oomspinner.post(new Runnable() {
            @Override
            public void run() {
                oomspinner.setOnItemSelectedSpinner2Listener(new Spinner2.OnItemSelectedSpinner2Listener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String[] choose = getResources().getStringArray(R.array.oomlist);
                        if (choose[position].contains("(стандарт)") || choose[position].contains("(standart)")) {
                            if (RootTools.isAccessGiven()) {
                                @SuppressLint("SdCardPath") Command standartoomcommand = new Command(0,
                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                        "rm -f /system/etc/init.d/oom_disable",
                                        "rm -f /system/etc/init.d/oom_enable",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                );
                                try {
                                    RootTools.getShell(true).add(standartoomcommand);
                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.ok)).withBackgroundColorId(R.color.resultgood).show();
                                } catch (IOException | RootDeniedException | TimeoutException ex) {
                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                                }
                            } else {
                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                            }
                        }
                        if ((choose[position].contains("Включен")) || (choose[position].contains("Enabled"))) {
                            if (RootTools.isAccessGiven()) {
                                @SuppressLint("SdCardPath") Command enabledoomcommand = new Command(0,
                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                        "cp /data/data/com.nowenui.systemtweaker/files/oom_enable /system/etc/init.d/",
                                        "chmod 777 /system/etc/init.d/oom_enable",
                                        "rm -f /system/etc/init.d/oom_disable",
                                        "/system/etc/init.d/oom_enable",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                );
                                try {
                                    RootTools.getShell(true).add(enabledoomcommand);
                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.ok)).withBackgroundColorId(R.color.resultgood).show();
                                } catch (IOException | RootDeniedException | TimeoutException ex) {
                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                                }
                            } else {
                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                            }
                        }
                        if ((choose[position].contains("Disabled")) || (choose[position].contains("Отключен"))) {

                            if (RootTools.isAccessGiven()) {
                                @SuppressLint("SdCardPath") Command disabledoomcommand = new Command(0,
                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                        "cp /data/data/com.nowenui.systemtweaker/files/oom_disable /system/etc/init.d/",
                                        "chmod 777 /system/etc/init.d/oom_disable",
                                        "rm -f /system/etc/init.d/oom_enable",
                                        "/system/etc/init.d/oom_disable",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                );
                                try {
                                    RootTools.getShell(true).add(disabledoomcommand);
                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.ok)).withBackgroundColorId(R.color.resultgood).show();
                                } catch (IOException | RootDeniedException | TimeoutException ex) {
                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                                }
                            } else {
                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                            }
                        }
                    }
                });
            }
        });

        //////////////////////////////////
        ////// RAM MODE Tweak ////////////
        /////////////////////////////////
        final Spinner2 rammodespinner = view.findViewById(R.id.rammodespinner);
        ArrayAdapter<CharSequence> rammodeadapter =
                ArrayAdapter.createFromResource(getActivity(), R.array.ramlist, android.R.layout.simple_spinner_item);
        rammodeadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rammodespinner.setAdapter(rammodeadapter, false);

        if ((new File("/system/etc/init.d/ram_gaming").exists() && (new File("/system/etc/init.d/cfq_improve_gaming").exists())
                && HelperClass.BuildPropText().toString().contains("persist.sys.NV_FPSLIMIT=60")
                && HelperClass.BuildPropText().toString().contains("persist.sys.NV_POWERMODE=1")
                && HelperClass.BuildPropText().toString().contains("persist.sys.NV_PROFVER=15")
                && HelperClass.BuildPropText().toString().contains("persist.sys.NV_STEREOCTRL=0")
                && HelperClass.BuildPropText().toString().contains("persist.sys.NV_STEREOSEPCHG=0")
                && HelperClass.BuildPropText().toString().contains("persist.sys.NV_STEREOSEP=20")
                && HelperClass.BuildPropText().toString().contains("persist.sys.purgeable_assets=1"))) {

            if (isLangRU || isLangBE || isLangUK || isLangKK || isLangKZ) {
                final int gamingruposition = rammodeadapter.getPosition("Игровой");
                rammodespinner.post(new Runnable() {
                    @Override
                    public void run() {
                        rammodespinner.setSelection(false, gamingruposition);
                    }
                });
            } else {
                final int gamingenposition = rammodeadapter.getPosition("Gaming");
                rammodespinner.post(new Runnable() {
                    @Override
                    public void run() {
                        rammodespinner.setSelection(false, gamingenposition);
                    }
                });
            }

        }

        if (new File("/system/etc/init.d/ram_balanced").exists()) {
            if (isLangRU || isLangBE || isLangUK || isLangKK || isLangKZ) {
                final int balancedruposition = rammodeadapter.getPosition("Сбалансированный");
                rammodespinner.post(new Runnable() {
                    @Override
                    public void run() {
                        rammodespinner.setSelection(false, balancedruposition);
                    }
                });
            } else {
                final int balancedenposition = rammodeadapter.getPosition("Balanced");
                rammodespinner.post(new Runnable() {
                    @Override
                    public void run() {
                        rammodespinner.setSelection(false, balancedenposition);
                    }
                });
            }
        }

        if (new File("/system/etc/init.d/ram_multitasking").exists()) {
            if (isLangRU || isLangBE || isLangUK || isLangKK || isLangKZ) {
                final int manytasksruposition = rammodeadapter.getPosition("Многозадачность");
                rammodespinner.post(new Runnable() {
                    @Override
                    public void run() {
                        rammodespinner.setSelection(false, manytasksruposition);
                    }
                });
            } else {
                final int manytasksenposition = rammodeadapter.getPosition("Multitasking");
                rammodespinner.post(new Runnable() {
                    @Override
                    public void run() {
                        rammodespinner.setSelection(false, manytasksenposition);
                    }
                });
            }
        }

        rammodespinner.post(new Runnable() {
            @Override
            public void run() {
                rammodespinner.setOnItemSelectedSpinner2Listener(new Spinner2.OnItemSelectedSpinner2Listener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String[] choose = getResources().getStringArray(R.array.ramlist);
                        if (choose[position].contains("(стандарт)") || choose[position].contains("(standart)")) {
                            if (RootTools.isAccessGiven()) {
                                @SuppressLint("SdCardPath") Command standartramcommand = new Command(0,
                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                        "rm -f /system/etc/init.d/ram_gaming",
                                        "rm -f /system/etc/init.d/ram_balanced",
                                        "rm -f /system/etc/init.d/ram_multitasking",
                                        "rm -f /system/etc/init.d/cfq_improve_gaming",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/persist.sys.NV_FPSLIMIT/d' /system/build.prop",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/persist.sys.NV_POWERMODE/d' /system/build.prop",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/persist.sys.NV_PROFVER/d' /system/build.prop",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/persist.sys.NV_STEREOCTRL/d' /system/build.prop",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/persist.sys.NV_STEREOSEPCHG/d' /system/build.prop",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/persist.sys.NV_STEREOSEP/d' /system/build.prop",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/persist.sys.NV_PROFVER/d' /system/build.prop",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/persist.sys.purgeable_assets/d' /system/build.prop",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                );
                                try {
                                    RootTools.getShell(true).add(standartramcommand);
                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.ok)).withBackgroundColorId(R.color.resultgood).show();
                                } catch (IOException | RootDeniedException | TimeoutException ex) {
                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                                }
                            } else {
                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                            }
                        }
                        if ((choose[position].contains("Balanced")) || (choose[position].contains("Сбалансированный"))) {
                            if (RootTools.isAccessGiven()) {
                                @SuppressLint("SdCardPath") Command standartramcommand = new Command(0,
                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/persist.sys.NV_FPSLIMIT/d' /system/build.prop",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/persist.sys.NV_POWERMODE/d' /system/build.prop",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/persist.sys.NV_PROFVER/d' /system/build.prop",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/persist.sys.NV_STEREOCTRL/d' /system/build.prop",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/persist.sys.NV_STEREOSEPCHG/d' /system/build.prop",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/persist.sys.NV_STEREOSEP/d' /system/build.prop",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/persist.sys.NV_PROFVER/d' /system/build.prop",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/persist.sys.purgeable_assets/d' /system/build.prop",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system",
                                        "cp /data/data/com.nowenui.systemtweaker/files/ram_balanced /system/etc/init.d/",
                                        "chmod 777 /system/etc/init.d/ram_balanced",
                                        "rm -f /system/etc/init.d/ram_gaming",
                                        "rm -f /system/etc/init.d/ram_multitasking",
                                        "rm -f /system/etc/init.d/cfq_improve_gaming",
                                        "/system/etc/init.d/ram_balanced",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                );
                                try {
                                    RootTools.getShell(true).add(standartramcommand);
                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.ok)).withBackgroundColorId(R.color.resultgood).show();
                                } catch (IOException | RootDeniedException | TimeoutException ex) {
                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                                }
                            } else {
                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                            }
                        }
                        if ((choose[position].contains("Multitasking")) || (choose[position].contains("Многозадачность"))) {
                            if (RootTools.isAccessGiven()) {
                                @SuppressLint("SdCardPath") Command standartramcommand = new Command(0,
                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/persist.sys.NV_FPSLIMIT/d' /system/build.prop",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/persist.sys.NV_POWERMODE/d' /system/build.prop",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/persist.sys.NV_PROFVER/d' /system/build.prop",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/persist.sys.NV_STEREOCTRL/d' /system/build.prop",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/persist.sys.NV_STEREOSEPCHG/d' /system/build.prop",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/persist.sys.NV_STEREOSEP/d' /system/build.prop",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/persist.sys.NV_PROFVER/d' /system/build.prop",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/persist.sys.purgeable_assets/d' /system/build.prop",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system",
                                        "cp /data/data/com.nowenui.systemtweaker/files/ram_multitasking /system/etc/init.d/",
                                        "chmod 777 /system/etc/init.d/ram_multitasking",
                                        "rm -f /system/etc/init.d/ram_gaming",
                                        "rm -f /system/etc/init.d/ram_balanced",
                                        "rm -f /system/etc/init.d/cfq_improve_gaming",
                                        "/system/etc/init.d/ram_multitasking",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                );
                                try {
                                    RootTools.getShell(true).add(standartramcommand);
                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.ok)).withBackgroundColorId(R.color.resultgood).show();
                                } catch (IOException | RootDeniedException | TimeoutException ex) {
                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                                }
                            } else {
                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                            }
                        }
                        if (choose[position].contains("Gaming") || choose[position].contains("Игровой")) {
                            if (RootTools.isAccessGiven()) {
                                @SuppressLint("SdCardPath") Command standartramcommand = new Command(0,
                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/persist.sys.NV_FPSLIMIT/d' /system/build.prop",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/persist.sys.NV_POWERMODE/d' /system/build.prop",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/persist.sys.NV_PROFVER/d' /system/build.prop",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/persist.sys.NV_STEREOCTRL/d' /system/build.prop",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/persist.sys.NV_STEREOSEPCHG/d' /system/build.prop",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/persist.sys.NV_STEREOSEP/d' /system/build.prop",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/persist.sys.NV_PROFVER/d' /system/build.prop",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/persist.sys.purgeable_assets/d' /system/build.prop",
                                        "echo \"persist.sys.NV_FPSLIMIT=60\" >> /system/build.prop",
                                        "echo \"persist.sys.NV_POWERMODE=1\" >> /system/build.prop",
                                        "echo \"persist.sys.NV_PROFVER=15\" >> /system/build.prop",
                                        "echo \"persist.sys.NV_STEREOCTRL=0\" >> /system/build.prop",
                                        "echo \"persist.sys.NV_STEREOSEPCHG=0\" >> /system/build.prop",
                                        "echo \"persist.sys.NV_STEREOSEP=20\" >> /system/build.prop",
                                        "echo \"persist.sys.purgeable_assets=1\" >> /system/build.prop",
                                        "setprop persist.sys.NV_FPSLIMIT 60",
                                        "setprop persist.sys.NV_POWERMODE 1",
                                        "setprop persist.sys.NV_PROFVER 15",
                                        "setprop persist.sys.NV_STEREOCTRL 0",
                                        "setprop persist.sys.NV_STEREOSEPCHG 0",
                                        "setprop persist.sys.NV_STEREOSEP 20",
                                        "setprop persist.sys.purgeable_assets 1",
                                        "cp /data/data/com.nowenui.systemtweaker/files/cfq_improve_gaming /system/etc/init.d/",
                                        "chmod 777 /system/etc/init.d/cfq_improve_gaming",
                                        "/system/etc/init.d/cfq_improve_gaming",
                                        "cp /data/data/com.nowenui.systemtweaker/files/ram_gaming /system/etc/init.d/",
                                        "chmod 777 /system/etc/init.d/ram_gaming",
                                        "rm -f /system/etc/init.d/ram_balanced",
                                        "rm -f /system/etc/init.d/ram_multitasking",
                                        "/system/etc/init.d/ram_gaming",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                );
                                try {
                                    RootTools.getShell(true).add(standartramcommand);
                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.ok)).withBackgroundColorId(R.color.resultgood).show();
                                } catch (IOException | RootDeniedException | TimeoutException ex) {
                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                                }
                            } else {
                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                            }
                        }
                    }
                });
            }
        });

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///// Tweak: disabling Memory limit and a low Memory notification when installing applications in GPlay //////
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Button gptweak = view.findViewById(R.id.gptweak);
        gptweak.setBackgroundResource(R.drawable.roundbuttoncal);
        gptweak.setTextColor(Color.WHITE);

        gptweak.setOnClickListener(new View.OnClickListener() {
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
                    @SuppressLint("SdCardPath") Command installtweak = new Command(0,
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                            "settings put global sys_storage_threshold_max_bytes 1048",
                            "settings put secure sys_storage_threshold_max_bytes 1048",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                            "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                    );
                    try {
                        RootTools.getShell(true).add(installtweak);
                        RebootDialog();
                    } catch (IOException | RootDeniedException | TimeoutException ex) {
                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                    }
                } else {
                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                }
            }

        });

        ///////////////////////////////////////////
        ////// Boost & Performance tweak //////////
        //////////////////////////////////////////
        CheckBox perfomancetweak = view.findViewById(R.id.perfomancetweak);
        if (HelperClass.isInitdSupport() == 0) {
            perfomancetweak.setEnabled(false);
        } else {
            perfomancetweak.setEnabled(true);
        }
        if (HelperClass.BuildPropText().toString().contains("persist.service.lgospd.enable=0")
                && HelperClass.BuildPropText().toString().contains("persist.service.pcsync.enable=0")
                && HelperClass.BuildPropText().toString().contains("touch.pressure.scale=0.001")
                && HelperClass.BuildPropText().toString().contains("persist.sys.use_dithering=0")
                && HelperClass.BuildPropText().toString().contains("persist.sys.use_16bpp_alpha=1")
                && new File("/system/etc/init.d/boost").exists()) {
            perfomancetweak.setChecked(true);
        } else {
            perfomancetweak.setChecked(false);
        }
        perfomancetweak.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                AboutTweak(R.string.perf);
                return true;
            }
        });
        perfomancetweak.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                                       @Override
                                                       public void onCheckedChanged(CompoundButton buttonView,
                                                                                    boolean isChecked) {
                                                           if (isChecked) {
                                                               if (RootTools.isAccessGiven()) {
                                                                   @SuppressLint("SdCardPath") Command installtweak = new Command(0,
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/persist.sys.use_dithering/d' /system/build.prop",
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/persist.sys.use_16bpp_alpha/d' /system/build.prop",
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/touch.pressure.scale/d' /system/build.prop",
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/persist.service.pcsync.enable/d' /system/build.prop",
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/persist.service.lgospd.enable/d' /system/build.prop",
                                                                           "echo \"persist.sys.use_dithering=0\" >> /system/build.prop",
                                                                           "echo \"persist.sys.use_16bpp_alpha=1\" >> /system/build.prop",
                                                                           "echo \"persist.service.lgospd.enable=0\" >> /system/build.prop",
                                                                           "echo \"persist.service.pcsync.enable=0\" >> /system/build.prop",
                                                                           "echo \"touch.pressure.scale=0.001\" >> /system/build.prop",
                                                                           "setprop persist.service.lgospd.enable 0",
                                                                           "setprop persist.service.pcsync.enable 0",
                                                                           "setprop persist.sys.use_dithering 0",
                                                                           "setprop persist.sys.use_16bpp_alpha 1",
                                                                           "setprop touch.pressure.scale 0.001",
                                                                           "cp /data/data/com.nowenui.systemtweaker/files/boost /system/etc/init.d/",
                                                                           "chmod 777 /system/etc/init.d/boost",
                                                                           "/system/etc/init.d/boost",
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                                                   );
                                                                   try {
                                                                       RootTools.getShell(true).add(installtweak);
                                                                       new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakenabled)).withBackgroundColorId(R.color.resultgood).show();
                                                                   } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                                       new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                                                                   }
                                                               } else {
                                                                   new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                                                               }
                                                           } else {
                                                               if (RootTools.isAccessGiven()) {
                                                                   @SuppressLint("SdCardPath") Command deletetweak = new Command(0,
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/persist.sys.use_dithering/d' /system/build.prop",
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/persist.sys.use_16bpp_alpha/d' /system/build.prop",
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/touch.pressure.scale/d' /system/build.prop",
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/persist.service.pcsync.enable/d' /system/build.prop",
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/persist.service.lgospd.enable/d' /system/build.prop",
                                                                           "rm -f /system/etc/init.d/boost",
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                                                   );
                                                                   try {
                                                                       RootTools.getShell(true).add(deletetweak);
                                                                       new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakdisabled)).withBackgroundColorId(R.color.resultgood).show();
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

        ///////////////////////////////
        ////// Low RAM Tweak //////////
        //////////////////////////////
        CheckBox ramflagtweak = view.findViewById(R.id.lowram);
        if ((easyMemoryMod.getTotalRAM() <= 1024) && (Build.VERSION.SDK_INT >= 19)) {
            ramflagtweak.setEnabled(true);
        } else {
            ramflagtweak.setEnabled(false);
        }
        if (HelperClass.BuildPropText().toString().contains("ro.config.low_ram=true")) {
            ramflagtweak.setChecked(true);
        } else {
            ramflagtweak.setChecked(false);
        }
        ramflagtweak.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                AboutTweak(R.string.sys12);
                return true;
            }
        });
        ramflagtweak.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                                    @Override
                                                    public void onCheckedChanged(CompoundButton buttonView,
                                                                                 boolean isChecked) {
                                                        if (isChecked) {
                                                            if (RootTools.isAccessGiven()) {
                                                                @SuppressLint("SdCardPath") Command installtweak = new Command(0,
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.config.low_ram/d' /system/build.prop",
                                                                        "echo \"ro.config.low_ram=true\" >> /system/build.prop",
                                                                        "setprop ro.config.low_ram true",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                                                );
                                                                try {
                                                                    RootTools.getShell(true).add(installtweak);
                                                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakenabled)).withBackgroundColorId(R.color.resultgood).show();
                                                                } catch (IOException | RootDeniedException | TimeoutException ex) {

                                                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                                                                }
                                                            } else {
                                                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                                                            }
                                                        } else {
                                                            if (RootTools.isAccessGiven()) {
                                                                @SuppressLint("SdCardPath") Command deletetweak = new Command(0,
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.config.low_ram/d' /system/build.prop",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                                                );
                                                                try {
                                                                    RootTools.getShell(true).add(deletetweak);
                                                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakdisabled)).withBackgroundColorId(R.color.resultgood).show();
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

        ////////////////////////////////////////////////////////
        ////// Low RAM visual effects disabling tweak //////////
        ////////////////////////////////////////////////////////
        CheckBox ramflagvisualeffect = view.findViewById(R.id.lowrameffects);
        if ((easyMemoryMod.getTotalRAM() <= 1024) && (Build.VERSION.SDK_INT >= 19) && ramflagtweak.isChecked()) {
            ramflagvisualeffect.setEnabled(true);
        } else {
            ramflagvisualeffect.setEnabled(false);
        }
        if (HelperClass.BuildPropText().toString().contains("persist.sys.force_highendgfx=true")) {
            ramflagvisualeffect.setChecked(true);
        } else {
            ramflagvisualeffect.setChecked(false);
        }
        ramflagvisualeffect.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                AboutTweak(R.string.sys13);
                return true;
            }
        });
        ramflagvisualeffect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                                           @Override
                                                           public void onCheckedChanged(CompoundButton buttonView,
                                                                                        boolean isChecked) {
                                                               if (isChecked) {
                                                                   if (RootTools.isAccessGiven()) {
                                                                       @SuppressLint("SdCardPath") Command installtweak = new Command(0,
                                                                               "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                               "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                               "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                               "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/persist.sys.force_highendgfx/d' /system/build.prop",
                                                                               "echo \"persist.sys.force_highendgfx=true\" >> /system/build.prop",
                                                                               "setprop persist.sys.force_highendgfx true",
                                                                               "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                               "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                               "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                                                       );
                                                                       try {
                                                                           RootTools.getShell(true).add(installtweak);
                                                                           new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakenabled)).withBackgroundColorId(R.color.resultgood).show();
                                                                       } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                                           new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                                                                       }
                                                                   } else {
                                                                       new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                                                                   }
                                                               } else {
                                                                   if (RootTools.isAccessGiven()) {
                                                                       @SuppressLint("SdCardPath") Command deletetweak = new Command(0,
                                                                               "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                               "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                               "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                               "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/persist.sys.force_highendgfx/d' /system/build.prop",
                                                                               "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                               "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                               "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                                                       );
                                                                       try {
                                                                           RootTools.getShell(true).add(deletetweak);
                                                                           new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakdisabled)).withBackgroundColorId(R.color.resultgood).show();
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


        //////////////////////////////
        ////// GPU Tweaks ///////////
        /////////////////////////////
        CheckBox gputweak = view.findViewById(R.id.gputweak);
        if (HelperClass.isInitdSupport() == 0) {
            gputweak.setEnabled(false);
        } else {
            gputweak.setEnabled(true);
        }
        if (HelperClass.BuildPropText().toString().contains("debug.composition.type=gpu")
                && HelperClass.BuildPropText().toString().contains("debug.composition.type=c2d")
                && HelperClass.BuildPropText().toString().contains("debug.egl.hw=1")
                && HelperClass.BuildPropText().toString().contains("debug.egl.profiler=1")
                && HelperClass.BuildPropText().toString().contains("debug.enabletr=true")
                && HelperClass.BuildPropText().toString().contains("debug.overlayui.enable=1")
                && HelperClass.BuildPropText().toString().contains("debug.performance.tuning=1")
                && HelperClass.BuildPropText().toString().contains("debug.sf.hw=1")
                && HelperClass.BuildPropText().toString().contains("dev.pm.dyn_samplingrate=1")
                && HelperClass.BuildPropText().toString().contains("hw3d.force=1")
                && HelperClass.BuildPropText().toString().contains("hwui.disable_vsync=true")
                && HelperClass.BuildPropText().toString().contains("hwui.render_dirty_regions=false")
                && HelperClass.BuildPropText().toString().contains("persist.sys.composition.type=c2d")
                && HelperClass.BuildPropText().toString().contains("persist.sys.composition.type=gpu")
                && HelperClass.BuildPropText().toString().contains("persist.sys.ui.hw=1")
                && HelperClass.BuildPropText().toString().contains("ro.config.enable.hw_accel=true")
                && HelperClass.BuildPropText().toString().contains("ro.product.gpu.driver=1")
                && HelperClass.BuildPropText().toString().contains("ro.fb.mode=1")
                && HelperClass.BuildPropText().toString().contains("ro.sf.compbypass.enable=0")
                && HelperClass.BuildPropText().toString().contains("video.accelerate.hw=1")
                && new File("/system/etc/init.d/81GPU_rendering").exists()) {
            gputweak.setChecked(true);
        } else {
            gputweak.setChecked(false);
        }
        gputweak.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                AboutTweak(R.string.sys4);
                return true;
            }
        });
        gputweak.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                                @Override
                                                public void onCheckedChanged(CompoundButton buttonView,
                                                                             boolean isChecked) {
                                                    if (isChecked) {
                                                        if (RootTools.isAccessGiven()) {
                                                            @SuppressLint("SdCardPath") Command installtweak = new Command(0,
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                    "cp /data/data/com.nowenui.systemtweaker/files/81GPU_rendering /system/etc/init.d/",
                                                                    "chmod 777 /system/etc/init.d/81GPU_rendering",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/debug.composition.type/d' /system/build.prop",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/debug.egl.hw/d' /system/build.prop",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/debug.egl.profiler/d' /system/build.prop",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/debug.enabletr/d' /system/build.prop",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/debug.overlayui.enable/d' /system/build.prop",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/debug.performance.tuning/d' /system/build.prop",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/debug.sf.hw/d' /system/build.prop",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/dev.pm.dyn_samplingrate/d' /system/build.prop",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/hw3d.force/d' /system/build.prop",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/hwui.disable_vsync/d' /system/build.prop",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/hwui.render_dirty_regions/d' /system/build.prop",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/persist.sys.composition.type/d' /system/build.prop",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/persist.sys.ui.hw/d' /system/build.prop",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.config.enable.hw_accel/d' /system/build.prop",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.product.gpu.driver/d' /system/build.prop",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.fb.mode/d' /system/build.prop",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.sf.compbypass.enable/d' /system/build.prop",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/video.accelerate.hw/d' /system/build.prop",
                                                                    "echo \"debug.composition.type=gpu\" >> /system/build.prop",
                                                                    "echo \"debug.composition.type=c2d\" >> /system/build.prop",
                                                                    "echo \"debug.egl.hw=1\" >> /system/build.prop",
                                                                    "echo \"debug.egl.profiler=1\" >> /system/build.prop",
                                                                    "echo \"debug.enabletr=true\" >> /system/build.prop",
                                                                    "echo \"debug.overlayui.enable=1\" >> /system/build.prop",
                                                                    "echo \"debug.performance.tuning=1\" >> /system/build.prop",
                                                                    "echo \"debug.sf.hw=1\" >> /system/build.prop",
                                                                    "echo \"dev.pm.dyn_samplingrate=1\" >> /system/build.prop",
                                                                    "echo \"hw3d.force=1\" >> /system/build.prop",
                                                                    "echo \"hwui.disable_vsync=true\" >> /system/build.prop",
                                                                    "echo \"hwui.render_dirty_regions=false\" >> /system/build.prop",
                                                                    "echo \"persist.sys.composition.type=c2d\" >> /system/build.prop",
                                                                    "echo \"persist.sys.composition.type=gpu\" >> /system/build.prop",
                                                                    "echo \"persist.sys.ui.hw=1\" >> /system/build.prop",
                                                                    "echo \"ro.config.enable.hw_accel=true\" >> /system/build.prop",
                                                                    "echo \"ro.product.gpu.driver=1\" >> /system/build.prop",
                                                                    "echo \"ro.fb.mode=1\" >> /system/build.prop",
                                                                    "echo \"ro.sf.compbypass.enable=0\" >> /system/build.prop",
                                                                    "echo \"video.accelerate.hw=1\" >> /system/build.prop",
                                                                    "setprop debug.composition.type gpu",
                                                                    "setprop debug.composition.type c2d",
                                                                    "setprop debug.egl.hw 1",
                                                                    "setprop debug.egl.profiler 1",
                                                                    "setprop debug.enabletr true",
                                                                    "setprop debug.overlayui.enable 1",
                                                                    "setprop debug.performance.tuning 1",
                                                                    "setprop debug.sf.hw 1",
                                                                    "setprop dev.pm.dyn_samplingrate 1",
                                                                    "setprop hw3d.force 1",
                                                                    "setprop hwui.disable_vsync true",
                                                                    "setprop hwui.render_dirty_regions false",
                                                                    "setprop persist.sys.composition.type c2d",
                                                                    "setprop persist.sys.composition.type gpu",
                                                                    "setprop persist.sys.ui.hw 1",
                                                                    "setprop ro.config.enable.hw_accel true",
                                                                    "setprop ro.product.gpu.driver 1",
                                                                    "setprop ro.fb.mode 1",
                                                                    "setprop ro.sf.compbypass.enable 0",
                                                                    "setprop video.accelerate.hw 1",
                                                                    "/system/etc/init.d/81GPU_rendering",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system");
                                                            try {
                                                                RootTools.getShell(true).add(installtweak);
                                                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakenabled)).withBackgroundColorId(R.color.resultgood).show();
                                                            } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                                                            }
                                                        } else {
                                                            new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                                                        }
                                                    } else {
                                                        if (RootTools.isAccessGiven()) {
                                                            @SuppressLint("SdCardPath") Command deletetweak = new Command(0,
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                    "rm -f /system/etc/init.d/81GPU_rendering",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/debug.composition.type/d' /system/build.prop",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/debug.egl.hw/d' /system/build.prop",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/debug.egl.profiler/d' /system/build.prop",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/debug.enabletr/d' /system/build.prop",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/debug.overlayui.enable/d' /system/build.prop",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/debug.performance.tuning/d' /system/build.prop",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/debug.sf.hw/d' /system/build.prop",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/dev.pm.dyn_samplingrate/d' /system/build.prop",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/hw3d.force/d' /system/build.prop",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/hwui.disable_vsync/d' /system/build.prop",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/hwui.render_dirty_regions/d' /system/build.prop",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/persist.sys.composition.type/d' /system/build.prop",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/persist.sys.ui.hw/d' /system/build.prop",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.config.enable.hw_accel/d' /system/build.prop",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.product.gpu.driver/d' /system/build.prop",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.fb.mode/d' /system/build.prop",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.sf.compbypass.enable/d' /system/build.prop",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/video.accelerate.hw/d' /system/build.prop",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                                            );
                                                            try {
                                                                RootTools.getShell(true).add(deletetweak);
                                                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakdisabled)).withBackgroundColorId(R.color.resultgood).show();
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

        //////////////////////////////
        ////// FPS Tweak ////////////
        ////////////////////////////
        CheckBox fpsse = view.findViewById(R.id.fps);
        if (HelperClass.BuildPropText().toString().contains("debug.egl.swapinterval=-60")) {
            fpsse.setChecked(true);
        } else {
            fpsse.setChecked(false);
        }
        fpsse.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                AboutTweak(R.string.fpset);
                return true;
            }
        });
        fpsse.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                             @Override
                                             public void onCheckedChanged(CompoundButton buttonView,
                                                                          boolean isChecked) {
                                                 if (isChecked) {
                                                     if (RootTools.isAccessGiven()) {
                                                         @SuppressLint("SdCardPath") Command installtweak = new Command(0,
                                                                 "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                 "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                 "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                 "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/debug.egl.swapinterval/d' /system/build.prop",
                                                                 "echo \"debug.egl.swapinterval=-60\" >> /system/build.prop",
                                                                 "setprop debug.egl.swapinterval -60",
                                                                 "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                 "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                 "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                                         );
                                                         try {
                                                             RootTools.getShell(true).add(installtweak);
                                                             new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakenabled)).withBackgroundColorId(R.color.resultgood).show();
                                                         } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                             new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                                                         }
                                                     } else {
                                                         new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                                                     }
                                                 } else {
                                                     if (RootTools.isAccessGiven()) {
                                                         @SuppressLint("SdCardPath") Command deletetweak = new Command(0,
                                                                 "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                 "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                 "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                 "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/debug.egl.swapinterval/d' /system/build.prop",
                                                                 "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                 "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                 "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                                         );
                                                         try {
                                                             RootTools.getShell(true).add(deletetweak);
                                                             new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakdisabled)).withBackgroundColorId(R.color.resultgood).show();
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

        //////////////////////////////////
        ////// Android 7 Tweaks //////////
        /////////////////////////////////
        CheckBox and7tweaks = view.findViewById(R.id.andrusha7tweaks);
        if ((Build.VERSION.SDK_INT >= 24)) {
            and7tweaks.setEnabled(true);
        } else {
            and7tweaks.setEnabled(false);
        }
        if (HelperClass.BuildPropText().toString().contains("dalvik.vm.jitthreshold=15000")
                && HelperClass.BuildPropText().toString().contains("dalvik.vm.usejitprofiles=true")
                && HelperClass.BuildPropText().toString().contains("dalvik.vm.jitprithreadweight=750")
                && HelperClass.BuildPropText().toString().contains("dalvik.vm.jittransitionweight=1500")) {
            and7tweaks.setChecked(true);
        } else {
            and7tweaks.setChecked(false);
        }
        and7tweaks.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                AboutTweak(R.string.and7tizer);
                return true;
            }
        });
        and7tweaks.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


                                                  @Override
                                                  public void onCheckedChanged(CompoundButton buttonView,
                                                                               boolean isChecked) {
                                                      if (isChecked) {
                                                          if (RootTools.isAccessGiven()) {
                                                              @SuppressLint("SdCardPath") Command installtweak = new Command(0,
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/dalvik.vm.jitthreshold/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/dalvik.vm.usejitprofiles/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/dalvik.vm.jitprithreadweight/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/dalvik.vm.jittransitionweight/d' /system/build.prop",
                                                                      "echo \"dalvik.vm.jitthreshold=15000\" >> /system/build.prop",
                                                                      "echo \"dalvik.vm.usejitprofiles=true\" >> /system/build.prop",
                                                                      "echo \"dalvik.vm.jitprithreadweight=750\" >> /system/build.prop",
                                                                      "echo \"dalvik.vm.jittransitionweight=1500\" >> /system/build.prop",
                                                                      "setprop dalvik.vm.jitthreshold 15000",
                                                                      "setprop dalvik.vm.usejitprofiles true",
                                                                      "setprop dalvik.vm.jitprithreadweight 750",
                                                                      "setprop dalvik.vm.jittransitionweight 1500",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                                              );
                                                              try {
                                                                  RootTools.getShell(true).add(installtweak);
                                                                  new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakenabled)).withBackgroundColorId(R.color.resultgood).show();
                                                              } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                                  new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                                                              }
                                                          } else {
                                                              new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                                                          }
                                                      } else {
                                                          if (RootTools.isAccessGiven()) {
                                                              @SuppressLint("SdCardPath") Command deletetweak = new Command(0,
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/dalvik.vm.jitthreshold/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/dalvik.vm.usejitprofiles/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/dalvik.vm.jitprithreadweight/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/dalvik.vm.jittransitionweight/d' /system/build.prop",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                                              );
                                                              try {
                                                                  RootTools.getShell(true).add(deletetweak);
                                                                  new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakdisabled)).withBackgroundColorId(R.color.resultgood).show();
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

        //////////////////////////////////////
        ///// FreeForm window mode tweak /////
        /////////////////////////////////////
        CheckBox freeform = view.findViewById(R.id.freemulti);
        if (HelperClass.BuildPropText().toString().contains("ro.config.low_ram=true")) {
            freeform.setEnabled(false);
        } else {
            if ((Build.VERSION.SDK_INT >= 24)) {
                freeform.setEnabled(true);
            } else {
                freeform.setEnabled(false);
            }
        }
        if (new File("/system/etc/permissions/android.software.freeform_window_management.xml").exists()) {
            freeform.setChecked(true);
        } else {
            freeform.setChecked(false);
        }

        freeform.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                AboutTweak(R.string.and7freeform);
                return true;
            }
        });
        freeform.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


                                                @Override
                                                public void onCheckedChanged(CompoundButton buttonView,
                                                                             boolean isChecked) {
                                                    if (isChecked) {
                                                        if (RootTools.isAccessGiven()) {
                                                            @SuppressLint("SdCardPath") Command installtweak = new Command(0,
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                    "cp /data/data/com.nowenui.systemtweaker/files/android.software.freeform_window_management.xml /system/etc/permissions/",
                                                                    "chmod 644 /system/etc/permissions/android.software.freeform_window_management.xml",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                                            );
                                                            try {
                                                                RootTools.getShell(true).add(installtweak);
                                                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakenabled)).withBackgroundColorId(R.color.resultgood).show();
                                                            } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                                                            }
                                                        } else {
                                                            new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                                                        }
                                                    } else {
                                                        if (RootTools.isAccessGiven()) {
                                                            @SuppressLint("SdCardPath") Command deletetweak = new Command(0,
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                    "rm -f /system/etc/permissions/android.software.freeform_window_management.xml",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                                            );
                                                            try {
                                                                RootTools.getShell(true).add(deletetweak);
                                                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakdisabled)).withBackgroundColorId(R.color.resultgood).show();
                                                                RebootDialog();
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

        ///////////////////////////////////////////
        ////// Display Calibration Tweak //////////
        //////////////////////////////////////////
        CheckBox display_cal = view.findViewById(R.id.display_cal);
        if (HelperClass.isInitdSupport() == 0) {
            display_cal.setEnabled(false);
        } else {
            display_cal.setEnabled(true);
        }
        if (new File("system/etc/init.d/display").exists()) {
            display_cal.setChecked(true);
        } else {
            display_cal.setChecked(false);
        }
        display_cal.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                AboutTweak(R.string.sys2);
                return true;
            }
        });
        display_cal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


                                                   @Override
                                                   public void onCheckedChanged(CompoundButton buttonView,
                                                                                boolean isChecked) {
                                                       if (isChecked) {
                                                           if (RootTools.isAccessGiven()) {
                                                               @SuppressLint("SdCardPath") Command installtweak = new Command(0,
                                                                       "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                       "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                       "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                       "cp /data/data/com.nowenui.systemtweaker/files/display /system/etc/init.d/",
                                                                       "chmod 777 /system/etc/init.d/display",
                                                                       "cp /data/data/com.nowenui.systemtweaker/files/ad_calib.cfg /system/etc/",
                                                                       "chmod 755 /system/etc/ad_calib.cfg",
                                                                       "/system/etc/init.d/display",
                                                                       "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                       "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                       "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system");
                                                               try {
                                                                   RootTools.getShell(true).add(installtweak);
                                                                   new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakenabled)).withBackgroundColorId(R.color.resultgood).show();
                                                               } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                                   new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                                                               }
                                                           } else {
                                                               new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                                                           }
                                                       } else {
                                                           if (RootTools.isAccessGiven()) {
                                                               @SuppressLint("SdCardPath") Command deletetweak = new Command(0,
                                                                       "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                       "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                       "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                       "rm -f /system/etc/init.d/display",
                                                                       "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                       "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                       "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                                               );
                                                               try {
                                                                   RootTools.getShell(true).add(deletetweak);
                                                                   new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakdisabled)).withBackgroundColorId(R.color.resultgood).show();
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

        ////////////////////////////////////
        ////// Smooth tweaks //////////////
        ///////////////////////////////////
        CheckBox smoothtweaks = view.findViewById(R.id.smoothtweaks);
        if (HelperClass.BuildPropText().toString().contains("windowsmgr.max_events_per_sec=150")
                && HelperClass.BuildPropText().toString().contains("ro.min_pointer_dur=8")
                && HelperClass.BuildPropText().toString().contains("persist.sys.scrollingcache=3")
                && HelperClass.BuildPropText().toString().contains("ro.max.fling_velocity=12000")
                && HelperClass.BuildPropText().toString().contains("ro.min.fling_velocity=8000")) {
            smoothtweaks.setChecked(true);
        } else {
            smoothtweaks.setChecked(false);
        }
        smoothtweaks.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                AboutTweak(R.string.sys5);
                return true;
            }
        });
        smoothtweaks.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                                    @Override
                                                    public void onCheckedChanged(CompoundButton buttonView,
                                                                                 boolean isChecked) {
                                                        if (isChecked) {
                                                            if (RootTools.isAccessGiven()) {
                                                                @SuppressLint("SdCardPath") Command installtweak = new Command(0,
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/windowsmgr.max_events_per_sec/d' /system/build.prop",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/persist.sys.scrollingcache/d' /system/build.prop",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.min_pointer_dur/d' /system/build.prop",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.max.fling_velocity/d' /system/build.prop",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.min.fling_velocity/d' /system/build.prop",
                                                                        "echo \"ro.min_pointer_dur=8\" >> /system/build.prop",
                                                                        "echo \"persist.sys.scrollingcache=3\" >> /system/build.prop",
                                                                        "echo \"ro.max.fling_velocity=12000\" >> /system/build.prop",
                                                                        "echo \"ro.min.fling_velocity=8000\" >> /system/build.prop",
                                                                        "echo \"windowsmgr.max_events_per_sec=150\" >> /system/build.prop",
                                                                        "setprop ro.min_pointer_dur 8",
                                                                        "setprop persist.sys.scrollingcache 3",
                                                                        "setprop ro.max.fling_velocity 12000",
                                                                        "setprop ro.min.fling_velocity 8000",
                                                                        "setprop windowsmgr.max_events_per_sec 150",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                                                );
                                                                try {
                                                                    RootTools.getShell(true).add(installtweak);
                                                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakenabled)).withBackgroundColorId(R.color.resultgood).show();
                                                                } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                                                                }
                                                            } else {
                                                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                                                            }
                                                        } else {
                                                            if (RootTools.isAccessGiven()) {
                                                                @SuppressLint("SdCardPath") Command deletetweak = new Command(0,
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/windowsmgr.max_events_per_sec/d' /system/build.prop",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/persist.sys.scrollingcache/d' /system/build.prop",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.min_pointer_dur/d' /system/build.prop",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.max.fling_velocity/d' /system/build.prop",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/ro.min.fling_velocity/d' /system/build.prop",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                        "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                                                );
                                                                try {
                                                                    RootTools.getShell(true).add(deletetweak);
                                                                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakdisabled)).withBackgroundColorId(R.color.resultgood).show();
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

        ///////////////////////////////////
        ////// TouchScreen Tweak //////////
        //////////////////////////////////
        CheckBox touchtweak = view.findViewById(R.id.touchtweak);
        if (HelperClass.isInitdSupport() == 0) {
            touchtweak.setEnabled(false);
        } else {
            touchtweak.setEnabled(true);
        }
        if (new File("/system/etc/init.d/touch").exists()) {
            touchtweak.setChecked(true);
        } else {
            touchtweak.setChecked(false);
        }
        touchtweak.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                AboutTweak(R.string.tw);
                return true;
            }
        });
        touchtweak.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                                  @Override
                                                  public void onCheckedChanged(CompoundButton buttonView,
                                                                               boolean isChecked) {
                                                      if (isChecked) {
                                                          if (RootTools.isAccessGiven()) {
                                                              @SuppressLint("SdCardPath") Command installtweak = new Command(0,
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                      "cp /data/data/com.nowenui.systemtweaker/files/touch /system/etc/init.d/",
                                                                      "chmod 777 /system/etc/init.d/touch",
                                                                      "/system/etc/init.d/touch",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                                              );
                                                              try {
                                                                  RootTools.getShell(true).add(installtweak);
                                                                  new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakenabled)).withBackgroundColorId(R.color.resultgood).show();
                                                              } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                                  new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                                                              }
                                                          } else {
                                                              new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                                                          }
                                                      } else {
                                                          if (RootTools.isAccessGiven()) {
                                                              @SuppressLint("SdCardPath") Command deletetweak = new Command(0,
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                      "rm -f /system/etc/init.d/touch",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                      "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                                              );
                                                              try {
                                                                  RootTools.getShell(true).add(deletetweak);
                                                                  new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakdisabled)).withBackgroundColorId(R.color.resultgood).show();
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

        ////////////////////////////////
        ////// Zipalign Tweak //////////
        ///////////////////////////////
        CheckBox zipaligntweak = view.findViewById(R.id.zipaligntweak);
        if (HelperClass.isInitdSupport() == 0) {
            zipaligntweak.setEnabled(false);
        } else {
            zipaligntweak.setEnabled(true);
        }
        if (new File("/system/etc/init.d/93zipalign").exists()) {
            zipaligntweak.setChecked(true);
        } else {
            zipaligntweak.setChecked(false);
        }
        zipaligntweak.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                AboutTweak(R.string.sys6);
                return true;
            }
        });
        zipaligntweak.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                                     @Override
                                                     public void onCheckedChanged(CompoundButton buttonView,
                                                                                  boolean isChecked) {
                                                         if (isChecked) {
                                                             if (RootTools.isAccessGiven()) {
                                                                 @SuppressLint("SdCardPath") Command installtweak = new Command(0,
                                                                         "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                         "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                         "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                         "cp /data/data/com.nowenui.systemtweaker/files/zipalign /system/xbin/zipalign",
                                                                         "chmod 755 /system/xbin/zipalign",
                                                                         "cp /data/data/com.nowenui.systemtweaker/files/93zipalign /system/etc/init.d/93zipalign",
                                                                         "chmod 777 /system/etc/init.d/93zipalign",
                                                                         "/system/etc/init.d/93zipalign",
                                                                         "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                         "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                         "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                                                 );
                                                                 try {
                                                                     RootTools.getShell(true).add(installtweak);
                                                                     TweakEnableOrDisableDialog(R.string.tweakenabled);
                                                                 } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                                     new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                                                                 }
                                                             } else {
                                                                 new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                                                             }
                                                         } else {
                                                             if (RootTools.isAccessGiven()) {
                                                                 @SuppressLint("SdCardPath") Command deletetweak = new Command(0,
                                                                         "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                         "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                         "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                         "rm -f /system/xbin/zipalign",
                                                                         "rm -f /system/etc/init.d/93zipalign",
                                                                         "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                         "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                         "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                                                 );
                                                                 try {
                                                                     RootTools.getShell(true).add(deletetweak);
                                                                     TweakEnableOrDisableDialog(R.string.tweakdisabled);
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

        ///////////////////////////////
        ////// SQLite Tweaks //////////
        //////////////////////////////
        CheckBox sqlitetweak = view.findViewById(R.id.sqlitetweak);
        if (HelperClass.isInitdSupport() == 0) {
            sqlitetweak.setEnabled(false);
        } else {
            sqlitetweak.setEnabled(true);
        }
        if (new File("/system/etc/init.d/11sqlite").exists()) {
            sqlitetweak.setChecked(true);
        } else {
            sqlitetweak.setChecked(false);
        }
        sqlitetweak.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                AboutTweak(R.string.sys7);
                return true;
            }
        });
        sqlitetweak.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                                   @Override
                                                   public void onCheckedChanged(CompoundButton buttonView,
                                                                                boolean isChecked) {
                                                       if (isChecked) {
                                                           if (RootTools.isAccessGiven()) {
                                                               @SuppressLint("SdCardPath") Command installtweak = new Command(0,
                                                                       "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                       "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                       "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                       "cp /data/data/com.nowenui.systemtweaker/files/sqlite3 /system/xbin/sqlite3",
                                                                       "chmod 755 /system/xbin/sqlite3",
                                                                       "cp /data/data/com.nowenui.systemtweaker/files/11sqlite /system/etc/init.d/11sqlite",
                                                                       "chmod 777 /system/etc/init.d/11sqlite",
                                                                       "/system/etc/init.d/11sqlite",
                                                                       "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                       "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                       "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                                               );
                                                               try {
                                                                   RootTools.getShell(true).add(installtweak);
                                                                   TweakEnableOrDisableDialog(R.string.tweakenabled);
                                                               } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                                   new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                                                               }
                                                           } else {
                                                               new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                                                           }
                                                       } else {
                                                           if (RootTools.isAccessGiven()) {
                                                               @SuppressLint("SdCardPath") Command deletetweak = new Command(0,
                                                                       "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                       "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                       "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                       "rm -f /system/xbin/sqlite3",
                                                                       "rm -f /system/etc/init.d/11sqlite",
                                                                       "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                       "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                       "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                                               );
                                                               try {
                                                                   RootTools.getShell(true).add(deletetweak);
                                                                   TweakEnableOrDisableDialog(R.string.tweakdisabled);
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

        TextView alertheader = view.findViewById(R.id.alertheader);
        final SharedPreferences mSharedPreference = PreferenceManager.getDefaultSharedPreferences(getContext());
        if (mSharedPreference.contains("alerttweaks")) {
            alertheader.setVisibility(View.VISIBLE);
        } else {
            alertheader.setVisibility(View.GONE);
        }

        //////////////////////////////////////
        ///// Dangerous tweak: ART Tweak /////
        //////////////////////////////////////
        CheckBox artfix = view.findViewById(R.id.artfix);
        if (mSharedPreference.contains("alerttweaks")) {
            artfix.setVisibility(View.VISIBLE);
        } else {
            artfix.setVisibility(View.GONE);
        }
        if ((Build.VERSION.SDK_INT >= 21)) {
            if (HelperClass.isInitdSupport() == 0) {
                artfix.setEnabled(false);
            } else {
                artfix.setEnabled(true);
            }
        } else {
            artfix.setEnabled(false);
        }
        if (HelperClass.BuildPropText().toString().contains("dalvik.vm.dex2oat-filter=speed") &&
                HelperClass.BuildPropText().toString().contains("dalvik.vm.image-dex2oat-filter=speed")) {
            artfix.setChecked(true);
        } else {
            artfix.setChecked(false);
        }
        artfix.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                AboutTweak(R.string.art);
                return true;
            }
        });
        artfix.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    if (RootTools.isAccessGiven()) {
                        @SuppressLint("SdCardPath") Command installtweak = new Command(0,
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                "cp /data/data/com.nowenui.systemtweaker/files/art_fix /system/etc/",
                                "chmod 777 /system/etc/art_fix",
                                "/system/etc/art_fix",
                                "rm -f /system/etc/art_fix",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system");
                        try {
                            RootTools.getShell(true).add(installtweak);
                            new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakenabled)).withBackgroundColorId(R.color.resultgood).show();
                            RebootDialog();
                        } catch (IOException | RootDeniedException | TimeoutException ex) {
                            new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                        }
                    } else {
                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                    }
                } else {
                    if (RootTools.isAccessGiven()) {
                        @SuppressLint("SdCardPath") Command deletetweak = new Command(0,
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /data",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /data", "mount -o rw,remount /data",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /data",
                                "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/dalvik.vm.dex2oat-filter/d' /system/build.prop",
                                "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/dalvik.vm.image-dex2oat-filter/d' /system/build.prop",
                                "rm -rf /data/dalvik-cache",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /data",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /data", "mount -o ro,remount /data",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /data"
                        );
                        try {
                            RootTools.getShell(true).add(deletetweak);
                            new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakdisabled)).withBackgroundColorId(R.color.resultgood).show();
                            RebootDialog();
                        } catch (IOException | RootDeniedException | TimeoutException ex) {
                            new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                        }
                    } else {
                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                    }
                }
            }
        });

        /////////////////////////////////////////////////////////////////
        ///// Dangerous tweak: Heap and runtime optimization ///////////
        ////////////////////////////////////////////////////////////////
        CheckBox heaptweak = view.findViewById(R.id.heaptweak);
        if (mSharedPreference.contains("alerttweaks")) {
            heaptweak.setVisibility(View.VISIBLE);
        } else {
            heaptweak.setVisibility(View.GONE);
        }
        if (easyMemoryMod.getTotalRAM() <= 256) {
            heap = 64;
            grow = 24;
        }
        if ((easyMemoryMod.getTotalRAM() <= 512) && (easyMemoryMod.getTotalRAM() > 256)) {
            heap = 128;
            grow = 48;
        }
        if ((easyMemoryMod.getTotalRAM() <= 1024) && (easyMemoryMod.getTotalRAM() > 512)) {
            heap = 256;
            grow = 96;
        }
        if ((easyMemoryMod.getTotalRAM() <= 2048) && (easyMemoryMod.getTotalRAM() > 1024)) {
            heap = 512;
            grow = 256;
        }
        if ((easyMemoryMod.getTotalRAM() <= 3072) && (easyMemoryMod.getTotalRAM() > 2048)) {
            heap = 1024;
            grow = 512;
        }
        if ((easyMemoryMod.getTotalRAM() <= 4096) && (easyMemoryMod.getTotalRAM() > 3072)) {
            heap = 2048;
            grow = 1024;
        }
        if (HelperClass.BuildPropText().toString().contains("dalvik.vm.heapsize=" + heap + "m")
                && HelperClass.BuildPropText().toString().contains("dalvik.vm.heaptargetutilization=0.75")
                && HelperClass.BuildPropText().toString().contains("dalvik.vm.heapgrowthlimit=" + grow + "m")
                && HelperClass.BuildPropText().toString().contains("dalvik.vm.check-dex-sum=false")
                && HelperClass.BuildPropText().toString().contains("dalvik.vm.checkjni=false")
                && HelperClass.BuildPropText().toString().contains("dalvik.vm.execution-mode=jit")) {
            heaptweak.setChecked(true);
        } else {
            heaptweak.setChecked(false);
        }
        heaptweak.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                AboutTweak(R.string.heaptizer);
                return true;
            }
        });
        heaptweak.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    if (RootTools.isAccessGiven()) {
                        @SuppressLint("SdCardPath") Command installtweak = new Command(0,
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/dalvik.vm.heapsize/d' /system/build.prop",
                                "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/dalvik.vm.heaptargetutilization/d' /system/build.prop",
                                "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/dalvik.vm.heapgrowthlimit/d' /system/build.prop",
                                "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/dalvik.vm.check-dex-sum/d' /system/build.prop",
                                "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/dalvik.vm.checkjni/d' /system/build.prop",
                                "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/dalvik.vm.execution-mode/d' /system/build.prop",
                                "echo \"dalvik.vm.heapsize=" + heap + "m\" >> /system/build.prop",
                                "echo \"dalvik.vm.heaptargetutilization=0.75\" >> /system/build.prop",
                                "echo \"dalvik.vm.heapgrowthlimit=" + grow + "m\" >> /system/build.prop",
                                "echo \"dalvik.vm.check-dex-sum=false\" >> /system/build.prop",
                                "echo \"dalvik.vm.checkjni=false\" >> /system/build.prop",
                                "echo \"dalvik.vm.execution-mode=jit\" >> /system/build.prop",
                                "setprop dalvik.vm.heapsize " + heap + "m",
                                "setprop dalvik.vm.heaptargetutilization 0.75",
                                "setprop dalvik.vm.heapgrowthlimit " + grow + "m",
                                "setprop dalvik.vm.check-dex-sum false",
                                "setprop dalvik.vm.checkjni false",
                                "setprop dalvik.vm.execution-mode jit",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                        );
                        try {
                            RootTools.getShell(true).add(installtweak);
                            new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakenabled)).withBackgroundColorId(R.color.resultgood).show();
                        } catch (IOException | RootDeniedException | TimeoutException ex) {
                            new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                        }
                    } else {
                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                    }
                } else {
                    if (RootTools.isAccessGiven()) {
                        @SuppressLint("SdCardPath") Command deletetweak = new Command(0,
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/dalvik.vm.heapsize/d' /system/build.prop",
                                "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/dalvik.vm.heaptargetutilization/d' /system/build.prop",
                                "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/dalvik.vm.heapgrowthlimit/d' /system/build.prop",
                                "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/dalvik.vm.check-dex-sum/d' /system/build.prop",
                                "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/dalvik.vm.checkjni/d' /system/build.prop",
                                "/data/data/com.nowenui.systemtweaker/files/busybox sed -i '/dalvik.vm.execution-mode/d' /system/build.prop",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                        );
                        try {
                            RootTools.getShell(true).add(deletetweak);
                            new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakdisabled)).withBackgroundColorId(R.color.resultgood).show();
                        } catch (IOException | RootDeniedException | TimeoutException ex) {
                            new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                        }
                    } else {
                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                    }
                }
            }
        });

        ///////////////////////////////////////////////////////////////////////
        ///// Dangerous tweak: Move dalvik-cache to cache tweak ///////////////
        ///////////////////////////////////////////////////////////////////////
        CheckBox cachetransfer = view.findViewById(R.id.cachetransfer);
        if (HelperClass.isInitdSupport() == 0) {
            cachetransfer.setEnabled(false);
        } else {
            cachetransfer.setEnabled(true);
        }
        if (mSharedPreference.contains("alerttweaks")) {
            cachetransfer.setVisibility(View.VISIBLE);
        } else {
            cachetransfer.setVisibility(View.GONE);
        }
        if (new File("/system/etc/init.d/09cachetransfe").exists()) {
            cachetransfer.setChecked(true);
        } else {
            cachetransfer.setChecked(false);
        }
        cachetransfer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    if (RootTools.isAccessGiven()) {
                        @SuppressLint("SdCardPath") Command installtweak = new Command(0,
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                "cp /data/data/com.nowenui.systemtweaker/files/09cachetransfer /system/etc/init.d/",
                                "chmod 777 /system/etc/init.d/09cachetransfer",
                                "/system/etc/init.d/09cachetransfer",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system");
                        try {
                            RootTools.getShell(true).add(installtweak);
                            new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakenabled)).withBackgroundColorId(R.color.resultgood).show();
                        } catch (IOException | RootDeniedException | TimeoutException ex) {
                            new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                        }
                    } else {
                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                    }
                } else {
                    if (RootTools.isAccessGiven()) {
                        @SuppressLint("SdCardPath") Command deletetweak = new Command(0,
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                "rm -f /system/etc/init.d/09cachetransfer",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                        );
                        try {
                            RootTools.getShell(true).add(deletetweak);
                            new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakdisabled)).withBackgroundColorId(R.color.resultgood).show();
                        } catch (IOException | RootDeniedException | TimeoutException ex) {
                            new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.resultbad).show();
                        }
                    } else {
                        new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.resultbad).show();
                    }
                }
            }
        });

    }

    public void AboutTweak(int tweaktext) {
        if (ThemeUtility.getTheme(getActivity().getApplicationContext()) == 1) {
            new AlertDialog.Builder(getContext())
                    .setTitle(R.string.tweakabout)
                    .setMessage(tweaktext)
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .setIcon(R.drawable.warning)
                    .show();
        }
        if (ThemeUtility.getTheme(getActivity().getApplicationContext()) == 2) {
            new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark))
                    .setTitle(R.string.tweakabout)
                    .setMessage(tweaktext)
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .setIcon(R.drawable.warning)
                    .show();
        }
        if (ThemeUtility.getTheme(getActivity().getApplicationContext()) == 3) {
            new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack))
                    .setTitle(R.string.tweakabout)
                    .setMessage(tweaktext)
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .setIcon(R.drawable.warning)
                    .show();
        }
    }

    public void RebootDialog() {
        if (ThemeUtility.getTheme(getActivity().getApplicationContext()) == 1) {
            new AlertDialog.Builder(getContext())
                    .setTitle(R.string.reboot)
                    .setMessage(R.string.sucessreboot)
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            try {
                                Process proc = Runtime.getRuntime().exec(new String[]{"su", "-c", "reboot"});
                                proc.waitFor();
                            } catch (Exception ignored) {
                                new SnackBar.Builder(getActivity()).withMessage("ROOT NEEDED!").withBackgroundColorId(R.color.resultbad).show();
                            }
                        }
                    })
                    .setIcon(R.drawable.warning)
                    .show();
        }
        if (ThemeUtility.getTheme(getActivity().getApplicationContext()) == 2) {
            new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark))
                    .setTitle(R.string.reboot)
                    .setMessage(R.string.sucessreboot)
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            try {
                                Process proc = Runtime.getRuntime().exec(new String[]{"su", "-c", "reboot"});
                                proc.waitFor();
                            } catch (Exception ignored) {
                                new SnackBar.Builder(getActivity()).withMessage("ROOT NEEDED!").withBackgroundColorId(R.color.resultbad).show();
                            }
                        }
                    })
                    .setIcon(R.drawable.warning)
                    .show();
        }
        if (ThemeUtility.getTheme(getActivity().getApplicationContext()) == 3) {
            new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack))
                    .setTitle(R.string.tweakabout)
                    .setMessage(R.string.and7freeform)
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .setIcon(R.drawable.warning)
                    .show();
        }
    }

    public void TweakEnableOrDisableDialog(final int status) {
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
                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(status)).withBackgroundColorId(R.color.resultgood).show();
                }
            }, 10000);
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
                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(status)).withBackgroundColorId(R.color.resultgood).show();
                }
            }, 10000);
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
                    new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(status)).withBackgroundColorId(R.color.resultgood).show();
                }
            }, 10000);
        }
    }

}