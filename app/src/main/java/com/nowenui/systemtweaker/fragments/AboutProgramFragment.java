package com.nowenui.systemtweaker.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nowenui.systemtweaker.R;

public class AboutProgramFragment extends Fragment {

    private boolean isClicked;

    public static AboutProgramFragment newInstance(Bundle bundle) {
        AboutProgramFragment AboutProgram = new AboutProgramFragment();

        if (bundle != null) {
            AboutProgram.setArguments(bundle);
        }

        return AboutProgram;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_aboutprogram, parent, false);
    }


    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {

        /////////////////////////////////////////////////
        ////// Links to our site //////////// ///////////
        /////////////////////////////////////////////////

        TextView textView30 = view.findViewById(R.id.textView30);

        textView30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isClicked) {
                    return;
                }
                isClicked = true;
                v.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        isClicked = false;
                    }
                }, 1000);
                Uri address = Uri.parse("http://devnowenui.wixsite.com/nowenui");
                Intent openlink = new Intent(Intent.ACTION_VIEW, address);
                startActivity(openlink);
            }
        });
        TextView textView31 = view.findViewById(R.id.textView31);

        textView31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isClicked) {
                    return;
                }
                isClicked = true;
                v.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        isClicked = false;
                    }
                }, 1000);
                Uri address = Uri.parse("http://devnowenui.wixsite.com/nowenui");
                Intent openlink = new Intent(Intent.ACTION_VIEW, address);
                startActivity(openlink);
            }
        });

    }

}