package com.example.wsr.myapplication.view;


import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wsr.myapplication.R;


/**
 * Created by ZM on 2015/10/23.
 * 自定义底部状态栏中的按钮
 */
public class PracticeButtomButton extends RelativeLayout {


    TextView tv;
    View lineView;
    private boolean isSelect = false;

    public boolean isSelect() {
        return isSelect;
    }

    public void setIsSelect(boolean isSelect) {
        this.isSelect = isSelect;
        if (isSelect) {
            tv.setTextColor(getResources().getColor(R.color.color_top_text_blue));
            lineView.setVisibility(VISIBLE);
        } else {
            tv.setTextColor(getResources().getColor(R.color.color_tv_hui));
            lineView.setVisibility(INVISIBLE);
        }
    }

    public PracticeButtomButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public PracticeButtomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PracticeButtomButton(Context context) {
        super(context);
        init();
    }

    private void init() {
        InitText();
    }

    public void InitText() {
        if (tv == null) {
            tv = new TextView(getContext());
            tv.setTextSize(16);
        }
        LayoutParams p = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        p.addRule(RelativeLayout.CENTER_IN_PARENT);
        addView(tv, p);

        lineView = new View(getContext());
        LayoutParams p2 = new LayoutParams(LayoutParams.MATCH_PARENT, 10);
        p2.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        addView(lineView, p2);
        lineView.setBackgroundColor(getContext().getResources().getColor(R.color.color_top_text_blue));
    }

    public void setButtonText(String str) {
        tv.setText(str);
    }

}
