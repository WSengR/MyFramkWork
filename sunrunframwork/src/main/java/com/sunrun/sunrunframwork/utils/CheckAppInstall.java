package com.sunrun.sunrunframwork.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

/**
 *  检测软件是否安装安装包
 */
public class CheckAppInstall {

    public static Boolean isWeiXinCheck(Context context) {
        if (checkApkExist(context, "com.tencent.mm")) {
            return true;
        }
        return false;
    }

    public static Boolean isZhifubao(Context context) {
        if (checkApkExist(context, "com.eg.android.AlipayGphone")) {
            return true;
        }
        return false;
    }

    public static Boolean isWeibo(Context context) {
        if (checkApkExist(context, "com.sina.weibo"))
            return true;
        return false;
    }

    public static Boolean isQQ(Context context) {
        if (checkApkExist(context, "com.tencent.mobileqq"))
            return true;
        return false;
    }


    /***
     * 通过包名检查客户端是否安装
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean checkApkExist(Context context, String packageName) {
        if (packageName == null || "".equals(packageName))
            return false;
        try {
            ApplicationInfo info = context.getPackageManager()
                    .getApplicationInfo(packageName,
                            PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }


}
