package com.example.ccd;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.Arrays;
import java.util.List;

public class bestsellerLookupFragment extends Fragment {
    RecyclerView mRecyclerView;
    bestsellerWordListAdapter mAdapter;
    EditText bsSearchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = (View)inflater.inflate(R.layout.fragment_bestseller_lookup, container, false);

        bsSearchView = view.findViewById(R.id.bsSearchView);
        mRecyclerView = view.findViewById(R.id.bs_recyclerV);
        mAdapter = new bestsellerWordListAdapter(view.getContext());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        bsSearchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bIntent = new Intent(getActivity(), bsSearch.class);
                startActivity(bIntent);
            }
        });

        initData();

        return view;
    }

    void initData() {
        List<String> bsTitle = Arrays.asList(
                "나미야 잡화점의 기적", "제3인류", "기린의 날개"
        );
        List<String> bsAuthor = Arrays.asList(
                "히가시노 게이고", "베르나르 베르베르", "히가시노 게이고"
        );
        List<Integer> bsImg = Arrays.asList(
                R.drawable.namiya, R.drawable.third, R.drawable.giraffe
        );
        List<String> bsStar = Arrays.asList(
                "3", "3", "4"
        );

        for(int i=0; i<bsTitle.size(); i++) {
            bestsellerData data = new bestsellerData();
            data.setBsTitle(bsTitle.get(i));
            data.setBsauthor(bsAuthor.get(i));
            data.setBsImg(bsImg.get(i));
            data.setBsStarRatingting(bsStar.get(i));
            mAdapter.addItem(data);
        }
        mAdapter.notifyDataSetChanged();
    }
}