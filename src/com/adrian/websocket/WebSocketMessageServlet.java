package com.adrian.websocket;

import java.util.UUID;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;

@WebServlet(urlPatterns = { "/message"}) 
public class WebSocketMessageServlet extends WebSocketServlet {
	private static final long serialVersionUID = 1L;  
    
    public static int ONLINE_USER_COUNT = 1;  
      
    public String getUser(HttpServletRequest request){  
        String user = (String) request.getSession().getAttribute("user");
        return null == user ? UUID.randomUUID().toString() : user;
    }
	@Override
	protected StreamInbound createWebSocketInbound(String arg0,
			HttpServletRequest request) {
		return new WebSocketMessageInbound(this.getUser(request));  
	}
	@Override
	protected boolean verifyOrigin(String origin) {
		System.out.println(origin);
		return super.verifyOrigin(origin);
	}

	
	
}
