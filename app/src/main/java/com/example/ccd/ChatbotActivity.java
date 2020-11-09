package com.example.ccd;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class ChatbotActivity extends AppCompatActivity {
    Button home;
    Button bookList;
    Button chatbot;
    Button mypage;
    EditText editText;
    TextView outputpart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatbot);

        Intent intent = getIntent();

        editText = findViewById(R.id.editText);
        outputpart = findViewById(R.id.outputpart);

        outputpart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "실행", Toast.LENGTH_SHORT).show();

                HttpClient hc = new HttpClient();
                hc.execute();
            }
        });
    }
}