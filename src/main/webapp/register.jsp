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
	<form action="register" method="post">
		<h3><a href="index.jsp">회원가입</a></h3>
		id : <input type="text" name="userId"><br>
		pw : <input type="password" name="password"><br>
		name : <input type="text" name="name"><br>
		<button>회원가입</button>
	</form>
</body>
</html>