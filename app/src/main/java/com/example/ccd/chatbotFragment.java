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

import com.example.ccd.controller.chatbotControl;
import com.example.ccd.controller.chatbotHttp;
import com.example.ccd.controller.chatbotOpen;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

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
        data.add(new chatbotData("챗봇말풍선", 1));
        data.add(new chatbotData("send", 2));
        mAdapter = new chatbotWordListAdapter(data, view.getContext());

//        chatbotOpen co = new chatbotOpen();
//
//        co.execute();
//
//
//        String openstr = null;
//        try {
//            openstr = co.get();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        chatbotData d = new chatbotData();
//        d.setContent(openstr);
//        d.setViewType(1);
//        mAdapter.addItem(d);
//        mAdapter.notifyDataSetChanged();
//

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