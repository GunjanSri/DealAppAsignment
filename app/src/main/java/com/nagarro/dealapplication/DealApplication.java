package com.nagarro.dealapplication;

import android.app.Application;

import com.nagarro.dealapplication.analytics.FbTracker;

public class DealApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        FbTracker.getAnalyticsInstance(this);
    }
}
