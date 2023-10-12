<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="login" method="post">
		<h3><a href="index.jsp">로그인 화면</a></h3>
		<div id="msg"></div>
		id : <input type="text" name="id" value="${cookie.id.value }" autofocus>
		pw : <input type="password" name="password"><br><br>
		<button>로그인</button>
		<input type="checkbox" name="rememberId" ${empty cookie.id.value ? "" : "checked" }>아이디 기억
		<a href="register.jsp">회원가입</a>
	</form>
</body>
</html>