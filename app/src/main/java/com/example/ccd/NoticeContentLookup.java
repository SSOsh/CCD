package com.example.ccd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

public class NoticeContentLookup extends AppCompatActivity {
    TextView noticeDate, noticeTitleText, noticeContentText;
    Button noticeListBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_content_lookup);

        Intent intent = getIntent();

        noticeDate = findViewById(R.id.noticeDate);
        noticeTitleText = findViewById(R.id.noticeTitleText);
        noticeContentText = findViewById(R.id.noticeContentText);

        noticeTitleText.setText(intent.getStringExtra("title"));
        noticeDate.setText(intent.getStringExtra("date"));
        noticeContentText.setText(intent.getStringExtra("content"));

        noticeListBtn = findViewById(R.id.noticeListBtn);
        noticeListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}