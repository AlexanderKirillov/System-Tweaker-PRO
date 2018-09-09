package com.nowenui.systemtweaker;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class ThemeUtility {
    public static void setTheme(Context context, int theme) {
        SharedPreferences BD = PreferenceManager.getDefaultSharedPreferences(context);
        BD.edit().putInt(context.getString(R.string.prefs_theme_key), theme).apply();
    }

    public static int getTheme(Context context) {
        SharedPreferences BD = PreferenceManager.getDefaultSharedPreferences(context);
        return BD.getInt(context.getString(R.string.prefs_theme_key), -1);
    }
}