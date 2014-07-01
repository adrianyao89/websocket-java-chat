package com.adrian.webchat.extension.router;

import java.lang.reflect.Method;

import com.adrian.webchat.bean.request.Protocol;
import com.adrian.webchat.bean.response.Response;
import com.adrian.webchat.common.constants.ProtocolType;
import com.adrian.webchat.common.constants.StateCode;
import com.adrian.webchat.common.utils.MessageUtil;
import com.adrian.webchat.common.utils.SpringContextUtil;
import com.adrian.webchat.exception.ChatException;
import com.adrian.webchat.processer.Processer;

public class DefaultRouter implements Router {
	
	private ProtocolType protocolType;

	@Override
	public void route(Protocol protocol) {
		Processer processer = SpringContextUtil.getBean(protocol.getModule().getName() + "_" + protocol.getModule().getAction(), Processer.class);
		String type = protocol.getHeader().getType();
		if (processer != null && protocolType.getTypes().contains(type)) {
			try {
				Method method = processer.getClass().getMethod("handle" + type.substring(0, 1).toUpperCase() + type.substring(1, type.length()), Protocol.class);
				method.invoke(processer, protocol);
			} catch (ChatException e) {
				MessageUtil.response(new Response(e.getCode()));
				return;
			} catch (Exception e) {
				MessageUtil.response(new Response(StateCode.NOT_SERVICE));
				return;
			}
		} else {
			MessageUtil.response(new Response(StateCode.NOT_SERVICE));
			return;
		}
	}

	public void setProtocolType(ProtocolType protocolType) {
		this.protocolType = protocolType;
	}
}
