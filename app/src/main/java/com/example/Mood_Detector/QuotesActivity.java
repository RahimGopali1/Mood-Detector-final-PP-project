package com.example.Mood_Detector;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class QuotesActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes);

        //giving reference to recycle view
        mRecyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setFocusable(false);

        adapter = new Adapter(this);
        mRecyclerView.setAdapter(adapter);


        //adding data to adapter

        ArrayList<Items> list = new ArrayList<>();
        list.add(new Items("Life",
                "Whatever we are, whatever we make of ourselves, is all we will ever have—and that, in its profound simplicity, is the meaning of life.",
                R.drawable.life));

        list.add(new Items("FriendShip",
                "Friendship is the hardest thing in the world to explain. It’s not something you learn in school. " +
                        "But if you haven’t learned the meaning of friendship, you really haven’t learned anything.",
                R.drawable.friends));

        list.add(new Items("Happiness",
                "Resolve to keep happy, and your joy and you shall form an invincible host against difficulties.",
                R.drawable.download));
        list.add(new Items("Time",
                "Healing is a matter of time, but it is sometimes also a matter of opportunity.",
                R.drawable.time));

        adapter.setDataToAdapter(list);

    }
}