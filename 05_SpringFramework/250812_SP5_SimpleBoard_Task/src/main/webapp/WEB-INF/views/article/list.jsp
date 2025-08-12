<%@ page language="java" contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8" isELIgnored="false"  %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<!DOCTYPE html>
	<html>
	<head>
	    <meta charset="UTF-8" />
	    <title>게시물 목록</title>
	</head>
	<body>
	    <h1>게시물 목록</h1>

	    <!--
	        로그인/로그아웃 상태에 따른 UI 표시:
	        세션에 "loginMember" 객체가 비어있으면 (로그아웃 상태) 로그인 링크를 표시합니다.
	        세션에 "loginMember" 객체가 있으면 (로그인 상태) 환영 메시지와 로그아웃 링크를 표시합니다.
	    -->
	    <div style="text-align: right;">
        <c:choose>
            <c:when test="${empty sessionScope.loginMember}">
                <a href="/controller/member/login">로그인</a>
            </c:when>
            <c:otherwise>
                <span>${sessionScope.loginMember.name}님 환영합니다.</span>
                <a href="/controller/member/logout">로그아웃</a>
            </c:otherwise>
        </c:choose>
    </div>

    <!--
        게시물 리스트 테이블:
        Controller에서 "list"라는 이름으로 전달받은 ArticleDTO 리스트를 반복하여 표시합니다.
        각 게시물의 번호, 제목, 이름, 날짜, 조회수를 보여줍니다.
        제목 클릭 시 해당 게시물의 상세 페이지로 이동합니다.
    -->
	    <table border="1">
		<caption>게시물 리스트</caption>
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
		<td><fmt:formatDate value="${dto.regdate}" pattern="yyyy-MM-dd"/></td>
		<td>${dto.readcount}</td>
	</tr>
	</c:forEach>
	</table><br>
	<!-- 게시글 작성 페이지로 이동하는 링크 -->
	<a href="insert">글쓰기</a>
	</body>
	</html>