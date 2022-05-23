package com.example.tranquil1;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPreference {

    public Context getContext = null;
    public SharedPreference(Context context) {
        getContext = context;
    }

    public int getIntegerPreference(String key, int defaultValue) {
        int value = defaultValue;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext);
        if (preferences != null) {
            value = preferences.getInt(key, defaultValue);
        }
        return value;
    }

    public boolean setIntegerPreference(String key, int value) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext);
        if (preferences != null) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt(key, value);
            return editor.commit();
        }
        return false;
    }
}
