package edu.udacity.java.nano.chat;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebSocket Server
 *
 * @author utkarsh
 */
@Component
@ServerEndpoint("/chat")
public class WebSocketChatServer {

	Logger logger = Logger.getLogger(WebSocketChatServer.class);

	/**
	 * All chat sessions.
	 */
	private static Map<String, Session> onlineSessions = new ConcurrentHashMap<>();

	/**
	 * Send Message To All.
	 * 
	 * @param msg
	 */
	private static void sendMessageToAll(String msg) {
		for (Session session : onlineSessions.values()) {
			try {
				session.getBasicRemote().sendText(msg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Open connection, 1) add session, 2) add user.
	 */
	@OnOpen
	public void onOpen(Session session) {
		logger.info("Session Id : "+session.getId());
		onlineSessions.put(session.getId() + "", session);
		Message message = new Message();
		message.setOnlineCount(onlineSessions.size());
		sendMessageToAll(JSON.toJSONString(message));
	}

	/**
	 * Send message, 1) get username and session, 2) send message to all.
	 */
	@OnMessage
	public void onMessage(Session session, String jsonStr) {
		logger.info("Message JSON String : "+jsonStr);
		Message message = JSON.parseObject(jsonStr, Message.class);
		message.setMessageType("SPEAK");
		message.setOnlineCount(onlineSessions.size());
		sendMessageToAll(JSON.toJSONString(message));
	}

	/**
	 * Close connection, 1) remove session, 2) update user.
	 */
	@OnClose
	public void onClose(Session session) {
		onlineSessions.remove(session.getId() + "");
		Message message = new Message();
		message.setOnlineCount(onlineSessions.size());
		sendMessageToAll(JSON.toJSONString(message));
	}

	/**
	 * Print exception.
	 */
	@OnError
	public void onError(Session session, Throwable error) {
		error.printStackTrace();
	}

}
