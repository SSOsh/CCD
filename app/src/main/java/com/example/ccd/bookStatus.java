package com.example.ccd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.Arrays;
import java.util.List;

public class bookStatus extends AppCompatActivity {
    RecyclerView mRecyclerView;
    bookStatusWordListAdapter mAdapter;
    Button goReadBook, goDoneBook, enrollStatusBtn;
    ImageButton homeBtn;
    MainActivity mActivity;
    homeFragment hFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_status);

        Intent intent = getIntent();

        goReadBook = findViewById(R.id.goReadBook);
        goDoneBook = findViewById(R.id.goDoneBook);
        enrollStatusBtn = findViewById(R.id.enrollStatusBtn);
        mRecyclerView = findViewById(R.id.bspRecyclerV);
        mAdapter = new bookStatusWordListAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mActivity = new MainActivity();
        hFragment = new homeFragment();
        homeBtn = findViewById(R.id.homeBtn);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mActivity.replaceFragment(hFragment);
            }
        });

        goReadBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sToR = new Intent(getApplicationContext(), bookRead.class);
                view.getContext().startActivity(sToR);
            }
        });

        goDoneBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sToD = new Intent(getApplicationContext(), bookDone.class);
                view.getContext().startActivity(sToD);
            }
        });

        enrollStatusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent eIntent = new Intent(getApplicationContext(), bookStatusEnroll.class);
                view.getContext().startActivity(eIntent);
            }
        });

        initData();
    }

    void initData() {
        List<String> bsTitle = Arrays.asList(
                "나미야 잡화점의 기적"
        );
        List<String> bsAuthor = Arrays.asList(
                "히가시노 게이고"
        );
        List<Integer> bsImg = Arrays.asList(
                R.drawable.namiya
        );

        for(int i=0; i<bsTitle.size(); i++) {
            bookStatusData data = new bookStatusData();
            data.setBspTitle(bsTitle.get(i));
            data.setBspAuthor(bsAuthor.get(i));
            data.setBspImg(bsImg.get(i));
            mAdapter.addItem(data);
        }
        mAdapter.notifyDataSetChanged();
    }
}