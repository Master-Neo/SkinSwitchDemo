package com.netease.skinswitchdemo.attr;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

/**
 * @author neo
 * @date 2017/12/25
 * Copyright 2017 NetEase. All rights reserved.
 */

public class SrcAttr extends CustomizeAttr {

    public SrcAttr(Context context) {
        super(context);
    }

    @Override
    protected void switchSkin(View view) {
        if (view instanceof ImageView) {
            ImageView imageView = (ImageView) view;
            imageView.setImageDrawable(resources.getDrawable(attrValueRefId));
        }
    }
}
