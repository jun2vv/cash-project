<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		// 회원정보 수정버튼 클릭시 실행
		$('#modifyMemberBtn').click(function(){
			if($('#prePw').val().length == 0) {
				console.log($('#prePw').val());
				$('#prePwMsg').text('비밀번호를 입력해주세요');
				return;
			} else {
				$('#prePwMsg').text('');
			}
			
			if($('#modifyPw').val().length == 0) {
				console.log($('#modifyPw').val());
				$('#modifyPwMsg').text('변경할 비밀번호를 입력해주세요');
				return;
			} else {
				$('#modifyPwMsg').text('');
			}
			
			if($('#modifyPwCk').val().length == 0) {
				console.log($('#modifyPwCk').val());
				$('#modifyPwCkMsg').text('재확인 비밀번호를 입력해주세요');
				return;
				// 변경할 비밀번호와 확인비밀번호가 일치하지 않으면 실행
			} else if($('#modifyPw').val() != $('#modifyPwCk').val()) {
				$('#modifyPwCkMsg').text('변경비밀번호와 확인비밀번호가 일치하지 않습니다');
				return;
			} else {
				$('#modifyPwCkMsg').text('');
			}
			
			$('#modifyMemberForm').submit();
		});
	});
</script>
</head>
<body>
	<%
		String msg = (String)request.getParameter("msg");
		if(msg != null) {
	%>
			<script>
				alert('<%=msg %>');
			</script>		
	<%
		}
	%>

	<h1>회원정보수정</h1>
	<form id="modifyMemberForm" method="post" action="${pageContext.request.contextPath}/on/modifyMember">
	<table>
		<tr>
			<td>기존 비밀번호</td>
			<td>
				<input id="prePw" type="text" name="memberPw">
				<span id="prePwMsg"></span>
			</td>
		</tr>
		<tr>
			<td>비밀번호 수정</td>
			<td>
				<input id="modifyPw" type="text" name="modifyPw">
				<span id="modifyPwMsg"></span>
			</td>
		</tr>
		<tr>
			<td>변경비밀번호 재확인</td>
			<td>
				<input id="modifyPwCk" type="text" name="modifyPw2">
				<span id="modifyPwCkMsg"></span>
			</td>
		</tr>
	</table>
	<button id="modifyMemberBtn" type="button">수정하기</button>
	</form>
</body>
</html>