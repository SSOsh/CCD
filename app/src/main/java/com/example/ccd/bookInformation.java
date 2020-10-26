package com.example.ccd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class bookInformation extends AppCompatActivity {

    TextView booktitleInfo, authorName, starRatingInfo, table, authorInfo;
    ImageView bookcoverInfo;
    Button purchaseBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_information);

        booktitleInfo = (TextView)findViewById(R.id.booktitleInfo);
        authorName = (TextView)findViewById(R.id.authorName);
        starRatingInfo = (TextView)findViewById(R.id.starRatingInfo);
        table = (TextView)findViewById(R.id.table);
        authorInfo = (TextView)findViewById(R.id.authorInfo);
        bookcoverInfo = (ImageView) findViewById(R.id.bookcoverInfo);
        purchaseBtn = (Button)findViewById(R.id.purchaseBtn);

        purchaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //검색 시 값 출력
        Intent intent = getIntent();
        String tValue = intent.getStringExtra("title");
        String aValue = intent.getStringExtra("author");

        try {
            //자료 들어있는 file
            InputStream is = getAssets().open("file.xml");

            DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = df.newDocumentBuilder();
            Document d = db.parse(is);

            NodeList nL = d.getElementsByTagName("output");
            for(int i=0; i<nL.getLength(); i++) {
                boolean check = false;
                NodeList nodeList;
                Node node = nL.item(i);
                Element element = (Element)node;

            }
        } catch (IOException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
    }
}
