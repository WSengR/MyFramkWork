package com.sunrun.sunrunframwork.view.title;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sunrun.sunrunframwork.R;

/**
 * @类名: TitleLayout.java
 * @功能描述: 顶部TitleBar
 * @作者 Wangsr
 * @时间 2015-9-11 下午2:06:28
 * @创建版本 V3.0
 */
@SuppressLint("InflateParams")
public class TitleLayout extends RelativeLayout  {
    private RelativeLayout topLayout;// 顶部栏layout
    private ImageView backImageView, rightImageView;// 返回键,右边图片按钮
    private TextView titleTextView;// 标题,右边文字按钮
    private Context context;// 上下文对象
    private View view;// 自定义布局

    public TitleLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public TitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TitleLayout(Context context) {
        super(context);
        init(context);
    }

    /**
     * 初始化
     */
    private void init(Context context) {
        this.context = context;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.title_bar, null);
        if (view != null) {
            topLayout = (RelativeLayout) view.findViewById(R.id.title_layout);
            backImageView = (ImageView) view.findViewById(R.id.title_left);
            titleTextView = (TextView) view.findViewById(R.id.title_text);
            rightImageView = (ImageView) view.findViewById(R.id.title_right);

            addView(view);
            DisplayMetrics display = new DisplayMetrics();
            ((Activity) context).getWindowManager().getDefaultDisplay()
                    .getMetrics(display);
            int width = display.widthPixels;
            LayoutParams linearParams = (LayoutParams) topLayout
                    .getLayoutParams();
            linearParams.width = width;
            topLayout.setLayoutParams(linearParams);
        }
    }


    public RelativeLayout getTopLayout() {
        return topLayout;
    }

    public void setTopLayout(RelativeLayout topLayout) {
        this.topLayout = topLayout;
    }

    public ImageView getBackImageView() {
        return backImageView;
    }

    public void setBackImageView(ImageView backImageView) {
        this.backImageView = backImageView;
    }

    public TextView getTitleTextView() {
        return titleTextView;
    }

    public void setTitleTextView(TextView titleTextView) {
        this.titleTextView = titleTextView;
    }

    public ImageView getRightImageView() {
        return rightImageView;
    }

    public void setRightImageView(ImageView rightImageView) {
        this.rightImageView = rightImageView;
    }

}
