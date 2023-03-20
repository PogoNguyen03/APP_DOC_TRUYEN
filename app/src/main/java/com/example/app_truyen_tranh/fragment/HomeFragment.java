package com.example.app_truyen_tranh.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.app_truyen_tranh.MainActivity;
import com.example.app_truyen_tranh.R;
import com.example.app_truyen_tranh.adapter.TruyenTranhAdapter;


public class HomeFragment extends Fragment {

    GridView gdvDSTruyen;
    TruyenTranhAdapter adapter;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_home, container, false);
    }


}