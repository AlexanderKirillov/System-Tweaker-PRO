package com.nowenui.systemtweaker;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.franmontiel.localechanger.LocaleChanger;
import com.nowenui.systemtweaker.fragments.AboutDeviceFragment;
import com.nowenui.systemtweaker.fragments.AboutProgramFragment;
import com.nowenui.systemtweaker.fragments.AgreementFragment;
import com.nowenui.systemtweaker.fragments.BackupRestoreFragment;
import com.nowenui.systemtweaker.fragments.BatteryCalibrationFragment;
import com.nowenui.systemtweaker.fragments.BatteryTweaksFragment;
import com.nowenui.systemtweaker.fragments.BraviaEngineFragment;
import com.nowenui.systemtweaker.fragments.BugReportFragment;
import com.nowenui.systemtweaker.fragments.DPIChangerFragment;
import com.nowenui.systemtweaker.fragments.EntropyGeneratorFragment;
import com.nowenui.systemtweaker.fragments.FAQFragment;
import com.nowenui.systemtweaker.fragments.FstrimFragment;
import com.nowenui.systemtweaker.fragments.GPSTweaksFragment;
import com.nowenui.systemtweaker.fragments.HomeFragment;
import com.nowenui.systemtweaker.fragments.InternetTweaksFragment;
import com.nowenui.systemtweaker.fragments.KernelTweaksFragment;
import com.nowenui.systemtweaker.fragments.MediaBoosterFragment;
import com.nowenui.systemtweaker.fragments.MediaTweaksFragment;
import com.nowenui.systemtweaker.fragments.MusicFragment;
import com.nowenui.systemtweaker.fragments.OneClickFragment;
import com.nowenui.systemtweaker.fragments.RebootManagerFragment;
import com.nowenui.systemtweaker.fragments.SDFixFragment;
import com.nowenui.systemtweaker.fragments.SettingsFragment;
import com.nowenui.systemtweaker.fragments.SystemTweaksFragment;
import com.nowenui.systemtweaker.fragments.TipsPerfomanceFragment;
import com.nowenui.systemtweaker.fragments.TipsPowerSaveFragment;
import com.nowenui.systemtweaker.fragments.VariosTweaksFragment;
import com.nowenui.systemtweaker.fragments.WifiPasswordsFragment;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private final static int THEME_LIGHT = 1;
    private final static int THEME_DARK = 2;
    private final static int THEME_AMOLED = 3;
    public int themestyle;
    DrawerLayout androidDrawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    boolean doubleBackToExitPressedOnce = false;
    private MenuItem prevMenuItem;

    @Override
    protected void attachBaseContext(Context newBase) {
        newBase = LocaleChanger.configureBaseContext(newBase);
        super.attachBaseContext(newBase);
    }

    /////////////////////////////////////////////////
    ////// Set Applications Theme //////////////////
    /////////////////////////////////////////////////
    public void updateTheme() {
        if (ThemeUtility.getTheme(getApplicationContext()) <= THEME_LIGHT) {
            setTheme(R.style.AppTheme_Light);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
            }
        } else if (ThemeUtility.getTheme(getApplicationContext()) == THEME_DARK) {
            setTheme(R.style.AppTheme_Dark);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
            }
        } else if (ThemeUtility.getTheme(getApplicationContext()) == THEME_AMOLED) {
            setTheme(R.style.AppTheme_Amoled);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
            }
        }

        themestyle = ThemeUtility.getTheme(getApplicationContext());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ////////////////////////////////////////
        ////// Check theme and apply ///////////
        ////////////////////////////////////////
        super.onCreate(savedInstanceState);

        final SharedPreferences BD = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        if (BD.contains("THEME_BASE")) {
            updateTheme();
        } else {
            BD.edit().putInt(getApplicationContext().getString(R.string.prefs_theme_key), 1).apply();
            SharedPreferences.Editor BDEditor = BD.edit();
            BDEditor.putString("THEME_BASE", "152458875688545666565989865659885");
            BDEditor.apply();

        }

        setContentView(R.layout.activity_main);

        /////////////////////////////////////////////////
        ////// Enable animations... /////////////////////
        /////////////////////////////////////////////////
        if (Build.VERSION.SDK_INT >= 15) {
            overridePendingTransition(R.anim.anim1, R.anim.anim2);
        }

        initInstancesDrawer();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("System Tweaker PRO");
        }

        /////////////////////////////////////////////////
        ////// Show starting dialog /////////////////////
        /////////////////////////////////////////////////
        boolean isLangRU = Locale.getDefault().getLanguage().equals("ru");
        boolean isLangBE = Locale.getDefault().getLanguage().equals("be");
        boolean isLangUK = Locale.getDefault().getLanguage().equals("uk");
        boolean isLangKK = Locale.getDefault().getLanguage().equals("kk");
        boolean isLangKZ = Locale.getDefault().getLanguage().equals("kz");
        if (isLangRU || isLangBE || isLangUK || isLangKK || isLangKZ) {
            LayoutInflater inflater = getLayoutInflater();
            View dialogru = inflater.inflate(R.layout.hello_dialog_ru, null);
            if (!BD.contains("DIALOGSTART")) {
                new android.app.AlertDialog.Builder(this)
                        .setView(dialogru)
                        .setCancelable(false)
                        .setPositiveButton(R.string.ponatno, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                SharedPreferences.Editor BDEditor = BD.edit();
                                BDEditor.putString("DIALOGSTART", "46543");
                                BDEditor.apply();
                                dialog.dismiss();
                            }
                        })
                        .show();
            }
        } else {
            LayoutInflater inflater = getLayoutInflater();
            View dialogen = inflater.inflate(R.layout.hello_dialog_en, null);
            final SharedPreferences mSharedPreference = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
            if (!mSharedPreference.contains("DIALOGSTART")) {
                new android.app.AlertDialog.Builder(this)
                        .setView(dialogen)
                        .setCancelable(false)
                        .setPositiveButton(R.string.ponatno, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                SharedPreferences.Editor BDEditor = BD.edit();
                                BDEditor.putString("DIALOGSTART", "46543");
                                BDEditor.apply();
                                dialog.dismiss();
                            }
                        })
                        .show();
            }
        }
    }

    private void initInstancesDrawer() {

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        androidDrawerLayout = findViewById(R.id.drawer_design_support_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this, androidDrawerLayout, R.string.app_name, R.string.app_name);
        androidDrawerLayout.setDrawerListener(actionBarDrawerToggle);

        NavigationView navigationView = findViewById(R.id.navigation_drawer);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }
        assert navigationView != null;
        navigationView.getMenu().getItem(0).setChecked(true);
        navigationView.getMenu().performIdentifierAction(R.id.home_item, 0);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public void onBackPressed() {
        if (this.androidDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.androidDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            androidDrawerLayout.openDrawer(GravityCompat.START);
            if (doubleBackToExitPressedOnce || getSupportFragmentManager().getBackStackEntryCount() != 0) {
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

    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                androidDrawerLayout.openDrawer(GravityCompat.START);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }


    private void setupDrawerContent(final NavigationView navigationView) {
        final Resources res = getResources();
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        if (prevMenuItem != null)
                            prevMenuItem.setChecked(false);

                        menuItem.setChecked(true);
                        Bundle bundle = new Bundle();
                        String fragmentTitle = null;
                        switch (menuItem.getItemId()) {
                            case R.id.home_item:
                                fragmentTitle = "System Tweaker PRO";
                                getSupportFragmentManager()
                                        .beginTransaction()
                                        .replace(R.id.content, HomeFragment.newInstance(bundle))
                                        .commit();
                                break;
                            case R.id.agreement_item:
                                fragmentTitle = res.getString(R.string.soglasenie);
                                getSupportFragmentManager()
                                        .beginTransaction()
                                        .setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left)
                                        .replace(R.id.content, AgreementFragment.newInstance(bundle))
                                        .commit();
                                break;
                            case R.id.info_about_device_item:
                                fragmentTitle = res.getString(R.string.infoabout);
                                getSupportFragmentManager()
                                        .beginTransaction()
                                        .setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left)
                                        .replace(R.id.content, AboutDeviceFragment.newInstance(bundle))
                                        .commit();
                                break;
                            case R.id.speedup_item:
                                fragmentTitle = res.getString(R.string.speedup);
                                getSupportFragmentManager()
                                        .beginTransaction()
                                        .setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left)
                                        .replace(R.id.content, OneClickFragment.newInstance(bundle))
                                        .commit();
                                break;
                            case R.id.batterycalibration_item:
                                fragmentTitle = res.getString(R.string.calbattery);
                                getSupportFragmentManager()
                                        .beginTransaction()
                                        .setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left)
                                        .replace(R.id.content, BatteryCalibrationFragment.newInstance(bundle))
                                        .commit();
                                break;
                            case R.id.soundimprover_item:
                                fragmentTitle = res.getString(R.string.soundimprover);
                                getSupportFragmentManager()
                                        .beginTransaction()
                                        .setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left)
                                        .replace(R.id.content, MusicFragment.newInstance(bundle))
                                        .commit();
                                break;
                            case R.id.braviainstaller_item:
                                fragmentTitle = "Bravia Engine Installer";
                                getSupportFragmentManager()
                                        .beginTransaction()
                                        .setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left)
                                        .replace(R.id.content, BraviaEngineFragment.newInstance(bundle))
                                        .commit();
                                break;
                            case R.id.sdcardfix_item:
                                fragmentTitle = "SDCard R/W Fix";
                                getSupportFragmentManager()
                                        .beginTransaction()
                                        .setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left)
                                        .replace(R.id.content, SDFixFragment.newInstance(bundle))
                                        .commit();
                                break;
                            case R.id.dpichanger_item:
                                fragmentTitle = res.getString(R.string.dpititle);
                                getSupportFragmentManager()
                                        .beginTransaction()
                                        .setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left)
                                        .replace(R.id.content, DPIChangerFragment.newInstance(bundle))
                                        .commit();
                                break;
                            case R.id.kerneltuner_item:
                                fragmentTitle = res.getString(R.string.kerneltweaks);
                                getSupportFragmentManager()
                                        .beginTransaction()
                                        .setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left)
                                        .replace(R.id.content, KernelTweaksFragment.newInstance(bundle))
                                        .commit();
                                break;
                            case R.id.backuprestore_item:
                                fragmentTitle = res.getString(R.string.backupandrestore);
                                getSupportFragmentManager()
                                        .beginTransaction()
                                        .setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left)
                                        .replace(R.id.content, BackupRestoreFragment.newInstance(bundle))
                                        .commit();
                                break;
                            case R.id.rebootmanager_item:
                                fragmentTitle = res.getString(R.string.rebootmanager);
                                getSupportFragmentManager()
                                        .beginTransaction()
                                        .setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left)
                                        .replace(R.id.content, RebootManagerFragment.newInstance(bundle))
                                        .commit();
                                break;
                            case R.id.batterytweaks_item:
                                fragmentTitle = res.getString(R.string.batterytweaks);
                                getSupportFragmentManager()
                                        .beginTransaction()
                                        .setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left)
                                        .replace(R.id.content, BatteryTweaksFragment.newInstance(bundle))
                                        .commit();

                                break;
                            case R.id.internettweaks_item:
                                fragmentTitle = res.getString(R.string.internettweaks);
                                getSupportFragmentManager()
                                        .beginTransaction()
                                        .setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left)
                                        .replace(R.id.content, InternetTweaksFragment.newInstance(bundle))
                                        .commit();
                                break;
                            case R.id.wifipasswords_item:
                                fragmentTitle = "WIFI Passwords";
                                getSupportFragmentManager()
                                        .beginTransaction()
                                        .setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left)
                                        .replace(R.id.content, WifiPasswordsFragment.newInstance(bundle))
                                        .commit();
                                break;
                            case R.id.systemtweaks_item:
                                fragmentTitle = res.getString(R.string.systemtweaks);
                                getSupportFragmentManager()
                                        .beginTransaction()
                                        .setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left)
                                        .replace(R.id.content, SystemTweaksFragment.newInstance(bundle))
                                        .commit();
                                break;
                            case R.id.fstrim_item:
                                fragmentTitle = "FSTRIM";
                                getSupportFragmentManager()
                                        .beginTransaction()
                                        .setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left)
                                        .replace(R.id.content, FstrimFragment.newInstance(bundle))
                                        .commit();
                                break;
                            case R.id.entropy_item:
                                fragmentTitle = "Entropy Generator";
                                getSupportFragmentManager()
                                        .beginTransaction()
                                        .setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left)
                                        .replace(R.id.content, EntropyGeneratorFragment.newInstance(bundle))
                                        .commit();
                                break;
                            case R.id.mediamanager_item:
                                fragmentTitle = "MediaServer | MediaScanner";
                                getSupportFragmentManager()
                                        .beginTransaction()
                                        .setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left)
                                        .replace(R.id.content, MediaBoosterFragment.newInstance(bundle))
                                        .commit();
                                break;
                            case R.id.mediatweaks_item:
                                fragmentTitle = res.getString(R.string.mediatweaks);
                                getSupportFragmentManager()
                                        .beginTransaction()
                                        .setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left)
                                        .replace(R.id.content, MediaTweaksFragment.newInstance(bundle))
                                        .commit();
                                break;
                            case R.id.gpsboost_item:
                                fragmentTitle = res.getString(R.string.gpstitle);
                                getSupportFragmentManager()
                                        .beginTransaction()
                                        .setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left)
                                        .replace(R.id.content, GPSTweaksFragment.newInstance(bundle))
                                        .commit();
                                break;
                            case R.id.othertweaks_item:
                                fragmentTitle = res.getString(R.string.varioustweaks);
                                getSupportFragmentManager()
                                        .beginTransaction()
                                        .setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left)
                                        .replace(R.id.content, VariosTweaksFragment.newInstance(bundle))
                                        .commit();
                                break;
                            case R.id.tipspowersaving_item:
                                fragmentTitle = res.getString(R.string.sovetspowersave);
                                getSupportFragmentManager()
                                        .beginTransaction()
                                        .setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left)
                                        .replace(R.id.content, TipsPowerSaveFragment.newInstance(bundle))
                                        .commit();
                                break;
                            case R.id.tipsperformance_item:
                                fragmentTitle = res.getString(R.string.sovetsperfomance);
                                getSupportFragmentManager()
                                        .beginTransaction()
                                        .setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left)
                                        .replace(R.id.content, TipsPerfomanceFragment.newInstance(bundle))
                                        .commit();
                                break;
                            case R.id.faq_item:
                                fragmentTitle = res.getString(R.string.faq);
                                getSupportFragmentManager()
                                        .beginTransaction()
                                        .setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left)
                                        .replace(R.id.content, FAQFragment.newInstance(bundle))
                                        .commit();
                                break;
                            case R.id.bugreport_item:
                                fragmentTitle = res.getString(R.string.connect);
                                getSupportFragmentManager()
                                        .beginTransaction()
                                        .setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left)
                                        .replace(R.id.content, BugReportFragment.newInstance(bundle))
                                        .commit();
                                break;

                            case R.id.official_site_item:
                                fragmentTitle = "System Tweaker PRO";
                                Handler siteHandler = new Handler();
                                siteHandler.postDelayed(new Runnable() {
                                    public void run() {
                                        Uri siteaddress = Uri.parse("http://devnowenui.wixsite.com/nowenui");
                                        Intent openlinksite = new Intent(Intent.ACTION_VIEW, siteaddress);
                                        startActivity(openlinksite);
                                    }
                                }, 300);

                                menuItem.setChecked(false);

                                break;

                            case R.id.vkgroup_item:
                                fragmentTitle = "System Tweaker PRO";
                                Handler vkgroup = new Handler();
                                vkgroup.postDelayed(new Runnable() {
                                    public void run() {
                                        Uri vkaddress = Uri.parse("http://vk.com/nowenui_official_group");
                                        Intent openvk = new Intent(Intent.ACTION_VIEW, vkaddress);
                                        startActivity(openvk);
                                    }
                                }, 300);

                                menuItem.setChecked(false);

                                break;

                            case R.id.twitter_item:
                                fragmentTitle = "System Tweaker PRO";
                                Handler twitteropen = new Handler();
                                twitteropen.postDelayed(new Runnable() {
                                    public void run() {
                                        Uri twitadress = Uri.parse("https://twitter.com/intent/user?user_id=4771768877");
                                        Intent opentwitter = new Intent(Intent.ACTION_VIEW, twitadress);
                                        startActivity(opentwitter);
                                    }
                                }, 300);

                                menuItem.setChecked(false);

                                break;


                            case R.id.settings_item:
                                fragmentTitle = res.getString(R.string.settingstile);
                                getSupportFragmentManager()
                                        .beginTransaction()
                                        .setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left)
                                        .replace(R.id.content, SettingsFragment.newInstance(bundle))
                                        .commit();
                                break;


                            case R.id.aboutprogram_item:
                                fragmentTitle = res.getString(R.string.aboutapp);
                                getSupportFragmentManager()
                                        .beginTransaction()
                                        .setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left)
                                        .replace(R.id.content, AboutProgramFragment.newInstance(bundle))
                                        .commit();
                                break;

                            case R.id.exit_item:
                                Intent intent = new Intent(Intent.ACTION_MAIN);
                                intent.addCategory(Intent.CATEGORY_HOME);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                int pid = android.os.Process.myPid();
                                android.os.Process.killProcess(pid);
                                break;
                        }

                        if (getSupportActionBar() != null) {
                            getSupportActionBar().setTitle(fragmentTitle);
                        }

                        androidDrawerLayout.closeDrawers();
                        prevMenuItem = menuItem;
                        return true;
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}