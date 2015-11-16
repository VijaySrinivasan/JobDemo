package com.vijaysrini.jobdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AutoCompleteTextView;

import com.vijaysrini.jobdemo.R;
import com.vijaysrini.jobdemo.service.WebAppInterface;


public class WebViewActivity extends AppCompatActivity {

    private AutoCompleteTextView urlText;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        webView = (WebView) findViewById(R.id.webView);
        webView.addJavascriptInterface(new WebAppInterface(this),"Android");

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setUserAgentString("my-user-agent");

        webView.setWebViewClient(new WebViewClient());
        Log.i("Webview onCreate", "url is " + getIntent().getStringExtra("url"));
        webView.loadUrl(getIntent().getStringExtra("url"));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void loadUrl (View v){
        Log.i("Load", "Loading url " + urlText.getText().toString());
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromInputMethod(v.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
        webView.loadUrl(urlText.getText().toString());
    }

}
