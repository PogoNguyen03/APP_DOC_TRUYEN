package com.example.app_truyen_tranh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.app_truyen_tranh.adapter.ChapTruyenAdapter;
import com.example.app_truyen_tranh.object.ChapTruyen;
import com.example.app_truyen_tranh.object.TruyenTranh;

import java.util.ArrayList;

public class ChapActivity extends AppCompatActivity {
    TextView txvTenTruyens;
    ImageView imgAnhTruyens;
    TruyenTranh truyenTranh;
    ListView lsvDanhSachChap;
    ArrayList<ChapTruyen> arrChap;
    ChapTruyenAdapter chapTruyenAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chap);
        init();
        anhXa();
        setUp();
        setClick();
    }
    private void init(){
        Bundle b = getIntent().getBundleExtra("data");
        truyenTranh =(TruyenTranh) b.getSerializable("truyen");

        arrChap = new ArrayList<>();
        for(int i = 0; i < 20; i++){
            arrChap.add(new ChapTruyen("Chapter " + i,"24-03-2023"));
        }
        chapTruyenAdapter = new ChapTruyenAdapter(this, 0,arrChap);
    }
    private void anhXa(){
        imgAnhTruyens = findViewById(R.id.imgAnhTruyens);
        txvTenTruyens = findViewById(R.id.txvTenTruyens);
        lsvDanhSachChap = findViewById(R.id.lsvDanhSachChap);
    }
    private void setUp(){
        txvTenTruyens.setText(truyenTranh.getTenChap());
        Glide.with(this).load(truyenTranh.getLinkAnh()).into(imgAnhTruyens);

        lsvDanhSachChap.setAdapter((chapTruyenAdapter));
    }
    private void setClick(){}
}