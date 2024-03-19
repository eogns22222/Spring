<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<style>
	table, td, th{
		border: 1px solid black;
		border-collapse: collapse;
	}
	th, td{
		padding: 5px 10px;
	}
</style>
</head>
<body>
	<h3>회원 리스트</h3>
	<hr/>
	<table>
		<tr>
			<th>ID</th>
			<th>이름</th>
			<th>나이</th>
			<th>이메일</th>
			<th>삭제</th>
		</tr>
		<c:forEach items="${list}" var="list">
			<tr>
				<td>${list.id}</td>
				<td><a href="detail?id=${list.id}">${list.name}</a></td>
				<td>${list.age}</td>
				<td>${list.email}</td>
				<td><a href="del?id=${list.id}">삭제</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
<script>

</script>
</html>

























