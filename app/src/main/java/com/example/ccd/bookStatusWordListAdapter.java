package com.example.ccd;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ccd.controller.bookStatusHttp;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class bookStatusWordListAdapter extends RecyclerView.Adapter<bookStatusWordListAdapter.bookStatusWordViewHolder> {
    private ArrayList<bookStatusData> bspData = new ArrayList<>();
    LayoutInflater mInflater;
//    public bspWordListAdapter(ArrayList<bookStatusData> searchDataSet, Activity activity) {
//        bspData = searchDataSet;
//    }

    public bookStatusWordListAdapter(Context context) {mInflater = LayoutInflater.from(context);}

//    public bookStatusWordListAdapter(ArrayList<bookStatusData> mData) {
//        bspData = mData;
//    }

    @NonNull
    @Override
    public bookStatusWordViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View mItemView = mInflater.inflate(R.layout.book_status_recycler, viewGroup, false);
        return new bookStatusWordViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull bookStatusWordViewHolder holder, int position) {
        holder.onBind(bspData.get(position));

        holder.bspTitle.setText(bspData.get(position).getBspTitle());
        holder.bspAuthor.setText(bspData.get(position).getBspAuthor());
        holder.bspImg.setImageResource(bspData.get(position).getBspImg());
    }

    @Override
    public int getItemCount() {return bspData.size();}

    void addItem(bookStatusData data) {bspData.add(data);}

    class bookStatusWordViewHolder extends RecyclerView.ViewHolder {
        public final TextView bspTitle, bspAuthor;
        public final ImageView bspImg;
        public final Button readingBtn;
        final bookStatusWordListAdapter mAdapter;

        public bookStatusWordViewHolder(View itemView, bookStatusWordListAdapter adapter) {
            super(itemView);
            bspTitle = itemView.findViewById(R.id.bspTitle);
            bspAuthor = itemView.findViewById(R.id.bspAuthor);
            bspImg = itemView.findViewById(R.id.bspImg);
            readingBtn = itemView.findViewById(R.id.readingBtn);
            final JSONObject jsonObject = new JSONObject();

            readingBtn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    //json 변환
                    String title = bspTitle.getText().toString();
                    String author = bspAuthor.getText().toString();
                    String oldStatus = "읽을 책";
                    //memberID 추가
                    String result = title + "/" + author + "/" + oldStatus;

                    try {
                        jsonObject.put("title", title);
                        jsonObject.put("author", author);
                        jsonObject.put("oldStatus", oldStatus);
                    } catch(JSONException e) {
                        e.printStackTrace();
                    }

                    bookStatusHttp hc = new bookStatusHttp(result);
                    hc.execute();

                    Intent goReadIntent = new Intent(view.getContext(), bookRead.class);
                    view.getContext().startActivity(goReadIntent);
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
