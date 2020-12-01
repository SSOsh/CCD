package com.example.ccd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.ccd.controller.bookPurchaseHttp;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class bookInformation extends AppCompatActivity {
    TextView booktitleInfo, authorName, starRatingInfo, table, sumInfo;
    ImageView bookcoverInfo;
    ImageButton backBtn;
    Button purchaseBtn;
    String booktitleI, bookAuthorI, bookStarRatingI, bTableI, bSumI, bImgI;
    String bsTitleI, bsAuthorI, rTitleI, rAuthorI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_information);

        booktitleInfo = findViewById(R.id.booktitleInfo);
        authorName = findViewById(R.id.authorName);
        starRatingInfo = findViewById(R.id.starRatingInfo);
        table = findViewById(R.id.table);
        sumInfo = findViewById(R.id.sumInfo);
        bookcoverInfo = findViewById(R.id.bookcoverInfo);
        purchaseBtn = findViewById(R.id.purchaseBtn);
        backBtn = findViewById(R.id.backBtn);

        Intent intent = getIntent();
        booktitleI = intent.getExtras().getString("bookTitle");
        booktitleInfo.setText(booktitleI);
        bookAuthorI = intent.getExtras().getString("author");
        authorName.setText(bookAuthorI);
        bookStarRatingI = intent.getExtras().getString("starRating");
        starRatingInfo.setText(bookStarRatingI);
        bTableI = intent.getExtras().getString("table");
        table.setText(bTableI);
        bSumI = intent.getExtras().getString("summarize");
        sumInfo.setText(bSumI);
        bImgI = intent.getExtras().getString("bookcoverUrl");
        //bookcoverInfo.setImageResource(Integer.parseInt(bImgI));

//        Intent bsIntent = getIntent();
//        bsTitleI = bsIntent.getExtras().getString("bsTitle");
//        bsAuthorI = bsIntent.getExtras().getString("bsauthor");
//
//        Intent rIntent = getIntent();
//        rTitleI = rIntent.getExtras().getString("rTitle");
//        rAuthorI = rIntent.getExtras().getString("rAuthor");

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        final JSONObject jsonObject = new JSONObject();
        //구매 링크
        purchaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //json 변환
                String title = booktitleInfo.getText().toString();
                String author = authorName.getText().toString();
                String result = title + "/" + author;

                bookPurchaseHttp hc = new bookPurchaseHttp(result);
                hc.execute();

                try {
                    String st = hc.get();
                    System.out.println(st);
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(st));
                    startActivity(intent);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}