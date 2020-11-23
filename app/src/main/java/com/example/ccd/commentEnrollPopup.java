package com.example.ccd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ccd.controller.commentEnrollHttp;

import org.json.JSONException;
import org.json.JSONObject;

public class commentEnrollPopup extends AppCompatActivity {
    EditText enrollComment;
    Button commentBtn, commentCancleBtn, enrollStatusBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("댓글 등록 팝업");
        setContentView(R.layout.activity_comment_enroll_popup);

        enrollComment = findViewById(R.id.enrollComment);
        commentBtn = findViewById(R.id.commentBtn);
        commentCancleBtn = findViewById(R.id.commentCancleBtn);
        enrollStatusBtn = findViewById(R.id.enrollStatusBtn);

        Intent intent = getIntent();

        final JSONObject jsonObject = new JSONObject();
        commentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //등록
                //json 변환
                String comment = enrollComment.getText().toString();
                //memberID 추가
                String result = comment;

                try {
                    jsonObject.put("comment", comment);
                    //memberID 추가
                } catch(JSONException e) {
                    e.printStackTrace();
                }

                commentEnrollHttp hc = new commentEnrollHttp(result);
                hc.execute();

                //원래 화면으로
                Toast.makeText(commentEnrollPopup.this, "댓글이 등록되었습니다.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        commentCancleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //취소
                finish();
                Toast.makeText(commentEnrollPopup.this, "댓글 등록이 취소되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        enrollStatusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), bookStatusEnroll.class);
                startActivity(intent);
            }
        });
    }
}