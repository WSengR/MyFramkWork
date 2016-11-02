package com.sunrun.sunrunframwork.common;
import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @类名: ViewHolder.java
 * @功能描述: 万能 ViewHolder
 * @作者 Wangsr
 * @时间 2015-9-7 下午2:31:50
 * @创建版本 V3.0
 */
public class ViewHolder {

	/** Key 为 int 的 map 效率更高*/
	private SparseArray<View> mViews;
	private int mPosition;
	private View mConvertView;

	public int getPosition() {
		return mPosition;
	}

	public void setPosition(int mPosition) {
		this.mPosition = mPosition;
	}

	private ViewHolder(Context context, ViewGroup parent, int layoutId,
			int position) {

		this.mPosition = position;
		this.mViews = new SparseArray<View>();
		mConvertView = LayoutInflater.from(context).inflate(layoutId, parent,
				false);
		mConvertView.setTag(this);
	}

	/**
	 *
	 * @功能描述: 提供外界初始化入口
	 * @param context
	 * @param convertView
	 * @param parent
	 * @param layoutId
	 * @param position
	 * @return ViewHolder对象
	 */

	public static ViewHolder get(Context context, View convertView,
			ViewGroup parent, int layoutId, int position) {
		if (convertView == null) {
			return new ViewHolder(context, parent, layoutId, position);
		} else {
			ViewHolder holder = (ViewHolder) convertView.getTag();
			holder.mPosition = position;
			return holder;
		}

	}

	/**
	 *
	 * @功能描述: 通过viewId获取控件
	 * @param viewId
	 * @return view
	 */

	public <T extends View> T getView(int viewId) {
		View view = mViews.get(viewId);
		if (view == null) {
			view = mConvertView.findViewById(viewId);
			mViews.put(viewId, view);
		}
		return (T) view;
	}

	public View getConvertView() {
		return mConvertView;
	}

	/**
	 *
	 * @功能描述: 为TextView设置值
	 * @param viewId
	 *            布局Id
	 * @param text
	 *            设置值
	 * @return 本类对象（基于链式编程）
	 */
	public ViewHolder setText(int viewId, String text) {
		TextView tv = getView(viewId);
		tv.setText(text);
		return this;
	}

	/**
	 *
	 * @功能描述: 为ImageView设置值
	 * @param viewId
	 *            布局Id
	 * @param resId
	 * @return 本类对象（基于链式编程）
	 */
	public ViewHolder setImageResource(int viewId, int resId) {
		ImageView img = getView(viewId);
		img.setImageResource(resId);
		return this;
	}

	/**
	 *
	 * @功能描述: 为CheckBox设置值
	 * @param viewId
	 *            布局Id
	 * @param resId
	 * @return 本类对象（基于链式编程）
	 */

	public ViewHolder setChecked(int viewId, Boolean checked) {
		CheckBox cb = getView(viewId);
		cb.setChecked(checked);
		return this;
	}

}
