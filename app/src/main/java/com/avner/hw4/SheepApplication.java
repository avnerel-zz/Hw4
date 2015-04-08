package com.avner.hw4;

import android.app.Application;

/**
 * Created by avner on 07/04/2015.
 */
public class SheepApplication extends Application {

    public SQLUtils sqlUtils;

    @Override
    public void onCreate() {
        super.onCreate();

        sqlUtils = new SQLUtils(getApplicationContext());


    }
}
