package com.zakiyah.Pawfella.pawapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.zakiyah.Pawfella.pprojekkk.R;

public class pawcorner extends AppCompatActivity {
    WebView web;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pawcorner);


        //WEBVIEW MODULE
        web = findViewById(R.id.webv);
        web.setWebViewClient(new WebViewClient());
        web.loadUrl("http://147.139.129.198/f/");
        web.getSettings().setAppCachePath(getApplicationContext().getFilesDir().getAbsolutePath() + "/cache");
        web.getSettings().setDomStorageEnabled(true);
        web.getSettings().setJavaScriptEnabled(true);
        //END OF WEB VIEW MODULE

        //appbar
        toolbar = findViewById(R.id.toolbarpawcorner);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Paw Corner");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public void onBackPressed() {
        if(web.canGoBack()){
            web.goBack();
        }else{
            startActivity(new Intent(pawcorner.this,Main3Activity.class));
            finish();
        }
    }

    @Override
    public boolean onSupportNavigateUp(){
        Main3Activity m = new Main3Activity();
        startActivity(new Intent(pawcorner.this,m.getClass()));
        finish();
        return true;
    }
}
