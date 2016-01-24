package com.vijaysrini.jobdemo.activity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
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
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.vijaysrini.jobdemo.R;
import com.vijaysrini.jobdemo.common.Analytics;
import com.vijaysrini.jobdemo.common.WebAppInterface;

import java.util.HashMap;
import java.util.Map;


public class WebViewActivity extends AppCompatActivity {

    private AutoCompleteTextView urlText;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        webView = (WebView) findViewById(R.id.webView);
        webView.addJavascriptInterface(new WebAppInterface(this), "Android");

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setUserAgentString("my-user-agent");

        webView.setWebViewClient(new WebViewClient() {
            ProgressDialog pd;

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                pd = ProgressDialog.show(WebViewActivity.this, null, "Loading, please wait...");
                pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                pd.show();
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                pd.dismiss();
                super.onPageFinished(view, url);
            }
        });
        String url = getIntent().getStringExtra("url");
        Log.i("Webview onCreate", "url is " + url);

        webView.loadUrl(url);

        Log.i("Webview onCreate", "cancelled the progress dialog");

        // Trigger an analytics event
        Map attibutes = new HashMap<String,String>();
        attibutes.put("URL",url);
        Analytics.generateAWSAnalyticsEvent("Web Module",attibutes);
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

}
