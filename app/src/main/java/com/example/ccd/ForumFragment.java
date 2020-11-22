package com.example.ccd;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.ccd.controller.forumRowHttp;

import org.json.JSONException;
import org.json.JSONObject;

public class ForumFragment extends Fragment {
    TextView tableId1, tableContent1, tableDate1, tv_result;
    TableRow tableRow1, tableRow2;
    Button writeBtn;
    TableLayout forumTable;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = (View)inflater.inflate(R.layout.fragment_forum, container, false);

        //게시물 등록 버튼
        writeBtn = (Button)view.findViewById(R.id.writeBtn);
        writeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ForumEnroll으로 이동
                Intent intent = new Intent(getActivity(), ForumEnroll.class);
                startActivity(intent);
            }
        });

        //tableRow 클릭 시 해당 게시물로 이동
        forumTable = (TableLayout)view.findViewById(R.id.forumTable);
        tableId1 = (TextView)view.findViewById(R.id.tableId1);
        tableContent1 = (TextView)view.findViewById(R.id.tableContent1);
        tableDate1 = (TextView)view.findViewById(R.id.tableDate1);

        tableRow2 = (TableRow)view.findViewById(R.id.tableRow2);
        final JSONObject jsonObject = new JSONObject();
        tableRow2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //게시물로 이동
                //디비 연결 시에는 작성자와 내용? 등 동일한 게시물과 연결
                //json 변환
                String tId = tableId1.getText().toString();
                String tContent = tableContent1.getText().toString();
                String tDate = tableDate1.getText().toString();
                String result = tId + "/" + tContent + "/" + tDate;

                try {
                    jsonObject.put("tId", tId);
                    jsonObject.put("tContent", tContent);
                    jsonObject.put("tDate", tDate);
                } catch(JSONException e) {
                    e.printStackTrace();
                }

                jsonObject.toString();
                forumRowHttp hc = new forumRowHttp(result);
                hc.execute();

                Intent contentIntent = new Intent(getActivity(), ForumContentLookup.class);
                startActivity(contentIntent);
            }
        });

        return view;
    }
}