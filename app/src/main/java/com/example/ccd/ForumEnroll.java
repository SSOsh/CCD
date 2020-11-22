package com.example.ccd;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

public class ForumEnroll extends AppCompatActivity {
    TextView forumMem, forumDate;
    EditText forumTitle, forumText;
    Button fEnrollBtn, fEnrollCancleBtn;
    ForumFragment ff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_enroll);

        Intent intent = getIntent();

        ff = new ForumFragment();
        forumTitle = findViewById(R.id.forumTitle);
        forumText = findViewById(R.id.forumText);
        fEnrollBtn = findViewById(R.id.fEnrollBtn);
        fEnrollCancleBtn = findViewById(R.id.fEnrollCancleBtn);
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

                tText.setText(fTitle);
                tableRow.addView(tText);
                teText.setText(fText);
                tableRow.addView(teText);
                mText.setText(fMem);
                tableRow.addView(mText);
                dText.setText(fDate);
                tableRow.addView(dText);

                ff.forumTable.addView(tableRow);
                
                //json 변환
                String result = fTitle + "/" + fText;

                try {
                    jsonObject.put("fTitle", fTitle);
                    jsonObject.put("fText", fText);
                } catch(JSONException e) {
                    e.printStackTrace();
                }

                jsonObject.toString();
                postEnrollHttp hc = new postEnrollHttp(result);
                hc.execute();

                Toast.makeText(getApplicationContext(), "등록되었습니다.", Toast.LENGTH_SHORT).show();
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