package com.adrian.webchat.dao;

import com.adrian.webchat.bean.request.RGroupMessage;

public interface GroupMessageService {
	void sendToGroup(RGroupMessage groupMessage);
}
