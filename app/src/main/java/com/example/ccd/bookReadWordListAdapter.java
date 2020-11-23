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

import com.example.ccd.controller.bookReadHttp;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class bookReadWordListAdapter extends RecyclerView.Adapter<bookReadWordListAdapter.bookReadWordViewHolder> {
    ArrayList<bookReadData> brData = new ArrayList<>();
    LayoutInflater mInflater;

    public bookReadWordListAdapter(Context context) {mInflater = LayoutInflater.from(context);}

    @NonNull
    @Override
    public bookReadWordListAdapter.bookReadWordViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View mItemView = mInflater.inflate(R.layout.book_read_recycler, viewGroup, false);
        return new bookReadWordViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull bookReadWordListAdapter.bookReadWordViewHolder holder, int position) {
        holder.onBind(brData.get(position));

        holder.bingTitle.setText(brData.get(position).getBingTitle());
        holder.bingAuthor.setText(brData.get(position).getBingAuthor());
        holder.bingImg.setImageResource(brData.get(position).getBingImg());
    }

    @Override
    public int getItemCount() {return brData.size();}

    void addItem(bookReadData data) {brData.add(data);}

    class bookReadWordViewHolder extends RecyclerView.ViewHolder {
        public final TextView bingTitle, bingAuthor;
        public final ImageView bingImg;
        public final Button readBtn;
        final bookReadWordListAdapter mAdapter;

        public bookReadWordViewHolder(final View itemView, bookReadWordListAdapter adapter) {
            super(itemView);
            bingTitle = itemView.findViewById(R.id.bingTitle);
            bingAuthor = itemView.findViewById(R.id.bingAuthor);
            bingImg = itemView.findViewById(R.id.bingImg);
            readBtn = itemView.findViewById(R.id.readBtn);
            final JSONObject jsonObject = new JSONObject();

            readBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //독서 완료 페이지로
                    //json 변환
                    String title = bingTitle.getText().toString();
                    String author = bingAuthor.getText().toString();
                    //memberID 추가
                    String oldStatus = "읽는 중";
                    String result = title + "/" + author + "/" + oldStatus;

                    try {
                        jsonObject.put("title", title);
                        jsonObject.put("author", author);
                        jsonObject.put("oldStatus", oldStatus);
                        //memberID 추가
                    } catch(JSONException e) {
                        e.printStackTrace();
                    }

                    bookReadHttp hc = new bookReadHttp(result);
                    hc.execute();

                    Intent goDoneIntent = new Intent(itemView.getContext(), bookDone.class);
                    itemView.getContext().startActivity(goDoneIntent);
                }
            });

            this.mAdapter = adapter;
        }

        void onBind(bookReadData data) {
            bingTitle.setText(data.getBingTitle());
            bingAuthor.setText(data.getBingAuthor());
            bingImg.setImageResource(data.getBingImg());
        }
    }
}
