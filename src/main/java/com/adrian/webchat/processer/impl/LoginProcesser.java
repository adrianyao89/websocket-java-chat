package com.adrian.webchat.processer.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.adrian.webchat.bean.model.MUser;
import com.adrian.webchat.bean.request.RUser;
import com.adrian.webchat.bean.response.Response;
import com.adrian.webchat.bean.response.SUser;
import com.adrian.webchat.common.constants.StateCode;
import com.adrian.webchat.common.utils.BeanUtils;
import com.adrian.webchat.common.utils.ResponseUtil;
import com.adrian.webchat.processer.Processer;
import com.adrian.webchat.service.UserService;

@Component("oauth_login")
public class LoginProcesser implements Processer {
	
	@Resource
	private UserService userService;

	@Override
	public void handle(Map<String, Object> data) {
		MUser userdb = userService.login(BeanUtils.copyProperties(new RUser(), data));
		Response<SUser> response = new Response<SUser>(StateCode.COMPLETED, BeanUtils.copyProperties(new SUser(), userdb));
		ResponseUtil.send(response);
	}
}
