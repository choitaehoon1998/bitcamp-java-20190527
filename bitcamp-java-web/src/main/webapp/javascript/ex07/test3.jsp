<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
response.setHeader("Access-Control-Allow-Origin", "*");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>test3</title>
</head>
<body>
<h1>test3.jsp 실행!</h1>
<%
out.flush();// 일단 이전까지 버퍼에 출려된 내용을 클라이언트로 보낸다 
Thread.currentThread().sleep(5000);// 5초후에 나머지 데이터를 보낸디 . 
%>
<h2>오호라!!!</h2>
</body>
</html>
<%
%>









