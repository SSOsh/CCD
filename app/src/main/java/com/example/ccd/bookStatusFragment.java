package com.example.ccd;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class bookStatusFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private bspWordListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<bookStatusData> mData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_status, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.bspRecyclerV);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.scrollToPosition(0);
        mAdapter = new bspWordListAdapter(mData);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    void initData() {
        List<String> bspTitleList = Arrays.asList(
                "나미야 잡화점의 기적"
        );
        List<String> bspAuthorList = Arrays.asList(
                "히가시노 게이고"
        );
        List<Integer> bspImgList = Arrays.asList(
                R.drawable.namiya
        );

        for(int i=0; i<bspTitleList.size(); i++) {
            bookStatusData data = new bookStatusData();
            data.setBspTitle(bspTitleList.get(i));
            data.setBspAuthor(bspAuthorList.get(i));
            data.setBspImg(bspImgList.get(i));
            mAdapter.addItem(data);
        }
        mAdapter.notifyDataSetChanged();
    }
}
