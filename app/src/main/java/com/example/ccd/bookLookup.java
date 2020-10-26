package com.example.ccd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class bookLookup extends AppCompatActivity {
    EditText titleEdit, authorEdit;
    Button searchButton;
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_lookup);

        titleEdit = (EditText)findViewById(R.id.titleEdit);
        authorEdit = (EditText)findViewById(R.id.authorEdit);
        searchButton = (Button)findViewById(R.id.searchButton);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }
}
