package com.netease.skinswitchdemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;

import com.netease.skinswitchdemo.skin.CustomLayoutInflaterFactory;
import com.netease.skinswitchdemo.skin.ISkinObserver;
import com.netease.skinswitchdemo.skin.SkinManager;

import java.lang.reflect.Field;

/**
 * @author neo
 * @date 2017/12/11
 * Copyright 2017 NetEase. All rights reserved.
 */

public class BaseActivity extends FragmentActivity implements ISkinObserver {

    public CustomLayoutInflaterFactory mFactory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        createInflateFactory();
        super.onCreate(savedInstanceState);
    }

    private void createInflateFactory() {
        try {
            Field field = LayoutInflater.class.getDeclaredField("mFactorySet");
            field.setAccessible(true);
            field.set(getLayoutInflater(), false);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        mFactory = new CustomLayoutInflaterFactory();
        getLayoutInflater().setFactory(mFactory);
    }

    @Override
    protected void onResume() {
        super.onResume();
        attach();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        detach();
    }

    @Override
    public void attach() {
        SkinManager.getInstance(this).pushObserver(this);
    }

    @Override
    public void update() {
        mFactory.switchSkin();
    }

    @Override
    public void detach() {
        SkinManager.getInstance(this).popObserver(this);
    }
}
