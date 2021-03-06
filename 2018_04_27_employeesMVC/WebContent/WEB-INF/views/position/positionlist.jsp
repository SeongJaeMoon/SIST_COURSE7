<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%-- jstl-1.2.jar 파일 필요 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
		
		//삭제
		$("button.btnDelete").on("click", function() {
			//경고창 메시지
			//삭제 요청 -> 직위번호 전송
			if (confirm("현재 자료를 삭제할까요?")) {
				location.assign("${pageContext.request.contextPath}/position/delete?job_id="+$(this).val());
			}
		});
		
		//수정
		$("button.btnUpdate").on("click", function() {
			
			var obj = JSON.parse($(this).val());

			//입력폼에 대한 제목 수정
			//입력폼에 대한 action 속성 주소 수정
			//입력폼에 대한 hidden 태그의 value 속성에 기존 job_id 설정
			//입력폼에 대한 기존 job_name, minBasicPay 설정
			$("div#input .panel-heading").text("직위 수정");
			$("div#input form").attr("action", "${pageContext.request.contextPath}/position/update");
			$("div#input input[type='hidden']").attr("value", obj.job_id);
			$("div#input input#job_title").attr("value", obj.job_title);
			$("div#input input#minBasicPay").attr("value", obj.minBasicPay);
			 
		});	
		
	});
</script>

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
						style="color: red">${sessionScope.adminLoginInfo.id_} 로그아웃</a></li>
				</ul>
			</div>

		</div>
		<div class="panel panel-default" id="input">
			<div class="panel-heading">직위 입력</div>
			<div class="panel-body" id="demo">
				<form role="form"
					action="${pageContext.request.contextPath}/position/insert"
					method="post">
					<input type="hidden" name="job_id" value="">
					<div class="form-group">
						<input type="text" class="form-control" id="job_title"
							name="job_title" placeholder="직위명 (30자 이내)" maxlength="30"
							required="required">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" id="minBasicPay"
							name="minBasicPay" placeholder="최소기본급" required="required">
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
					
					<!-- 입력, 수정, 삭제 성공, 실패 메시지 출력 -->
					<c:if test="${success==1}">
					<div class="alert alert-success alert-dismissible fade in" style="display:inline-block; padding-top:5px; padding-bottom:5px; margin:0px;">  <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>  <strong>Success!</strong> 요청한 작업이 처리되었습니다. </div>
					</c:if>
					<c:if test="${success==0}">
					<div class="alert alert-danger alert-dismissible fade in" style="display:inline-block; padding-top:5px; padding-bottom:5px; margin:0px;">  <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>  <strong>Fail!</strong> 요청한 작업 처리가 실패했습니다. </div>
					</c:if>
										
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
							<td><fmt:formatNumber value="${e.minBasicPay}" groupingUsed="true" /></td>
							<td><button type="button"
									class="btn btn-default btn-xs  btnDelete" ${(e.deleteCheck == 0)?"":"disabled"} value="${e.job_id}">삭제</button></td>
							<td><button type="button"
									class="btn btn-default btn-xs  btnUpdate"  value='{"job_id":"${e.job_id}", "job_title":"${e.job_title}", "minBasicPay":"${e.minBasicPay}"}'>수정</button></td>
						</tr>
						 </c:forEach>

					</tbody>
				</table>


			</div>
		</div>
	</div>

</body>
</html>
