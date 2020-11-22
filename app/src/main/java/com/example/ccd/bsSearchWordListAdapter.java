package com.example.ccd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class bsSearchWordListAdapter extends BaseAdapter  {
    private ArrayList<bsSearchData> bsData = new ArrayList<>();

    public bsSearchWordListAdapter() {}

    @Override
    public int getCount() {return bsData.size();}

    @Override
    public bsSearchData getItem(int position) {return bsData.get(position);}

    @Override
    public long getItemId(int position) {return position;}

    public void addItem(String bsHistoryText) {
        bsSearchData item = new bsSearchData();
        item.setBsHistoryText(bsHistoryText);
        bsData.add(item);
    }

    public class bsListViewHolder {
        TextView bsHistoryText;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();
        bsListViewHolder holder;

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.bs_search_recycler, parent, false);
            holder = new bsListViewHolder();

            holder.bsHistoryText = (TextView)convertView.findViewById(R.id.bsHistoryText);
            convertView.setTag(holder);
        }
        else {
            holder = (bsListViewHolder)convertView.getTag();
        }

        holder.bsHistoryText.setText(bsData.get(position).getBsHistoryText());

        return convertView;
    }
}
