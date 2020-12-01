package com.example.ccd;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ccd.controller.loginHttp;

import java.util.concurrent.ExecutionException;


public class login extends AppCompatActivity {
    Button addinfo, nonlogin;
    EditText id, pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        id = (EditText) findViewById(R.id.id);
        pw = (EditText) findViewById(R.id.pw);
        addinfo = (Button) findViewById(R.id.addinfo);
        addinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), signup.class);
                startActivity(intent);
            }
        });

        //로그인 버튼
        Button login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //json 변환
                String ID = id.getText().toString();
                String PW = pw.getText().toString();
                String result = ID + "/" + PW;

                loginHttp hc = new loginHttp(result, getApplicationContext());
                hc.execute();
                try {
                    String[] st = null;
                    st = hc.get().split("/");
                    if(st[5].equals("success")) {
                        SharedPreferences sharedPreferences = getSharedPreferences("Value", MODE_PRIVATE);    // test 이름의 기본모드 설정
                        SharedPreferences.Editor editor= sharedPreferences.edit(); //sharedPreferences를 제어할 editor를 선언
                        editor.putString("id", st[0]); // key,value 형식으로 저장
                        editor.putString("pw", st[1]); // key,value 형식으로 저장
                        editor.putString("name", st[2]); // key,value 형식으로 저장
                        editor.putString("address", st[3]); // key,value 형식으로 저장
                        editor.putString("nickname", st[4]); // key,value 형식으로 저장
                        editor.commit();    //최종 커밋. 커밋을 해야 저장이 된다.

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(login.this, "로그인 실패하셨습니다 ㅜ", Toast.LENGTH_SHORT).show();
                    }

                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        nonlogin = (Button) findViewById(R.id.nonlogin);
        nonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("Value", MODE_PRIVATE);    // test 이름의 기본모드 설정
                SharedPreferences.Editor editor= sharedPreferences.edit(); //sharedPreferences를 제어할 editor를 선언
                editor.clear();
                editor.commit();    //최종 커밋. 커밋을 해야 저장이 된다.

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
