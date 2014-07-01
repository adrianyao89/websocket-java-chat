package com.adrian.webchat.extension.security;

import com.adrian.webchat.bean.model.MToken;
import com.adrian.webchat.bean.request.Protocol;
import com.adrian.webchat.common.utils.ActiveWebSocketSessionPool;
import com.adrian.webchat.common.utils.RequestContextManager;
import com.adrian.webchat.dao.TokenDao;

public class DefaultAuthentication implements Authentication {
	
	private TokenDao tokenDao;
	
	@Override
	public boolean needAuth(Protocol protocol) {
		return false;
	}

	@Override
	public boolean authenticate(Protocol protocol) {
		if (needAuth(protocol)) {
			String token = protocol.getHeader().getToken();
			if (token != null && !token.isEmpty()) {
				MToken mToken = tokenDao.getTokenByToken(token);
				if (mToken != null) {
					ActiveWebSocketSessionPool.add(mToken.getUserId(), RequestContextManager.getSession());
					ActiveWebSocketSessionPool.addAuthSession(RequestContextManager.getSession(), mToken);
					return true;
				}
			}
		}
		return false;
	}

	public void setTokenDao(TokenDao tokenDao) {
		this.tokenDao = tokenDao;
	}
}
