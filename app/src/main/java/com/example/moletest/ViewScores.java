package com.example.moletest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

public class ViewScores extends AppCompatActivity {
    RecyclerView recyclerView;

    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_scores);

        back = findViewById(R.id.back);
        recyclerView = findViewById(R.id.recyclerView);
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        List<Record> record = dbHelper.getEveryone();

        MyAdapter myAdapter = new MyAdapter(ViewScores.this, this, record);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    public void back(View view) {
        finish();
    }
}