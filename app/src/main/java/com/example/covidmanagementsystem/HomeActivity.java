package com.example.covidmanagementsystem;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private List<CardItem> cardItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, AddPatientActivity.class);
                startActivity(intent);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Object> itemList = new ArrayList<>();
            // Add items to the list
        itemList.add(new CardItem(R.drawable.p1, "John Doe", "Medical Status: Recovering from flu", "ID: JD20240523"));
        itemList.add(new CardItem(R.drawable.p2, "Emily Smith", "Medical Status: Broken arm", "ID: ES20240523"));
        itemList.add(new CardItem(R.drawable.p3, "David Johnson", "Medical Status: Allergic reaction", "ID: DJ20240523"));
        itemList.add(new CardItem(R.drawable.p4, "Sarah Wilson", "Medical Status: High fever", "ID: SW20240523"));
        itemList.add(new CardItem(R.drawable.p5, "Michael Brown", "Medical Status: Recovered from surgery", "ID: MB20240523"));

        CardAdapter cardAdapter = new CardAdapter(this, itemList);
        recyclerView.setAdapter(cardAdapter);

        }
}