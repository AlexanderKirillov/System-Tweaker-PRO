package com.nowenui.systemtweaker.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.franmontiel.localechanger.LocaleChanger;
import com.franmontiel.localechanger.utils.ActivityRecreationHelper;
import com.nowenui.systemtweaker.R;
import com.nowenui.systemtweaker.ThemeUtility;

import java.util.Locale;

public class SettingsFragment extends Fragment {

    public static SettingsFragment newInstance(Bundle bundle) {
        SettingsFragment Settings = new SettingsFragment();

        if (bundle != null) {
            Settings.setArguments(bundle);
        }

        return Settings;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void recreateActivity() {
        Intent intent = getActivity().getIntent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        getActivity().finish();
        getActivity().overridePendingTransition(0, 0);
        startActivity(intent);
        getActivity().overridePendingTransition(0, 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.settings, parent, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Button light_theme_btn = view.findViewById(R.id.light_theme_btn);
        Button dark_theme_btn = view.findViewById(R.id.dark_theme_btn);
        Button amoled_theme_btn = view.findViewById(R.id.amoled_theme_btn);
        CheckBox alerttweaks = view.findViewById(R.id.alerttweaks);

        Button localen = view.findViewById(R.id.localeEN);
        localen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LocaleChanger.setLocale(new Locale("en", "US"));
                ActivityRecreationHelper.recreate(getActivity(), false);
            }
        });

        Button localru = view.findViewById(R.id.localeRU);
        localru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LocaleChanger.setLocale(new Locale("ru", "RU"));
                ActivityRecreationHelper.recreate(getActivity(), false);
            }
        });


        ////////////////////////////////////////////////////
        ////// Dangerous tweak enabling option  ////////////
        ///////////////////////////////////////////////////
        final SharedPreferences mSharedPreference = PreferenceManager.getDefaultSharedPreferences(getContext());
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        if (mSharedPreference.contains("alerttweaks")) {
            alerttweaks.setChecked(true);
        } else {
            alerttweaks.setChecked(false);
        }


        alerttweaks.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {


                                                   @Override
                                                   public void onCheckedChanged(CompoundButton buttonView,
                                                                                boolean isChecked) {
                                                       if (isChecked) {
                                                           SharedPreferences.Editor editor = prefs.edit();
                                                           editor.putString("alerttweaks", "alerttweaks");
                                                           editor.commit();
                                                       } else {
                                                           SharedPreferences.Editor editor = prefs.edit();
                                                           editor.remove("alerttweaks");
                                                           editor.commit();
                                                       }
                                                   }
                                               }
        );
        /////////////////////////////////////////////////////////
        ////// Show selected theme symbol on buttons ////////////
        /////////////////////////////////////////////////////////
        if (ThemeUtility.getTheme(getActivity().getApplicationContext()) == 1) {
            light_theme_btn.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.okeysmalllogo), null, null, null);
        }
        if (ThemeUtility.getTheme(getActivity().getApplicationContext()) == 2) {
            dark_theme_btn.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.okeysmalllogo), null, null, null);
        }
        if (ThemeUtility.getTheme(getActivity().getApplicationContext()) == 3) {
            amoled_theme_btn.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(R.drawable.okeysmalllogo), null, null, null);
        }


        //////////////////////////////////////////
        ////// Set application theme  ////////////
        /////////////////////////////////////////
        light_theme_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ThemeUtility.setTheme(getContext(), 1);
                recreateActivity();
            }
        });


        dark_theme_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ThemeUtility.setTheme(getContext(), 2);
                recreateActivity();
            }
        });


        amoled_theme_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ThemeUtility.setTheme(getContext(), 3);
                recreateActivity();
            }
        });

    }

}
