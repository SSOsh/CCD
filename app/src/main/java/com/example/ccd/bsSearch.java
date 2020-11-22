package com.example.ccd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ccd.controller.bsSearchHttp;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class bsSearch extends AppCompatActivity {
    ArrayList<bsSearchData> bsDataList;
    private ListView listView;
    private bsSearchWordListAdapter bsSearchAdapter;
    private String[] arr = {"히가시노 게이고", "나미야"};
    TextView bsSearchText;
    ImageButton backBsBtn, bsSearchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bs_search);

        Intent bIntent = getIntent();

        bsSearchText = findViewById(R.id.bsSearchText);
        backBsBtn = findViewById(R.id.backBsBtn);
        bsSearchBtn = findViewById(R.id.bsSearchBtn);
        final JSONObject jsonObject = new JSONObject();
        bsSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //검색 내용
                //json 변환
                String text = bsSearchText.getText().toString();
                String result = text;

                try {
                    jsonObject.put("text", text);
                } catch(JSONException e) {
                    e.printStackTrace();
                }

                jsonObject.toString();
                bsSearchHttp hc = new bsSearchHttp(result);
                hc.execute();

                //검색 결과 호출
                Intent intent = new Intent(bsSearch.this, bsResult.class);
                intent.putExtra("bsSearchText",bsSearchText.getText().toString());
                startActivity(intent);
            }
        });

        backBsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        listView = (ListView)findViewById(R.id.bsHistoryList);
        bsSearchAdapter = new bsSearchWordListAdapter();
        listView.setAdapter(bsSearchAdapter);

        for(int i=0 ; i<arr.length ; i++) {
            bsSearchAdapter.addItem(this.arr[i]);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                //검색 내역 클릭 시 해당 검색 내역에 해당하는 도서 출력
            }
        });
    }
}