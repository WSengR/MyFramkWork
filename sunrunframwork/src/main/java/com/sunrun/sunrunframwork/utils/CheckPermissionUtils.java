package com.sunrun.sunrunframwork.utils;

import android.content.Context;
import android.content.pm.PackageManager;

import com.sunrun.sunrunframwork.uiutils.ToastHelper;


/**
 * @作者: Wang'sr
 * @时间: 2016/4/26 09:30
 * @功能描述:百度定位工具封装类
 */
public class CheckPermissionUtils {
    public static boolean checkVideoPermission(Context context) {
        if (!checkPermission(context, "android.permission.CAMERA") &&
                !checkPermission(context, "android.permission.RECORD_AUDIO")) {
            ToastHelper.showToast(context,"请打开拍照权限");
            return false;
        }
        if (!checkPermission(context, "android.permission.CAMERA")) {
//            JGToast.showToast(context.getString(R.string.Permission_camera));
            return false;
        }
        if (!checkPermission(context, "android.permission.RECORD_AUDIO")) {
//            JGToast.showToast(context.getString(R.string.Permission_audio));
            return false;
        }
        return true;
    }


    /***
     * 检查权限是否存在
     *
     * @param context
     * @param permissionName
     * @return
     */
    public static boolean checkPermission(Context context, String permissionName) {
        PackageManager pkm = context.getPackageManager();
        boolean has_permission = (PackageManager.PERMISSION_GRANTED ==
                pkm.checkPermission(permissionName, context.getPackageName()));
        return has_permission;
    }
}
