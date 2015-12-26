package com.vijaysrini.jobdemo.common;

import android.app.Application;
import android.util.Log;

import org.json.JSONObject;

/**
 * Created by vijaysrinivasan on 12/9/15.
 */
public class AppSingleton extends Application {

    private JSONObject appSettingJson;

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

        //Later -- Add methods when you need to initialize anything in the app.

    }


}

