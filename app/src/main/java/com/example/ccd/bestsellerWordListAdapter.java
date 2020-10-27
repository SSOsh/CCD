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

public class bestsellerWordListAdapter extends RecyclerView.Adapter<bestsellerWordListAdapter.bestsellerWordViewHolder> {
    private ArrayList<bestsellerData> bsData = new ArrayList<>();
    LayoutInflater mInflater;

    public bestsellerWordListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @NonNull
    @Override
    public  bestsellerWordListAdapter.bestsellerWordViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View mItemView = mInflater.inflate(R.layout.bestseller_output, viewGroup, false);
        return new bestsellerWordViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull bestsellerWordListAdapter.bestsellerWordViewHolder holder, int position) {
        holder.onBind(bsData.get(position));


    }

    @Override
    public int getItemCount() { return bsData.size(); }

    void addItem(bestsellerData data) { bsData.add(data);}

    class bestsellerWordViewHolder extends RecyclerView.ViewHolder {
        public final TextView bsTitle, bsauthor, bsStarRating;
        public final ImageView bsImg;
        public final Button bsGoBookInfoBtn;
        public final ToggleButton bsLike_toggle;
        final bestsellerWordListAdapter mAdapter;

        public bestsellerWordViewHolder(View itemView, bestsellerWordListAdapter adapter) {
            super(itemView);
            bsTitle = itemView.findViewById(R.id.bsTitle);
            bsauthor = itemView.findViewById(R.id.bsauthor);
            bsStarRating = itemView.findViewById(R.id.bsStarRating);
            bsImg = itemView.findViewById(R.id.bsImg);
            bsGoBookInfoBtn = itemView.findViewById(R.id.bsGoBookInfoBtn);
            bsLike_toggle = itemView.findViewById(R.id.bsLike_toggle);

            bsGoBookInfoBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    String str = "";
                    Context context = view.getContext();

                    Intent intent = new Intent(view.getContext(), bookInformation.class);
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
