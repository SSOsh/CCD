package com.example.ccd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ccd.controller.signupHttp;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.IDN;
import java.util.concurrent.ExecutionException;


public class signup extends AppCompatActivity {
    EditText username, userid, userpassword, usernickname, useremail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        //아래처럼 선언 edittext

        username = (EditText)findViewById(R.id.Name);
        userid = (EditText)findViewById(R.id.ID);
        userpassword = (EditText)findViewById(R.id.Password);
        usernickname = (EditText)findViewById(R.id.Nickname);
        useremail = (EditText)findViewById(R.id.Email);

        Intent intent = getIntent();

        final Button backtologin = (Button)findViewById(R.id.backtologin);
        Button signup = (Button)findViewById(R.id.signup);


        backtologin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });

        final JSONObject jsonObject = new JSONObject();
        signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //json 변환
                String name = username.getText().toString();
                String userID = userid.getText().toString();
                String password = userpassword.getText().toString();
                String nickname = usernickname.getText().toString();
                String email = useremail.getText().toString();
                String result = name + "/" + userID + "/" + password + "/" + nickname + "/" + email;

                if(name.equals("") || userID.equals("") || password.equals("") || nickname.equals("") || email.equals("")) {
                    Toast.makeText(getApplicationContext(), "정보를 모두 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                signupHttp hc = new signupHttp(result);
                hc.execute();
                String ret = "";
                try {
                    ret = hc.get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(ret.equals("success")) {
                    Toast.makeText(getApplicationContext(), "등록되었습니다.", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(), "등록실패되었습니다.", Toast.LENGTH_SHORT).show();
                }
                
            }

        });



    }
}
