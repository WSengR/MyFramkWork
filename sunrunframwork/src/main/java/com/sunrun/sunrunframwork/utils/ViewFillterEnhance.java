package com.sunrun.sunrunframwork.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.text.Html;
import android.util.SparseArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @视图填充器加强
 */
public class ViewFillterEnhance {
    private SparseArray<View> mViews = new SparseArray<View>();// 建立id与视图对应关系,避免重复查找
    protected View rootView;// 根视图
    protected Activity viewOwmner;// 填充器

    public ViewFillterEnhance(View rootView) {
        this.rootView = rootView;
    }

    public ViewFillterEnhance(Activity viewOwmner) {
        this.viewOwmner = viewOwmner;
    }

    /**
     * 通过ViewId获取控件
     */

    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            if (rootView != null)
                view = rootView.findViewById(viewId);
            else if (viewOwmner != null)
                view = viewOwmner.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

//	// 填充视图
//	public void fillView(Object rootView, Object entity) {
//		Class<?> clazz = entity.getClass();
//		Field[] fields = clazz.getDeclaredFields();
//		for (Field field : fields) {
//			try {
//				if (field.isAnnotationPresent(ToView.class)) {
//					field.setAccessible(true);
//					ToView anno = field.getAnnotation(ToView.class);
//					int viewid = anno.value()[0];
//					String format = anno.format();
//					Object value = field.get(entity);
//					if (value != null)
//						setViewContent(viewid, String.format(format, value));// 值不为空时为视图对象赋值
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	}

    /**
     * @param id
     * @param value
     */
    public void setViewContent(int id, Object value) {
        View view = getView(id);
        if (value instanceof CharSequence) {
            if (view instanceof TextView) {
                setText(id, Html.fromHtml(String.valueOf(value)));
            }
            if (view instanceof ImageView){}
//				setImageURL(id, String.valueOf(value));
        } else if (value instanceof Integer) {
            if (view instanceof TextView)
                setText(id, (Integer) value);
            if (view instanceof ImageView)
                setImageResourse(id, ((Integer) value));
        }
    }

    public ViewFillterEnhance performClick(int viewId) {
        getView(viewId).performClick();
        return this;
    }

    public ViewFillterEnhance setOnClickListener(int viewId, OnClickListener lin) {
        getView(viewId).setOnClickListener(lin);
        return this;
    }

    public ViewFillterEnhance requestFocus(int viewId) {
        getView(viewId).requestFocus();
        return this;
    }

    public ViewFillterEnhance setBackgroundColor(int viewId, int color) {
        getView(viewId).setBackgroundColor(color);
        return this;
    }


    public ViewFillterEnhance setColor(int viewId, int color) {
        ((TextView) getView(viewId)).setTextColor(color);
        return this;
    }

    public ViewFillterEnhance setHintColor(int viewId, int color) {
        ((TextView) getView(viewId)).setHintTextColor(color);
        return this;
    }

    /**
     * 为TextView赋值
     */
    public ViewFillterEnhance setText(int viewId, CharSequence text) {
        ((TextView) getView(viewId)).setText(text);
        return this;
    }

    public ViewFillterEnhance setText(int viewId, int resId) {
        ((TextView) getView(viewId)).setText(resId);
        return this;
    }

    public ViewFillterEnhance setHintText(int viewId, int resId) {
        ((TextView) getView(viewId)).setHint(resId);
        return this;
    }

    public ViewFillterEnhance setHintText(int viewId, CharSequence text) {
        ((TextView) getView(viewId)).setText(text);
        return this;
    }

    /**
     * 为Imageview设置本地图片
     */
    public ViewFillterEnhance setImageResourse(int viewId, int resId) {
        ((ImageView) getView(viewId)).setImageResource(resId);
        return this;
    }

    /**
     * 为ImageView设置Bitmap
     */
    public ViewFillterEnhance setImageBitmap(int viewId, Bitmap bitmap) {
        ((ImageView) getView(viewId)).setImageBitmap(bitmap);
        return this;
    }

    public ViewFillterEnhance setCheck(int viewId, boolean isCheck) {
        ((CompoundButton) getView(viewId)).setChecked(isCheck);
        return this;
    }

    public ViewFillterEnhance setViewVisible(int viewid, boolean isVisible) {
        getView(viewid).setVisibility(isVisible ? View.VISIBLE : View.GONE);
        return this;
    }

    public ViewFillterEnhance setViewVisible(int viewid, int visible) {
        getView(viewid).setVisibility(visible);
        return this;
    }

    public ViewFillterEnhance startAnim(int viewId, Context context, int animRes) {
        return startAnim(viewId, AnimationUtils.loadAnimation(context, animRes));
    }

    public ViewFillterEnhance startAnim(int viewId, Animation anim) {
        getView(viewId).clearAnimation();
        getView(viewId).setVisibility(View.VISIBLE);
        getView(viewId).startAnimation(anim);
        return this;
    }

    public ViewFillterEnhance setTextColor(int viewId, int color) {
        ((TextView) getView(viewId)).setTextColor(color);
        return this;
    }

    public ViewFillterEnhance setTextColorId(int viewId, int colorId) {
        ((TextView) getView(viewId)).setTextColor(getResources().getColor(colorId));
        return this;
    }

    public ViewFillterEnhance setGravity(int viewId, int gravity) {
        ((TextView) getView(viewId)).setGravity(gravity);
        return this;
    }

    public ViewFillterEnhance addView(int viewGroupId, View view) {
        ((ViewGroup) getView(viewGroupId)).addView(view);
        return this;
    }

    public ViewFillterEnhance addView(int viewGroupId, View view, LayoutParams params) {
        ((ViewGroup) getView(viewGroupId)).addView(view, params);
        return this;
    }

    public ViewFillterEnhance removeView(int viewGroupId, View view) {
        ((ViewGroup) getView(viewGroupId)).removeView(view);
        return this;
    }

    public ViewFillterEnhance removeAllView(int viewGroupId) {
        ((ViewGroup) getView(viewGroupId)).removeAllViews();
        return this;
    }

    public Resources getResources() {
        if (viewOwmner != null)
            return viewOwmner.getResources();
        if (rootView != null)
            return rootView.getContext().getResources();
        return null;
    }
}
