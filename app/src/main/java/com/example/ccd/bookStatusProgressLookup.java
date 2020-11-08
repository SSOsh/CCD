package com.example.ccd;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class bookStatusProgressLookup extends AppCompatActivity {
    RecyclerView mRecyclerView;
    bspWordListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_status_progress_lookup);

        mRecyclerView = findViewById(R.id.bspRecyclerV);
        mAdapter = new bspWordListAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        initData();
    }

    void initData() {
        List<String> bspTitleList = Arrays.asList(
                "나미야 잡화점의 기적"
        );
        List<String> bspAuthorList = Arrays.asList(
                "히가시노 게이고"
        );
        List<Integer> bspImgList = Arrays.asList(
                R.drawable.namiya
        );

        for(int i=0; i<bspTitleList.size(); i++) {
            bookStatusData data = new bookStatusData();
            data.setBspTitle(bspTitleList.get(i));
            data.setBspAuthor(bspAuthorList.get(i));
            data.setBspImg(bspImgList.get(i));
            mAdapter.addItem(data);
        }
        mAdapter.notifyDataSetChanged();
    }
}
