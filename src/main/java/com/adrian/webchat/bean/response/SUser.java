package com.adrian.webchat.bean.response;

import java.io.Serializable;

public class SUser implements Serializable {
	
	private static final long serialVersionUID = -8222520096014587208L;
	
	private int id;
	
	private String name;

	private String icon;
	
	private SToken token;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public SToken getToken() {
		return token;
	}

	public void setToken(SToken token) {
		this.token = token;
	}
}
