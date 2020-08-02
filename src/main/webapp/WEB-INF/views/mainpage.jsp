<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
 <!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="utf-8">
    <meta name="description" content="네이버 예약, 네이버 예약이 연동된 곳 어디서나 바로 예약하고, 네이버 예약 홈(나의예약)에서 모두 관리할 수 있습니다.">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no">
    <title>네이버 예약</title>
    <link href="./css/style.css?ver=3" rel="stylesheet">
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
</head>

<body>
    <div id="container">
        <div class="header">
            <header class="header_tit">
                <h1 class="logo">
                    <a href="https://m.naver.com/" class="lnk_logo" title="네이버"> <span class="spr_bi ico_n_logo">네이버</span> </a>
                    <a href="./myreservation" class="lnk_logo" title="예약"> <span class="spr_bi ico_bk_logo">예약</span> </a>
                </h1>
                <c:if test = "${sessionScope.reservationEmail == null}"><a href="/reservation/bookinglogin" class="btn_my"> <span class="viewReservation" title="예약확인">예약확인</span> </a></c:if>
                <c:if test = "${sessionScope.reservationEmail != null}"><a href="/reservation/myreservation" class="btn_my"> 
                	<span class="viewReservation" title="이메일정보">${sessionScope.reservationEmail}</span> </a></c:if>
            </header>
        </div>
        <hr>
      <div class="event">
            <div class="section_visual">
                <div class="group_visual">
                    <div class="container_visual">
                        <div class="prev_e" style="display:none;">
                            <div class="prev_inn">
                                <a href="#" class="btn_pre_e" title="이전"> <i class="spr_book_event spr_event_pre">이전</i> </a>
                            </div>
                        </div>
                        <div class="nxt_e" style="display:none;">
                            <div class="nxt_inn">
                                <a href="#" class="btn_nxt_e" title="다음"> <i class="spr_book_event spr_event_nxt">다음</i> </a>
                            </div>
                        </div>
                        <div>
                            <div class="container_visual" >
                                <!-- [D] 이전,다음 버튼을 클릭할때마다 캐러셀 형태로 순환 됨 -->
                                <ul class="visual_img" style=" transform: ; transition-duration : ; width: max-content"> 
                                </ul>
                            </div>
                            <span class="nxt_fix" style="display:none;"></span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="section_event_tab">
                <ul id="event_tab" class="event_tab_lst tab_lst_min">
                 <li class="item" data-category="0">
                        <a class="anchor active"> <span>전체보기</span> </a>
                 </li>
                </ul>
            </div>
            <div class="section_event_lst">
                <p class="event_lst_txt">바로 예매 가능한 행사가 <span class="pink"></span> 있습니다</p>
                <div class="wrap_event_box">
                    <!-- [D] lst_event_box 가 2컬럼으로 좌우로 나뉨, 더보기를 클릭할때마다 좌우 ul에 li가 추가됨 -->
                    <ul class="lst_event_box">
                     <div class = "displayProductList_left"></div>
                    </ul>
                   <ul class="lst_event_box">
                     <div class = "displayProductList_right"></div>
                    </ul>
          
                    <!-- 더보기 -->
                    <div class="more"><button class="btn" id="moreBtn" style="display:"><span>더보기</span></button></div>
                </div>
            </div>
        </div>
    </div>
    <footer>
        <div class="gototop">
            <a href="#" class="lnk_top"> <span class="lnk_top_text">TOP</span> </a>
        </div>
        <div class="footer">
            <p class="dsc_footer">네이버(주)는 통신판매의 당사자가 아니며, 상품의정보, 거래조건, 이용 및 환불 등과 관련한 의무와 책임은 각 회원에게 있습니다.</p>
            <span class="copyright">© NAVER Corp.</span>
        </div>
    </footer>

<!-- 템플릿 모음 -->

	<script type = "rv-template" id ="categoryItem">
       <li class="item" data-category="{categoryId}">
          <a class="anchor"><span>{categoryName}</span> </a>
       </li>
    </script>
   
    <script type="rv-template" id="productItem">
        <li class="item">
            <a href="products/{displayInfoId}" class="item_book" id="{displayInfoId}">
                <div class="item_preview">
                    <img alt="{productDescription}" class="img_thumb" src="./img/{productImageUrl}">
                    <span class="img_border"></span>
                </div>
                <div class="event_txt">
                    <h4 class="event_txt_tit"> <span>{productDescription}</span> <small class="sm">{placeName}</small> </h4>
                    <p class="event_txt_dsc">{productContent}</p>
                </div>
            </a>
        </li>
    </script>
    <!-- 템플릿모음 -->
    <script type="rv-template" id="promotionItem">
        <li class="item event" id= "{sequence}" style="float:left; list-style:none; width: 414px;" >
           <div class="item event" style="background-image: url(./img/{productImageUrl}); width: 414px;"></div>                             
	    </li>
    </script>
    
     <!-- 스크립트 모음 -->
    <script src="./js/templatingProducts.js?ver=6"></script>
    <script src="./js/templatingPromotions.js?ver=2"></script>

</body>
 
</html>
 