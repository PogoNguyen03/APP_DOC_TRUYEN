package com.example.daocomics.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.daocomics.MainActivity;
import com.example.daocomics.R;
import com.example.daocomics.model.Comic;
import com.example.daocomics.ui.comic.ComicDetailsActivity;
import com.squareup.picasso.Picasso;

import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;

public class ComicsAdapter extends  RecyclerView.Adapter<ComicsAdapter.ComicsViewHolder>{
    Context ct;

    public void setComicsList(ArrayList<Comic> comicsList) {
        this.comicsList = comicsList;
    }

    public ArrayList<Comic> getComicsList() {
        return comicsList;
    }

    ArrayList<Comic> comicsList;
    public ComicsAdapter(Context ct) {
        this.ct = ct;
        comicsList = new ArrayList<>();
    }
    public void Add(Comic item){
        comicsList.add(item);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ComicsAdapter.ComicsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_comic,parent,false);
        return new ComicsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ComicsAdapter.ComicsViewHolder holder, int position) {
        Comic comic = comicsList.get(position);
        Picasso.get().load(comic.getImageThumb()).into(holder.img);
        holder.name.setText(comic.getName());
        holder.item.setOnClickListener(view -> onClickComic(position));

    }

    private void onClickComic(int position) {
        Comic comic = comicsList.get(position);
        Intent i = new Intent(ct, ComicDetailsActivity.class);
        Bundle b = new Bundle();
        b.putSerializable("comic",comic);
        i.putExtras(b);
        ct.startActivity(i);
    }

    @Override
    public int getItemCount() {
        return comicsList.size();
    }

    public void SortComic(ArrayList<Comic> comicSearch, String s) {
        s = s.toUpperCase();
        int k = 0;
        comicsList.clear();
        for(Comic c : comicSearch){
            String name = c.getName().toUpperCase();
            if(name.contains(s))
                comicsList.add(c);
        }
    }

    public class ComicsViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView name;
        LinearLayout item;

        public ComicsViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imgMoTaTr);
            name = itemView.findViewById(R.id.tvChapName);
            item = itemView.findViewById(R.id.comic_item);

        }
    }
}
