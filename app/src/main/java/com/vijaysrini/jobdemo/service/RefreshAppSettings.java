package com.vijaysrini.jobdemo.service;

import android.app.Application;
import android.app.IntentService;
import android.content.Intent;
import android.nfc.Tag;
import android.util.Log;

import com.vijaysrini.jobdemo.R;
import com.vijaysrini.jobdemo.common.AppSingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


/* An IntentService subclass for downloading appsettings.*/

public class RefreshAppSettings extends IntentService {
    public static String TAG = "RefreshAppSettings";

    public RefreshAppSettings() {
        super("RefreshAppSettings");
        Log.d(TAG, "constructor starts");
    }

    @Override
    protected void onHandleIntent(Intent intent)  {
        Log.d(TAG, "onHandleIntent starts");
        if (intent != null) {
            String appsettingUrl = this.getResources().getString(R.string.appsettings_url).toString();
            HttpURLConnection connection = null;
            try {
                connection = (HttpURLConnection) (new URL(appsettingUrl)).openConnection();
                if (connection != null) {
                    connection.setReadTimeout(10000);
                    connection.setConnectTimeout(15000);
                    connection.setDoInput(true);
                    String appSettingsValue = downloadAppSettings(connection);

                    //Now save in app context
                    try {
                        JSONObject appsettingJson =  new JSONObject(appSettingsValue);
                        AppSingleton myapp =  (AppSingleton) getApplication();
                        myapp.setAppSettingJson(appsettingJson);
                    } catch (JSONException je) {
                        Log.d(TAG, je.getMessage());

                    }

                }
            } catch (IOException exception) {
                Log.e(TAG, exception.getMessage());
                // @TODO: create and set the downloaded flag as false
            }
            finally {
                if (connection != null) {
                    connection.disconnect();
                }

            }
        }
    }
    private String downloadAppSettings(HttpURLConnection connection) {
        Log.d(TAG, "downloadAppSettings starts");
        try {
            int status = connection.getResponseCode();
            if (status >= 300) {
                String message = connection.getResponseMessage();
                throw  new Exception(message);
            }

            InputStream in = connection.getInputStream();
            String encoding = connection.getContentEncoding();
            int contentLength = connection.getContentLength();
            if (encoding == null) {
                encoding = "UTF-8";
            }
            byte[] buffer = new byte[1024];
            int length = contentLength > 0 ? contentLength : 0;
            ByteArrayOutputStream out = new ByteArrayOutputStream(length);
            int downloadedBytes = 0;
            int read;
            while ((read = in.read(buffer)) != -1) {
                downloadedBytes += read;
                out.write(buffer, 0, read);
            }
            String jsonValue = new String(out.toByteArray(), encoding);
            Log.d(TAG, "The downloaded AppSettings is " + jsonValue);
            return jsonValue;
        } catch (Exception exception) {
            Log.e(TAG, exception.getMessage());
            return null;
        }

    }

}
