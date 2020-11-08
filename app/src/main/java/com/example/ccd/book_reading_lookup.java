package com.example.ccd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class book_reading_lookup extends AppCompatActivity {
    RecyclerView mRecyclerView;
    bReadingWordListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_reading_lookup);

        mRecyclerView = findViewById(R.id.bingRecyclerV);
        mAdapter = new bReadingWordListAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        initData();

        Intent intent = getIntent();
        String brTitleV = intent.getStringExtra("bspTitle");
        String brAuthorV = intent.getStringExtra("bspAuthor");
        String brImgV = intent.getStringExtra("bspImg");

//        bReadingData briData = new bReadingData(brTitleV, brAuthorV, brImgV);
//        mAdapter.addItem(briData);
//        mAdapter.notifyDataSetChanged();
    }

    void initData() {
        List<String> brTitleList = Arrays.asList(
                "제3인류"
        );
        List<String> brAuthorList = Arrays.asList(
                "베르나르 베르베르"
        );
        List<Integer> brImgList = Arrays.asList(
                R.drawable.third
        );

        for(int i=0; i<brTitleList.size(); i++) {
            bReadingData data = new bReadingData();
            data.setBingTitle(brTitleList.get(i));
            data.setBingAuthor(brAuthorList.get(i));
            data.setBingImg(brImgList.get(i));
            mAdapter.addItem(data);
        }
        mAdapter.notifyDataSetChanged();
    }
}
