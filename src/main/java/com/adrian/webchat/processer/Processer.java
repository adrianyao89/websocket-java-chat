package com.adrian.webchat.processer;

import com.adrian.webchat.bean.request.Protocol;

public interface Processer {
	
	void handleRequest(Protocol protocol);
	
	void handleResponse(Protocol protocol);
}
