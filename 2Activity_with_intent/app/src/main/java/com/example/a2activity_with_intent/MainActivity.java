package com.example.a2activity_with_intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button send_button;
    EditText send_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        send_button = findViewById(R.id.send_button_id);
        send_text = findViewById(R.id.send_text_id);

        // add the OnClickListener in sender button after clicked this button following Instruction will run
        send_button.setOnClickListener(v -> {
            // get the value which input by user in EditText and convert it to string
            String str = send_text.getText().toString();
            // Create the Intent object of this class Context() to Second_activity class
            Intent intent = new Intent(MainActivity.this, Second_activity.class);
            // now by putExtra method put the value in key, value pair key is
            // message_key by this key we will receive the value, and put the string
            intent.putExtra("message_key", str);
            // start the Intent
            startActivity(intent);
        });
    }
}