package com.example.ccd;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.ccd.controller.chatbotHttp;

import java.util.ArrayList;

public class chatbotFragment extends Fragment {
    EditText editText;
    Button button;

    RecyclerView chatbotRecyclerView;
    chatbotWordListAdapter mAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chatbot, container, false);

        editText = view.findViewById(R.id.editText);
        button = view.findViewById(R.id.button);
        chatbotRecyclerView = view.findViewById(R.id.chatbotRecyclerView);


        LinearLayoutManager manager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);

        //1은 받는거 2는 주는거
        //이름, 내용, 종류
        ArrayList<chatbotData> data = new ArrayList<chatbotData>();
        data.add(new chatbotData("안녕안녕 나는 주리야 헬륨가스 먹었더니 요로케돼찌~", 1));
        data.add(new chatbotData("Gray", 2));
        mAdapter = new chatbotWordListAdapter(data, view.getContext());


        chatbotRecyclerView.setAdapter(mAdapter);
        chatbotRecyclerView.setLayoutManager(manager);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chatbotHttp chatbotHttp = new chatbotHttp();

                String str = editText.getText().toString();
                chatbotData data = new chatbotData();
                data.setContent(str);
                data.setViewType(2);
                mAdapter.addItem(data);
                mAdapter.notifyDataSetChanged();
                chatbotRecyclerView.scrollToPosition(mAdapter.getItemCount() - 1);
                //이제 서버에서 값 보내고 받기
                chatbotHttp.execute();
            }
        });

        return view;
    }
}