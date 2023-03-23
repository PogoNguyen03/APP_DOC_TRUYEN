package com.example.app_truyen_tranh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;

import com.example.app_truyen_tranh.adapter.TruyenTranhAdapter;
import com.example.app_truyen_tranh.fragment.ScreenSlidePageAdapter;
import com.example.app_truyen_tranh.object.TruyenTranh;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity /*implements LayTruyenVe*/ {
    GridView gdvDSTruyen;
    TruyenTranhAdapter adapter;
    ArrayList<TruyenTranh>truyenTranhArrayList;
    EditText edtTimKiemTruyen;
    BottomNavigationView btnavView;

    // using to move between Fragment
    ViewPager2 viewPager2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhXa();


        /*new ApiLayTruyen(this).execute();*/


        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Main");
        actionBar.setDisplayHomeAsUpEnabled(true);


        // using to move between Fragment
        setupFragment();


    }
    private  void  anhXa(){

        btnavView = findViewById(R.id.navMenu);
        viewPager2 = findViewById(R.id.fragVPager);
        edtTimKiemTruyen = findViewById(R.id.edtTimKiemTruyen);
    }
/*    private  void  setClik(){
    edtTimKiemTruyen.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            String s = edtTimKiemTruyen.getText().toString();
            adapter.sortTruyen(s);
        }
    });
    }*/


   /* @Override
    public void batDau() {
        Toast.makeText(this,"Dang Lay Ve",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ketThuc(String data) {
        try{
            truyenTranhArrayList.clear();
            JSONArray arr = new JSONArray(data);
            for (int i=0; i<arr.length();i++){
                JSONObject o = arr.getJSONObject(i);
                truyenTranhArrayList.add(new TruyenTranh(o));
            }
            adapter = new TruyenTranhAdapter(this,0,truyenTranhArrayList);
            gdvDSTruyen.setAdapter(adapter);

        }catch (JSONException e){

        }
    }

    @Override
    public void biLoi() {
        Toast.makeText(this,"Loi ket noi",Toast.LENGTH_SHORT).show();

    }*/
    void setupFragment(){
        ScreenSlidePageAdapter fragAdapter = new ScreenSlidePageAdapter(this);
        viewPager2.setAdapter(fragAdapter);
        btnavView.setOnItemSelectedListener(getBtnListener());
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position)
                {
                    case 0:
                        btnavView.getMenu().findItem(R.id.mnHome).setChecked(true);
                        break;
                    case 1:
                        btnavView.getMenu().findItem(R.id.mnInfo).setChecked(true);
                        break;
                    case 2:
                        btnavView.getMenu().findItem(R.id.mnSetting).setChecked(true);
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
                getSupportActionBar().setTitle(item.getTitle());
                switch(item.getItemId()) {
                    case R.id.mnHome:
                        getSupportActionBar().setTitle(item.getTitle());
                        viewPager2.setCurrentItem(0);
                        return true;
                    case R.id.mnInfo:
                        getSupportActionBar().setTitle(item.getTitle());
                        viewPager2.setCurrentItem(1);
                        return true;
                    case R.id.mnSetting:
                        getSupportActionBar().setTitle(item.getTitle());
                        viewPager2.setCurrentItem(2);
                        return true;
                }
                return false;
            }
        };
    }



}