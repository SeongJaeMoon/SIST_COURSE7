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

<script>
	$(document).ready(function() {

		//검색
		var key = "${key}";
		var value = "${value}";
        $("select#key > option[value='"+key+"']").attr("selected","selected");
        $("input#value").val(value);
        
	});
</script>
</head>
<body>
	<div class="container">
		<div style="margin-bottom: 1%;">
			<div>
				<h1 style="font-size: xx-large;">
					<a href="${pageContext.request.contextPath}/employee/list"><img
						src="${pageContext.request.contextPath}/resources/img/sist_logo.png"
						alt="logo" style="vertical-align: bottom;"></a> 직원관리<small>v4.0</small>
				</h1>
			</div>
			<div>
				<ul class="nav nav-pills nav-justified ">
					<li class="active"><a
						href="${pageContext.request.contextPath}/employee/list">직원관리</a></li>
					<li><a href="${pageContext.request.contextPath}/region/list">지역관리</a></li>
					<li><a
						href="${pageContext.request.contextPath}/department/list">부서관리</a></li>
					<li><a href="${pageContext.request.contextPath}/position/list">직위관리</a></li>
					<li><a href="${pageContext.request.contextPath}/logout"
						style="color: red">홍길동 로그아웃</a></li>
				</ul>
			</div>

		</div>
		<div class="panel-group" id="output">
			<div class="panel panel-default">
				<div class="panel-heading">직원 출력</div>
				<div class="panel-body">
					<form class="form-inline" role="form" method="post">
						<select class="form-control" id="key" name="key">
							<option value="eid">번호</option>
							<option value="name_">이름</option>
							<option value="reg_name">지역</option>
							<option value="dept_name">부서</option>
							<option value="job_title">직위</option>
						</select> <label for="name"></label> <input type="text"
							class="form-control" id="value" name="value"
							required="required">
						<button type="submit" class="btn btn-default">
							<span class="glyphicon glyphicon-search"></span> Search
						</button>
					</form>


					<table class="table">
						<thead>
							<tr>
								<th><a href="${pageContext.request.contextPath}/employee/list?key=eid">사번</a></th>
								<th><a href="${pageContext.request.contextPath}/employee/list?key=name_">이름/사진</a></th>
								<th><a href="${pageContext.request.contextPath}/employee/list?key=ssn">주민번호</a></th>
								<th><a href="${pageContext.request.contextPath}/employee/list?key=phone">전화번호</a></th>
								<th><a href="${pageContext.request.contextPath}/employee/list?key=hiredate">입사일</a></th>
								<th><a href="${pageContext.request.contextPath}/employee/list?key=reg_name">지역명</a></th>
								<th><a href="${pageContext.request.contextPath}/employee/list?key=dept_name">부서명</a></th>
								<th><a href="${pageContext.request.contextPath}/employee/list?key=job_title">직위명</a></th>
								<th><a href="${pageContext.request.contextPath}/employee/list?key=basicpay">기본급</a></th>
								<th><a href="${pageContext.request.contextPath}/employee/list?key=extrapay">수당</a></th>
								<th><a href="${pageContext.request.contextPath}/employee/list?key=pay">급여</a></th>
								<th>사진등록</th>
								<th>삭제/수정</th>
							</tr>
						</thead>
						<tbody>
							<!-- 
							<tr>
								<td>1001</td>
								<td>홍길동/
									<button type="button" class="btn btn-default btn-xs"
										data-toggle="modal" data-target="#myModal">사진</button>
								</td>
								<td>1022432</td>
								<td>010-123-1234</td>
								<td>1998-10-11</td>
								<td>서울</td>
								<td>개발부</td>
								<td>사원</td>
								<td>2,000,000</td>
								<td>1,000,000</td>
								<td>3,000,000</td>
								<td><button type="button" class="btn btn-default btn-xs">사진등록</button></td>
								<td><button type="button" class="btn btn-default btn-xs">삭제</button>/<button type="button" class="btn btn-default btn-xs  btnUpdateForm" >수정</button></td>
							</tr>
							 -->
							<c:forEach var="e" items="${list}">
							<tr>
								<td>${e.eid}</td>
								<td>${e.name_}/
									<button type="button" class="btn btn-default btn-xs"
										data-toggle="modal" data-target="#myModal">사진</button>
								</td>
								<td>${e.ssn}</td>
								<td>${e.phone}</td>
								<td>${e.hiredate}</td>
								<td>${e.reg_name}</td>
								<td>${e.dept_name}</td>
								<td>${e.job_title}</td>
								<td>${e.basicpay}</td>
								<td>${e.extrapay}</td>
								<td>${e.pay}</td>
								<td><button type="button" class="btn btn-default btn-xs">사진등록</button></td>
								<td><button type="button" class="btn btn-default btn-xs">삭제</button>/<button type="button" class="btn btn-default btn-xs  btnUpdateForm" >수정</button></td>
							</tr>
							</c:forEach>

						</tbody>
					</table>

					<form class="form-inline" role="form" method="post">
						<a href="${pageContext.request.contextPath}/employee/insertform"
							class="btn btn-default">Add</a>
						<button type="button" class="btn btn-default">
							Count <span class="badge">${count}</span>
						</button>
					</form>

				</div>
			</div>
		</div>

		<div id="myModal" class="modal fade" role="dialog">
			<div class="modal-dialog modal-sm">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">홍길동의 사진</h4>
					</div>
					<div class="modal-body">
						<div style="text-align: center;">
							<img
								src="${pageContext.request.contextPath}/resources/pictures/avatar.png"
								width="100%">
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>

			</div>
		</div>

	</div>

</body>
</html>
