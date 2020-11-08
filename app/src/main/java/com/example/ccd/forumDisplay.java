package com.example.ccd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class forumDisplay extends AppCompatActivity {
    Button writeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_display);

        writeBtn = findViewById(R.id.writeBtn);
        writeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str="";
                Context context = view.getContext();

                Intent intent = new Intent(context, forumEnroll.class);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
    }
}
