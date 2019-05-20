package com.example.root.displaydatabase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;

public class MainActivity extends AppCompatActivity {
    final static String DB_URL="https://mdata-f0234.firebaseio.com";
    EditText nameTxt,descTxt;
    Button saveBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize
        InitializeFirebase();
        nameTxt=(EditText)findViewById(R.id.etname);
        descTxt=(EditText)findViewById(R.id.et_desc);
        saveBtn=(Button)findViewById(R.id.bt_save);

        //Get firebase instance
        final Firebase fire=new Firebase(DB_URL);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Movie m=new Movie();
                m.setName(nameTxt.getText().toString());
                m.setDesription(descTxt.getText().toString());

                //Persist
                fire.child("Movie").setValue(m);
                nameTxt.setText("");
                descTxt.setText("");

            }
        });
    }

    //Initialize our firebase
    private void InitializeFirebase(){
        Firebase.setAndroidContext(this);
    }
}
