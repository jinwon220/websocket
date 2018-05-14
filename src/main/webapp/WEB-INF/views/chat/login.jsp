<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<div id="join-form" class="join-form margin-large">
	<c:if test="${param.error != null}">
		<div>
		  로그인실패<br>
			<c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null}">
				이유 : 비밀번호가 맞지 않습니다
			</c:if>
		</div>
	</c:if> 
	
	<form action="" method="post">
		<label>아 이 디:</label><input type="text" id="userid" name="userid"> <br>
		<label>비밀번호:</label><input type="password" id="password" name="password"> <br>
		<input type="submit" value="접속 or 가입">
	</form>
</div>
</body>
</html>