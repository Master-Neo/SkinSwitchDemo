package com.netease.skinswitchdemo.skin;

import android.annotation.TargetApi;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.netease.skinswitchdemo.MyApp;
import com.netease.skinswitchdemo.util.FileUtil;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author neo
 * @date 2017/11/8
 * Copyright 2017 NetEase. All rights reserved.
 */

public class CustomResources extends Resources {

    private Resources mSkinResources;
    private final String mDexPath = FileUtil.getSkinFilePath();

    public CustomResources(Resources resources) {
        super(resources.getAssets(), resources.getDisplayMetrics(), resources.getConfiguration());
        try {
            if (TextUtils.isEmpty(mDexPath)) {
                return;
            }
            File file = new File(mDexPath);
            if (!file.exists() || !file.isFile()) {
                return;
            }
            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetPath = assetManager.getClass().getMethod("addAssetPath", String.class);
            addAssetPath.invoke(assetManager, mDexPath);
            mSkinResources = new Resources(assetManager, resources.getDisplayMetrics(), resources.getConfiguration());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    private int getSkinResourceId(int resId) {
        if (mSkinResources == null) {
            return 0;
        }

        PackageManager pm = MyApp.getInstance().getPackageManager();
        /**
         * 当6.0以上手机未申请动态权限时，pkInfo返回为null
         */
        PackageInfo pkInfo = pm.getPackageArchiveInfo(mDexPath, PackageManager.GET_ACTIVITIES);

        String resourceName = getResourceName(resId);
        String packageName = MyApp.getInstance().getPackageName();
        String skinResourceName = resourceName.replace(packageName, pkInfo.packageName);
        int skinResourceId = mSkinResources.getIdentifier(skinResourceName, null, null);
        return skinResourceId;
    }

    @Nullable
    @Override
    public Drawable getDrawable(int id) throws NotFoundException {
        int skinResourceId = getSkinResourceId(id);
        if (skinResourceId == 0) {
            return super.getDrawable(id);
        } else {
            return mSkinResources.getDrawable(skinResourceId);
        }
    }

    @Override
    public int getColor(int id) throws NotFoundException {
        int skinResourceId = getSkinResourceId(id);
        if(skinResourceId == 0) {
            return super.getColor(id);
        } else {
            return mSkinResources.getColor(skinResourceId);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public int getColor(int id, Theme theme) throws NotFoundException {
        int skinResourceId = getSkinResourceId(id);
        if (skinResourceId == 0) {
            return super.getColor(id, theme);
        } else {
            return mSkinResources.getColor(skinResourceId, theme);
        }
    }

    @Override
    public int getDimensionPixelOffset(int id) throws NotFoundException {
        int skinResourceId = getSkinResourceId(id);
        if (skinResourceId == 0) {
            return super.getDimensionPixelOffset(id);
        } else {
            return mSkinResources.getDimensionPixelOffset(skinResourceId);
        }
    }

    @Override
    public int getDimensionPixelSize(int id) throws NotFoundException {
        int skinResourceId = getSkinResourceId(id);
        if (skinResourceId == 0) {
            return super.getDimensionPixelSize(id);
        } else {
            return mSkinResources.getDimensionPixelOffset(skinResourceId);
        }
    }

    @Override
    public float getDimension(int id) throws NotFoundException {
        int skinResourceId = getSkinResourceId(id);
        if (skinResourceId == 0) {
            return super.getDimension(id);
        } else {
            return mSkinResources.getDimension(skinResourceId);
        }
    }

    /**
     * 加载指定资源颜色drawable,转化为ColorStateList，保证selector类型的Color也能被转换
     * 无皮肤包资源返回默认主题颜色
     * @param resId
     */
    public ColorStateList convertToColorStateList(int resId) {
        int skinResourceId = getSkinResourceId(resId);
        if (skinResourceId == 0) {
            ColorStateList trueColorList;
            try {
                trueColorList = mSkinResources.getColorStateList(skinResourceId);
                return trueColorList;
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        } else {
            try {
                ColorStateList originColorList = super.getColorStateList(resId);
                return originColorList;
            } catch (NotFoundException e) {
                e.printStackTrace();
            }

        }

        int[][] states = new int[1][1];
        return new ColorStateList(states, new int[] { mSkinResources.getColor(resId) });
    }
}
