<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.Calendar"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%><!-- jstl에서 substring호출 -->
<!-- JSP컴파일시 자바코드로 변환되는 c:... (제어문)사용코드 커스텀태그 사용가능 -->

<html lang="en">
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
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Dashboard - SB Admin</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
        <link href="${pageContext.request.contextPath}/css/css/styles.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
    </head>
    <body class="sb-nav-fixed">
        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            <!-- Navbar Brand-->
            <a class="navbar-brand ps-3" href=${pageContext.request.contextPath}/on/calendar>가계부</a>
            <!-- Sidebar Toggle-->
            <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><i class="fas fa-bars"></i></button>
            <!-- Navbar Search-->
            <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">
                <div class="input-group">
                	<span style="color: white;">${loginMember}님 ${targetYear}년 ${targetMonth+1}월 - ${minusPrice}원 + ${plusPrice}원</span>
                </div>
            </form>
            <!-- Navbar-->
            <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
                <li class="nav-item dropdown">
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="#!">Settings</a></li>
                        <li><a class="dropdown-item" href="#!">Activity Log</a></li>
                        <li><hr class="dropdown-divider" /></li>
                        <li><a class="dropdown-item" href="#!">Logout</a></li>
                    </ul>
                </li>
            </ul>
        </nav>
        <div id="layoutSidenav">
            <div id="layoutSidenav_nav">
                <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                    <div class="sb-sidenav-menu">
                        <div class="nav">
                            <div class="sb-sidenav-menu-heading">memu</div>
                            <a class="nav-link" href="${pageContext.request.contextPath}/on/cashbook">
                                <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                                home
                            </a>
                            <a class="nav-link collapsed" href="${pageContext.request.contextPath}/on/memberOne">
                                <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                                회원정보
                            </a>
                            <!--  
                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapsePages" aria-expanded="false" aria-controls="collapsePages">
                                <div class="sb-nav-link-icon"><i class="fas fa-book-open"></i></div>
                                Pages
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            <div class="collapse" id="collapsePages" aria-labelledby="headingTwo" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav accordion" id="sidenavAccordionPages">
                                    <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#pagesCollapseAuth" aria-expanded="false" aria-controls="pagesCollapseAuth">
                                        Authentication
                                        <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                                    </a>
                                    <div class="collapse" id="pagesCollapseAuth" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordionPages">
                                        <nav class="sb-sidenav-menu-nested nav">
                                            <a class="nav-link" href="login.html">Login</a>
                                            <a class="nav-link" href="register.html">Register</a>
                                            <a class="nav-link" href="password.html">Forgot Password</a>
                                        </nav>
                                    </div>
                                    <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#pagesCollapseError" aria-expanded="false" aria-controls="pagesCollapseError">
                                        Error
                                        <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                                    </a>
                                    <div class="collapse" id="pagesCollapseError" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordionPages">
                                        <nav class="sb-sidenav-menu-nested nav">
                                            <a class="nav-link" href="401.html">401 Page</a>
                                            <a class="nav-link" href="404.html">404 Page</a>
                                            <a class="nav-link" href="500.html">500 Page</a>
                                        </nav>
                                    </div>
                                </nav>
                            </div>
                            -->
                        </div>
                    </div>
                    <div class="sb-sidenav-footer">
                        <div class="small">
                        	<a href="http://3.37.133.115/Shopping/main/home.jsp">
                        		내일주말
                        	</a>
                        </div>
                    </div>
                </nav>
            </div>
            <div id="layoutSidenav_content">
                <main>
                    <!-- 변수값or반환값 대신 EL사용 -->
					<!-- 속성값대신 EL사용 표현식
						ex)
						request.getAttribute("targetYear") -- requestScope.targetYear 보통requestScope는생략 
						장점 : 형변환연산필요없다 EL이자동으로 처리해줌
					-->
						
					<!-- 자바코드(제어문) if,for문 대신 JSTL 사용 -->
					
				<div class="container">
					<h2 style="text-align: center;">${targetYear}년 ${targetMonth+1}월</h2>
					<div>
						<a href="${pageContext.request.contextPath}/on/calendar?targetYear=${targetYear}&targetMonth=${targetMonth-1}">이전달</a>
						<a style="text-align: right;" href="${pageContext.request.contextPath}/on/calendar?targetYear=${targetYear}&targetMonth=${targetMonth+1}">다음달</a>
					</div>
					<div>
					<h2>해시태그</h2>
				        <script>
						  $(document).ready(function() {
						    $('.hashtag-toggle').click(function(e) {
						      e.preventDefault();
						      $(this).siblings('.hashtag-list').slideToggle();
						    });
						  });
						</script>
						<div>
				    		<nav>
				      			<ul>
				        			<li>
							        	<a style="color: black;" href="#" class="hashtag-toggle">전체해시태그</a>
							    			<ul class="hashtag-list">
							           			<c:forEach var="map" items="${htList}">
							              			<span>
							                			<a style="color: #3f729b;" href="${pageContext.request.contextPath}/on/cashbookListByTag?hashtag=${map.word}">${map.word}(${map.cnt})</a>
							              			</span>
							            		</c:forEach>
							          		</ul>
							       	</li>
							        <li>
							          	<a style="color: black;" href="#" class="hashtag-toggle">월별해시태그</a>
							         		<ul class="hashtag-list">
							            		<c:forEach var="map" items="${htList}">
							              			<span>
							                			<a style="color: #3f729b;" href="${pageContext.request.contextPath}/on/hashtag?targetYear=${targetYear}&targetMonth=${targetMonth}&hashtag=${map.word}">${map.word}(${map.cnt})</a>
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
										<div><a style="color: black;" href="${pageContext.request.contextPath}/on/dateOne?targetDate=${d}&targetMonth=${targetMonth}&targetYear=${targetYear}">${d}</a></div>
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
						</tr>			
					</table>
					</div>
                </main>
                <footer class="py-4 bg-light mt-auto">
                    <div class="container-fluid px-4">
                        <div class="d-flex align-items-center justify-content-between small">
                            <div class="text-muted">Copyright &copy; cashbook</div>
                            <div>
                                <a href="https://ko-kr.facebook.com/">
                                	<img width="30px;" height="30px;" src="${pageContext.request.contextPath}/icon/face.png">
                                </a>
                                &middot;
                                <a href="https://www.instagram.com/">
                                	<img width="30px;" height="30px;" src="${pageContext.request.contextPath}/icon/insta.png">
                                </a>
                            </div>
                        </div>
                    </div>
                </footer>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="${pageContext.request.contextPath}/css/js/scripts.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
        <script src="${pageContext.request.contextPath}/css/assets/demo/chart-area-demo.js"></script>
        <script src="<%=request.getContextPath()%>/css/assets/demo/chart-bar-demo.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
        <script src="${pageContext.request.contextPath}/css/js/datatables-simple-demo.js"></script>
    </body>
</html>


