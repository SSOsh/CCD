package com.example.ccd;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class chatbotWordListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<chatbotData> cbData = null;
    LayoutInflater mInflater;

    public chatbotWordListAdapter(ArrayList<chatbotData> data, Context context) {
        mInflater = LayoutInflater.from(context);
        cbData = data;
    }

    public chatbotWordListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View mItemView;

        if (viewType == chatbotCode.ViewType.LEFT) {
            mItemView = mInflater.inflate(R.layout.chatbot_receive_recycler, viewGroup, false);
            return new chatbotSendWordViewHolder(mItemView);
        } else {
            mItemView = mInflater.inflate(R.layout.chatbot_send_recycler, viewGroup, false);
            return new chatbotReceiveWordViewHolder(mItemView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof chatbotSendWordViewHolder) {
            ((chatbotSendWordViewHolder) holder).sendContent.setText(cbData.get(position).getContent());
        } else {
            ((chatbotReceiveWordViewHolder) holder).receiveContent.setText(cbData.get(position).getContent());
        }
    }

    @Override
    public int getItemCount() {
        return cbData.size();
    }

    @Override
    public int getItemViewType(int position) {
        return cbData.get(position).getViewType();
    }

    void addItem(chatbotData data) {
        cbData.add(data);
    }

    class chatbotSendWordViewHolder extends RecyclerView.ViewHolder {
        public final TextView sendContent;

        public chatbotSendWordViewHolder(View itemView) {
            super(itemView);
            sendContent = itemView.findViewById(R.id.send_content);
        }
    }

    class chatbotReceiveWordViewHolder extends RecyclerView.ViewHolder {
        public final TextView receiveContent;

        public chatbotReceiveWordViewHolder(View itemView) {
            super(itemView);
            receiveContent = itemView.findViewById(R.id.receive_content);
        }
    }
}
