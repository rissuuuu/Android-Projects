package com.example.risu.sql_listview;

import android.net.Uri;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static android.R.attr.data;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    ArrayAdapter<String> arrayAdapter;
    String spec="http://localhost/list.php";
    InputStream inputStream=null;
    String line=null;
    String result=null;
    String[] data1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv=(ListView)findViewById(R.id.List_items);
       StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
        getData();
       arrayAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,data1);
        lv.setAdapter(arrayAdapter);
    }
    private void getData(){
        try {
            URL url=new URL(spec);
            HttpURLConnection connection=(HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            inputStream=new BufferedInputStream(connection.getInputStream());



        } catch (Exception e) {
            e.printStackTrace();
        }



        try{
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder=new StringBuilder();
            while((line=bufferedReader.readLine())!=null){
                stringBuilder.append(line+"\n");
            }
            inputStream.close();
            result=stringBuilder.toString();

        }
        catch (Exception e){
            e.printStackTrace();
        }
        try{
            JSONArray jsonArray=new JSONArray(result);
            JSONObject jsonObject;
            data1=new String[jsonArray.length()];


            for(int i=0;i<jsonArray.length();i++){
                jsonObject=jsonArray.getJSONObject(i);
                data1[i]=jsonObject.getString("Complain")+"    Status:  "+jsonObject.getString("Status");


            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
