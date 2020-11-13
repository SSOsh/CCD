package com.example.ccd;

import android.app.Activity;
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
import java.util.zip.Inflater;

public class bspWordListAdapter extends RecyclerView.Adapter<bspWordListAdapter.bspWordViewHolder> {
    private ArrayList<bookStatusData> bspData = new ArrayList<>();
    LayoutInflater mInflater;
//    public bspWordListAdapter(ArrayList<bookStatusData> searchDataSet, Activity activity) {
//        bspData = searchDataSet;
//    }

    public bspWordListAdapter(bookStatusProgressLookup context) {mInflater = LayoutInflater.from(context);}

    public bspWordListAdapter(ArrayList<bookStatusData> mData) {
        bspData = mData;
    }

    @NonNull
    @Override
    public bspWordListAdapter.bspWordViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View mItemView = mInflater.inflate(R.layout.booksp_lookup_recycle, viewGroup, false);
        return new bspWordViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull bspWordListAdapter.bspWordViewHolder holder, int position) {
        holder.onBind(bspData.get(position));

        holder.bspTitle.setText(bspData.get(position).getBspTitle());
        holder.bspAuthor.setText(bspData.get(position).getBspAuthor());
        holder.bspImg.setImageResource(bspData.get(position).getBspImg());
    }

    @Override
    public int getItemCount() {return bspData.size();}

    void addItem(bookStatusData data) {bspData.add(data);}

    class bspWordViewHolder extends RecyclerView.ViewHolder {
        public final TextView bspTitle, bspAuthor;
        public final ImageView bspImg;
        public final Button readingBtn;
        final bspWordListAdapter mAdapter;

        public bspWordViewHolder(View itemView, bspWordListAdapter adapter) {
            super(itemView);
            bspTitle = itemView.findViewById(R.id.bspTitle);
            bspAuthor = itemView.findViewById(R.id.bspAuthor);
            bspImg = itemView.findViewById(R.id.bspImg);
            readingBtn = itemView.findViewById(R.id.readingBtn);

            readingBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //읽는 중 페이지로 Intent
                    Context context = view.getContext();

                    Intent intent = new Intent(context, book_reading_lookup.class);
                    intent.putExtra("bspTitle", bspTitle.getText());
                    intent.putExtra("bspAuthor", bspAuthor.getText());
                    intent.putExtra("bspImg", bspImg.toString());
                    context.startActivity(intent);

//                    bspData.remove(getAdapterPosition());
//                    notifyItemRemoved(getAdapterPosition());
//                    notifyItemRangeChanged(getAdapterPosition(), bspData.size());
                }
            });

            this.mAdapter = adapter;
        }

        void onBind(bookStatusData data) {
            bspTitle.setText(data.getBspTitle());
            bspAuthor.setText(data.getBspAuthor());
            bspImg.setImageResource(data.getBspImg());
        }
    }
}
