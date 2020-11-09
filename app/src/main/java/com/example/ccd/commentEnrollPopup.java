package com.example.ccd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class commentEnrollPopup extends AppCompatActivity {
    EditText enrollComment;
    Button commentBtn, commentCancleBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("댓글 등록 팝업");
        setContentView(R.layout.comment_enroll_popup);

        enrollComment = findViewById(R.id.enrollComment);
        commentBtn = findViewById(R.id.commentBtn);
        commentCancleBtn = findViewById(R.id.commentCancleBtn);

        Intent intent = getIntent();

        commentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //등록
                //원래 화면으로
                Toast.makeText(commentEnrollPopup.this, "댓글이 등록되었습니다.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        commentCancleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //취소
                Toast.makeText(commentEnrollPopup.this, "댓글 등록이 취소되었습니다.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
