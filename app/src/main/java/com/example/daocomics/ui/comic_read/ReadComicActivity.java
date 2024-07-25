package com.example.daocomics.ui.comic_read;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.daocomics.R;
import com.example.daocomics.adapter.ReadAdapter;
import com.example.daocomics.databinding.ActivityComicDetailsBinding;
import com.example.daocomics.databinding.ActivityMainBinding;
import com.example.daocomics.databinding.ActivityReadComicBinding;
import com.example.daocomics.model.Chapter;
import com.example.daocomics.model.ImgChap;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ReadComicActivity extends AppCompatActivity {
    ActivityReadComicBinding binding;
    ReadAdapter adapter,readAdapter;
    Chapter chapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_comic);
        binding = ActivityReadComicBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        getChapter();
        setAdapter();


        //event
        event();

    }

    private void setAdapter() {
        binding.rcvRead.setHasFixedSize(false);
        binding.rcvRead.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ReadAdapter(this);
        getImgToRead();
        binding.rcvRead.setAdapter(adapter);
        binding.rcvRead.setOnTouchListener(new TranslateAnimationUtil(this,binding.actionBar));

    }

    private void event() {
        binding.btnBack.setOnClickListener(v->finish());
    }


    private void setupActionbar() {
        binding.btnNextChap.setOnClickListener(v-> nextC());
        binding.btnPreChap.setOnClickListener(v-> preC());

        binding.textView3.setText(chapter.getChapterName());

        if(chapter.getPreChap().isEmpty()) {
            binding.btnPreChap.setColorFilter(getResources().getColor(R.color.xam1));
            binding.btnPreChap.setEnabled(false);
        }
        else {
            binding.btnPreChap.setColorFilter(getResources().getColor(R.color.xanhduong));
            binding.btnPreChap.setEnabled(true);
        }

        if(chapter.getNextChap().isEmpty()) {
            binding.btnNextChap.setColorFilter(getResources().getColor(R.color.xam1));
            binding.btnNextChap.setEnabled(false);
        }
        else {
            binding.btnNextChap.setColorFilter(getResources().getColor(R.color.xanhduong));
            binding.btnNextChap.setEnabled(true);
        }

    }

    private void preC() {
        FirebaseFirestore.getInstance().collection("Chapter").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<DocumentSnapshot> documentSnapshotList = queryDocumentSnapshots.getDocuments();
                for (DocumentSnapshot dc : documentSnapshotList)
                {
                    if(chapter.getPreChap().equals(dc.getId()))
                    {
                        Chapter pre = dc.toObject(Chapter.class);
                        Intent i = new Intent(ReadComicActivity.this, ReadComicActivity.class);
                        Bundle b = new Bundle();
                        b.putSerializable("chapter",pre);
                        i.putExtras(b);
                        ReadComicActivity.this.finish();
                        startActivity(i);
                    }
                }
            }
        });
    }

    private void nextC() {
        FirebaseFirestore.getInstance().collection("Chapter").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<DocumentSnapshot> documentSnapshotList = queryDocumentSnapshots.getDocuments();
                for (DocumentSnapshot dc : documentSnapshotList)
                {
                    if(chapter.getNextChap().equals(dc.getId())) {
                        Chapter next = dc.toObject(Chapter.class);
                        Intent i = new Intent(ReadComicActivity.this, ReadComicActivity.class);
                        Bundle b = new Bundle();
                        b.putSerializable("chapter",next);
                        i.putExtras(b);
                        ReadComicActivity.this.finish();
                        startActivity(i);
                    }
                }
            }
        });
    }

    private void getChapter() {
        Bundle b = getIntent().getExtras();
        chapter = (Chapter)b.getSerializable("chapter");
        setupActionbar();

    }

    private void getImgToRead() {

        FirebaseFirestore.getInstance().collection("ImgChap").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<DocumentSnapshot> documentSnapshotList = queryDocumentSnapshots.getDocuments();
                for (DocumentSnapshot dc : documentSnapshotList)
                {
                    if(dc.get("chapterID").toString().equals(chapter.getId())) {
                        ImgChap img = dc.toObject(ImgChap.class);
                        adapter.Add(img);
                    }
                }
            }
        });
    }
}