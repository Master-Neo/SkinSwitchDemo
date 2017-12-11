package com.netease.skinswitchdemo.attr;

import android.view.View;
import android.widget.TextView;

public class TextColorAttr extends CustomizeAttr {

	@Override
	public void switchSkin(View view) {
		if(view instanceof TextView){
			TextView tv = (TextView)view;
			if(RES_TYPE_NAME_COLOR.equals(attrValueTypeName)){
				tv.setTextColor(resources.getColor(attrValueRefId));
			}
		}
	}
}
