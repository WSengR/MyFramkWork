package com.sunrun.sunrunframwork.http.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Locale;

/**
 * 日志相关
 */
public class Logger {

	/** 是否输出debug信息 */
	public static boolean isDebug = true;
	/** 是否输出error信息 */
	public static boolean isErr = true;
	/** 是否输出info信息 */
	public static boolean isInfo = true;
	/** 是否输出isCustomer信息 */
	public static boolean isCustomer = true;
	/** 是否输出warn信息 */
	public static boolean isWarn = true;
	/** 是否显示调用行数 */
	public static boolean isShowBuildMessage = true;
	/** 是否显示调用者类 */
	public static boolean isShowCaller = true;
	/** 是否保存日志到本地 */
	public static boolean isLocal = true;
	/** 默认TAG */
	public static String TAG = "weiquan";
	/** 本地日志文件名 */
	public static String LOG_NAME = "default_name";
	/** 本地日志单个最大大小 */
	public static long LOCAL_FILE_LIMIT_SIZE = 1024 * 1024 * 5;// 日志文件默认分割大小
	/** 本地日志最大个数 */
	public static int LOCAL_FILE_NUM = 10;// 日志文件默认最大数目

	public static void setHasLog(boolean hasLog) {
		isDebug = isErr = isInfo = isCustomer = isWarn = isShowBuildMessage = isShowCaller = isLocal = hasLog;
	}

	/**
	 * 显示Debug信息
	 * 
	 * @param mess
	 *            消息
	 */
	public static void D(Object mess) {

		INFO(TAG, String.format("%s %s: \n%s", getTag(), buildMessage(), String.valueOf(mess)), Log.DEBUG);
	}

	/**
	 * 显示ERROR信息
	 * 
	 * @param mess
	 *            消息
	 */
	public static void E(Object mess) {

		INFO(TAG, String.format("%s %s: \n%s", getTag(), buildMessage(), String.valueOf(mess)), Log.ERROR);
	}

	/**
	 * 显示警告信息
	 * 
	 * @param mess
	 */
	public static void W(Object mess) {

		INFO(TAG, String.format("%s %s: \n%s", getTag(), buildMessage(), String.valueOf(mess)), Log.WARN);
	}

	/**
	 * 显示INFO
	 * 
	 * @param mess
	 */
	public static void I(Object mess) {

		INFO(TAG, String.format("%s %s: \n%s", getTag(), buildMessage(), String.valueOf(mess)), Log.INFO);
	}

	/**
	 * 自定义Tag信息(类型为ERROR)
	 * 
	 * @param TAG
	 * @param mess
	 */
	public static void C(String TAG, Object mess) {

		INFO(TAG, String.format("%s %s: \n%s", getTag(), buildMessage(), String.valueOf(mess)), 0);
	}

	/**
	 * 自定义Tag 自定义类型
	 * 
	 * @param TAG
	 * @param mess
	 * @param type
	 */
	public static void C(String TAG, Object mess, int type) {

		INFO(TAG, String.format("%s %s: \n%s", getTag(), buildMessage(), String.valueOf(mess)), type);
	}

	private static void INFO(String TAG, String mess, int type) {
		switch (type) {
		case Log.INFO:
			if (isInfo)
				Log.i(TAG, mess);
			break;
		case Log.DEBUG:
			if (isDebug)
				Log.d(TAG, mess);
			break;
		case Log.ERROR:
			if (isErr)
				Log.e(TAG, mess);
			break;
		case Log.WARN:
			if (isWarn)
				Log.w(TAG, mess);
			break;
		default:
			if (isCustomer)
				Log.e(TAG, mess);
			break;
		}
		if (isLocal)
			save2Local(TAG, mess, "" + type);
	}

	/**
	 * 设置日志文件默认名称
	 * 
	 * @param context
	 */
	public static void setLogName(Context context) {
		LOG_NAME = context.getPackageName();
	}

	/**
	 * 保存日志到本地文件中
	 * 
	 * @param tag
	 * @param mess
	 * @param type
	 */
	private static void save2Local(String tag, String mess, String type) {
		File logPath = Environment.getExternalStorageDirectory();
		if (!logPath.exists())
			if (!logPath.mkdirs())
				return;
		try {
			logPath = getNewestfile(logPath, LOG_NAME);
			// 以追加模式打开日志文件
			if (!logPath.exists())
				logPath.createNewFile();
			BufferedWriter write = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(logPath, true)));
			write.write(type + ":\t" + tag + " --> " + mess + "\r\n");
			write.flush();
			write.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 获取最新日志文件
	 * 
	 * @param dir
	 * @param logName
	 * @return
	 */
	private static File getNewestfile(File dir, final String logName) {
		String paths[] = dir.list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String filename) {
				// TODO Auto-generated method stub
				return filename.contains(logName);
			}
		});

		if (paths != null && paths.length != 0) {
			Arrays.sort(paths);
//			for (String string : paths) {}
			if (paths.length - LOCAL_FILE_NUM >= 0) {
				for (int i = paths.length - LOCAL_FILE_NUM; i < paths.length; i++) {
					new File(dir, paths[i]).delete();
				}
			}
			if (new File(dir, paths[paths.length - 1]).length() <= LOCAL_FILE_LIMIT_SIZE)
				return new File(dir, paths[paths.length - 1]);
			String ns[] = paths[paths.length - 1].split("_");
			int num = Integer.parseInt(ns[0]);
			return new File(dir, (num + 1) + "_" + logName + ".log");
		}
		return new File(dir, "0_" + logName + ".log");
	}

	// ------------------------------------获取运行时的一些信息------------------------------------
	/**
	 * 获取标签(类名)
	 * 
	 * @return
	 */
	private static String getTag() {
		if (!isShowCaller)
			return "";
		StackTraceElement[] trace = new Throwable().fillInStackTrace().getStackTrace();
		String callingClass = "";
		for (int i = 2; i < trace.length; i++) {
			Class<?> clazz = trace[i].getClass();
			if (!clazz.equals(Logger.class)) {
				callingClass = trace[i].getClassName();
				callingClass = callingClass.substring(callingClass.lastIndexOf('.') + 1);
				break;
			}
		}
		return callingClass;
	}

	/**
	 * 获取调用方法和行数
	 * 
	 * @return
	 */
	private static String buildMessage() {
		if (!isShowBuildMessage)
			return "";
		StackTraceElement[] trace = new Throwable().fillInStackTrace().getStackTrace();
		String caller = "";
		String lineNumber = "";
		for (int i = 2; i < trace.length; i++) {
			Class<?> clazz = trace[i].getClass();
			if (!clazz.equals(Logger.class)) {
				caller = trace[i].getMethodName();
				lineNumber = Integer.toString(trace[i].getLineNumber());
				break;
			}
		}
		return String.format(Locale.US, "[%s(%s)]", caller, lineNumber);
	}
}
