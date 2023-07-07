<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		// 로그인 클릭시 실행
		$('#loginBtn').click(function(){
			if($('#loginId').val().length == 0) {
				console.log($('#loginId').val());
				$('#loginIdMsg').text('아이디를 입력해주세요');
				return;
			} else {
				$('#loginIdMsg').text('');
			}
			
			if($('#loginPw').val().length == 0) {
				console.log($('#loginPw').val());
				$('#loginPwMsg').text('비밀번호를 입력해주세요');
				return;
			} else {
				$('#loginPwMsg').text('');
			}
			
			$('#loginForm').submit();
		});
	});
		
		
</script>
</head>
<body>
	<%
		String msg = (String)request.getAttribute("msg");
		if(msg != null) {
	%>
			<script>
				alert(${msg});
			</script>
	<% 
		}
	%>
	<!--  el코드도 출력은된다
		<p>msg</p>
	-->
	<h1>로그인</h1>
								<!--
									request.getContextPath() 요놈은
									${pageContext.request.contextPath} 이렇게 EL안에서 쓸수있다.   
								-->
	<form id="loginForm" method="post" action="${pageContext.request.contextPath}/off/login">
		<table border="1">
			<tr>
				<td>id</td>
				<td>
					<input id="loginId" type="text" name="memberId" value="${cookieId}">
					<span id="loginIdMsg" class="msg"></span>
				</td>
			</tr>
			<tr>
				<td>pw</td>
				<td>
					<input id="loginPw" type="password" name="memberPw">
					<span id="loginPwMsg" class="msg"></span>
				</td>
			</tr>
			<tr>
				<td>
					<input type="checkbox" name="idSave" value="y">id저장
				</td>
			</tr>
		</table>
		<button id="loginBtn" type="button">로그인</button>
	</form>
		<a href="${pageContext.request.contextPath}/addMember">회원가입</a>
</body>
</html>