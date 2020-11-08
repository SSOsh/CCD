package com.example.ccd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.Arrays;
import java.util.List;

public class book_done_lookup extends AppCompatActivity {
    RecyclerView mRecyclerView;
    bDoneWordListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_done_lookup);

        mRecyclerView = findViewById(R.id.bdoneRecyclerV);
        mAdapter = new bDoneWordListAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        initData();

        Intent intent = getIntent();
    }

    void initData() {
        List<String> bdTitleList = Arrays.asList(
                "기린의 날개"
        );
        List<String> bdAuthorList = Arrays.asList(
                "히가시노 게이고"
        );
        List<Integer> bdImgList = Arrays.asList(
                R.drawable.giraffe
        );

        for(int i=0; i<bdTitleList.size(); i++) {
            bDoneData data = new bDoneData();
            data.setBdoneTitle(bdTitleList.get(i));
            data.setBdoneAuthor(bdAuthorList.get(i));
            data.setBdoneImg(bdImgList.get(i));
            mAdapter.addItem(data);
        }
        mAdapter.notifyDataSetChanged();
    }
}
