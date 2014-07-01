package com.adrian.webchat.common.utils;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.web.socket.WebSocketSession;

import com.adrian.webchat.bean.model.MToken;

public class ActiveWebSocketSessionPool {
	
	private static final Map<String, WebSocketSession> anonymousSessions = new LinkedHashMap<String, WebSocketSession>();
	
	private static final Map<Integer, WebSocketSession> sessions = new LinkedHashMap<Integer, WebSocketSession>();
	
	private static final Map<WebSocketSession, MToken> authenticatedSessions = new LinkedHashMap<WebSocketSession, MToken>();
	
	public static void add(int userId, WebSocketSession session) {
		sessions.put(userId, session);
	}
	
	public static void remove(int userId) {
		sessions.remove(userId);
	}
	
	public static WebSocketSession get(String id) {
		return sessions.get(id);
	}
	
	public static void addAn(WebSocketSession session) {
		anonymousSessions.put(session.getId(), session);
	}
	
	public static void removeAn(WebSocketSession session) {
		anonymousSessions.remove(session.getId());
	}
	
	public static WebSocketSession getAn(String id) {
		return anonymousSessions.get(id);
	}
	
	public static Collection<WebSocketSession> getAnAll() {
		return anonymousSessions.values();
	}

	public static MToken getAuthToken(WebSocketSession session) {
		return authenticatedSessions.get(session);
	}
	
	public static void addAuthSession(WebSocketSession session, MToken mToken) {
		authenticatedSessions.put(session, mToken);
	}
	
	public static void removeAuthSession(WebSocketSession session) {
		authenticatedSessions.remove(session);
	}
}
