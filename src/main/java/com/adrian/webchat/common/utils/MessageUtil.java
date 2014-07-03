package com.adrian.webchat.common.utils;

import java.io.IOException;
import java.util.UUID;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.adrian.webchat.bean.request.Header;
import com.adrian.webchat.bean.request.Module;
import com.adrian.webchat.bean.request.Protocol;
import com.adrian.webchat.bean.response.Response;
import com.google.gson.Gson;

public class MessageUtil {
	
	public static <T extends Response<?>> void response(T t) {
		try {
			Header header = RequestContextManager.getHeader();
			boolean needACK = header == null ? false : header.getUid() == null ? false : true;
			if (needACK) {
				Protocol protocol = new Protocol();
				header.setType("response");
				protocol.setHeader(header);
				Module module = new Module();
				module.setResponse(t);
				protocol.setModule(module);
				RequestContextManager.getSession().sendMessage(new TextMessage(new Gson().toJson(protocol).getBytes()));
			}
		} catch (IOException e) {
		}
	}
	
	public static void request(Module module) {
		request(module, false);
	}
	
	public static void request(Module module, boolean needACK) {
		try {
				Protocol protocol = new Protocol();
				Header header = new Header();
				header.setType("request");
				header.setUid(needACK ? UUID.randomUUID().toString() : null);
				protocol.setHeader(header);
				protocol.setModule(module);
				RequestContextManager.getSession().sendMessage(new TextMessage(new Gson().toJson(protocol).getBytes()));
		} catch (IOException e) {
		}
	}
	
	public static void request(WebSocketSession session, Module module) {
		request(session, module, false);
	}
	
	public static void request(WebSocketSession session, Module module, boolean needACK) {
		try {
				Protocol protocol = new Protocol();
				Header header = new Header();
				header.setType("request");
				header.setUid(needACK ? UUID.randomUUID().toString() : null);
				protocol.setHeader(header);
				protocol.setModule(module);
				session.sendMessage(new TextMessage(new Gson().toJson(protocol).getBytes()));
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
