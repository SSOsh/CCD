package com.example.ccd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class bookResult extends AppCompatActivity {
    ArrayList<resultData> rDataList;
    Button bookListBtn;
    MainActivity mactivity;
    bookLookupFragment blFragment;
    private ListView resultList;
    private resultWordListAdapter resultWordListAdapter;
    private int[] img = {R.drawable.namiya};
    private String[] title = {"나미야 잡화점의 기적"};
    private String[] author = {"히가시노 게이고"};
    String resultS;
    TextView searchResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_result);

//        this.InitializeResult();

        resultList = (ListView)findViewById(R.id.resultList);
        searchResult = (TextView) findViewById(R.id.searchResult);

        Intent intent = getIntent();
        resultS = intent.getStringExtra("searchText");
        searchResult.setText(resultS);

        resultWordListAdapter = new resultWordListAdapter();
        resultList.setAdapter(resultWordListAdapter);

        bookListBtn = (Button)findViewById(R.id.bookListBtn);
        mactivity = new MainActivity();
        blFragment = new bookLookupFragment();

        for(int i=0 ; i<img.length ; i++) {
            resultWordListAdapter.addItem(this.img[i], title[i], author[i]);
        }

        bookListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mactivity.replaceFragment(blFragment);
            }
        });
    }

//    public void InitializeResult() {
//        rDataList = new ArrayList<resultData>();
//        rDataList.add(new resultData("나미야 잡화점의 기적", "히가시노 게이고", R.drawable.namiya));
//    }
}