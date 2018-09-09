package com.nowenui.systemtweaker.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nowenui.systemtweaker.R;

public class AgreementFragment extends Fragment {

    public static AgreementFragment newInstance(Bundle bundle) {
        AgreementFragment Agreement = new AgreementFragment();

        if (bundle != null) {
            Agreement.setArguments(bundle);
        }

        return Agreement;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.agreement, parent, false);
    }

}