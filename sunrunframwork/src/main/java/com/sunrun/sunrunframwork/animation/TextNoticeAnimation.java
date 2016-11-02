/**
 * 
 */
package com.sunrun.sunrunframwork.animation;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.animation.Animation.AnimationListener;
import android.widget.TextView;

/**
 * @作者: Wang'sr
 * @时间: 2016年10月25日
 * @功能描述: 公告上下轮播动画
 * @version V1.0
 */
public class TextNoticeAnimation {

	private Animation toOutAnimation, toInAnimation;
	private int intDuration = 3000;
	private int index = 0;
	private TextView tvNotice;
	private String[] datas;
	private Boolean boolStart = false;
	private static int MSG_ANIMATION_ONE = 1;

	public TextNoticeAnimation(TextView tvNotice, String[] datas) {
		this.tvNotice = tvNotice;
		this.datas = datas;
		this.tvNotice.setText(datas[0]);

		toOutAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, -3);
		toOutAnimation.setDuration(500);
		toOutAnimation.setRepeatMode(Animation.RESTART);

		toInAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 3, Animation.RELATIVE_TO_SELF, 0);
		toInAnimation.setDuration(200);
		toInAnimation.setRepeatMode(Animation.RESTART);

	}

	public void setDatas(String[] datas) {
		this.datas = datas;
		index = 0;
	}

	public void startTextNoticeAnimation() {
		Log.e("wsr", "======================" + index);
		startAnimation();

	}

	public void startAnimation() {
		handler.removeMessages(MSG_ANIMATION_ONE);//先移除以前的再添加新的消息队列
		handler.sendMessageDelayed(new Message(), intDuration);
		boolStart = true;
	}

	public void stopAnimation() {
		boolStart = false;
		handler.removeMessages(MSG_ANIMATION_ONE);
	}

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			Log.e("wsr", datas.length + "======================" + index);
			toOutAnimation.setAnimationListener(new AnimationListener() {
				@Override
				public void onAnimationStart(Animation arg0) {
				}

				@Override
				public void onAnimationRepeat(Animation arg0) {
				}

				@Override
				public void onAnimationEnd(Animation arg0) {
					if(datas!=null){
						tvNotice.setText(datas[index++ % datas.length]);
					}
					tvNotice.startAnimation(toInAnimation);
					if (boolStart) {
						handler.sendEmptyMessageDelayed(MSG_ANIMATION_ONE, intDuration);
					}
				}
			});
			tvNotice.startAnimation(toOutAnimation);
		}
	};

}
