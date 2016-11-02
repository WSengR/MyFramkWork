/**
 * 
 */
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

/**
 * @作者: Wang'sr
 * @时间: 2016年10月25日
 * @功能描述:   物品添加的动画
 * @version V1.0
 */
public class FootAddAnimation {

	private static int AnimationDuration = 1000;
	/**
	 * 执行添加到收藏的动画
	 * @param act   上下文
	 * @param drawable   图片资源
	 * @param startView  起始位置
	 * @param endView    结束位置
	 */
	public static void statFootAddAnimation(Activity act, Drawable drawable,
			 View startView, View endView) {
		try {
			int[] start_location = new int[2];
			startView.getLocationInWindow(start_location);// 获取点击商品图片的位置
			int[] end_location = new int[2];
			endView.getLocationInWindow(end_location);
			setAnim(act, drawable,start_location, end_location);
		} catch (Exception e) {
			e.printStackTrace(); 
		} finally {
		
		}
	}

	private static int dip2px(Context context, float dpValue) {
		float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 设置动画
	 * @param act
	 * @param drawable
	 * @param start_location
	 * @param end_location
	 */
	private static void setAnim(Activity act, Drawable drawable,
			int[] start_location, int[] end_location) {
		final FrameLayout animation_viewGroup = createAnimLayout(act);
		animation_viewGroup.removeAllViews();
		Animation mScaleAnimation = new ScaleAnimation(1.5f, 0.0f, 1.5f, 0.0f,
				Animation.RELATIVE_TO_SELF, 0.1f, Animation.RELATIVE_TO_SELF,
				0.1f);
		mScaleAnimation.setDuration(AnimationDuration);
		mScaleAnimation.setFillAfter(true);

		final ImageView iview = new ImageView(act);
		iview.setImageDrawable(drawable);
		final View view = addViewToAnimLayout(act, animation_viewGroup, iview,
				start_location);
		view.setAlpha(0.6f);

		int endX = end_location[0] - start_location[0];
		int endY = end_location[1] - start_location[1];

		Animation mTranslateAnimation = new TranslateAnimation(0, endX, 0, endY);
		Animation mRotateAnimation = new RotateAnimation(0, 180,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		mRotateAnimation.setDuration(AnimationDuration);
		mTranslateAnimation.setDuration(AnimationDuration);
		AnimationSet mAnimationSet = new AnimationSet(true);

		mAnimationSet.setFillAfter(true);
		mAnimationSet.addAnimation(mRotateAnimation);
		mAnimationSet.addAnimation(mScaleAnimation);
		mAnimationSet.addAnimation(mTranslateAnimation);
		view.startAnimation(mAnimationSet);

	}

	/*
	 * 创建动画视图
	 */
	private static FrameLayout createAnimLayout(Activity act) {
		ViewGroup rootView = (ViewGroup) act.getWindow().getDecorView();
		FrameLayout animLayout = new FrameLayout(act);
		FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.MATCH_PARENT,
				FrameLayout.LayoutParams.MATCH_PARENT);
		animLayout.setLayoutParams(lp);
		animLayout.setBackgroundResource(android.R.color.transparent);
		rootView.addView(animLayout);
		return animLayout;

	}
	
	/*
	 * 添加试图到动画视图中
	 */
	private static View addViewToAnimLayout(Activity activity, ViewGroup vg,
			View view, int[] location) {
		int x = location[0];
		int y = location[1];
		vg.addView(view);
		FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(dip2px(
				activity, 90), dip2px(activity, 90));
		lp.leftMargin = x;
		lp.topMargin = y;
		view.setPadding(5, 5, 5, 5);
		view.setLayoutParams(lp);
		return view;
	}
}
