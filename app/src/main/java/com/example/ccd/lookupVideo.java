package com.example.ccd;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class lookupVideo extends AppCompatActivity {
    TextView videoTitleText, videoContentText;
    VideoView videoView;
    Button viedoBookBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lookup_video);

        videoTitleText = findViewById(R.id.videoTitleText);
        videoContentText = findViewById(R.id.videoContentText);
        videoView = findViewById(R.id.videoView);
        viedoBookBtn = findViewById(R.id.viedoBookBtn);

        Intent intent = getIntent();

        viedoBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //해당 도서로 이동(intent)
                Intent intent = new Intent(view.getContext(), bookInformation.class);
                intent.putExtra("videoTitleText", videoTitleText.toString());
                startActivity(intent);
            }
        });

        //비디오 url
        Uri videoUri = Uri.parse("https://www.youtube.com/watch?v=YcOMjfAstYM");
        //비디오뷰 재생, 일시정지 기능
        videoView.setMediaController(new MediaController(this));
        //비디오뷰 경로 설정
        videoView.setVideoURI(videoUri);
        //로딩 준비 후 실행 될 리스너 설정
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                videoView.start();
            }
        });
    }

    //화면에 안보일 때
    @Override
    protected  void onPause() {
        super.onPause();

        //비디오 일시정지
        if(videoView!=null && videoView.isPlaying()) videoView.pause();
    }

    //액티비티가 메모리에서 사라질 때
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //
        if(videoView!=null) videoView.stopPlayback();
    }
}
