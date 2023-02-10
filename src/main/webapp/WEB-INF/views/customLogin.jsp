<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="includes/header.jsp" %>
<link rel="stylesheet" href="/resources/css/security.css" />
<!-- subpage banner -->
<div class="subpage_banner wrap z-index relative">
	<div class="container">
		<h1 class="page-header">로그인</h1>
	</div>
</div>
<div class="wrap wrap1">
	<div class="login-layout">
		<div class="row">
			<div class="col-md4 col-md-offser-4">
				<div class="login-panel panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">로그인</h3>
					</div>
					<div class="panel-body">
					<p class="red"><c:out value="${error}"/></p>
					<p><c:out value="${logout}"/></p>
						<form role="form" method="post" action="/login">
							<fieldset>
								<div class="form-group">
									<input class="form-control" placeholder="아이디" name="username" type="text" autofocus>
								</div>
								<div class="form-group">
									<input class="form-control" placeholder="비밀번호" name="password" type="password" value="">
								</div>
								<div class="checkbox">
									<label for="rememberMe" aria-describedby="rememberMeHelp"><input name="remember-me" type="checkbox" id="rememberMe">로그인상태 유지</label>
								</div>
								<a href="index.html" class="btn btn-lg btn-success btn-block">로그인</a>
							</fieldset>
							<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token}" />
						</form>
						<div class="find_wrap">
							 <ul>
							 	<!-- <li><a href="#">아이디 찾기</a></li>
							 	<li><a href="#">비밀번호 찾기</a></li> -->
								<li><a href="/customRegister">회원가입</a></li>
							</ul>
							<!-- <a href="" class=""></a> -->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
	<script src="/resources/vendor/jquery/jquery.min.js"></script>
	<script>
		
		$(".btn-success").on("click", function(e){
				e.preventDefault();
		$("form").submit();
				});
	</script>
	<c:if test="${param.logout != null}">
		<script>
			$(document).ready(function(){
				alert("로그아웃하였습니다. ");
			});
		</script>
	</c:if>
<%@include file="includes/footer.jsp" %>