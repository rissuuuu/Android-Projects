package com.example.root.thub_login;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.StringBuilderPrinter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText usernamelogin,passwordlogin;
    TextView tvregister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernamelogin=(EditText)findViewById(R.id.etusername);
        passwordlogin=(EditText)findViewById(R.id.etpassword);
        tvregister=(TextView)findViewById(R.id.tvreg);
        tvregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });



        }
        public void OnLogin(View view){

                String user_name=usernamelogin.getText().toString();
                String password=passwordlogin.getText().toString();
                if(user_name.equals("")||password.equals("")){
                Toast.makeText(getApplicationContext(),"LOGIN FAILED",Toast.LENGTH_SHORT).show();
                 }
                 else {
                String type="login";
                Background_process background_process=new Background_process(this);
                background_process.execute(type,user_name,password);}

        }
    }



















