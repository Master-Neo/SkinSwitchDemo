package com.netease.skinswitchdemo.attr;

import android.graphics.drawable.Drawable;
import android.view.View;

import com.netease.skinswitchdemo.MyApp;
import com.netease.skinswitchdemo.skin.SkinManager;

public class BackgroundAttr extends CustomizeAttr {

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
