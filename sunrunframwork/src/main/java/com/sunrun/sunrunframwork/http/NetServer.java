package com.sunrun.sunrunframwork.http;


import android.content.Context;
import android.util.Log;
import android.util.SparseArray;

import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.TextHttpResponseHandler;
import com.sunrun.sunrunframwork.bean.BaseBean;
import com.sunrun.sunrunframwork.http.cache.NetSession;
import com.sunrun.sunrunframwork.http.utils.JsonDeal;
import com.sunrun.sunrunframwork.http.utils.Logger;
import com.sunrun.sunrunframwork.http.utils.UIUpdateHandler;

import org.apache.http.Header;
import org.apache.http.conn.ConnectTimeoutException;

import java.net.SocketTimeoutException;

/**
 * 网络请求服务
 * 
 * @author cnsunrun
 *
 */
public class NetServer implements NetRequestHandler {
	/** 是否使用缓存的标识 */
	boolean useCache = false;
	/** 网络缓存管理对象 */
	NetSession session;
	/** UI更新接口 */
	protected UIUpdateHandler uiUpdateHandler;
	/** 网络请求集合 */
	SparseArray<RequestHandle> reuqests;
	public final static String TAG = "NetServer";
	Context mContext;

	public NetServer(Context context, UIUpdateHandler uiUpdateHandler) {
		// TODO Auto-generated constructor stub
		this.uiUpdateHandler = uiUpdateHandler;
		this.mContext = context;
		reuqests = new SparseArray<RequestHandle>();
		if (session == null)
			session = uiUpdateHandler.getSession();
	}

	public NetServer(Context context) {
		this.mContext = context;
		reuqests = new SparseArray<RequestHandle>();
	}

	@Override
	public void requestAsynGet(NAction action) {
		// TODO Auto-generated method stub
		action.setRequestType(GET);
		doRequest(action);
	}

	@Override
	public void requestAsynPost(NAction action) {
		// TODO Auto-generated method stub
		action.setRequestType(POST);
		doRequest(action);
	}

