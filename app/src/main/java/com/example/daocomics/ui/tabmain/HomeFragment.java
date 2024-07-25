package com.example.daocomics.ui.tabmain;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.daocomics.MainActivity;
import com.example.daocomics.R;
import com.example.daocomics.Utils;
import com.example.daocomics.adapter.ComicsAdapter;
import com.example.daocomics.ui.comic_read.TranslateAnimationUtil;
import com.example.daocomics.ui.genre.GenreActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class HomeFragment extends Fragment{
    RecyclerView rcvHome;
    ComicsAdapter comicsAdapter;
    TextView tvHello;
    MainActivity temp;
    FirebaseUser firebaseUser;
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         v = inflater.inflate(R.layout.fragment_home, container, false);
        rcvHome = v.findViewById(R.id.rcvHome);
        tvHello = v.findViewById(R.id.tvHello);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        loadInfo();
        event();

        temp = (MainActivity) getActivity();
        comicsAdapter = temp.getComicsAdapter();
        GridLayoutManager grv = new GridLayoutManager(getActivity(),3);
        rcvHome.setAdapter(comicsAdapter);
        rcvHome.setLayoutManager(grv);
        // Inflate the layout for this fragment

        return v;
    }

    private void event() {
        Button btTL = v.findViewById(R.id.btnTheLoai);
        MaterialButton b1 = v.findViewById(R.id.btnCateAct);
        MaterialButton b2 = v.findViewById(R.id.btnCateCom);
        MaterialButton b3 = v.findViewById(R.id.btnCateHor);
        MaterialButton b4 = v.findViewById(R.id.btnCateRom);

        btTL.setOnClickListener(view -> intent(1,""));
        b1.setOnClickListener(v->intent(2,"Hành động"));
        b2.setOnClickListener(v->intent(2,"Hài kịch"));
        b3.setOnClickListener(v->intent(2,"Kinh dị"));
        b4.setOnClickListener(v->intent(2,"Tình cảm"));
    }

    private void intent(int x,String genre) {
        Intent i = new Intent(getActivity(), GenreActivity.class);
        i.putExtra("fragmentG",x);
        i.putExtra("genre",genre);
        startActivity(i);

    }

    private void loadInfo() {
        FirebaseFirestore.getInstance().collection("Users").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                    String s = documentSnapshot.getString("email");
                    if(firebaseUser.getEmail().equals(s)){
                        tvHello.setText("Xin chào "+documentSnapshot.getString("name"));
                    }
                }
            }
        });
    }
}