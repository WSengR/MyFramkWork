package com.sunrun.sunrunframwork.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.params.CoreConnectionPNames;
//import org.apache.http.protocol.HTTP;

/**
 * 网络相关的工具类
 * 
 */
public class NetWorkUtil
{
	/**
	 * 发送post请求
	 *
	 * @param url
	 * @param params
	 * @return
	 */
//	public static String doPost(String url, Map<String, String> params) throws Exception
//	{
//		List<NameValuePair> pairList = new ArrayList<NameValuePair>();
//		NameValuePair pair = null;
//		if (params != null)
//		{
//			Iterator<Entry<String, String>> iterator = params.entrySet().iterator();
//			while (iterator.hasNext())
//			{// 只遍历一次,速度快
//				Entry<String, String> entry = iterator.next();
//				String key = entry.getKey();
//				String value = entry.getValue();
//				pair = new BasicNameValuePair(key, value);
//				pairList.add(pair);
//			}
//		}
//		try
//		{
//			HttpEntity requestHttpEntity = new UrlEncodedFormEntity(pairList, HTTP.UTF_8);
//			// URL使用基本URL即可，其中不需要加参数
//			HttpPost httpPost = new HttpPost(url);
//			// 将请求体内容加入请求中
//			httpPost.setEntity(requestHttpEntity);
//			// 需要客户端对象来发送请求
//			HttpClient httpClient = new DefaultHttpClient();
//			// 设置连接超时为5s
//			httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 50000);
//			// 发送请求
//			HttpResponse response = httpClient.execute(httpPost);
//			// 显示响应
//			String result = getResponseResult(response);
//			System.out.println("-----------" + result);
//			return result;
//		} catch (Exception e)
//		{
//			throw e;
//		}
//	}

	/**
	 * 发送get请求
	 *
	 * @param baseUrl
	 * @param params
	 * @return
	 */
//	public static String doGet(String baseUrl, Map<String, String> params) throws Exception
//	{
//		String url = baseUrl;
//		if (params != null && params.size() > 0)
//		{
//			Iterator<Entry<String, String>> iterator = params.entrySet().iterator();
//			while (iterator.hasNext())
//			{// 只遍历一次,速度快
//				Entry<String, String> entry = iterator.next();
//				String key = entry.getKey().toString();
//				String value = entry.getValue().toString();
//				url = url + "?" + key + "=" + value + "&";
//			}
//			// 去掉最后一个&符号
//			url = url.substring(0, url.length() - 1);
//			// 去掉中间添加的多余？符号
//			url = url.replace("&?", "&");
//		}
//		// 生成请求对象
//		HttpGet httpGet = new HttpGet(url);
//		HttpClient httpClient = new DefaultHttpClient();
//		// 设置连接超时为5s
//		httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 50000);
//		HttpResponse httpResponse;
//		try
//		{
//			httpResponse = httpClient.execute(httpGet);
//			String result = getResponseResult(httpResponse);
//			return result;
//		} catch (Exception e)
//		{
//			throw e;
//		}
//	}

	/**
	 * 获取http请求返回的结果
	 *
	 * @param response
	 */
//	public static String getResponseResult(HttpResponse response)
//	{
//		if (null == response)
//		{
//			return null;
//		}
//		int code = response.getStatusLine().getStatusCode();
//		HttpEntity httpEntity = response.getEntity();
//		try
//		{
//			InputStream inputStream = httpEntity.getContent();
//			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//			String result = "";
//			String line = "";
//			while (null != (line = reader.readLine()))
//			{
//				result += line;
//			}
//			return result;
//		} catch (Exception e)
//		{
//			e.printStackTrace();
//		}
//		return null;
//
//	}

	/**
	 * 判断网络是否可用
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isNetworkAvailable(Context context)
	{
		ConnectivityManager mgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo[] info = mgr.getAllNetworkInfo();
		if (info != null)
		{
			for (int i = 0; i < info.length; i++)
			{
				if (info[i].getState() == NetworkInfo.State.CONNECTED)
				{
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 获取当前联网类型
	 * 
	 * @param context
	 * @return
	 */
	public static String getNetworkTypeName(Context context)
	{
		if (context != null)
		{
			ConnectivityManager connectMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (connectMgr != null)
			{
				NetworkInfo info = connectMgr.getActiveNetworkInfo();
				if (info != null)
				{
					switch (info.getType())
					{
					case ConnectivityManager.TYPE_WIFI:
						return "WIFI";
					case ConnectivityManager.TYPE_MOBILE:
						return getNetworkTypeName(info.getSubtype());
					}
				}
			}
		}
		return getNetworkTypeName(TelephonyManager.NETWORK_TYPE_UNKNOWN);
	}

	public static String getNetworkTypeName(int type)
	{
		switch (type)
		{
		case TelephonyManager.NETWORK_TYPE_GPRS:
			return "GPRS";
		case TelephonyManager.NETWORK_TYPE_EDGE:
			return "EDGE";
		case TelephonyManager.NETWORK_TYPE_UMTS:
			return "UMTS";
		case TelephonyManager.NETWORK_TYPE_HSDPA:
			return "HSDPA";
		case TelephonyManager.NETWORK_TYPE_HSUPA:
			return "HSUPA";
		case TelephonyManager.NETWORK_TYPE_HSPA:
			return "HSPA";
		case TelephonyManager.NETWORK_TYPE_CDMA:
			return "CDMA";
		case TelephonyManager.NETWORK_TYPE_EVDO_0:
			return "CDMA - EvDo rev. 0";
		case TelephonyManager.NETWORK_TYPE_EVDO_A:
			return "CDMA - EvDo rev. A";
		case TelephonyManager.NETWORK_TYPE_1xRTT:
			return "CDMA - 1xRTT";
		case TelephonyManager.NETWORK_TYPE_IDEN:
			return "iDEN";
		default:
			return "UNKNOWN";
		}
	}

}
