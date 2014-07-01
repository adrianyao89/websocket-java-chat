package com.adrian.webchat.common.utils;

import java.io.IOException;

import org.springframework.web.socket.TextMessage;

import com.adrian.webchat.bean.request.Header;
import com.adrian.webchat.bean.response.Response;
import com.google.gson.Gson;

public class ResponseUtil {
	
	public static <T extends Response<?>> void send(T t) {
		try {
			Header header = RequestContextManager.getHeader();
			boolean needACK = header == null ? false : header.getUid() == null ? false : true;
			if (needACK) {
				RequestContextManager.getSession().sendMessage(new TextMessage(new Gson().toJson(t).getBytes()));
			}
		} catch (IOException e) {
		}
	}
}
