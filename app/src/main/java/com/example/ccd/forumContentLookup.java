package com.example.ccd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class forumContentLookup extends AppCompatActivity {
    TextView forumNickname, forumDate, forumTitleText, forumContentText;
    Button forumListBtn, commentEnrollBtn;
    RecyclerView mRecyclerView;
    commentWordListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forum_content_lookup);

        forumNickname = findViewById(R.id.forumNickname);
        forumDate = findViewById(R.id.forumDate);
        forumTitleText = findViewById(R.id.forumTitleText);
        forumContentText = findViewById(R.id.forumContentText);
        forumListBtn = findViewById(R.id.forumListBtn);
        commentEnrollBtn = findViewById(R.id.commentEnrollBtn);

        Intent intent = getIntent();
        String nickname = intent.getStringExtra("tableId1");
        forumNickname.setText(nickname);
        String date = intent.getStringExtra("tableDate1");
        forumDate.setText(date);
        String content = intent.getStringExtra("tableContent1");
        forumContentText.setText(content);

        forumListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //목록으로 돌아감
                finish();
            }
        });

        commentEnrollBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //댓글 등록 팝업
                Intent intent = new Intent(getApplicationContext(), commentEnrollPopup.class);
                startActivity(intent);
            }
        });

        mRecyclerView = findViewById(R.id.comment_recycler);
        mAdapter = new commentWordListAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        initData();
    }

    void initData() {
        List<String> commentIdList = Arrays.asList(
                "주희"
        );
        List<String> commentList = Arrays.asList(
                "댓글 내용입니다."
        );

        for(int i=0; i<commentIdList.size(); i++) {
            commentData data = new commentData();
            data.setCommentId(commentIdList.get(i));
            data.setComment(commentList.get(i));
            mAdapter.addItem(data);
        }
        mAdapter.notifyDataSetChanged();
    }
}
