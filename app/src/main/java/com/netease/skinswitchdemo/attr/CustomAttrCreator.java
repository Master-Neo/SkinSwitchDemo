package com.netease.skinswitchdemo.attr;

import android.content.Context;

import com.netease.skinswitchdemo.skin.SkinManager;

import java.util.Arrays;
import java.util.List;

/**
 * @author neo
 * @date 2017/12/4
 * Copyright 2017 NetEase. All rights reserved.
 */

public class CustomAttrCreator {

    public static final String BACKGROUND = "background";
    public static final String TEXT_COLOR = "textColor";
    public static final String LIST_SELECTOR = "listSelector";
    public static final String DIVIDER = "divider";
    public static final List<String> SUPPORT_ATTRS = Arrays.asList(new String[]{BACKGROUND, TEXT_COLOR, LIST_SELECTOR, DIVIDER});

    public static CustomizeAttr create(Context context, String attrName, int attrValueRefId, String attrValueRefName, String typeName) {
        CustomizeAttr attr = null;

        switch (attrName) {
            case BACKGROUND:
                attr = new BackgroundAttr(context);
                break;
            case TEXT_COLOR:
                attr = new TextColorAttr(context);
                break;
            case LIST_SELECTOR:
                attr = new ListSelectorAttr(context);
                break;
            case DIVIDER:
                attr = new DividerAttr(context);
                break;
            default:
                return null;
        }

        attr.resources = SkinManager.getInstance(context).getResources();

        attr.attrName = attrName;
        attr.attrValueRefId = attrValueRefId;
        attr.attrValueRefName = attrValueRefName;
        attr.attrValueTypeName = typeName;

        return attr;
    }

    public static boolean isSupportedAttr(String attrName) {
        if (SUPPORT_ATTRS.contains(attrName)) {
            return true;
        }
        return false;
    }
}
