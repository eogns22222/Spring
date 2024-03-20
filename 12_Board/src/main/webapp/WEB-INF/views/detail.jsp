<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<link rel="stylesheet" href="resources/common.css" type="text/css">
<style>

</style>
</head>
<body>
	<table>
		<tr>
			<th>글번호</th>
			<td>${bbs.idx}</td>
		</tr>
		<tr>
			<th>조회수</th>
			<td>${bbs.bHit}</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${bbs.subject}</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${bbs.user_name}</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>${bbs.content}</td>
		</tr>
		<tr>
			<th colspan="2">
				<input type="button" onclick="location.href='./'" value="리스트">
				<button onclick="location.href='updateForm?idx=${bbs.idx}'">수정</button>
			</th>
		</tr>
	</table>
</body>
<script>

</script>
</html>



















