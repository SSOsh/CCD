package com.example.ccd;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class bReadingWordListAdapter extends RecyclerView.Adapter<bReadingWordListAdapter.bReadingWordViewHolder> {
    ArrayList<bReadingData> brData = new ArrayList<>();
    LayoutInflater mInflater;

    public bReadingWordListAdapter(Context context) {mInflater = LayoutInflater.from(context);}

    @NonNull
    @Override
    public bReadingWordListAdapter.bReadingWordViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View mItemView = mInflater.inflate(R.layout.book_ing_recycle, viewGroup, false);
        return new bReadingWordViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull bReadingWordListAdapter.bReadingWordViewHolder holder, int position) {
        holder.onBind(brData.get(position));

        holder.bingTitle.setText(brData.get(position).getBingTitle());
        holder.bingAuthor.setText(brData.get(position).getBingAuthor());
        holder.bingImg.setImageResource(brData.get(position).getBingImg());
    }

    @Override
    public int getItemCount() {return brData.size();}

    void addItem(bReadingData data) {brData.add(data);}

    class bReadingWordViewHolder extends RecyclerView.ViewHolder {
        public final TextView bingTitle, bingAuthor;
        public final ImageView bingImg;
        public final Button readBtn;
        final bReadingWordListAdapter mAdapter;

        public bReadingWordViewHolder(View itemView, bReadingWordListAdapter adapter) {
            super(itemView);
            bingTitle = itemView.findViewById(R.id.bingTitle);
            bingAuthor = itemView.findViewById(R.id.bingAuthor);
            bingImg = itemView.findViewById(R.id.bingImg);
            readBtn = itemView.findViewById(R.id.readBtn);

            readBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //독서 완료 페이지로 Intent
                    Context context = view.getContext();

                    Intent intent = new Intent(context, book_done_lookup.class);
                    intent.putExtra("bingTitle", bingTitle.getText());
                    intent.putExtra("bingAuthor", bingAuthor.getText());
                    intent.putExtra("bingImg", bingImg.toString());
                    context.startActivity(intent);
                }
            });

            this.mAdapter = adapter;
        }

        void onBind(bReadingData data) {
            bingTitle.setText(data.getBingTitle());
            bingAuthor.setText(data.getBingAuthor());
            bingImg.setImageResource(data.getBingImg());
        }
    }
}
