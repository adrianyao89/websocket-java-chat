package com.adrian.webchat.handler;


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
import com.adrian.webchat.common.utils.MessageUtil;
import com.adrian.webchat.extension.protocol.convertor.Convertible;
import com.adrian.webchat.extension.protocol.validator.Validator;
import com.adrian.webchat.extension.router.Router;
import com.adrian.webchat.extension.security.Authentication;
import com.adrian.webchat.extension.security.Token;

@SuppressWarnings("rawtypes")
public class CbusHandler extends TextWebSocketHandler {

	private Convertible protocolConvertor;
	
	private Validator protocolValidator;
	
	private Router router;
	
	private Authentication authentication;
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("estable");
		ActiveWebSocketSessionPool.addAn(session);
	}
	
	@Override
	public void handleTextMessage(WebSocketSession session,
			TextMessage message) {
		Protocol protocol = protocolConvertor.convert(message.getPayload());
		if (protocol == null || !protocolValidator.validate(protocol)) {
			MessageUtil.response(new Response(StateCode.PROTOCOL_ERROR));
			return;
		}
		
		RequestContextManager.add(new RequestContext(protocol.getHeader(), session));
		
		if (authentication.authenticate(protocol)) {
			router.route(protocol);
		} else {
			MessageUtil.response(new Response(StateCode.PERMISSION_DENY));
		}


		RequestContextManager.remove();
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session,
			CloseStatus status) throws Exception {
		ActiveWebSocketSessionPool.removeAn(session);
		Token token = ActiveWebSocketSessionPool.getAuthToken(session);
		ActiveWebSocketSessionPool.remove(null == token ? null : token.getUserId());
		ActiveWebSocketSessionPool.removeAuthSession(session);
		
	}

	public void setProtocolConvertor(Convertible protocolConvertor) {
		this.protocolConvertor = protocolConvertor;
	}

	public void setProtocolValidator(Validator protocolValidator) {
		this.protocolValidator = protocolValidator;
	}

	public void setRouter(Router router) {
		this.router = router;
	}

	public void setAuthentication(Authentication authentication) {
		this.authentication = authentication;
	}
	
}
