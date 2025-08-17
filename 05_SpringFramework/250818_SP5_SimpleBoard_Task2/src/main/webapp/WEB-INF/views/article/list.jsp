<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" isELIgnored="false" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib prefix="fmt"
uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>게시물 목록</title>
    <style>
      .page-item a {
        color: black;
        text-decoration: none;
      }
      .page-item.active a {
        color: red;
        font-weight: bold;
      }
    </style>
  </head>
  <body>
    <h1>게시물 목록</h1>

    <!--
	        로그인/로그아웃 상태에 따른 UI 표시:
	        세션에 "loginMember" 객체가 비어있으면(로그아웃 상태) 로그인 링크 표시
	        세션에 "loginMember" 객체가 있으면(로그인 상태) 환영 메시지 & 로그아웃 링크 표시
	-->
    <div style="text-align: left">
      <c:choose>
        <c:when test="${empty sessionScope.loginMember}">
          <a href="/member/login">로그인</a>
        </c:when>
        <c:otherwise>
          <span>${sessionScope.loginMember.name}님 환영합니다.</span>
          <a href="/member/logout">로그아웃</a>
        </c:otherwise>
      </c:choose>
    </div>

    <table border="1">
      <caption>
        게시물 리스트
      </caption>
      <tr>
        <th>번호</th>
        <th>제목</th>
        <th>이름</th>
        <th>날짜</th>
        <th>조회수</th>
      </tr>
      <c:forEach items="${list}" var="dto">
        <tr>
          <td>${dto.no}</td>
          <td><a href="detail?no=${dto.no}">${dto.title}</a></td>
          <td>${dto.name}</td>
          <td>
            <fmt:formatDate value="${dto.regdate}" pattern="yyyy-MM-dd" />
          </td>
          <td>${dto.readcount}</td>
        </tr>
      </c:forEach>
    </table>
    <br />

    <div class="pull-right">
      <ul class="pagination">
        <c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
          <li class="page-item ${pageMaker.cri.pageNum == num ? 'active' : ''}"><a href="list?pageNum=${num}&amount=${pageMaker.cri.amount}">${num}</a></li>
        </c:forEach>
      </ul>
    </div>

    <!-- 게시글 작성 페이지로 이동하는 링크 -->
    <a href="insert">글쓰기</a>
  </body>
</html>
