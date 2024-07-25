package com.example.daocomics.ui.comic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.daocomics.MainActivity;
import com.example.daocomics.R;
import com.example.daocomics.adapter.ChapterListAdapter;
import com.example.daocomics.adapter.ScreenSP2;
import com.example.daocomics.adapter.ScreenSlidePageAdapter;
import com.example.daocomics.databinding.ActivityComicDetailsBinding;
import com.example.daocomics.databinding.ActivityMainBinding;
import com.example.daocomics.model.Chapter;
import com.example.daocomics.model.Comic;
import com.example.daocomics.model.FavComic;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class ComicDetailsActivity extends AppCompatActivity{
    ActivityComicDetailsBinding binding;
    Comic comic;
    ChapterListAdapter chapterListAdapter;
    FirebaseUser user;
    private FirebaseFirestore db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic_details);
        binding = ActivityComicDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        user = FirebaseAuth.getInstance().getCurrentUser();
        db = FirebaseFirestore.getInstance();
        //set up View
        setUpView();
        //get data
        getComicOnclicked();
        // get chapter list
        getChapterList();
        //set 2 fragment
        setupFragment();
        //Favourite
        setupFAV();
    }

    private void setupFAV() {
       checkFav();
        binding.btnFav.setOnClickListener(view -> addOrRemoveFav());

    }
    private void setUpView() {
        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);if(getSupportActionBar()!= null){getSupportActionBar().setDisplayHomeAsUpEnabled(true);}
        binding.CollapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.xanh2));
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ComicDetailsActivity.this,MainActivity.class));
            }
        });

    }




    private void getChapterList() {
        chapterListAdapter = new ChapterListAdapter(this);
        FirebaseFirestore.getInstance().collection("Chapter").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<DocumentSnapshot> documentSnapshotList = queryDocumentSnapshots.getDocuments();
                for (DocumentSnapshot dc : documentSnapshotList)
                {
                    if(comic.getName().equals(dc.get("comicName").toString().trim())) {
                        Chapter chapter = dc.toObject(Chapter.class);
                        chapterListAdapter.addChapter(chapter);
                    }
                }
            }
        });
    }


    private void getComicOnclicked() {
        Bundle b = getIntent().getExtras();
        comic = (Comic) b.getSerializable("comic");
        binding.CollapsingToolbarLayout.setTitle(comic.getName());
        Glide.with(this).load(comic.getImageThumb()).into(binding.imgComic);
    }
    public String getComicDesciption() {
        return comic.getDesciption();
    }
    public String getComicName(){return  comic.getName();}
    public ChapterListAdapter GetChapterListAdapter() {
        return chapterListAdapter;
    }
    public Chapter getFirstChap(){
        Chapter c = new Chapter();
        return chapterListAdapter.getFirst();
    }

    void setupFragment(){
        ScreenSP2 fragAdapter = new ScreenSP2(this);
        binding.fragDesChap.setAdapter(fragAdapter);

        binding.navMenuTab.setOnItemSelectedListener(getBtnListener());
        binding.fragDesChap.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position)
                {
                    case 0:
                        binding.navMenuTab.getMenu().findItem(R.id.tabMota).setChecked(true);
                        break;
                    case 1:
                        binding.navMenuTab.getMenu().findItem(R.id.tabChapList).setChecked(true);
                        break;
                }
            }
        });
    }

    @NonNull
    private NavigationBarView.OnItemSelectedListener getBtnListener() {
        return new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.tabMota:
                        binding.fragDesChap.setCurrentItem(0);
                        return true;
                    case R.id.tabChapList:
                        binding.fragDesChap.setCurrentItem(1);
                        return true;
                }
                return false;
            }
        };
    }
    public void addToFav(){
        String id = UUID.randomUUID().toString();
        FavComic favComic = new FavComic(comic.getName(),user.getUid(),id);
        HashMap<String, Object> map = new HashMap<>();
        map.put("id",favComic.getId() );
        map.put("ComicName", favComic.getComicName());
        map.put("UsName", favComic.getUsName());
        db.collection("FavComic").document(favComic.getId()).set(map)

                .addOnCompleteListener(new OnCompleteListener<Void>() { // Kiểm tra lưu thành công không
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) { // Bắt sự kiện lưu thành công
                            Toast.makeText(ComicDetailsActivity.this, "Đã thêm vào danh sách yêu thích", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ComicDetailsActivity.this, "Lưu thất bại", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    public void removeFav(FavComic favComic){
        db.collection("FavComic").document(favComic.getId()).delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(ComicDetailsActivity.this, "Đã huỷ yêu thích", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ComicDetailsActivity.this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    public void checkFav(){
        db.collection("FavComic").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() { // sự kiện get data thành công
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (DocumentSnapshot snapshot : task.getResult()) {
                            String comicN = snapshot.getString("ComicName").trim();
                            String userN = snapshot.getString("UsName").trim();
                            if(comic.getName().equals(comicN)&&userN.equals(user.getUid())) {
                                binding.btnFav.setColorFilter(getResources().getColor(R.color.xanhduong));
                                return;
                            }
                        }
                        binding.btnFav.setColorFilter(getResources().getColor(R.color.xam));
                    }
                });
    }
    public void addOrRemoveFav(){
        db.collection("FavComic").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (DocumentSnapshot snapshot : task.getResult()) {
                            String comicN = snapshot.getString("ComicName").trim();
                            String userN = snapshot.getString("UsName").trim();

                            if(comic.getName().equals(comicN)&&userN.equals(user.getUid())) {
                                binding.btnFav.setColorFilter(getResources().getColor(R.color.xam));
                                FavComic favComic = snapshot.toObject(FavComic.class);
                                removeFav(favComic);
                                return;
                            }
                        }
                        binding.btnFav.setColorFilter(getResources().getColor(R.color.xanhduong));
                        addToFav();
                    }
                });
    }
}