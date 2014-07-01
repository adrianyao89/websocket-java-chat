package com.adrian.webchat.extension.protocol.convertor;

import com.adrian.webchat.bean.request.Protocol;
import com.google.gson.Gson;

public class DefaultConvertor implements Convertible {

	@Override
	public Protocol convert(String text) {
		Protocol protocol = null;
		if (text != null) {
			try {
				protocol = new Gson().fromJson(text, Protocol.class);
			} catch (Exception e) {
			}
		}
		return protocol;
	}

}
