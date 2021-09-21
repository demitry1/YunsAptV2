<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>     
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="Responsive Admin &amp; Dashboard Template based on Bootstrap 5">
	<meta name="author" content="AdminKit">
	<meta name="keywords" content="adminkit, bootstrap, bootstrap 5, admin, dashboard, template, responsive, css, sass, html, theme, front-end, ui kit, web">

	<link rel="shortcut icon" href="img/icons/icon-48x48.png" />


	<title>Yun's 아파트 거래정보</title>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">		
  <link href="../css/app.css" rel="stylesheet">
  <link href="../css/simple-line-icons.css" rel="stylesheet">
  
</head>
<body> 
	<div class="wrapper">
<!-- ----------------------------------------------------------------------------- -->
<!--  사이드 메뉴 -->	
<!-- ----------------------------------------------------------------------------- -->
<!--  사이드 메뉴는 sidebar/sidebarmenufile.jsp에서 가져온다 -->	
    <c:import url="/sidebar/sidebarmenufile.jsp"/>
<!-- ----------------------------------------------------------------------------- -->
<!--  메인 화면 -->	
<!-- ----------------------------------------------------------------------------- -->
		<div class="main">
<!--  top 영역 -->		
<!--  --------------------------------------------------------------------------- -->		
			<nav class="navbar navbar-expand navbar-light navbar-bg">
				<a class="sidebar-toggle d-flex">
          <i class="hamburger align-self-center"></i>
        </a>

				<form class="d-none d-sm-inline-block">
					<div class="input-group input-group-navbar">
						<input type="text" class="form-control" placeholder="Search all…" aria-label="Search">
						<button class="btn" type="button">
              <i class="align-middle" data-feather="search"></i>
            </button>
					</div>
				</form>
				
				<div class="col-auto ml-auto text-right mt-n1 pl-3 d-none d-sm-inline-block">
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb bg-transparent p-0 mt-1 mb-0">
							<li class="breadcrumb-item"><a href="../index">홈</a></li>
							<li class="breadcrumb-item text-danger">메인화면</li>
						</ol>
					</nav>
				</div>
				
				<div class="navbar-collapse collapse">
					<ul class="navbar-nav navbar-align">
						<li class="nav-item dropdown">
							<a class="nav-icon dropdown-toggle d-inline-block d-sm-none" href="#" data-toggle="dropdown">
                <i class="align-middle" data-feather="user"></i>
              </a>
							<a class="nav-link dropdown-toggle d-none d-sm-inline-block" href="#" data-toggle="dropdown">
                <i class="fas fa-user fa-lg"></i>  
                <span class="text-dark">${(userid != null)?userid:'로그인'}</span>
              </a>
							<div class="dropdown-menu dropdown-menu-right">
							  <c:if test="${userid == null }">
									<a class="dropdown-item" href="../login/login?cmd=login">
										<i class="align-middle mr-1" data-feather="user"></i>로그인</a>
								</c:if>
								<div class="dropdown-divider"></div>
								<c:if test="${userid != null }">
									<a class="dropdown-item" href="../login/profile?cmd=display">
										<i class="align-middle mr-1" data-feather="settings"></i>프로필</a>
									<div class="dropdown-divider"></div>
									<a class="dropdown-item" href="../login/login?cmd=logout">
										<i class="align-middle mr-1" data-feather="user"></i>로그아웃</a>
								</c:if>	
							</div>
					
						</li>
					</ul>
				</div>
			</nav>
<!-- ---------------------------------------------------------------------------------------- -->			
<!--  메인 영역 -->
<!-- ---------------------------------------------------------------------------------------- -->			
			<main class="content">
				<div class="container">	
				 <div class="row justify-content-center">
          <div class="col-md-6 d-flex">
            <div class="my-3 my-lg-0 bg-white border rounded border-light p-4 p-lg-5 w-100">
	             <div class="text-center text-md-center mb-4 mt-md-0">
	                 <h1 class="mb-0 h3">로그인</h1>
	             </div>
	             <form id="loginform" action="../login/logincheck" method="post">
	                <!-- Form -->
	                <div class="form-group mb-4">
	                    <label for="user">사용자</label>
	                    <div class="input-group">
	                        <span class="input-group-text" id="basic-addon1"><span class="fas fa-user"></span></span>
	                        <input type="text" class="form-control" placeholder="userid" id="userid" name="userid" autofocus style="line-height: 2">
	                    </div>  
	                </div>
	                <!-- End of Form -->
	                <div class="form-group">
	                    <!-- Form -->
	                    <div class="form-group mb-4">
	                        <label for="password">패스워드</label>
	                        <div class="input-group">
	                            <span class="input-group-text" id="basic-addon2"><span class="fas fa-unlock-alt"></span></span>
	                            <input type="password" placeholder="Password" name="password" class="form-control" id="password" style="line-height: 2">
	                        </div>  
	                    </div>
	                    <!-- End of Form -->
	                </div>
	                <button type="submit" class="btn btn-block btn-primary" name="cmd" value="login">Sign in</button>
	                <button type="submit" class="btn btn-block btn-danger" name="cmd" value="forget">Forget Password</button>
	                <button type="submit" class="btn btn-block btn-info" name="cmd" value="join">Join</button>
	             </form>
            </div>
          </div>
          </div>
   			</div>
			</main>
<!--------------------------------------------------------- -->			
<!--  footer -->
<!--------------------------------------------------------- -->			
			<footer class="footer">
				<div class="container-fluid">
					<div class="row text-muted">
						<div class="col-12 text-center">
							<p class="mb-0">
								<a href="index.html" class="text-muted"><strong>Copyright Sangyoun Kim</strong></a> &copy;
							</p>
						</div>
					</div>
				</div>
			</footer>
		</div>
	</div>
<!-- bootstrap core js -->
  <script src="../js/bootstrap.bundle.min.js"></script>
	<script src="../js/app.js"></script>
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>		
<!-- 커스텀 js -->
  <script src="../js/yunsapt.js"></script>

</body>

</html>