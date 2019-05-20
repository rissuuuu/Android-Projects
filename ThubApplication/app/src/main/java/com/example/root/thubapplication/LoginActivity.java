package com.example.root.thubapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText usernamelogin,passwordlogin;
    Button buttonlogin,buttonregister;
    String usr,pw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernamelogin=(EditText)findViewById(R.id.etusername);
        passwordlogin=(EditText)findViewById(R.id.etpassword);
        buttonlogin=(Button)findViewById(R.id.bt_login);
        buttonregister=(Button)findViewById(R.id.bt_register);

        usr="rishav";
        pw="rishav123";

        buttonregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                /*if((usr.equals(usernamelogin.getText().toString())) && (pw.equals(passwordlogin.getText().toString()))) {

                }
                else{
                    Toast.makeText(getApplicationContext(),"Incorrect username and password",Toast.LENGTH_SHORT).show();
                }*/
            }
        });
    }
    }

