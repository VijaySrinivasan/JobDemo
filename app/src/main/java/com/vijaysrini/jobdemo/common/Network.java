package com.vijaysrini.jobdemo.common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by vijaysrinivasan on 12/3/15.
 */
public class Network {

    private static final int readTimeout = 20000; // this.getApplicationContext()
    private static final int connectTimeout = 20000;
    private static final String TAG = "Network";

    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        // if no network is available networkInfo will be null
        // otherwise check if we are connected
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }

    public static RestTask obtainGetTask(String url) throws MalformedURLException, IOException {

        Log.d(TAG, "Starting obtainGetTask with " + url);
        HttpURLConnection connection = (HttpURLConnection) (new URL(url)).openConnection();
        if (connection != null) {
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(15000);
            connection.setDoInput(true);
            RestTask task = new RestTask(connection);
            return task;
        } else {
            Log.d(TAG,"Unable to create a connection");
            return null;
        }
    }




}
