package com.adrian.webchat.dao;

import com.adrian.webchat.bean.model.MUser;
import com.adrian.webchat.bean.request.RUser;

public interface UserDao {

	MUser getUser(RUser user);

	MUser addUser(RUser user);
}
