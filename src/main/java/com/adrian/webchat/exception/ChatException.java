package com.adrian.webchat.exception;

import com.adrian.webchat.common.constants.StateCode;

public class ChatException extends RuntimeException {

	private static final long serialVersionUID = 8578634394481745051L;
	
	private StateCode code;
	
	private String message;
	
	private String description;
	
	public ChatException(StateCode code) {
		this.code = code;
	}
	
	public ChatException(StateCode code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public ChatException(StateCode code, String message, String description) {
		this.code = code;
		this.message = message;
		this.description = description;
	}

	public StateCode getCode() {
		return code;
	}

	public void setCode(StateCode code) {
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
