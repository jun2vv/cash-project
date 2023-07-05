<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.Calendar"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%><!-- jstl에서 substring호출 -->
<!-- JSP컴파일시 자바코드로 변환되는 c:... (제어문)사용코드 커스텀태그 사용가능 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
	<!-- Latest compiled and minified CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
	<!-- Latest compiled JavaScript -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<style>
  .hashtag-list {
    display: none;
  }
  .hashtag-list.active {
    display: block;
  }
</style>
</head>
<body>
	<!-- 변수값or반환값 대신 EL사용 -->
	<!-- 속성값대신 EL사용 표현식
		ex)
		request.getAttribute("targetYear") -- requestScope.targetYear 보통requestScope는생략 
		장점 : 형변환연산필요없다 EL이자동으로 처리해줌
	-->
		
	<!-- 자바코드(제어문) if,for문 대신 JSTL 사용 -->
	
	<div class="container">
		<a style="text-align: right;" href="${pageContext.request.contextPath}/cashbook">뒤로가기</a>
		<h1 style=" text-align: center;">${targetYear}년 ${targetMonth+1}월</h1>
		<a href="${pageContext.request.contextPath}/calendar?targetYear=${targetYear}&targetMonth=${targetMonth-1}">이전달</a>
		<a href="${pageContext.request.contextPath}/calendar?targetYear=${targetYear}&targetMonth=${targetMonth+1}">다음달</a>
		<script>
		  $(document).ready(function() {
		    $('.hashtag-toggle').click(function(e) {
		      e.preventDefault();
		      $(this).siblings('.hashtag-list').slideToggle();
		    });
		  });
		</script>
		<div>
		<h2>해시태그</h2>
			<div>
	    		<nav>
	      			<ul>
	        			<li>
				        	<a style="color: black;" href="#" class="hashtag-toggle">전체해시태그</a>
				    			<ul class="hashtag-list">
				           			<c:forEach var="map" items="${htList}">
				              			<span>
				                			<a style="color: #3f729b;" href="${pageContext.request.contextPath}/cashbookListByTag?hashtag=${map.word}">${map.word}(${map.cnt})</a>
				              			</span>
				            		</c:forEach>
				          		</ul>
				       	</li>
				        <li>
				          	<a style="color: black;" href="#" class="hashtag-toggle">월별해시태그</a>
				         		<ul class="hashtag-list">
				            		<c:forEach var="map" items="${htList}">
				              			<span>
				                			<a style="color: #3f729b;" href="${pageContext.request.contextPath}/hashtag?targetYear=${targetYear}&targetMonth=${targetMonth}&hashtag=${map.word}">${map.word}(${map.cnt})</a>
				              			</span>
				            		</c:forEach>
				          		</ul>
				      	</li>
					</ul>
				</nav>
			</div>
		</div>
		<br>
		
		<table class="table table-striped">
			<tr>
				<th>일</th>
				<th>월</th>
				<th>화</th>
				<th>수</th>
				<th>목</th>
				<th>금</th>
				<th>토</th>
			</tr>
			<tr>
				<!-- 0부터 totalCell-1 까지 반복 -->
				<c:forEach var="i" begin="0" end="${totalCell-1}" step="1">
					<!-- 날짜 출력변수 월의 시작공백 빼주고 출력 -->
					<c:set var="d" value="${i-beginBlank+1}"></c:set>
					<!-- 전체날짜에서 7 로 나누어 떨어지면 행변경-->
					<c:if test="${i != 0 && i%7==0}">
						</tr><tr>
					</c:if>
					
					<!-- 날짜출력  i-beginBlank+1 = 현재달의 날짜 1~30 or 1 ~31 -->
					<c:if test="${d > 0 && d <= lastDate}">
						<td style="height: 100px; width: 100px;">
							<div><a style="color: black;" href="${pageContext.request.contextPath}/dateOne?targetDate=${d}&targetMonth=${targetMonth}&targetYear=${targetYear}">${d}</a></div>
							<c:forEach var="c" items="${list}">
								<!-- 날짜별 수입지출을 알기위해 년도를 날짜만 나타나게 자른다 -->
								<c:if test="${d == fn:substring(c.cashbookDate,8,10)}">
									<div>
										<c:if test="${c.category == '수입' }">
											<span>+${c.price}</span>
										</c:if>
										<c:if test="${c.category == '지출' }">
											<span style="color: red;">-${c.price}</span>
										</c:if>
									</div>
								</c:if>
							</c:forEach>
						</td>
					</c:if>
					
					<!-- 공백출력 -->
					<c:if test="${!(d > 0 && d <= lastDate)}">
						<td></td>
					</c:if>
				</c:forEach>				
		</table>
		
		<!--  
		<table class="table table-striped">
			<tr>
				<th>일</th>
				<th>월</th>
				<th>화</th>
				<th>수</th>
				<th>목</th>
				<th>금</th>
				<th>토</th>
			</tr>
			
			<tr>
				<%	
					/*
					
					// 총42칸 출력
					for(int i=0; i<totalCell; i++) {
						// 날짜 출력변수 월의 시작공백 빼주고 출력
						int num = i-beginBlank+1;
						// 전체날짜에서 7 로 나누어 떨어지면 행변경
						if(i%7==0) {
				%>
							</tr><tr>						
				<% 
						}
						// 1일부터 달의 마지막날까지 출력
						// 오늘 날짜인 경우 색상표시
						// 오늘 날짜 구하기
						
						
						if(num > 0 && num <= lastDate) {
							if(targetYear == currentYear && targetMonth == currentMonth && num == currentDate) {
								color = "green";
							} 
							// i%7 = 나머지가 0이면 일요일 이므로 red색상표시
							if(i % 7 == 0) {
								color = "red";
							}
							// i%7 = 나머지가 6이면 토요일 이므로 blue색상표시
							if(i % 7 == 6) {
								color = "blue";
							}
				%>
							<td style="color: <%=color%>">
								날짜출력 
								<%=num %>
							</td>
				<% 		
						// 1일앞 마지막날짜 뒤 공백출력
						} else {
				%>	
							<td></td>
				<% 
						}
						
					}
				
					*/
				%>
		</table>
		-->
	</div>
</body>
</html>