<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" isELIgnored="false"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>게시물 등록</title>
  </head>
  <body>
    <!-- 로그인/로그아웃 상태에 따른 UI 표시(list.jsp와 동일) -->
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

    <!--
        로그인 여부에 따라 게시물 작성 폼 or 로그인 안내 메시지 표시:
        사용자가 로그인되어 있으면(세션에 loginMember 존재하면) 게시물 작성 화면 출력
        로그인되어 있지 않으면 로그인 필요 메시지 & 로그인 페이지로 이동하는 링크 출력
    -->
    <c:if test="${not empty sessionScope.loginMember}">
      <form method="post">
        <table>
          <caption>
            게시물 쓰기
          </caption>
          <tr>
            <th>제목</th>
            <td>
              <input
                type="text"
                name="title"
                autofocus="autofocus"
                required="required"
              />
            </td>
          </tr>
          <tr>
            <th>이름</th>
            <td>
              <input
                type="text"
                name="name"
                value="${sessionScope.loginMember.name}"
                readonly="readonly"
                required="required"
              />
            </td>
          </tr>
          <tr>
            <th>비밀번호</th>
            <td>
              <input type="password" name="password" required="required" />
            </td>
          </tr>
          <tr>
            <th>내용</th>
            <td>
              <textarea
                name="content"
                rows="5"
                cols="40"
                required="required"
              ></textarea>
            </td>
          </tr>
          <tr>
            <td colspan="2" align="center">
              <input type="submit" value="완료" />
            </td>
          </tr>
        </table>
      </form>
    </c:if>
    <c:if test="${empty sessionScope.loginMember}">
      <p>로그인이 필요한 서비스입니다.</p>
      <a href="/member/login">로그인 페이지로 이동</a>
    </c:if>
  </body>
</html>