	/**
	 * 网络请求的实现
	 *
	 * @param action
	 *            网络请求动作对象
	 */
	protected void doRequest(final NAction action) {
		if (reuqests.get(action.requestCode) != null) {// 同一请求存在时,不予以响应,防止在一个请求尚未结束时,又重复进行请求
			return;
		}

		// TODO Auto-generated method stub
		final boolean useCache = action.useCache ? action.useCache
				: this.useCache;// 判断是否使用缓存
		final boolean cachePriority = action.cachePriority;
		RequestHandle handler = null;
		final TextHttpResponseHandler responseHandler = new TextHttpResponseHandler() {

			@SuppressWarnings("unchecked")
			@Override
			public void onSuccess(int arg0, Header[] arg1, String json) {
				BaseBean bean = null;
				try {
					Logger.C(TAG, "请求成功: " + json, Log.INFO);
					// TODO Auto-generated method stub
					if (action.resultDataType != null)
						bean = JsonDeal.createBean(json, action.resultDataType);
					else if (action.typeToken != null)
						bean = JsonDeal.createBean(json, action.typeToken);
					else
						bean = JsonDeal.createBean(json);

					if (arg0 != Integer.MIN_VALUE) {
						if (session != null && !bean.isEmpty()) {
							session.put("" + action.hashCode(), json);
						}
						bean.setCache(useCache);
					}

					dispatch(action, bean);// 派发请求结果(不管是否为空都派发)
					if (bean.isEmpty()) {
						uiUpdateHandler.emptyData(action.requestCode, bean);// 数据空
					}

				} catch (Exception e) {
					Logger.E("来自:" + action + " 的请求结果处理异常:" + e + "" + json);
					uiUpdateHandler.loadFaild(action.requestCode, bean);// 通知UI请求失败
				}
			}

			@Override
			public void onStart() {
				uiUpdateHandler.requestStart();
			}

			@Override
			public void onFinish() {
				Logger.C(TAG, "请求结束", Log.INFO);
				// TODO Auto-generated method stub
				cancelRequest(action.requestCode);
				uiUpdateHandler.requestFinish();
			}

			@Override
			public void onCancel() {
				Logger.C(TAG, "请求取消", Log.INFO);
				// TODO Auto-generated method stub
				cancelRequest(action.requestCode);
				uiUpdateHandler.requestCancel();
				BaseBean bean = new BaseBean();
				bean.msg = JsonDeal.CANCLE_MSG;
				// uiUpdateHandler.loadFaild(action.requestCode, bean);//
				// 通知UI请求失败
			}

			@Override
			public void onProgress(int bytesWritten, int totalSize) {
				// TODO Auto-generated method stub
				uiUpdateHandler.nofityUpdate(action.requestCode, bytesWritten
						* 1f / totalSize);// 通知UI更新进度
			}

			@Override
			public void onFailure(int arg0, Header[] arg1, String arg2,
								  Throwable arg3) {

				if (useCache) {
					// 请求异常情况尝试读取缓存数据
					Logger.C(TAG, "CACHE: " + action.url + "?" + action.params,
							Log.INFO);
					String json = session.getString("" + action.hashCode());// 读取缓存信息
					if (json != null) {
						onSuccess(Integer.MIN_VALUE, null, json);
						return;
					}
				}
				// TODO Auto-generated method stub
				Logger.C(TAG, "请求失败: " + arg2 + "  " + arg3, Log.INFO);
				BaseBean bean = new BaseBean();
				bean.msg = JsonDeal.ERR_MSG;
				if (arg3 instanceof SocketTimeoutException
						|| arg3 instanceof ConnectTimeoutException) {
					bean.msg = "请求超时!";
				} else {
					bean.msg = "网络连接不可用";
				}

				uiUpdateHandler.loadFaild(action.requestCode, bean);// 通知UI请求失败
			}
		};

		switch (action.requestType) {
			case GET:
				if (cachePriority) {// 缓存优先,优先使用缓存,再进行网络请求
					String json = session.getString("" + action.hashCode());// 读取缓存信息
					if (json != null) {
						responseHandler.onSuccess(Integer.MIN_VALUE, null, json);
					}
				}
				Logger.C(TAG, "GET: " + action.url + "?" + action.params, Log.INFO);
				handler = NetUtils
						.doGet(action.url, action.params, responseHandler);
				break;
			case POST:
				if (cachePriority) {// 缓存优先,优先使用缓存,再进行网络请求
					String json = session.getString("" + action.hashCode());// 读取缓存信息
					if (json != null) {
						responseHandler.onSuccess(Integer.MIN_VALUE, null, json);
					}
				}
				Logger.C(TAG, "POST: " + action.url + "?" + action.params, Log.INFO);
				handler = NetUtils.doPost(action.url, action.params,
						responseHandler);
				break;
		}
		if (handler != null)// 保存网络请求相关的句柄,用于取消网络请求
			reuqests.put(action.requestCode, handler);
	}

	/**
	 * 派发请求结果,通知Ui更新界面
	 *
	 * @param action
	 * @param bean
	 */
	protected void dispatch(final NAction action, BaseBean bean) {
		if (bean.status == -1) {
//			LoginActivity.isLogin((Activity) mContext, true);
			return;

		}
		uiUpdateHandler.dealData(action.requestCode, bean);
		uiUpdateHandler.nofityUpdate(action.requestCode, bean);
	}

	@Override
	public synchronized void cancelRequest(int requestCode) {
		// TODO Auto-generated method stub
		cancelRequest(requestCode, true);
	}

	private void cancelRequest(int requestCode, boolean delete) {
		RequestHandle handler = reuqests.get(requestCode);
		if (handler != null)
			handler.cancel(true);
		if (delete)
			reuqests.remove(requestCode);
	}

	@Override
	public synchronized void cancelAllRequest() {
		// TODO Auto-generated method stub
		for (int i = 0; i < reuqests.size(); i++)
			cancelRequest(reuqests.keyAt(i), false);// 逐个遍历请求对象并取消
		reuqests.clear();// 清空缓存
	}

	@Override
	public void useCache(boolean useCache) {
		// TODO Auto-generated method stub
		this.useCache = useCache;
	}
}
