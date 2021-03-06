package com.adrian.webchat.common.utils;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.socket.WebSocketSession;

import com.adrian.webchat.extension.security.Token;

public class ActiveWebSocketSessionPool {
	
	private static final Map<String, WebSocketSession> anonymousSessions = new ConcurrentHashMap<String, WebSocketSession>();
	
	private static final Map<Integer, WebSocketSession> sessions = new ConcurrentHashMap<Integer, WebSocketSession>();
	
	private static final Map<WebSocketSession, Token> authenticatedSessions = new ConcurrentHashMap<WebSocketSession, Token>();
	
	public static void add(int userId, WebSocketSession session) {
		sessions.put(userId, session);
	}
	
	public static void remove(int userId) {
		sessions.remove(userId);
	}
	
	public static WebSocketSession get(Integer id) {
		return sessions.get(id);
	}
	
	public static Set<Integer> getKeys() {
		return sessions.keySet();
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

	public static Token getAuthToken(WebSocketSession session) {
		return authenticatedSessions.get(session);
	}
	
	public static void addAuthSession(WebSocketSession session, Token token) {
		authenticatedSessions.put(session, token);
	}
	
	public static void removeAuthSession(WebSocketSession session) {
		authenticatedSessions.remove(session);
	}
}
