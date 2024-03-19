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
	<table>
		<tr>
			<th>ID</th>
			<td>${detail.id}</td>
		</tr>
		<tr>
			<th>PW</th>
			<td>${detail.pw}</td>
		</tr>
		<tr>
			<th>NAME</th>
			<td>${detail.name}</td>
		</tr>
		<tr>
			<th>AGE</th>
			<td>${detail.age}</td>
		</tr>
		<tr>
			<th>GENDER</th>
			<td>${detail.gender}</td>
		</tr>
		<tr>
			<th>EMAIL</th>
			<td>${detail.email}</td>
		</tr>
		<tr>
			<th colspan="2">
				<a href="list">회원목록</a>
			</th>
		</tr>
	</table>
</body>
<script>

</script>
</html>

























