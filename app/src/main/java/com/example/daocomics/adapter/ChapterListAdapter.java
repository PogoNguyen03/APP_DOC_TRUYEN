package com.example.daocomics.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.daocomics.R;
import com.example.daocomics.model.Chapter;
import com.example.daocomics.ui.comic.ComicDetailsActivity;
import com.example.daocomics.ui.comic_read.ReadComicActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ChapterListAdapter extends RecyclerView.Adapter<ChapterListAdapter.ChapterListViewHolder> {
    private Context ct;
    private ArrayList<Chapter> arr;
    public Chapter getFirst(){
        for (Chapter c : arr)
            if(c.getChapterName().equals("Chapter 1"))
                return c;
        return null;
    }

    public void addChapter(Chapter chapter){
        arr.add(chapter);
        Collections.sort(arr, new Comparator<Chapter>() {
            @Override
            public int compare(Chapter chapter, Chapter t1) {
                return chapter.getChapterName().compareTo(t1.getChapterName());
            }
        });
        notifyDataSetChanged();
    }

    public ChapterListAdapter(Context ct) {
        arr = new ArrayList<>();
        this.ct = ct;
    }


    @NonNull
    @Override
    public ChapterListAdapter.ChapterListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_chaplist,parent,false);
        return new ChapterListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ChapterListAdapter.ChapterListViewHolder holder, int position) {
        Chapter chapter = arr.get(position);
        holder.name.setText(chapter.getChapterName());
        holder.chapter.setOnClickListener(v->readChap(chapter));
    }

    private void readChap(Chapter chapter ) {
        Intent i = new Intent(ct, ReadComicActivity.class);
        Bundle b = new Bundle();
        b.putSerializable("chapter",chapter);
        i.putExtras(b);
        ct.startActivity(i);

    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    public class ChapterListViewHolder extends RecyclerView.ViewHolder{
        LinearLayout chapter;
        TextView name;
        public ChapterListViewHolder(@NonNull View itemView) {
            super(itemView);
            chapter = itemView.findViewById(R.id.chapter);
            name = itemView.findViewById(R.id.tvChapName);
        }
    }
}
