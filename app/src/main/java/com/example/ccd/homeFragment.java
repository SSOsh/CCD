package com.example.ccd;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import java.util.Arrays;
import java.util.List;

public class homeFragment extends Fragment {
    RecyclerView mRecyclerView;
    homeWordListAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mRecyclerView = view.findViewById(R.id.vRecyclerView);
        mAdapter = new homeWordListAdapter(view.getContext());
        mRecyclerView.setAdapter(mAdapter);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));

//        SnapHelper snapHelper = new PagerSnapHelper();
//        snapHelper.attachToRecyclerView(mRecyclerView);

        initData();

        return view;
    }

    void initData() {
        List<String> vTitle = Arrays.asList(
                "나미야 잡화점의 기적", "에베베"
        );
        List<String> vText = Arrays.asList(
                "히가시노 게이고의 대표작", "아뇽앙뇨온ㄻㅇ"
        );
        List<String> video = Arrays.asList(
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4", "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
        );

        for(int i=0; i<vTitle.size(); i++) {
            homeData data = new homeData();
            data.setVideoTitleText(vTitle.get(i));
            data.setVideoTitleText(vText.get(i));
            data.setVideoView(video.get(i));
            mAdapter.addItem(data);
        }
        mAdapter.notifyDataSetChanged();
    }
}