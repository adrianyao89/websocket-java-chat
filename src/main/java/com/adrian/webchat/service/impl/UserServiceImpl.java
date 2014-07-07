package com.adrian.webchat.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.adrian.webchat.bean.model.MUser;
import com.adrian.webchat.bean.request.RUser;
import com.adrian.webchat.common.constants.StateCode;
import com.adrian.webchat.common.utils.BeanUtils;
import com.adrian.webchat.dao.UserDao;
import com.adrian.webchat.exception.ChatException;
import com.adrian.webchat.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;
	
	@Override
	public MUser login(RUser user) {
		MUser userDb = userDao.getUser(BeanUtils.copyProperties(new MUser(), user));
		if (userDb == null) {
			return null;
		}
		System.out.println(userDb.getName());
		if (userDb.getPassword().equals(user.getPassword())) {
			return userDb;
		} else {
			return null;
		}
	}
	
	@Override
	public MUser register(RUser user) throws ChatException {
		MUser muser = BeanUtils.copyProperties(new MUser(), user);
		MUser userDb = userDao.getUser(muser);
		if (userDb != null) {
			throw new ChatException(StateCode.USER_REGISTERED, "用户名已被注册");
		} else {
			MUser userUpdated = userDao.addUser(muser);
			if (userUpdated != null) {
				userDb = userUpdated;
			} else {
				throw new ChatException(StateCode.USER_REGISTER_ERROR, "用户注册失败");
			}
		}
		return userDb;

	}
}
