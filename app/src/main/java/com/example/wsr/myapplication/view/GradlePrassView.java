package com.example.wsr.myapplication.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wsr.myapplication.R;

/**
 * @作者: Wang'sr
 * @时间: 2016/11/4
 * @功能描述: 旋转进度动画
 */

public class GradlePrassView extends RelativeLayout {

    private Context mContext;
    private View rootView;
    private TextView tvText;
    private ImageView ivRotateView;

    private boolean isAutoBool = false;
    /**
     * 默认是两面钟
     */
    private long druation = 2000;
    /**
     * 回调监听
     */
    private final static int HANDLE_MASG = 2;

    private OnAnimationFinish onAnimationFinish;

    Handler handle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (isAutoBool) {
                clearAnimation();
            }
            if (onAnimationFinish != null) {
                onAnimationFinish.AnimationFinish(GradlePrassView.this);
            }
        }
    };

    public GradlePrassView(Context context) {
        this(context, null);
    }

    public GradlePrassView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GradlePrassView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView();
    }

    /**
     * 初始化
     */

    private void initView() {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rootView = inflater.inflate(R.layout.lay_gradle_pragress, null);
        if (rootView != null) {
            tvText = (TextView) rootView.findViewById(R.id.tv_text);
            ivRotateView = (ImageView) rootView.findViewById(R.id.iv_rotate_view);
            addView(rootView);
        }
    }


    public void setViewText(String string) {
        if (tvText != null && string != null) {
            tvText.setText(string);
        }
    }


    public void statrImgViewAnim() {
        if (ivRotateView.getVisibility() == GONE) {
            ivRotateView.setVisibility(VISIBLE);
            ivRotateView.setImageResource(R.drawable.img_gradle_graess);
        }
        ivRotateView.startAnimation(getRotateAnim());
        handle.sendEmptyMessageDelayed(HANDLE_MASG, this.druation);

    }

    /**
     * 设置动画
     *
     * @param onAnimationFinish 回调监听
     * @param isAutoBool        是否自动播放取消监听
     * @param druation          结束时间
     *                          <p/>
     *                          注：若不自动取消则调用取消方法  clearAnimation();
     */
    public void statrImgViewAnim(final OnAnimationFinish onAnimationFinish, final boolean isAutoBool, long druation) {

        this.onAnimationFinish = onAnimationFinish;
        this.isAutoBool = isAutoBool;
        this.druation = druation;
        statrImgViewAnim();
    }


    /**
     * 获取旋转动画
     */
    public Animation getRotateAnim() {
        Animation rotateAnim = AnimationUtils.loadAnimation(mContext, R.anim.anim_rotate);
        LinearInterpolator lin = new LinearInterpolator();
        rotateAnim.setInterpolator(lin);
        return rotateAnim;
    }

    /**
     * 取消动画
     */
    public void clearAnimation() {
        ivRotateView.clearAnimation();
        ivRotateView.setImageResource(R.drawable.img_gradle_ok);
        handle.removeMessages(HANDLE_MASG);//移除动画消息队列
    }

    public interface OnAnimationFinish {
        void AnimationFinish(GradlePrassView gradlePrassView);
    }


    /** GET  SET */
    public void setOnAnimationFinish(OnAnimationFinish onAnimationFinish) {
        this.onAnimationFinish = onAnimationFinish;
    }

    public void setDruation(long druation) {
        this.druation = druation;
    }

    public void setIsAutoBool(boolean isAutoBool) {
        this.isAutoBool = isAutoBool;
    }

}
