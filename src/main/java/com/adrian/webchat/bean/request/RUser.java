package com.adrian.webchat.bean.request;

import java.io.Serializable;

public class RUser implements Serializable {
	
	private static final long serialVersionUID = -8222520096014587208L;

	private String username;
	
	private String password;
	
	private String name;

	private String icon;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

}
