package com.example.ccd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MyInfoManagement extends AppCompatActivity {
    EditText nameEdit, memberIdEdit, memberPwEdit, nicknameEdit, emailEdit;
    Button modifyBtn, withdrawBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info_management);

        Intent intent = getIntent();

        nameEdit = findViewById(R.id.nameEdit);
        memberIdEdit = findViewById(R.id.memberIdEdit);
        memberPwEdit = findViewById(R.id.memberPwEdit);
        nicknameEdit = findViewById(R.id.nicknameEdit);
        emailEdit = findViewById(R.id.emailEdit);
        modifyBtn = findViewById(R.id.modifyBtn);
        withdrawBtn = findViewById(R.id.withdrawBtn);

        modifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "수정되었습니다.", Toast.LENGTH_LONG).show();
            }
        });

        withdrawBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "취소되었습니다.", Toast.LENGTH_LONG).show();
            }
        });
    }
}
