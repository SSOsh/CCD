package com.example.ccd;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ccd.controller.homeHttp;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class homeWordListAdapter extends RecyclerView.Adapter<homeWordListAdapter.homeWordViewHolder> {
    private ArrayList<homeData> hData = new ArrayList<>();
    LayoutInflater mInflater;

    public homeWordListAdapter(Context context) {mInflater = LayoutInflater.from(context);}

    @NonNull
    @Override
    public homeWordViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View mItemView = mInflater.inflate(R.layout.video_recycler, viewGroup, false);
        return new homeWordViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull homeWordViewHolder holder, int position){
        holder.onBind(hData.get(position));

        holder.videoTitleText.setText(hData.get(position).getVideoTitleText());
        holder.videoContentText.setText(hData.get(position).getVideoContentText());
        holder.videoView.setVideoURI(Uri.parse(hData.get(position).getVideoView()));
    }

    @Override
    public int getItemCount() {return hData.size();}

    void addItem(homeData data) {hData.add(data);}

    class homeWordViewHolder extends RecyclerView.ViewHolder {
        public final VideoView videoView;
        public final Button goBook;
        public final TextView videoTitleText, videoContentText;
        final homeWordListAdapter mAdapter;

        public homeWordViewHolder(View itemView, homeWordListAdapter adapter) {
            super(itemView);

            videoView = itemView.findViewById(R.id.videoView);
            goBook = itemView.findViewById(R.id.goBook);
            videoTitleText = itemView.findViewById(R.id.videoTitleText);
            videoContentText = itemView.findViewById(R.id.videoContentText);

            //비디오뷰의 재생, 일시정지 등을 할 수 있는 '컨트롤바'를 붙여주는 작업
            videoView.setMediaController(new MediaController(itemView.getContext()));

            //동영상을 읽어오는데 시간이 걸리므로..
            //비디오 로딩 준비가 끝났을 때 실행하도록..
            //리스너 설정
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    //비디오 시작
                    videoView.start();
                }
            });

            //json 변환
            //이 책 보러가기
            final JSONObject jsonObject = new JSONObject();
            goBook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String title = videoTitleText.getText().toString();
                    String result = title;

                    try {
                        jsonObject.put("title", title);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    jsonObject.toString();
                    homeHttp hc = new homeHttp(result);
                    hc.execute();

                    Context context = view.getContext();
                    Intent intent = new Intent(context, bookInformation.class);
                    context.startActivity(intent);
                }
            });

            this.mAdapter = adapter;
        }

        void onBind(homeData data) {
            videoTitleText.setText(data.getVideoTitleText());
            videoContentText.setText(data.getVideoContentText());
            videoView.setVideoURI(Uri.parse(data.getVideoView()));
        }

//        //화면에 안보일때...
//        @Override
//        public void onPause() {
//            super.onPause();
//
//            //비디오 일시 정지
//            if(videoView!=null && videoView.isPlaying()) videoView.pause();
//        }

    }
}
