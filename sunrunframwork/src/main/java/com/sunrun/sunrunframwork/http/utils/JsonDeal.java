package com.sunrun.sunrunframwork.http.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.sunrun.sunrunframwork.bean.BaseBean;
import com.sunrun.sunrunframwork.utils.EmptyDeal;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @Json数据处理类
 */
public class JsonDeal {

	/** 常用请求码 只显示提示信息 */
	public static final int MESSAGE_CODE = 0x6cc9;// 字符串消息的JSON
	public static final int MESSAGE_CODE_TOAST = 0x6cc8;// 字符串消息的JSON,自动提示

	/** json状态字段 */
	public final static String STATUS = "status";
	/** json信息字段 */
	public final static String INFO = "info";
	/** json消息字段 */
	public final static String MSG = "msg";
	public final static String REQUEST_TIME = "post_time";
	/** 请求提示信息 */
	public static final String ERR_MSG = "请求失败,请检查网络是否连接";
	public static final String CANCLE_MSG = "请求取消";
	public static final String LODING_MSG = "正在加载..";
	public static final String LODING_FAILD_MSG = "加载失败";
	public static final String EMPTY_MSG = "暂无数据";

	/**
	 * 从json数据创建BaseBean
	 *
	 * @param json 格式 {status:0,info:{},msg:"哈哈",post_time:"2016-03-25 11:24:00"}
	 * @param clazz
	 *            INFO 字段需要转换成的bean实体类字节码
	 * @return
	 */
	public static BaseBean createBean(String json, Class<?> clazz) {
		JSONObject jobj = createJsonObj(json);
		BaseBean bean = new BaseBean();
		bean.status = jobj.optInt(STATUS);
		bean.msg = jobj.optString(MSG);
		Object obj =  jobj.opt(INFO) ;//获取info中的值作为数据
		bean.post_time = jobj.optString(REQUEST_TIME);
		bean.data = json2Object(String.valueOf(obj), clazz);
		return bean;
	}

	/**
	 * 从json数据创建BaseBean
	 *
	 * @param json  格式 {status:0,info:null,msg:"哈哈",post_time:"2016-03-25 11:24:00"}  info格式为[]或{};
	 * @param typeToken INFO 字段需要转换成的实体类型
	 * @return
	 */
	public static <T> BaseBean createBean(String json, TypeToken<T> typeToken) {// 创建Bean
		JSONObject jobj = createJsonObj(json);
		BaseBean bean = new BaseBean();
		bean.status = jobj.optInt(STATUS);
		bean.msg = jobj.optString(MSG);
		Object obj = jobj.opt(INFO) ;//获取info中的值作为数据
		bean.post_time = jobj.optString(REQUEST_TIME);
		bean.data = json2Object(String.valueOf(obj), typeToken);
		return bean;
	}

	/**
	 * 从json数据创建BaseBean
	 * @param json 格式 {status:0,info:null,msg:"哈哈",post_time:"2016-03-25 11:24:00"}
	 * @return
	 */
	public static <T> BaseBean createBean(String json) {// 创建JSONObject对象
		JSONObject jobj = createJsonObj(json);
		BaseBean bean = new BaseBean();
		bean.status = jobj.optInt(STATUS);
		bean.msg = jobj.optString(MSG);
		Object obj = jobj.has(INFO) ? jobj.opt(INFO) : json;//存在info字段时,返回info中的json字符串,否则返回整个json字符串
		bean.post_time = jobj.optString(REQUEST_TIME);
		bean.data = String.valueOf(obj);
		return bean;
	}

	/**
	 * 创建JSONObject对象
	 *
	 * @param json
	 * @return
	 */
	public static JSONObject createJsonObj(String json) {//
		JSONObject obj = new JSONObject();
		try {
			obj = new JSONObject(json);
		} catch (JSONException e) {
			Logger.E("创建jsonobject对象失败:" + e);
			e.printStackTrace();
		}
		return obj;
	}

	/**
	 * json转对象
	 *
	 * @param json
	 * @param type
	 *            对象类型,可以是List<Bean> <br\>
	 *            用法eg:List<Bean> beans=json2Object(json,new
	 *            TypeToken<List<Bean>>(){}); <br\>
	 *            用法eg:Bean bean=json2Object(json,new TypeToken<Bean>(){});
	 * @return
	 */

	public static <T> T json2Object(String json, TypeToken<T> type) {
		if (json == null){
			return newType(type);
		}
		Gson gson = new Gson();
		try {
			return gson.fromJson(json, type.getType());
		} catch (Exception e) {
			e.printStackTrace();
			Logger.E("数据解析异常" + e + " " + json);
		}
		return newType(type);
	}

	/**
	 * json转对象
	 *
	 * @param json
	 * @param cls
	 *            只能是对象 Bean.class<br\>
	 *            用法eg:Bean bean=json2Object(json,Bean.class);
	 * @return
	 */
	public static <T> T json2Object(String json, Class<T> cls) {
		if (json == null || json.equals(""))
		{
			return newType(cls);
		}
		try {
			Gson gson = new Gson();
			return gson.fromJson(json, cls);
		} catch (Exception e) {
			e.printStackTrace();
			Logger.E("数据解析异常" + e + " " + json);
		}
		return newType(cls);
	}
	static <T> T newType(TypeToken type) {
		Class<?> clazz = type.getRawType();
		if (clazz == List.class) { return (T) new ArrayList(); }
		return newType(clazz);
	}

	static <T>  T newType(Class<?> clazz){
		try {
			return (T) clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;

	}
	/**
	 * 对象转json
	 *
	 * @param obj
	 * @return
	 */
	public static String object2Json(Object obj) {
		if (EmptyDeal.isEmpy(obj))
			return "";
		Gson gson = new Gson();
		return gson.toJson(obj);
	}

	/**
	 * 对象转json 排除非Expose 注解模式
	 *
	 * @param obj
	 * @return
	 */
	public static String expose2Json(Object obj) {
		if (EmptyDeal.isEmpy(obj))
			return "";
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		return gson.toJson(obj);
	}
}
