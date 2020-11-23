package com.example.ccd;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ccd.controller.homeHttp;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

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
                    String videoUrl = hData.get(getAdapterPosition()).getVideoView();
                    String result = videoUrl;

                    try {
                        jsonObject.put("videoUrl", videoUrl);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    homeHttp hc = new homeHttp(result);
                    hc.execute();
                    Context context = view.getContext();

                    try {
                        String arr[] = hc.get().split("/");

                        Intent intent = new Intent(view.getContext(), bookInformation.class);
                        intent.putExtra("bookTitle", arr[0]);
                        intent.putExtra("author", arr[1]);
                        intent.putExtra("starRating", arr[2]);
                        intent.putExtra("table", arr[3]);
                        intent.putExtra("summarize", arr[4]);
                        intent.putExtra("bookcoverUrl", arr[5]);

                        context.startActivity(intent);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
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
