package com.goshine.ptadmui.sys.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import com.goshine.ptadmui.core.model.Context;
import com.goshine.ptadmui.sys.entity.Message;
import com.goshine.ptadmui.sys.mapper.MessageMapper;
import com.goshine.ptadmui.sys.vo.MessageVo;

@Component
public class SocketHandler extends TextWebSocketHandler{

    private MessageMapper msgMapper;
    
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private static final Map<String,List<WebSocketSession>> userSocketSessionMap;
	 
    static{
        userSocketSessionMap = new HashMap<String,List<WebSocketSession>>();
    }
    
    public SocketHandler(MessageMapper msgMapper){
    	this.msgMapper=msgMapper;
    }
	
    /**
     * 建立连接后
     */
    @Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		String userId=(String)session.getAttributes().get("userId");
        if(!userSocketSessionMap.containsKey(userId)){
        	List<WebSocketSession> sessionList=new ArrayList<WebSocketSession>();
        	sessionList.add(session);
            userSocketSessionMap.put(userId,sessionList);
        }else{
        	List<WebSocketSession> sessionList=userSocketSessionMap.get(userId);
        	boolean isExist=false;//是否存在，存在则为true
        	for(WebSocketSession wss:sessionList){
        		if(wss.getId().equals(session.getId())){
        			isExist=true;
        			break;
        		}
        	}
        	if(!isExist){//不存在则放入
        		sessionList.add(session);
        		userSocketSessionMap.put(userId,sessionList);
        	}
        }
        Context context=new Context();
        context.setUserId(userId);
        Message message=new Message();
        message.setUserId(userId);
		MessageVo vo=msgMapper.queryCountByUserId(message);
		if(vo!=null){
    		vo.setNoReadCount(vo.getTotalCount()-vo.getReadCount());
    	}
        TextMessage firstMessage = new TextMessage("{\"status\":\"FIRST\",\"total\":" +(vo==null?"0":vo.getNoReadCount())+ "}");
        session.sendMessage(firstMessage);
	}
    
    /**
     * 消息传输错误处理
     */
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		if (session.isOpen()) {
	        session.close();
	    }
	    Iterator<Entry<String,List<WebSocketSession>>> it = userSocketSessionMap.entrySet().iterator();
	    // 移除Socket会话
	    while (it.hasNext()) {
	        Entry<String,List<WebSocketSession>> entry = it.next();
	        List<WebSocketSession> sessionList=entry.getValue();
	        if(sessionList!=null&&sessionList.size()>0){
	        	WebSocketSession wssModel=null;
	        	for(WebSocketSession wss:sessionList){
	        		if(wss.getId().equals(session.getId())){//相同
	        			wssModel=wss;
	        		}
	        	}
	        	if(wssModel!=null){//假如存在则移除session
	        		sessionList.remove(wssModel);
	        	}
	        	if(sessionList!=null&&sessionList.size()>0){//移除后假如当前用户的登录人员还大于0，则放入新的sessionList
	        		userSocketSessionMap.put(entry.getKey(),sessionList);
	        	}else{//否则，则直接移除该用户的session集合
	        		userSocketSessionMap.remove(entry.getKey());
	        	}
	        	logger.debug("WebSocket会话已经移除:用户ID " + entry.getKey());
	            break;
	        }
	    }
	}

	/**
     * 关闭连接后
     */
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		logger.info("WebSocket: " + session.getId() + "已经关闭");
	    Iterator<Entry<String,List<WebSocketSession>>> it = userSocketSessionMap.entrySet().iterator();
	    // 移除Socket会话
	    while (it.hasNext()) {
	    	Entry<String,List<WebSocketSession>> entry = it.next();
	        List<WebSocketSession> sessionList=entry.getValue();
	        if(sessionList!=null&&sessionList.size()>0){
	        	WebSocketSession wssModel=null;
	        	for(WebSocketSession wss:sessionList){
	        		if(wss.getId().equals(session.getId())){//相同
	        			wssModel=wss;
	        		}
	        	}
	        	if(wssModel!=null){//假如存在则移除session
	        		sessionList.remove(wssModel);
	        	}
	        	if(sessionList!=null&&sessionList.size()>0){//移除后假如当前用户的登录人员还大于0，则放入新的sessionList
	        		userSocketSessionMap.put(entry.getKey(),sessionList);
	        	}else{//否则，则直接移除该用户的session集合
	        		userSocketSessionMap.remove(entry.getKey());
	        	}
	        	logger.debug("WebSocket会话已经移除:用户ID " + entry.getKey());
	            break;
	        }
	    }
	}

	/**
	 * 不支持PartialMessages
	 */
	@Override
	public boolean supportsPartialMessages() {
		return false;
	}

	/**
	 * 给单个用户发送消息
	 * 假如有多终端，则都发送
	 */
	public void sendMsg(String userId, TextMessage msg) throws IOException {
		List<WebSocketSession> sessionList = userSocketSessionMap.get(userId);
		if(sessionList!=null&&sessionList.size()>0){
			for(WebSocketSession session:sessionList){
				if(session!=null&&session.isOpen()){
		            session.sendMessage(msg);
		        }
			}
		}
	}
}