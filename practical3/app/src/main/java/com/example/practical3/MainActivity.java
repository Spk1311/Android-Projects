package com.example.practical3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String[] userNames = {"User 1", "User 2", "User 3"};

        ListView listView = findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, userNames);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {

            String selectedUserName = adapter.getItem(position);
            Intent intent = new Intent(MainActivity.this, UserActivity.class);
            intent.putExtra(UserActivity.userName, selectedUserName);
            Toast.makeText(MainActivity.this,"i will buy : " + selectedUserName,Toast.LENGTH_SHORT).show();
            startActivity(intent);
        });
    }
}