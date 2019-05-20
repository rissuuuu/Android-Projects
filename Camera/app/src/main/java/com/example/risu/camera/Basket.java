package com.example.risu.camera;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class Basket extends AppCompatActivity {
TextView tv_block,tv_room,tv_des;
    ImageView iv_image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        ArrayAdapter arrayAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1);
        ListView listView=(ListView)findViewById(R.id.listviewbasket);
        listView.setAdapter(arrayAdapter);

    }
}
