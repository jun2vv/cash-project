<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>${hashtag}(태그) 목록</h1>
	<table>
		<tr>
			<th>번호</th>
			<th>카테고리</th>
			<th>금액</th>
			<th>해시태그</th>
			<th>수정일</th>
			<th>생성일</th>
		</tr>
			<c:forEach var="map" items="${list}" >
				<tr>
					<td>
						<a href="${pageContext.request.contextPath}/dateOne?cashbookDate=${map.cashbookDate}&targetYear=${targetYear}&targetMonth=${targetMonth}">
							${map.cashbookNo}
						</a>
					</td>
					<td>${map.category}</td>
					<td>${map.price}</td>
					<td>${map.memo}</td>
					<td>${map.updatedate}</td>
					<td>${map.createdate}</td>
				</tr>
			</c:forEach>
	</table>
</body>
</html>