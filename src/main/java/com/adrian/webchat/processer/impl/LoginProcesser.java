package com.adrian.webchat.processer.impl;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.adrian.webchat.bean.model.MToken;
import com.adrian.webchat.bean.model.MUser;
import com.adrian.webchat.bean.request.Protocol;
import com.adrian.webchat.bean.request.RUser;
import com.adrian.webchat.bean.response.Response;
import com.adrian.webchat.bean.response.SToken;
import com.adrian.webchat.bean.response.SUser;
import com.adrian.webchat.common.constants.StateCode;
import com.adrian.webchat.common.utils.ActiveWebSocketSessionPool;
import com.adrian.webchat.common.utils.BeanUtils;
import com.adrian.webchat.common.utils.MessageUtil;
import com.adrian.webchat.common.utils.RequestContextManager;
import com.adrian.webchat.dao.TokenDao;
import com.adrian.webchat.exception.ChatException;
import com.adrian.webchat.extension.security.Token;
import com.adrian.webchat.extension.security.User;
import com.adrian.webchat.processer.Processer;
import com.adrian.webchat.service.UserService;

@Component("oauth_login")
public class LoginProcesser implements Processer {
	
	@Resource
	private UserService userService;
	
	@Resource
	private TokenDao tokenDao;

	@Override
	public void handleRequest(Protocol protocol) {
		Map<String, Object> data = protocol.getModule().getData();
		MUser userdb = userService.login(BeanUtils.copyProperties(new RUser(), data));
		if (null == userdb) {
			MessageUtil.response(new Response<SUser>(StateCode.USER_NOT_FOUND));
			return;
		}
		final MToken mToken = tokenDao.refresh(new MToken(userdb.getId(), UUID.randomUUID().toString(), new Date(System.currentTimeMillis() + 1000 * 60 * 24)));
		SToken sToken = BeanUtils.copyProperties(new SToken(), mToken);
		SUser sUser = BeanUtils.copyProperties(new SUser(), userdb);
		sUser.setToken(sToken);
		ActiveWebSocketSessionPool.removeAn(RequestContextManager.getSession());
		ActiveWebSocketSessionPool.add(sUser.getId(), RequestContextManager.getSession());
		ActiveWebSocketSessionPool.addAuthSession(RequestContextManager.getSession(), new Token() {
			private static final long serialVersionUID = 1058291541507329638L;

			@Override
			public int getUserId() {
				return mToken.getUserId();
			}
			
			@Override
			public String getToken() {
				return mToken.getToken();
			}
			
			@Override
			public Date getExpired() {
				return mToken.getExpired();
			}
			
			@Override
			public User getUser() {
				final MUser mUser = mToken.getmUser();
				return new User() {
					private static final long serialVersionUID = 3823581200965719596L;

					@Override
					public String getUsername() {
						return mUser.getUsername();
					}
					
					@Override
					public String getPassword() {
						return mUser.getPassword();
					}
					
					@Override
					public String getName() {
						return mUser.getName();
					}
					
					@Override
					public String getIcon() {
						return mUser.getIcon();
					}
				};
			}
		});
		MessageUtil.response(new Response<SUser>(StateCode.COMPLETED, sUser));
	}

	@Override
	public void handleResponse(Protocol protocol) throws ChatException {
		
	}
}
