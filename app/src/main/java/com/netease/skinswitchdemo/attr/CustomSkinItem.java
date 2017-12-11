package com.netease.skinswitchdemo.attr;

import android.view.View;

import com.netease.skinswitchdemo.MyApp;
import com.netease.skinswitchdemo.skin.SkinManager;

import java.util.List;

/**
 * @author neo
 * @date 2017/12/4
 * Copyright 2017 NetEase. All rights reserved.
 */

public class CustomSkinItem {

    public View target;
    public List<CustomizeAttr> attrList;

    public CustomSkinItem(View target, List<CustomizeAttr> attrList) {
        this.target = target;
        this.attrList = attrList;
    }

    /**
     * 标记了可切换状态的view将所有切换的
     */
    public void switchSkin() {
        if (target == null) {
            return;
        }
        if(null == attrList || attrList.isEmpty()) {
            return;
        }
        for (CustomizeAttr attr : attrList) {
            attr.apply(target);
        }
    }
}
