package com.nowenui.systemtweaker.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nowenui.systemtweaker.R;

public class SovetsPerfomanceFragment extends Fragment {

    public static SovetsPerfomanceFragment newInstance(Bundle bundle) {
        SovetsPerfomanceFragment SovetsPerfomance = new SovetsPerfomanceFragment();

        if (bundle != null) {
            SovetsPerfomance.setArguments(bundle);
        }

        return SovetsPerfomance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.perfomance, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }


}