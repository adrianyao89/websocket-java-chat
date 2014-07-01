package com.adrian.webchat.extension.router;

import com.adrian.webchat.bean.request.Protocol;

public interface Router {

	void route(Protocol protocol);
}
