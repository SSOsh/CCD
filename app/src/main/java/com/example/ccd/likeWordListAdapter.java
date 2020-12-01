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


import com.bumptech.glide.Glide;
import com.example.ccd.controller.disLikeHttp;
import com.example.ccd.controller.likeHttp;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class likeWordListAdapter extends RecyclerView.Adapter<likeWordListAdapter.likeWordViewHolder> {
    private ArrayList<likeData> listData = new ArrayList<>();
    LayoutInflater mInflater;
    Context context;
    String memberID;
    public likeWordListAdapter(Context context, String memberID) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
        this.memberID =memberID;
    }

    @NonNull
    @Override
    public likeWordViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View mItemView = mInflater.inflate(R.layout.like_recycler, viewGroup, false);
        return new likeWordViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull likeWordViewHolder holder, int position) {
        holder.onBind(listData.get(position));
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    void addItem(likeData data) {
        listData.add(data);
    }

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
                    String bname = bookName.getText().toString();
                    String author = writer.getText().toString();
                    String result = bname + "/" + author + "/" + memberID;

                    if (like_toggle.isChecked()) {
                        likeHttp hc = new likeHttp(result);
                        hc.execute();
                    } else {
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
            Glide.with(itemView.getContext()).load(data.getBook_image()).into(bookImage);
        }
    }
}
