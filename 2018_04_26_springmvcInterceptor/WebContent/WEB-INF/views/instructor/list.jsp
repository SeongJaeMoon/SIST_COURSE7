<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%-- jstl-1.2.jar 파일 필요 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1">

<title>SIST_쌍용교육센터</title>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<style>

div#input:hover, div#output:hover {
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
}

</style>

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


<script>
$(document).ready(function(){

	   // jQuery methods go here...

});
</script>
</head>
<body>

	<div class="container">
	
		<div style="margin-bottom: 1%;">
			<div>
				<h1 style="font-size: xx-large;">
					<a href="${pageContext.request.contextPath}/instructor/list"><img
						src="${pageContext.request.contextPath}/resources/img/sist_logo.png"
						alt="logo" style="vertical-align: bottom;"></a> 성적관리<small>v4.0</small>
				</h1>
			</div>
			<div>
				<ul class="nav nav-pills nav-justified ">
					<li class="active"><a
						href="">강사정보</a></li>
					<li><a
						href="">스케줄조회</a></li>
					<li><a
						href="">시험관리</a></li>
					<li><a
						href="">성적관리</a></li>
					<li><a href="${pageContext.request.contextPath}/logout"
						style="color: red">${sessionScope.instructorLoginInfo.id_} 로그아웃</a></li>
				</ul>
			</div>

		</div>
		
		<div class="panel-group" id="output">
			<div class="panel panel-default">
				<div class="panel-heading">강사 정보</div>
				<div class="panel-body">
				
				<h1>강사 개인 정보 페이지</h1>
	

				</div>
			</div>
		</div>
	</div>

</body>
</html>