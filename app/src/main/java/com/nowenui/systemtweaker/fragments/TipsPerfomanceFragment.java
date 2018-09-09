package com.nowenui.systemtweaker.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nowenui.systemtweaker.R;

public class TipsPerfomanceFragment extends Fragment {

    public static TipsPerfomanceFragment newInstance(Bundle bundle) {
        TipsPerfomanceFragment SovetsPerfomance = new TipsPerfomanceFragment();

        if (bundle != null) {
            SovetsPerfomance.setArguments(bundle);
        }

        return SovetsPerfomance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tips_performance, parent, false);
    }


}