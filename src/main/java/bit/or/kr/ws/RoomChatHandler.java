package bit.or.kr.ws;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class RoomChatHandler extends TextWebSocketHandler {
	
private Map<String, WebSocketSession> users = new ConcurrentHashMap<>();
	
	@Override
	public void afterConnectionEstablished(
			WebSocketSession session) throws Exception {
		users.put(session.getId(), session);
		System.out.println("접속된 아이디 숫자: " + session.getId());
		System.out.println("접속된 아이디: " + session.getPrincipal().getName());
	}

	@Override
	public void afterConnectionClosed(
			WebSocketSession session, CloseStatus status) throws Exception {
		users.remove(session.getId());
	}

	@Override
	protected void handleTextMessage(
			WebSocketSession session, TextMessage message) throws Exception {
		for (WebSocketSession s : users.values()) {
			s.sendMessage(message);
			System.out.println("아이디: " + s.getPrincipal().getName());
		}
		//귓속말
	}

	@Override
	public void handleTransportError(
			WebSocketSession session, Throwable exception) throws Exception {
	}
}
