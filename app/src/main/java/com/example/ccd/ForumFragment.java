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
import android.widget.Toast;

import com.example.ccd.controller.forumRowHttp;
import com.example.ccd.controller.postLookupHttp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class ForumFragment extends Fragment {
    TextView tableId1, tableContent1, tableDate1;
    TableRow tableRow2;
    Button writeBtn;
    TableLayout forumTable;
    TextView t1,t2,t3, tt;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = (View)inflater.inflate(R.layout.fragment_forum, container, false);


        forumTable = (TableLayout)view.findViewById(R.id.forumTable);

        //값넣기
        postLookupHttp pl = new postLookupHttp();
        pl.execute();

        try {
            JSONObject object = new JSONObject(pl.get());
            JSONArray jarr = object.getJSONArray("postLookup");
            int leng = jarr.length();
            for(int i=0;i < leng;i++) {
                JSONObject tmp = (JSONObject)jarr.get(i);
                String memberID = (String)tmp.get("memberID");
                final String title = (String)tmp.get("title");
                final String content = (String)tmp.get("content");
                String date = tmp.get("date").toString().substring(0,10);

                t1 = new TextView(view.getContext());
                t2 = new TextView(view.getContext());
                t3 = new TextView(view.getContext());

                t1.setText(memberID);
                t2.setText(title);
                t3.setText(date);

                System.out.println(memberID + title + date);
                TableRow trow = new TableRow(view.getContext());
                trow.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                trow.addView(t1);
                trow.addView(t2);
                trow.addView(t3);
                forumTable.addView(trow);
                trow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TableRow tablerow = (TableRow) view;
                        String memberID=((TextView) tablerow.getChildAt(0)).getText().toString();
                        String title=((TextView) tablerow.getChildAt(1)).getText().toString();
                        String date=((TextView) tablerow.getChildAt(2)).getText().toString();

                        Intent intent = new Intent(getActivity(), ForumContentLookup.class);
                        intent.putExtra("memberID", memberID);
                        intent.putExtra("title", title);

                        intent.putExtra("date", date);
                        startActivity(intent);
                    }
                });
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


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

        return view;
    }
}