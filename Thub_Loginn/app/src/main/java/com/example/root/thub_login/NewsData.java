package com.example.root.thub_login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

public class NewsData extends AppCompatActivity {
WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_data);
        webView=(WebView)findViewById(R.id.webview);
        Bundle bundle=getIntent().getExtras();
        webView.loadUrl(bundle.getString("Link"));
    }
}
