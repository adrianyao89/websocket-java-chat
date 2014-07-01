package com.adrian.webchat.common.constants;

public enum StateCode {

	COMPLETED(200),
	SERVER_ERROR(500),
	PROTOCOL_ERROR(400),
	NOT_SERVICE(404),
	USER_REGISTERED(10000),
	USER_REGISTER_ERROR(10001), ;
	
	private int code;
	
	private StateCode(int code) {
		this.code = code;
	}
	
	public int code() {
		return code;
	}
}
