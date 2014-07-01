package com.adrian.webchat.bean.request;

import java.io.Serializable;

public class Header implements Serializable {
	
	private static final long serialVersionUID = 5621500267564780930L;
	
	private String uid;
	
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
}
