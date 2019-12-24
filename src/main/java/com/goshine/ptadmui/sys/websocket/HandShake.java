package com.goshine.ptadmui.sys.websocket;

import java.util.Map;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import com.goshine.ptadmui.core.model.Context;

/**
 * Socket握手和断开
 * @author Admui
 */
public class HandShake implements HandshakeInterceptor {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	public boolean beforeHandshake(ServerHttpRequest request,
			ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {
		
		if (request instanceof ServletServerHttpRequest) {
			ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
			HttpSession session = servletRequest.getServletRequest().getSession(false);
			Context context=(Context)session.getAttribute("context");
			// 标记用户
			if(context!=null){
				logger.debug("WebSocket:用户["+context.getUserName()+"]已经建立连接");
				attributes.put("userId",context.getUserId());
			}else{
				return false;
			}
		}
		return true;
	}

	public void afterHandshake(ServerHttpRequest request,
			ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception exception) {
	}
}