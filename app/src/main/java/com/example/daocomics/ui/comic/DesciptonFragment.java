package com.example.daocomics.ui.comic;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.daocomics.R;
import com.example.daocomics.adapter.ChapterListAdapter;
import com.example.daocomics.adapter.ReadAdapter;
import com.example.daocomics.model.Chapter;
import com.example.daocomics.model.ImgChap;
import com.example.daocomics.ui.comic_read.ReadComicActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class DesciptonFragment extends Fragment {
    Context ct;
    TextView tvDescrip;
    ComicDetailsActivity temp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_descipton, container, false);
        tvDescrip = v.findViewById(R.id.tvDescrip);
        temp = (ComicDetailsActivity) getActivity();
        ct = temp;
        String descrip = temp.getComicDesciption();
        String comicName = temp.getComicName();
        tvDescrip.setText(descrip);
        ImageButton b = v.findViewById(R.id.btnStartRead);
        b.setOnClickListener(r-> Read(comicName));

        return v;
    }

    private void Read(String comicName) {
        Chapter chapter = temp.getFirstChap();
         if(chapter == null) {
             Toast.makeText(getActivity(), "Chưa cập nhật truyện", Toast.LENGTH_SHORT).show();
             return;
         }
        Intent i = new Intent(getActivity(), ReadComicActivity.class);
        Bundle b = new Bundle();
        b.putSerializable("chapter",chapter);
        i.putExtras(b);
        startActivity(i);
    }

}