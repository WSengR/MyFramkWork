package com.sunrun.sunrunframwork.bean;

import java.io.Serializable;

public class SerializableBean implements Serializable {
	public String id="";
	/** */
	public static final long serialVersionUID = 1180329787328184786L;

	@Override
	public int hashCode() {
		if (id != null){
			return String.valueOf(id).hashCode();
		}
		return super.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof SerializableBean) { return hashCode() == o.hashCode(); }
		return super.equals(o);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
