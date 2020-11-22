package com.example.ccd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ccd.controller.searchHttp;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class search extends AppCompatActivity {
    ArrayList<searchData> sDataList;
    private ListView listView;
    private searchWordListAdapter searchAdapter;
    private String[] arr = {"히가시노 게이고", "나미야"};
    TextView searchText;
    ImageButton backListBtn, searchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Intent sIntent = getIntent();

        searchText = findViewById(R.id.searchText);
        searchBtn = findViewById(R.id.searchBtn);
        final JSONObject jsonObject = new JSONObject();
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 검색 결과 호출
                //json 변환
                String sText = searchText.getText().toString();
                String result = sText;

                try {
                    jsonObject.put("sText", sText);
                } catch(JSONException e) {
                    e.printStackTrace();
                }

                searchHttp hc = new searchHttp(result);
                hc.execute();

                try {
                    String arr[] = hc.get().split("/");

                    Context context = view.getContext();
//                    Intent intentWeb = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"));
                    Intent intent = new Intent(view.getContext(), bookResult.class);
                    intent.putExtra("bookTitle", arr[0]);
                    intent.putExtra("author", arr[1]);
                    intent.putExtra("starRating", arr[2]);
                    intent.putExtra("bookcoverUrl", arr[3]);

                    context.startActivity(intent);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });

        backListBtn = findViewById(R.id.backListBtn);
        backListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        listView = (ListView)findViewById(R.id.historyList);
        searchAdapter = new searchWordListAdapter();
        listView.setAdapter(searchAdapter);

        for(int i=0 ; i<arr.length ; i++) {
            searchAdapter.addItem(this.arr[i]);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                //검색 내역 클릭 시 해당 검색 내역에 해당하는 도서 출력
            }
        });
    }

//    public void InitializeHistoryText() {
//        sDataList = new ArrayList<searchData>();
//        sDataList.add(new searchData("나미야"));
//        sDataList.add(new searchData("히가시노 게이고"));
//    }
}}