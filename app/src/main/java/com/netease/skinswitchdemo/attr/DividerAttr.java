package com.netease.skinswitchdemo.attr;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.ListView;

public class DividerAttr extends CustomizeAttr {

	public int dividerHeight = 1;

	public DividerAttr(Context context) {
		super(context);
	}

	@Override
	public void switchSkin(View view) {
		if(view instanceof ListView){
			ListView tv = (ListView)view;
			if(RES_TYPE_NAME_COLOR.equals(attrValueTypeName)){
				int color = resources.getColor(attrValueRefId);
				ColorDrawable sage = new ColorDrawable(color);
				tv.setDivider(sage);
				tv.setDividerHeight(dividerHeight);
			}else if(RES_TYPE_NAME_DRAWABLE.equals(attrValueTypeName)){
				tv.setDivider(resources.getDrawable(attrValueRefId));
			}
		}
	}
}
