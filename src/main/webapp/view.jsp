<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
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
				<span id="likeIcon"></span>
				<span id="likeCnt"></span>
			</td>
		</tr>
	</table>
	<%@include file="comment.jsp" %>
	<script>
		let likeBoardId = ${board.boardId };
		let showLike = function(likeBoardId) {
			$.ajax({
				type: "GET",
				url: "./like",
				data: { boardId: likeBoardId },
				success: function(jArray) {
					jArray.forEach(function (like) {
						if (like.res == 1) {
							$("#likeIcon").html("<button id='afterLike' type='button'>💚</button>");
						} else {
							$("#likeIcon").html("<button id='beforeLike' type='button'>🤍</button>");
						}
						$("#likeCnt").html('['+ like.likeCnt +']');
					});
				}, //success
				error: function(request, status, error){ alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error+"showLike 중 에러") }
			}); //ajax
		}; //showLike
		
		$(document).ready(function(){
			showLike(likeBoardId);
			$('#likeIcon').on("click", "#afterLike", function() {
				$.ajax({
					type : "GET",
					url : "./like",
					data : { boardId: likeBoardId, mode: "deleteLike"},
					success : function(result) {
						showLike(likeBoardId);
					},
					error: function(request, status, error){ alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error+"\n" +"댓글 등록 에러") }
				}); //ajax
			}); //afterLike
			
			$('#likeIcon').on("click", "#beforeLike", function() {
				$.ajax({
					type : "GET",
					url : "./like",
					data : { boardId: likeBoardId, mode: "addLike"},
					success : function(result) {
						showLike(likeBoardId);
					},
					error: function(request, status, error){ alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error+"\n" +"댓글 등록 에러") }
				}); //ajax
			}); //beforeLike	
		});	
	</script>
</body>
</html>

















