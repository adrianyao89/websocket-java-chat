package com.adrian.webchat.dao;

import com.adrian.webchat.bean.model.MUser;

public interface UserDao {
	
	MUser getUser(MUser user);

	MUser addUser(MUser user);
}
