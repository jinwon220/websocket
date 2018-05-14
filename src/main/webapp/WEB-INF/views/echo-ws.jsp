<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>에코</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#sendBtn').click(function() { sendMessage(); });
	});
	
	var wsocket;
	function sendMessage() {
		wsocket = new WebSocket("ws://192.168.0.28:8090/kr/echo-ws");
		/*
		      이 엔드포인트 경로는 스프링 설정의 경로
		    <websocket:mapping handler="echoHandler" path="/echo-ws" />
		      와 매핑되는 것이다.
		*/
		wsocket.onmessage = onMessage;
		wsocket.onclose = onClose;
		wsocket.onopen = function() {
			wsocket.send( $("#message").val() );
		};
	}
	function onMessage(evt) {
		var data = evt.data;
		alert("서버에서 데이터 받음: " + data);
		wsocket.close();
	}
	function onClose(evt) {
		alert("연결 끊김");
	}
</script>
</head>
<body>
    <input type="text" id="message">
    <input type="button" id="sendBtn" value="전송">
</body>
</html>