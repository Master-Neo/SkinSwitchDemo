package com.netease.skinswitchdemo.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author neo
 * @date 2017/12/11
 * Copyright 2017 NetEase. All rights reserved.
 */

public class PreferenceUtil {

    private static final String PREFERENCE_NAME = "skin_preference";

    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        SharedPreferences sp = context
                .getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(key, defaultValue);
    }

    public static void putBoolean(Context context, String key, boolean value) {
        SharedPreferences sp = context
                .getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean(key, value).apply();
    }
}
