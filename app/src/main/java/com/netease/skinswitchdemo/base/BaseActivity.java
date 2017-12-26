package com.netease.skinswitchdemo.base;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
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

public class BaseActivity extends AppCompatActivity implements ISkinObserver {

    private static final int REQUEST_CODE_PERMISSION_READ_EXTERNAL_STORAGE = 0x01;
    public CustomLayoutInflaterFactory mFactory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // 6.0 动态权限申请
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (PermissionChecker.checkSelfPermission(
                    this, Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PermissionChecker.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_PERMISSION_READ_EXTERNAL_STORAGE);
            }
        }
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
