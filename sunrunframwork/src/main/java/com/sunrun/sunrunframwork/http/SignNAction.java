package com.sunrun.sunrunframwork.http;

import java.io.File;

import com.google.gson.reflect.TypeToken;

/**
 * NAction 扩展
 * @author WQ 下午5:22:41
 */
public class SignNAction extends NAction {
	@Override
	public SignNAction putSign(String key, String appendValue) {
		return (SignNAction) super.putSign(key, appendValue);
	}
	@Override
	public SignNAction put(Object[] value) {
		// TODO Auto-generated method stub
		return (SignNAction) super.put(value);
	}
	@Override
	public SignNAction put(String key, File value) {
		// TODO Auto-generated method stub
		return (SignNAction) super.put(key, value);
	}
	@Override
	public SignNAction put(String key, Object value) {
		// TODO Auto-generated method stub
		return (SignNAction) super.put(key, value);
	}
	@Override
	public SignNAction put(String key, File[] value) {
		return (SignNAction) super.put(key, value);
	}
	
	@Override
	public SignNAction setUseCache() {
		// TODO Auto-generated method stub
		return (SignNAction) super.setUseCache();
	}
	@Override
	public SignNAction cachePriority(boolean bool) {
		// TODO Auto-generated method stub
		return (SignNAction) super.cachePriority(bool);
	}
	@Override
	public SignNAction setResultDataType(Class<?> resultDataType) {
		// TODO Auto-generated method stub
		return (SignNAction) super.setResultDataType(resultDataType);
	}
	@Override
	public SignNAction setUrl(String url) {
		// TODO Auto-generated method stub
		return (SignNAction) super.setUrl(url);
	}
	@Override
	public <T> SignNAction setTypeToken(TypeToken<T> typeToken) {
		// TODO Auto-generated method stub
		return (SignNAction) super.setTypeToken(typeToken);
	}
	@Override
	public <T> SignNAction setTypeToken(Class<?> resultDataType) {
		// TODO Auto-generated method stub
		return (SignNAction) super.setTypeToken(resultDataType);
	}
	@Override
	public SignNAction setRequestType(int requestType) {
		// TODO Auto-generated method stub
		return (SignNAction) super.setRequestType(requestType);
	}
	@Override
	public SignNAction setTag(Object tag) {
		// TODO Auto-generated method stub
		return (SignNAction) super.setTag(tag);
	}
	@Override
	public SignNAction setRequestCode(int requestCode) {
		// TODO Auto-generated method stub
		return (SignNAction) super.setRequestCode(requestCode);
	}
	public SignNAction	putSign(){
		putSign("sign", BaseConfig.getLoginInfo().getSign_key());
		return this;
	}
}
