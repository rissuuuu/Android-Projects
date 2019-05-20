package com.example.risu.listview;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    SharedPreferences.Editor fd;
    SharedPreferences FeedPref;
    EditText txt1,txt2;
    Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt1=(EditText)findViewById(R.id.editText1);
        txt2=(EditText)findViewById(R.id.editText2);
        submit=(Button)findViewById(R.id.button1);



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String u_name=txt1.getText().toString();
                String pwdtxt=txt2.getText().toString();
                FeedPref= PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                fd=FeedPref.edit();
                fd.putString("Uname",u_name);
                fd.putString("pwd",pwdtxt);
                fd.commit();
                Intent sample2=new Intent(getApplicationContext(),Sharedlist.class);
                startActivity(sample2);
                finish();
            }
        });

    }
}
