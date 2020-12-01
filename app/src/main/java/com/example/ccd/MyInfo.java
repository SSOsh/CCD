package com.example.ccd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.ccd.controller.myInfoHttp;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

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

        SharedPreferences sharedPreferences= getSharedPreferences("Value", MODE_PRIVATE);    // test 이름의 기본모드 설정, 만약 test key값이 있다면 해당 값을 불러옴.
        String id = sharedPreferences.getString("id","");
        String pw = sharedPreferences.getString("pw","");
        String name = sharedPreferences.getString("name","");
        String nickname = sharedPreferences.getString("nickname","");
        String address = sharedPreferences.getString("address","");

        final String old = id +"/" +  pw + "/" + name +"/"+ nickname +"/"+ address;

        memberIdEdit.setText(id);
        memberPwEdit.setText(pw);
        nameEdit.setText(name);
        nicknameEdit.setText(nickname);
        emailEdit.setText(address);

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

                myInfoHttp hc = new myInfoHttp(old + "/" + result);
                hc.execute();

                String res = null;
                try {
                    res = hc.get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(res.equals("success")) {
                    Toast.makeText(getApplicationContext(), "수정되었습니다.", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(), "수정실패! ㅜ", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //취소 버튼 클릭
        withdrawBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("Value", MODE_PRIVATE);    // test 이름의 기본모드 설정, 만약 test key값이 있다면 해당 값을 불러옴.
                String inputText = sharedPreferences.getString("id","");
                Toast.makeText(view.getContext(), inputText, Toast.LENGTH_SHORT).show();
                //finish();
            }
        });
    }
}