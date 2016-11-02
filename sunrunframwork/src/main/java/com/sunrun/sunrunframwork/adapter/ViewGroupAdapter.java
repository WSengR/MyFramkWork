package com.sunrun.sunrunframwork.adapter;

import java.util.List;

import android.content.Context;
import android.view.ViewGroup;

public abstract class ViewGroupAdapter<T> extends ViewHolderAdapter<T> {

	ViewGroup mVg;
	public ViewGroupAdapter(Context context, ViewGroup mVg, List<T> data, int layoutId) {
		super(context, data, layoutId);
		this.mVg = mVg;
		init();
	}

	private void init() {
		mVg.removeAllViews();
		for (int i = 0, len = getCount(); i < len; i++) {
			mVg.addView(getView(i, null, mVg));
		}
	}
	
	@Override
	public void notifyDataSetChanged() {
		init();
	}


}
