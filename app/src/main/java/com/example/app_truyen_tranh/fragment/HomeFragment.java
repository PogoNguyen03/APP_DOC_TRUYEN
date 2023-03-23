package com.example.app_truyen_tranh.fragment;

import android.os.Bundle;

import androidx.annotation.ContentView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.app_truyen_tranh.MainActivity;
import com.example.app_truyen_tranh.R;
import com.example.app_truyen_tranh.adapter.TruyenTranhAdapter;
import com.example.app_truyen_tranh.object.TruyenTranh;

import java.util.ArrayList;


public class HomeFragment extends Fragment{

    GridView gdvDSTruyen;
    TruyenTranhAdapter adapter;
    ArrayList<TruyenTranh>truyenTranhArrayList;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gdvDSTruyen = view.findViewById(R.id.gdvDSTruyen);
        init();
        gdvDSTruyen.setAdapter(adapter);

    }

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home,container,false);

        return v;
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


        adapter = new TruyenTranhAdapter(getContext(),0,truyenTranhArrayList);
    }

}