package com.vijaysrini.jobdemo.service;

/**
 * Created by vijaysrinivasan on 11/15/15.
 */
public class Settings {
    private static Settings ourInstance = new Settings();

    public static Settings getInstance() {

        return ourInstance;
    }

    private Settings() {
    }
}
