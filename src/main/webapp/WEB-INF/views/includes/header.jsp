<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>JAFA PhotoGraphy Traveller</title>

    <!-- Bootstrap Core CSS -->
    <link href="/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    
    <!-- MetisMenu CSS -->
    <link href="/resources/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- DataTables CSS -->
    <link href="/resources/vendor/datatables-plugins/dataTables.bootstrap.css" rel="stylesheet">

    <!-- DataTables Responsive CSS -->
    <link href="/resources/vendor/datatables-responsive/dataTables.responsive.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="/resources/dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="/resources/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    
    <link rel="stylesheet" href="/resources/css/common.css" />
    <link rel="stylesheet" href="/resources/css/responsive.css" />
    
    <!-- bxslider -->
    <link rel="stylesheet" href="/resources/css/jquery.bxslider.css" />
    
    <!-- bxslider js -->
    <script src="/resources/js/jquery.bxslider.js"></script>
    <script src="/resources/js/jquery.bxslider.min.js"></script>
    
    <!-- ckeditor -->
    <script src="/resources/ckeditor/build/ckeditor.js"></script>
    <script src="/resources/ckeditor/translations/ko.js"></script>
    

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>
<!--login_modal-->
<!--<p class="modal-show">login</p>-->
<%--<div id="login_modal">
	<div class="login-area">
		<div class="modal-top">
			<p class="close_button">
			<svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-x-square" viewBox="0 0 16 16">
  <path d="M14 1a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H2a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h12zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z"/>
  <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
</svg><!-- X --></p>
		</div>
		<div class="modal-bottom">
			<%@include file="../customLogin.jsp" %>
		</div>
	</div>
</div>
<script>
	$(document).ready(function(){
		$('.modal-show-button').click(function(){
			$('#login_modal').show();
		});
		$('.close_button').click(function(){
			$('#login_modal').hide();
		});
	});
</script>--%>
<!--login_modal-->
<header id="header" class="wrap">
	<div id="top_menu" class="wrap">
		<div class="container">
			<div class="login">
				<ul>
					<sec:authorize access="hasRole('ADMIN')">
                       	<li><a href="<c:url value='/admin/index' />"><i class="fa fa-sign-out fa-fw"></i>관리</a></li>
                    </sec:authorize>
					<sec:authorize access="isAuthenticated()">
                       	<%-- <li><c:out value="${member.userId}" />님 환영합니다. </li>--%>
                       	<li><a href="/customLogout"><i class="fa fa-sign-out fa-fw"></i>로그아웃</a></li>
                       	<%--<li><a href="/customModify?userId=${member.getUserId()}"><i class="fa fa-sign-out fa-fw"></i>정보수정</a></li> --%>
                       	<!-- <li><a href="/customModify"><i class="fa fa-sign-out fa-fw"></i>정보수정</a></li> -->
                    </sec:authorize>
                    <sec:authorize access="isAnonymous()">
                       	<li><a href="/customLogin"><i class="fa fa-sign-in fa-fw"></i>로그인</a></li>
                      	<!-- <li><a href="/customRegister"><i class="fa fa-sign-in fa-fw"></i>회원가입</a></li>
                      	<li class="modal-show-button"><i class="fa fa-sign-in fa-fw"></i>로그인</li> -->
                    </sec:authorize>
				</ul>
			</div>
		</div>
	</div>
	<div class="container">
		<h1 class="logo">
			<a href="/">JAFA Photo Traveller</a>
		</h1>
		<nav id="gnb">
			<ul>
				<li><a href="/notice/list">공지사항</a></li>
				<li><a href="/travell/list">추천 사진여행지</a></li>
				<li><a href="/gallery/list">갤러리</a></li>
				<%--<li>
					<a href="#">여행 후기</a>
				</li> --%>
				<li>
					<a href="/board/list">게시판</a>
				</li>
			</ul>
		</nav>
		<div id="m-menu-btn">
			<svg xmlns="http://www.w3.org/2000/svg" width="50" height="50" fill="currentColor" class="bi bi-list" viewBox="0 0 16 16">
  				<path fill-rule="evenodd" d="M2.5 12a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5z"/>
			</svg>
		</div>
	</div>
</header>
<div id="hamberger_menu_wrapper">
	<div class="rgba_area"></div>
</div>
<div class="hamberger_menu">
	<div class="hb-top">
		<ul class="mobile-login">
			<sec:authorize access="hasRole('ADMIN')">
				<li><a href="<c:url value='/admin' />"><i class="fa fa-sign-out fa-fw"></i>관리</a></li>
			</sec:authorize>
			<sec:authorize access="isAnonymous()">
				<li><a href="/customLogin"><i class="fa fa-sign-out fa-fw"></i>로그인</a></li>
				<li><a href="/customRegister"><i class="fa fa-sign-out fa-fw"></i>회원가입</a></li>
			</sec:authorize>
			<sec:authorize access="isAuthenticated()">
				<li><a href="/customLogout"><i class="fa fa-sign-out fa-fw"></i>로그아웃</a></li>
				<!-- <li><a href="/customModify"><i class="fa fa-sign-out fa-fw"></i>정보수정</a></li> -->
			</sec:authorize>
		</ul>
	</div>
	<div class="accodian">
		<ul id="ac">
			<li class="menu1"><a href="/notice/list">공지사항</a></li>
			<li class="menu1"><a href="/travell/list">추천 사진 여행지</a>
				<!-- <ul class="m-menu">
					<li><a href="#">계절별</a></li>
					<li><a href="#">지역별</a></li>
					<li><a href="#">여행코스</a></li>
				</ul>-->
			</li>
			<li class="menu1"><a href="/gallery/list">갤러리</a></li>
			<!-- <li class="menu1"><a href="#">여행후기</a></li> -->
			<li class="menu1"><a href="/board/list">게시판</a></li>
		</ul>
	</div>
	<script>
        $('.menu1').click(function(){
         $('.m-menu').slideUp();
         if ($(this).children('.m-menu').is(':hidden')){
            $(this).children('.m-menu').slideDown();
         } else{
            $(this).children('.m-menu').slideUp();
         }
      });
     </script>
	</div>
	<script>
	$(function(){
		$("#m-menu-btn").click(function(){
			$("#hamberger_menu_wrapper").show();
			$(".hamberger_menu").stop().animate({left: '0'});
		});
		$(".rgba_area").click(function(){
			$("#hamberger_menu_wrapper").hide();
			$(".hamberger_menu").stop().animate({left: '-100%'});
		});
	});
</script>