package com.example.ccd;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ccd.controller.bookDislikeHttp;
import com.example.ccd.controller.bsBookInfoHttp;
import com.example.ccd.controller.bsBookLikeHttp;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class bestsellerWordListAdapter  extends RecyclerView.Adapter<bestsellerWordListAdapter.bestsellerWordViewHolder> {
    private ArrayList<bestsellerData> bsData = new ArrayList<>();
    LayoutInflater mInflater;

    public bestsellerWordListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @NonNull
    @Override
    public bestsellerWordListAdapter.bestsellerWordViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View mItemView = mInflater.inflate(R.layout.bestseller_lookup_recycler, viewGroup, false);
        return new bestsellerWordViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull bestsellerWordListAdapter.bestsellerWordViewHolder holder, int position) {
        holder.onBind(bsData.get(position));

        holder.bsTitle.setText(bsData.get(position).getBsTitle());
        holder.bsauthor.setText(bsData.get(position).getBsauthor());
        holder.bsStarRating.setText(bsData.get(position).getBsStarRating());
        holder.bsImg.setImageResource(bsData.get(position).getBsImg());
    }

    @Override
    public int getItemCount() { return bsData.size(); }

    void addItem(bestsellerData data) { bsData.add(data);}

    class bestsellerWordViewHolder extends RecyclerView.ViewHolder {
        public final TextView bsTitle, bsauthor, bsStarRating;
        //        public final EditText editText;
        public final ImageView bsImg;
        public final Button bsGoBookInfoBtn;
        public final ToggleButton bsLike_toggle;
        final bestsellerWordListAdapter mAdapter;

        public bestsellerWordViewHolder(View itemView, bestsellerWordListAdapter adapter) {
            super(itemView);
            bsTitle = itemView.findViewById(R.id.bsBookTitle);
            bsauthor = itemView.findViewById(R.id.bsAuthor);
            bsStarRating = itemView.findViewById(R.id.bsStarRating);
//            editText = itemView.findViewById(R.id.editText);
            bsImg = itemView.findViewById(R.id.bsBookCoverImg);
            bsGoBookInfoBtn = itemView.findViewById(R.id.bsGoBookInfoBtn);
            bsLike_toggle = itemView.findViewById(R.id.bsLike_toggle);

            //json 변환
            final JSONObject jsonObject = new JSONObject();
            bsGoBookInfoBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    String title = bsTitle.getText().toString();
                    String author = bsauthor.getText().toString();
                    String result = title + "/" + author;

                    try {
                        jsonObject.put("title", title);
                        jsonObject.put("author", author);
                    } catch(JSONException e) {
                        e.printStackTrace();
                    }

                    bsBookInfoHttp hc = new bsBookInfoHttp(result);
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

            bsLike_toggle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //좋아요 선택되면 좋아요 페이지에 도서 추가, 취소되면 삭제
                    if(bsLike_toggle.isChecked()) {
                        //json 변환
                        JSONObject jso = new JSONObject();

                        String bookName = bsTitle.getText().toString();
                        String writer = bsauthor.getText().toString();
                        String result = bookName + "/" + writer;

                        try {
                            jso.put("bookName", bookName);
                            jso.put("writer", writer);
                        } catch(JSONException e) {
                            e.printStackTrace();
                        }

                        bsBookLikeHttp hc = new bsBookLikeHttp(result);
                        hc.execute();
                    }
                    else {
                        //json 변환
                        JSONObject json = new JSONObject();

                        String title = bsTitle.getText().toString();
                        String author = bsauthor.getText().toString();
                        //userID 추가
                        String result = title + "/" + author;

                        try {
                            json.put("title", title);
                            json.put("author", author);
                        } catch(JSONException e) {
                            e.printStackTrace();
                        }

                        bookDislikeHttp hc = new bookDislikeHttp(result);
                        hc.execute();
                    }
                }
            });

            this.mAdapter = adapter;
        }

        void onBind(bestsellerData data) {
            bsTitle.setText(data.getBsTitle());
            bsauthor.setText(data.getBsauthor());
            bsStarRating.setText(data.getBsStarRating());
            bsImg.setImageResource(data.getBsImg());
        }
    }
}
