package com.example.ccd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class bsResult extends AppCompatActivity {
    ArrayList<bsResultData> bDataList;
    Button bsListBtn;
    MainActivity mactivity;
    bestsellerLookupFragment bsFragment;
    private ListView bsResultList;
    private bsResultWordListAdapter bsResultAdapter;
    private int[] img = {R.drawable.namiya};
    private String[] title = {"나미야 잡화점의 기적"};
    private String[] author = {"히가시노 게이고"};
    String resultB;
    TextView bsSearchResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bs_result);

        bsResultList = (ListView)findViewById(R.id.bsResultList);
        bsSearchResult = (TextView)findViewById(R.id.bsSearchResult);

        Intent intent = getIntent();
        resultB = intent.getStringExtra("bsSearchText");
        bsSearchResult.setText(resultB);

        bsResultAdapter = new bsResultWordListAdapter();
        bsResultList.setAdapter(bsResultAdapter);

        bsListBtn = (Button)findViewById(R.id.bsListBtn);
        mactivity = new MainActivity();
        bsFragment = new bestsellerLookupFragment();

        for(int i=0 ; i<img.length ; i++) {
            bsResultAdapter.addItem(this.img[i], title[i], author[i]);
        }

        bsListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //베스트셀러 목록으로 이동
                mactivity.replaceFragment(bsFragment);
            }
        });
    }
}