package com.example.ccd;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class forumModify extends AppCompatActivity {
    Button forumModifyBtn, forumDeleteBtn, fListBtn, fmCancleBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forum_modify);

        forumModifyBtn = findViewById(R.id.forumModifyBtn);
        forumDeleteBtn = findViewById(R.id.forumDeleteBtn);
        fmCancleBtn = findViewById(R.id.fmCancleBtn);
        fListBtn = findViewById(R.id.fListBtn);

        forumModifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //게시물 수정
                Toast.makeText(getApplicationContext(), "수정되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        forumDeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //게시물 삭제
                AlertDialog.Builder ad = new AlertDialog.Builder(forumModify.this);
                ad.setTitle("삭제 확인 메시지");
                ad.setMessage("게시물을 삭제하시겠습니까?");

                final EditText et = new EditText(forumModify.this);
                ad.setView(et);

                ad.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        Toast.makeText(getApplicationContext(), "삭제되었습니다.", Toast.LENGTH_SHORT).show();
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

        fmCancleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        fListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder atd = new AlertDialog.Builder(forumModify.this);
                atd.setTitle("목록으로");
                atd.setMessage("수정을 취소하고 목록으로 돌아가시겠습니까?");

                final EditText edit = new EditText(forumModify.this);
                atd.setView(edit);

                atd.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                        finish();
                    }
                });

                atd.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                });
                atd.show();
            }
        });
    }
}
