package com.nowenui.systemtweaker.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;

import com.github.mrengineer13.snackbar.SnackBar;
import com.nowenui.systemtweaker.Constants;
import com.nowenui.systemtweaker.Frequency;
import com.nowenui.systemtweaker.R;
import com.nowenui.systemtweaker.SysUtils;
import com.nowenui.systemtweaker.Utility;
import com.onebit.spinner2.Spinner2;
import com.stericson.RootShell.exceptions.RootDeniedException;
import com.stericson.RootShell.execution.Command;
import com.stericson.RootTools.RootTools;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.TimeoutException;

public class KernelFragment extends Fragment {

    public static KernelFragment newInstance(Bundle bundle) {
        KernelFragment KernelTweaks = new KernelFragment();

        if (bundle != null) {
            KernelTweaks.setArguments(bundle);
        }

        return KernelTweaks;
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
        return inflater.inflate(R.layout.fragment_kerneltweaks, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        ///////////////////////////////
        ////// SDCard Tweak ///////////
        ////////////////////////////////
        CheckBox sdtweak = view.findViewById(R.id.sdcardtweak);
        String one = "/etc/init.d/sdcard_tweak";
        String one1 = "/system/etc/init.d/sdcard_tweak";
        final SharedPreferences mSharedPreference = PreferenceManager.getDefaultSharedPreferences(getContext());
        if (mSharedPreference.contains("skipnitd")) {
            sdtweak.setEnabled(false);
        } else {
            sdtweak.setEnabled(true);
        }
        if (new File(Environment.getRootDirectory() + one).exists() || new File(one1).exists() || new File(Environment.getRootDirectory() + one1).exists()) {
            sdtweak.setChecked(true);
        } else {
            sdtweak.setChecked(false);
        }
        sdtweak.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                if (Utility.getTheme(getActivity().getApplicationContext()) == 1) {
                    new AlertDialog.Builder(getContext())
                            .setTitle(R.string.tweakabout)
                            .setMessage(R.string.ker1)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(R.drawable.warning)
                            .show();
                }
                if (Utility.getTheme(getActivity().getApplicationContext()) == 2) {
                    new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark))
                            .setTitle(R.string.tweakabout)
                            .setMessage(R.string.ker1)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(R.drawable.warning)
                            .show();
                }
                if (Utility.getTheme(getActivity().getApplicationContext()) == 3) {
                    new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack))
                            .setTitle(R.string.tweakabout)
                            .setMessage(R.string.ker1)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(R.drawable.warning)
                            .show();
                }

                return true;
            }
        });
        sdtweak.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


                                               @Override
                                               public void onCheckedChanged(CompoundButton buttonView,
                                                                            boolean isChecked) {

                                                   if (isChecked) {

                                                       if (RootTools.isAccessGiven()) {
                                                           Command command1 = new Command(0,
                                                                   "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                   "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                   "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                   "cp /data/data/com.nowenui.systemtweaker/files/sdcard_tweak /system/etc/init.d/",
                                                                   "chmod 777 /system/etc/init.d/sdcard_tweak",
                                                                   "/system/etc/init.d/sdcard_tweak",
                                                                   "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                   "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                   "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system");
                                                           try {
                                                               RootTools.getShell(true).add(command1);
                                                               new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakenabled)).withBackgroundColorId(R.color.textview1good).show();
                                                           } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                               ex.printStackTrace();
                                                               new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                                                           }
                                                       } else {
                                                           new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.textview1bad).show();
                                                       }


                                                   } else {

                                                       if (RootTools.isAccessGiven()) {
                                                           Command command1 = new Command(0,
                                                                   "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                   "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                   "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                   "rm -f /system/etc/init.d/sdcard_tweak",
                                                                   "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                   "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                   "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                                           );
                                                           try {
                                                               RootTools.getShell(true).add(command1);
                                                               new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakdisabled)).withBackgroundColorId(R.color.textview1good).show();
                                                           } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                               ex.printStackTrace();
                                                               new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                                                           }
                                                       } else {
                                                           new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.textview1bad).show();
                                                       }

                                                   }

                                               }
                                           }

        );


        //////////////////////////////
        ////// I/O Tweaks ////////////
        //////////////////////////////
        CheckBox iotweak = view.findViewById(R.id.iotweaks);
        String five = "/etc/init.d/io_tweak";
        String five1 = "/system/etc/init.d/io_tweak";
        if ((new File(Environment.getRootDirectory() + five).exists() || new File(five1).exists() || new File(Environment.getRootDirectory() + five1).exists())) {
            iotweak.setChecked(true);
        } else {
            iotweak.setChecked(false);
        }
        if (mSharedPreference.contains("skipnitd")) {
            iotweak.setEnabled(false);
        } else {
            iotweak.setEnabled(true);
        }
        iotweak.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                if (Utility.getTheme(getActivity().getApplicationContext()) == 1) {
                    new AlertDialog.Builder(getContext())
                            .setTitle(R.string.tweakabout)
                            .setMessage(R.string.ker4)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(R.drawable.warning)
                            .show();
                }
                if (Utility.getTheme(getActivity().getApplicationContext()) == 2) {
                    new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark))
                            .setTitle(R.string.tweakabout)
                            .setMessage(R.string.ker4)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(R.drawable.warning)
                            .show();
                }
                if (Utility.getTheme(getActivity().getApplicationContext()) == 3) {
                    new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack))
                            .setTitle(R.string.tweakabout)
                            .setMessage(R.string.ker4)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(R.drawable.warning)
                            .show();
                }

                return true;
            }
        });
        iotweak.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


                                               @Override
                                               public void onCheckedChanged(CompoundButton buttonView,
                                                                            boolean isChecked) {

                                                   if (isChecked) {


                                                       if (RootTools.isAccessGiven()) {
                                                           Command command1 = new Command(0,
                                                                   "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                   "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                   "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                   "cp /data/data/com.nowenui.systemtweaker/files/io_tweak /system/etc/init.d/",
                                                                   "chmod 777 /system/etc/init.d/io_tweak",
                                                                   "/system/etc/init.d/io_tweak",
                                                                   "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                   "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                   "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system");
                                                           try {
                                                               RootTools.getShell(true).add(command1);
                                                               new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakenabled)).withBackgroundColorId(R.color.textview1good).show();
                                                           } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                               ex.printStackTrace();
                                                               new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                                                           }
                                                       } else {
                                                           new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.textview1bad).show();
                                                       }


                                                   } else {

                                                       if (RootTools.isAccessGiven()) {
                                                           Command command1 = new Command(0,
                                                                   "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                   "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                   "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                   "rm -f /system/etc/init.d/io_tweak",
                                                                   "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                   "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                   "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                                           );
                                                           try {
                                                               RootTools.getShell(true).add(command1);
                                                               new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakdisabled)).withBackgroundColorId(R.color.textview1good).show();
                                                           } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                               ex.printStackTrace();
                                                               new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                                                           }
                                                       } else {
                                                           new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.textview1bad).show();
                                                       }
                                                   }

                                               }
                                           }

        );

        //////////////////////////////////////////////////////////
        ////// Kernel Freq/Sheduler/Governor settings ////////////
        /////////////////////////////////////////////////////////
        final Spinner mSpnMinFreq = view.findViewById(R.id.sp_min_freq);
        final Spinner mSpnMaxFreq = view.findViewById(R.id.sp_max_freq);
        final Spinner mSpnGovernor = view.findViewById(R.id.sp_governor);
        final Spinner mSpnIOScheduler = view.findViewById(R.id.sp_ioscheduler);
        final CheckBox mCkbApplyOnBoot = view.findViewById(R.id.ckb_apply_on_boot);
        final Button mBtnApply = view.findViewById(R.id.btn_apply);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                final SharedPreferences prefs2 = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
                mCkbApplyOnBoot.setChecked(prefs2.getBoolean(Constants.PREF_APPLY_ON_BOOT, false));

                mCkbApplyOnBoot.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        SharedPreferences.Editor editor = prefs2.edit();
                        editor.putBoolean(Constants.PREF_APPLY_ON_BOOT, isChecked);
                        editor.apply();
                    }
                });

                String[] frequencies = SysUtils.getAvailableFrequencies();
                Frequency min_frequency = SysUtils.getMinFreq();
                Frequency max_frequency = SysUtils.getMaxFreq();
                if (frequencies != null && min_frequency != null && max_frequency != null) {

                    Frequency[] freqs = new Frequency[frequencies.length];
                    for (int i = 0; i < frequencies.length; i++) {
                        freqs[i] = new Frequency(frequencies[i]);
                    }

                    Frequency requested_min_freq = new Frequency(prefs2.getString(Constants.PREF_MIN_FREQ, min_frequency.getValue()));
                    Frequency requested_max_freq = new Frequency(prefs2.getString(Constants.PREF_MAX_FREQ, max_frequency.getValue()));

                    ArrayAdapter<Frequency> minFreqAdapter = new ArrayAdapter<>(getActivity(),
                            android.R.layout.simple_spinner_item, freqs);
                    minFreqAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mSpnMinFreq.setAdapter(minFreqAdapter);
                    mSpnMinFreq.setSelection(minFreqAdapter.getPosition(requested_min_freq));

                    ArrayAdapter<Frequency> maxFreqAdapter = new ArrayAdapter<>(getActivity(),
                            android.R.layout.simple_spinner_item, freqs);
                    maxFreqAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mSpnMaxFreq.setAdapter(maxFreqAdapter);
                    mSpnMaxFreq.setSelection(maxFreqAdapter.getPosition(requested_max_freq));
                } else {
                    mSpnMinFreq.setEnabled(false);
                    mSpnMaxFreq.setEnabled(false);
                }

                String governor = SysUtils.getGovernor();
                String[] governors = SysUtils.getAvailableGovernors();
                if (governor != null && governors != null) {

                    String requested_governor = prefs2.getString(Constants.PREF_GOVERNOR, governor);

                    ArrayAdapter<String> governorAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item,
                            governors);
                    governorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mSpnGovernor.setAdapter(governorAdapter);
                    mSpnGovernor.setSelection(governorAdapter.getPosition(requested_governor));
                } else {
                    mSpnGovernor.setEnabled(false);
                }

                String ioscheduler = SysUtils.getIOScheduler();
                String[] ioschedulers = SysUtils.getAvailableIOSchedulers();
                if (ioscheduler != null && ioschedulers != null) {
                    String requested_ioscheduler = prefs2.getString(Constants.PREF_IOSCHEDULER, ioscheduler);

                    ArrayAdapter<String> ioschedulerAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item,
                            ioschedulers);
                    ioschedulerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mSpnIOScheduler.setAdapter(ioschedulerAdapter);
                    mSpnIOScheduler.setSelection(ioschedulerAdapter.getPosition(requested_ioscheduler));
                } else {
                    mSpnIOScheduler.setEnabled(false);
                }

                mBtnApply.setBackgroundResource(R.drawable.roundbuttoncal);
                mBtnApply.setTextColor(Color.WHITE);
                mBtnApply.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        v.post(new Runnable() {
                            public void run() {
                                String min_freq = null;
                                String max_freq = null;
                                String governor = null;
                                String ioscheduler = null;

                                if (mSpnMinFreq.isEnabled()) {
                                    Frequency aux = (Frequency) mSpnMinFreq.getSelectedItem();
                                    if (aux != null) {
                                        min_freq = aux.getValue();
                                    }
                                }

                                if (mSpnMaxFreq.isEnabled()) {
                                    Frequency aux = (Frequency) mSpnMaxFreq.getSelectedItem();
                                    if (aux != null) {
                                        max_freq = aux.getValue();
                                    }
                                }

                                if (mSpnGovernor.isEnabled()) {
                                    Object aux = mSpnGovernor.getSelectedItem();
                                    if (aux != null) {
                                        governor = aux.toString();
                                    }
                                }

                                if (mSpnIOScheduler.isEnabled()) {
                                    ioscheduler = mSpnIOScheduler.getSelectedItem().toString();
                                }

                                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
                                SharedPreferences.Editor editor = prefs.edit();
                                editor.putBoolean(Constants.CHECK_SHUTDOWN_OK, false);
                                editor.putLong(Constants.LAST_UPTIME, SystemClock.uptimeMillis());
                                editor.commit();

                                boolean changePermissions = prefs.getBoolean(Constants.PREF_CHANGE_PERMISSIONS,
                                        Constants.PREF_DEFAULT_CHANGE_PERMISSIONS);

                                SysUtils.setFrequenciesAndGovernor(min_freq, max_freq, governor, ioscheduler, changePermissions,
                                        getActivity(), R.string.ok, R.string.error);
                                if (Constants.DEBUG) {
                                    if (prefs.getBoolean(Constants.PREF_NOTIFY_ON_BOOT, Constants.PREF_DEFAULT_NOTIFY_ON_BOOT)) {
                                        String pattern = null;
                                        if (prefs.getBoolean(Constants.PREF_VIBRATE, Constants.PREF_DEFAULT_VIBRATE)) {
                                            pattern = prefs.getString(Constants.PREF_VIBRATE_PATTERN, "");
                                        }
                                    }
                                }
                                editor.putString(Constants.PREF_MIN_FREQ, min_freq);
                                editor.putString(Constants.PREF_MAX_FREQ, max_freq);
                                editor.putString(Constants.PREF_GOVERNOR, governor);
                                editor.putString(Constants.PREF_IOSCHEDULER, ioscheduler);
                                editor.commit();
                            }
                        });
                    }
                });
            }
        }, 800);


        //////////////////////////////////////////////
        ////// Kernel Tweaks Light/Hard //////////////
        //////////////////////////////////////////////
        final Spinner2 spinner3 = view.findViewById(R.id.spinner3);
        if (mSharedPreference.contains("skipnitd")) {
            spinner3.setEnabled(false);
        } else {
            spinner3.setEnabled(true);
        }

        final ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(getActivity(), R.array.kernellist, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner3.setAdapter(adapter, false);

        String fuck = "/etc/init.d/light_tweak_kernel";
        String fucka = "/system/etc/init.d/light_tweak_kernel";

        boolean isLangRU = Locale.getDefault().getLanguage().equals("ru");
        boolean isLangBE = Locale.getDefault().getLanguage().equals("be");
        boolean isLangUK = Locale.getDefault().getLanguage().equals("uk");
        if (new File(Environment.getRootDirectory() + fuck).exists() || new File(fucka).exists() || new File(Environment.getRootDirectory() + fucka).exists()) {
            if (isLangRU || isLangBE || isLangUK) {
                final int spinnerPosition2 = adapter.getPosition("Легкие твики");
                spinner3.post(new Runnable() {
                    @Override
                    public void run() {
                        spinner3.setSelection(false, spinnerPosition2);
                    }
                });
            } else {
                final int spinnerPosition1 = adapter.getPosition("'Light tweaks system'");
                spinner3.post(new Runnable() {
                    @Override
                    public void run() {
                        spinner3.setSelection(false, spinnerPosition1);
                    }
                });
            }

        }

        String fuck1 = "/etc/init.d/heavy_tweak_kernel";
        String fuck1a = "/system/etc/init.d/heavy_tweak_kernel";
        if (new File(Environment.getRootDirectory() + fuck1).exists() || new File(fuck1a).exists() || new File(Environment.getRootDirectory() + fuck1a).exists()) {
            if (isLangRU || isLangBE || isLangUK) {
                final int spinnerPosition4 = adapter.getPosition("Тяжелые твики");
                spinner3.post(new Runnable() {
                    @Override
                    public void run() {
                        spinner3.setSelection(false, spinnerPosition4);
                    }
                });
            } else {
                final int spinnerPosition3 = adapter.getPosition("'Heavy tweaks system'");
                spinner3.post(new Runnable() {
                    @Override
                    public void run() {
                        spinner3.setSelection(false, spinnerPosition3);
                    }
                });
            }

        }

        spinner3.post(new Runnable() {
            @Override
            public void run() {
                spinner3.setOnItemSelectedSpinner2Listener(new Spinner2.OnItemSelectedSpinner2Listener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        String[] choose = getResources().getStringArray(R.array.kernellist);

                        if (choose[position].contains("(стандарт)") || choose[position].contains("(standart)")) {
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
                                outputStream.writeBytes("rm -f /system/etc/init.d/heavy_tweak_kernel\n");
                                outputStream.flush();
                                outputStream.writeBytes("rm -f /system/etc/init.d/light_tweak_kernel\n");
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
                            } catch (IOException e) {
                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                            }
                        }
                        if (choose[position].contains("Light tweaks system") || choose[position].contains("Легкие твики")) {
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
                                outputStream.writeBytes("cp /data/data/com.nowenui.systemtweaker/files/light_tweak_kernel /system/etc/init.d/\n");
                                outputStream.flush();
                                outputStream.writeBytes("chmod 777 /system/etc/init.d/light_tweak_kernel\n");
                                outputStream.flush();
                                outputStream.writeBytes("rm -f /system/etc/init.d/heavy_tweak_kernel\n");
                                outputStream.flush();
                                outputStream.writeBytes("/system/etc/init.d/light_tweak_kernel\n");
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
                            } catch (IOException e) {
                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                            }
                        }
                        if (choose[position].contains("Heavy tweaks system") || choose[position].contains("Тяжелые твики")) {
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
                                outputStream.writeBytes("cp /data/data/com.nowenui.systemtweaker/files/heavy_tweak_kernel /system/etc/init.d/\n");
                                outputStream.flush();
                                outputStream.writeBytes("chmod 777 /system/etc/init.d/heavy_tweak_kernel\n");
                                outputStream.flush();
                                outputStream.writeBytes("rm -f /system/etc/init.d/light_tweak_kernel\n");
                                outputStream.flush();
                                outputStream.writeBytes("/system/etc/init.d/heavy_tweak_kernel\n");
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
                            } catch (IOException e) {
                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                            }
                        }
                    }
                });
            }
        });


        ////////////////////////////////////////////
        ////// ZRAM Tweak (if supports) ////////////
        ////////////////////////////////////////////
        if (ZRAM() == 1) {

            CheckBox zram = view.findViewById(R.id.zram);
            String eight = "/etc/init.d/zram";
            String eight1 = "/system/etc/init.d/zram";
            if (mSharedPreference.contains("skipnitd")) {
                zram.setEnabled(false);
            } else {
                zram.setEnabled(true);
            }
            if (new File(Environment.getRootDirectory() + eight).exists() || new File(eight1).exists() || new File(Environment.getRootDirectory() + eight1).exists()) {
                zram.setChecked(true);
            } else {
                zram.setChecked(false);
            }
            zram.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View arg0) {
                    if (Utility.getTheme(getActivity().getApplicationContext()) == 1) {
                        new AlertDialog.Builder(getContext())
                                .setTitle(R.string.tweakabout)
                                .setMessage(R.string.ker9)
                                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                                .setIcon(R.drawable.warning)
                                .show();
                    }
                    if (Utility.getTheme(getActivity().getApplicationContext()) == 2) {
                        new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark))
                                .setTitle(R.string.tweakabout)
                                .setMessage(R.string.ker9)
                                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                                .setIcon(R.drawable.warning)
                                .show();
                    }
                    if (Utility.getTheme(getActivity().getApplicationContext()) == 3) {
                        new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack))
                                .setTitle(R.string.tweakabout)
                                .setMessage(R.string.ker9)
                                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                                .setIcon(R.drawable.warning)
                                .show();
                    }
                    return true;
                }
            });
            zram.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


                                                @Override
                                                public void onCheckedChanged(CompoundButton buttonView,
                                                                             boolean isChecked) {

                                                    if (isChecked) {


                                                        if (RootTools.isAccessGiven()) {
                                                            Command command1 = new Command(0,
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                    "cp /data/data/com.nowenui.systemtweaker/files/zram /system/etc/init.d/",
                                                                    "chmod 777 /system/etc/init.d/zram",
                                                                    "/system/etc/init.d/zram",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system");
                                                            try {
                                                                RootTools.getShell(true).add(command1);
                                                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakenabled)).withBackgroundColorId(R.color.textview1good).show();
                                                            } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                                ex.printStackTrace();
                                                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                                                            }
                                                        } else {
                                                            new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.textview1bad).show();
                                                        }


                                                    } else {

                                                        if (RootTools.isAccessGiven()) {
                                                            Command command1 = new Command(0,
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                    "rm -f /system/etc/init.d/zram",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                                            );
                                                            try {
                                                                RootTools.getShell(true).add(command1);
                                                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakdisabled)).withBackgroundColorId(R.color.textview1good).show();
                                                            } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                                ex.printStackTrace();
                                                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                                                            }
                                                        } else {
                                                            new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.textview1bad).show();
                                                        }
                                                    }

                                                }
                                            }

            );
        } else {
            CheckBox zram = view.findViewById(R.id.zram);
            zram.setEnabled(false);
            zram.setBackgroundResource(R.drawable.roundbuttonfuck);
            zram.setTextColor(Color.WHITE);
            zram.setText(R.string.zrambad);
        }


        /////////////////////////////////////////////////////
        ////// Ondemand/Hotplug governors tweak ////////////
        ////////////////////////////////////////////////////
        CheckBox ohtweak = view.findViewById(R.id.ohtweak);
        String n = "/etc/init.d/ondemand_hotplug";
        String n1 = "/system/etc/init.d/ondemand_hotplug";
        if (mSharedPreference.contains("skipnitd")) {
            ohtweak.setEnabled(false);
        } else {
            ohtweak.setEnabled(true);
        }
        if (new File(Environment.getRootDirectory() + n).exists() || new File(n1).exists() || new File(Environment.getRootDirectory() + n1).exists()) {
            ohtweak.setChecked(true);
        } else {
            ohtweak.setChecked(false);
        }
        String n3 = "/sys/devices/system/cpu/cpufreq/ondemand/up_threshold";
        String n4 = "/sys/devices/system/cpu/cpufreq/ondemand/sampling_rate";
        String n5 = "/sys/devices/system/cpu/cpufreq/ondemand/sampling_down_factor";
        String n12 = "/sys/devices/system/cpu/cpufreq/ondemand/down_differential";

        String n13 = "/sys/devices/system/cpu/cpufreq/hotplug/up_threshold";
        String n14 = "/sys/devices/system/cpu/cpufreq/hotplug/sampling_rate";
        String n6 = "/sys/devices/system/cpu/cpufreq/hotplug/sampling_down_factor";
        String n7 = "/sys/devices/system/cpu/cpufreq/hotplug/down_differential";

        if ((new File(n3).exists() && new File(n4).exists() && new File(n5).exists() && new File(n12).exists()) || (new File(n13).exists() && new File(n14).exists() && new File(n6).exists() && new File(n7).exists())) {
            ohtweak.setEnabled(true);
        } else {
            ohtweak.setEnabled(false);
        }
        ohtweak.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                if (Utility.getTheme(getActivity().getApplicationContext()) == 1) {
                    new AlertDialog.Builder(getContext())
                            .setTitle(R.string.tweakabout)
                            .setMessage(R.string.ohopt)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(R.drawable.warning)
                            .show();
                }
                if (Utility.getTheme(getActivity().getApplicationContext()) == 2) {
                    new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark))
                            .setTitle(R.string.tweakabout)
                            .setMessage(R.string.ohopt)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(R.drawable.warning)
                            .show();
                }
                if (Utility.getTheme(getActivity().getApplicationContext()) == 3) {
                    new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack))
                            .setTitle(R.string.tweakabout)
                            .setMessage(R.string.ohopt)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(R.drawable.warning)
                            .show();
                }

                return true;
            }
        });
        ohtweak.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


                                               @Override
                                               public void onCheckedChanged(CompoundButton buttonView,
                                                                            boolean isChecked) {

                                                   if (isChecked) {


                                                       if (RootTools.isAccessGiven()) {
                                                           Command command1 = new Command(0,
                                                                   "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                   "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                   "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                   "cp /data/data/com.nowenui.systemtweaker/files/ondemand_hotplug /system/etc/init.d/",
                                                                   "chmod 777 /system/etc/init.d/ondemand_hotplug",
                                                                   "/system/etc/init.d/ondemand_hotplug",
                                                                   "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                   "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                   "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system");
                                                           try {
                                                               RootTools.getShell(true).add(command1);
                                                               new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakenabled)).withBackgroundColorId(R.color.textview1good).show();
                                                           } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                               ex.printStackTrace();
                                                               new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                                                           }
                                                       } else {
                                                           new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.textview1bad).show();
                                                       }


                                                   } else {

                                                       if (RootTools.isAccessGiven()) {
                                                           Command command1 = new Command(0,
                                                                   "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                   "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                   "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                   "rm -f /system/etc/init.d/ondemand_hotplug",
                                                                   "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                   "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                   "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                                           );
                                                           try {
                                                               RootTools.getShell(true).add(command1);
                                                               new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakdisabled)).withBackgroundColorId(R.color.textview1good).show();
                                                           } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                               ex.printStackTrace();
                                                               new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                                                           }
                                                       } else {
                                                           new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.textview1bad).show();
                                                       }
                                                   }

                                               }
                                           }

        );


        /////////////////////////////////////////////
        ////// Interactive Goernor Tweak ////////////
        /////////////////////////////////////////////
        CheckBox cpuoptimize = view.findViewById(R.id.cpuoptimize);
        String nine = "/etc/init.d/cpu_optimize";
        String nine1 = "/system/etc/init.d/cpu_optimize";
        if (mSharedPreference.contains("skipnitd")) {
            cpuoptimize.setEnabled(false);
        } else {
            cpuoptimize.setEnabled(true);
        }
        if (new File(Environment.getRootDirectory() + nine).exists() || new File(nine1).exists() || new File(Environment.getRootDirectory() + nine1).exists()) {
            cpuoptimize.setChecked(true);
        } else {
            cpuoptimize.setChecked(false);
        }
        String nine3 = "/dev/cpuctl/cpu.shares";
        String nine4 = "/dev/cpuctl/cpu.rt_runtime_us";
        String nine5 = "/dev/cpuctl/cpu.rt_period_us";
        String nine12 = "/dev/cpuctl/apps/cpu.shares";
        String nine13 = "/dev/cpuctl/apps/cpu.rt_runtime_us";
        String nine14 = "/dev/cpuctl/apps/cpu.rt_period_us";

        String nine6 = "/dev/cpuctl/bg_non_interactive/cpu.shares";
        String nine7 = "/dev/cpuctl/bg_non_interactive/cpu.rt_runtime_us";
        String nine10 = "/dev/cpuctl/apps/bg_non_interactive/cpu.shares";
        String nine11 = "/dev/cpuctl/apps/bg_non_interactive/cpu.rt_runtime_us";

        String nine8 = "/dev/cpuctl/cgroup.procs";
        String nine9 = "/dev/cpuctl/tasks";
        String nine15 = "/sys/devices/system/cpu/cpufreq/interactive/boost";

        if ((((new File(nine3).exists() && new File(nine4).exists() && new File(nine5).exists()) || (new File(nine12).exists() && new File(nine13).exists() && new File(nine14).exists())) && ((new File(nine6).exists() && new File(nine7).exists()) || (new File(nine10).exists() && new File(nine11).exists())) && new File(nine8).exists() && new File(nine9).exists()) && (new File(nine15).exists())) {
            cpuoptimize.setEnabled(true);
        } else {
            cpuoptimize.setEnabled(false);
        }
        cpuoptimize.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                if (Utility.getTheme(getActivity().getApplicationContext()) == 1) {
                    new AlertDialog.Builder(getContext())
                            .setTitle(R.string.tweakabout)
                            .setMessage(R.string.ker8)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(R.drawable.warning)
                            .show();
                }
                if (Utility.getTheme(getActivity().getApplicationContext()) == 2) {
                    new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark))
                            .setTitle(R.string.tweakabout)
                            .setMessage(R.string.ker8)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(R.drawable.warning)
                            .show();
                }
                if (Utility.getTheme(getActivity().getApplicationContext()) == 3) {
                    new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack))
                            .setTitle(R.string.tweakabout)
                            .setMessage(R.string.ker8)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(R.drawable.warning)
                            .show();
                }

                return true;
            }
        });
        cpuoptimize.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


                                                   @Override
                                                   public void onCheckedChanged(CompoundButton buttonView,
                                                                                boolean isChecked) {

                                                       if (isChecked) {


                                                           if (RootTools.isAccessGiven()) {
                                                               Command command1 = new Command(0,
                                                                       "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                       "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                       "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                       "cp /data/data/com.nowenui.systemtweaker/files/cpu_optimize /system/etc/init.d/",
                                                                       "chmod 777 /system/etc/init.d/cpu_optimize",
                                                                       "/system/etc/init.d/cpu_optimize",
                                                                       "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                       "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                       "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system");
                                                               try {
                                                                   RootTools.getShell(true).add(command1);
                                                                   new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakenabled)).withBackgroundColorId(R.color.textview1good).show();
                                                               } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                                   ex.printStackTrace();
                                                                   new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                                                               }
                                                           } else {
                                                               new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.textview1bad).show();
                                                           }

                                                       } else {

                                                           if (RootTools.isAccessGiven()) {
                                                               Command command1 = new Command(0,
                                                                       "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                       "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                       "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                       "rm -f /system/etc/init.d/cpu_optimize",
                                                                       "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                       "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                       "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                                               );
                                                               try {
                                                                   RootTools.getShell(true).add(command1);
                                                                   new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakdisabled)).withBackgroundColorId(R.color.textview1good).show();
                                                               } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                                   ex.printStackTrace();
                                                                   new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                                                               }
                                                           } else {
                                                               new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.textview1bad).show();
                                                           }

                                                       }

                                                   }
                                               }

        );

        /////////////////////////////////////////
        ////// Kernel Sleepers tweaks ///////////
        /////////////////////////////////////////
        CheckBox kernelsleeper = view.findViewById(R.id.kernelsleeper);
        if (mSharedPreference.contains("skipnitd")) {
            kernelsleeper.setEnabled(false);
        } else {
            kernelsleeper.setEnabled(true);
        }
        String ten = "/etc/init.d/kernel_sleeper";
        String ten3 = "/sys/kernel/debug/sched_features";
        String ten1 = "/system/etc/init.d/kernel_sleeper";
        if (!new File(ten3).exists()) {
            kernelsleeper.setEnabled(false);
        }

        if (new File(Environment.getRootDirectory() + ten).exists() || new File(ten1).exists() || new File(Environment.getRootDirectory() + ten1).exists()) {
            kernelsleeper.setChecked(true);
        } else {
            kernelsleeper.setChecked(false);
        }
        kernelsleeper.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                if (Utility.getTheme(getActivity().getApplicationContext()) == 1) {
                    new AlertDialog.Builder(getContext())
                            .setTitle(R.string.tweakabout)
                            .setMessage(R.string.ker7)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(R.drawable.warning)
                            .show();
                }
                if (Utility.getTheme(getActivity().getApplicationContext()) == 2) {
                    new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark))
                            .setTitle(R.string.tweakabout)
                            .setMessage(R.string.ker7)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(R.drawable.warning)
                            .show();
                }
                if (Utility.getTheme(getActivity().getApplicationContext()) == 3) {
                    new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack))
                            .setTitle(R.string.tweakabout)
                            .setMessage(R.string.ker7)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(R.drawable.warning)
                            .show();
                }
                return true;
            }
        });
        kernelsleeper.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


                                                     @Override
                                                     public void onCheckedChanged(CompoundButton buttonView,
                                                                                  boolean isChecked) {
                                                         if (isChecked) {


                                                             if (RootTools.isAccessGiven()) {
                                                                 Command command1 = new Command(0,
                                                                         "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                         "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                         "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                         "cp /data/data/com.nowenui.systemtweaker/files/kernel_sleeper /system/etc/init.d/",
                                                                         "chmod 777 /system/etc/init.d/kernel_sleeper",
                                                                         "/system/etc/init.d/kernel_sleeper",
                                                                         "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                         "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                         "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system");
                                                                 try {
                                                                     RootTools.getShell(true).add(command1);
                                                                     new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakenabled)).withBackgroundColorId(R.color.textview1good).show();
                                                                 } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                                     ex.printStackTrace();
                                                                     new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                                                                 }
                                                             } else {
                                                                 new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.textview1bad).show();
                                                             }


                                                         } else {

                                                             if (RootTools.isAccessGiven()) {
                                                                 Command command1 = new Command(0,
                                                                         "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                         "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                         "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                         "rm -f /system/etc/init.d/kernel_sleeper",
                                                                         "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                         "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                         "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                                                 );
                                                                 try {
                                                                     RootTools.getShell(true).add(command1);
                                                                     new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakdisabled)).withBackgroundColorId(R.color.textview1good).show();
                                                                 } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                                     ex.printStackTrace();
                                                                     new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                                                                 }
                                                             } else {
                                                                 new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.textview1bad).show();
                                                             }
                                                         }

                                                     }
                                                 }

        );


        ///////////////////////////////////////////
        ////// Normalize Sleeper Tweak ////////////
        ///////////////////////////////////////////
        CheckBox normsleeper = view.findViewById(R.id.normsleeper);
        if (mSharedPreference.contains("skipnitd")) {
            normsleeper.setEnabled(false);
        } else {
            normsleeper.setEnabled(true);
        }
        String elev = "/etc/init.d/11NSTweak";
        String elev1 = "/system/etc/init.d/11NSTweak";
        String checknormsleeper = "/sys/kernel/debug/sched_features";
        if (new File(Environment.getRootDirectory() + elev).exists() || new File(elev1).exists() || new File(Environment.getRootDirectory() + elev1).exists()) {
            normsleeper.setChecked(true);
        } else {
            normsleeper.setChecked(false);
        }
        normsleeper.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                if (Utility.getTheme(getActivity().getApplicationContext()) == 1) {
                    new AlertDialog.Builder(getContext())
                            .setTitle(R.string.tweakabout)
                            .setMessage(R.string.ker6)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(R.drawable.warning)
                            .show();
                }
                if (Utility.getTheme(getActivity().getApplicationContext()) == 2) {
                    new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark))
                            .setTitle(R.string.tweakabout)
                            .setMessage(R.string.ker6)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(R.drawable.warning)
                            .show();
                }
                if (Utility.getTheme(getActivity().getApplicationContext()) == 3) {
                    new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack))
                            .setTitle(R.string.tweakabout)
                            .setMessage(R.string.ker6)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(R.drawable.warning)
                            .show();
                }

                return true;
            }
        });
        if (new File(Environment.getRootDirectory() + checknormsleeper).exists() || new File(checknormsleeper).exists()) {
            normsleeper.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


                                                       @Override
                                                       public void onCheckedChanged(CompoundButton buttonView,
                                                                                    boolean isChecked) {
                                                           if (isChecked) {
                                                               if (RootTools.isAccessGiven()) {
                                                                   Command command1 = new Command(0,
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                           "cp /data/data/com.nowenui.systemtweaker/files/11NSTweak /system/etc/init.d/",
                                                                           "chmod 777 /system/etc/init.d/11NSTweak",
                                                                           "/system/etc/init.d/11NSTweak",
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system");
                                                                   try {
                                                                       RootTools.getShell(true).add(command1);
                                                                       new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakenabled)).withBackgroundColorId(R.color.textview1good).show();
                                                                   } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                                       ex.printStackTrace();
                                                                       new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                                                                   }
                                                               } else {
                                                                   new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.textview1bad).show();
                                                               }

                                                           } else {
                                                               if (RootTools.isAccessGiven()) {
                                                                   Command command1 = new Command(0,
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                           "rm -f /system/etc/init.d/11NSTweak",
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                           "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                                                   );
                                                                   try {
                                                                       RootTools.getShell(true).add(command1);
                                                                       new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakdisabled)).withBackgroundColorId(R.color.textview1good).show();
                                                                   } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                                       ex.printStackTrace();
                                                                       new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                                                                   }
                                                               } else {
                                                                   new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.textview1bad).show();
                                                               }
                                                           }

                                                       }
                                                   }

            );
        } else {
            normsleeper.setEnabled(false);
        }


        ////////////////////////////////////////////
        ////// KSM Enable (if supports) ////////////
        ////////////////////////////////////////////
        if (ksm() == 1) {
            CheckBox ksm = view.findViewById(R.id.ksm);
            if (mSharedPreference.contains("skipnitd")) {
                ksm.setEnabled(false);
            } else {
                ksm.setEnabled(true);
            }
            String twel = "/etc/init.d/ksm";
            String twel1 = "/system/etc/init.d/ksm";
            if (new File(Environment.getRootDirectory() + twel).exists() || new File(twel1).exists() || new File(Environment.getRootDirectory() + twel1).exists()) {
                ksm.setChecked(true);
            } else {
                ksm.setChecked(false);
            }
            ksm.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View arg0) {
                    if (Utility.getTheme(getActivity().getApplicationContext()) == 1) {
                        new AlertDialog.Builder(getContext())
                                .setTitle(R.string.tweakabout)
                                .setMessage(R.string.ker10)
                                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                                .setIcon(R.drawable.warning)
                                .show();
                    }
                    if (Utility.getTheme(getActivity().getApplicationContext()) == 2) {
                        new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark))
                                .setTitle(R.string.tweakabout)
                                .setMessage(R.string.ker10)
                                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                                .setIcon(R.drawable.warning)
                                .show();
                    }
                    if (Utility.getTheme(getActivity().getApplicationContext()) == 3) {
                        new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack))
                                .setTitle(R.string.tweakabout)
                                .setMessage(R.string.ker10)
                                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                                .setIcon(R.drawable.warning)
                                .show();
                    }

                    return true;
                }
            });
            ksm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


                                               @Override
                                               public void onCheckedChanged(CompoundButton buttonView,
                                                                            boolean isChecked) {

                                                   if (isChecked) {

                                                       if (RootTools.isAccessGiven()) {
                                                           Command command1 = new Command(0,
                                                                   "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                   "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                   "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                   "cp /data/data/com.nowenui.systemtweaker/files/ksm /system/etc/init.d/",
                                                                   "chmod 777 /system/etc/init.d/ksm",
                                                                   "/system/etc/init.d/ksm",
                                                                   "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                   "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                   "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system");
                                                           try {
                                                               RootTools.getShell(true).add(command1);
                                                               new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakenabled)).withBackgroundColorId(R.color.textview1good).show();
                                                           } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                               ex.printStackTrace();
                                                               new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                                                           }
                                                       } else {
                                                           new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.textview1bad).show();
                                                       }

                                                   } else {

                                                       if (RootTools.isAccessGiven()) {
                                                           Command command1 = new Command(0,
                                                                   "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                   "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                   "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                   "rm -f /system/etc/init.d/ksm",
                                                                   "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                   "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                   "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                                           );
                                                           try {
                                                               RootTools.getShell(true).add(command1);
                                                               new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakdisabled)).withBackgroundColorId(R.color.textview1good).show();
                                                           } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                               ex.printStackTrace();
                                                               new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                                                           }
                                                       } else {
                                                           new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.textview1bad).show();
                                                       }
                                                   }

                                               }
                                           }

            );
        } else {
            CheckBox ksm = view.findViewById(R.id.ksm);
            ksm.setEnabled(false);
            ksm.setBackgroundResource(R.drawable.roundbuttonfuck);
            ksm.setTextColor(Color.WHITE);
            ksm.setText(R.string.ksmbad);
        }


        /////////////////////////////////////////////////////////////////////
        ////// MultiCorePowerSaving enable option (if available) ////////////
        /////////////////////////////////////////////////////////////////////

        if (mpcs() == 1) {

            CheckBox mpcs = view.findViewById(R.id.mpcs);
            if (mSharedPreference.contains("skipnitd")) {
                mpcs.setEnabled(false);
            } else {
                mpcs.setEnabled(true);
            }
            String thi = "/etc/init.d/mpcs";
            String thi1 = "/system/etc/init.d/mpcs";
            if (new File(Environment.getRootDirectory() + thi).exists() || new File(thi1).exists() || new File(Environment.getRootDirectory() + thi1).exists()) {
                mpcs.setChecked(true);
            } else {
                mpcs.setChecked(false);
            }
            mpcs.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View arg0) {
                    if (Utility.getTheme(getActivity().getApplicationContext()) == 1) {
                        new AlertDialog.Builder(getContext())
                                .setTitle(R.string.tweakabout)
                                .setMessage(R.string.ker11)
                                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                                .setIcon(R.drawable.warning)
                                .show();
                    }
                    if (Utility.getTheme(getActivity().getApplicationContext()) == 2) {
                        new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark))
                                .setTitle(R.string.tweakabout)
                                .setMessage(R.string.ker11)
                                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                                .setIcon(R.drawable.warning)
                                .show();
                    }
                    if (Utility.getTheme(getActivity().getApplicationContext()) == 3) {
                        new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack))
                                .setTitle(R.string.tweakabout)
                                .setMessage(R.string.ker11)
                                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                                .setIcon(R.drawable.warning)
                                .show();
                    }

                    return true;
                }
            });
            mpcs.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


                                                @Override
                                                public void onCheckedChanged(CompoundButton buttonView,
                                                                             boolean isChecked) {
                                                    if (isChecked) {

                                                        if (RootTools.isAccessGiven()) {
                                                            Command command1 = new Command(0,
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                    "cp /data/data/com.nowenui.systemtweaker/files/mpcs /system/etc/init.d/",
                                                                    "chmod 777 /system/etc/init.d/mpcs",
                                                                    "/system/etc/init.d/mpcs",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system");
                                                            try {
                                                                RootTools.getShell(true).add(command1);
                                                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakenabled)).withBackgroundColorId(R.color.textview1good).show();
                                                            } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                                ex.printStackTrace();
                                                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                                                            }
                                                        } else {
                                                            new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.textview1bad).show();
                                                        }


                                                    } else {

                                                        if (RootTools.isAccessGiven()) {
                                                            Command command1 = new Command(0,
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                    "rm -f /system/etc/init.d/mpcs",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                    "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                                            );
                                                            try {
                                                                RootTools.getShell(true).add(command1);
                                                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakdisabled)).withBackgroundColorId(R.color.textview1good).show();
                                                            } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                                ex.printStackTrace();
                                                                new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                                                            }
                                                        } else {
                                                            new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.textview1bad).show();
                                                        }
                                                    }

                                                }
                                            }

            );
        } else {
            CheckBox mpcs = view.findViewById(R.id.mpcs);
            mpcs.setEnabled(false);
            mpcs.setBackgroundResource(R.drawable.roundbuttonfuck);
            mpcs.setTextColor(Color.WHITE);
            mpcs.setText(R.string.mpcsbad);
        }


        //////////////////////////////////////////////
        ////// FastCharge enabling option ////////////
        //////////////////////////////////////////////
        if (fastcharge() == 1) {
            CheckBox fastcharge = view.findViewById(R.id.fastcharge);
            if (mSharedPreference.contains("skipnitd")) {
                fastcharge.setEnabled(false);
            } else {
                fastcharge.setEnabled(true);
            }
            String fix = "/etc/init.d/fastcharge";
            String fix1 = "/system/etc/init.d/fastcharge";
            if (new File(Environment.getRootDirectory() + fix).exists() || new File(fix1).exists() || new File(Environment.getRootDirectory() + fix1).exists()) {
                fastcharge.setChecked(true);
            } else {
                fastcharge.setChecked(false);
            }
            fastcharge.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View arg0) {
                    if (Utility.getTheme(getActivity().getApplicationContext()) == 1) {
                        new AlertDialog.Builder(getContext())
                                .setTitle(R.string.tweakabout)
                                .setMessage(R.string.ker12)
                                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                                .setIcon(R.drawable.warning)
                                .show();
                    }
                    if (Utility.getTheme(getActivity().getApplicationContext()) == 2) {
                        new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogDark))
                                .setTitle(R.string.tweakabout)
                                .setMessage(R.string.ker12)
                                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                                .setIcon(R.drawable.warning)
                                .show();
                    }
                    if (Utility.getTheme(getActivity().getApplicationContext()) == 3) {
                        new AlertDialog.Builder(new ContextThemeWrapper(getContext(), R.style.AlertDialogBlack))
                                .setTitle(R.string.tweakabout)
                                .setMessage(R.string.ker12)
                                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                                .setIcon(R.drawable.warning)
                                .show();
                    }

                    return true;
                }
            });
            fastcharge.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


                                                      @Override
                                                      public void onCheckedChanged(CompoundButton buttonView,
                                                                                   boolean isChecked) {
                                                          if (isChecked) {

                                                              if (RootTools.isAccessGiven()) {
                                                                  Command command1 = new Command(0,
                                                                          "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                          "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                          "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                          "cp /data/data/com.nowenui.systemtweaker/files/fastcharge /system/etc/init.d/",
                                                                          "chmod 777 /system/etc/init.d/fastcharge",
                                                                          "/system/etc/init.d/fastcharge",
                                                                          "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                          "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                          "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system");
                                                                  try {
                                                                      RootTools.getShell(true).add(command1);
                                                                      new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakenabled)).withBackgroundColorId(R.color.textview1good).show();
                                                                  } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                                      ex.printStackTrace();
                                                                      new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                                                                  }
                                                              } else {
                                                                  new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.textview1bad).show();
                                                              }

                                                          } else {

                                                              if (RootTools.isAccessGiven()) {
                                                                  Command command1 = new Command(0,
                                                                          "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /proc /system",
                                                                          "/data/data/com.nowenui.systemtweaker/files/busybox mount -o rw,remount /system",
                                                                          "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,rw /system", "mount -o rw,remount /system",
                                                                          "rm -f /system/etc/init.d/fastcharge",
                                                                          "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /proc /system",
                                                                          "/data/data/com.nowenui.systemtweaker/files/busybox mount -o ro,remount /system", "mount -o ro,remount /system",
                                                                          "/data/data/com.nowenui.systemtweaker/files/busybox mount -o remount,ro /system"
                                                                  );
                                                                  try {
                                                                      RootTools.getShell(true).add(command1);
                                                                      new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.tweakdisabled)).withBackgroundColorId(R.color.textview1good).show();
                                                                  } catch (IOException | RootDeniedException | TimeoutException ex) {
                                                                      ex.printStackTrace();
                                                                      new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.errordev)).withBackgroundColorId(R.color.textview1bad).show();
                                                                  }
                                                              } else {
                                                                  new SnackBar.Builder(getActivity()).withMessage(getContext().getResources().getString(R.string.error)).withBackgroundColorId(R.color.textview1bad).show();
                                                              }
                                                          }

                                                      }
                                                  }

            );
        } else {
            CheckBox fastcharge = view.findViewById(R.id.fastcharge);
            fastcharge.setEnabled(false);
            fastcharge.setBackgroundResource(R.drawable.roundbuttonfuck);
            fastcharge.setTextColor(Color.WHITE);
            fastcharge.setText(R.string.fastchargebad);
        }

    }


    ////////////////////////////////////////////
    ////// Check fastcharge support ////////////
    ////////////////////////////////////////////
    public int fastcharge() {
        String fast = "force_fast_charge";
        String[] locations = {"/sys/kernel/fast_charge/"};
        for (String location : locations) {
            if (new File(location + fast).exists()) {
                return 1;
            }
        }
        return 0;
    }


    ////////////////////////////////////////////////////
    ////// Check MultiCorePowerSaving support //////////
    ////////////////////////////////////////////////////
    public int mpcs() {
        String mpcs = "sched_mc_power_savings";
        String[] locations = {"/sys/devices/system/cpu/"};
        for (String location : locations) {
            if (new File(location + mpcs).exists()) {
                return 1;
            }
        }
        return 0;
    }


    /////////////////////////////////////
    ////// Check KSM support ////////////
    /////////////////////////////////////

    public int ksm() {
        String ksm = "run";
        String[] locations = {"/sys/kernel/mm/ksm/"};
        for (String location : locations) {
            if (new File(location + ksm).exists()) {
                return 1;
            }
        }
        return 0;
    }


    /////////////////////////////////////////
    ////// Check ZRAM support ////////////
    /////////////////////////////////////////
    public int ZRAM() {
        File f = new File("/sys/block/zram0");
        if ((f.exists()) && (f.isDirectory())) {
            return 1;
        }
        return 0;
    }


}