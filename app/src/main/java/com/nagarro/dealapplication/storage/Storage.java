package com.nagarro.dealapplication.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public abstract class Storage {

    private static final String TAG = Storage.class.getSimpleName();

    final Context context;

    public Storage(Context context) {
        this.context = context.getApplicationContext();
    }

    public abstract String getStorageId();

    public String getString(String key) {
        return getSharedPreferences().getString(key, "");
    }

    public String getString(String key , String defaultValue) {
        return getSharedPreferences().getString(key, defaultValue);
    }

    public void putString(String key, String value) {
        final SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(key, value);
        commit(editor);
    }

    public int getInt(String key, int defaultValue) {
        return getSharedPreferences().getInt(key, defaultValue);
    }

    public void putInt(String key, int value) {
        final SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putInt(key, value);
        commit(editor);
    }

    public long getLong(String key, long defaultValue) {
        return getSharedPreferences().getLong(key, defaultValue);
    }

    public void putLong(String key, long value) {
        final SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putLong(key, value);
        commit(editor);
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return getSharedPreferences().getBoolean(key, defaultValue);
    }

    public void putBoolean(String key, boolean value) {
        final SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putBoolean(key, value);
        commit(editor);
    }

    private SharedPreferences getSharedPreferences() {
        return context.getSharedPreferences(getStorageId(), Context.MODE_PRIVATE);
    }

    public void clear() {
        final SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.clear();
        commit(editor);
    }

    private static void commit(SharedPreferences.Editor editor) {
        if (!editor.commit()) {
            Log.w(TAG, "Failed to commit data to storage", null);
        }
    }
}
