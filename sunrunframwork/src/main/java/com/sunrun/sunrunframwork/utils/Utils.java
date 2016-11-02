package com.sunrun.sunrunframwork.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.app.ActivityManager;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Base64;

/**
 * 常用工具类
 * 
 * @author cnsunrun
 *
 */
public class Utils {
	private static long time;
	/**
	 * 计算地球上任意两点(经纬度)距离
	 * 
	 * @param long1
	 *            第一点经度
	 * @param lat1
	 *            第一点纬度
	 * @param long2
	 *            第二点经度
	 * @param lat2
	 *            第二点纬度
	 * @return 返回距离 单位：米
	 */
	public static double getDistance(double long1, double lat1, double long2, double lat2)
	{
		double a, b, R;
		R = 6378137; // 地球半径
		lat1 = lat1 * Math.PI / 180.0;
		lat2 = lat2 * Math.PI / 180.0;
		a = lat1 - lat2;
		b = (long1 - long2) * Math.PI / 180.0;
		double d;
		double sa2, sb2;
		sa2 = Math.sin(a / 2.0);
		sb2 = Math.sin(b / 2.0);
		d = 2 * R * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1) * Math.cos(lat2) * sb2 * sb2));
		return d;
	}
	
	public static String getDiscount(String base_price, String discount_price) {
		float sour = Utils.valueOf(base_price, 0);
		float disco = Utils.valueOf(discount_price, 0);
		if (sour - disco <= 0 || disco == 0 || (disco / sour) < 0.004f)
			return null;
		return String.format("%.1f折", (disco / sour * 10));
	}

	public static String getPath(Context context, Uri uri) {
		if ("content".equalsIgnoreCase(uri.getScheme())) {
			String[] projection =
			{ "_data" };
			Cursor cursor = null;

			try {
				cursor = context.getContentResolver().query(uri, projection, null, null, null);
				int column_index = cursor.getColumnIndexOrThrow("_data");
				if (cursor.moveToFirst()) { return cursor.getString(column_index); }
			} catch (Exception e) {
				// Eat it
			}
		}

		else if ("file".equalsIgnoreCase(uri.getScheme())) { return uri.getPath(); }

		return null;
	}

	/**
	 * 匹配是否手机号码，是 True
	 * 
	 * @param tel
	 * @return
	 */
	public static boolean isMobileNO(String mobiles) {
		Pattern p = Pattern.compile("^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9]|17[0|1|2|3|5|6|7|8|9])\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	/**
	 * 匹配邮箱格式是否正确
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		Pattern p = Pattern.compile("^([a-zA-Z0-9_\\.-])+@(([a-zA-Z0-9-])+\\.)+([a-zA-Z0-9]{2,4})+$");
		Matcher m = p.matcher(email);
		return m.matches();
	}

	/**
	 * 隐藏电话号码中间数字
	 * 
	 * @param phone
	 * @return
	 */
	public static String hiddenPhone(String phone) {
		if (phone.length() > 6) {
			String temp = "*********************";
			phone.subSequence(3, phone.length() - 3);
			return phone.substring(0, 3).concat(temp.substring(0, phone.length() - 6)).concat(phone.substring(phone.length() - 3));
		}
		return phone;
	}

	/**
	 * 格式化金额
	 * 
	 * @param money
	 * @return
	 */
	public static String formatMoney(String money) {
		StringBuffer sb = new StringBuffer(money);
		int index = money.indexOf(".");
		index = index == -1 ? money.length() : index;
		for (int len = money.length(), i = len - 1; i >= 0; i--)
			if (i < index && (len - i - 1 - 2) % 3 == 0 && i != 0)
				sb.insert(i, ',');
		return sb.toString();
	}

	/**
	 * 判断是否快速点击
	 * 
	 * @param mm
	 *            间隔时间(毫秒)
	 * @return
	 */
	public static boolean isQuck(long mm) {
		long temp = System.currentTimeMillis();
		if (temp - time <= mm)
			return true;
		time = temp;
		return false;
	}

	public static String lastTime() {
		lastTime = System.currentTimeMillis();
		return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(lastTime));
	}

	public static <T> T search(List<T> data, Object... objs) {
		for (T t : data) {
			for (Object obj : objs)
				if (t.equals(obj)) { return t; }
		}
		return null;
	}

	public static long lastTime = System.currentTimeMillis();

	/**
	 * Base 64加密
	 * 
	 * @param str
	 * @return
	 */
	public static String base64Encode(String str) {
		if (str == null)
			return null;
		return new String(Base64.encode(str.getBytes(), Base64.DEFAULT)).trim();
	}

	/**
	 * Base 64解密
	 * 
	 * @param str
	 * @return
	 */
	public static String base64Decode(String str) {
		if (str == null)
			return null;
		return new String(Base64.decode(str, Base64.DEFAULT));
	}

	/**
	 * 对字符串进行MD5加密
	 * 
	 * @param str
	 * @return
	 */
	public static String getMD5(String string) {
		try {
			return getMD5(string.getBytes("utf-8"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	public static String getMD5(byte[] hash) {
		try {
			hash = MessageDigest.getInstance("MD5").digest(hash);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Huh, MD5 should be supported?", e);
		} catch (Exception e) {
			throw new RuntimeException("Huh, UTF-8 should be supported?", e);
		}
		StringBuilder hex = new StringBuilder(hash.length * 2);
		for (byte b : hash) {
			if ((b & 0xFF) < 0x10)
				hex.append("0");
			hex.append(Integer.toHexString(b & 0xFF));
		}
		return hex.toString();
	}

	/**
	 * 获取当前进程名字
	 * 
	 * @param context
	 * @return
	 */
	public static String getCurProcessName(Context context) {
		int pid = android.os.Process.myPid();
		ActivityManager mActivityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager.getRunningAppProcesses()) {
			if (appProcess.pid == pid) { return appProcess.processName; }
		}
		return "";
	}

	/**
	 * 字符串转float(忽略错误)
	 * 
	 * @param value
	 * @param defValue
	 * @return
	 */
	public static float valueOf(String value, float defValue) {
		try {
			return Float.parseFloat(String.valueOf(value));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return defValue;
	}
	/**
	 * 去除字符串左右指定字符 ,类似于去除左右空格 trim
	 * @param feed_lable
	 * @param str
	 * @return
	 */
	public static String format(String feed_lable,String str){
		System.out.println(feed_lable);
		if(feed_lable.startsWith(str))
			feed_lable= format(feed_lable.substring(feed_lable.indexOf(str)+1),str);
		if(feed_lable.endsWith(str))
			feed_lable= format(feed_lable.substring(0, feed_lable.lastIndexOf(str)),str);
		return feed_lable;
	}
	public static String connectArr(List<String> arr) 
	{
		if(arr==null)return "";
		StringBuffer sb = new StringBuffer();
		for (String string : arr) {
			sb.append(string + ",");
		}
		if (sb.length() != 0) {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}
	public static String connectArr(String[] arr) 
	{
		StringBuffer sb = new StringBuffer();
		for (String string : arr) {
			sb.append(string + ",");
		}
		if (sb.length() != 0) {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}
	// 休眠
	public static void sleep(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
