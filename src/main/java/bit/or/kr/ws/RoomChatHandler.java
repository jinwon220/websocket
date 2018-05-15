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
		//귓속말
		for (WebSocketSession s : users.values()) {
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
