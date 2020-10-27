package com.example.ccd;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

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
    public void onBindViewHolder(@NonNull WordListAdapter.WordViewHolder holder, final int position) {
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

    void addItem(Data data) {
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
                    String str = "";
                    Context context = view.getContext();
//                    Intent intentWeb = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"));
                    Intent intent = new Intent(view.getContext(), bookInformation.class);
                    intent.putExtra("bookTitle", bookTitle.getText());
                    intent.putExtra("author", author.getText());
                    intent.putExtra("starRating", starRating.getText());
                    intent.putExtra("bookCoverImg", bookCoverImg.toString());

                    context.startActivity(intent);
                }
            });

            like_toggle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

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
