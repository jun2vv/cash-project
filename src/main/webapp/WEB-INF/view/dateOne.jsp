<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>일별가계부 ${targetYear}년 ${targetMonth+1}월 ${targetDate}일</h1>
	<table>
		<tr>
			<th>번호</th>
			<th>카테고리</th>
			<th>금액</th>
			<th>메모</th>
			<th>수정일</th>
			<th>생성일</th>
		</tr>
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
	</table>
	<a style="text-align: right;" href="${pageContext.request.contextPath}/calendar">뒤로가기</a>
	<a style="text-align: right;" href="${pageContext.request.contextPath}/memberOne">회원정보</a>
	<a style="text-align: right;" href="${pageContext.request.contextPath}/addCashbook?targetYear=${targetYear}&targetMonth=${targetMonth}&targetDate=${targetDate}">추가하기</a>
</body>
</html>