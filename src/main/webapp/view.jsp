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
			<td>${board.getBoardId() }</td>
			<td>${board.getAuthor() }</td>
			<td>${board.getViewCnt() }</td>
			<td>${board.getCreatedAt() }</td>
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
				<c:if test="${not empty sessionScope.userId }">
					<button type="button" onclick="location.href='update?boardId=${board.getBoardId() }';">수정</button>
					<button type="button" onclick="location.href='delete?boardId=${board.getBoardId() }'">삭제</button>
				</c:if>
				<button type="button" onclick="location.href='board';">목록보기</button>
				<!-- 좋아요 기능 추가 -->
				<c:if test="${res == 1 }">
					<button id='afterLike' type='button' onclick='likeOff(boardId);' data-cnt ='1' data-check='true'>💚</button>
				</c:if>
				<c:if test='${res!=1 }'>
					<button id='beforeLike' type='button' onclick='likeOn(boardId);' data-cnt ='0' data-check='false'>🤍</button>
				</c:if>
				[${board.getLikeCnt() }]
			</td>
		</tr>
	</table>
	<%@include file="comment.jsp" %>
	<script>
		boardId = ${param.boardId };
		function likeOn(boardId) {
			location.href = "./like?boardId="+boardId+"&mode=likeOn";
		}
		function likeOff(boardId) {
			location.href = "./like?boardId="+boardId+"&mode=likeOff";
		}
	</script>
</body>
</html>

















