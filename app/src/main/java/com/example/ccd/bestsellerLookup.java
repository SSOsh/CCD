package com.example.ccd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class bestsellerLookup extends AppCompatActivity implements TextWatcher {
    TextView bsTitle;
    EditText editText;
    RecyclerView mRecyclerView;
    bestsellerWordListAdapter mAdapter;
    ArrayList<String> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bestseller_lookup);

        bsTitle = findViewById(R.id.bsTitle);
        editText = findViewById(R.id.editText);
        mRecyclerView = findViewById(R.id.bs_recyclerV);
        mAdapter = new bestsellerWordListAdapter(this, items);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        editText.addTextChangedListener(this);
        items.add("나미야 백화점의 기적");
        items.add("제3인류");
        items.add("기린의 날개");

        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        mAdapter.getFilter().filter(charSequence);
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
