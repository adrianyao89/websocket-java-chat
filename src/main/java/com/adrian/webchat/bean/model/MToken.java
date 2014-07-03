package com.adrian.webchat.bean.model;

import java.util.Date;

public class MToken {
	private int userId;
	
	private MUser mUser;
	
	private String token;
	
	private Date expired;
	
	public MToken() {
	}
	
	public MToken(int userId, String token, Date expired) {
		this.userId = userId;
		this.token = token;
		this.expired = expired;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

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

	public MUser getmUser() {
		return mUser;
	}

	public void setmUser(MUser mUser) {
		this.mUser = mUser;
	}
}
