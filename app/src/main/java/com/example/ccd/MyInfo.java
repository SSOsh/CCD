package com.example.ccd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.ccd.controller.myInfoHttp;

import org.json.JSONException;
import org.json.JSONObject;

public class MyInfo extends AppCompatActivity {
    EditText nameEdit, emailEdit, memberPwEdit, nicknameEdit, memberIdEdit;
    Button modifyBtn, withdrawBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);

        nameEdit = findViewById(R.id.nameEdit);
        emailEdit = findViewById(R.id.emailEdit);
        memberPwEdit = findViewById(R.id.memberPwEdit);
        nicknameEdit = findViewById(R.id.nicknameEdit);
        memberIdEdit = findViewById(R.id.memberIdEdit);
        modifyBtn = findViewById(R.id.modifyBtn);
        withdrawBtn = findViewById(R.id.withdrawBtn);

        Intent intent = getIntent();

        final JSONObject jsonObject = new JSONObject();
        //수정 버튼 클릭
        modifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //json 변환
                String name = nameEdit.getText().toString();
                String email = emailEdit.getText().toString();
                String pw = memberPwEdit.getText().toString();
                String nickname = nicknameEdit.getText().toString();
                String id = memberIdEdit.getText().toString();
                String result = id + "/" + pw + "/" + name + "/" + nickname + "/" + email;

                try {
                    jsonObject.put("name", name);
                    jsonObject.put("email", email);
                    jsonObject.put("pw", pw);
                    jsonObject.put("nickname", nickname);
                    jsonObject.put("id", id);
                } catch(JSONException e) {
                    e.printStackTrace();
                }

                jsonObject.toString();
                myInfoHttp hc = new myInfoHttp(result);
                hc.execute();

                Toast.makeText(getApplicationContext(), "수정되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });
        
        //취소 버튼 클릭
        withdrawBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}