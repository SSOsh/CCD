package com.example.ccd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ccd.controller.statusEnrollHttp;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

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
                SharedPreferences sharedPreferences= getSharedPreferences("Value", MODE_PRIVATE);    // test 이름의 기본모드 설정, 만약 test key값이 있다면 해당 값을 불러옴.
                String id = sharedPreferences.getString("id","");

                String result = title + "/" + author + "/" + id;

                statusEnrollHttp hc = new statusEnrollHttp(result);
                hc.execute();

                try {
                    String r = hc.get();

                    if(r.equals(true)) {
                        //원래 화면으로
                        Toast.makeText(bookStatusEnroll.this, "독서 상황이 등록되었습니다.", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else {
                        Toast.makeText(bookStatusEnroll.this, "독서 상황이 등록에 실패했습니다.", Toast.LENGTH_SHORT).show();
                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


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