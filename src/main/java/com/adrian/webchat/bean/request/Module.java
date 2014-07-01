package com.adrian.webchat.bean.request;

import java.io.Serializable;
import java.util.Map;

import com.adrian.webchat.bean.response.Response;

public class Module implements Serializable {
	
	private static final long serialVersionUID = 8311075172608713058L;
	private String name;
	private String action;
	private Map<String, Object> data;
	private Response<?> response;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	public Response<?> getResponse() {
		return response;
	}
	public void setResponse(Response<?> response) {
		this.response = response;
	}
	
}
