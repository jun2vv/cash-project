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
	  color: #353535;
	  padding-top: 30px;
	}
	
	.container1 {
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
	  border: 1px solid #353535;
	  border-radius: 5px;
	  text-decoration: none;
	  color: #353535;
	}
	
	.links a:hover {
	  background-color: #D5D5D5;
	  color: #000000;
	}
</style>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<meta charset="UTF-8">
<title>Cashbook</title>
</head>
<body>
	<h1>Home</h1>
  	<div class="container1">
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
	<div class="container1">
		<br>
		<div class="row">
		  	<div style="text-align: center;">
				<h3>Model2구조 회원전용 개인 가계부 프로젝트</h3>
				- 기간 2023.06.30 ~ 2023.07.20 -<br>
			</div>
			<div class="col-sm-6">
				<span style="font-weight: bold;">
				<br>-개발환경 - <br>
				<span style="color: red;">OS</span> : window11<br>
				<span style="color: red;">Tool</span> : Eclipse,heidiSQL<br>
				<span style="color: red;">DB</span> : mariaDB(3.1.3)<br>
				<span style="color: red;">WAS</span> : Tomcat9<br>
				<span style="color: red;">Language</span> : HTML5,<br> 
				CSS3, Java(JDK17), mySQL, JavaScript
				</span>
			</div>
			<div class="col-sm-6">
				<div>
					<span style="font-weight: bold;">
					<br>- 구현기능 -<br>
					1. session을 사용한 로그인 및<br>
					&nbsp;&nbsp;&nbsp;회원가입<br>
					2. 회원 비밀번호 수정 및 회원탈퇴<br>
					3. Calendar라이브러리를 사용한<br> 
					&nbsp;&nbsp;&nbsp;달력 기능<br>
					4. 해시태그를 사용한 캐시북<br> 
					&nbsp;&nbsp;&nbsp;관리 기능<br>
					5. 캐쉬북 추가, 삭제<br>
					6. Listener를 사용하여 방문자 수<br> 
					&nbsp;&nbsp;&nbsp;구하는 기능<br>
					7. Cookie를 사용하여 아이디값 저장
					</span>
				</div>
			</div>
		</div>
  	</div>
</body>
</html>