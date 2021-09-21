<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>       
<!doctype html>
<html lang="ko">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.83.1">
    <title>Yun's Apartment Infomation Site</title>

	  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="canonical" href="https://getbootstrap.com/docs/5.0/examples/sidebars/">
	  <link rel="stylesheet" href="../css/bootstrap-select.css">    
    <!-- Bootstrap core CSS -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="../css/sidebars.css" rel="stylesheet">
  </head>
  <body>
   <!---- 전체 컨테이너 -->
    <div class="container">
       <!-- 헤더 -->      
        <!-- 헤더 -->      
      <div class="row p-1">
        <header class="navbar navbar-dark p-1 shadow" style="background-color: #7952B3;">
          <div class="col col-md-2 p-1">
            <a class="navbar-brand me-0 px-3" href="../index">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-house-door" viewBox="0 0 16 16">
              <path d="M8.354 1.146a.5.5 0 0 0-.708 0l-6 6A.5.5 0 0 0 1.5 7.5v7a.5.5 0 0 0 .5.5h4.5a.5.5 0 0 0 .5-.5v-4h2v4a.5.5 0 0 0 .5.5H14a.5.5 0 0 0 .5-.5v-7a.5.5 0 0 0-.146-.354L13 5.793V2.5a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5v1.293L8.354 1.146zM2.5 14V7.707l5.5-5.5 5.5 5.5V14H10v-4a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0-.5.5v4H2.5z"/>
            </svg>
            <span>아파트 거래정보</span>
            </a>
          </div>
          <div class="col col-md-2 mt-2" >
            <nav aria-label="breadcrumb" style="--bs-breadcrumb-divider: '>'">
              <ol class="breadcrumb">
                <li class="breadcrumb-item active " style="color:yellow;">메인화면</li>
              </ol>
            </nav>         
          </div>
          <div class="col col-md-4 p-1">
           <form class="d-flex">
	           <input class="form-control form-control-dark " type="text" placeholder="전체검색" aria-label="Search">
						 <button class="btn btn-success" type="submit">Search</button>
					 </form>
          </div>  
          <!--      로그인 메뉴 -->
          <div class="btn-group col-md-2" >
            <button type="button" class="btn btn-light dropdown-toggle fs-5 fw-semibold" data-bs-toggle="dropdown" aria-expanded="false">
            Demitry
            </button>
            <ul class="dropdown-menu">
              <li><a class="dropdown-item" href="/error404">프로필</a></li>
              <li><a class="dropdown-item" href="/error404">로그인</a></li>
              <li><a class="dropdown-item" href="/error404">로그아웃</a></li>
            </ul>
          </div>
        </header>
      </div>

       <!-- 메인영역 -->    
      <div class="row">
        <!-- 사이드메뉴 -->            
        <div class="col-2 " style="background-color:rgba(93%, 95%, 98%,0.7)">
          <ul class="list-unstyled ps-0 pt-4">
            <li class="mb-1">
              <button class="btn btn-toggle align-items-center rounded collapsed" data-bs-toggle="collapse" data-bs-target="#alarm-collapse" aria-expanded="true">
                게시판
              </button>
              <div class="collapse show" id="alarm-collapse">
                <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
                  <li><a href="notice/notice" class="link-dark rounded">공지사항</a></li>
                </ul>
              </div>
            </li>
            <li class="mb-1">
              <button class="btn btn-toggle align-items-center rounded collapsed" data-bs-toggle="collapse" data-bs-target="#aptinfo-collapse" aria-expanded="true">
                아파트 정보
              </button>
              <div class="collapse show" id="aptinfo-collapse">
                <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
                  <li><a href="/error404" class="link-dark rounded">서울지역</a></li>
                  <li><a href="/error404" class="link-dark rounded">경기지역</a></li>
                  <li><a href="/error404" class="link-dark rounded">기타지역</a></li>
                </ul>
              </div>
            </li>
            <li class="mb-1">
              <button class="btn btn-toggle align-items-center rounded collapsed" data-bs-toggle="collapse" data-bs-target="#realtrade-collapse" aria-expanded="false">
                아파트 실거래가
              </button>
              <div class="collapse show" id="realtrade-collapse">
                <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
                  <li><a href="../realprice/realprice" class="link-dark rounded">매매</a></li>
                  <li><a href="/error404" class="link-dark rounded">전월세</a></li>
                </ul>
              </div>
            </li>
            <li class="mb-1">
              <button class="btn btn-toggle align-items-center rounded collapsed" data-bs-toggle="collapse" data-bs-target="#tradestatus-collapse" aria-expanded="false">
                거래현황
              </button>
              <div class="collapse show" id="tradestatus-collapse">
                <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
                  <li><a href="/error404" class="link-dark rounded">지역별 현황</a></li>
                  <li><a href="/error404" class="link-dark rounded">일자별 현황</a></li>
                  <li><a href="/error404" class="link-dark rounded">증감 현황</a></li>
                </ul>
              </div>
            </li>
            <li class="mb-1">
              <button class="btn btn-toggle align-items-center rounded collapsed" data-bs-toggle="collapse" data-bs-target="#pricestatus-collapse" aria-expanded="false">
                가격현황
              </button>
              <div class="collapse show" id="pricestatus-collapse">
                <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
                  <li><a href="/error404" class="link-dark rounded">신고가 현황</a></li>
                  <li><a href="/error404" class="link-dark rounded">상승/하락 현황</a></li>
                </ul>
              </div>
            </li>            
            <li class="border-top my-3"></li>
            <li class="mb-1">
              <button class="btn btn-toggle align-items-center rounded collapsed" data-bs-toggle="collapse" data-bs-target="#manage-collapse" aria-expanded="false">
                관리자
              </button>
              <div class="collapse" id="manage-collapse">
                <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
                  <li><a href="/error404" class="link-dark rounded">공지사항 관리</a></li>
                  <li><a href="/error404" class="link-dark rounded">사용자 관리</a></li>
                </ul>
              </div>
            </li>
          </ul>
        </div>


       <!-- 메인화면 메인 -->    
        <div class="col-10 mt-3">

				    <div class="row align-items-md-stretch">
				      <div class="col-md-6">
				        <div class="h-100 p-5 bg-light border rounded-3">
				          <h2>공지사항</h2>
				          <p>1.아파트 거래정보 사이트가 오픈되었습니다.</p>
				          <p>2.회원가입 절차 안내</p>
				          <p>3.사이트 이용방법 안내</p>
				          <button class="btn btn-outline-secondary" type="button">확인하러 가기</button>
				        </div>
				      </div>
				      <div class="col-md-6">
				        <div class="h-100 p-5 bg-light border rounded-3">
				          <h2>Add borders</h2>
				          <p>Or, keep it light and add a border for some added definition to the boundaries of your content. Be sure to look under the hood at the source HTML here as we've adjusted the alignment and sizing of both column's content for equal-height.</p>
				          <button class="btn btn-outline-secondary" type="button">Example button</button>
				        </div>
				      </div>
				    </div>
 				    <div class="p-5 mb-4 bg-light rounded-3">
				      <div class="container-fluid py-2">
				        <h1 class="display-5 fw-bold">Custom jumbotron</h1>
				        <p class="col-md-8 fs-4">Using a series of utilities, you can create this jumbotron, just like the one in previous versions of Bootstrap. Check out the examples below for how you can remix and restyle it to your liking.</p>
				        <button class="btn btn-primary btn-lg" type="button">Example button</button>
				      </div>
				    </div>      
        </div>
        
        <!--  footer-->
        <div class="container col-12 pb-6">
          <footer class="sticky-footer bg-white">
            <div class="container my-auto">
                <div class="copyright text-center my-auto">
                    <h6><span>Copyright sangyoun kim, 2021</span></h6>
                </div>
            </div>
          </footer>
        </div>        
      </div>
    </div>
  </body>
<!-- bootstrap core js -->
  <script src="../js/bootstrap.bundle.min.js"></script>
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.bundle.min.js"></script>  
	<script src="../js/bootstrap-select.js"></script>    
<!-- 커스텀 js -->
  <script src="../js/yunsapt.js"></script>
</html>
