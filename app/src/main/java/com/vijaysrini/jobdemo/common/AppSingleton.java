package com.vijaysrini.jobdemo.common;

import android.app.Application;
import android.util.Log;

/**
 * Created by vijaysrinivasan on 12/9/15.
 */
public class AppSingleton extends Application {

    @Override
    public void onCreate() {
        Log.d("AppSingleton","This class is getting called.");
        super.onCreate();
    }
}

