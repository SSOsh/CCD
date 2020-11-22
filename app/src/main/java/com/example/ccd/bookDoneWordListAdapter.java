package com.example.ccd;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ccd.controller.bookDoneHttp;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class bookDoneWordListAdapter extends RecyclerView.Adapter<bookDoneWordListAdapter.bookDoneWordViewHolder>{
    private ArrayList<bookDoneData> bdData = new ArrayList<>();
    LayoutInflater mInflater;

    public bookDoneWordListAdapter(Context context) {mInflater = LayoutInflater.from(context);}

    @NonNull
    @Override
    public bookDoneWordListAdapter.bookDoneWordViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View mItemView = mInflater.inflate(R.layout.book_done_recycler, viewGroup, false);
        return new bookDoneWordViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull bookDoneWordListAdapter.bookDoneWordViewHolder holder, int position) {
        holder.onBind(bdData.get(position));

        holder.bdoneTitle.setText(bdData.get(position).getBdoneTitle());
        holder.bdoneAuthor.setText(bdData.get(position).getBdoneAuthor());
        holder.bdoneImg.setImageResource(bdData.get(position).getBdoneImg());
    }

    @Override
    public int getItemCount() {return bdData.size();}

    void addItem(bookDoneData data) {bdData.add(data);}

    class bookDoneWordViewHolder extends RecyclerView.ViewHolder {
        public final TextView bdoneTitle, bdoneAuthor;
        public final ImageView bdoneImg;
        public final Button deleteBtn;
        final bookDoneWordListAdapter mAdapter;

        public bookDoneWordViewHolder(View itemView, bookDoneWordListAdapter adapter) {
            super(itemView);
            bdoneTitle = itemView.findViewById(R.id.bdoneTitle);
            bdoneAuthor = itemView.findViewById(R.id.bdoneAuthor);
            bdoneImg = itemView.findViewById(R.id.bdoneImg);
            deleteBtn = itemView.findViewById(R.id.deleteBtn);
            final JSONObject jsonObject = new JSONObject();

            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //삭제
                    //json 변환
                    String title = bdoneTitle.getText().toString();
                    //userID
                    String result = title + "/" ;

                    try {
                        jsonObject.put("title", title);
                    } catch(JSONException e) {
                        e.printStackTrace();
                    }

                    bookDoneHttp hc = new bookDoneHttp(result);
                    hc.execute();

                    //화면에서 삭제
                    bdData.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    notifyItemRangeChanged(getAdapterPosition(), bdData.size());
                    Toast.makeText(view.getContext(), "삭제되었습니다.", Toast.LENGTH_SHORT).show();
                }
            });

            this.mAdapter = adapter;
        }

        void onBind(bookDoneData data) {
            bdoneTitle.setText(data.getBdoneTitle());
            bdoneAuthor.setText(data.getBdoneAuthor());
            bdoneImg.setImageResource(data.getBdoneImg());
        }
    }
}
