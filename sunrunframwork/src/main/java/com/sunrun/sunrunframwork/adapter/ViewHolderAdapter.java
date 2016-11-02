package com.sunrun.sunrunframwork.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * @带简单组合器的适配器
 * @param <T>
 *            数据源类型
 */
public abstract class ViewHolderAdapter<T> extends BaseAdapter {
	/** 数据源对象 */
	protected List<T> mData;
	/** 上下文对象 */
	protected Context mContext;
	protected int layoutId = -1;

	public ViewHolderAdapter(Context context, List<T> data, int layoutId) {
		this.mContext = context;
		this.layoutId = layoutId;
		setData(data);
	}

	public ViewHolderAdapter(Context context, int layoutId) {
		this(context, null, layoutId);
	}

	@Override
	public int getCount() {
		return mData.size();
	}

	@Override
	public T getItem(int position) {
		if (position >= getCount() ||position<0)
			return null;
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	public int getColor(int colorId){
		return mContext.getResources().getColor(colorId);
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHodler holder = null;
		if (convertView == null) {
			convertView = View.inflate(mContext, layoutId, null);
			if (convertView == null)
				new IllegalArgumentException("item 布局文件不存在 :" + layoutId);
			holder = new ViewHodler(convertView);
		} else {
			holder = (ViewHodler) convertView.getTag();
		}
		fillView(holder, getItem(position), position);
		return convertView;
	}

	/**
	 * 设置数据源对象
	 * 
	 * @param list
	 */
	public void setData(List<T> list) {
		if (list == null)
			list = new ArrayList<T>();
		mData = list;
	}

	/**
	 * 获取数据源对象
	 * 
	 * @return
	 */
	public List<T> getData() {
		return mData;
	}

	/**
	 * 填充数据到视图,由子类具体实现
	 * 
	 * @param holder
	 * @param mItem
	 * @param position
	 */
	public abstract void fillView(ViewHodler holder, T mItem, int position);
}
