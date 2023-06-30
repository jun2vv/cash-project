<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원탈퇴</h1>
	<form method="post" action="${pageContext.request.contextPath}/removeMember">
		<table>
			<tr>
				<td>비밀번호를 입력해주세요</td>
				<td>
					<input type="text" name="memberPw">
				</td>
			</tr>
		</table>
	<button type="submit">탈퇴하기</button>
	</form>
</body>
</html>