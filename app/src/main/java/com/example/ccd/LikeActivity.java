package com.example.ccd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.List;

public class LikeActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    likeWordListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like);

        mRecyclerView = findViewById(R.id.recyclerView2);
        mAdapter = new likeWordListAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        initData();
    }

    void initData() {
        List<String> listName = Arrays.asList(
                "나미야 잡화점의 기적", "제3인류", "기린의 날개"
        );
        List<String> listWriter = Arrays.asList(
                "히가시노 게이고", "베르나르 베르베르", "히가시노 게이고"
        );
        List<Integer> listImg = Arrays.asList(
                R.drawable.namiya, R.drawable.third, R.drawable.giraffe
        );

        for(int i=0; i<listName.size(); i++) {
            likeData data = new likeData();
            data.setBookName(listName.get(i));
            data.setWriter(listWriter.get(i));
            data.setBook_image(listImg.get(i));
            mAdapter.addItem(data);
        }
        mAdapter.notifyDataSetChanged();
    }

    //  이 친구 recyclerview설정하고 각 좋아요마다 이미지 변경 예시임
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        final ToggleButton tb2 =
//                (ToggleButton) this.findViewById(R.id.toggleButton2);
//
//        tb2.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                if(tb2.isChecked()){
//                    tb2.setBackgroundDrawable(
//                            getResources().
//                                    getDrawable(R.drawable.angry_birds_yellow_button_by_vyndo_d3hc3yl)
//                    );
//                }else{
//                    tb2.setBackgroundDrawable(
//                            getResources().
//                                    getDrawable(R.drawable.angry_birds_red_button_by_vyndo_d3hc3t4)
//                    );
//                } // end if
//            } // end onClick()
//        });
//
//
//        출처: https://bitsoul.tistory.com/37 [Happy Programmer~]
}