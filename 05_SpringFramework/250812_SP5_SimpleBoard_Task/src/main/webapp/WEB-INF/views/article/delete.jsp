<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" isELIgnored="false" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>게시물 삭제</title>
  </head>
  <body>
    <!--
        게시물 삭제 폼:
        POST 요청으로 현재 페이지에 폼 데이터를 전송하여 게시물 삭제를 처리합니다.
        삭제할 게시물의 번호(no)는 hidden 필드로 전달됩니다.
    -->
    <form method="post">
      <table>
        <caption>
          게시물 삭제
        </caption>
        <tr>
          <th>번호</th>
          <td>
            ${articleDTO.no}<input
              type="hidden"
              name="no"
              value="${articleDTO.no}"
            />
          </td>
        </tr>
        <tr>
          <th>제목</th>
          <td>
            <input
              type="text"
              name="title"
              value="${articleDTO.title}"
              readonly
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
              readonly
            />
          </td>
        </tr>
        <tr>
          <th>내용</th>
          <td>
            <textarea name="content" rows="5" cols="30" readonly>
${articleDTO.content}</textarea
            >
          </td>
        </tr>
        <tr>
          <th>비밀번호</th>
          <td>
            <input
              type="password"
              name="password"
              required="required"
              autofocus="autofocus"
            /><br />
            * 처음 글 입력시 비밀번호를 재 입력하세요.
          </td>
        </tr>
        <tr>
          <td colspan="2" align="center">
            <input type="submit" value="완료" />
            <input type="button" value="취소" onclick="history.back();" />
          </td>
        </tr>
      </table>
    </form>
  </body>
</html>