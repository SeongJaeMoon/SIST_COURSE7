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

		//최초 실행시 Ajax 자동 요청 및 응답 처리
		ajax($("select#job_id").val());
		
		//직위 선택시 선택된 직위 정보를 가지고 Ajax 요청 및 응답 처리
		$("select#job_id").on("change", function() {
			ajax($(this).val());
		});

	});
	
	function ajax(temp) {
		$.ajax({
			url : "${pageContext.request.contextPath}/employee/getminbasicpay",
			data : {
				query : temp
			},
			success : function(data) {
				$("input#basicpay").attr("placeholder", "기본급 (최소 " + data.minBasicPay + "원 이상)");
			}
		});		
	}
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
					<li class="active"><a
						href="${pageContext.request.contextPath}/employee/list">직원관리</a></li>
					<li><a href="${pageContext.request.contextPath}/region/list">지역관리</a></li>
					<li><a
						href="${pageContext.request.contextPath}/department/list">부서관리</a></li>
					<li><a href="${pageContext.request.contextPath}/position/list">직위관리</a></li>
					<li><a href="${pageContext.request.contextPath}/logout"
						style="color: red">${sessionScope.adminLoginInfo.id_} 로그아웃</a></li>
				</ul>
			</div>

		</div>
		<div class="panel-group" id="input">
			<div class="panel panel-default">
				<div class="panel-heading">직원 수정</div>
				<div class="panel-body">
					<form role="form"
						action="${pageContext.request.contextPath}/employee/update"
						method="post">
						<div class="form-group">
							<label for="eid">사번:</label> <input type="text"
								class="form-control" id="eid" name="eid"
								readonly="readonly" value="${emp.eid}">
						</div>
						<div class="form-group">
							<label for="name_">이름:</label> <input type="text"
								class="form-control" id="name_" name="name_"
								placeholder="이름 (30자 이내)" maxlength="30" required="required"  value="${emp.name_}">
						</div>
						<div class="form-group">
							<label for="ssn">주민번호(뒷자리):</label> <input type="number"
								class="form-control " id="ssn" name="ssn"
								placeholder="주민번호 뒷자리 (NNNNNNN)" min="1000000" max="9999999"
								required="required"  value="${emp.ssn}">
						</div>

						<div class="form-group">
							<label for="phone">전화번호:</label> <input type="text"
								class="form-control" id="phone" name="phone"
								placeholder="전화번호 (30자 이내)" maxlength="30" required="required"  value="${emp.phone}">
						</div>
						<div class="form-group">
							<label for="hiredate">입사일:</label> <input type="date"
								class="form-control" id="hiredate" name="hiredate"
								placeholder="입사일 (YYYY-MM-DD)" required="required"  value="${emp.hiredate}">
						</div>
						<div class="form-group">
							<label for="reg_id">지역:</label> <select class="form-control"
								id="reg_id" name="reg_id">
								<!-- 
								<option value="REG01">서울</option>
								 -->
								<c:forEach var="e" items="${regionList}">
								<option value="${e.reg_id}" ${(e.reg_id == emp.reg_id)?"selected":""}>${e.reg_name}</option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group">
							<label for="dept_id">부서:</label> <select
								class="form-control" id="dept_id" name="dept_id">
								<!-- 
								<option value="DEPT01">개발부</option>
								 -->
								<c:forEach var="e" items="${deptList}">
								<option value="${e.dept_id}" ${(e.dept_id == emp.dept_id)?"selected":""}>${e.dept_name}</option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group">
							<label for="job_id">직위:</label> <select class="form-control"
								id="job_id" name="job_id">
								<!-- 
								<option value="JOB01">사원</option>
								 -->
								<c:forEach var="e" items="${jobList}">
								<option value="${e.job_id}" ${(e.job_id == emp.job_id)?"selected":""}>${e.job_title}</option>
								</c:forEach> 
							</select>
						</div>
						<div class="form-group">
							<label for="basicpay">기본급:</label> <input type="number"
								class="form-control" id="basicpay" name="basicpay"
								placeholder="기본급 (최소 0원 이상)" required="required" value="${emp.basicpay}">
						</div>
						<div class="form-group">
							<label for="extrapay">수당:</label> <input type="number"
								class="form-control" id="extrapay" name="extrapay"
								placeholder="수당" required="required" value="${emp.extrapay}">
						</div>

						<button type="submit" class="btn btn-default">Submit</button>
					</form>

				</div>
			</div>
		</div>
	</div>

</body>
</html>