package com.example.ccd;

import android.content.Context;
import android.content.Intent;
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
import java.util.concurrent.ExecutionException;

public class bsResultWordListAdapter extends BaseAdapter {
    private ArrayList<bsResultData> bData = new ArrayList<bsResultData>();

    public bsResultWordListAdapter() {}

    @Override
    public int getCount() {return bData.size();}

    @Override
    public bsResultData getItem(int position) {return bData.get(position);}

    @Override
    public long getItemId(int position) {return position;}

    public void addItem(int img, String title, String author) {
        bsResultData item = new bsResultData();
        item.setBsrTitle(title);
        item.setBsrAuthor(author);
        item.setBsrImg(img);
        bData.add(item);
    }

    public class bListViewHolder {
        TextView bsrTitle, bsrAuthor;
        ImageView bsrImg;
        Button bsrBtn;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();
        final bListViewHolder holder;

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.bs_result_recycler, parent, false);
            holder = new bListViewHolder();

            holder.bsrTitle = (TextView)convertView.findViewById(R.id.bsrTitle);
            holder.bsrAuthor = (TextView)convertView.findViewById(R.id.bsrAuthor);
            holder.bsrImg = (ImageView)convertView.findViewById(R.id.bsrImg);
            holder.bsrBtn = (Button)convertView.findViewById(R.id.bsrBtn);
        }
        else {
            holder = (bsResultWordListAdapter.bListViewHolder)convertView.getTag();
        }

        holder.bsrTitle.setText(bData.get(position).getBsrTitle());
        holder.bsrAuthor.setText(bData.get(position).getBsrAuthor());
        holder.bsrImg.setImageResource(bData.get(position).getBsrImg());
        final JSONObject jsonObject = new JSONObject();

        holder.bsrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //버튼 클릭 시 해당 도서 정보로
                //json 변환
                String title = holder.bsrTitle.getText().toString();
                String author = holder.bsrAuthor.getText().toString();
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

                try {
                    String arr[] = hc.get().split("/");

                    Context context = view.getContext();
                    Intent intent = new Intent(context, bookInformation.class);
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

        return  convertView;
    }
}
