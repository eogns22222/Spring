<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/common.css" type="text/css">
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>    
<script src="resources/js/jquery.twbsPagination.js" type="text/javascript"></script>
<style>
</style>
</head>
<body>
	게시판 리스트
	${loginBox}
	<button onclick="location.href='write.go'">글쓰기</button>
	
	<c:if test="${sessionScope.loginInfo.perm eq 'on'}">
		<a href="member_list">회원리스트 보기</a>
	</c:if>
	<hr />
	<button onclick="del()">선택 삭제</button>
	<select id="pagePerNum">
		<option value="5">5</option>
		<option value="10">10</option>
		<option value="15">15</option>
		<option value="20">20</option>
	</select>
	<table>
		<thead>
			<tr>
				<th><input type="checkbox" id="all" /></th>
				<th>글번호</th>
				<th>이미지</th>
				<th>제목</th>
				<th>작성자</th>
				<th>조회수</th>
				<th>날짜</th>
			</tr>
		</thead>
		<tbody id="list"></tbody>
 		<tr>
			<td colspan="7">
				<div class="container">
					<nav aria-label="Page navigation" style="text-align: center">
						<ul class="pagination" id="pagination"></ul>
					</nav>
				</div>
			</td>
		</tr>
	</table>
</body>
<script>
	var showPage = 1;
	
	$(document).ready(function(){
		listCall(showPage);
	});
	
	$('#pagePerNum').on('change', function(){
		$('#pagination').twbsPagination('destroy');
		listCall(showPage);
	});
	
	function listCall(page){
		$.ajax({
			type : 'post',
			url : './list.ajax',
			data : {
				'page' : page,
				'cnt' : $('#pagePerNum').val()
			},
			dataType : 'json',
			success : function(data) {
				drawList(data.list);
				
				var startPage = data.currPage > data.totalPages ? data.totalPages : data.currPage;
				
				$('#pagination').twbsPagination({
				    startPage: startPage, 
				    totalPages: data.totalPages, 
				    visiblePages: 5, 
				    onPageClick:function(evt, pg){ 
				       console.log(evt); 
				       console.log(pg); 
				       showPage = pg;
				       listCall(pg);
				    }
				 });
			},
			error : function(error) {
				console.log('sss');
				console.log(error);
			}
		});
		
	}
	
	
    function drawList(list){
        var content = '';
        for (item of list) {

            content += '<tr>';
            content += '<td><input type="checkbox" name="del" value="' + item.idx +'"/></td>';
            content += '<td>' + item.idx + '</td>';

            var img = item.imt_cnt > 0 ? 'image.png' : 'no_image.png';

            content += '<td><img class="icon" src="resources/img/' + img + '"/></td>';
            content += '<td><a href="detail?idx=' + item.idx + '">' + item.subject + '</a></td>';
            content += '<td>' + item.user_name + '</td>';
            content += '<td>' + item.bHit + '</td>';

            var date = new Date(item.reg_date);
            var dateStr = date.toLocaleDateString("ko-KR"); //en-US

            content += '<td>' + dateStr + '</td>';
            content += '</tr>';
        }

        $('#list').html(content);
	}
	
</script>
</html>






















