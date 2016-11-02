package com.example.wsr.myapplication.quest;

import com.sunrun.sunrunframwork.http.quest.NetQuestConfig;

/**
 * @作者: Wang'sr
 * @时间: 2016/10/31
 * @功能描述:
 */

public class BaseQuestConfig implements NetQuestConfig {

    public static String bodyHttp = "http://test.cnsunrun.com/qbk/App/";


    public static String bannerURl = bodyHttp + "Home/Find/get_home_recommend";
    public static final int QUEST_BANNER_CODE = 0X0012;

    public static String bodyURL = bodyHttp + "Home/Find/index";
    public static final int QUEST_BODY_CODE = 0X0013;
    //    发现页分类:
    public static String daoHangURL = bodyHttp + "User/Publish/publish_special";
    public static final int QUEST_DAOHANG_CODE = 0X0014;
}
