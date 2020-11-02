package com.example.ccd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class likeWordListAdapter extends RecyclerView.Adapter<likeWordListAdapter.likeWordViewHolder> {
   private ArrayList<likeData> listData = new ArrayList<>();
   LayoutInflater mInflater;

   public likeWordListAdapter(Context context) {mInflater = LayoutInflater.from(context);}

    @NonNull
    @Override
    public likeWordListAdapter.likeWordViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
       View mItemView = mInflater.inflate(R.layout.output_like, viewGroup, false) ;
       return new likeWordViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull likeWordListAdapter.likeWordViewHolder holder, int position) {
        holder.onBind(listData.get(position));
    }

    @Override
    public int getItemCount() { return listData.size(); }

    void addItem(likeData data) { listData.add(data); }
    void deleteItem(likeData data) {listData.remove(data);}

    class likeWordViewHolder extends RecyclerView.ViewHolder {
       public final TextView bookName, writer;
       public final ImageView bookImage;
       public final ToggleButton like_toggle;
       final likeWordListAdapter mAdapter;

       public likeWordViewHolder(final View itemView, likeWordListAdapter adapter) {
           super(itemView);
           bookName = itemView.findViewById(R.id.bookName);
           writer = itemView.findViewById(R.id.writer);
           bookImage = itemView.findViewById(R.id.bookImage);
           like_toggle = itemView.findViewById(R.id.like_toggle);

           like_toggle.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   for(int i=0; i<listData.size(); i++) {
                       likeData data = new likeData();
                       mAdapter.addItem(data);
                   }
               }
           });
           this.mAdapter = adapter;
       }

       void onBind(likeData data) {
           bookName.setText(data.getBookName());
           writer.setText(data.getWriter());
           bookImage.setImageResource(data.getBook_image());
       }
    }
}
