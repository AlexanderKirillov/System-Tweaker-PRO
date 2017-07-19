package com.nowenui.systemtweaker;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.preference.PreferenceManager;

public class ShutdownService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onCreate();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(Constants.CHECK_SHUTDOWN_OK, true);

        if (prefs.contains(Constants.STATS_ZERO_POINT)) {
            editor.remove(Constants.STATS_ZERO_POINT);
        }
        editor.apply();
        stopSelf();
    }
}
