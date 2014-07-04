package com.adrian.webchat.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import com.adrian.webchat.bean.request.Module;
import com.adrian.webchat.bean.request.RGroupMessage;
import com.adrian.webchat.common.utils.ActiveWebSocketSessionPool;
import com.adrian.webchat.common.utils.BeanUtils;
import com.adrian.webchat.common.utils.MessageUtil;
import com.adrian.webchat.service.GroupMessageService;

@Service
public class GroupMessageServiceImpl implements GroupMessageService {

	@Override
	public void sendToGroup(RGroupMessage groupMessage) {
		int userid = groupMessage.getUserId();
		
		for (Integer id : ActiveWebSocketSessionPool.getKeys()) {
			WebSocketSession session = ActiveWebSocketSessionPool.get(id);
			if (userid != id && session != null) {
				try {
					MessageUtil.request(session, new Module().setAction("group").setName("chat").setData(BeanUtils.describe(groupMessage)));
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}
	}

}
