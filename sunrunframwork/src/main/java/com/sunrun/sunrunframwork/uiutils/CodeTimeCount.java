package com.sunrun.sunrunframwork.uiutils;

import android.os.CountDownTimer;
import android.widget.Button;

/**
 * @类名: CodeTimeCount.java
 * @功能描述: 获取验证码 timecount
 * @作者 Wangsr
 * @时间 2015-9-17 下午5:39:00
 * @创建版本 V3.0
 */
public class CodeTimeCount extends CountDownTimer {

	private Button codeBtn;
	/**
	 * 
	 * @param millisInFuture
	 *            总时间
	 * @param countDownInterval
	 *            间隔时间
	 * @param codeBtn
	 *            点击Button
	 */
	public CodeTimeCount(long millisInFuture, long countDownInterval, Button codeBtn) {
		super(millisInFuture, countDownInterval);
		this.codeBtn = codeBtn;
	}

	@Override
	public void onTick(long millisUntilFinished) {
		codeBtn.setEnabled(false);

		codeBtn.setText(millisUntilFinished / 1000 + "秒");
	}

	@Override
	public void onFinish() {
		codeBtn.setText("获取验证码");
		codeBtn.setEnabled(true);
	}

}
