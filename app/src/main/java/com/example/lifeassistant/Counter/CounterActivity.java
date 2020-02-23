package com.example.lifeassistant.Counter;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lifeassistant.BaseDrawerActivity;
import com.example.lifeassistant.R;

import java.util.ArrayList;
import java.util.List;

public class CounterActivity extends BaseDrawerActivity {
    private RecyclerView counterRecyclerView;
    CounterRecAdapter counterRecAdapter;
    List<Counter> counterList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.counters_main, contentFrameLayout);
        counterRecyclerView = findViewById(R.id.counterRecView);
        counterList = new ArrayList<>();
        counterList.add(new Counter("Siemasiema",4));
        counterList.add(new Counter("siema",2));
        counterRecAdapter = new CounterRecAdapter(counterList, counterRecyclerView);
        counterRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        counterRecyclerView.setAdapter(counterRecAdapter);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
