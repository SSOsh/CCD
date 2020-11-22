package com.example.ccd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.ccd.controller.disLikeHttp;
import com.example.ccd.controller.likeHttp;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class likeWordListAdapter extends RecyclerView.Adapter<likeWordListAdapter.likeWordViewHolder> {
    private ArrayList<likeData> listData = new ArrayList<>();
    LayoutInflater mInflater;

    public likeWordListAdapter(Context context) {mInflater = LayoutInflater.from(context);}

    @NonNull
    @Override
    public likeWordViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View mItemView = mInflater.inflate(R.layout.like_recycler, viewGroup, false) ;
        return new likeWordViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull likeWordViewHolder holder, int position) {
        holder.onBind(listData.get(position));
    }

    @Override
    public int getItemCount() { return listData.size(); }

    void addItem(likeData data) { listData.add(data); }

    class likeWordViewHolder extends RecyclerView.ViewHolder {
        public final TextView bookName, writer;
        public final ImageView bookImage;
        public final ToggleButton like_toggle;
        final likeWordListAdapter mAdapter;

        public likeWordViewHolder(final View itemView, likeWordListAdapter adapter) {
            super(itemView);
            bookName = itemView.findViewById(R.id.bookName);
            writer = itemView.findViewById(R.id.writer);
            bookImage = itemView.findViewById(R.id.bookImage);
            like_toggle = itemView.findViewById(R.id.like_toggle);

            like_toggle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(like_toggle.isChecked()) {
                        JSONObject jsonObject = new JSONObject();
                        //json 변환
                        String bname = bookName.getText().toString();
                        String author = writer.getText().toString();
                        String result = bname + "/" + author;

                        try {
                            jsonObject.put("bookName", bname);
                            jsonObject.put("author", author);
                        } catch(JSONException e) {
                            e.printStackTrace();
                        }

                        jsonObject.toString();
                        likeHttp hc = new likeHttp(result);
                        hc.execute();
                    } else {
                        JSONObject jso = new JSONObject();
                        //json 변환
                        String bname = bookName.getText().toString();
                        String author = writer.getText().toString();
                        String result = bname + "/" + author;

                        try {
                            jso.put("bookName", bname);
                            jso.put("author", author);
                        } catch(JSONException e) {
                            e.printStackTrace();
                        }

                        jso.toString();
                        disLikeHttp hc = new disLikeHttp(result);
                        hc.execute();
                    }
                }
            });
            this.mAdapter = adapter;
        }

        void onBind(likeData data) {
            bookName.setText(data.getBookName());
            writer.setText(data.getWriter());
            bookImage.setImageResource(data.getBook_image());
        }
    }
}
