<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	table, th, td{
		border: 1px solid black;
		border-collapse: collapse;
		padding: 5px 10px;
	}
</style>
</head>
<body>
	<h2>member 테이블 생성</h2>
	<table>
		<tr>
			<th>컬럼명</th>
			<th>타입</th>
			<th>사이즈</th>
		</tr>
		<tr>
			<td>ID</td>
			<td>VARCHAR</td>
			<td>50</td>
		</tr>
		<tr>
			<td>PW</td>
			<td>VARCHAR</td>
			<td>100</td>
		</tr>
		<tr>
			<td>NAME</td>
			<td>VARCHAR</td>
			<td>50</td>
		</tr>
		<tr>
			<td>AGE</td>
			<td>INT</td>
			<td>4</td>
		</tr>
		<tr>
			<td>GENDER</td>
			<td>VARCHAR</td>
			<td>4</td>
		</tr>
		<tr>
			<td>EMAIL</td>
			<td>VARCHAR</td>
			<td>100</td>
		</tr>
	</table>
	<a href="stmt">테이블 생성 요청</a>
	테이블 생성 결과 : ${result}
	<form action="insert" method="post">
		ID : <input type="text" name="id"><br/>
		PW : <input type="text" name="pw"><br/>
		NAME : <input type="text" name="name"><br/>
		AGE : <input type="text" name="age"><br/>
		GENDER : <input type="text" name="gender"><br/>
		EMAIL : <input type="text" name="email"><br/>
		<input type="submit" value="회원가입">
	</form>
	추가된 데이터 수 : ${row}
	<hr/>
	<a href="list">회원 목록 보기</a>
	
</body>
</html>














