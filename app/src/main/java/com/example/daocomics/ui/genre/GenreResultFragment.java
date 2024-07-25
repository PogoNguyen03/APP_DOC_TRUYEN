package com.example.daocomics.ui.genre;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.daocomics.MainActivity;
import com.example.daocomics.R;
import com.example.daocomics.adapter.FavComicAdapter;
import com.example.daocomics.databinding.FragmentGenreListBinding;
import com.example.daocomics.databinding.FragmentGenreResultBinding;
import com.example.daocomics.model.Comic;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class GenreResultFragment extends Fragment {
    FragmentGenreResultBinding binding;
    View v;
    FavComicAdapter comicGenreSearchAdapter;
    String genre;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGenreResultBinding.inflate(inflater, container, false);
        v = binding.getRoot();
        GenreActivity temp = (GenreActivity) getActivity();
        genre = temp.getGenre();
        binding.tvG.setText(genre);
        getComicfromFireBase();
        binding.rcvGenreSearch.setAdapter(comicGenreSearchAdapter);
        binding.rcvGenreSearch.setLayoutManager(new LinearLayoutManager(getActivity()));
        return v;
    }

    public void getComicfromFireBase() {
        comicGenreSearchAdapter = new FavComicAdapter(getActivity());
        FirebaseFirestore.getInstance().collection("Comics").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<DocumentSnapshot> documentSnapshotList = queryDocumentSnapshots.getDocuments();
                for (DocumentSnapshot dc : documentSnapshotList)
                {
                    if(dc.get("genre").toString().equals(genre))
                    {
                        Comic c = dc.toObject(Comic.class);
                        comicGenreSearchAdapter.Add(c);
                    }
                }
            }
        });
    }
}