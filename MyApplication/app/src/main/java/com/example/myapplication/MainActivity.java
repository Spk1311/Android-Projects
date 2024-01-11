package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView title_txt = (TextView) findViewById(R.id.myTextView);
        Animation rotate = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_m);
        title_txt.startAnimation(rotate);

        rotate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // start the fade animation after the rotate animation finishes
                Animation fade = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade);
                title_txt.startAnimation(fade);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }
}