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
	<h1>${hashtag}(월별태그) 목록</h1>
	<table>
		<thead>
			<tr>
				<th>번호</th>
				<th>카테고리</th>
				<th>금액</th>
				<th>해시태그</th>
				<th>수정일</th>
				<th>생성일</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="map" items="${list}" >
				<tr>
					<td>
						<a href="${pageContext.request.contextPath}/dateOne?cashbookDate=${map.cashbookDate}&targetYear=${targetYear}&targetMonth=${targetMonth}">
							${map.cashbookNo}
						</a>
					</td>
					<td>${map.category}</td>
					<td>${map.price}</td>
					<td><span style="color: #3f729b;">${map.memo}</span></td>
					<td>${map.updatedate}</td>
					<td>${map.createdate}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="${pageContext.request.contextPath}/calendar"><img width="50" height="50" src="${pageContext.request.contextPath}/icon/back.png"></a>
</body>
</html>