package com.example.daocomics.ui.comic;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.daocomics.R;
import com.example.daocomics.adapter.ChapterListAdapter;
import com.example.daocomics.model.Chapter;

import java.util.ArrayList;


public class ListChapterFragment extends Fragment {
    RecyclerView lsvListChap;
    ChapterListAdapter chapterListAdapter;
    Context ct;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_chapter, container, false);
        lsvListChap = v.findViewById(R.id.lsvListChap);
        ComicDetailsActivity comicDetailsActivity = (ComicDetailsActivity) getActivity();
        ct = comicDetailsActivity.getBaseContext();
        chapterListAdapter = comicDetailsActivity.GetChapterListAdapter();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        lsvListChap.setAdapter(chapterListAdapter);
        lsvListChap.setLayoutManager(linearLayoutManager);

        // Inflate the layout for this fragment
        return v;
    }
}