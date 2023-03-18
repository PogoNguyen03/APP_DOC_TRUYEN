package com.example.app_truyen_tranh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.example.app_truyen_tranh.adapter.TruyenTranhAdapter;
import com.example.app_truyen_tranh.api.ApiLayTruyen;
import com.example.app_truyen_tranh.interfaces.LayTruyenVe;
import com.example.app_truyen_tranh.object.TruyenTranh;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LayTruyenVe {
GridView gdvDSTruyen;
TruyenTranhAdapter adapter;
ArrayList<TruyenTranh>truyenTranhArrayList;
EditText edtTimKiemTruyen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        anhXa();
        setUp();
        setClik();
        new ApiLayTruyen(this).execute();
    }
    private  void  init(){
        truyenTranhArrayList = new ArrayList<>();

        adapter = new TruyenTranhAdapter(this,0,truyenTranhArrayList);
    }
    private  void  anhXa(){
        gdvDSTruyen = findViewById(R.id.gdvDSTruyen);
        edtTimKiemTruyen = findViewById(R.id.edtTimKiemTruyen);
    }
    private  void  setUp(){
        gdvDSTruyen.setAdapter(adapter);
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

    @Override
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

    }
}