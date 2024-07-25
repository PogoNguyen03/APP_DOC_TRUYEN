package com.example.daocomics.ui.tabmain;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.daocomics.MainActivity;
import com.example.daocomics.R;
import com.example.daocomics.adapter.FavComicAdapter;
import com.example.daocomics.model.Comic;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class FavouriteFragment extends Fragment {
    RecyclerView rcvFavList;
    FavComicAdapter comicAdapter;

    MainActivity temp;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_favourite, container, false);
        rcvFavList = v.findViewById(R.id.rcvFavList);

        temp  = (MainActivity)getActivity();

        comicAdapter = new FavComicAdapter(getActivity());
        getFavComicfromFireBase();
        rcvFavList.setHasFixedSize(true);

        LinearLayoutManager ln = new LinearLayoutManager(getActivity());
        rcvFavList.setLayoutManager(ln);
        rcvFavList.setAdapter(comicAdapter);
        // Inflate the layout for this fragment
        return v;
    }

    public void getFavComicfromFireBase() {
        FirebaseFirestore.getInstance().collection("FavComic").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<DocumentSnapshot> documentSnapshotList = queryDocumentSnapshots.getDocuments();
                comicAdapter.getArrayList().clear();
                for (DocumentSnapshot dc : documentSnapshotList)
                {
                    if(FirebaseAuth.getInstance().getCurrentUser().getUid().equals(dc.get("UsName"))){
                        ArrayList<Comic> tempc =  temp.getComicsAdapter().getComicsList();
                        for(Comic c : tempc){
                            if(dc.get("ComicName").toString().equals(c.getName())) {
                                comicAdapter.Add(c);
                            }
                        }
                    }
                }
            }
        });
    }

}