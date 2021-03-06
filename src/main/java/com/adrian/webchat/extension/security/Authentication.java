package com.adrian.webchat.extension.security;

import com.adrian.webchat.bean.request.Protocol;

public interface Authentication {
	
	Token getToken(String token);

	boolean authenticate(Protocol protocol);

	boolean needAuth(Protocol protocol);
}
