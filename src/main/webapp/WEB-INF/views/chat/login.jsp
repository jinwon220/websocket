<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div style="text-align: center">
		<h2>로그인</h2>
	</div>
	<div id="join-form" class="join-form margin-large container">

		<form class="form-inline" action="" method="post">
			<div class="input-group col-sm-offset-4 col-sm-4">
				<span class="input-group-addon"><i
					class="glyphicon glyphicon-user"></i></span> <input class="form-control"
					type="text" id="userid" name="userid" placeholder="id">
			</div>

			<div class="input-group col-sm-offset-4 col-sm-4">
				<span class="input-group-addon"><i
					class="glyphicon glyphicon-lock"></i></span> <input class="form-control"
					type="password" id="password" name="password"
					placeholder="Password">
			</div>
			<br>
			<div class="form-group col-sm-offset-4">
				<div style="margin-top: 20px;">
					<input type="submit" class="btn btn-default" value="접속 or 가입">
				</div>
			</div>
		</form>

		<div class="form-group col-sm-offset-4" style="margin-top: 20px;">
			<c:if test="${param.error != null}">
				<div>
					로그인실패<br>
					<c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null}">
				이유 : 비밀번호가 맞지 않습니다
			</c:if>
				</div>
			</c:if>
		</div>
	</div>
</body>
</html>