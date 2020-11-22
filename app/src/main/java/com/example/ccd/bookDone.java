package com.example.ccd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class bookDone extends AppCompatActivity {
    RecyclerView mRecyclerView;
    bookDoneWordListAdapter mAdapter;
    TextView bdoneTitle, bdoneAuthor;
    ImageView bdoneImg;
    Button gostatusBook, goReadBook;
    String titleD, authorD;
    int imgD;
    ImageButton homeBtn;
    MainActivity mActivity;
    homeFragment hFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_done);

        bdoneTitle = findViewById(R.id.bdoneTitle);
        bdoneAuthor = findViewById(R.id.bdoneAuthor);
        bdoneImg = findViewById(R.id.bdoneImg);
        mActivity = new MainActivity();
        hFragment = new homeFragment();
        homeBtn = findViewById(R.id.homeBtn);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mActivity.replaceFragment(hFragment);
            }
        });

        mRecyclerView = findViewById(R.id.bdoneRecyclerV);
        mAdapter = new bookDoneWordListAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
//        titleD = intent.getExtras().getString("bingTitle");
//        bdoneTitle.setText(titleD);
//        authorD = intent.getExtras().getString("bingAuthor");
//        bdoneAuthor.setText(authorD);
//        imgD = Integer.parseInt(intent.getExtras().getString("bingImg"));
//        bdoneImg.setImageResource(imgD);

        gostatusBook = findViewById(R.id.gostatusBook);
        goReadBook = findViewById(R.id.goReadBook);

        gostatusBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dToS = new Intent(getApplicationContext(), bookStatus.class);
                view.getContext().startActivity(dToS);
            }
        });

        goReadBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dToR = new Intent(getApplicationContext(), bookRead.class);
                view.getContext().startActivity(dToR);
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
            bookDoneData data = new bookDoneData();
            data.setBdoneTitle(bsTitle.get(i));
            data.setBdoneAuthor(bsAuthor.get(i));
            data.setBdoneImg(bsImg.get(i));
            mAdapter.addItem(data);
        }
        mAdapter.notifyDataSetChanged();
    }
}