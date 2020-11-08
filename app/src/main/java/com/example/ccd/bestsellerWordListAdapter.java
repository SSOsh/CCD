package com.example.ccd;

import android.content.Context;
import android.content.Intent;
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

import java.util.ArrayList;

public class bestsellerWordListAdapter extends RecyclerView.Adapter<bestsellerWordListAdapter.bestsellerWordViewHolder> implements Filterable{
    private ArrayList<bestsellerData> bsData = new ArrayList<>();
    LayoutInflater mInflater;
    ArrayList<String> unFilteredlist;
    ArrayList<String> filteredList;

    public bestsellerWordListAdapter(Context context, ArrayList<String> list) {
        super();
        this.unFilteredlist = list;
        this.filteredList = list;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public  bestsellerWordListAdapter.bestsellerWordViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View mItemView = mInflater.inflate(R.layout.bestseller_output, viewGroup, false);
        return new bestsellerWordViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull bestsellerWordListAdapter.bestsellerWordViewHolder holder, int position) {
        holder.onBind(bsData.get(position));
        holder.editText.setText(filteredList.get(position));
    }

    @Override
    public int getItemCount() { return filteredList.size(); }

    void addItem(bestsellerData data) { bsData.add(data);}

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if(charString.isEmpty()) {
                    filteredList = unFilteredlist;
                }
                else {
                    ArrayList<String> filteringList = new ArrayList<>();
                    for(String name : unFilteredlist) {
                        if(name.toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(name);
                        }
                    }
                    filteredList = filteringList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredList = (ArrayList<String>)results.values;
                notifyDataSetChanged();
            }
        };

    }

    class bestsellerWordViewHolder extends RecyclerView.ViewHolder  {
        public final TextView bsTitle, bsauthor, bsStarRating;
        public final EditText editText;
        public final ImageView bsImg;
        public final Button bsGoBookInfoBtn;
        public final ToggleButton bsLike_toggle;
        final bestsellerWordListAdapter mAdapter;

        public bestsellerWordViewHolder(View itemView, bestsellerWordListAdapter adapter) {
            super(itemView);
            bsTitle = itemView.findViewById(R.id.bsTitle);
            bsauthor = itemView.findViewById(R.id.bsauthor);
            bsStarRating = itemView.findViewById(R.id.bsStarRating);
            editText = itemView.findViewById(R.id.editText);
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
                    //좋아요 선택되면 좋아요 페이지에 도서 추가, 취소되면 삭제
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
