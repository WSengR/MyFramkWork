/**
 * 
 */
package com.example.wsr.myapplication.quest;


import com.sunrun.sunrunframwork.http.NAction;
import com.sunrun.sunrunframwork.uibase.BaseActivity;
import com.sunrun.sunrunframwork.uiutils.UIUtils;

/**
 * @作者: Wang'sr
 * @时间: 2016年10月26日
 * @功能描述: 网络请求 执行开始
 * @version V1.0
 */
public class BaseQuestStart extends BaseQuestConfig {

	/**
	 * 请求导航栏
	 * @param baseActivity (必须继承BaseActivity)
	 */
	public static void bannerQuest(BaseActivity baseActivity) {
		UIUtils.showLoadDialog(baseActivity, "加载中..");
		NAction homeAction = Config.getUidNAction().setRequestCode(QUEST_BANNER_CODE).setUseCache().setUrl(BaseQuestConfig.bannerURl);
		baseActivity.requestAsynPost(homeAction);
	}
}
