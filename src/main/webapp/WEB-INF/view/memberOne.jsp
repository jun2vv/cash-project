<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
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

	<h1>회원정보</h1>
	<table>
		<tr>
			<td>아이디 </td>
			<td>: ${member.memberId}</td>
		</tr>
		<tr>
			<td>비밀번호 </td>
			<td>: ****</td>
		</tr>
		<tr>
			<td>생성일 </td>
			<td>: ${member.createdate}</td>
		</tr>
		<tr>
			<td>수정일 </td>
			<td>: ${member.updatedate}</td>
		</tr>
	</table>
	<a href = "${pageContext.request.contextPath}/on/modifyMember"> 회원정보수정</a>
	<a href="${pageContext.request.contextPath}/on/removeMember">회원탈퇴</a>
	<a href="${pageContext.request.contextPath}/on/cashbook">뒤로가기</a>
	<a href="${pageContext.request.contextPath}/on/calendar">가계부</a>
</body>
</html>