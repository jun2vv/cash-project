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
				
		        // 입력값을 공백으로 구분하여 각 단어를 분리한 후, #을 추가하여 다시 조합
		        var words = $('#memoId').val().split(" ");
		        // 반복문을 통하여 words배열에 각인덱스값마다 앞에#이 없다면 #을 추가하여 저장
		        for (var i = 0; i < words.length; i++) {
		            if (!words[i].startsWith("#")) {
		                words[i] = "#" + words[i];
		            }
		        }
		        // words배열을 공백을 기준으로 memoId에 다시 문자열로 나열하여 넣음
		        $('#memoId').val(words.join(' '));
				
				$('#addCashbookForm').submit();
			});
		});
	</script>
	<style>
    .container {
      max-width: 400px;
      margin: 0 auto;
      padding: 20px;
      border: 1px solid #ccc;
      border-radius: 5px;
    }

    h1 {
      font-size: 24px;
      margin-bottom: 20px;
      text-align: center;
    }

    table {
      width: 100%;
      border-collapse: collapse;
    }

    td {
      padding: 8px;
    }

    select,
    input {
      width: 100%;
      padding: 8px;
      border: 1px solid #ccc;
      border-radius: 5px;
      outline: none;
    }

    #categoryIdMsg,
    #priceIdMsg,
    #memoIdMsg {
      color: red;
    }

    button:hover {
      background-color: #0056b3;
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
                	<span style="color: white;">${loginMember}님 ${targetYear}년 ${targetMonth+1}월 ${targetDate}일 추가</span>
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
                            <a class="nav-link collapsed" href="${pageContext.request.contextPath}/on/dateOne?targetYear=${targetYear}&targetMonth=${targetMonth}&targetDate=${targetDate}">
                            	<div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                           	 	목록으로
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
                	<div class="container">
                   	<h3>날짜${targetYear}년${targetMonth+1}월${targetDate}일</h3>
					<form id="addCashbookForm" action="${pageContext.request.contextPath}/on/addCashbook" method="post">
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
									<input id="memoId" type="text" name="memo" placeholder="각단어를 띄어쓰기로 구분해주세요">
									<span id="memoIdMsg"></span>
								</td>
							</tr>
						</table>
						<button style="text-align: center;" class="btn btn-dark" id="addCashbookBtn" type="button">추가하기</button>
					</form>
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