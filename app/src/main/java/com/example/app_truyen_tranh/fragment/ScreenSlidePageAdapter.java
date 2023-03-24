package com.example.app_truyen_tranh.fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.app_truyen_tranh.MainActivity;
import com.example.app_truyen_tranh.R;

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
                return new InfoFragment();
            case 2:
                return new SettingFragment();
            default:
                return  new HomeFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

}
