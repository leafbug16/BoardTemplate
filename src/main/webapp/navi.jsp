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
	<%
	System.out.println(session.getAttribute("id"));
	%>
	<a href="main">STUDY LOG</a>
	<c:if test="${not empty sessionScope.name }">${sessionScope.name }님 환영합니다</c:if>
	<div class="topNav">
		<a href="#">HTML</a>
		<a href="#">CSS</a>
		<a href="#">JAVASCRIPT</a>
		<a href="bbs">게시판</a>
		<div class=topNavRight">
			<c:choose>
				<c:when test="${not empty sessionScope.name }"><a href="logout">로그아웃</a></c:when>
				<c:otherwise><a href="login">로그인</a></c:otherwise>
			</c:choose>
		</div>
	</div>
</body>
</html>