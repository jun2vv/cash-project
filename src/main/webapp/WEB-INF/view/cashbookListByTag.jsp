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
	<h1>${hashtag}(태그) 목록</h1>
	<table>
		<thead>
			<tr>
				<th>번호</th>
				<th>날짜</th>
				<th>카테고리</th>
				<th>금액</th>
				<th>해시태그</th>
				<th>수정일</th>
				<th>생성일</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="list" items="${list}" >
				<tr>
					<td>
						<a href="${pageContext.request.contextPath}/on/dateOne?cashbookDateByTag=${list.cashbookDate}">
							${list.cashbookNo}
						</a>
					</td>
					<td>${list.cashbookDate}</td>
					<td>${list.category}</td>
					<td>${list.price}</td>
					<td><span style="color: #3f729b;">${list.memo}</span></td>
					<td>${list.updatedate}</td>
					<td>${list.createdate}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div>
		<!-- 최소페이지가 1보다크면 이전페이지(이전페이지는 만약 내가 11페이지면 1페이지로 21페이지면 11페이지로)버튼-->
		<c:if test="${minPage > 1}">
			<a href="${pageContext.request.contextPath}/on/cashbookListByTag?currentPage=${minPage-pagePerPage}&hashtag=${hashtag}"><span>이전</span></a>
		</c:if>
		<!-- 최소 페이지부터 최대 페이지까지 표시 -->
		<c:forEach var="i" begin="${minPage}" end="${maxPage}" step="1">
			<!-- 현재페이지는 링크 비활성화 -->
			<c:if test="${i == currentPage}"> 
				<!-- i와 현재페이지가 같은곳이라면 현재위치한 페이지 빨간색표시 -->
				<span class="styled-link" style="color: red;">${i}</span>
			</c:if>
			<!-- i가 현재페이지와 다르다면 출력 -->
			<c:if test="${i != currentPage}">
				<a href="${pageContext.request.contextPath}/on/cashbookListByTag?currentPage=${i}&hashtag=${hashtag}">${i}</a>
			</c:if>
		</c:forEach>
		<!-- maxPage가 마지막페이지와 다르다면 다음버튼 마지막페이지에서는 둘이 같으니 다음버튼이 안나오겠죠
			 다음페이지(만약 내가 1페이지에서 누르면 11페이지로 11페이지에서 누르면 21페이지로)버튼 -->
		<c:if test="${maxPage != lastPage}">
			<a href="${pageContext.request.contextPath}/on/cashbookListByTag?currentPage=${ minPage+pagePerPage}&hashtag=${hashtag}"><span>다음</span></a>
		</c:if>
	</div>
	<div>
		<!-- 뒤로가기 -->
		<a href="${pageContext.request.contextPath}/on/calendar"><img width="50" height="50" src="${pageContext.request.contextPath}/icon/back.png"></a>
	</div>
</body>
</html>