package com.example.daocomics.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.daocomics.R;
import com.example.daocomics.model.ImgChap;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ReadAdapter extends RecyclerView.Adapter<ReadAdapter.MyViewHolder> {
    Context ct;
    ArrayList<ImgChap> imgChaps;

    public ReadAdapter(Context ct) {
       this.ct = ct;
       this.imgChaps = new ArrayList<>();
    }
    public void Add(ImgChap item){
        imgChaps.add(item);

        Collections.sort(imgChaps, new Comparator<ImgChap>() {
            @Override
            public int compare(ImgChap imgChap, ImgChap t1) {
                return (int) imgChap.getPage() - t1.getPage();
            }
        });
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public ReadAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_img_read,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ReadAdapter.MyViewHolder holder, int position) {
        ImgChap item = imgChaps.get(position);
        Picasso.get().load(item.getUrl()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return imgChaps.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imgRead);
        }
    }
}
