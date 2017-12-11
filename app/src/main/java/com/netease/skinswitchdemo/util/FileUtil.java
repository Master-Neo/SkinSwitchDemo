package com.netease.skinswitchdemo.util;

import android.os.Environment;
import android.util.Log;

import java.io.File;

/**
 * @author neo
 * @date 2017/11/8
 * Copyright 2017 NetEase. All rights reserved.
 */

public class FileUtil {

    private static final String ROOT_PATH_DIRECTORY = "skin_res";
    private static boolean sIsSDCardAvailable;
    private static final String PATH_SKIN_APK;

    static{
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            sIsSDCardAvailable = true;
        } else {
            sIsSDCardAvailable = false;
        }
        PATH_SKIN_APK = mkSDFilePath("skin.apk");
    }

    private static String mkSDFilePath(String filePath) {
        if(!sIsSDCardAvailable) {
            return null;
        }
        String path = null;
        File file = new File(Environment.getExternalStorageDirectory(), filePath);
        try {
            if (file.exists() && !file.isFile()) {
                File to = new File(file.getAbsolutePath() + System.currentTimeMillis());
                file.renameTo(to);
                to.delete();
            }
            path = file.getAbsolutePath();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return path;
    }

    public static String getSkinFilePath() {
        return PATH_SKIN_APK;
    }
}
