package com.adrian.webchat.extension.security;

import java.util.Date;
import java.util.List;

import com.adrian.webchat.bean.model.MToken;
import com.adrian.webchat.bean.model.MUser;
import com.adrian.webchat.bean.request.Protocol;
import com.adrian.webchat.common.utils.ActiveWebSocketSessionPool;
import com.adrian.webchat.common.utils.RequestContextManager;
import com.adrian.webchat.dao.TokenDao;

public class DefaultAuthentication implements Authentication {
	
	private TokenDao tokenDao;
	
	private List<String> excludeAuth;
	
	@Override
	public boolean needAuth(Protocol protocol) {
		if (excludeAuth != null && excludeAuth.contains(protocol.getPath())) {
			return false;
		}
		return true;
	}

	@Override
	public boolean authenticate(Protocol protocol) {
		if (needAuth(protocol)) {
			String token = protocol.getHeader().getToken();
			if (token != null && !token.isEmpty()) {
				Token tokenObj = getToken(token);
				if (tokenObj != null) {
					ActiveWebSocketSessionPool.add(tokenObj.getUserId(), RequestContextManager.getSession());
					ActiveWebSocketSessionPool.addAuthSession(RequestContextManager.getSession(), tokenObj);
					ActiveWebSocketSessionPool.removeAn(RequestContextManager.getSession());
					return true;
				}
			}
		} else{
			return true;
		}
		return false;
	}

	@Override
	public Token getToken(String token) {
		final MToken mToken = tokenDao.getTokenByToken(token);
		
		Token tokenObj = null;
		
		if (mToken != null) {
			tokenObj = new Token() {
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
			};
		}
		return tokenObj;
	}

	public void setExcludeAuth(List<String> excludeAuth) {
		this.excludeAuth = excludeAuth;
	}
	
	public void setTokenDao(TokenDao tokenDao) {
		this.tokenDao = tokenDao;
	}
	
}
