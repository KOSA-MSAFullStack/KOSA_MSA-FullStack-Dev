<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>로그인</title>
  </head>
  <body>
    <h1>로그인</h1>
    <!--
        로그인 폼:
        action="login"으로 설정되어 있어 현재 URL(/member/login)에 POST 요청
        입력 필드 'id'와 'password'는 MemberDTO 필드와 매핑
    -->
    <form action="login" method="post">
      <p>
        <label for="id">아이디:</label>
        <input type="text" id="id" name="id" />
      </p>
      <p>
        <label for="password">비밀번호:</label>
        <input type="password" id="password" name="password" />
      </p>
      <p>
        <input type="submit" value="로그인" />
      </p>
    </form>
  </body>
</html>
