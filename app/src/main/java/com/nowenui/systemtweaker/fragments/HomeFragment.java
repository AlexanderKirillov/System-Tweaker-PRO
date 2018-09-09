package com.nowenui.systemtweaker.fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nowenui.systemtweaker.R;
import com.nowenui.systemtweaker.SplashActivity;

import java.io.File;

public class HomeFragment extends Fragment {

    public static HomeFragment newInstance(Bundle bundle) {
        HomeFragment Home = new HomeFragment();

        if (bundle != null) {
            Home.setArguments(bundle);
        }

        return Home;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.homepage, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        TextView rootinfo = view.findViewById(R.id.rootinfo);
        TextView busyboxinfo = view.findViewById(R.id.busyboxinfo);
        TextView initdinfo = view.findViewById(R.id.initdinfo);
        TextView selinuxinfo = view.findViewById(R.id.selinuxinfo);

        //////////////////////////////////
        ////// Check SuperUser ///////////
        //////////////////////////////////
        if (SplashActivity.checksu == 1) {
            rootinfo.setText(R.string.root);
            rootinfo.setBackgroundResource(R.drawable.roundbuttonmaingood);
            rootinfo.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.successfull, 0, 0, 0);
        } else {
            rootinfo.setText(R.string.rootbad);
            rootinfo.setBackgroundResource(R.drawable.roundbuttonbad);
            rootinfo.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.bad, 0, 0, 0);
        }

        ///////////////////////////////
        ////// Check BusyBox //////////
        ///////////////////////////////
        busyboxinfo.setText(R.string.busybox);
        busyboxinfo.setBackgroundResource(R.drawable.roundbuttonmaingood);
        busyboxinfo.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.successfull, 0, 0, 0);

        ///////////////////////////////////////
        ////// Check init.d support ///////////
        ///////////////////////////////////////
        initdinfo.setText(isInitdSupportAtHomePage());
        if (initdinfo.getText().toString().contains("INIT.D WORKING!") || initdinfo.getText().toString().contains("ПАПКА INIT.D \nПРИСУТСТВУЕТ!")) {
            initdinfo.setBackgroundResource(R.drawable.roundbuttonmaingood);
            initdinfo.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.successfull, 0, 0, 0);
        } else {
            initdinfo.setBackgroundResource(R.drawable.roundbuttonbad);
            initdinfo.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.bad, 0, 0, 0);
        }

        //////////////////////////////////
        ////// SELinux check /////////////
        //////////////////////////////////
        if ((Build.VERSION.SDK_INT >= 18)) {
            if (SplashActivity.selinuxstatus == 1) {
                selinuxinfo.setText("SELINUX: PERMISSIVE");
                selinuxinfo.setBackgroundResource(R.drawable.roundbuttonmaingood);
                selinuxinfo.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.successfull, 0, 0, 0);
            } else {
                selinuxinfo.setText("SELINUX: ENFORCING");
                selinuxinfo.setBackgroundResource(R.drawable.roundbuttonwarning);
                selinuxinfo.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.bad, 0, 0, 0);
            }
        } else {
            selinuxinfo.setVisibility(View.GONE);
        }
    }

    public int isInitdSupportAtHomePage() {
        File sud = new File("/system/su.d");
        File initd = new File("/system/etc/init.d");
        if ((initd.exists()) && (initd.isDirectory()) || (sud.exists()) && (sud.isDirectory())) {
            return R.string.initd;
        }
        return R.string.initdbad;
    }
}