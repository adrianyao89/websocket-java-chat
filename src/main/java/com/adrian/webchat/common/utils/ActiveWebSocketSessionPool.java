package com.adrian.webchat.common.utils;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.web.socket.WebSocketSession;

public class ActiveWebSocketSessionPool {
	
	private static final Map<String, WebSocketSession> sessions = new LinkedHashMap<String, WebSocketSession>();
	
	private static final Map<String, WebSocketSession> anonymousSessions = new LinkedHashMap<String, WebSocketSession>();
	
	public static void add(WebSocketSession session) {
		sessions.put(session.getId(), session);
	}
	
	public static void remove(String id) {
		sessions.remove(id);
	}
	
	public static WebSocketSession get(String id) {
		return sessions.get(id);
	}
	
	public static void addAn(WebSocketSession session) {
		anonymousSessions.put(session.getId(), session);
	}
	
	public static void removeAn(String id) {
		anonymousSessions.remove(id);
	}
	
	public static WebSocketSession getAn(String id) {
		return anonymousSessions.get(id);
	}
	
	public static Collection<WebSocketSession> getAnAll() {
		return anonymousSessions.values();
	}
}
