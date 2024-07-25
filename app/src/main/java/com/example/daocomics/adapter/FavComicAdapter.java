package com.example.daocomics.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.daocomics.R;
import com.example.daocomics.model.Comic;
import com.example.daocomics.ui.comic.ComicDetailsActivity;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

public class FavComicAdapter extends RecyclerView.Adapter<FavComicAdapter.ComicViewHolder> {
    public ArrayList<Comic> getArrayList() {
        return arrayList;
    }

    ArrayList<Comic> arrayList;
    Context ct;

    public FavComicAdapter(Context ct) {
        this.ct = ct;
        arrayList = new ArrayList<>();
    }
    public void Add(Comic item){
        arrayList.add(item);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FavComicAdapter.ComicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ct = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(ct);
        View comicV = inflater.inflate(R.layout.layout_item_favcomicitem,parent,false);
        ComicViewHolder viewHolder = new ComicViewHolder(comicV);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavComicAdapter.ComicViewHolder holder, int position) {
        Comic comic = arrayList.get(position);
        holder.name.setText(comic.getName());
        Glide.with(this.ct).load(comic.getImageThumb()).into(holder.img);
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Comic comic = arrayList.get(position);
                Intent i = new Intent(ct,ComicDetailsActivity.class);
                Bundle b = new Bundle();
                b.putSerializable("comic",comic);
                i.putExtras(b);
                ct.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ComicViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        ImageView img;
        TextView name;
        ItemClickListener itemClickListener;

        public ComicViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            img = itemView.findViewById(R.id.imgMoTaTr);
            name = itemView.findViewById(R.id.tvChapName);

        }
        public void setItemClickListener(ItemClickListener itemClickListener)
        {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view,getAdapterPosition(),false);
            }
        @Override
        public boolean onLongClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition(),true);
            return true;
        }
    }

}

