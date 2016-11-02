package com.sunrun.sunrunframwork.net;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.sunrun.sunrunframwork.utils.LogUtils;

import org.apache.http.Header;


public class HttpUtils {
	public static AsyncHttpClient client = new AsyncHttpClient();

	public static void doGet(String url,
			final AsyncHttpResponseHandler responseHandler) {

		// 创建请求参数的封装的对象
		 RequestParams params = new RequestParams();
		params.put("cellphone", "13088888000");
		params.put("password", "123456");

		LogUtils.i("Http", url + "");
		client.get(url, new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
				LogUtils.d("Http", arg2 + "  ");
				responseHandler.onSuccess(arg0, arg1, arg2);
			}

			/**
			 * 失败处理的方法 error：响应失败的错误信息封装到这个异常对象中
			 */
			@Override
			public void onFailure(int arg0, Header[] arg1, byte[] arg2,
					Throwable arg3) {
				responseHandler.onFailure(arg0, arg1, arg2, arg3);
			}
		});
	}

	public static void doGet(String url,
			final AsyncHttpResponseHandler responseHandler, int timeout) {
		client.setTimeout(timeout);
		client.get(url, new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
				LogUtils.d("Http", arg2 + "  ");
				responseHandler.onSuccess(arg0, arg1, arg2);
			}

			@Override
			public void onFailure(int arg0, Header[] arg1, byte[] arg2,
					Throwable arg3) {
				responseHandler.onFailure(arg0, arg1, arg2, arg3);
			}
		});
	}

	public static void doPost(String url, RequestParams params,
			final AsyncHttpResponseHandler responseHandler) {
		LogUtils.i("Http", url + "");
		client.post(url, params, new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
				responseHandler.onSuccess(arg0, arg1, arg2);
			}

			@Override
			public void onFailure(int arg0, Header[] arg1, byte[] arg2,
					Throwable arg3) {
				responseHandler.onFailure(arg0, arg1,
						(arg2 == null ? "".getBytes() : arg2), arg3);
			}
		});
	}

}
