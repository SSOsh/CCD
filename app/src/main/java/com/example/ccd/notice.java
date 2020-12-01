package com.example.ccd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


import com.example.ccd.controller.noticeRowHttp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.concurrent.ExecutionException;

public class notice extends AppCompatActivity {
    TextView ntableId1, ntableContent1, ntableDate1;
    TableRow ntableRow1, ntableRow2;
    ImageButton backBtn;
    TableLayout noticeTable;
    TextView t1,t2,t3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        noticeTable = findViewById(R.id.noticeTable);

        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        noticeRowHttp nr = new noticeRowHttp();
        nr.execute();
        try {
            JSONObject object = new JSONObject(nr.get());
            JSONArray jarr = object.getJSONArray("noticeLookup");
            int leng = jarr.length();
            for(int i=0;i < leng;i++) {
                JSONObject tmp = (JSONObject)jarr.get(i);
                final String title = (String)tmp.get("title");
                final String content = (String)tmp.get("content");
                String date = tmp.get("date").toString().substring(0,10);

                t2 = new TextView(this);
                t3 = new TextView(this);

                t2.setText(title);
                t3.setText(date);

                TableRow trow = new TableRow(this);
                trow.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                trow.addView(t2);
                trow.addView(t3);
                noticeTable.addView(trow);
                trow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TableRow tablerow = (TableRow) view;
                        String contents=((TextView) tablerow.getChildAt(0)).getText().toString();
                        String date=((TextView) tablerow.getChildAt(1)).getText().toString();

                        Intent intent = new Intent(getApplicationContext(), NoticeContentLookup.class);
                        intent.putExtra("title", title);
                        intent.putExtra("content", content);
                        intent.putExtra("date", date);
                        startActivity(intent);
                    }
                });
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}