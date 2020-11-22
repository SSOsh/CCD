package com.example.ccd;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ccd.controller.bookDislikeHttp;
import com.example.ccd.controller.bookInfoHttp;
import com.example.ccd.controller.booklikeHttp;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {
    private ArrayList<bookData> listData = new ArrayList<>();
    private ArrayList<likeData> likedata = new ArrayList<>();
    LayoutInflater mInflater;

    public WordListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View mItemView = mInflater.inflate(R.layout.book_lookup_recycler, viewGroup, false);
        return new WordViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, final int position) {
        holder.onBind(listData.get(position));

        holder.bookTitle.setText(listData.get(position).getBookTitle());
        holder.author.setText(listData.get(position).getAuthor());
        holder.starRating.setText(listData.get(position).getStarRating());
        holder.bookCoverImg.setImageResource(listData.get(position).getBookCoverImg());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    void addItem(bookData data) {
        listData.add(data);
    }

    class WordViewHolder extends RecyclerView.ViewHolder {
        public final TextView bookTitle, author, starRating;
        public final ImageView bookCoverImg;
        public final Button goBookInfoBtn;
        public final ToggleButton like_toggle;
        final WordListAdapter mAdapter;

        public WordViewHolder(View itemView, WordListAdapter adapter) {
            super(itemView);
            bookTitle = itemView.findViewById(R.id.bookTitle);
            author = itemView.findViewById(R.id.author);
            starRating = itemView.findViewById(R.id.starRating);
            bookCoverImg = itemView.findViewById(R.id.bookCoverImg);
            goBookInfoBtn = itemView.findViewById(R.id.goBookInfoBtn);
            like_toggle = itemView.findViewById(R.id.like_toggle);

            goBookInfoBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //json 변환
                    JSONObject jsonObject = new JSONObject();

                    String title = bookTitle.getText().toString();
                    String writer = author.getText().toString();
                    String result = title + "/" + writer;

                    try {
                        jsonObject.put("title", title);
                        jsonObject.put("writer", writer);
                    } catch(JSONException e) {
                        e.printStackTrace();
                    }

                    bookInfoHttp hc = new bookInfoHttp(result);
                    hc.execute();

                    try {
                        String arr[] = hc.get().split("/");

                        Context context = view.getContext();
//                    Intent intentWeb = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"));
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

            like_toggle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(like_toggle.isChecked()) {
                        //json 변환
                        JSONObject jso = new JSONObject();

                        String title = bookTitle.getText().toString();
                        String writer = author.getText().toString();
                        String result = title + "/" + writer;

                        try {
                            jso.put("title", title);
                            jso.put("writer", writer);
                        } catch(JSONException e) {
                            e.printStackTrace();
                        }

                        booklikeHttp hc = new booklikeHttp(result);
                        hc.execute();
                    }
                    else {
                        //json 변환
                        JSONObject json = new JSONObject();

                        String title = bookTitle.getText().toString();
                        String writer = author.getText().toString();
                        String result = title + "/" + writer;

                        bookDislikeHttp hc = new bookDislikeHttp(result);
                        hc.execute();
                    }
                }
            });

            this.mAdapter = adapter;
        }

        void onBind(bookData data) {
            bookTitle.setText(data.getBookTitle());
            author.setText(data.getAuthor());
            starRating.setText(data.getStarRating());
            bookCoverImg.setImageResource(data.getBookCoverImg());
        }
    }
}
