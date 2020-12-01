package com.example.ccd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ccd.controller.commentEnrollHttp;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class commentEnrollPopup extends AppCompatActivity {
    EditText enrollComment;
    Button commentBtn, commentCancleBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setTitle("댓글 등록 팝업");
        setContentView(R.layout.activity_comment_enroll_popup);

        enrollComment = findViewById(R.id.enrollComment);
        commentBtn = findViewById(R.id.commentBtn);
        commentCancleBtn = findViewById(R.id.commentCancleBtn);

        Intent intent = getIntent();
        final String postTitle = intent.getStringExtra("forumTitleText");
        final String postContent = intent.getStringExtra("forumContentText");

        final JSONObject jsonObject = new JSONObject();
        commentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //등록
                //json 변환
                String comment = enrollComment.getText().toString();
                //memberID 추가
                SharedPreferences sharedPreferences = getSharedPreferences("Value", MODE_PRIVATE);    // test 이름의 기본모드 설정, 만약 test key값이 있다면 해당 값을 불러옴.
                String id = sharedPreferences.getString("id", "");
                String result = comment + "/" + postTitle + "/" + postContent + "/" + id;

                commentEnrollHttp hc = new commentEnrollHttp(result);
                hc.execute();

                result = null;
                try {
                    result = hc.get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (result.equals("success")) {
                    //원래 화면으로
                    Toast.makeText(commentEnrollPopup.this, "댓글이 등록되었습니다.", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(commentEnrollPopup.this, "댓글 등록 실패!ㅜ", Toast.LENGTH_SHORT).show();
                }

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


    }
}