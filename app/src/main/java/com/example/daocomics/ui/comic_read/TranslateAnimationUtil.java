package com.example.daocomics.ui.comic_read;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;

import com.example.daocomics.R;

public class TranslateAnimationUtil implements View.OnTouchListener{
    private GestureDetector gestureDetector;
    public TranslateAnimationUtil(Context ct, View v){
        gestureDetector = new GestureDetector(ct,new SimpleGestureDetector(v));
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return gestureDetector.onTouchEvent(motionEvent);
    }

    public  class SimpleGestureDetector extends GestureDetector.SimpleOnGestureListener{
        private View view;
        public boolean isfinishA = true;

        @Override
        public boolean onScroll(@NonNull MotionEvent e1, @NonNull MotionEvent e2, float distanceX, float distanceY) {
            if(distanceY > 0)
                hiddenView();
            else showView();
            return super.onScroll(e1, e2, distanceX, distanceY);
        }

        private void showView() {
            if(view == null || view.getVisibility() == view.VISIBLE)
                return;
            Animation animationUp = AnimationUtils.loadAnimation(view.getContext(), R.anim.btomup);
            animationUp.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    view.setVisibility(View.VISIBLE);
                    isfinishA = false;
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    isfinishA = true;
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            if (isfinishA)
                view.startAnimation(animationUp);
        }

        private void hiddenView() {
            if(view == null || view.getVisibility() == view.GONE)
                return;
            Animation animationDown = AnimationUtils.loadAnimation(view.getContext(),R.anim.btomdown);
            animationDown.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    view.setVisibility(View.VISIBLE);
                    isfinishA = false;
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    view.setVisibility(View.GONE);
                    isfinishA = true;
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            if (isfinishA)
                view.startAnimation(animationDown);
        }

        public SimpleGestureDetector(View view) {
            this.view = view;
        }
    }
}
