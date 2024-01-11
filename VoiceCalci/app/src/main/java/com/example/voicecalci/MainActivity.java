package com.example.voicecalci;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    //IDs of all numeric buttons
    private final int[] numericButtons = {R.id.btnZero, R.id.one_button, R.id.two_button, R.id.three_button, R.id.four_button, R.id.five_button,
            R.id.six_button, R.id.seven_button, R.id.eight_button, R.id.nine_button};

    //IDs of all operators
    private final int[] operatorsButtons = {R.id.plus_button, R.id.minus_button, R.id.divide_button, R.id.btnDivide};

    private TextView txtScreen;
    private boolean lastNumeric;
    private boolean stateError;
    private boolean lastDot;
    private final int REQ_CODE_SPEECH_INPUT = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtScreen = findViewById(R.id.input_view);

        //find and set onClickListener to numeric Buttons
        setNumericOnClickListener();

        //find and set onClickListener to operators, equal button and decimal point button
        setOperatorOnClickListner();
    }
    private void setNumericOnClickListener(){
        View.OnClickListener listner = v -> {
            //just append/set the text of clicked Button
            Button button = (Button) v;
            if(stateError){
                //if current state is Error, replace the error message
                txtScreen.setText(button.getText());
                stateError = false;
            }else{
                txtScreen.append(button.getText());
            }
            //set the FLAG
            lastNumeric = true;
        };

        //assign the listener to all numeric button
        for (int id : numericButtons){
            findViewById(id).setOnClickListener(listner);
        }
    }

    @SuppressLint("SetTextI18n")
    private void setOperatorOnClickListner(){
        View.OnClickListener listner = view -> {
            //if the current state is error do not append the operator
            //If the last input is number only, append the operator
            if(lastNumeric && !stateError){
                Button button = (Button) view;
                txtScreen.append(button.getText());
                lastNumeric = false;
                lastDot = false; //rest the DOT flag
            }
        };
        // assign the listner to all the operator buttons
        for (int id : operatorsButtons){
            findViewById(id).setOnClickListener(listner);
        }
        //decimal point
        findViewById(R.id.btnDot).setOnClickListener(view -> {
            if (lastNumeric && !stateError && !lastDot){
                txtScreen.append(".");
                lastNumeric = false;
                lastDot = false;
            }
        });
        //clear button
        findViewById(R.id.btnClear).setOnClickListener(view -> {
            txtScreen.setText("");
            lastNumeric = false;
            stateError = false;
            lastDot = false;
        });
        //equal button
        findViewById(R.id.btnEqual).setOnClickListener(view -> onEqual());
        //speak button
        findViewById(R.id.voice_button).setOnClickListener(view -> {
            if(stateError){
                txtScreen.setText("Try Again");
                stateError = false;
            }else{
                promptSpeechInput();
            }
            lastNumeric = true;
        });
    }



    @SuppressLint("SetTextI18n")
    private void onEqual() {
        if (lastNumeric && !stateError){
            String txt = txtScreen.getText().toString();

            //create an expression
            try{
                Expression expression;
                try{
                    expression = new ExpressionBuilder(txt).build();
                    double result = expression.evaluate();
                    txtScreen.setText(Double.toString(result));
                }catch (Exception e){
                    txtScreen.setText("Error");
                }
            }
            catch (ArithmeticException ex){
                txtScreen.setText("Error");
                stateError = true;
                lastNumeric = false;
            }
        }
    }

    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        }catch (ActivityNotFoundException a){
            Toast.makeText(getApplicationContext(), getString(R.string.speech_not_supported), Toast.LENGTH_SHORT).show();
        }
    }
    //receiving speech input

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_CODE_SPEECH_INPUT) {
            if (resultCode == RESULT_OK && null != data) {
                ArrayList<String> result = data
                        .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                String change = result.get(0);
                change = change.replace("x", "*");
                change = change.replace("X", "*");
                change = change.replace("add", "+");
                change = change.replace("sub", "-");
                change = change.replace("to", "2");
                change = change.replace("plus", "+");
                change = change.replace("minus", "-");
                change = change.replace("times", "*");
                change = change.replace("into", "*");
                change = change.replace("in2", "*");
                change = change.replace("multiply by", "*");
                change = change.replace("divide by", "/");
                change = change.replace("divide", "/");
                change = change.replace("equal", "/");
                change = change.replace("equals", "/");

                if (change.contains("=")) {
                    change = change.replace("=", "");
                    txtScreen.setText(change);
                    onEqual();
                } else {
                    txtScreen.setText(change);
                }
            }
        }
    }
}

