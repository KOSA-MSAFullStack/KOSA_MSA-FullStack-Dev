<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Insert title here</title>
  </head>
  <body>
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
              required="required"
            />
          </td>
        </tr>
        <tr>
          <th>비밀번호</th>
          <td>
            <input type="password" name="password" required="required" /><br />
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
  </body>
</html>
