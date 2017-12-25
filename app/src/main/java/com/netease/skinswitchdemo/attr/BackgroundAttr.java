package com.netease.skinswitchdemo.attr;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;

public class BackgroundAttr extends CustomizeAttr {

	public BackgroundAttr(Context context) {
		super(context);
	}

	@Override
	public void switchSkin(View view) {
		if(RES_TYPE_NAME_COLOR.equals(attrValueTypeName)){
			view.setBackgroundColor(resources.getColor(attrValueRefId));
		}else if(RES_TYPE_NAME_DRAWABLE.equals(attrValueTypeName)){
			Drawable bg = resources.getDrawable(attrValueRefId);
			view.setBackground(bg);
		}
	}
}
