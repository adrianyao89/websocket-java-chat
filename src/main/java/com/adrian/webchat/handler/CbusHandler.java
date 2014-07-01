package com.adrian.webchat.handler;


import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.adrian.webchat.bean.request.Protocol;
import com.adrian.webchat.bean.response.Response;
import com.adrian.webchat.common.constants.StateCode;
import com.adrian.webchat.common.utils.ActiveWebSocketSessionPool;
import com.adrian.webchat.common.utils.RequestContext;
import com.adrian.webchat.common.utils.RequestContextManager;
import com.adrian.webchat.common.utils.ResponseUtil;
import com.adrian.webchat.common.utils.SpringContextUtil;
import com.adrian.webchat.exception.ChatException;
import com.adrian.webchat.extension.protocol.convertor.Convertible;
import com.adrian.webchat.extension.protocol.validator.Validator;
import com.adrian.webchat.processer.Processer;

@Component("cbus")
public class CbusHandler extends TextWebSocketHandler {

	@Resource
	private Convertible protocolConvertible;
	
	@Resource
	private Validator protocolValidator;
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		ActiveWebSocketSessionPool.add(session);
	}
	
	@Override
	public void handleTextMessage(WebSocketSession session,
			TextMessage message) {
		Protocol protocol = protocolConvertible.convert(message.getPayload());
		if (protocol == null || !protocolValidator.validate(protocol)) {
			ResponseUtil.send(new Response(StateCode.PROTOCOL_ERROR));
			return;
		}

		RequestContextManager.add(new RequestContext(protocol.getHeader(), session));
		
		Processer processer = SpringContextUtil.getBean(protocol.getModule().getName() + "_" + protocol.getModule().getAction(), Processer.class);
		if (processer != null) {
			try {
				processer.handle(protocol.getModule().getData());
			} catch (ChatException e) {
				ResponseUtil.send(new Response(e.getCode()));
				return;
			}
		} else {
			ResponseUtil.send(new Response(StateCode.NOT_SERVICE));
		}
		RequestContextManager.remove();
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session,
			CloseStatus status) throws Exception {
		ActiveWebSocketSessionPool.remove(session.getId());
	}

}
