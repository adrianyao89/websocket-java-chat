package com.adrian.webchat.bean.request;

import java.io.Serializable;

public class Header implements Serializable {
	
	private static final long serialVersionUID = 5621500267564780930L;
	
	private String uid;
	
	private String type;
	
	private String token;
	
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
