package com.example.ccd;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.ccd.controller.postDeleteHttp;
import com.example.ccd.controller.postModifyHttp;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

public class ForumContentLookup extends AppCompatActivity {
    TextView forumNickname, forumDate, forumTitleText, forumContentText;
    Button forumListBtn, commentEnrollBtn, forumModifyBtn, forumDeleteBtn;
    RecyclerView mRecyclerView;
    commentWordListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_content_lookup);

        forumNickname = findViewById(R.id.forumNickname);
        forumDate = findViewById(R.id.forumDate);
        forumTitleText = findViewById(R.id.forumTitleText);
        forumContentText = findViewById(R.id.forumContentText);
        forumListBtn = findViewById(R.id.forumListBtn);
        commentEnrollBtn = findViewById(R.id.commentEnrollBtn);
        forumModifyBtn = findViewById(R.id.forumModifyBtn);
        forumDeleteBtn = findViewById(R.id.forumDeleteBtn);

        Intent intent = getIntent();

        final JSONObject jsonObject = new JSONObject();
        //수정버튼
        forumModifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //userID 확인
                //동일하면 수정 아니면 메시지
                //json 변환
                String titleText = forumTitleText.getText().toString();
                String content = forumContentText.getText().toString();
                String result = titleText + "/" + content;

                try {
                    jsonObject.put("titleText", titleText);
                    jsonObject.put("content", content);
                } catch(JSONException e) {
                    e.printStackTrace();
                }

                postModifyHttp hc = new postModifyHttp(result);
                hc.execute();
            }
        });

        final JSONObject jso = new JSONObject();
        //삭제버튼
        forumDeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //닉네임 동일한지 확인
                //동일하면 삭제 아니면 메시지
                AlertDialog.Builder ad = new AlertDialog.Builder(ForumContentLookup.this);
                ad.setTitle("삭제 확인 메시지");
                ad.setMessage("게시물을 삭제하시겠습니까?");

                final EditText et = new EditText(ForumContentLookup.this);
                ad.setView(et);

                ad.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        //json 변환
                        //memberID 추가
                        String titleText = forumTitleText.getText().toString();
                        String result = titleText;

                        try {
                            jso.put("titleText", titleText);
                        } catch(JSONException e) {
                            e.printStackTrace();
                        }

                        postDeleteHttp hc = new postDeleteHttp(result);
                        hc.execute();

//                        String result = et.getText().toString();
                        dialog.dismiss();
                        finish();
                    }
                });

                ad.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                });
                ad.show();
            }
        });

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