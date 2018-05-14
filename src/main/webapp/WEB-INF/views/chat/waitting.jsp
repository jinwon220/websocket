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
$(function(){
	showrom();
	
	$('#roombtn').click(function() {
		if($('#roomname').val()==""){
			alert("방이름을 입력하세요")
		}else{
			$.ajax({
				type : "post",
				url  : "waitting.htm",
				data : {roomname:$('#roomname').val()},
				contentType: "application/json; charset=utf-8",
				success : function(data){
					if(data.trim() == "1"){
						showrom();
						$('#roomname').val("");
					}else{
						alert("방 생성 실패");
					}
				} 
			});
		}
	});
	
});

function showrom(){
	$.ajax({
		type : "post",
		url  : "showroom.htm",
		contentType: "application/json; charset=utf-8",
		success : function(data){
			$('#roomarea').empty();
			var htmltext = '';
			$.each(data.data, function(index, elt) {
				htmltext += '<form action="room'+elt.roomnumber+'.htm" method="post">'
						  + '<input type="hidden" name="roomnumber" value="'+elt.roomnumber+'" >'
						  + '<label type="text" name="roomnumber">'+elt.roomname+'</label> '
						  +	'<input type="submit" value="입장"> </form><br>'
			});
			htmltext += '<hr>';
			
			$('#roomarea').html(htmltext);
		}
	});
}
</script>
</head>
<body>
<input type="text" id="roomname" >
<input type="button" value="방만들기" id="roombtn">
<hr>
<div id="roomarea">
	
</div>
</body>
</html>