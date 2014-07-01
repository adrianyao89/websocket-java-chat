package com.adrian.webchat.extension.protocol.validator;

import com.adrian.webchat.bean.request.Protocol;

public class DefaultValidator implements Validator {

	@Override
	public boolean validate(Protocol protocol) {
		return true;
	}

}
