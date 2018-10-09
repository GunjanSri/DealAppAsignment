package com.nagarro.dealapplication.analytics;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;

public class FbTracker {

    private static final String TAG = FbTracker.class.getSimpleName();
    private static FirebaseAnalytics firebaseAnalytics;

    public static FirebaseAnalytics getAnalyticsInstance(Context context){
        firebaseAnalytics = FirebaseAnalytics.getInstance(context);
        return firebaseAnalytics;
    }

    public  static void trackCurrentActivity(Activity activity, String activityName){
        if(firebaseAnalytics != null) {
            firebaseAnalytics.setCurrentScreen(activity,activityName,null);
        }
    }

    public static void trackLogEvent(String itemCatogory){
        if(firebaseAnalytics != null){
            Bundle bundle = new Bundle();
            bundle.putString(FirebaseAnalytics.Param.ITEM_CATEGORY , itemCatogory );
            firebaseAnalytics.logEvent(FirebaseAnalytics.Event.VIEW_ITEM, bundle);
        }
    }
}
