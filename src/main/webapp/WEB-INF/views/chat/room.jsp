<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
	var wsocket;
	
	function connect() {
		wsocket = new WebSocket("ws://192.168.0.28:8090/kr/room"+$('#hiddenroomnumber').val());
		wsocket.onopen = onOpen;
		wsocket.onmessage = onMessage;
		wsocket.onclose = onClose;
	}
	function disconnect() {
		wsocket.close();
	}
	function onOpen(evt) {
		appendMessage("연결되었습니다.");
	}
	function onMessage(evt) {
		var data = evt.data.trim();
		if (data.split(":")[0] == "room"+$('#hiddenroomnumber').val()) {
			appendMessage(data.substr(data.split(":")[0].length+1));
		}
	}
	function onClose(evt) {
		appendMessage("연결을 끊었습니다.");
	}
	
	function send() {
		var nickname = $("#hiddenuserid").val();
		var msg = $("#roomMessage").val();
		wsocket.send("room"+$('#hiddenroomnumber').val()+":"+nickname+":" + msg);
		$("#roomMessage").val("");
	}
	
	function appendMessage(msg) {
		$("#roomChatMessageArea").append(msg+"<br>");
		var chatAreaHeight = $("#chatArea").height();
		var maxScroll = $("#roomChatMessageArea").height() - chatAreaHeight;
		$("#chatArea").scrollTop(maxScroll);
	}
	
	$(function(){
		connect();
		
		$('#roomMessage').keypress(function(event){
			var keycode = (event.keyCode ? event.keyCode : event.which);
			if(keycode == '13'){
				send();	
			}
			event.stopPropagation();
		});
		$('#roomSendBtn').click(function() { send(); });
	});
</script>
<style>
#chatArea {
	width: 200px; height: 100px; overflow-y: auto; border: 1px solid black;
}
</style>
</head>
<body>
	방이름: ${room.roomname}
	<hr>
	<input type="hidden" value="${room.roomnumber}" id="hiddenroomnumber" >
	<input type="hidden" value="${userid}" id="hiddenuserid" >
    <h1>대화 영역</h1>
    <div id="chatArea"><div id="roomChatMessageArea"></div></div>
    <br/>
    <input type="text" id="roomMessage">
    <input type="button" id="roomSendBtn" value="전송">
</body>
</html>