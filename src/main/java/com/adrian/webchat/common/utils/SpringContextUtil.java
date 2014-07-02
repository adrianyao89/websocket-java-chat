package com.adrian.webchat.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("unchecked")
public class SpringContextUtil implements ApplicationContextAware {
	
	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		applicationContext = context;
	}
	
	public static <T> T getBean(String name, Class<T> classes) {
		return (T) applicationContext.getBean(name);
	}
	
	public static <T> T getBean(Class<T> classes) {
		return applicationContext.getBean(classes);
	}

}
