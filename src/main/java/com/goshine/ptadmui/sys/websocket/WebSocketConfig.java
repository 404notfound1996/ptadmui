package com.goshine.ptadmui.sys.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.goshine.ptadmui.sys.mapper.MessageMapper;

@Component
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer{
	@Autowired
	private MessageMapper msgMapper;
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry){
		registry.addHandler(new SocketHandler(msgMapper),"/socket").addInterceptors(new HandShake());
	}
	@Bean
	public TaskScheduler taskScheduler() {
	    ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
	    taskScheduler.setPoolSize(10);
	    taskScheduler.initialize();
	    return taskScheduler;
	}
}