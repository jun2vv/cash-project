<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Cashbook</title>
        <link href="${pageContext.request.contextPath}/css/css/styles.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
        
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
					
					if($('#addPw').val() != $('#addCheckPw').val()) {
						alert('비밀번호가 일치하지 않습니다.')
						return;
					}
					
					$('#addMemberForm').submit();
				});
			});
		</script>
    </head>
    <body class="bg-primary">
        <div id="layoutAuthentication">
            <div id="layoutAuthentication_content">
                <main>
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-lg-7">
                                <div class="card shadow-lg border-0 rounded-lg mt-5">
                                    <div class="card-header"><h3 class="text-center font-weight-light my-4">회원가입</h3></div>
                                    <div class="card-body">
                                        <form id="addMemberForm" method="post" action="${pageContext.request.contextPath}/off/addMember">
                                            <div class="row mb-3">
                                                <div class="col-md-12">
                                                    <div class="form-floating mb-3 mb-md-0">
                                                        <input class="form-control" id="addId" type="text" name="memberId">
                                                        <label for="inputFirstName">아이디</label>
														<span id="addIdMsg"></span>
                                                    </div>
                                                </div>
                                            </div>
                                            
                                            <!--  
                                            <div class="form-floating mb-3">
                                                <input class="form-control" id="inputEmail" type="email" placeholder="name@example.com" />
                                                <label for="inputEmail">Email address</label>
                                            </div>
                                            -->
                                            <div class="row mb-3">
                                                <div class="col-md-6">
                                                    <div class="form-floating">
                                                        <input class="form-control" id="addPw" type="password" name="memberPw">
                                                        <label for="inputLastName">비밀번호</label>
														<span id="addPwMsg"></span>
                                                    </div>
                                                </div>
                                                
                                                <div class="col-md-6">
                                                    <div class="form-floating">
                                                        <input class="form-control" id="addCheckPw" type="password" name="memberPw">
                                                        <label for="inputLastName">비밀번호 확인</label>
														<span id="addCheckPwMsg"></span>
                                                    </div>
                                                </div>
                                                
                                                <!--  
                                                <div class="col-md-6">
                                                    <div class="form-floating mb-3 mb-md-0">
                                                        <input class="form-control" id="inputPassword" type="password" placeholder="Create a password" />
                                                        <label for="inputPassword">Password</label>
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="form-floating mb-3 mb-md-0">
                                                        <input class="form-control" id="inputPasswordConfirm" type="password" placeholder="Confirm password" />
                                                        <label for="inputPasswordConfirm">Confirm Password</label>
                                                    </div>
                                                </div>
                                                -->
                                            </div>
                                            <div class="mt-4 mb-0">
                                                <div class="d-grid"><button class="btn btn-secondary btn-block" id="addMemberBtn" type="button">회원가입</button></div>
                                            </div>
                                        </form>
                                    </div>
                                    <div class="card-footer text-center py-3">
                                        <div class="small"><a href="${pageContext.request.contextPath}/off/login">계정이 있으신가요? 로그인으로 가세요!</a></div>
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
