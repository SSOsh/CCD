package com.example.ccd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class bookRead extends AppCompatActivity {
    RecyclerView mRecyclerView;
    bookReadWordListAdapter mAdapter;
    String titleR, authorR;
    int imgR;
    MainActivity mActivity;
    homeFragment hFragment;
    Button gostatusBook, goDoneBook;
    TextView bingTitle, bingAuthor;
    ImageButton homeBtn;
    ImageView bingImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_read);

        bingTitle = findViewById(R.id.bingTitle);
        bingAuthor = findViewById(R.id.bingAuthor);
        bingImg = findViewById(R.id.bingImg);
        mActivity = new MainActivity();
        hFragment = new homeFragment();
        homeBtn = findViewById(R.id.homeBtn);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mActivity.replaceFragment(hFragment);
            }
        });

        mRecyclerView = findViewById(R.id.bingRecyclerV);
        mAdapter = new bookReadWordListAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
//        titleR = intent.getExtras().getString("bspTitle");
//        bingTitle.setText(titleR);
//        authorR = intent.getExtras().getString("bspAuthor");
//        bingAuthor.setText(authorR);
//        imgR = Integer.parseInt(intent.getExtras().getString("bspImg"));
//        bingImg.setImageResource(imgR);

        gostatusBook = findViewById(R.id.gostatusBook);
        goDoneBook = findViewById(R.id.goDoneBook);

        gostatusBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rToS = new Intent(getApplicationContext(), bookStatus.class);
                view.getContext().startActivity(rToS);
            }
        });

        goDoneBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rToD = new Intent(getApplicationContext(), bookDone.class);
                view.getContext().startActivity(rToD);
            }
        });

        mAdapter.notifyDataSetChanged();

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
            bookReadData data = new bookReadData();
            data.setBingTitle(bsTitle.get(i));
            data.setBingAuthor(bsAuthor.get(i));
            data.setBingImg(bsImg.get(i));
            mAdapter.addItem(data);
        }
        mAdapter.notifyDataSetChanged();
    }
}