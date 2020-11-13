package com.example.ccd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class test extends AppCompatActivity {
    TabLayout tab;
    TabItem homeTab, bookListTab, bestsellerTab, chatbotTab;
    Button myInfoBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        tab = (TabLayout)findViewById(R.id.tab);
        homeTab = (TabItem)findViewById(R.id.homeTab);
        bookListTab = (TabItem)findViewById(R.id.bookListTab);
        bestsellerTab = (TabItem)findViewById(R.id.bestsellerTab);
        chatbotTab = (TabItem)findViewById(R.id.chatbotTab);
        myInfoBtn = findViewById(R.id.myInfoBtn);

        myInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //내 정보 페이지로 이동(intent)
                Intent intent = new Intent(view.getContext(), MyInfoManagement.class);
                startActivity(intent);
            }
        });

        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition() ;
                changeView(pos) ;
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        }) ;
    }

    private void changeView(int index) {
        switch (index) {
            case 0:
                Intent intent = new Intent(getApplicationContext(), lookupVideo.class);
                startActivity(intent);
                break;
            case 1:
                Intent intent2 = new Intent(getApplicationContext(), bookLookup.class);
                startActivity(intent2);
                break;
            case 2:
                Intent intent3 = new Intent(getApplicationContext(), bestsellerLookup.class);
                startActivity(intent3);
                break;
            case 3:
                Intent intent4 = new Intent(getApplicationContext(), ChatbotActivity.class);
                startActivity(intent4);
                break;
        }
    }
}
