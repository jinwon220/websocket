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
	$("#subbtn").trigger('click');
	//location.href=sessionStorage.getItem("contextpath")+"join.htm";
	console.log(sessionStorage.getItem("contextpath"));
});
</script>
</head>
<body>
<div id="join-form" class="join-form margin-large">
	
	<c:url value="/login" var="loginurl" />
	<form action="${loginurl}" method="post">
		<label>아 이 디:</label><input type="text" name="userid" value="${userid}"> <br>
		<label>비밀번호:</label><input type="password" name="password" value="${password}"> <br>
		<input type="submit" value="접속 or 가입" id="subbtn">
	</form>
</div>
</body>
</html>