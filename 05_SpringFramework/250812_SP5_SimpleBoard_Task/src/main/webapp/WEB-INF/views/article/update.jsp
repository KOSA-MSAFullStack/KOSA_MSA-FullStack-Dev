<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" isELIgnored="false" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>게시물 수정</title>
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
        수정 권한 확인:
        현재 로그인된 사용자의 이름(sessionScope.loginMember.name)과
        수정하려는 게시물의 작성자 이름(articleDTO.name)이 일치하는 경우에만 수정 폼을 표시합니다.
    -->
    <c:if test="${sessionScope.loginMember.name == articleDTO.name}">
      <form method="post">
        <table>
          <caption>
            게시물 수정
          </caption>
          <tr>
            <th>제목</th>
            <td>
              <input
                type="text"
                name="title"
                value="${articleDTO.title}"
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
                value="${articleDTO.name}"
                readonly="readonly"
                required="required"
              />
            </td>
          </tr>
          <tr>
            <th>비밀번호</th>
            <td>
              <input
                type="password"
                name="password"
                required="required"
              /><br />
              * 처음 글 입력시 비밀번호를 재 입력하세요.
            </td>
          </tr>
          <tr>
            <th>내용</th>
            <td>
              <textarea name="content" rows="5" cols="40" required="required">
${articleDTO.content}</textarea
              >
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
    <!--
        수정 권한이 없는 경우 메시지 표시:
        로그인된 사용자와 게시물 작성자가 일치하지 않으면 수정 권한이 없다는 메시지를 보여줍니다.
    -->
    <c:if test="${sessionScope.loginMember.name != articleDTO.name}">
      <p>수정 권한이 없습니다.</p>
      <a href="javascript:history.back();">이전 페이지로</a>
    </c:if>
  </body>
</html>
