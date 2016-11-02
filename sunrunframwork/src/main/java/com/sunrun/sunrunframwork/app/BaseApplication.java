package com.sunrun.sunrunframwork.app;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;


import com.sunrun.sunrunframwork.http.utils.Logger;
import com.sunrun.sunrunframwork.uibase.BaseFragment;

import java.util.LinkedList;

/**
 * @用于程序启动时的初始化操作,和Activity的管理
 */
public class BaseApplication extends Application {
    private LinkedList<Activity> activityList = null;// 已启动的Activity集合
    private LinkedList<BaseFragment> fragments = null;// 已加载的Fragment集合

    private static BaseApplication _application;// 全局上下文对象

    @Override
    public void onCreate() {
        super.onCreate();
        _application = this;
        String processName = getCurProcessName(this);
        Logger.E(processName + "    " + getPackageName());
        if (getPackageName().equals(processName)) {// 判断当前Application实例是否属于App基础进程
            activityList = new LinkedList<Activity>();
            fragments = new LinkedList<BaseFragment>();
        }
    }

    /**
     * 获取当前进程名字
     *
     * @param context
     * @return
     */
    private String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager mActivityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

    /**
     * 获得当前程序全局上下文实例
     *
     * @return
     */
    public static BaseApplication getInstance() {
        if (_application == null)
            throw new RuntimeException("BaseApplication 未被正常初始化!");
        return _application;
    }

    /**
     * 启动Activity(FLAG_ACTIVITY_NEW_TASK 方式)
     *
     * @param cls
     */
    public void startAct(Class<?> cls) {
        Intent intent = new Intent(BaseApplication.getInstance(), cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    // ---------------------------------------Activity和Fragment管理相关方法-------------------------------------

    /**
     * 获取当前正在栈顶的Activity 字节码文件
     *
     * @return
     */
    public Class<?> getCurrentAct() {
        return activityList.size() == 0 ? null : activityList.getLast().getClass();
    }

    /**
     * 添加Activity到管理栈中,方便统一管理
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        if (activity != null && !activityList.contains(activity))
            activityList.add(activity);
    }

    /**
     * 从栈中移除Activity
     *
     * @param activity
     */
    public void removeActivity(Activity activity) {
        if (activityList.size() != 0)
            activityList.remove(activity);
    }

    /**
     * 将Fragment加入栈中以便于管理
     *
     * @param fragment
     */
    public void addFragment(BaseFragment fragment) {
        fragments.add(fragment);
    }

    public void removeFragment(BaseFragment fragment) {
        fragments.remove(fragment);
    }

}
