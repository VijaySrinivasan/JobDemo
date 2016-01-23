package com.vijaysrini.jobdemo.common;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import com.amazonaws.mobileconnectors.amazonmobileanalytics.EventClient;
import com.vijaysrini.jobdemo.BuildConfig;
import com.vijaysrini.jobdemo.aws.AWSMobileClient;
import com.vijaysrini.jobdemo.service.RefreshAppSettings;

import org.json.JSONObject;

/**
 * Created by vijaysrinivasan on 12/9/15.
 * This class is mentioned in the AndroidManifest as the "Application". This class is called before the launcher activity is called.
 */
public class AndroidDemoApplication extends Application {

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
        Log.d("AndroidDemoApplication","Constructor starts");
        super.onCreate();

        // Get appsettings
        Intent refreshAppSettings = new Intent(this,RefreshAppSettings.class);
        startService(refreshAppSettings);

        //Now start initializing key lib classes.
        AWSMobileClient.initializeMobileClientIfNecessary(getApplicationContext());
        final EventClient eventClient =
                AWSMobileClient.defaultMobileClient().getMobileAnalyticsManager().getEventClient();
        eventClient.addGlobalAttribute("app version", BuildConfig.VERSION_NAME);

        Analytics.generateScreenOpenAWSAnalyticsEvent("App Home");



    }


}