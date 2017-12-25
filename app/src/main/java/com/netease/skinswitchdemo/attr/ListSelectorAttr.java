package com.netease.skinswitchdemo.attr;

import android.content.Context;
import android.view.View;
import android.widget.AbsListView;

public class ListSelectorAttr extends CustomizeAttr {

	public ListSelectorAttr(Context context) {
		super(context);
	}

	@Override
	public void switchSkin(View view) {
		if(view instanceof AbsListView) {
			AbsListView tv = (AbsListView)view;
			
			if(RES_TYPE_NAME_COLOR.equals(attrValueTypeName)) {
				tv.setSelector(resources.getColor(attrValueRefId));
			} else if(RES_TYPE_NAME_DRAWABLE.equals(attrValueTypeName)) {
				tv.setSelector(resources.getDrawable(attrValueRefId));
			}
		}
	}
}
