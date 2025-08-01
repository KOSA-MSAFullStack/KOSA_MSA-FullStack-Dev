<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
   <!--필요한 외부 클래스 임포트-->
<%@ page import="java.text.SimpleDateFormat"%> 
<%@ page import="java.util.Date"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>첫번째 JSP 페이지</title>
</head>
<body>
 <h1>hello JSP!!</h1>
 <h1>JSP 실습입니다!!</h1>
<%
Date today = new Date();  // 외부 클래스 생성
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
String todayStr = dateFormat.format(today);
out.println("오늘 날짜 : " + todayStr);  // 오늘 날짜를 웹 브라우저에 출력
%>
</body>
</html>

