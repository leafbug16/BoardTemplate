<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="UTF-8">
  <title>Insert title here</title>
  <style>
  	* {
  		text-decoration: none;
  		color: black;
  	}
    tr {
      height: 35px;
    }
   	.check{
			color: red;
			border:1px solid #e5e7ea;
		}
  </style>
</head>

<body>
  <%@include file="navi.jsp" %>
  <h3>게시판</h3>
  <!-- 검색폼 -->
  <form action="board" method="get" onsubmit="return searchCheck(this)">
    <table>
      <tr>
        <td>
          <select name="searchField">
            <option value="title" ${param.searchField eq "title" ? "selected" : "" }>제목</option>
            <option value="content" ${param.searchField eq "content" ? "selected" : "" }>내용</option>
          </select>
          <input type="text" name="searchWord" id="search" value='${ empty param.searchWord ? "" : param.searchWord }'>
          <button>검색</button>
        </td>
      </tr>
    </table>
  </form>
	
  <table style="border: 1px solid black; width:50%; border-collapse: collapse;">
    <thead>
      <tr>
        <th>글번호</th>
        <th>제목</th>
        <th>작성자</th>
        <th>조회수</th>
        <th>작성일</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="boards" items="${boards }">
        <tr style="text-align: center; border-top: 1px solid lightgray;">
          <td>${boards.getPostId() }</td>
          <td><a href="view?postId=${boards.getPostId() }">${boards.getTitle() }</a></td>
          <td>${boards.getAuthorId() }</td>
          <td>${boards.getViewCount() }</td>
          <fmt:formatDate value="${boards.getPostDate() }" type="date" pattern="yyyy-MM-dd" var="postDate" />
          <fmt:formatDate value="${boards.getPostDate() }" type="time" pattern="HH:mm" var="postTime" />
          <fmt:formatDate value="<%=new java.util.Date()%>" type="date" pattern="yyyy-MM-dd" var="today" />
          <c:choose>
            <c:when test="${postDate eq today }">
        			<td>${postTime }</td>
        		</c:when>
        		<c:otherwise>
          		<td>${postDate }</td>
        		</c:otherwise>
        	</c:choose>
        </tr>
      </c:forEach>
    </tbody>
  </table>
    
  <button type="button" onclick="location.href='write';">글쓰기</button>
  <c:if test="${pageHandler.showPrev }">
    <a href="<c:url value='/board${searchCondition.getQueryString(pageHandler.beginPage-1)}'/>">&laquo;</a>
  </c:if>
  <c:forEach var="i" begin="${pageHandler.beginPage }" end="${pageHandler.endPage }">
    <a class="${pageHandler.searchCondition.pageNum==i? 'check':'' }" href="<c:url value='/board${pageHandler.searchCondition.getQueryString(i)}'/>">${i }</a>
  </c:forEach>
  <c:if test="${pageHandler.showNext }">
    <a href="<c:url value='/board${pageHandler.searchCondition.getQueryString(pageHandler.endPage+1)}'/>">&raquo;</a>
  </c:if>
  
  <script>
    function searchCheck(frm) {
      if (frm.searchWord.value.trim() == "") {
        alert("검색어를 입력해주세요");
        frm.searchWord.focus();
        return false;
      }
    }
  </script>
</body>

</html>













