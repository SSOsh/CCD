package com.example.ccd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class bDoneWordListAdapter extends RecyclerView.Adapter<bDoneWordListAdapter.bDoneWordViewHolder> {
    private ArrayList<bDoneData> bdData = new ArrayList<>();
    LayoutInflater mInflater;

    public bDoneWordListAdapter(Context context) {mInflater = LayoutInflater.from(context);}

    @NonNull
    @Override
    public bDoneWordListAdapter.bDoneWordViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View mItemView = mInflater.inflate(R.layout.book_done_recycle, viewGroup, false);
        return new bDoneWordViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull bDoneWordListAdapter.bDoneWordViewHolder holder, int position) {
        holder.onBind(bdData.get(position));

        holder.bdoneTitle.setText(bdData.get(position).getBdoneTitle());
        holder.bdoneAuthor.setText(bdData.get(position).getBdoneAuthor());
        holder.bdoneImg.setImageResource(bdData.get(position).getBdoneImg());
    }

    @Override
    public int getItemCount() {return bdData.size();}

    void addItem(bDoneData data) {bdData.add(data);}

    class bDoneWordViewHolder extends RecyclerView.ViewHolder {
        public final TextView bdoneTitle, bdoneAuthor;
        public final ImageView bdoneImg;
        public final Button deleteBtn;
        final bDoneWordListAdapter mAdapter;

        public bDoneWordViewHolder(View itemView, bDoneWordListAdapter adapter) {
            super(itemView);
            bdoneTitle = itemView.findViewById(R.id.bdoneTitle);
            bdoneAuthor = itemView.findViewById(R.id.bdoneAuthor);
            bdoneImg = itemView.findViewById(R.id.bdoneImg);
            deleteBtn = itemView.findViewById(R.id.deleteBtn);

            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //삭제
                    bdData.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    notifyItemRangeChanged(getAdapterPosition(), bdData.size());
                    Toast.makeText(view.getContext(), "삭제되었습니다.", Toast.LENGTH_SHORT).show();
                }
            });

            this.mAdapter = adapter;
        }

        void onBind(bDoneData data) {
            bdoneTitle.setText(data.getBdoneTitle());
            bdoneAuthor.setText(data.getBdoneAuthor());
            bdoneImg.setImageResource(data.getBdoneImg());
        }
    }
}
