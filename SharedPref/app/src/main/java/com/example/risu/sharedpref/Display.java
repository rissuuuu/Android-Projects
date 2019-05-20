package com.example.risu.sharedpref;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class Display extends AppCompatActivity {
    ListView lv;
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
         lv = (ListView) findViewById(R.id.lv_display);

        SharedPreferences shared = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        String[] get= {shared.getString("data1", null)};

            adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, get);
            lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

}
