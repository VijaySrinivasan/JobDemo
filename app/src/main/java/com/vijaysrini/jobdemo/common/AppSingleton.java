package com.vijaysrini.jobdemo.common;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import com.vijaysrini.jobdemo.service.RefreshAppSettings;

import org.json.JSONObject;

/**
 * Created by vijaysrinivasan on 12/9/15.
 */
public class AppSingleton extends Application {

    private JSONObject appSettingJson;
    //@TODO: You can add a timestamp variable to track when the values were last refreshed.

    public JSONObject getAppSettingJson() {
        return appSettingJson;
    }

    public void setAppSettingJson(JSONObject appSettingJson) {
        this.appSettingJson = appSettingJson;
    }

    @Override
    public void onCreate() {
        Log.d("AppSingleton","Constructor starts");
        super.onCreate();

        // Get appsettings
        Intent refreshAppSettings = new Intent(this,RefreshAppSettings.class);
        startService(refreshAppSettings);

        //Later -- Add methods when you need to initialize anything in the app.

    }


}

