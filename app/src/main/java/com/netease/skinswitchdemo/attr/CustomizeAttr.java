package com.netease.skinswitchdemo.attr;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;

import com.netease.skinswitchdemo.skin.SkinManager;

/**
 *
 */
public abstract class CustomizeAttr {
	
	protected static final String RES_TYPE_NAME_COLOR = "color";
	protected static final String RES_TYPE_NAME_DRAWABLE = "drawable";

	public CustomizeAttr(Context context) {
		this.context = context;
	}

	public Context context;
	public Resources resources;
	
	/**
	 * name of the attr, ex: background or textSize or textColor
	 */
	public String attrName;
	
	/**
	 * id of the attr value refered to, normally is [2130745655]
	 */
	public int attrValueRefId;
	
	/**
	 * entry name of the value , such as [bt_background]
	 */
	public String attrValueRefName;
	
	/**
	 * type of the value , such as color or drawable
	 */
	public String attrValueTypeName;

	public void apply(View view) {
		resources = SkinManager.getInstance(context).getResources();
		switchSkin(view);
	}

	/**
	 * @param view
	 */
	protected abstract void switchSkin(View view);
}
