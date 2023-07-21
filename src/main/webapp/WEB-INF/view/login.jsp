<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Login - SB Admin</title>
        <link href="${pageContext.request.contextPath}/css/css/styles.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
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
				<c:if test="${msg != null}">
					alert('${msg}')
				</c:if>
			});
		</script>
    </head>
    <body class="bg-primary">
        <div id="layoutAuthentication">
            <div id="layoutAuthentication_content">
                <main>
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-lg-5">
                                <div class="card shadow-lg border-0 rounded-lg mt-5">
                                    <div class="card-header"><h3 class="text-center font-weight-light my-4">Login</h3></div>
                                    <div class="card-body">
										
										<!--  el코드도 출력은된다
											<p>msg</p>
										-->
                                        <form id="loginForm" method="post" action="${pageContext.request.contextPath}/off/login">
                                            <div class="form-floating mb-3">
                                                <input class="form-control" id="loginId" type="text" name="memberId" value="${cookieId}">
                                                 <label for="inputEmail">id</label>
												<span id="loginIdMsg" class="msg"></span>
                                            </div>
                                            <div class="form-floating mb-3">
                                                <input class="form-control" id="loginPw" type="password" name="memberPw">
                                                <label for="inputPassword">Password</label>
												<span id="loginPwMsg" class="msg"></span>
                                            </div>
                                            <div class="form-check mb-3">
                                                <input class="form-check-input" type="checkbox" name="idSave" value="y">id저장
                                            </div>
                                            <div class="d-flex align-items-center justify-content-between mt-4 mb-0">
                                            	<button class="btn btn-primary" id="loginBtn" type="button">로그인</button>
                                            </div>
                                        </form>
                                    </div>
                                    <div class="card-footer text-center py-3">
                                        <div class="small"><a href="${pageContext.request.contextPath}/off/addMember">회원가입</a></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
            </div>
            <div id="layoutAuthentication_footer">
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
    </body>
</html>

