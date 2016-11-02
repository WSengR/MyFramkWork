package com.sunrun.sunrunframwork.http.cache;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.sunrun.sunrunframwork.http.utils.JsonDeal;

import java.io.Serializable;

/**
 * 网络缓存
 * @author cnsunrun
 */
public class NetSession {
	static NetSession _session;

	private NetSession(Context context) {
		acache = ACache.get(context);
	};

	public static NetSession instance(Context context) {
		if (_session == null)
			synchronized (NetSession.class) {
				if (_session == null)
					_session = new NetSession(context);
			}
		return _session;
	}

	ACache acache;

	public <T> T getObject(String key, Class<T> clazz) {
		// TODO Auto-generated method stub
		return JsonDeal.json2Object(acache.getAsString(key), clazz);
	}

	public <T> T getBean(String key, TypeToken<T> type) {
		// TODO Auto-generated method stub
		return JsonDeal.json2Object(acache.getAsString(key), type);
	}

	public Integer getInt(String key) {
		// TODO Auto-generated method stub
		Integer value = (Integer) acache.getAsObject(key);
		return value == null ? Integer.MAX_VALUE : value;
	}

	public Float getFloat(String key) {
		// TODO Auto-generated method stub
		return (Float) acache.getAsObject(key);
	}

	public Double getDouble(String key) {
		// TODO Auto-generated method stub
		return (Double) acache.getAsObject(key);
	}

	public String getString(String key) {
		// TODO Auto-generated method stub
		return acache.getAsString(key);
	}

	public Boolean getBoolean(String key) {
		// TODO Auto-generated method stub
		return (Boolean) acache.getAsObject(key);
	}

	public void remove(String key) {
		acache.remove(key);
	}

	public void removeAll() {
		acache.clear();
	}

	public boolean hasValue(String key) {
		return acache.file(key) != null;
	}

	public void put(String key, Object value) {
		if (value instanceof Number || value instanceof Boolean) {
			acache.put(key, (Serializable) value);
		} else if (value instanceof String) {

			acache.put(key, String.valueOf(value));
		} else {
			acache.put(key, JsonDeal.object2Json(value));
		}
	}

}
