package com.example.ccd;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.ccd.controller.bookInfoHttp;
import com.example.ccd.controller.bookListHttp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class bookLookupFragment extends Fragment {
    RecyclerView mRecyclerView;
    WordListAdapter mAdapter;
    EditText searchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = (View)inflater.inflate(R.layout.fragment_book_lookup, container, false);

        searchView = view.findViewById(R.id.searchView);
        mRecyclerView = view.findViewById(R.id.recyclerV);
        mAdapter = new WordListAdapter(view.getContext());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sIntent = new Intent(getActivity(), search.class);
                startActivity(sIntent);
            }
        });

        initData();

//        bookListHttp bh=  new bookListHttp();
//        bh.execute();
//        JSONArray jarr = null;
//        try {
//            jarr = new JSONArray(bh.get());
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        for(int i=0; i<jarr.length(); i++) {
//            JSONObject obj;
//            try {
//                obj = jarr.getJSONObject(i);
//                bookData data = new bookData();
//                data.setBookTitle(obj.getString("title"));
//                data.setAuthor(obj.getString("author"));
//                data.setStarRating(obj.getString("starRating"));
//                data.setBookCoverImg(obj.getString("bookcoverUrl"));
//                mAdapter.addItem(data);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//        mAdapter.notifyDataSetChanged();

        return view;
    }

    void initData() {
        List<String> listTitle = Arrays.asList(
                "나미야 잡화점의 기적", "제3인류", "기린의 날개" , "일인칭 단수", "방구석 미술관2"
        );
        List<String> listAuthor = Arrays.asList(
                "히가시노 게이고", "베르나르 베르베르", "히가시노 게이고", "무라카미하루키", "조원재"
        );
        List<Integer> listImg = Arrays.asList(
                R.drawable.namiya, R.drawable.third, R.drawable.giraffe, R.drawable.inch, R.drawable.won
        );
        List<String> listStar = Arrays.asList(
                "3", "3", "4", "3", "4"
        );

        for(int i=0; i<listTitle.size(); i++) {
            bookData data = new bookData();
            data.setBookTitle(listTitle.get(i));
            data.setAuthor(listAuthor.get(i));
            data.setBookCoverImg(listImg.get(i));
            data.setStarRating(listStar.get(i));
            mAdapter.addItem(data);
        }
        mAdapter.notifyDataSetChanged();
    }
}