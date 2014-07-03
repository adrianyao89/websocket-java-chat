package com.adrian.webchat.processer.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.adrian.webchat.bean.request.Protocol;
import com.adrian.webchat.bean.request.RGroupMessage;
import com.adrian.webchat.common.utils.BeanUtils;
import com.adrian.webchat.dao.GroupMessageService;
import com.adrian.webchat.exception.ChatException;
import com.adrian.webchat.processer.Processer;

@Component("chat_group")
public class GroupChatProcesser implements Processer {
	
	@Resource
	private GroupMessageService groupMessageService;

	@Override
	public void handleRequest(Protocol protocol) {
		Map<String, Object> data = protocol.getModule().getData();
		RGroupMessage message = BeanUtils.copyProperties(new RGroupMessage(), data);
		groupMessageService.sendToGroup(message);
	}

	@Override
	public void handleResponse(Protocol protocol) throws ChatException {
		
	}
}
