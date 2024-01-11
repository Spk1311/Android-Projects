package com.example.practical3;
import static com.example.practical3.R.drawable.img_1;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UserActivity extends AppCompatActivity {
    public static final String userName = "userName";
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        button = findViewById(R.id.button);
        Intent intent = getIntent();
        String uName = intent.getStringExtra(userName);

        TextView textView = findViewById(R.id.textView);
        textView.setText(uName);

        ImageView imageView = findViewById(R.id.imageView);
        if (!uName.equals("User 1")) {
            if (uName.equals("User 2")) {
                imageView.setImageResource(R.drawable.img_2);
            } else if (uName.equals("User 3")) {
                imageView.setImageResource(img_1);
            }
        } else {
            imageView.setImageResource(R.drawable.img);
        }

        button.setOnClickListener(v -> {
            Intent i = new Intent(UserActivity.this,MainActivity.class);
            startActivity(i);
        });
    }
}
