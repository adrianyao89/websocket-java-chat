package com.adrian.webchat.service;

import com.adrian.webchat.bean.request.RGroupMessage;

public interface GroupMessageService {
	void sendToGroup(RGroupMessage groupMessage);
}
