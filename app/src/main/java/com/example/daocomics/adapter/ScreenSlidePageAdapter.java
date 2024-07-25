package com.example.daocomics.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.daocomics.MainActivity;
import com.example.daocomics.ui.tabmain.ExploreFragment;
import com.example.daocomics.ui.tabmain.FavouriteFragment;
import com.example.daocomics.ui.tabmain.HomeFragment;

public class ScreenSlidePageAdapter extends FragmentStateAdapter {
    public ScreenSlidePageAdapter(@NonNull MainActivity mainActivity) {
        super(mainActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new HomeFragment();
            case 1:
                return new ExploreFragment();
            case 2:
                return new FavouriteFragment();
            default:
                return  new HomeFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

}
