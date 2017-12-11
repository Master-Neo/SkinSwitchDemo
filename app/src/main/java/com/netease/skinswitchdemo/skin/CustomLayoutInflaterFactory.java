package com.netease.skinswitchdemo.skin;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.netease.skinswitchdemo.attr.CustomAttrCreator;
import com.netease.skinswitchdemo.attr.CustomSkinItem;
import com.netease.skinswitchdemo.attr.CustomizeAttr;

import java.util.ArrayList;
import java.util.List;

/**
 * @author neo
 * @date 2017/11/23
 * Copyright 2017 NetEase. All rights reserved.
 */

public class CustomLayoutInflaterFactory implements LayoutInflater.Factory {
    private static final String TAG = "LayoutInflaterFactory";

    private List<CustomSkinItem> skinItemList;
    public View mView;

    public CustomLayoutInflaterFactory() {
        skinItemList = new ArrayList<>();
    }

    @Override
    public View onCreateView(String s, Context context, AttributeSet attributeSet) {
        boolean isSkinEnable = attributeSet.getAttributeBooleanValue("http://schemas.android.com/android/skin", "skin_switch", false);
        if(!isSkinEnable) {
            return null;
        }

        View view = createView(context, s, attributeSet);

        if (null == view) {
            return null;
        }
        mView = view;

        int n = attributeSet.getAttributeCount();
        List<CustomizeAttr> attrList = new ArrayList<>();
        for(int i = 0; i < n; ++ i) {
            String key = attributeSet.getAttributeName(i);
            String value = attributeSet.getAttributeValue(i);
            if (CustomAttrCreator.isSupportedAttr(key)) {
                if (value.startsWith("@")) {
                    int resId = Integer.parseInt(value.substring(1));

                    String entryName = context.getResources().getResourceEntryName(resId);
                    String typeName = context.getResources().getResourceTypeName(resId);

                    CustomizeAttr attr = CustomAttrCreator.create(context, key, resId, entryName, typeName);

                    if (null != attr) {
                        attrList.add(attr);
                    }
                }

            }
        }

        if (!attrList.isEmpty()) {
            CustomSkinItem item = new CustomSkinItem(view, attrList);
            item.switchSkin();
            skinItemList.add(item);
        }

        return view;
    }

    private View createView(Context context, String name, AttributeSet attrs) {
        View view = null;
        try {
            if (-1 == name.indexOf('.')){
                if ("View".equals(name)) {
                    view = LayoutInflater.from(context).createView(name, "android.view.", attrs);
                }
                if (view == null) {
                    view = LayoutInflater.from(context).createView(name, "android.widget.", attrs);
                }
                if (view == null) {
                    view = LayoutInflater.from(context).createView(name, "android.webkit.", attrs);
                }
            }else {
                view = LayoutInflater.from(context).createView(name, null, attrs);
            }
        } catch (Exception e) {
            view = null;
        }
        return view;
    }

    public void switchSkin() {
        if (skinItemList.isEmpty()) {
            return;
        }
        for (CustomSkinItem item : skinItemList) {
            item.switchSkin();
        }
    }
}
