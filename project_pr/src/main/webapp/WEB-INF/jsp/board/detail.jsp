<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>사진 게시물 보기 </title>
<link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css' integrity='sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T' crossorigin='anonymous'>
<link rel='stylesheet' href='/css/common.css'>
</head>
<body>
<div id='content'>
<h1>사진게시물</h1>
<form action='update'
      method='post' enctype='multipart/form-date'>
번호: <input type='text' name='no' value='${board.no}' readonly><br>
제목: <textarea name='title' rows='1' cols='30' >${board.title}</textarea><br>
내용: <textarea name='contents' rows='5' cols='50'>${board.contents}</textarea><br>
작성자: ${board.memberNo}<br>
조회수: ${board.viewCount}<br>
<p>
<c:forEach items="${Board.files}" var="file">
    <img src='/upload/photoboard/${file.filepath}'class='photo2'>
  </c:forEach>
</p>
<c:forEach begin="1" end="6">
 사진: <input type='file' name='filepath'><br>
</c:forEach>
<button>변경</button>
<a href='delete?no=${board.no}'>삭제</a>
</form>


</div>
</body>
</html>