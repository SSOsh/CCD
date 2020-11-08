package com.example.ccd;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class forumEnroll extends AppCompatActivity {
    Button fEnrollBtn, fEnrollCancleBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_enroll);

        fEnrollBtn = findViewById(R.id.fEnrollBtn);
        fEnrollCancleBtn = findViewById(R.id.fEnrollCancleBtn);

        fEnrollBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "등록되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        fEnrollCancleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder ad = new AlertDialog.Builder(forumEnroll.this);
                ad.setTitle("취소 확인 메시지");
                ad.setMessage("게시물 작성을 취소하시겠습니까?");

                final EditText et = new EditText(forumEnroll.this);
                ad.setView(et);

                ad.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
//                        String result = et.getText().toString();
                        dialog.dismiss();
                        finish();
                    }
                });

                ad.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                });
                ad.show();
            }
        });

        Intent intent=new Intent(this.getIntent());
//        intent.getAction();
    }

}
