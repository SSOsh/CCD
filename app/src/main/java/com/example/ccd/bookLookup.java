package com.example.ccd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class bookLookup extends AppCompatActivity {
    EditText titleEdit, authorEdit;
    Button searchButton;
    RecyclerView mRecyclerView;
    WordListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_lookup);

        titleEdit = findViewById(R.id.titleEdit);
        authorEdit = findViewById(R.id.authorEdit);
        searchButton = findViewById(R.id.searchButton);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        mRecyclerView = findViewById(R.id.recyclerV);
        mAdapter = new WordListAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        initData();
    }

    void initData() {
        List<String> listTitle = Arrays.asList(
            "나미야 잡화점의 기적", "제3인류", "기린의 날개"
        );
        List<String> listAuthor = Arrays.asList(
            "히가시노 게이고", "베르나르 베르베르", "히가시노 게이고"
        );
        List<Integer> listImg = Arrays.asList(
            R.drawable.namiya, R.drawable.third, R.drawable.giraffe
        );
        List<String> listStar = Arrays.asList(
                "3", "3", "4"
        );

        for(int i=0; i<listTitle.size(); i++) {
            Data data = new Data();
            data.setBookTitle(listTitle.get(i));
            data.setAuthor(listAuthor.get(i));
            data.setBookCoverImg(listImg.get(i));
            data.setStarRating(listStar.get(i));
            mAdapter.addItem(data);
    }
        mAdapter.notifyDataSetChanged();
    }
}
