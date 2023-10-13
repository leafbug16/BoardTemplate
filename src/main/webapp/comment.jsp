<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
	<h3>댓글</h3>
	<div>
		<input type="text" name="comment" id="comment">
		<button type="button" id="sendBtn">등록</button>
	</div>
	<div id="mod"></div>
	<div id="comments"></div>
	
	<script>
		let boardId = ${param.boardId };
		let mode = false;
		let showComments = function(boardId){
			//인풋태그를 비움
			$('input[name=comment]').val("");
			$.ajax({
				type : "GET",
				url : "./comment?boardId=" + boardId,
				success : function(jArray) {
					$("#comments").html(toHtml(jArray));
					console.log(jArray);
				},
				error: function(request, status, error){ alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error+"showComments 중 에러") }
			});
		}
		
		let toHtml = function(jArray) {
			let tmp = "<ul>";
			jArray.forEach(function (comment) {
				tmp += "<li data-commentId="+comment.commentId + " data-boardId="+comment.boardId + ">";
				tmp +='commenter= <span class="commenter"> '+comment.commenter+'</span>';
				tmp +='   comment= <span class="comment"> '+comment.comment+'</span>';
				if (comment.commenter == "${sessionScope.userId }") {
					tmp += "<button type='button' id='delBtn'>삭제</button>";
					tmp += "<button type='button' id='modBtn'>수정</button>";
				}
				tmp += "</li>";
			})
			return tmp + "</ul>";
		}
		
		$(document).ready(function() {
			showComments(boardId);
			//등록 버튼
			$("#sendBtn").click(function() {
				let comment = $("input[name=comment]").val();
				if (comment.trim() == "") {
					alert("입력하세요");
					return;
				}
				$.ajax({
					type : "POST",
					url : "./comment",
					data : { boardId: boardId, comment: comment},
					success : function(result) {
						showComments(boardId);
					},
					error: function(request, status, error){ alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error+"\n" +"댓글 등록 에러") }
				}); //ajax
			}); //sendBtn
			
			//댓글 옆 수정 버튼 클릭 이벤트
			$("#comments").on("click", "#modBtn", (function() {
				let commentId = $(this).parent().attr("data-commentId");
				let boardId = $(this).parent().attr("data-boardId");
				$("#mod").append("<input type='text' name='recomment' id='recomment'>");
				$("#mod").append("<button type='button' id='modCompleteBtn'>수정완료</button>");
				$("input[name=recomment]").val($("span.comment", $(this).parent()).text());
				$("#modBtn").attr("data-cno", cno);
			})); //modBtnb
			
			//수정완료 버튼 클릭 이벤트
			$("#mod").on("click", "#modCompleteBtn", (function() {
				let comment = $("input[name=recomment]").val();
				if (comment.trim() == "") {
					alert("내용을 입력하세요");
					return;
				}
				let commentId = $("#modCompleteBtn").attr("data-commentId");
				let delTmp = $("#recomment").detach();
				let btnTmp = $("#modCompleteBtn").detach();
				$.ajax({
					type : "POST",
					url : "./comment",
					data : { commentId: commentId, comment: comment, mode: "patch" },
					success : function(result){
						showComments(boardId);
					},
					error: function(request, status, error){ alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error+"\n" +"댓글 수정 에러") }
				}); //ajax
			})); //modBtn
			
			//삭제 버튼
			$("#comments").on("click", ".delBtn", (function() {
				let commentId = $(this).parent().attr("data-commentId");
				let boardId = $(this).parent().attr("data-boardId");
				$.ajax({
					type : "GET",
					url : "./comment",
					data : { commentId: commentId, boardId: boardId, mode: "delete" },
					success : function(result) {
						showComments(boardId);
					},
					error: function(request, status, error){ alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error+"\n" +"댓글 삭제 에러") }
				}); //ajax
			})); //delBtn	
		}); //ready
	</script>
</body>
</html>
































