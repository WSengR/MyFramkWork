package com.sunrun.sunrunframwork.utils;

import android.util.Log;

/**
 * Log工具类，设置开关，防止发布版本时log信息泄露
 */

public class LogUtils {

		public static void v(String tag, String msg) {
				Log.v(tag, msg);

		}

		public static void d(String tag, String msg) {
				Log.d(tag, msg);

		}

		public static void i(String tag, String msg) {
				Log.i(tag, msg);

		}

		public static void w(String tag, String msg) {
				Log.w(tag, msg);

		}

		public static void e(String tag, String msg) {
				Log.e(tag, msg);
		}

}
