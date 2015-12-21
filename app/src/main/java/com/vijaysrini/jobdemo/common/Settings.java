package com.vijaysrini.jobdemo.common;

/**
 * Created by vijaysrinivasan on 11/15/15.
 * This is the singleton that has the application settings.
 */
public class Settings {
    private static Settings ourInstance = new Settings();

    public static Settings getInstance() {

        return ourInstance;
    }

    private Settings() {
    }
}
