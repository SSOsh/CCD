package com.example.ccd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


import com.example.ccd.controller.noticeRowHttp;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class notice extends AppCompatActivity {
    TextView ntableId1, ntableContent1, ntableDate1;
    TableRow ntableRow1, ntableRow2;
    ImageButton backBtn;
    TableLayout noticeTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //tableRow 클릭 시 해당 게시물로 이동
        final JSONObject jsonObject = new JSONObject();
        ntableId1 = (TextView)findViewById(R.id.ntableId1);
        ntableContent1 = (TextView)findViewById(R.id.ntableContent1);
        ntableDate1 = (TextView) findViewById(R.id.ntableDate1);
        ntableRow2 = (TableRow)findViewById(R.id.ntableRow2);

        ntableRow2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //게시물로 이동
                //디비 연결 시에는 작성자와 내용? 등 동일한 게시물과 연결
                //json 변환
                String tId = ntableId1.getText().toString();
                String tContent = ntableContent1.getText().toString();
                String tDate = ntableDate1.getText().toString();
                String result = tId + "/" + tContent + "/" + tDate;

                try {
                    jsonObject.put("tId", tId);
                    jsonObject.put("tContent", tContent);
                    jsonObject.put("tDate", tDate);
                } catch(JSONException e) {
                    e.printStackTrace();
                }

                noticeRowHttp hc = new noticeRowHttp(result);
                hc.execute();

                Intent contentIntent = new Intent(getApplicationContext(), NoticeContentLookup.class);
                startActivity(contentIntent);
            }
        });
    }
}