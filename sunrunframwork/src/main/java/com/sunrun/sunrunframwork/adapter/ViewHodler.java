package com.sunrun.sunrunframwork.adapter;

import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.util.SparseArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;


import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.sunrun.sunrunframwork.utils.ImageLoadOptions;


/**
 * @author WQ
 * @通用组合器
 */
public class ViewHodler {
	private SparseArray<View> mViews;// 存放convertview中的组件[视图集会被创建多份,待优化]
	private View mConvertView;
	private DisplayImageOptions options;
	private DisplayImageOptions options_Y;
	private AnimationDrawable animationDrawable;
	private int shortId=-1;
	public ViewHodler(View convertView) {
		this.mViews = new SparseArray<View>();
		this.mConvertView = convertView;
		mConvertView.setTag(this);
		options = ImageLoadOptions.getExactlyOption();
		options_Y = ImageLoadOptions.getExactlyOption_Y();
	}
	
	void updateShortId(int id){
		shortId=id;
	}
	public <T> T choice(boolean status,T a,T b){
		return status?a:b;
	}
	public <T> T choice(int status,T ... bs){
		return bs[status];
	}
	/**
	 * 通过ViewId获取控件
	 */
	@SuppressWarnings("unchecked")
	public <T extends View> T getView(int viewId) {
		updateShortId(viewId);
		View view = mViews.get(viewId);
		if (view == null) {
			view = mConvertView.findViewById(viewId);
			mViews.put(viewId, view);
		}
		return (T) view;
	}

	/**
	 * 获取ConvertView
	 * 
	 * @return
	 */
	public View getmConvertView() {
		return mConvertView;
	}

	/**
	 * 为TextView赋值
	 */
	public ViewHodler setText(int viewId, CharSequence text) {
		TextView tv = getView(viewId);
		if (tv != null)
			tv.setText(text);
		return this;
	}

	/**
	 * 设置TextView 色值
	 */
	public ViewHodler setTextColor(int viewId, int color) {
		TextView tv = getView(viewId);
		if (tv != null)
		tv.setTextColor(color);
		return this;
	}
	
	/**
	 * 设置颜色Id
	 * @param viewId
	 * @param colorId
	 * @return
	 */
	public ViewHodler setTextColorId(int viewId,int colorId){
		TextView tv = getView(viewId);
		if (tv != null)
		tv.setTextColor(getColor(colorId));
		return this;
	}
	
	public int getColor(int colorId){
		return mConvertView.getResources().getColor(colorId);
	}

	/**
	 * 设置布局动画
	 */
	public ViewHodler setAnimation(int viewId, int anim) {
		ImageView vw = getView(viewId);
		vw.setBackgroundResource(anim);
		animationDrawable = (AnimationDrawable) vw.getBackground();
		animationDrawable.start();
		return this;
	}

	/**
	 * 关闭布局动画
	 */
	public ViewHodler stopAnimation() {
		animationDrawable.stop();
		return this;
	}

	// 创建帧动画并开始

	/**
	 * 为Imageview设置本地图片
	 */
	public ViewHodler setImageResourse(int viewId, int resId) {
		ImageView view = getView(viewId);
		view.setImageResource(resId);
		return this;
	}

	/**
	 * 为ImageView设置Bitmap
	 * 
	 */
	public ViewHodler setImageBitmap(int viewId, Bitmap bitmap) {
		ImageView view = getView(viewId);
		view.setImageBitmap(bitmap);
		return this;
	}

	/**
	 * 设置CheckBox选中状态
	 * 
	 * @param viewId
	 * @param isCheck
	 * @return
	 */
	public ViewHodler setCheck(int viewId, boolean isCheck) {
		CompoundButton cb = getView(viewId);
		cb.setChecked(isCheck);
		return this;
	}

	/**
	 * 为ImageView设置网络图片
	 */
	public ViewHodler setImageURL(int viewId, String url) {
		ImageView view = getView(viewId);
		ImageLoader.getInstance().displayImage(url, view, options);
		return this;
	}

	/**
	 * 为ImageView设置网络图片
	 */
	public ViewHodler setImageURL(int viewId, String url,
			DisplayImageOptions options) {
		ImageView view = getView(viewId);
		ImageLoader.getInstance().displayImage(url, view, options);
		return this;
	}

	/**
	 * 为ImageView设置网络圆图片
	 */
	public ViewHodler setImageURL_Y(int viewId, String url) {
		ImageView view = getView(viewId);
		ImageLoader.getInstance().displayImage(url, view, options_Y);
		return this;
	}

	/**
	 * 
	 */
	public ViewHodler setBackgroundResource(int viewId, int resid) {
		View view = getView(viewId);
		view.setBackgroundResource(resid);
		return this;
	}

	public ViewHodler setBackgroundcolor(int viewId, int resid) {
		View view = getView(viewId);
		view.setBackgroundColor(resid);
		return this;
	}

	public ViewHodler setClickListener(int viewId, OnClickListener lin) {
		View view = getView(viewId);
		if (view != null)// 不为空
			view.setOnClickListener(lin);
		return this;
	}

	/**
	 * 隐藏控件
	 */
	public ViewHodler setVisibility(int viewId, int b) {
		View view = getView(viewId);
		view.setVisibility(b);
		return this;
	}

	/**
	 * 设置视图显示状态
	 * 
	 * @param viewid
	 * @param isVisible
	 * @return
	 */
	public ViewHodler setVisibility(int viewid, boolean isVisible) {
		getView(viewid).setVisibility(isVisible ? View.VISIBLE : View.GONE);
		return this;
	}
	
	public ViewHodler setShow(boolean isShow){
		setVisibility(shortId, isShow);
		return this;
	}
}
