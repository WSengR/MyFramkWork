package com.sunrun.sunrunframwork.adapter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

/**
 * @author WQ 复杂事件适配器
 * @param <T>数据源类型
 */
public abstract class ComplexEventAdapter<T> extends ViewHolderAdapter<T> implements OnClickListener {
	protected Set<Integer> clicklisteners = null;
	protected List<OnItemChildClickListener<T>> onItemChildClickListeners;// 子控件监听集合

	public ComplexEventAdapter(Context context, int layoutId) {
		this(context, null, layoutId);
	}

	public ComplexEventAdapter(Context context, List<T> data, int layoutId) {
		super(context, data, layoutId);
	}

	/**
	 * 为指定id的控件添加点击事件监听
	 * 
	 * @不要更改设置过点击事件监听的组件的Tag值,这会使得添加的监听失效
	 * @param ids
	 */
	public void addClickListener(int... ids) {
		if (clicklisteners == null)
			clicklisteners = new HashSet<Integer>();
		for (int id : ids)
			clicklisteners.add(id);
	}

	/**
	 * 设置item中子控件的点击事件监听
	 * 
	 * @param onItemChildClickListener
	 */
	public void addOnItemChildClickListener(OnItemChildClickListener<T> onItemChildClickListener) {
		if (onItemChildClickListeners == null)
			onItemChildClickListeners = new ArrayList<OnItemChildClickListener<T>>();
		if (!onItemChildClickListeners.contains(onItemChildClickListener))
			onItemChildClickListeners.add(onItemChildClickListener);
	}

	/**
	 * item 中子组件点击事件回调接口
	 */
	public interface OnItemChildClickListener<T> {
		/**
		 * 子组件点击事件回调方法
		 * 
		 * @param position
		 *            所在item的位置
		 * @param data
		 *            数据源
		 * @param v
		 *            被点击的组件
		 */
		public void onItemChildClick(int position, List<T> data, T mItem, View v);
	}

	/**
	 * @子组件点击事件的内部响应方法 并通过用户注册的子组件点击事件回调接口进行事件派发
	 */
	@Override
	final public void onClick(View v) {
		int position = (Integer) v.getTag();
		for (OnItemChildClickListener<T> onItemChildClickListener : onItemChildClickListeners)
			onItemChildClickListener.onItemChildClick(position, mData, mData.get(position), v);
	}

	/**
	 * 绑定监听的方法
	 * 
	 * @param hv
	 * @param position
	 */
	protected void bindListener(ViewHodler contentView, int position) {
		if (onItemChildClickListeners != null && clicklisteners != null) {
			for (Integer id : clicklisteners) {
				View v = contentView.getView(id);
				v.setOnClickListener(this);
				v.setTag(position);
			}
		}
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHodler holder = (ViewHodler) super.getView(position, convertView, parent).getTag();
		bindListener(holder, position);// 绑定监听
		return holder.getmConvertView();
	}
}
