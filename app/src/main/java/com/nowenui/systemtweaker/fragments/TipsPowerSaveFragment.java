package com.nowenui.systemtweaker.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nowenui.systemtweaker.R;

public class TipsPowerSaveFragment extends Fragment {

    public static TipsPowerSaveFragment newInstance(Bundle bundle) {
        TipsPowerSaveFragment SovetsPowerSave = new TipsPowerSaveFragment();

        if (bundle != null) {
            SovetsPowerSave.setArguments(bundle);
        }

        return SovetsPowerSave;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tips_powersave, parent, false);
    }


}