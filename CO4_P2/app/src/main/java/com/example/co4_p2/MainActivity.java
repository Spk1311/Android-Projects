package com.example.co4_p2;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView mRedBall = findViewById(R.id.red_ball);
        TextView mBlueBall = findViewById(R.id.blue_ball);
        RotateAnimation redAnimation = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        redAnimation.setDuration(3000);
        redAnimation.setRepeatCount(Animation.INFINITE);
        redAnimation.setInterpolator(new LinearInterpolator());

        RotateAnimation blueAnimation = new RotateAnimation(0, -360,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        blueAnimation.setDuration(3000);
        blueAnimation.setRepeatCount(Animation.INFINITE);
        blueAnimation.setInterpolator(new LinearInterpolator());

        mRedBall.startAnimation(redAnimation);
        mBlueBall.startAnimation(blueAnimation);
    }
}
