package com.example.ccd;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ccd.controller.postEnrollHttp;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class ForumEnroll extends AppCompatActivity {
    TextView forumMem, forumDate;
    EditText forumTitle, forumText;
    Button fEnrollBtn, fEnrollCancleBtn;
    ForumFragment ff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_enroll);

        SharedPreferences sharedPreferences= getSharedPreferences("Value", MODE_PRIVATE);    // test 이름의 기본모드 설정, 만약 test key값이 있다면 해당 값을 불러옴.
        String inputText = "";
        inputText = sharedPreferences.getString("id","");

        Intent intent = getIntent();

//        ff = new ForumFragment();
        //게시물제목
        forumTitle = findViewById(R.id.forumTitle);
        //게시물내용
        forumText = findViewById(R.id.forumText);
        //등록버튼
        fEnrollBtn = findViewById(R.id.fEnrollBtn);
        //취소버튼
        fEnrollCancleBtn = findViewById(R.id.fEnrollCancleBtn);

        forumMem = findViewById(R.id.forumMem);
        forumDate = findViewById(R.id.forumDate);
        forumMem.setText(inputText);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time = new Date();
        forumDate.setText(format.format(time));
        final JSONObject jsonObject = new JSONObject();

        final TableRow tableRow = new TableRow(getApplicationContext());
        tableRow.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        fEnrollBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //게시물 등록
                String fTitle = forumTitle.getText().toString();
                String fText = forumText.getText().toString();
                String fMem = forumMem.getText().toString();
                String fDate = forumDate.getText().toString();
                //table에 추가
                TextView tText = new TextView(getApplicationContext());
                TextView teText = new TextView(getApplicationContext());
                TextView mText = new TextView(getApplicationContext());
                TextView dText = new TextView(getApplicationContext());

//                //추가
//                tText.setText(fTitle);
//                tableRow.addView(tText);
//                teText.setText(fText);
//                tableRow.addView(teText);
//                mText.setText(fMem);
//                tableRow.addView(mText);
//                dText.setText(fDate);
//                tableRow.addView(dText);
//
//                System.out.println(ff.forumTable.getScaleX());
//                ff.forumTable.addView(tableRow);
                
                //json 변환
                String result = fMem + "/" + fTitle + "/" + fText;

                postEnrollHttp hc = new postEnrollHttp(result);
                hc.execute();
                try {
                    result = hc.get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(result);
                if(result.equals("success")) {
                    Toast.makeText(getApplicationContext(), "등록되었습니다.", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(), "등록실패입니다.", Toast.LENGTH_SHORT).show();
                }


                
            }
        });

        fEnrollCancleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder ad = new AlertDialog.Builder(ForumEnroll.this);
                ad.setTitle("취소 확인 메시지");
                ad.setMessage("게시물 작성을 취소하시겠습니까?");

                final EditText et = new EditText(ForumEnroll.this);
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

    }
}