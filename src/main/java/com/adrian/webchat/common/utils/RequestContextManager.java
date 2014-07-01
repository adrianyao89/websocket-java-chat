package com.adrian.webchat.common.utils;

import org.springframework.web.socket.WebSocketSession;

import com.adrian.webchat.bean.request.Header;


public class RequestContextManager {
	
	private static final ThreadLocal<RequestContext> session = new ThreadLocal<RequestContext>();
	
	public static void add(RequestContext context) {
		session.set(context);
	}
	
	public static RequestContext get() {
		return session.get();
	}
	
	public static void remove() {
		session.remove();
	}
	
	public static Header getHeader() {
		return get() == null ? null : get().getHeader() == null ? null : get().getHeader();
	}
	
	public static WebSocketSession getSession() {
		return get() == null ? null : get().getSession() == null ? null : get().getSession();
	}
}
