package com.sunrun.sunrunframwork.uiutils;

import android.content.Context;
import android.widget.Toast;


public class ToastHelper {
	private static Toast mToast;

	public static void showToast(Context context, String msg, int duration) {
		if (null == mToast) {
			mToast = Toast.makeText(context, msg, duration);
		} else {
			mToast.setText(msg);
		}
		mToast.show();
	}
	public static void showToast(Context context, String msg) {
		if (null == mToast) {
			mToast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
		} else {
			mToast.setText(msg);
		}
		mToast.show();
	}
}
