package com.adrian.webchat.bean.response;

import java.io.Serializable;

import com.adrian.webchat.common.constants.StateCode;

public class Response<T>  implements Serializable {
	
	private static final long serialVersionUID = -4515222765281420692L;

	private String uid;
	
	private State state;
	
	private T data;
	
	public Response() {
	}
	
	public Response(StateCode code) {
		this.state = new State(code.code());
	}
	
	public Response(StateCode code, String message) {
		this.state = new State(code.code(), message);
	}

	public Response(T data) {
		this.data = data;
	}
	
	public Response(StateCode code, T data) {
		this.state = new State(code.code());
		this.data = data;
	}
	
	public Response(StateCode code, String message, T data) {
		this.state = new State(code.code(), message);
		this.data = data;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
	
}
