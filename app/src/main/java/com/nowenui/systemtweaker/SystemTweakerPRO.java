package com.nowenui.systemtweaker;

import android.app.Application;
import android.content.res.Configuration;

import com.franmontiel.localechanger.LocaleChanger;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class SystemTweakerPRO extends Application {

    public static final List<Locale> SUPPORTED_LOCALES =
            Arrays.asList(
                    new Locale("en", "US"),
                    new Locale("ru", "RU")
            );


    @Override
    public void onCreate() {

        ///////////////////////////////////////////
        ////// Check language and apply ///////////
        ///////////////////////////////////////////
        super.onCreate();
        LocaleChanger.initialize(getApplicationContext(), SUPPORTED_LOCALES);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LocaleChanger.onConfigurationChanged();
    }
}
