package com.adrian.webchat.dao;

import com.adrian.webchat.bean.model.MToken;

public interface TokenDao {
	
	MToken getTokenByUser(int userId);

	MToken getTokenByToken(String token);

	MToken refresh(MToken mToken);

}
