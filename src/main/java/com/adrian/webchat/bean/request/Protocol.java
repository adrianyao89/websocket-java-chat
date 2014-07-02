package com.adrian.webchat.bean.request;

import java.io.Serializable;

public class Protocol implements Serializable {

	private static final long serialVersionUID = 2074693402513460633L;
	
	private Header header;
	
	private Module module;

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}
	
	public String getPath() {
		if (null != module) {
			return module.getName() + "_" + module.getAction();
		} else {
			return null;
		}
	}
}
