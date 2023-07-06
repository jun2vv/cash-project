<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
	body {
		font-family: Arial, sans-serif;
	}

	h1 {
		text-align: center;
		margin-top: 20px;
	}

	table {
		width: 100%;
		border-collapse: collapse;
		margin-top: 20px;
	}

	th, td {
		padding: 10px;
		text-align: center;
		border: 1px solid #ccc;
	}

	thead {
		background-color: #f2f2f2;
	}

	a {
		text-decoration: none;
		color: #007bff;
	}

	a:hover {
		text-decoration: underline;
	}

	.back-link {
		display: block;
		text-align: center;
		margin-top: 20px;
	}
</style>
<body>
	<h1>일별가계부 ${targetYear}년 ${targetMonth+1}월 ${targetDate}일</h1>
	<table>
		<thead>
			<tr>
				<th>번호</th>
				<th>카테고리</th>
				<th>금액</th>
				<th>메모</th>
				<th>수정일</th>
				<th>생성일</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="list" items="${list}" >
				<tr>
					<td>${list.cashbookNo}</td>
					<td>${list.category}</td>
					<td>${list.price}</td>
					<td>${list.memo}</td>
					<td>${list.updatedate}</td>
					<td>${list.createdate}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- 뒤로가기 -->
	<a href="${pageContext.request.contextPath}/on/calendar"><img width="50" height="50" src="${pageContext.request.contextPath}/icon/back.png"></a>
	
	<a style="text-align: right;" href="${pageContext.request.contextPath}/on/memberOne">회원정보</a>
	<a style="text-align: right;" href="${pageContext.request.contextPath}/on/addCashbook?targetYear=${targetYear}&targetMonth=${targetMonth}&targetDate=${targetDate}">추가하기</a>
</body>
</html>