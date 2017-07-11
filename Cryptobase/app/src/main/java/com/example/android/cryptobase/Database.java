package com.example.android.cryptobase;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Database extends AppCompatActivity {

    ListView listView;
    FloatingActionButton fab;
    ArrayList<PassData> passDatas;
    int index = 0;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        listView = (ListView) findViewById(R.id.list_view);
        passDatas = new ArrayList<>();

        adapter = new CustomAdapter(getApplicationContext(),passDatas);
        listView.setAdapter(adapter);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Database.this,NewEntryActivity.class);
                startActivityForResult(intent,0);

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PassData currentData = adapter.getItem(position);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 0)
        {
            if(resultCode == RESULT_OK)
            {
                String userName = data.getStringExtra("username");
                String passWord = data.getStringExtra("password");
                passDatas.add(index,new PassData(userName,passWord));
                index ++;
                adapter.notifyDataSetChanged();

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
