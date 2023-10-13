<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>update</title>
</head>
<body>
	<%@include file="navi.jsp" %>
	<h3>글수정</h3>
	<form action="update" method="post">
		<input type="hidden" name="boardId" value="${board.getBoardId() }">
		제목<br>
		<input type="text" name="title" value="${board.getTitle() }"><br>
		내용<br>
		<textarea name="content" style="height: 350px;">${board.getContent() }</textarea>
		<button type="submit">수정</button>
		<button type="reset">재입력</button>
		<button type="reset" onclick="location.href='board.jsp'">목록</button>
	</form>
</body>
</html>