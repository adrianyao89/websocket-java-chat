package com.adrian.webchat.extension.protocol.convertor;

import com.adrian.webchat.bean.request.Protocol;

public interface Convertible {

	Protocol convert(String text);
}
