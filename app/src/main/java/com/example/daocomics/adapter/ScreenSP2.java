package com.example.daocomics.adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.daocomics.MainActivity;
import com.example.daocomics.ui.comic.ComicDetailsActivity;
import com.example.daocomics.ui.comic.DesciptonFragment;
import com.example.daocomics.ui.comic.ListChapterFragment;
import com.example.daocomics.ui.tabmain.ExploreFragment;
import com.example.daocomics.ui.tabmain.FavouriteFragment;
import com.example.daocomics.ui.tabmain.HomeFragment;

public class ScreenSP2 extends FragmentStateAdapter {
    public ScreenSP2(@NonNull ComicDetailsActivity comicDetailsActivity) {
        super(comicDetailsActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 0:

                return new DesciptonFragment();
            case 1:

                return new ListChapterFragment();
            default:

                return  new DesciptonFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

}
