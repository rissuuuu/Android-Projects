package com.example.risu.listview;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by RISU on 9/21/2017.
 */

public class Sharedlist extends AppCompatActivity {
    String[] presidents;
    ListView listView;
    ArrayAdapter<String> adapter;
    SharedPreferences FeedPref;
    SharedPreferences.Editor fd;
    TextView txt1,txt2;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        listView=(ListView)findViewById(R.id.list);
        FeedPref= PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String uname=FeedPref.getString("uname",null);
    }
}
