package com.adrian.webchat.bean.model;

import java.io.Serializable;

public class MUser implements Serializable {
	
	private static final long serialVersionUID = -700125725786704299L;

	private int id;
	
	private String username;
	
	private String password;
	
	private String name;

	private String icon;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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
