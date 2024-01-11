package com.example.practical_exam;

import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get a reference to the TextView
        TextView myTextView = (TextView) findViewById(R.id.myTextView);

        // Create the first 360-degree rotation animation
        RotateAnimation rotation1 = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotation1.setDuration(50000); // 60 seconds
        rotation1.setInterpolator(new LinearInterpolator());
        rotation1.setFillAfter(true);

        // Create the fade-in effect animation
        AlphaAnimation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setDuration(50000); // 60 seconds
        fadeIn.setInterpolator(new LinearInterpolator());
        fadeIn.setFillAfter(true);

        AlphaAnimation fadeOut = new AlphaAnimation(1,0);
        fadeOut.setDuration(50000); // 60 seconds
        fadeOut.setStartOffset(50000);
        fadeOut.setInterpolator(new LinearInterpolator());
        fadeOut.setFillAfter(true);

        ScaleAnimation zoomAnimation = new ScaleAnimation(1, 3f, 1, 3f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        zoomAnimation.setDuration(50000);

        ScaleAnimation zoomAnimation1 = new ScaleAnimation(3, 1f, 3, 1f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        zoomAnimation1.setDuration(50000);

        // Create the second 360-degree rotation animation
        RotateAnimation rotation2 = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotation2.setDuration(50000); // 60 seconds
        rotation2.setInterpolator(new LinearInterpolator());
        rotation2.setFillAfter(true);

        // Create an animation set to combine the animations
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(rotation1);
        animationSet.addAnimation(zoomAnimation1);

        AnimationSet animationSet1 = new AnimationSet(true);
        animationSet1.addAnimation(fadeIn);
        animationSet1.addAnimation(zoomAnimation);
//        animationSet.addAnimation(fadeIn);
//        animationSet.addAnimation(zoomAnimation);

        myTextView.startAnimation(animationSet);
        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // start the fade animation after the rotate animation finished
                myTextView.startAnimation(animationSet1);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }
}