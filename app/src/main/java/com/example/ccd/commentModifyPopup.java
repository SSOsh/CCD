package com.example.ccd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ccd.controller.commentModifyHttp;

import org.json.JSONException;
import org.json.JSONObject;

public class commentModifyPopup extends AppCompatActivity {
    EditText modifyComment;
    Button cModifyBtn, mCancleBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_modify_popup);

        modifyComment = findViewById(R.id.modifyComment);
        cModifyBtn = findViewById(R.id.cModifyBtn);
        mCancleBtn = findViewById(R.id.mCancleBtn);

        //수정 버튼
        final JSONObject jsonObject = new JSONObject();
        cModifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                String oldC = intent.getExtras().getString("comment");

                //댓글 수정
                //json 변환
                String com = modifyComment.getText().toString();
                String result = com + oldC;

                try {
                    //memberID 추가
                    jsonObject.put("contents", com);
                    jsonObject.put("oldComment", oldC);
                } catch(JSONException e) {
                    e.printStackTrace();
                }

                commentModifyHttp hc = new commentModifyHttp(result);
                hc.execute();

                Context context = view.getContext();
                //수정 후 메시지
                Toast.makeText(context, "수정되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        //취소 버튼
        mCancleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Toast.makeText(getApplicationContext(), "댓글수정이 취소되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}