package com.example.ccd;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.ccd.controller.commentDeleteHttp;
import com.example.ccd.controller.commentModifyHttp;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class commentWordListAdapter extends RecyclerView.Adapter<commentWordListAdapter.commentWordViewHolder> {
    ArrayList<commentData> cData = new ArrayList<>();
    LayoutInflater mInflater;

    public commentWordListAdapter(Context context) {mInflater = LayoutInflater.from(context);}

    @NonNull
    @Override
    public commentWordViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View mItemView = mInflater.inflate(R.layout.comment_recycler, viewGroup, false);
        return new commentWordViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull commentWordViewHolder holder, int position) {
        holder.onBind(cData.get(position));

        holder.commentId.setText(cData.get(position).getCommentId());
        holder.comment.setText(cData.get(position).getComment());
    }

    @Override
    public int getItemCount() {return cData.size();}

    void addItem(commentData data) {cData.add(data);}

    class commentWordViewHolder extends RecyclerView.ViewHolder {
        public final TextView commentId, comment;
        public final Button commentModifyBtn, commentDeleteBtn;
        final commentWordListAdapter mAdapter;

        public commentWordViewHolder(View itemView, commentWordListAdapter adapter) {
            super(itemView);
            commentId = itemView.findViewById(R.id.commentId);
            comment = itemView.findViewById(R.id.comment);
            commentModifyBtn = itemView.findViewById(R.id.commentModifyBtn);
            commentDeleteBtn = itemView.findViewById(R.id.commentDeleteBtn);

            final JSONObject jsonObject = new JSONObject();
            commentModifyBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //json 변환
                    String id = commentId.getText().toString();
                    String com = comment.getText().toString();
                    String result = id + "/" + com;

                    try {
                        jsonObject.put("id", id);
                        jsonObject.put("com", com);
                    } catch(JSONException e) {
                        e.printStackTrace();
                    }

                    jsonObject.toString();
                    commentModifyHttp hc = new commentModifyHttp(result);
                    hc.execute();

                    Context context = view.getContext();
                    //수정 후 메시지
                    Toast.makeText(context, "수정되었습니다.", Toast.LENGTH_SHORT).show();
                }
            });

            final JSONObject jso = new JSONObject();
            commentDeleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Context context = view.getContext();
                    //삭제 확인 팝업
                    AlertDialog.Builder ad = new AlertDialog.Builder(context);
                    ad.setTitle("삭제 확인 메시지");
                    ad.setMessage("댓글을 삭제하시겠습니까?");

                    final EditText et = new EditText(context);
                    ad.setView(et);

                    ad.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            //삭제
                            //json 변환
                            String id = commentId.getText().toString();
                            String com = comment.getText().toString();
                            String result = id + "/" + com;

                            try {
                                jso.put("id", id);
                                jso.put("com", com);
                            } catch(JSONException e) {
                                e.printStackTrace();
                            }

                            jso.toString();
                            commentDeleteHttp hc = new commentDeleteHttp(result);
                            hc.execute();

                            dialog.dismiss();
                            Toast.makeText(context, "댓글이 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                        }
                    });

                    ad.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            dialog.dismiss();
                        }
                    });
                    ad.show();
                }
            });

            this.mAdapter = adapter;
        }

        void onBind(commentData data) {
            commentId.setText(data.getCommentId());
            comment.setText(data.getComment());
        }
    }
}
