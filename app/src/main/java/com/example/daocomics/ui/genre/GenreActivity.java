package com.example.daocomics.ui.genre;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.example.daocomics.R;
import com.example.daocomics.ui.setting.AboutUsFragment;
import com.example.daocomics.ui.setting.ChangePasswordFragment;
import com.example.daocomics.ui.setting.FeedbackFragment;

public class GenreActivity extends AppCompatActivity {
    String genre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre);

        int x = getIntent().getIntExtra("fragmentG",1);
        genre = getIntent().getStringExtra("genre");

        chooseFragment(x);
        ImageButton b = findViewById(R.id.btnBack);
        b.setOnClickListener(v->finish());

    }

    private void chooseFragment(int x)
    {
        switch(x) {
            case 1:
                loadFragment(new GenreListFragment());
                return ;
            case 2:
                loadFragment(new GenreResultFragment());
                return ;
        }
    }

    public void loadFragment(Fragment fmNew)
    {
        FragmentTransaction fmTran = getSupportFragmentManager().beginTransaction();
        fmTran.replace(R.id.fragGenre,fmNew);
        fmTran.addToBackStack(null);
        fmTran.commit();
    }
    public String getGenre(){return this.genre;}
    public void  setGenre(String genre){this.genre = genre;}
}