package com.example.practical06;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    private final Context context;

    public Integer[] Images = {
            R.drawable.lion, R.drawable.tiger, R.drawable.elephant, R.drawable.bear, R.drawable.deer, R.drawable.wolf,
            R.drawable.zibra, R.drawable.cheetah, R.drawable.cow, R.drawable.dog, R.drawable.havaj, R.drawable.peacock,
    };

    public ImageAdapter(Context c){
        context = c;
    }
    @Override
    public int getCount() {
        return Images.length;
    }
    @Override
    public Object getItem(int i) {
        return Images[i];
    }
    @Override
    public long getItemId(int i) {
        return 0;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(Images[i]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(300, 300));

        imageView.setOnClickListener(v -> zoomAndRotatePhoto(imageView));
        return imageView;
    }

    private void zoomAndRotatePhoto(ImageView imageView) {
        ScaleAnimation zoomAnimation = new ScaleAnimation(1, 1.5f, 1, 1.5f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        zoomAnimation.setDuration(1500);
        zoomAnimation.setFillAfter(true);
        RotateAnimation rotateAnimation = new RotateAnimation(0, 90,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(1500);
        rotateAnimation.setFillAfter(true);
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(zoomAnimation);
        animationSet.addAnimation(rotateAnimation);
        imageView.startAnimation(animationSet);
    }
}

