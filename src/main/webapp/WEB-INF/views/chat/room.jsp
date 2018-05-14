<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
			console.log(data.substr(data.split(":")[0].length+1));
			appendMessage(data.substr(data.split(":")[0].length+1));
		}else {
			$('#userListArea').empty();
			$.each(data.split(","), function(i, elt) {
				if(elt.indexOf("/") == -1){
					$('#userListArea').append("<h3>"+elt+"</h3>");
				}
			})
			
			if(data.indexOf("/") != -1){
				appendMessage(data.split("/")[1]);
			}
		}
	}
	function onClose(evt) {
		appendMessage("연결을 끊었습니다.");
	}
	
	function send() {
		var nickname = $("#hiddenuserid").val();
		var msg = $("#roomMessage").val();
		wsocket.send("room"+$('#hiddenroomnumber').val()+":"+nickname+" : " + msg);
		$("#roomMessage").val("");
	}
	
	function del(){
		$("#roomChatMessageArea").empty();
	}
	
	function appendMessage(msg) {
		console.log("1111111"+msg);
		if(msg.split(":")[0] == $('#hiddenuserid').val()+" "){
			$("#roomChatMessageArea").append('<div style="text-align: right; margin:20px;"><label class="bubble">'+msg.split(":")[1]+'</label></div>');
		}else if(msg.indexOf(":") != -1){
			$("#roomChatMessageArea").append('<div style="text-align: left; margin:20px;">'+msg.split(":")[0]+'<br><label class="bubble">'+msg.split(":")[1]+'</label></div>');
		}else {
			$("#roomChatMessageArea").append('<div style="text-align: center;">'+msg+'</div>');
		}
		
		var chatAreaHeight = $("#chatArea").height();
		var scroll = 10000000000000000;
		$("#chatArea").scrollTop(scroll);
	}
	
	$(function(){
		connect();
		
		
		$('#roomMessage').keypress(function(event){
			var keycode = (event.keyCode ? event.keyCode : event.which);
			if(keycode == '13'){
				if($('#roomMessage').val() != "") send();	
			}
			event.stopPropagation();
		});
		$('#roomSendBtn').click(function() { if($('#roomMessage').val() != "") send(); });
		
		$('#chatArea').click(function() {
			
		});
	});
</script>
<style>
#chatArea {
	width: 200px; height: 100px; overflow-y: auto; border: 1px solid black;
}

.bubble {
	position: relative;
	width: auto;
	height: auto;
	max-width: 300px;
	padding: 5px;
	word-wrap: break-word;
	background: #FFFFFF;
	-webkit-border-radius: 16px;
	-moz-border-radius: 16px;
	border-radius: 16px;
	border: #00B0FF solid 2px;
}

</style>
</head>
<body>
	<div style="margin-top: 20px; text-align: center;">
		방이름: ${room.roomname}
		<hr>
		<input type="hidden" value="${room.roomnumber}" id="hiddenroomnumber" >
		<input type="hidden" value="${userid}" id="hiddenuserid" >
		<h1>유저 목록</h1>
	</div>
	<div id="userArea" style="margin-top: 20px; text-align: center;">
		<div id="userListArea"></div>
	</div>
	
	<hr>
	<div style="margin-top: 20px; text-align: center;">
		<h1>대화 영역</h1>
	</div>
	<div style="margin-left: 10%; width: 80%; height: 500px;" id="chatArea">
		<div style="width: 100%; height: 100%;" id="roomChatMessageArea"></div>
	</div>
	<br />
	<div class="container">
		<div class="col-sm-8">
			<input class="form-control col-sm-11" type="text" id="roomMessage"
				placeholder="글을 입력해주세요">
		</div>
		<div class="col-sm-2">
			<input class="form-control col-sm-1" type="button" id="roomSendBtn"
				value="전송">
		</div>
		<div class="col-sm-2">
			<input class="form-control col-sm-1" type="button" onclick="del()"
				value="지우개">
		</div>
	</div>
</body>
</html>