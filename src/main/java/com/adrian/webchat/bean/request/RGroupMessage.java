package com.adrian.webchat.bean.request;

import java.io.Serializable;

public class RGroupMessage implements Serializable {

	private static final long serialVersionUID = 6230611232056232279L;
	
	private int groupId;
	
	private int userId;
	
	private String name;
	
	private String message;

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
