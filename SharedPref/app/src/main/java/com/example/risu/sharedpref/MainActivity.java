package com.example.risu.sharedpref;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText et;
    Button bt,btt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et=(EditText)findViewById(R.id.et_enter);

        bt=(Button)findViewById(R.id.bt_enter);
        btt=(Button)findViewById(R.id.bt_view);


        bt.setOnClickListener(this);
        btt.setOnClickListener(this);

        }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bt_enter:
                saveData();

                break;

            case R.id.bt_view:
                getData();

                break;
        }
    }
    private void saveData() {

            SharedPreferences values = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor edt = values.edit();
            edt.putString("data1", et.getText().toString());
            edt.commit();
            et.setText("");
        }



    private void getData() {


        Intent intent=new Intent(MainActivity.this,Display.class);
        startActivity(intent);
    }


}
