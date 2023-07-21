<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
	body {
	  font-family: 'Arial', sans-serif;
	  background-color: #f2f2f2;
	  color: #333;
	  margin: 0;
	  padding: 0;
	}
	
	h1 {
	  font-size: 28px;
	  text-align: center;
	  margin-bottom: 30px;
	  color: #007bff;
	  padding-top: 30px;
	}
	
	.container {
	  max-width: 600px;
	  margin: 0 auto;
	  padding: 20px;
	  border-radius: 5px;
	  background-color: #fff;
	  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
	}
	
	.info {
	  margin-bottom: 10px;
	}
	
	.info span {
	  font-weight: bold;
	  color: #007bff;
	}
	
	.links {
	  display: flex;
	  justify-content: center;
	  margin-top: 20px;
	}
	
	.links a {
	  margin: 0 10px;
	  padding: 8px 16px;
	  border: 1px solid #007bff;
	  border-radius: 5px;
	  text-decoration: none;
	  color: #007bff;
	}
	
	.links a:hover {
	  background-color: #007bff;
	  color: #fff;
	}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Home</h1>
  	<div class="container">
		<div class="info">
		  현재접속자 : <span>${currentCounter}</span>
		</div>
		<div class="info">
		  오늘 접속자 수 : <span>${counter}</span>
		</div>
		<div class="info">
		  누적접속자 수 : <span>${totalCounter}</span>
		</div>
		<div class="links">
		  <a href="${pageContext.request.contextPath}/on/logout">로그아웃</a>
		  <a href="${pageContext.request.contextPath}/on/memberOne">회원정보</a>
		  <a href="${pageContext.request.contextPath}/on/calendar">가계부</a>
		</div>
  	</div>
</body>
</html>