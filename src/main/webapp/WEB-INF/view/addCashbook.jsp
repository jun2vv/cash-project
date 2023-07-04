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
		// 추가하기 클릭시 실행
		$('#addCashbookBtn').click(function(){
			if($('#categoryId').val().length == 0) {
				$('#categoryIdMsg').text('카테고리를 입력해주세요');
				return;
			} else {
				$('#categoryIdMsg').text('');
			}
			
			if($('#priceId').val().length == 0 || isNaN($('#priceId').val()) == true){
				$('#priceIdMsg').text('금액을(숫자로) 작성해야합니다.');
				return;
			} else {
				$('#priceIdMsg').text('');
			}
			
			if($('#memoId').val().length == 0) {
				$('#memoIdMsg').text('해시태그를 입력해주세요');
				return;
			} else {
				$('#memoIdMsg').text('');
			}
			
			$('#addCashbookForm').submit();
		});
	});
</script>
</head>
<body>
	<h1>추가 날짜${targetYear}년${targetMonth+1}월${targetDate}일</h1>
	<form id="addCashbookForm" action="${pageContext.request.contextPath}/addCashbook" method="post">
		<input type="hidden" name="date" value="${targetYear}-${targetMonth+1}-${targetDate}">
		<table>
			<tr>
				<td>카테고리</td>
				<td>
					<select id="categoryId" name="category">
						<option value="수입">수입</option>
						<option value="지출">지출</option>
					</select>
					<span id="categoryIdMsg"></span>
				</td>			
			</tr>
			<tr>
				<td>금액</td>
				<td>
					<input id="priceId" type="text" name="price" placeholder="숫자로 입력해주세요">
					<span id="priceIdMsg"></span>
				</td>
			</tr>
			<tr>
				<td>해시태그</td>
				<td>
					<input id="memoId" type="text" name="memo">
					<span id="memoIdMsg"></span>
				</td>
			</tr>
		</table>
		<button id="addCashbookBtn" type="button">추가하기</button>
	</form>
</body>
</html>