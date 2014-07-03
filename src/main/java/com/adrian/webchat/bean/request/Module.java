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
	public Module setName(String name) {
		this.name = name;
		return this;
	}
	public String getAction() {
		return action;
	}
	public Module setAction(String action) {
		this.action = action;
		return this;
	}
	public Map<String, Object> getData() {
		return data;
	}
	public Module setData(Map<String, Object> data) {
		this.data = data;
		return this;
	}
	public Response<?> getResponse() {
		return response;
	}
	public Module setResponse(Response<?> response) {
		this.response = response;
		return this;
	}
	
}
