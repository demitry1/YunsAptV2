<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- ----------------------------------------------------------------------------- -->
<!--  사이드 메뉴 -->	
<!-- ----------------------------------------------------------------------------- -->
		<nav id="sidebar" class="sidebar">
			<div class="sidebar-content js-simplebar" id="ksy-sidebar2">
				<a class="sidebar-brand" href="../index">	
          <i class="fas fa-building"></i>
          <span class="align-middle"  id="ksy-sidebar">아파트 거래정보</span>
        </a>

				<ul class="sidebar-nav " >
					<li class="sidebar-header">
						Board
					</li>				
					<li class="sidebar-item" >
						<a data-target="#board" data-toggle="collapse" class="sidebar-link collapsed" id="ksy-sidebar">
              <i class="fas fa-clipboard fa-lg" ></i> 
              <span class="align-middle text-white">게시판</span>
            </a>
						<ul id="board" class="list-unstyled collapse " data-parent="#sidebar">
							<li class="sidebar-item pl-3"><a class="sidebar-link" href="../notice/notice" id="ksy-sidebar">
							<i class="fas fa-arrow-right"></i>공지사항</a></li>
						</ul>
					</li>

					<li class="sidebar-header">
						Main
					</li>			
					<li class="sidebar-item">
						<a data-target="#apt-info" data-toggle="collapse" class="sidebar-link collapsed" id="ksy-sidebar">
              <i class="fas fa-home fa-lg"></i> 
              <span class="align-middle text-white">아파트정보</span>
            </a>
						<ul id="apt-info" class="list-unstyled collapse " data-parent="#sidebar">
							<li class="sidebar-item pl-3"><a class="sidebar-link" href="../aptinfo/aptinfo" id="ksy-sidebar">
							<i class="fas fa-arrow-right"></i>서울지역</a></li>
						</ul>
					</li>
					<li class="sidebar-item">
						<a data-target="#real-price" data-toggle="collapse" class="sidebar-link collapsed" id="ksy-sidebar">
              <i class="fas fa-won-sign fa-lg"></i> 
              <span class="align-middle text-white">실거래가</span>
            </a>
						<ul id="real-price" class="list-unstyled collapse" data-parent="#sidebar">
							<li class="sidebar-item pl-3"><a class="sidebar-link" href="../realprice/realprice" id="ksy-sidebar">
							<i class="fas fa-arrow-right"></i>매매</a></li>
							<li class="sidebar-item pl-3"><a class="sidebar-link" href="../realprice/realpricerent" id="ksy-sidebar">
							<i class="fas fa-arrow-right"></i>전/월세</a></li>
						</ul>
					</li>
					<li class="sidebar-item">
						<a data-target="#trade-status" data-toggle="collapse" class="sidebar-link collapsed" id="ksy-sidebar">
              <i class="fas fa-people-arrows fa-lg"></i> 
              <span class="align-middle text-white">거래현황</span>
            </a>
						<ul id="trade-status" class="list-unstyled collapse " data-parent="#sidebar">
							<li class="sidebar-item pl-3"><a class="sidebar-link side_ilja" href="../trade/tradestatus" id="ksy-sidebar">
							<i class="fas fa-arrow-right"></i>일자별</a></li>
							<li class="sidebar-item pl-3"><a class="sidebar-link" href="../trade/tradearea" id="ksy-sidebar">
							<i class="fas fa-arrow-right"></i>지역별</a></li>
						</ul>
					</li>	
					<li class="sidebar-item">
						<a data-target="#price-status" data-toggle="collapse" class="sidebar-link collapsed" id="ksy-sidebar">
              <i class="fas fa-dollar-sign fa-lg"></i> 
              <span class="align-middle ml-2 text-white">가격현황</span>
            </a>
						<ul id="price-status" class="list-unstyled collapse " data-parent="#sidebar">
<!-- 							<li class="sidebar-item pl-3"><a class="sidebar-link" href="/error404" id="ksy-sidebar">
							<i class="fas fa-arrow-right"></i>상승/하락</a></li> -->
							<li class="sidebar-item pl-3"><a class="sidebar-link" href="../pricestats/highprice" id="ksy-sidebar">
							<i class="fas fa-arrow-right"></i>신고가 현황</a></li>
							<li class="sidebar-item pl-3"><a class="sidebar-link" href="/error404" id="ksy-sidebar">
							<i class="fas fa-arrow-right"></i>매매가 순위</a></li>
						</ul>
					</li>	
					<li class="sidebar-header">
						Admin
					</li>										
					<li class="sidebar-item">
						<a data-target="#admin" data-toggle="collapse" class="sidebar-link collapsed" id="ksy-sidebar">
              <i class="fas fa-user-cog fa-lg"></i> 
              <span class="align-middle text-white">관리자</span>
            </a>
						<ul id="admin" class="list-unstyled collapse " data-parent="#sidebar">
							<li class="sidebar-item pl-3"><a class="sidebar-link" href="/error404" id="ksy-sidebar">
							<i class="fas fa-arrow-right"></i>공지사항 관리</a></li>
							<li class="sidebar-item pl-3"><a class="sidebar-link" href="/error404" id="ksy-sidebar">
							<i class="fas fa-arrow-right"></i>사용자 관리</a></li>
						</ul>
					</li>	
				</ul>
				
				<div class="sidebar-cta">
					<div class="sidebar-cta-content" style="background: #262B40;">
					  <div class="row">
						  <div class="col">
								<p>
								  <a class="btn btn-success" style="margin-left:-10px;" data-bs-toggle="collapse" href="#siteCollapse" role="button" aria-expanded="false" aria-controls="siteCollapse"  id="ksy">관련사이트</a>
								</p>
							</div>
						</div>
						<div class="row" style="margin-top: -10px">
						  <div class="col ">
						    <div class="collapse multi-collapse" id="siteCollapse">
						      <div class="card card-body " id="ksy-sidebar">
					          <p class="card-text" style="margin-left: -20px;">
					          	<i class="fas fa-star"></i>
					          	<a href="https://hogangnono.com" target='_blank'><span class="text-secondary">호갱노노</span></a><br>
					          	<i class="fas fa-star"></i>
					          	<a href="https://land.naver.com" target='_blank'><span class="text-secondary">네이버부동산</span></a><br>
					          	<i class="fas fa-star"></i>
					          	<a href="http://rtdown.molit.go.kr" target='_blank'><span class="text-secondary">국토부실거래</span></a><br>
					          	<i class="fas fa-star"></i>
					          	<a href="https://www.zigbang.com" target='_blank'><span class="text-secondary">직방</span></a><br>
					          	<i class="fas fa-star"></i>
					          	<a href="https://asil.kr" target='_blank'><span class="text-secondary">아실</span></a><br>
					          	<i class="fas fa-star"></i>
					          	<a href="https://map.naver.com/v5/subway" target='_blank'><span class="text-secondary">지하철노선도</span></a><br>
					          	<i class="fas fa-star"></i>
					          	<a href="https://map.naver.com" id="yjw" target='_blank'><span class="text-secondary">네이버지도</span></a>
					          </p>	
						      </div>
						    </div>
						  </div>
						</div>			        
					</div>
				</div>
			</div>
		</nav>