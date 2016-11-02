package com.sunrun.sunrunframwork.animation;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @作者: Wang'sr
 * @时间: 2016/10/24
 * @功能描述:动画工具类
 */
public class AnimationUtils {

	/**
	 * 执行添加到收藏的动画
	 * 
	 * @param act
	 *            上下文
	 * @param drawable
	 *            图片资源
	 * @param startView
	 *            起始位置
	 * @param endView
	 *            结束位置
	 */
	public static void statFootAddAnimation(Activity act, Drawable drawable, View startView, View endView) {
		FootAddAnimation.statFootAddAnimation(act, drawable, startView, endView);

	}

	/**
	 * 执行公告通知的动画
	 * @param tvNotice   动画的textView
	 * @param datas	             数据 String[]
	 */
//	public static void startTextNoticeAnimation(TextView tvNotice, String[] datas) {
//
//	}

}
