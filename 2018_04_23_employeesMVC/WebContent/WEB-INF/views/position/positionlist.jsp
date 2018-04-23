<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%-- jstl-1.2.jar 파일 필요 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>SIST_쌍용교육센터</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<style>
div#input:hover, div#output:hover {
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
}
</style>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>
	<div class="container">
		<div style="margin-bottom: 1%;">
			<div>
				<h1 style="font-size: xx-large;">
					<img
						src="${pageContext.request.contextPath}/resources/img/sist_logo.png"
						alt="logo" style="vertical-align: bottom;"> 직원관리<small>v4.0</small>
				</h1>
			</div>
			<div>
				<ul class="nav nav-pills nav-justified ">
					<li><a
						href="${pageContext.request.contextPath}/employee/list">직원관리</a></li>
					<li><a href="${pageContext.request.contextPath}/region/list">지역관리</a></li>
					<li><a
						href="${pageContext.request.contextPath}/department/list">부서관리</a></li>
					<li class="active"><a href="${pageContext.request.contextPath}/position/list">직위관리</a></li>
					<li><a href="${pageContext.request.contextPath}/logout"
						style="color: red">홍길동 로그아웃</a></li>
				</ul>
			</div>

		</div>
		<div class="panel panel-default" id="input">
			<div class="panel-heading">직위 입력</div>
			<div class="panel-body" id="demo">
				<form role="form"
					action="${pageContext.request.contextPath}/position/insert"
					method="post">
					<div class="form-group">
						<input type="text" class="form-control" id="positionName"
							name="positionName" placeholder="직위명 (30자 이내)" maxlength="30"
							required="required">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" id="minBasicPay"
							name="minBasicPay" placeholder="최소기본급" required="required">
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
				</form>

			</div>
		</div>
		<div class="panel panel-default" id="output">
			<div class="panel-heading">직위 출력</div>
			<div class="panel-body">
				<table class="table">
					<thead>
						<tr>
							<th>번호</th>
							<th>직위명</th>
							<th>최소기본급</th>
							<th></th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<!-- 
						<tr>
							<td>1</td>
							<td>사원</td>
							<td>1,000,000</td>
							<td><button type="button"
									class="btn btn-default btn-xs  btnDelete">삭제</button></td>
							<td><button type="button"
									class="btn btn-default btn-xs  btnUpdate">수정</button></td>
						</tr>
						 -->
						 <c:forEach var="e" items="${list}">
						 <tr>
							<td>${e.job_id}</td>
							<td>${e.job_title}</td>
							<td>${e.minBasicPay}</td>
							<td><button type="button"
									class="btn btn-default btn-xs  btnDelete" ${(e.deleteCheck == 0)?"":"disabled"} >삭제</button></td>
							<td><button type="button"
									class="btn btn-default btn-xs  btnUpdate">수정</button></td>
						</tr>
						 </c:forEach>

					</tbody>
				</table>


			</div>
		</div>
	</div>

</body>
</html>
