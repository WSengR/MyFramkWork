package com.example.wsr.myapplication.bean;

import android.app.Activity;

/**
 * @作者: Wang'sr
 * @时间: 2016/11/14
 * @功能描述:
 */

public class StartActivityBean {
    private String activityName;//Activity
    private String aicitivyDis;//描述
    private Class<Activity> startAtivity;//要启动的Activity

    public StartActivityBean(String activityName, String aicitivyDis, Class startAtivity) {
        this.activityName = activityName;
        this.aicitivyDis = aicitivyDis;
        this.startAtivity = startAtivity;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getAicitivyDis() {
        return aicitivyDis;
    }

    public void setAicitivyDis(String aicitivyDis) {
        this.aicitivyDis = aicitivyDis;
    }

    public Class getStartAtivity() {
        return startAtivity;
    }

    public void setStartAtivity(Class startAtivity) {
        this.startAtivity = startAtivity;
    }



}
