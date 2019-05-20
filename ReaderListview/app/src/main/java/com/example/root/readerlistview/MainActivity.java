package com.example.root.readerlistview;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.root.readerlistview.Interface.Adapter.FeedAdapter;
import com.example.root.readerlistview.Interface.Common.HttpDataHandler;
import com.example.root.readerlistview.model.RssObject;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    RssObject rssObject;
    private final String Rss_link="http://rss.nytimes.com/services/xml/rss/nyt/Science.xml";
    private final String Rss_toJson_Api=" https://api.rss2json.com/v1/api.json?rss_url=";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("News");
        setSupportActionBar(toolbar);
        recyclerView=(RecyclerView)findViewById(R.id.recycleview);
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(getBaseContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        loadRss();
        
    }

    private void loadRss() {
        AsyncTask<String,String,String> loadRssAsync=new AsyncTask<String, String, String>() {
            ProgressDialog mDialog= new ProgressDialog(MainActivity.this);

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                mDialog.setMessage("Please Wait");
                mDialog.show();
            }

            @Override
            protected void onPostExecute(String s) {
                mDialog.dismiss();
                rssObject= new Gson().fromJson(s,RssObject.class);
                FeedAdapter adapter= new FeedAdapter(rssObject,getBaseContext());
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }

            @Override
            protected String doInBackground(String... params) {
                String result;
                HttpDataHandler http=new HttpDataHandler();
                result=http.GetHttpData(params[0]);
                return result;

            }
        };
    StringBuilder url_get_data=new StringBuilder(Rss_toJson_Api);
        url_get_data.append(Rss_link);
        loadRssAsync.execute(url_get_data.toString());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.menurefresh)
            loadRss();
        return true;

    }
}
