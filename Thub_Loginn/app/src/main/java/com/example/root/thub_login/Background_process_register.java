package com.example.root.thub_login;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Background_process_register extends AsyncTask<String,Void,String> {
    Context context;
    AlertDialog alertDialog;
    String types;

    Background_process_register(Context ctx) {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        types = params[0];
        String register_url = "http:/192.168.43.6/register.php";
        if (types.equals("register")) {
            try {
                String firstname = params[1];
                String lastname = params[2];
                String email = params[3];
                String username = params[4];
                String password = params[5];
                URL url =new URL(register_url);
                HttpURLConnection httpcon=(HttpURLConnection)url.openConnection();
                httpcon.setRequestMethod("POST");
                httpcon.setDoOutput(true);
                httpcon.setDoInput(true);
                OutputStream outputStream=httpcon.getOutputStream();
                BufferedWriter bufferWriter=new BufferedWriter(new OutputStreamWriter(outputStream));
                String post_data= URLEncoder.encode("firstname","UTF-8")+"="+URLEncoder.encode(firstname,"UTF-8")+"&"
                        +URLEncoder.encode("lastname","UTF-8")+"="+URLEncoder.encode(lastname,"UTF-8")+"&"
                        +URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"
                        +URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                bufferWriter.write(post_data);
                bufferWriter.flush();
                bufferWriter.close();
                outputStream.close();
                InputStream inputStream=httpcon.getInputStream();
                BufferedReader bufferReader=new BufferedReader(new InputStreamReader(inputStream));
                String result="";
                String line="";
                while ((line=bufferReader.readLine())!=null){
                    result+=line;

                }
                bufferReader.close();
                inputStream.close();
                httpcon.disconnect();
                return result;
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }return null;
    }

    @Override
    protected void onPreExecute() {
        alertDialog=new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Registration Status");
    }

    @Override
    protected void onPostExecute(String result) {
        if(types.equals("register")){
            Intent intent=new Intent(context,LoginActivity.class);
            context.startActivity(intent);
        }
        else{
            alertDialog.setMessage(result);
            alertDialog.show();
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
