package com.example.ccd;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class likeFragment extends Fragment {
    RecyclerView mRecyclerView;
    likeWordListAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = (View)inflater.inflate(R.layout.fragment_like, container, false);

        SharedPreferences sharedPreferences= getActivity().getSharedPreferences("Value", MODE_PRIVATE);    // test 이름의 기본모드 설정, 만약 test key값이 있다면 해당 값을 불러옴.
        String memberID = "";
        memberID = sharedPreferences.getString("id","");

        mRecyclerView = view.findViewById(R.id.recyclerView2);
        mAdapter = new likeWordListAdapter(view.getContext(), memberID);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        initData();

        return view;
    }

    void initData() {
        List<String> likeTitle = Arrays.asList(
                "나미야 잡화점의 기적", "제3인류", "기린의 날개"
        );
        List<String> likeAuthor = Arrays.asList(
                "히가시노 게이고", "베르나르 베르베르", "히가시노 게이고"
        );
        List<Integer> likeImg = Arrays.asList(
                R.drawable.namiya, R.drawable.third, R.drawable.giraffe
        );

        for(int i=0; i<likeTitle.size(); i++) {
            likeData data = new likeData();
            data.setBookName(likeTitle.get(i));
            data.setWriter(likeAuthor.get(i));
            data.setBook_image(likeImg.get(i));
            mAdapter.addItem(data);
        }
        mAdapter.notifyDataSetChanged();
    }
}