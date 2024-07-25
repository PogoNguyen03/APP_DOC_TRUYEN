package com.example.daocomics.ui.setting;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.daocomics.R;
import com.google.android.material.button.MaterialButton;


public class FeedbackFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_feedback, container, false);
        ImageButton b = v.findViewById(R.id.btnBack);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
        MaterialButton s = v.findViewById(R.id.sendFback);
        s.setOnClickListener(vs ->getActivity().finish());
        return v;
    }
}