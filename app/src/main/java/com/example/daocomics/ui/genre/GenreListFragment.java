package com.example.daocomics.ui.genre;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.daocomics.R;
import com.example.daocomics.databinding.FragmentGenreListBinding;


public class GenreListFragment extends Fragment {
    FragmentGenreListBinding binding;
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGenreListBinding.inflate(inflater, container, false);
        v = binding.getRoot();
        binding.btnHD.setOnClickListener(v->click("Hành động"));
        binding.btnHH.setOnClickListener(v->click("Hài hước"));
        binding.btnHK.setOnClickListener(v->click("Hài kịch"));
        binding.btnKD.setOnClickListener(v->click("Kinh dị"));
        binding.btnPL.setOnClickListener(v->click("Phiêu lưu"));
        binding.btnTC.setOnClickListener(v->click("Tình cảm"));
        binding.btnTrT.setOnClickListener(v->click("Trinh thám"));
        binding.btnTT.setOnClickListener(v->click("Thể thao"));
        return v;
    }

    private void click(String genre) {
        GenreActivity temp = (GenreActivity) getActivity();
        temp.setGenre(genre);
        temp.loadFragment(new GenreResultFragment());
    }
}