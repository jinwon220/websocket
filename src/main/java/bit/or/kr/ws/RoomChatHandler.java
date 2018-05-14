package bit.or.kr.ws;

import java.net.URI;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class RoomChatHandler extends TextWebSocketHandler {
	
	private Map<String, WebSocketSession> users = new ConcurrentHashMap<>();
	private Map<String, URI> room = new ConcurrentHashMap<>();
	
	@Override
	public void afterConnectionEstablished(
			WebSocketSession session) throws Exception {
		room.put(session.getPrincipal().getName(), session.getUri());
		users.put(session.getId(), session);
		
		System.out.println("접속된 아이디 숫자: " + session.getId());
		System.out.println("접속된 아이디: " + session.getPrincipal().getName());
		
		userListMessage(session, 1);
	}

	@Override
	public void afterConnectionClosed(
			WebSocketSession session, CloseStatus status) throws Exception {
		room.remove(session.getPrincipal().getName());
		users.remove(session.getId());
		
		userListMessage(session, 0);
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
			if(s.getUri().equals(session.getUri())) {
				s.sendMessage(message);
			}
		}
	}

	@Override
	public void handleTransportError(
			WebSocketSession session, Throwable exception) throws Exception {
	}
	
	//방에 입 퇴장시 유저리스트 뿌려주기 / 입퇴장 메세지
	public void userListMessage(WebSocketSession session, int check) throws Exception {
		Set<Map.Entry<String, URI>> set = room.entrySet();
		Iterator<Map.Entry<String, URI>> it = set.iterator();
		
		String idlist = "";
		while(it.hasNext()) {
			Entry<String, URI> entry = it.next();
			if(entry.getValue().equals(session.getUri())) {
				idlist += entry.getKey()+",";
			}
		}
		for (WebSocketSession s : users.values()) {
			if(s.getUri().equals(session.getUri())) {
				if(check == 1) {
					s.sendMessage(new TextMessage(idlist+"/"+session.getPrincipal().getName()+"님이 입장하셨습니다."));
				}else if(check == 0) {
					s.sendMessage(new TextMessage(idlist+"/"+session.getPrincipal().getName()+"님이 퇴장하셨습니다."));
				}
			}
		}
	}
	
}
