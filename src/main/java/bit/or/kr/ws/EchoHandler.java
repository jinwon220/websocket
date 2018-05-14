package bit.or.kr.ws;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class EchoHandler extends TextWebSocketHandler {
	/*
	웹소켓 서버를 구현할 때 WebSocketHandler인터페이스를 직접 상속받기 보다는 기본 구현을 일부 제공하고 있는
	AbstractWebSocketHandler나 TextWebSocketHandler 클래스를 상속받아 구현하게 된다.
	*/
	
	//AbstractWebSocketHandler에 있는 함수 오버라이드
	
	//WebSocketSession : 클라이언트와의 세션을 관리하는 객체
	//TextMessage : 클라이언트가 전송한 텍스트데이터 (getPayload())
	
	
	@Override //연결될 때 호출되는 함수
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.printf("%s 연결 됨\n", session.getId());
	}

	@Override //웹소켓 클라이언트가 텍스트 메시지를 전송 할 때 호출되는 함수
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		System.out.printf("%s로부터 [%s] 받음\n", session.getId(), message.getPayload());
		session.sendMessage(new TextMessage("echo: " + message.getPayload()));
	}

	@Override //연결종료 될 때 호출되는 함수
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.printf("%s 연결 끊김\n", session.getId());
	}
	
	
}
