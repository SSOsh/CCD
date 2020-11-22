package com.example.ccd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

public class searchWordListAdapter extends BaseAdapter {
//    LayoutInflater mInflater;
//    Context mContext;
    private ArrayList<searchData> sData = new ArrayList<searchData>();
//    private TextView historyText;

    public searchWordListAdapter() {
//        this.mContext = context;
//        this.sData = data;
//        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() { return sData.size(); }

    @Override
    public searchData getItem(int position) {return sData.get(position);}

    @Override
    public long getItemId(int position) { return position; }

    public void addItem(String historyText) {
        searchData item = new searchData();
        item.setHistoryText(historyText);
        sData.add(item);
    }

    public class ListViewHolder {
        TextView historyText;
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();
        ListViewHolder holder;

        if(converView == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            converView = inflater.inflate(R.layout.search_recycler, parent, false);
            holder = new ListViewHolder();

            holder.historyText = (TextView)converView.findViewById(R.id.historyText);
            converView.setTag(holder);
        }
        else {
            holder = (ListViewHolder)converView.getTag();
        }

        holder.historyText.setText(sData.get(position).getHistoryText());

        return converView;
    }
}
