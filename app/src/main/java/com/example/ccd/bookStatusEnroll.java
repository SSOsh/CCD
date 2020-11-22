package com.example.ccd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ccd.controller.statusEnrollHttp;

import org.json.JSONException;
import org.json.JSONObject;

public class bookStatusEnroll extends AppCompatActivity {
    EditText enrollTitle, enrollAuthor;
    Button statusBtn, statusCancleBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_status_enroll);

        enrollTitle = findViewById(R.id.enrollTitle);
        enrollAuthor = findViewById(R.id.enrollAuthor);
        statusBtn = findViewById(R.id.statusBtn);
        statusCancleBtn = findViewById(R.id.statusCancleBtn);

        Intent intent = getIntent();

        final JSONObject jsonObject = new JSONObject();
        //독서 상황 등록 버튼
        statusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //버튼 클릭 시 db에 정보 넘어감(읽을 책으로)
                //json 변환
                String title = enrollTitle.getText().toString();
                String author = enrollAuthor.getText().toString();
                String result = title + "/" + author;

                try {
                    jsonObject.put("title", title);
                    jsonObject.put("author", author);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                jsonObject.toString();
                statusEnrollHttp hc = new statusEnrollHttp(result);
                hc.execute();

                //원래 화면으로
                Toast.makeText(bookStatusEnroll.this, "독서 상황이 등록되었습니다.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        //취소
        statusCancleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Toast.makeText(bookStatusEnroll.this, "독서 상황 등록이 취소되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}