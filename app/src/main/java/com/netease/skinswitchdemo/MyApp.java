package com.netease.skinswitchdemo;

import android.app.Application;
import android.content.res.Resources;

import com.netease.skinswitchdemo.skin.CustomResources;

/**
 * @author neo
 * @date 2017/11/8
 * Copyright 2017 NetEase. All rights reserved.
 */

public class MyApp extends Application {

    public static final String TAG = "MyApp";

    private static MyApp mApp;
    private static CustomResources mResources;

    public static MyApp getInstance() {
        return mApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
    }
}
