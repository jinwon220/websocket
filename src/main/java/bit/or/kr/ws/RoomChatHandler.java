package bit.or.kr.ws;

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
		//room2:good:df 방이름:아이디:내용
		System.out.println("메시지: " + message.getPayload());
		
		//귓속말 1
		/*
		System.out.println("메시지 가공: " + message.getPayload().contains("/r"));
		boolean isWhisper = txText.contains("/r");
		if(isWhisper) {
			System.out.println("인덱스: " + txText.indexOf("/r"));
		}
		*/
		
		//귓속말 2
		String txText = message.getPayload();
		boolean isWhisper = txText.contains("*");
		if(isWhisper) {
			int begin = txText.indexOf("*");
			int end = txText.lastIndexOf("*");
			String whisperID = txText.substring(begin + 1, end);
			System.out.println("귓속말 아이디: " + whisperID);
			
			for (WebSocketSession s : users.values()) {
				if(s.getPrincipal().getName().equals(whisperID)) {
					session.sendMessage(message);
					s.sendMessage(message);
				}
			}
			return;
		}
		
		for (WebSocketSession s : users.values()) {
			s.sendMessage(message);
			System.out.println("아이디: " + s.getPrincipal().getName());
		}
	}

	@Override
	public void handleTransportError(
			WebSocketSession session, Throwable exception) throws Exception {
	}
}
