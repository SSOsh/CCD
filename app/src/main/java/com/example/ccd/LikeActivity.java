package com.example.ccd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.net.HttpURLConnection;

public class LikeActivity extends AppCompatActivity {
    Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like);

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