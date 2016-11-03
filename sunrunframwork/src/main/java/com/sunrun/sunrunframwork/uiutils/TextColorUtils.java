package com.sunrun.sunrunframwork.uiutils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;


/**
 * 文字改变类
 */
public class TextColorUtils {
    /**
     * 设置 Text中间颜色
     *
     * @param textView         TextView
     * @param indexStartString 改变文字前面的文字
     * @param changeString     改变的文字
     * @param color            颜色
     */
    public static void setTextColorForIndex(TextView textView, String indexStartString, String changeString, @ColorInt int color) {
        SpannableStringBuilder builder = new SpannableStringBuilder(textView.getText().toString());
        //ForegroundColorSpan 为文字前景色，BackgroundColorSpan为文字背景色
        ForegroundColorSpan blueSpan = new ForegroundColorSpan(color);
        builder.setSpan(blueSpan, indexStartString.length(), indexStartString.length() + changeString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(builder);
    }

    /**
     * 设置drawable
     *
     * @param context
     * @param view
     * @param leftResId
     * @param topResId
     * @param rightResId
     * @param bottomResId
     */
    public static void setTextDrawables(Context context, TextView view, Integer leftResId, Integer topResId,
                                        Integer rightResId, Integer bottomResId) {
        if (leftResId != null) {
            Drawable img = context.getResources().getDrawable(leftResId);
            img.setBounds(0, 0, img.getMinimumWidth(), img.getMinimumHeight());
            view.setCompoundDrawables(img, null, null, null);
        } else if (topResId != null) {
            Drawable img = context.getResources().getDrawable(topResId);
            img.setBounds(0, 0, img.getMinimumWidth(), img.getMinimumHeight());
            view.setCompoundDrawables(null, img, null, null);
        } else if (rightResId != null) {
            Drawable img = context.getResources().getDrawable(rightResId);
            img.setBounds(0, 0, img.getMinimumWidth(), img.getMinimumHeight());
            view.setCompoundDrawables(null, null, img, null);
        } else if (bottomResId != null) {
            Drawable img = context.getResources().getDrawable(bottomResId);
            img.setBounds(0, 0, img.getMinimumWidth(), img.getMinimumHeight());
            view.setCompoundDrawables(null, null, null, img);
        }

//        view.setCompoundDrawablePadding(5);

    }

}
