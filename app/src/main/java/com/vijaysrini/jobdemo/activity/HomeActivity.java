package com.vijaysrini.jobdemo.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.vijaysrini.jobdemo.R;
import com.vijaysrini.jobdemo.common.MyNotificationManager;
import com.vijaysrini.jobdemo.service.RefreshAppSettings;

public class HomeActivity extends AppCompatActivity{

    private final String LOGTAG = "HomeActivity";
    private final int NOTIFICATION_ID = 1;
    LinearLayout sec1Layout;
    String pref_sec1_show;
    String prefFile;
    SharedPreferences preferences;
    String showSec1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOGTAG, "onCreate starts");


        setContentView(R.layout.activity_home);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sec1Layout = (LinearLayout) findViewById(R.id.sec1_buttons_area);
        pref_sec1_show  = getString(R.string.pref_sec1_show);
        prefFile = getString(R.string.preference_key_file);
        preferences = getSharedPreferences(prefFile, Context.MODE_PRIVATE);
        showSec1 = preferences.getString(pref_sec1_show, "default");
        if (showSec1.compareTo("no") == 0) sec1Layout.setVisibility(View.GONE);
        else sec1Layout.setVisibility(View.VISIBLE);

        Log.i("onCreate", "Sec1 button area's visibility is " + sec1Layout.getVisibility());
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
        Log.d(LOGTAG,"onOptionsItemSelected starts");

        Intent nextAction = null;
        //noinspection SimplifiableIfStatement
        switch (item.getItemId()) {
            case R.id.action_settings:
                Log.i(LOGTAG, "Menu setting was selected.");
                return true;
            case R.id.menu_locator:
                nextAction = new Intent(this,MyLocatorActivity.class);
                return true;
            case R.id.menu_open_webview:
                Log.i(LOGTAG,"Menu Open WebView was selected.");
                nextAction = new Intent(this,WebViewActivity.class);
                startActivity(nextAction);
                return true;
            case R.id.menu_signin_signout:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void hideExpandSection1 (View view) {
        SharedPreferences.Editor editor = preferences.edit();
        if (sec1Layout.getVisibility()==View.GONE) {
            sec1Layout.setVisibility(View.VISIBLE);
            editor.putString(pref_sec1_show, "yes");
        } else {
            sec1Layout.setVisibility(View.GONE);
            editor.putString(pref_sec1_show, "no");
        }
        editor.commit();
        Log.i("hideExpandSection1", "pref_sec1_show in preference is " + preferences.getString(pref_sec1_show, "default"));
    }

    // Webview section methods
    public void showMobileWebInvesting(MenuItem menuItem) {
        Log.i(LOGTAG, "Showing WebView on menu tap");
        Intent nextAction = new Intent(this,WebViewActivity.class);
        startActivity(nextAction);
    }

    public void showMobileWebInvesting(View view) {
        Log.i(LOGTAG, "Showing MobileWebHome on icon tap");

        Intent nextAction = new Intent(this,WebViewActivity.class);
        nextAction.putExtra("url",getResources().getString(R.string.cb_web_invest_url).toString());
        startActivity(nextAction);
    }

    public void showCards (View view) {
        Log.i(LOGTAG, "Showing Cards on icon tap");
        Intent nextAction = new Intent(this,WebViewActivity.class);
        nextAction.putExtra("url", getResources().getString(R.string.cb_web_cards_url).toString());
        startActivity(nextAction);
    }

    public void showAngularExample (View view) {
        Log.i(LOGTAG, "Showing AngularExample on icon tap");
        Intent nextAction = new Intent(this,WebViewActivity.class);
        nextAction.putExtra("url", getResources().getString(R.string.myangular_example_url).toString());
        startActivity(nextAction);
    }

    // SECTION : Map, Notification, voice

    public void createNotification(View view) {
        Log.i("MyNotificationManager","calling MyNotificationManager class");
        MyNotificationManager.createNotification(this, "Time to record clinical result.", "1 day left");
        Log.i("MyNotificationManager", "done calling MyNotificationManager");
    }

    public void showInMap (View view) {
        Log.i(LOGTAG, "Starting showInMap");
        Intent nextAction = new Intent(this,MapsActivity.class);
        startActivity(nextAction);
    }

    public void voiceNavigation (View view) {
        Log.i(LOGTAG, "voiceNavigation");
        Intent nextAction = new Intent(this,VoiceActivity.class);
        startActivity(nextAction);
    }

    // SECTION: Background Tasks
    public void formSubmission(View view) {
        Log.d(LOGTAG, "formSubmission");
        Intent nextAction = new Intent(this,FormSubmissionActivity.class);
        startActivity(nextAction);
    }

    public void download(View view) {
        Log.d(LOGTAG, "download: NOT IMPLEMENTED");
        //TODO: Replace this download image functionality.
        // Intent nextAction = new Intent(this,DownloadActivity.class);
        //startActivity(nextAction);
    }

    public void search_products(View view) {
        Log.d(LOGTAG, "search_products starts");
        Intent nextAction = new Intent(this,BBOpenActivity.class);
        startActivity(nextAction);
    }

    // Calls the RefreshAppsettings service to save the appsettings to SharedPreference
    public void backgroundSync(View view) {
        Log.i(LOGTAG, "calling RefreshAppsettings");
        Intent refreshAppSettings = new Intent(this,RefreshAppSettings.class);
        startService(refreshAppSettings);
    }

}
