package com.adrian.webchat.processer.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.adrian.webchat.bean.model.MUser;
import com.adrian.webchat.bean.request.Protocol;
import com.adrian.webchat.bean.request.RUser;
import com.adrian.webchat.bean.response.Response;
import com.adrian.webchat.bean.response.SUser;
import com.adrian.webchat.common.constants.StateCode;
import com.adrian.webchat.common.utils.BeanUtils;
import com.adrian.webchat.common.utils.MessageUtil;
import com.adrian.webchat.exception.ChatException;
import com.adrian.webchat.processer.Processer;
import com.adrian.webchat.service.UserService;

@Component("oauth_login")
public class LoginProcesser implements Processer {
	
	@Resource
	private UserService userService;

	@Override
	public void handleRequest(Protocol protocol) {
		Map<String, Object> data = protocol.getModule().getData();
		MUser userdb = userService.login(BeanUtils.copyProperties(new RUser(), data));
		if (null == userdb) {
			MessageUtil.response(new Response<SUser>(StateCode.USER_NOT_FOUND));
			return;
		}
		MessageUtil.response(new Response<SUser>(StateCode.COMPLETED, BeanUtils.copyProperties(new SUser(), userdb)));
	}

	@Override
	public void handleResponse(Protocol protocol) throws ChatException {
		
	}
}
