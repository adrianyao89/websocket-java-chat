package com.adrian.webchat.common.utils;

import org.springframework.web.socket.WebSocketSession;

import com.adrian.webchat.bean.request.Header;


public class RequestContext {
	
	private WebSocketSession session;
	
	private Header header;
	
	public RequestContext() {
	}
	
	public RequestContext(Header header, WebSocketSession session) {
		this.session = session;
		this.header = header;
	}

	public WebSocketSession getSession() {
		return session;
	}

	public void setSession(WebSocketSession session) {
		this.session = session;
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

}
