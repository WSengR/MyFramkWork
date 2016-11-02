package com.sunrun.sunrunframwork.http;

import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;
import com.sunrun.sunrunframwork.http.utils.Logger;
import com.sunrun.sunrunframwork.utils.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

/**
 * 网络请求动作对象,封装请求的基本信息
 * 
 * @author wq
 */
public class NAction {
	public int requestCode = Integer.MIN_VALUE;// 请求码
	public String url;// 请求地址
	public int requestType;// 请求类型
	public RequestParams params;// 请求参数集
	public Class<?> resultDataType;// 结果数据的解析类型
	public TypeToken typeToken;// 结果数据的解析类型
	public boolean useCache;
	public boolean cachePriority;
	public Object tag;// 标签
	TreeMap<String,String> treeMap=new TreeMap<>(new Comparator<String>() {

		@Override
		public int compare(String lhs, String rhs) {
			return lhs.compareTo(rhs);
		}});
	public String getUrl() {
		return url;
	}

	/**
	 * 设置使用缓存,只在本次请求中有效
	 *
	 * @return
	 */
	public NAction setUseCache() {
		this.useCache = true;
		return this;
	}
	/**
	 * 设置使用缓存,只在本次请求中有效
	 *
	 * @return
	 */
	public NAction cachePriority(boolean bool) {
		setUseCache();
		this.cachePriority = bool;
		return this;
	}

	public NAction setResultDataType(Class<?> resultDataType) {
		this.resultDataType = resultDataType;
		return this;
	}

	public NAction setUrl(String url) {
		this.url = url;
		return this;
	}

	public <T> NAction setTypeToken(TypeToken<T> typeToken) {
		this.typeToken = typeToken;
		return this;
	}

	public <T> NAction setTypeToken(Class<?> resultDataType) {
		this.resultDataType = resultDataType;
		return this;
	}

	public int getRequestType() {
		return requestType;
	}

	public NAction setRequestType(int requestType) {
		this.requestType = requestType;
		return this;
	}

	public Object getTag() {
		return tag;
	}

	public NAction setTag(Object tag) {
		this.tag = tag;
		return this;
	}

	public NAction put(Object[] value) {
		if (value != null) {
			for (int i = 0; i < value.length; i++) {
				put(String.valueOf(i), value[i]);
			}
		}
		return this;
	}

	/**
	 * 放置请求参数
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public NAction put(String key, Object value) {
		dealNull();
		if (value instanceof android.widget.TextView) {
			String tmpVal=((android.widget.TextView) value).getText()
					.toString().trim();
			params.put(key, tmpVal);
			treeMap.put(key, tmpVal);
		} else if (value instanceof File) {
			put(key, (File) value);
		} else if (value instanceof List) {// 如果是集合,遍历集合并放入(对File集合做特殊处理)
			List list = (List) value;
			for (int i = 0, len = list.size(); i < len; i++) {
				if (list.get(i) instanceof File) {
					put(key + "_" + i, list.get(i));
				} else {
					put(key, list.toArray());
					break;
				}
			}
		} else {
			treeMap.put(key, String.valueOf(value));
			params.put(key, value);
		}
		return this;
	}

	/**
	 * 放置请求文件对象
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public NAction put(String key, File value) {
		dealNull();
		try {
			Logger.D("上传文件路径:" + value);
			if (value != null)
				params.put(key, value);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return this;
	}
	public NAction putSign(String key,String appendValue){
		StringBuilder result = new StringBuilder();
		for (Iterator<String> iterator = treeMap.keySet().iterator(); iterator.hasNext();) {
			String mapKey = (String) iterator.next();
			String value=treeMap.get(mapKey);
			if(value==null || value.isEmpty())continue;
			if (result.length() > 0)
				result.append("&");

			result.append(mapKey);
			result.append("=");
			result.append(value);

		}
		if(appendValue!=null)
			result.append(appendValue);
		String sign= Utils.getMD5(result.toString());
		Logger.E("拼接信息:"+result.toString()+" 签名信息:"+sign);
		params.put(key, sign);

		return this;
	}
	/**
	 * 放置请求文件对象
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public NAction put(String key, File[] value) {
		dealNull();

		for (int i = 0,j=0,k=0; i < value.length; i++) {
			try {
				if(value[i].toString().startsWith("http")){
					params.put(String.format("%s[%d]", key, i), value[i].toString());
				}else{
					params.put(String.format("%s[%d]", key, i), value[i]);
				}
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}
		}

		return this;
	}

	private void dealNull() {
		if (params == null)
			params = new RequestParams();
		// params.
	}

	public NAction setRequestCode(int requestCode) {
		this.requestCode = requestCode;
		return this;
	}

	@Override
	public int hashCode() {
		return new StringBuffer(url).append(requestCode).append(params)
				.toString().hashCode();
	}

	@Override
	public String toString() {
		return new StringBuffer().append('[').append("requestCode=")
				.append(requestCode).append(',').append("requestType=")
				.append(requestType).append(',').append("url").append(url)
				.append(',').append("params").append(params).append(']')
				.toString();
	}

}
