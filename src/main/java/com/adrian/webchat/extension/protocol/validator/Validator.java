package com.adrian.webchat.extension.protocol.validator;

import com.adrian.webchat.bean.request.Protocol;

public interface Validator {
	
	boolean validate(Protocol protocol);
	
}
