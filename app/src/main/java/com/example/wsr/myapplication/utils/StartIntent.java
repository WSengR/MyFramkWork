package com.example.wsr.myapplication.utils;

import android.app.Activity;
import android.content.Intent;

import com.example.wsr.myapplication.activity.MyActivity;
import com.example.wsr.myapplication.activity.TestPageActivity;

/**
 * @作者: Wang'sr
 * @时间: 2016/10/31
 * @功能描述: 启动其他Activity 统一入口 请给每个方法写上详尽的注释
 */

public class StartIntent {

    /**
     * 启动到我的Activity   MyActivity
     * @param activity
     */
    public static void startMyActivity(Activity activity) {
        Intent intent = new Intent(activity, MyActivity.class);
        activity.startActivity(intent);
    }

    public static void startTestPageActivity(Activity activity) {
        Intent intent = new Intent(activity, TestPageActivity.class);
        activity.startActivity(intent);
    }
}
