package com.example.ccd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.net.HttpURLConnection;

public class MainActivity extends AppCompatActivity {
    Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //이 뒤의 내용은 연습용, 나중에 삭제
        button2 = findViewById(R.id.button2);
        final RequestHttpConnection httpConnection = new RequestHttpConnection();
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                httpConnection.request("", null);
            }

        });


    }
}
