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
	<div class="mod"></div>
	<div id="commentList"></div>
	
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
		
		let toHtml = function(comments) {
			let tmp = "<ul>";
			comments.forEach(function (comment) {
				tmp += "<li data-cno="+comment.cno + " data-bno="+comment.bno + ">";
				tmp +='commenter= <span class="commenter"> '+comment.commenter+'</span>';
				tmp +='   comment= <span class="comment"> '+comment.comment+'</span>';
				if (comment.commenter == "${sessionScope.id }") {
					tmp += "<button type='button' class='delBtn'>삭제</button>";
					tmp += "<button type='button' class='modBtnb'>수정</button>";
				}
				tmp += "</li>";
			})
			return tmp + "</ul>";
		}
		
		$(document).ready(function() {
			showList(bno);
			//등록 버튼
			$("#sendBtn").click(function() {
				let comment = $("input[name=comment]").val();
				if (comment.trim() == "") {
					alert("입력하세요");
					return;
				}
				$.ajax({
					type : "POST",
					url : "./comments",
					data : { bno: bno, comment: comment},
					success : function(result) {
						showList(bno);
					},
					error: function(request, status, error){ alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error) }
				}); //ajax
			}); //sendBtn
			
			//댓글 옆 수정 버튼
			$("#commentList").on("click", ".modBtnb", (function() {
				let cno = $(this).parent().attr("data-cno");
				let bno = $(this).parent().attr("data-bno");
				$(".mod").append("<input type='text' name='recomment' id='recomment'>");
				$(".mod").append("<button type='button' id='modBtn'>수정</button>");
				$("input[name=recomment]").val($("span.comment", $(this).parent()).text());
				$("#modBtn").attr("data-cno", cno);
			})); //modBtnb
			
			//등록 버튼 옆 수정 버튼
			$(".mod").on("click", "#modBtn", (function() {
				let comment = $("input[name=recomment]").val();
				if (comment.trim() == "") {
					alert("입력 안하면 죽임");
					return;
				}
				let cno = $("#modBtn").attr("data-cno");
				let del = $("#recomment").detach();
				let btn = $("#modBtn").detach();
				$.ajax({
					type : "POST",
					url : "./comments",
					data : { cno: cno, comment: comment, mode: "mody" },
					success : function(result){
						showList(bno);
					},
					error: function(request, status, error){ alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error) }
				}); //ajax
			})); //modBtn
			
			//삭제 버튼
			$("#commentList").on("click", ".delBtn", (function() {
				let cno = $(this).parent().attr("data-cno");
				let bno = $(this).parent().attr("data-bno");
				$.ajax({
					type : "GET",
					url : "./comments",
					data : { cno: cno, bno: bno, mode: "del" },
					success : function(result) {
						showList(bno);
					},
					error: function(request, status, error){ alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error) }
				}); //ajax
			})); //delBtn	
		}); //ready
	</script>
</body>
</html>
































