package com.example.ccd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class forumDisplay extends AppCompatActivity {
    TextView tableId1, tableContent1, tableDate1;
    TableRow tableRow1;
    Button writeBtn;
    TableLayout forumTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_display);

//        //표 내용 넣기
//        forumTable = (TableLayout)findViewById(R.id.forumTable);
//        TableRow tableRow = new TableRow(this);
//        tableRow.setLayoutParams(new TableRow.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT));
//
//        //forum 새 데이터 넣기
//        for(int i=0 ; i<3 ; i++) {
//            TextView textView = new TextView(this);
//            textView.setText(String.valueOf(i));
//            textView.setGravity(Gravity.CENTER);
//            textView.setTextSize(15);
//            tableRow.addView(textView);
//        }
//        forumTable.addView(tableRow);

        tableId1 = findViewById(R.id.tableId1);
        tableDate1 = findViewById(R.id.tableDate1);
        tableContent1 = findViewById(R.id.tableContent1);

        //table 행 클릭 시 해당 게시글로 이동
        tableRow1 = (TableRow)findViewById(R.id.tableRow1);
        tableRow1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, forumContentLookup.class);
                intent.putExtra("tableId1", tableId1.toString());
                intent.putExtra("tableContent1", tableContent1.toString());
                intent.putExtra("tableDate1", tableDate1.toString());
                context.startActivity(intent);
            }
        });

        //게시물 등록 버튼
        writeBtn = findViewById(R.id.writeBtn);
        writeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str="";
                Context context = view.getContext();

                Intent intent = new Intent(context, forumEnroll.class);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
    }
}
