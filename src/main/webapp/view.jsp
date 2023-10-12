<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table, tr, td, th {
	border: 1px solid black;
}
</style>
</head>
<body>
	<%@include file="navi.jsp"%>
	<h3>view</h3>
	<table style="border: 1px solid black">
		<tr>
			<td colspan="4">게시판 글 보기</td>
		</tr>
		<tr>
			<td>번호</td>
			<td>작성자</td>
			<td>조회수</td>
			<td>작성일</td>
		</tr>
		<tr>
			<td>${board.getPostId() }</td>
			<td>${board.getAuthorId() }</td>
			<td>${board.getViewCount() }</td>
			<td>${board.getPostDate() }</td>
		</tr>

		<tr>
			<td>제목</td>
			<td colspan="3">${board.getTitle() }</td>
		</tr>
		<tr>
			<td colspan="4">내용</td>
		</tr>
		<tr>
			<td colspan="4">${board.getContent() }</td>
		</tr>
		<tr>
			<td colspan="4">
				<c:if test="${not empty sessionScope.id }">
					<button type="button" onclick="location.href='update?postId=${board.getPostId() }';">수정</button>
					<button type="button" onclick="location.href='delete?postId=${board.getPostId() }'">삭제</button>
				</c:if>
				<button type="button" onclick="location.href='board';">목록보기</button>
			</td>
		</tr>
	</table>
</body>
</html>

















