<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		// 회원가입버튼 클릭시 실행
		$('#addMemberBtn').click(function(){
			if($('#addId').val().length == 0) {
				console.log($('#addId').val());
				$('#addIdMsg').text('아이디를 입력해주세요');
				return;
			} else {
				$('#addIdMsg').text('');
			}
			
			if($('#addPw').val().length == 0) {
				console.log($('#addPw').val());
				$('#addPwMsg').text('비밀번호를 입력해주세요');
				return;
			} else {
				$('#addPwMsg').text('');
			}
			
			$('#addMemberForm').submit();
		});
	});
</script>

</head>
<body>
	<h1>회원가입</h1>
	<form id="addMemberForm" method="post" action="${pageContext.request.contextPath}/addMember">
		<table>
			<tr>
				<td>아이디</td>
				<td>
					<input id="addId" type="text" name="memberId">
					<span id="addIdMsg"></span>
				</td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td>
					<input id="addPw" type="text" name="memberPw">
					<span id="addPwMsg"></span>
				</td>
			</tr>
		</table>
		<button id="addMemberBtn" type="button">회원가입</button>
	</form>
</body>
</html>