<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>cashbook.jsp</h1>
	<div>
		현재접속자 : ${currentCounter} <!-- application.getAttribute(""); 안에 저장되어있음 -->
	</div>
	<div>
		오늘 접속자 수 : ${counter} <!-- request.getAttribute("counter") -->
	</div>
	<div>
		누적접속자 수 : ${totalCounter} <!-- request.getAttribute("totalCounter") -->
	</div>
	<a href="${pageContext.request.contextPath}/on/logout">로그아웃</a>
	<a href="${pageContext.request.contextPath}/on/memberOne">회원정보</a>
	<a href="${pageContext.request.contextPath}/on/calendar">가계부</a>
</body>
</html>