package com.netease.skinswitchdemo.skin;

/**
 * @author neo
 * @date 2017/12/7
 * Copyright 2017 NetEase. All rights reserved.
 */

public interface ISkinObserver {
    /**
     * 注册观察者
     */
    void attach();

    /**
     * 更新资源
     */
    void update();

    /**
     * 解注册观察者
     */
    void detach();
}
