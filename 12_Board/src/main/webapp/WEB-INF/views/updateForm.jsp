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
	<form action="update" method="post">
		<table>
			<tr>
				<th>글번호</th>
				<td>${bbs.idx}<input type="hidden" name="idx" value="${bbs.idx}"></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="subject" value="${bbs.subject}"></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><input type="text" name="user_name" value="${bbs.user_name}"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea name="content">${bbs.content}</textarea></td>
			</tr>
			<tr>
				<th colspan="2">
					<input type="button" onclick="location.href='./list'" value="취소">
					<button>저장</button>
				</th>
			</tr>
		</table>
	</form>
</body>
<script>

</script>
</html>



















