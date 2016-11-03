package com.sunrun.sunrunframwork.utils;

import java.io.File;
import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.media.AudioManager;
import android.net.Uri;

import com.sunrun.sunrunframwork.http.utils.Logger;

public class AppUtils {
	/**
	 * 静默方式 把多媒体音量设置到最大
	 * 
	 * @param context
	 */
	public static void setMaxVoice(Context context) {
		setVoice(context, getMaxVoice(context));
	}

	/**
	 * 静默方式 设置多媒体音量
	 * 
	 * @param context
	 * @param voice
	 */
	public static void setVoice(Context context, int voice) {
		AudioManager audioManager = (AudioManager) context
				.getSystemService(Service.AUDIO_SERVICE);

		// audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, voice, 0);
		audioManager.setStreamVolume(AudioManager.STREAM_ALARM, voice, 0);
	}

	/**
	 * 获取手机多媒体音量最大等级
	 * 
	 * @param context
	 * @return
	 */
	public static int getMaxVoice(Context context) {
		AudioManager audioManager = (AudioManager) context
				.getSystemService(Service.AUDIO_SERVICE);
		// return audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		return audioManager.getStreamMaxVolume(AudioManager.STREAM_ALARM);
	}

	/**
	 * 获取程序版本号,即AndroidManifest 文件中的 android:versionName
	 * 
	 * @param context
	 * @return
	 */
	public static String getVersionName(Context context) {
		String version = "";
		// 获取packagemanager的实例
		PackageManager packageManager = context.getPackageManager();
		// getPackageName()是你当前类的包名，0代表是获取版本信息
		try {
			PackageInfo packInfo = packageManager.getPackageInfo(
					context.getPackageName(), 0);
			version = packInfo.versionName;
		} catch (Exception e) {
			Logger.C(AppUtils.class.getSimpleName(), "版本号获取失败");
		}
		return version;
	}

	/**
	 * 描述：打开并安装文件.
	 *
	 * @param context
	 *            the context
	 * @param file
	 *            apk文件路径
	 */
	public static void installApk(Context context, File file) {
		Logger.E(file);
		Intent intent = new Intent();
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setAction(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(file),
				"application/vnd.android.package-archive");
		context.startActivity(intent);
	}

	/**
	 * 描述：卸载程序.
	 *
	 * @param context
	 *            the context
	 * @param packageName
	 *            包名
	 */
	public static void uninstallApk(Context context, String packageName) {
		Intent intent = new Intent(Intent.ACTION_DELETE);
		Uri packageURI = Uri.parse("package:" + packageName);
		intent.setData(packageURI);
		context.startActivity(intent);
	}

	/**
	 * 
	 * 描述：获取可用内存.
	 * 
	 * @param context
	 * @return 返回内存值 单位byte字节
	 */
	public static long getAvailMemory(Context context) {
		// 获取android当前可用内存大小
		ActivityManager activityManager = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		MemoryInfo memoryInfo = new MemoryInfo();
		activityManager.getMemoryInfo(memoryInfo);
		// 当前系统可用内存 ,将获得的内存大小规格化
		return memoryInfo.availMem;
	}

	/**
	 * 获取当前程序md5签名
	 * 
	 * @param context
	 * @return
	 */
	public static String getSingInfo(Context context) {
		try {
			PackageInfo packageInfo = context.getPackageManager()
					.getPackageInfo(context.getPackageName(),
							PackageManager.GET_SIGNATURES);
			Signature[] signs = packageInfo.signatures;
			Signature sign = signs[0];
			return Utils.getMD5(sign.toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 检测该包名所对应的应用是否存在
	 * 
	 * @param packageName
	 * @return
	 */
	public static boolean checkPackage(Context context, String packageName) {
		if (packageName == null || "".equals(packageName))
			return false;
		try {
			context.getPackageManager().getApplicationInfo(packageName,
					PackageManager.GET_UNINSTALLED_PACKAGES);
			return true;
		} catch (NameNotFoundException e) {
			return false;
		}
	}

	/**
	 * 判断是否有安装浏览器
	 * @param context
	 * @return
	 */
	public static  boolean hasBrowser(Context context) {
		PackageManager pm = context.getPackageManager();
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.addCategory(Intent.CATEGORY_BROWSABLE);
		intent.setData(Uri.parse("http://"));

		List<ResolveInfo> list = pm.queryIntentActivities(intent,
				PackageManager.GET_INTENT_FILTERS);
		final int size = (list == null) ? 0 : list.size();
		return size > 0;
	}

	public static void getLocation(Context context) {
		// LocationManager
		// manager=context.getSystemService(Context.LOCATION_SERVICE);
		// manager.
	}
	/***
	 * 检查权限是否存在
	 *
	 * @param context
	 * @param permissionName
	 * @return
	 */
	public static boolean checkPermission(Context context, String permissionName) {
		PackageManager pkm = context.getPackageManager();
		boolean has_permission = (PackageManager.PERMISSION_GRANTED ==
				pkm.checkPermission(permissionName, context.getPackageName()));
		return has_permission;

	}
}
