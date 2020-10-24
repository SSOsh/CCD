package com.example.ccd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {
    private ArrayList<Data> listData = new ArrayList<>();
    LayoutInflater mInflater;

    public WordListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public WordListAdapter.WordViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View mItemView = mInflater.inflate(R.layout.activity_book_lookup__recycler, viewGroup, false);
        return new WordViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull WordListAdapter.WordViewHolder holder, int position) {
        holder.onBind(listData.get(position));
    }

    @Override
    public int getItemCount() { return listData.size(); }

    void addItem(Data data) { listData.add(data); }

    class WordViewHolder extends  RecyclerView.ViewHolder {
        public final TextView bookTitle, author, starRating;
        public final ImageView bookCoverImg;
        final WordListAdapter mAdapter;

        public WordViewHolder(@NonNull View itemView, WordListAdapter adapter) {
            super(itemView);
            bookTitle = itemView.findViewById(R.id.bookTitle);
            author = itemView.findViewById(R.id.author);
            starRating = itemView.findViewById(R.id.starRating);
            bookCoverImg = itemView.findViewById(R.id.bookCoverImg);

            this.mAdapter = adapter;
        }

        void onBind(Data data) {
            bookTitle.setText(data.getBookTitle());
            author.setText(data.getAuthor());
            starRating.setText(data.getStarRating());
            bookCoverImg.setImageResource(data.getBookCoverImg());
        }
    }
}
