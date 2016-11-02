package com.sunrun.sunrunframwork.http;

/**
 * 网络请求处理接口
 * @author cnsunrun
 */
public interface NetRequestHandler {

		public static final int GET = 0x00911;
		public static final int POST = 0x00912;

		/**
		 * 异步get请求
		 *
		 * @param action
		 */
		public void requestAsynGet(NAction action);

		/**
		 * 是否使用缓存,只对设置之后再发起的请求有效
		 *
		 * @param useCache
		 */
		public void useCache(boolean useCache);

		/**
		 * 异步post请求
		 *
		 * @param action
		 */
		public void requestAsynPost(NAction action);

		/**
		 * 取消请求
		 *
		 * @param requestCode
		 */
		public void cancelRequest(int requestCode);

		/**
		 * 取消所有请求
		 */
		public void cancelAllRequest();
	}
