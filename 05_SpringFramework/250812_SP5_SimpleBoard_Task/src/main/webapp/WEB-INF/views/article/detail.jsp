<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" isELIgnored="false" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib prefix="fmt"
uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="dto" value="${articleDTO}" />
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>게시물 상세 보기</title>
  </head>
  <body>
    <!--
        로그인/로그아웃 상태에 따른 UI 표시:
        세션에 "loginMember" 객체가 비어있으면 (로그아웃 상태) 로그인 링크를 표시합니다.
        세션에 "loginMember" 객체가 있으면 (로그인 상태) 환영 메시지와 로그아웃 링크를 표시합니다.
    -->
    <div style="text-align: right">
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
        게시물 상세 정보 테이블:
        Controller에서 "articleDTO"라는 이름으로 전달받은 ArticleDTO 객체의 상세 정보를 표시합니다.
        글번호, 제목, 이름, 내용, 작성일, 조회수를 보여줍니다.
    -->
    <table>
      <caption>
        게시물 상세보기
      </caption>
      <tr>
        <th>글번호</th>
        <td>${dto.no}</td>
      </tr>
      <tr>
        <th>제목</th>
        <td>${dto.title}</td>
      </tr>
      <tr>
        <th>이름</th>
        <td>${dto.name}</td>
      </tr>
      <tr>
        <th>내용</th>
        <td>${dto.content}</td>
      </tr>
      <tr>
        <th>작성일</th>
        <td>
          <fmt:formatDate
            value="${dto.regdate}"
            pattern="yyyy-MM-dd HH:mm:ss"
          />
        </td>
      </tr>
      <tr>
        <th>조회수</th>
        <td>${dto.readcount}</td>
      </tr>
    </table>
    <br />
    <!--
        게시물 관련 액션 링크:
        수정: 현재 게시물의 번호를 파라미터로 넘겨 수정 페이지로 이동합니다.
        삭제: 현재 게시물의 번호를 파라미터로 넘겨 삭제 확인 페이지로 이동합니다.
        리스트: 게시물 목록 페이지로 이동합니다.
    -->
    <a href="update?no=${dto.no}">수정</a> |
    <a href="delete?no=${dto.no}">삭제</a> |
    <a href="list">리스트</a>
  </body>
</html>
