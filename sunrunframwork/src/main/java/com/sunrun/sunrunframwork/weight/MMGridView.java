package com.sunrun.sunrunframwork.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * @类名: MMGridView.java
 * @功能描述:自适应大小的GridView
 * @作者 Wangsr
 * @时间 2015-10-12 下午2:00:42
 * @创建版本 V3.0
 */
public class MMGridView extends GridView {

    public MMGridView(Context context) {
        super(context);
    }

    public MMGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MMGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
