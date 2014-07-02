package com.adrian.webchat.extension.security;

import java.io.Serializable;
import java.util.Date;

public interface Token extends Serializable {

	int getUserId();
	
	String getToken();
	
	Date getExpired();
	
	User getUser();
	
}
