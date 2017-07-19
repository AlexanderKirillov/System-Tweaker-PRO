package com.nowenui.systemtweaker;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.IBinder;
import android.os.SystemClock;
import android.preference.PreferenceManager;

public class BootService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onCreate();
        if (SysUtils.hasSysfs()) {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

            if (prefs.getBoolean(Constants.PREF_APPLY_ON_BOOT, false)) {
                String pattern = null;
                if (prefs.getBoolean(Constants.PREF_VIBRATE, Constants.PREF_DEFAULT_VIBRATE)) {
                    pattern = prefs.getString(Constants.PREF_VIBRATE_PATTERN, "");
                }

                if (!prefs.getBoolean(Constants.PREF_DISABLE_SAFETY_VALVE, Constants.PREF_DEFAULT_DISABLE_SAFETY_VALVE)
                        && !prefs.getBoolean(Constants.CHECK_SHUTDOWN_OK, false)) {

                    long uptime = SystemClock.uptimeMillis();
                    if (uptime > prefs.getLong(Constants.LAST_UPTIME, uptime)) {
                    } else {
                    }
                } else {
                    String min_freq = prefs.getString(Constants.PREF_MIN_FREQ, null);
                    String max_freq = prefs.getString(Constants.PREF_MAX_FREQ, null);
                    String governor = prefs.getString(Constants.PREF_GOVERNOR, null);
                    String ioscheduler = prefs.getString(Constants.PREF_IOSCHEDULER, null);
                    if (min_freq != null || max_freq != null || governor != null || ioscheduler != null) {
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putBoolean(Constants.CHECK_SHUTDOWN_OK, false);
                        editor.putLong(Constants.LAST_UPTIME, SystemClock.uptimeMillis());
                        editor.apply();

                        boolean changePermissions = prefs.getBoolean(Constants.PREF_CHANGE_PERMISSIONS,
                                Constants.PREF_DEFAULT_CHANGE_PERMISSIONS);

                        boolean updated = SysUtils.setFrequenciesAndGovernor(min_freq, max_freq, governor, ioscheduler,
                                changePermissions, this, R.string.ok_updated_freqs, R.string.err_update_failed);
                    }
                }
            }
            if (prefs.contains(Constants.STATS_ZERO_POINT)) {
                Editor editor = prefs.edit();
                editor.remove(Constants.STATS_ZERO_POINT);
                editor.apply();
            }
        }
        stopSelf();
    }
}
