package com.netease.skinswitchdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.netease.skinswitchdemo.base.BaseActivity;

/**
 * @author neo
 * @date 2017/12/11
 * Copyright 2017 NetEase. All rights reserved.
 */

public class SecondaryActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);
    }
}
