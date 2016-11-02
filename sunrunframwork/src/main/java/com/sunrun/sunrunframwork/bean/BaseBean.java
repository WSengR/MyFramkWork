package com.sunrun.sunrunframwork.bean;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @数据基础Bean
 */
public class BaseBean {
	public int status = 0;
	public String msg;
	public Object data;
	public boolean isCache=false;
	/** 默认获取当前时间作为请求时间 */
	public String post_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());

	public BaseBean() {
	}

	public boolean isCache() {
		return isCache;
	}

	public void setCache(boolean isCache) {
		this.isCache = isCache;
	}

	@Override
	public String toString() {
		return String.valueOf(data);
	}

	@SuppressWarnings("unchecked")
	public <T> T Data() {
		return (T) data;
	}

	/**
	 * 检查bean是否有效
	 *
	 * @return
	 */
	public boolean isVaild() {
		return status == 1 && data != null && !"".equals(data);
	}

	public boolean isEmpty() {
		return data == null || "".equals(data)||"[]".equals(data) ||"{}".equals(data);
	}}
