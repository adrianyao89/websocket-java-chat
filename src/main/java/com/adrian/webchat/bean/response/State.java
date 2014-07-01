package com.adrian.webchat.bean.response;

import java.io.Serializable;

public class State implements Serializable {

	private static final long serialVersionUID = 1411434818475511564L;
	private int code;
	private String message;
	private String description;
	
	public State() {
	}
	
	public State(int code) {
		this.code = code;
	}
	
	public State(String message) {
		this.message = message;
	}
	
	public State(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
