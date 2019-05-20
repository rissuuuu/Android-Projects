package com.example.root.thub_login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText et_firstname,et_lastname,et_email,et_username,et_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        et_firstname=(EditText)findViewById(R.id.first_name);
        et_lastname=(EditText)findViewById(R.id.last_name);
        et_email=(EditText)findViewById(R.id.email_register);
        et_username=(EditText)findViewById(R.id.username_register);
        et_password=(EditText)findViewById(R.id.password_register);
    }


public void OnRegister(View view){
    String firstname=et_firstname.getText().toString();
    String lastname=et_lastname.getText().toString();
    String email=et_email.getText().toString();
    String username=et_username.getText().toString();
    String password=et_password.getText().toString();
    if(firstname.equals("")||lastname.equals("")||email.equals("")||username.equals("")||password.equals("")){
        Toast.makeText(getApplicationContext(),"PLEASE ENTER ALL THE FIELDS",Toast.LENGTH_SHORT).show();
    }
    else {
        String type = "register";
        Background_process_register background_process_register = new Background_process_register(this);
        background_process_register.execute(type, firstname, lastname, email, username, password);
    }
}
}
