package com.sunrun.sunrunframwork.utils;

import android.app.ActivityManager;
import android.content.Context;

import java.util.List;


/**
 * Created by Administrator on 2016/5/12.
 */
public class MemoryUtils {

    /**
     * 清除内存
     *
     * @param context
     * @return 清理内存并返回清理的数量
     */
    public static long clearMemory(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> infoList = am.getRunningAppProcesses();
        List<ActivityManager.RunningServiceInfo> serviceInfos = am.getRunningServices(100);
        long beforeMem = getAvailMemory(context);
        int count = 0;
        if (infoList != null) {
            for (int i = 0; i < infoList.size(); ++i) {
                ActivityManager.RunningAppProcessInfo appprocessInfo = infoList.get(i);
                if (appprocessInfo.importance > ActivityManager.RunningAppProcessInfo.IMPORTANCE_VISIBLE) {
                    String[] pkglist = appprocessInfo.pkgList;
                    for (int j = 0; j < pkglist.length; ++j) {
                        am.killBackgroundProcesses(pkglist[j]);
                        count++;
                    }
                }
            }
        }
        long afterMom = getAvailMemory(context);
       L.e("clear" + count + "process," + (afterMom - beforeMem) + "M");
        return afterMom - beforeMem;

    }

    /***
     * 获取可用内存的大小
     *
     * @param context
     * @return
     */
    public static long getAvailMemory(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo info = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(info);
        return info.availMem / (1024 * 1024);
    }


}
