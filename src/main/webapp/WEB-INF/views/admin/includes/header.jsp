<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin 2 - Bootstrap Admin Theme</title>
    <link rel="stylesheet" href="/resources/css/admin.css" />

    <!-- Bootstrap Core CSS -->
    <link href="/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="/resources/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- DataTables CSS -->
    <link href="/resources/vendor/datatables-plugins/dataTables.bootstrap.css" rel="stylesheet">

    <!-- DataTables Responsive CSS -->
    <link href="/resources/vendor/datatables-responsive/dataTables.responsive.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="/resources/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>
	 <!--header.jsp-->
    <div class="admin-wrapper">
    <div class="admin-top">
        <div class="admin-logout">
            <ul>
            <sec:authorize access="hasRole('ADMIN')">
                <li>현재 관리자모드에 접속중입니다. </li>
                <li><a href="../customLogout">로그아웃</a></li>
                </sec:authorize>
                <li><a href="/">홈으로</a></li>
            </ul>
        </div>
    </div>
    <div class="admin-left">
		<h1 class="admin-logo"><a href="/admin/index">ADMIN PAGE</a></h1>
        <nav class="admin-nav">
            <ul>
                <li><a href="#">관리자 정보</a></li>
                <li><a href="/admin/member/list">회원관리</a></li>
                <!-- <li><a href="#">예약관리</a></li> -->
                <li><a href="/admin/notice/list">공지사항</a></li>
                <li><a href="#">게시물 관리</a></li>
            </ul>
        </nav>
	</div>
	</div>
    <!--header.jsp-->
        
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>