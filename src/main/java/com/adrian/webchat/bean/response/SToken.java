package com.adrian.webchat.bean.response;

import java.util.Date;

public class SToken {
	
	private String token;
	
	private Date expired;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getExpired() {
		return expired;
	}

	public void setExpired(Date expired) {
		this.expired = expired;
	}
}
