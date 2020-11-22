package com.example.ccd;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ccd.controller.searchBookInfoHttp;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class resultWordListAdapter extends BaseAdapter {
    private ArrayList<resultData> rData = new ArrayList<resultData>();
//    LayoutInflater mInflater;
//    Context mContext;

//    public resultWordListAdapter(Context context, ArrayList<resultData> data) {
//        mContext = context;
//        rData = data;
//        mInflater = LayoutInflater.from(context);
//    }

    public resultWordListAdapter() {

    }

    @Override
    public int getCount() { return rData.size(); }

    @Override
    public resultData getItem(int position) {return rData.get(position);}

    @Override
    public long getItemId(int position) { return position; }

    public void addItem(int img, String title, String author) {
        resultData item = new resultData();
        item.setrTitle(title);
        item.setrAuthor(author);
        item.setrImg(img);
        rData.add(item);
    }

    public class rListViewHolder {
        TextView rTitle, rAuthor;
        ImageView rImg;
        Button rBtn;
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();
        final rListViewHolder holder;

        if(converView == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            converView = inflater.inflate(R.layout.result_recycler, parent, false);
            holder = new rListViewHolder();

            holder.rTitle = (TextView)converView.findViewById(R.id.rTitle);
            holder.rAuthor = (TextView)converView.findViewById(R.id.rAuthor);
            holder.rImg = (ImageView)converView.findViewById(R.id.rImg);
            holder.rBtn = (Button)converView.findViewById(R.id.rBtn);
        }
        else {
            holder = (rListViewHolder)converView.getTag();
        }

        holder.rTitle.setText(rData.get(position).getrTitle());
        holder.rAuthor.setText(rData.get(position).getrAuthor());
        holder.rImg.setImageResource(rData.get(position).getrImg());
        final JSONObject jsonObject = new JSONObject();

        holder.rBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //버튼 클릭 시 해당 도서 정보로
                //json 변환
                String title = holder.rTitle.getText().toString();
                String author = holder.rAuthor.getText().toString();
                String result = title + "/" + author;

                try {
                    jsonObject.put("title", title);
                    jsonObject.put("author", author);
                } catch(JSONException e) {
                    e.printStackTrace();
                }

                jsonObject.toString();
                searchBookInfoHttp hc = new searchBookInfoHttp(result);
                hc.execute();

                Intent intent = new Intent(view.getContext(), bookInformation.class);
                intent.putExtra("rTitle", holder.rTitle.toString());
                intent.putExtra("rAuthor", holder.rAuthor.toString());
                view.getContext().startActivity(intent);
            }
        });

        return converView;
    }
}
