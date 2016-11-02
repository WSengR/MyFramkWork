package com.sunrun.sunrunframwork.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 不带滑动的ViewPage
 * @author Ren
 *
 */
public class NoToutchViewPage extends ViewPager {

	private boolean enabled;
	
	public NoToutchViewPage(Context context) {
		super(context);
		this.enabled = false;

	}

	public NoToutchViewPage(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.enabled = false;

	}

	

	// 触摸没有反应就可以了
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (this.enabled) {
			return super.onTouchEvent(event);
		}

		return false;
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent event) {
		if (this.enabled) {
			return super.onInterceptTouchEvent(event);
		}
		return false;
	}

	public void setPagingEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
