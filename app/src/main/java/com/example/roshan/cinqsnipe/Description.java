package com.example.roshan.cinqsnipe;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


public class Description extends AppCompatActivity {
    private WebView mywebview;
    private static String url = "http://api.mantraideas.com/";
    private ProgressDialog pDialog;
    private TextView desc, title, category,link,author;
    private Button btn_readmore;
    private ProgressBar progressBar;
    int progress = 0;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Description.this.requestWindowFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        desc = (TextView) findViewById(R.id.txt_description);
        title = (TextView) findViewById(R.id.txt_title);
        author=(TextView)findViewById(R.id.txt_author);
        category = (TextView) findViewById(R.id.txt_category);
        link=(TextView)findViewById(R.id.txt_link);
        btn_readmore=(Button)findViewById(R.id.btn_read_more);
        progressBar=(ProgressBar)findViewById(R.id.progressbar);
        mywebview=(WebView)findViewById(R.id.webview);

        String ttl = getIntent().getExtras().getString("title");
        final String description = getIntent().getExtras().getString("description");
        String cty = getIntent().getExtras().getString("category");
        final String lnk = getIntent().getExtras().getString("link");
        String athor=getIntent().getExtras().getString("author");
        title.setText(ttl);
        desc.setText(description);
        category.setText(cty);
        link.setText(lnk);
        author.setText(athor);
        Description.this.setTitle("Description");


        btn_readmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mywebview.setVisibility(View.VISIBLE);
                desc.setVisibility(View.GONE);
                title.setVisibility(View.GONE);
                link.setVisibility(View.GONE);
                author.setVisibility(View.GONE);
                category.setVisibility(View.GONE);
                btn_readmore.setVisibility(View.GONE);


                //webview

                WebSettings webSettings = mywebview.getSettings();
                webSettings.setJavaScriptEnabled(true);
                mywebview.loadUrl(lnk);
                mywebview.setWebChromeClient(new WebChromeClient(){

                    public void onProgressChanged(WebView view, int progress) {
                        progressBar.setVisibility(ProgressBar.VISIBLE);
                        progressBar.setProgress(progress);
                        if(progress == 100)
                            progressBar.setVisibility(ProgressBar.GONE);
                    }
                });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
        }
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
