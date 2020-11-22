package com.example.ccd;

import androidx.appcompat.app.AppCompatActivity;
import com.example.ccd.*;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ccd.MainActivity;
import com.example.ccd.controller.loginHttp;
import com.example.ccd.signup;

import org.json.JSONException;
import org.json.JSONObject;

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
                    String st = null;
                    st = hc.get();
                    System.out.println(st);
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
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
