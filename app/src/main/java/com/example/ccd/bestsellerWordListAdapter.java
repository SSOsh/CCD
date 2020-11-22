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

import com.example.ccd.controller.bookDislikeHttp;
import com.example.ccd.controller.bsBookInfoHttp;
import com.example.ccd.controller.bsBookLikeHttp;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class bestsellerWordListAdapter  extends RecyclerView.Adapter<bestsellerWordListAdapter.bestsellerWordViewHolder> {
    private ArrayList<bestsellerData> bsData = new ArrayList<>();
    LayoutInflater mInflater;

    public bestsellerWordListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @NonNull
    @Override
    public bestsellerWordViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View mItemView = mInflater.inflate(R.layout.bestseller_lookup_recycler, viewGroup, false);
        return new bestsellerWordViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull bestsellerWordViewHolder holder, int position) {
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
                    String starRate = bsStarRating.getText().toString();
                    String img = bsImg.getResources().toString();
                    String result = title + "/" + author + "/" + starRate + "/" + img;

                    try {
                        jsonObject.put("title", title);
                        jsonObject.put("author", author);
                        jsonObject.put("starRate", starRate);
                        jsonObject.put("img", img);
                    } catch(JSONException e) {
                        e.printStackTrace();
                    }

                    jsonObject.toString();
                    bsBookInfoHttp hc = new bsBookInfoHttp(result);
                    hc.execute();

                    String str = "";
                    Context context = view.getContext();
                    Intent intent = new Intent(context, bookInformation.class);
                    intent.putExtra("bsTitle", bsTitle.getText());
                    intent.putExtra("bsauthor", bsauthor.getText());
                    intent.putExtra("bsStarRating", bsStarRating.getText());
                    intent.putExtra("bsImg", bsImg.toString());

                    context.startActivity(intent);
                }
            });

            bsLike_toggle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //좋아요 선택되면 좋아요 페이지에 도서 추가, 취소되면 삭제
                    if(bsLike_toggle.isChecked()) {
                        //json 변환
                        JSONObject jso = new JSONObject();

                        String title = bsTitle.getText().toString();
                        String writer = bsauthor.getText().toString();
                        String result = title + "/" + writer;

                        try {
                            jso.put("title", title);
                            jso.put("writer", writer);
                        } catch(JSONException e) {
                            e.printStackTrace();
                        }

                        jso.toString();
                        bsBookLikeHttp hc = new bsBookLikeHttp(result);
                        hc.execute();
                    }
                    else {
                        //json 변환
                        JSONObject json = new JSONObject();

                        String title = bsTitle.getText().toString();
                        String writer = bsauthor.getText().toString();
                        String result = title + "/" + writer;

                        try {
                            json.put("title", title);
                            json.put("writer", writer);
                        } catch(JSONException e) {
                            e.printStackTrace();
                        }

                        json.toString();
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
