package com.sunrun.sunrunframwork.view.title;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sunrun.sunrunframwork.R;

/**
 * @作者: Wang'sr
 * @时间: 2016/11/2
 * @功能描述:顶部菜单的公共类
 */

public class BaseTitleLayoutView extends RelativeLayout {
    private View rootView;
    private Context mContext;

    private TextView tvLeft;
    private TextView tvRight;
    private ImageView ivLeftIcon;
    private ImageView ivRightIcon;
    private TextView tvTitle;

    private Drawable leftIconDrawable;
    private Drawable rightIconDrawable;
    private String leftText;
    private String rightText;
    private String titleText;
    private Boolean isShowRightImg;

    public BaseTitleLayoutView(Context context) {
        this(context, null);
    }

    public BaseTitleLayoutView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public BaseTitleLayoutView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        /**获取资源属性*/
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.BaseTitleLayoutView);
        leftIconDrawable = ta.getDrawable(R.styleable.BaseTitleLayoutView_TitlesLeftIcon);
        rightIconDrawable = ta.getDrawable(R.styleable.BaseTitleLayoutView_TitlesRightIcon);
        leftText = ta.getString(R.styleable.BaseTitleLayoutView_TitlesLeftTVText);
        rightText = ta.getString(R.styleable.BaseTitleLayoutView_TitlesRightTVText);
        titleText = ta.getString(R.styleable.BaseTitleLayoutView_TitlesText);
        isShowRightImg = ta.getBoolean(R.styleable.BaseTitleLayoutView_TitlesRightIconIsShow, false);
        ta.recycle();
        initView(context);
    }

    private void initView(Context context) {
        this.mContext = context;

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rootView = inflater.inflate(R.layout.include_title_view, null);
        if (rootView != null) {
            tvLeft = (TextView) rootView.findViewById(R.id.tv_left_text);
            tvRight = (TextView) rootView.findViewById(R.id.tv_right_text);
            ivLeftIcon = (ImageView) rootView.findViewById(R.id.iv_left_icon);
            ivRightIcon = (ImageView) rootView.findViewById(R.id.iv_right_icon);
            tvTitle = (TextView) rootView.findViewById(R.id.tv_title_text);
            addView(rootView);
        }

        setView();

    }

    /**
     * 设置值和参数
     */
    private void setView() {
        if (leftIconDrawable != null) {
            setLeftIconDrawable(leftIconDrawable);
        }
        if (rightIconDrawable != null) {
            setRightIconDrawable(rightIconDrawable);
        }
        if (rightText != null && rightText.length() > 0) {
            setRightText(rightText);
        }
        if (leftText != null && leftText.length() > 0) {
            setLeftText(leftText);
        }
        if (titleText != null && titleText.length() > 0) {
            setTitleText(titleText);
        }


        /**扩展设置公共监听*/
        getIvLeftIcon().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("TitleView", "OnClickListener");
                InputMethodManager imm = (InputMethodManager) mContext
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0); // 强制隐藏键盘
                ((Activity) mContext).finish();
//                ((Activity) mContext).overridePendingTransition(
//                        R.anim.slide_in_situ, R.anim.push_right_out);
            }
        });

    }

    /**
     * -------------------- Get View 方便扩展 ----------------
     */

    public TextView getTvLeft() {
        return tvLeft;
    }

    public TextView getTvRight() {
        return tvRight;
    }

    public ImageView getIvLeftIcon() {
        return ivLeftIcon;
    }

    public ImageView getIvRightIcon() {
        return ivRightIcon;
    }

    public TextView getTvTitle() {
        return tvTitle;
    }

    /**
     * -------------------- set View 方便扩展 ----------------
     */

    public void setLeftIconDrawable(Drawable leftIconDrawable) {
        this.leftIconDrawable = leftIconDrawable;
        getIvLeftIcon().setImageDrawable(leftIconDrawable);
    }

    public void setRightIconDrawable(Drawable rightIconDrawable) {
        this.rightIconDrawable = rightIconDrawable;
        getIvRightIcon().setImageDrawable(rightIconDrawable);
        getIvRightIcon().setVisibility(VISIBLE);
    }

    public void setLeftText(String leftText) {
        this.leftText = leftText;
        getTvLeft().setText(leftText);
    }

    public void setRightText(String rightText) {
        this.rightText = rightText;
        getTvRight().setText(rightText);
        getTvRight().setVisibility(VISIBLE);
        setIsShowRightImg(false);
    }

    public void setTitleText(String titleText) {
        this.titleText = titleText;
        getTvTitle().setText(titleText);
    }

    public void setIsShowRightImg(Boolean isShowRightImg) {
        this.isShowRightImg = isShowRightImg;
        getIvRightIcon().setVisibility(isShowRightImg ? VISIBLE : GONE);
    }
}
