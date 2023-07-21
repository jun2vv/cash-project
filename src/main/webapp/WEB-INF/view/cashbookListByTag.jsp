<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html lang="en">
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
	<!-- Latest compiled and minified CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
	<!-- Latest compiled JavaScript -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<style>
	body {
		font-family: Arial, sans-serif;
	}

	h1 {
		text-align: center;
		margin-top: 20px;
	}

	table {
		width: 100%;
		border-collapse: collapse;
		margin-top: 20px;
	}

	th, td {
		padding: 10px;
		text-align: center;
		border: 1px solid #ccc;
	}

	thead {
		background-color: #f2f2f2;
	}

	a {
		text-decoration: none;
		color: #007bff;
	}

	a:hover {
		text-decoration: underline;
	}

	.back-link {
		display: block;
		text-align: center;
		margin-top: 20px;
	}
	
	/* 페이징 버튼 기본 스타일 */
	.paging-button {
	    display: inline-block;
	    padding: 8px 16px;
	    margin: 5px;
	    font-size: 16px;
	    border: 2px solid black;
	    color: black;
	    text-decoration: none;
	    border-radius: 8px;
	}
	
	/* 현재 페이지 스타일 */
	.current-page {
	    background-color: black;
	    color: white;
	}
	
	/* a 태그 스타일 제거 (링크의 기본 스타일 제거) */
	a {
		text-decoration: none;
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
                	<span style="color: white;">${loginMember}님 ${targetYear}년 ${targetMonth+1}월 목록</span>
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
                            
                            <a class="nav-link collapsed" href="${pageContext.request.contextPath}/on/calendar">
                            	<div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                           	 	달력으로
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
                        <div class="small">내일주말</div>
                    </div>
                </nav>
            </div>
            <div id="layoutSidenav_content">
                <main>
                	<br>
                	<br>
                   	<h1>${hashtag}(태그) 목록</h1>
					<table>
						<thead>
							<tr>
								<th>번호</th>
								<th>날짜</th>
								<th>카테고리</th>
								<th>금액</th>
								<th>해시태그</th>
								<th>수정일</th>
								<th>생성일</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="list" items="${list}" >
								<tr>
									<td>
										<a href="${pageContext.request.contextPath}/on/dateOne?cashbookDateByTag=${list.cashbookDate}">
											${list.cashbookNo}
										</a>
									</td>
									<td>${list.cashbookDate}</td>
									<td>${list.category}</td>
									<td>${list.price}</td>
									<td><span style="color: #3f729b;">${list.memo}</span></td>
									<td>${list.updatedate}</td>
									<td>${list.createdate}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div>
						<!-- 최소페이지가 1보다크면 이전페이지(이전페이지는 만약 내가 11페이지면 1페이지로 21페이지면 11페이지로)버튼-->
						<c:if test="${minPage > 1}">
							<a href="${pageContext.request.contextPath}/on/cashbookListByTag?currentPage=${minPage-pagePerPage}&hashtag=${hashtag}">
								<span class="paging-button">이전</span>
							</a>
						</c:if>
						<!-- 최소 페이지부터 최대 페이지까지 표시 -->
						<c:forEach var="i" begin="${minPage}" end="${maxPage}" step="1">
							<!-- 현재페이지는 링크 비활성화 -->
							<c:if test="${i == currentPage}"> 
								<!-- i와 현재페이지가 같은곳이라면 현재위치한 페이지 빨간색표시 -->
								<span class="paging-button current-page">${i}</span>
							</c:if>
							<!-- i가 현재페이지와 다르다면 출력 -->
							<c:if test="${i != currentPage}">
								<a href="${pageContext.request.contextPath}/on/cashbookListByTag?currentPage=${i}&hashtag=${hashtag}">
									<span class="paging-button">${i}</span>
								</a>
							</c:if>
						</c:forEach>
						<!-- maxPage가 마지막페이지와 다르다면 다음버튼 마지막페이지에서는 둘이 같으니 다음버튼이 안나오겠죠
							 다음페이지(만약 내가 1페이지에서 누르면 11페이지로 11페이지에서 누르면 21페이지로)버튼 -->
						<c:if test="${maxPage != lastPage}">
							<a href="${pageContext.request.contextPath}/on/cashbookListByTag?currentPage=${ minPage+pagePerPage}&hashtag=${hashtag}">
								<span class="paging-button">다음</span>
							</a>
						</c:if>
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