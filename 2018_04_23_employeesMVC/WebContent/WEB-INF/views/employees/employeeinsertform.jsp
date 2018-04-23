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
		$("#birthday").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "yy-mm-dd"
		});
	});
</script>

</head>
<body>
	<div class="container">
		<div style="margin-bottom: 1%;">
			<div>
				<h1 style="font-size: x-large;">
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
						style="color: red">홍길동 로그아웃</a></li>
				</ul>
			</div>

		</div>
		<div class="panel-group" id="input">
			<div class="panel panel-default">
				<div class="panel-heading">직원 입력</div>
				<div class="panel-body">
					<form role="form"
						action="${pageContext.request.contextPath}/employee/insert"
						method="post">
						<div class="form-group">
							<label for="name">이름:</label> <input type="text"
								class="form-control" id="name" name="name"
								placeholder="이름 (30자 이내)" maxlength="30" required="required">
						</div>
						<div class="form-group">
							<label for="ssn">주민번호(뒷자리):</label> <input type="number"
								class="form-control " id="ssn" name="ssn"
								placeholder="주민번호 뒷자리 (NNNNNNN)" min="1000000" max="9999999"
								required="required">
						</div>
						<div class="form-group">
							<label for="birthday">생년월일:</label> <input type="text"
								class="form-control" id="birthday" name="birthday"
								placeholder="생년월일 (YYYY-MM-DD)" required="required">
						</div>

						<div class="form-group">
							<label for="lunar">양력음력:</label>
							<div class="radio">
								<label class="radio-inline"> <input type="radio"
									name="lunar" value="0" checked="checked">양력
								</label> <label class="radio-inline"> <input type="radio"
									name="lunar" value="1">음력
								</label>
							</div>
						</div>


						<div class="form-group">
							<label for="telephone">전화번호:</label> <input type="text"
								class="form-control" id="telephone" name="telephone"
								placeholder="전화번호 (30자 이내)" maxlength="30" required="required">
						</div>
						<div class="form-group">
							<label for="regionId">지역:</label> <select class="form-control"
								id="regionId" name="regionId">
								<option value="1">서울</option>
							</select>
						</div>
						<div class="form-group">
							<label for="departmentId">부서:</label> <select
								class="form-control" id="departmentId" name="departmentId">
								<option value="1">개발부</option>
							</select>
						</div>
						<div class="form-group">
							<label for="positionId">직위:</label> <select class="form-control"
								id="positionId" name="positionId">
								<option value="1">사원</option>
							</select>
						</div>
						<div class="form-group">
							<label for="basicPay">기본급:</label> <input type="number"
								class="form-control" id="basicPay" name="basicPay"
								placeholder="기본급 (최소 0원 이상)" required="required">
						</div>
						<div class="form-group">
							<label for="extraPay">수당:</label> <input type="number"
								class="form-control" id="extraPay" name="extraPay"
								placeholder="수당" required="required">
						</div>

						<button type="submit" class="btn btn-default">Submit</button>
					</form>

				</div>
			</div>
		</div>
	</div>

</body>
</html>
