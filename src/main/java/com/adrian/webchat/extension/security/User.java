package com.adrian.webchat.extension.security;

import java.io.Serializable;

public interface User extends Serializable {

	String getUsername();

	String getPassword();

	String getName();

	String getIcon();
}
