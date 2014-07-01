package com.adrian.webchat.service;

import com.adrian.webchat.bean.model.MUser;
import com.adrian.webchat.bean.request.RUser;
import com.adrian.webchat.exception.ChatException;

public interface UserService {

	MUser login(RUser user);

	MUser register(RUser user) throws ChatException;

}
