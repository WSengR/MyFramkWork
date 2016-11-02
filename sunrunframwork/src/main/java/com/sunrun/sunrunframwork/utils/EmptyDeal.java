package com.sunrun.sunrunframwork.utils;

import android.widget.TextView;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 用于处理各种Null,Empty
 */
public final class EmptyDeal {

	// --------------------------------Deal Null
	/**
	 * @param dest
	 * @return
	 */
	public static String dealNull(String dest) {
		if (dest == null)
			return (dest = "");
		return dest;
	}

	public static List<?> dealNull(List<?> list) {
		if (list == null)
			return list = Collections.emptyList();
		return list;
	}

	public static Map<?, ?> dealNull(Map<?, ?> map) {
		if (map == null)
			return map = Collections.emptyMap();
		return map;
	}

	public static Set<?> dealNull(Set<?> set) {
		if (set == null)
			return set = Collections.emptySet();
		return set;
	}

	public static Object[] dealNull(Object[] array) {
		if (array == null)
			return array = new Object[0];
		return array;
	}

	public static Object dealNull(Object obj) {
		return obj == null ? new Object() : obj;
	}

	// --------------------------------Deal Null

	/**
	 * 判断list大小是否比指定大小小
	 * 
	 * @param dest
	 * @param size
	 * @return
	 */
	public static boolean isLessThan(List<?> dest, int size) {
		return dest == null || dest.size() < size;
	}

	/**
	 * 判断list是否为空(null || size=0)
	 * 
	 * @param dest
	 * @return
	 */
	public static boolean isEmpy(List<?> dest) {
		return dest == null || dest.size() == 0;
	}

	/**
	 * 判断字符串是否为空(null || "")
	 * 
	 * @param dest
	 * @return
	 */
	public static boolean isEmpy(String dest) {
		return dest == null || dest.length() == 0 || "".equals(dest);
	}
	
	/**
	 * 判断文本框是否为空(null|| "")
	 * 
	 * @param dest
	 * @return
	 */
	public static boolean isEmpy(TextView dest) {
		return dest == null || dest.getText().toString().trim().equals("");
	}

	/**
	 * 判断Map是否为空(null || size=0)
	 * 
	 * @param dest
	 * @return
	 */
	public static boolean isEmpy(Map<?, ?> dest) {
		return dest == null || 0 == dest.size();
	}
	/**
	 * 判断数组是否为空(null || size=0)
	 * 
	 * @param array 待判定的数组对象
	 * @return 
	 */
	public static boolean isEmpy(Object[] array) {
		return array == null || array.length == 0;
	}
	public static boolean isEmpy(Object dest) {
		if(dest instanceof List){
			return isEmpy((List)dest);
		}
		if(dest instanceof Map){
			return isEmpy((Map)dest);
		}
		if(dest instanceof String){
			return isEmpy((String)dest);
		}
		if(dest instanceof TextView){
			return isEmpy((TextView)dest);
		}else{
			return dest==null;
		}
	}
}
