package com.nowenui.systemtweaker.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nowenui.systemtweaker.R;

public class AboutProgramFragment extends Fragment {

    public static AboutProgramFragment newInstance(Bundle bundle) {
        AboutProgramFragment AboutProgram = new AboutProgramFragment();

        if (bundle != null) {
            AboutProgram.setArguments(bundle);
        }

        return AboutProgram;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.about_program, parent, false);
    }

}