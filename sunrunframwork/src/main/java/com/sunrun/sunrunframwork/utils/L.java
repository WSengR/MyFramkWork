package com.sunrun.sunrunframwork.utils;

import java.util.List;


import android.text.TextUtils;
import android.util.Log;

/**
 * @类名:L
 * @功能描述:日志输出控制类
 * <li> 默认打印TAG为XMN,通过{@link setTag}设置打印TAG
 * <li>通过{@link setDebugLevel}方法设置打印级别,设置为如果小于等于0,表示不打印任何日志,设置为大于等于5表示打印所有级别日志
 * @作者:ZhouRui
 * @时间:2014-11-20 下午3:20:53
 * @Copyright 2014
 */
public class L {

	/** 日志输出级别NONE,不打印任何日志 */
	protected static final int LEVEL_NONE = 0;
	/** 日志输出级别V */
	private static final int LEVEL_VERBOSE = 1;
	/** 日志输出级别D */
	private static final int LEVEL_DEBUG = 2;
	/** 日志输出级别I */
	private static final int LEVEL_INFO = 3;
	/** 日志输出级别W */
	private static final int LEVEL_WARN = 4;
	/** 日志输出级别E */
	private static final int LEVEL_ERROR = 5;

	/** 日志输出时的TAG */
	private static String mTag = "XMN";
	/** 是否允许输出log, 设置为<=0,表示不打印任何日志,设置为>=5表示打印所有级别日志 */
	private static int mDebuggable = LEVEL_ERROR;
	/** 记录Log打印时间 */
	private static long mTimestamp = 0;
	/** Log写入文件锁 */
	private static final Object mLogLock = new Object();

	/**
	 * 方法名: setTag
	 * 
	 * 功能描述: 设置打印日志TAG
	 * 
	 * @param tag
	 * @return void
	 * 
	 *         </br>throws
	 */
	public static void setTag(String tag) {
		mTag = tag;
	}

	/**
	 * 方法名: setDebuggable
	 * 
	 * 功能描述: 设置打印控制级别
	 * 
	 * @param debugLevel
	 *            0,1,2,3,4,5 分别表示打印级别none,v,d,i,w,e
	 *            设置为<=0,表示不打印任何日志,设置为>=5表示打印所有级别日志
	 * @return void
	 * 
	 *         </br>throws
	 */
	public static void setDebugLevel(int debugLevel) {
		if (debugLevel < 0) {
			mDebuggable = LEVEL_NONE;
			return;
		}
		if (debugLevel > 5) {
			mDebuggable = LEVEL_ERROR;
			return;
		}
		mDebuggable = debugLevel;
	}

	/**
	 * 方法名: v
	 * 
	 * 功能描述: 以级别为 v 的形式输出LOG
	 * 
	 * @param msg
	 * @return void
	 * 
	 *         </br>throws
	 */
	public static void v(String msg) {
		if (mDebuggable >= LEVEL_VERBOSE) {
			Log.v(mTag, msg);
		}
	}

	/**
	 * 方法名: d
	 * 
	 * 功能描述: 以级别为 d 的形式输出LOG
	 * 
	 * @param msg
	 * @return void
	 * 
	 *         </br>throws
	 */
	public static void d(String msg) {
		if (mDebuggable >= LEVEL_DEBUG) {
			Log.d(mTag, msg);
		}
	}

	/**
	 * 方法名: i
	 * 
	 * 功能描述: 以级别为 i 的形式输出LOG
	 * 
	 * @param msg
	 * @return void
	 * 
	 *         </br>throws
	 */
	public static void i(String msg) {
		if (mDebuggable >= LEVEL_INFO) {
			Log.i(mTag, msg);
		}
	}

	/**
	 * 方法名: w
	 * 
	 * 功能描述: 以级别为 w 的形式输出LOG
	 * 
	 * @param msg
	 * @return void
	 * 
	 *         </br>throws
	 */
	public static void w(String msg) {
		if (mDebuggable >= LEVEL_WARN) {
			Log.w(mTag, msg);
		}
	}

	/**
	 * 方法名: w
	 * 
	 * 功能描述:以级别为 w 的形式输出Throwable
	 * 
	 * @param tr
	 *            异常
	 * @return void
	 * 
	 *         </br>throws
	 */
	public static void w(Throwable tr) {
		if (mDebuggable >= LEVEL_WARN) {
			Log.w(mTag, "", tr);
		}
	}

	/**
	 * 方法名: w
	 * 
	 * 功能描述:以级别为 w 的形式输出LOG信息和Throwable
	 * 
	 * @param msg
	 *            打印信息
	 * @param tr
	 *            warning异常信息
	 * @return void
	 * 
	 *         </br>throws
	 */
	public static void w(String msg, Throwable tr) {
		if (mDebuggable >= LEVEL_WARN && null != msg) {
			Log.w(mTag, msg, tr);
		}
	}

	/**
	 * 方法名: e
	 * 
	 * 功能描述:以级别为 e 的形式输出LOG
	 * 
	 * @param msg
	 * @return void
	 * 
	 *         </br>throws
	 */
	public static void e(String msg) {
		if (mDebuggable >= LEVEL_ERROR) {
			Log.e(mTag, msg);
		}
	}

	/**
	 * 方法名: e
	 * 
	 * 功能描述:以级别为 e 的形式输出Throwable
	 * 
	 * @param tr
	 *            异常信息
	 * @return void
	 * 
	 *         </br>throws
	 */
	public static void e(Throwable tr) {
		if (mDebuggable >= LEVEL_ERROR) {
			Log.e(mTag, "", tr);
		}
	}

	/**
	 * 方法名: e
	 * 
	 * 功能描述:以级别为 e 的形式输出LOG信息和Throwable
	 * 
	 * @param msg
	 *            打印error日志
	 * @param tr
	 *            打印error异常
	 * @return void
	 * 
	 *         </br>throws
	 */
	public static void e(String msg, Throwable tr) {
		if (mDebuggable >= LEVEL_ERROR && null != msg) {
			Log.e(mTag, msg, tr);
		}
	}






	/**
	 * 方法名: printList
	 * 
	 * 功能描述: 用于输入List<T>,泛型类必须实现toString方法
	 * 
	 * @param list
	 * @return void
	 * 
	 *         </br>throws
	 */
	public static <T> void printList(List<T> list) {
		if (list == null || list.size() < 1) {
			return;
		}
		int size = list.size();
		i("---begin---");
		for (int i = 0; i < size; i++) {
			i(i + ":" + list.get(i).toString());
		}
		i("---end---");
	}

	/**
	 * 方法名: printArray
	 * 
	 * 功能描述: 用于输出泛型数组,泛型必须实现toString方法
	 * 
	 * @param array
	 * @return void
	 * 
	 *         </br>throws
	 */
	public static <T> void printArray(T[] array) {
		if (array == null || array.length < 1) {
			return;
		}
		int length = array.length;
		i("---begin---");
		for (int i = 0; i < length; i++) {
			i(i + ":" + array[i].toString());
		}
		i("---end---");
	}
}
