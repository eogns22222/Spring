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
	<h3>회원가입</h3>
	<hr/>
	<form action="join" method="post">
		<table>
			<tr>
				<th>ID</th>
				<td><input type="text" name="id"></td>
			</tr>
			<tr>
				<th>PW</th>
				<td><input type="text" name="pw"></td>
			</tr>
			<tr>
				<th>NAME</th>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<th>AGE</th>
				<td><input type="text" name="age"></td>
			</tr>
			<tr>
				<th>GENDER</th>
				<td>
					<input type="radio" name="gender" value="남"> 남자
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="gender" value="여"> 여자
				</td>
			</tr>
			<tr>
				<th>EMAIL</th>
				<td><input type="text" name="email"></td>
			</tr>
			<tr>
				<th colspan="2"><button>회원가입</button></th>
			</tr>
		</table>
	</form>
</body>
<script>
	var msg = '${msg}';
	if(msg != ''){
		alert(msg);
		
	}
</script>
</html>















