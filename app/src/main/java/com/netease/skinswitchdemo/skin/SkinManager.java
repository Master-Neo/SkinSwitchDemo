package com.netease.skinswitchdemo.skin;

import android.content.Context;
import android.content.res.Resources;

import com.netease.skinswitchdemo.IConstants;
import com.netease.skinswitchdemo.util.PreferenceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author neo
 * @date 2017/11/28
 * Copyright 2017 NetEase. All rights reserved.
 */

public class SkinManager {
    private volatile static SkinManager sInstance;
    private Resources resources;
    private List<ISkinObserver> skinObservers;

    private SkinManager(Context context) {
        boolean isSkinResource = PreferenceUtil
                .getBoolean(context, IConstants.KEY_SKIN_PREFERENCE, false);
        if (isSkinResource) {
            resources = new CustomResources(context.getResources());
        } else {
            resources = context.getResources();
        }
        skinObservers = new ArrayList<>();
    }

    public static SkinManager getInstance(Context context) {
        if (sInstance == null) {
            synchronized (SkinManager.class) {
                if (sInstance == null) {
                    sInstance = new SkinManager(context);
                }
            }
        }
        return sInstance;
    }

    /**
     * 切换成加载皮肤资源
     * @param context
     */
    public void switch2SkinResource(Context context) {
        PreferenceUtil.putBoolean(context, IConstants.KEY_SKIN_PREFERENCE, true);
        resources = new CustomResources(context.getResources());
        notifyObservers();
    }

    /**
     * 使用默认资源
     * @param context
     */
    public void restore2DefaultResource(Context context) {
        PreferenceUtil.putBoolean(context, IConstants.KEY_SKIN_PREFERENCE, false);
        resources = context.getResources();
        notifyObservers();
    }

    /**
     * 增加换肤观察者
     * @param observer
     */
    public void pushObserver(ISkinObserver observer) {
        skinObservers.add(observer);
    }

    /**
     * 取消观察者
     * @param observer
     */
    public void popObserver(ISkinObserver observer) {
        if (skinObservers.isEmpty()) {
            return;
        }
        skinObservers.remove(observer);
    }

    /**
     * 通知观察者更新皮肤资源
     */
    private void notifyObservers() {
        if (skinObservers.isEmpty()) {
            return;
        }
        for (ISkinObserver observer : skinObservers) {
            observer.update();
        }
    }

    public Resources getResources() {
        return resources;
    }
}
