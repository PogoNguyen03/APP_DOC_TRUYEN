package com.example.app_truyen_tranh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.example.app_truyen_tranh.adapter.TruyenTranhAdapter;
import com.example.app_truyen_tranh.api.ApiLayTruyen;
import com.example.app_truyen_tranh.fragment.HomeFragment;
import com.example.app_truyen_tranh.fragment.InfoFragment;
import com.example.app_truyen_tranh.fragment.ScreenSlidePageAdapter;
import com.example.app_truyen_tranh.fragment.SettingFragment;
import com.example.app_truyen_tranh.interfaces.LayTruyenVe;
import com.example.app_truyen_tranh.object.TruyenTranh;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity /*implements LayTruyenVe*/ {
    GridView gdvDSTruyen;
    TruyenTranhAdapter adapter;
    ArrayList<TruyenTranh>truyenTranhArrayList;
    EditText edtTimKiemTruyen;
    BottomNavigationView btnavView;

    // using to move between Fragment
    ViewPager2 viewPager2;
    FragmentStateAdapter fragmentStateAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhXa();
        init();
        gdvDSTruyen.setAdapter(adapter);
        setClik();
        /*new ApiLayTruyen(this).execute();*/

        btnavView = findViewById(R.id.navMenu);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Main");
        actionBar.setDisplayHomeAsUpEnabled(true);

        btnavView.setOnItemSelectedListener(getBtnListener());

        // using to move between Fragment
/*        viewPager2 = findViewById(R.id.fragVPager);
        fragmentStateAdapter = new ScreenSlidePageAdapter(MainActivity.this);
        viewPager2.setAdapter(fragmentStateAdapter);*/
    }
    private  void  init(){
        truyenTranhArrayList = new ArrayList<>();
        truyenTranhArrayList.add(new TruyenTranh("Kakkou no Iinazuke","Chapter 121","https://st.nettruyenvt.com/data/comics/202/kakkou-no-iinazuke.jpg"));
        truyenTranhArrayList.add(new TruyenTranh("Ta Chính Là Không Theo Sáo Lộ Ra Bài","Chapter 90","https://st.nettruyenvt.com/data/comics/105/chi-co-tinh-yeu-moi-co-the-ngan-can-hac-7217.jpg"));
        truyenTranhArrayList.add(new TruyenTranh("Chuyện Tình Chú Cháu: Vô Pháp Có Được Em","Chapter 92","https://st.nettruyenvt.com/data/comics/222/chuyen-tinh-chu-chau-vo-phap-co-duoc-em.jpg"));
        truyenTranhArrayList.add(new TruyenTranh("Chỉ Có Tình Yêu Mới Có Thể Ngăn Cản Hắc Hóa","Chapter 87","https://st.nettruyenvt.com/data/comics/105/chi-co-tinh-yeu-moi-co-the-ngan-can-hac-7217.jpg"));
        truyenTranhArrayList.add(new TruyenTranh("Kakkou no Iinazuke","Chapter 121","https://st.nettruyenvt.com/data/comics/202/kakkou-no-iinazuke.jpg"));
        truyenTranhArrayList.add(new TruyenTranh("Ta Chính Là Không Theo Sáo Lộ Ra Bài","Chapter 90","https://st.nettruyenvt.com/data/comics/105/chi-co-tinh-yeu-moi-co-the-ngan-can-hac-7217.jpg"));
        truyenTranhArrayList.add(new TruyenTranh("Chuyện Tình Chú Cháu: Vô Pháp Có Được Em","Chapter 92","https://st.nettruyenvt.com/data/comics/222/chuyen-tinh-chu-chau-vo-phap-co-duoc-em.jpg"));
        truyenTranhArrayList.add(new TruyenTranh("Chỉ Có Tình Yêu Mới Có Thể Ngăn Cản Hắc Hóa","Chapter 87","https://st.nettruyenvt.com/data/comics/105/chi-co-tinh-yeu-moi-co-the-ngan-can-hac-7217.jpg"));
        truyenTranhArrayList.add(new TruyenTranh("Kakkou no Iinazuke","Chapter 121","https://st.nettruyenvt.com/data/comics/202/kakkou-no-iinazuke.jpg"));
        truyenTranhArrayList.add(new TruyenTranh("Ta Chính Là Không Theo Sáo Lộ Ra Bài","Chapter 90","https://st.nettruyenvt.com/data/comics/105/chi-co-tinh-yeu-moi-co-the-ngan-can-hac-7217.jpg"));
        truyenTranhArrayList.add(new TruyenTranh("Chuyện Tình Chú Cháu: Vô Pháp Có Được Em","Chapter 92","https://st.nettruyenvt.com/data/comics/222/chuyen-tinh-chu-chau-vo-phap-co-duoc-em.jpg"));
        truyenTranhArrayList.add(new TruyenTranh("Chỉ Có Tình Yêu Mới Có Thể Ngăn Cản Hắc Hóa","Chapter 87","https://st.nettruyenvt.com/data/comics/105/chi-co-tinh-yeu-moi-co-the-ngan-can-hac-7217.jpg"));


        adapter = new TruyenTranhAdapter(this,0,truyenTranhArrayList);
    }
    private  void  anhXa(){
        gdvDSTruyen = findViewById(R.id.gdvDSTruyen);
        edtTimKiemTruyen = findViewById(R.id.edtTimKiemTruyen);
    }
    private  void  setClik(){
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
    }

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


    @NonNull
    private NavigationBarView.OnItemSelectedListener getBtnListener() {
        return new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                getSupportActionBar().setTitle(item.getTitle());
                switch(item.getItemId()) {
                    case R.id.mnHome:
                        getSupportActionBar().setTitle(item.getTitle());
                        loadFragment(new HomeFragment());
                        return true;
                    case R.id.mnInfo:
                        getSupportActionBar().setTitle(item.getTitle());
                        loadFragment(new InfoFragment());
                        return true;
                    case R.id.mnSetting:
                        getSupportActionBar().setTitle(item.getTitle());
                        loadFragment(new SettingFragment());
                        return true;
                }
                return false;
            }
        };
    }

    void loadFragment (Fragment fmNew) {
        FragmentTransaction fmDang = getSupportFragmentManager().beginTransaction();
        fmDang.replace(R.id.main_fragment, fmNew);
        fmDang.addToBackStack(null);
        fmDang.commit();
    }
}