package com.adrian.webchat.processer;

import java.util.Map;

import com.adrian.webchat.exception.ChatException;

public interface Processer {
	
	void handle(Map<String, Object> data) throws ChatException;
}
