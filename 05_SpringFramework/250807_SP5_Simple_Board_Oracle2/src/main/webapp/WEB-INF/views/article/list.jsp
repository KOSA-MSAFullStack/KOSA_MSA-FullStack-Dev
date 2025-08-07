<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" isELIgnored="false" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %> <%@ taglib prefix="fmt"
uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
  <body>
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
    <a href="insert">글쓰기</a>
  </body>
</html>
