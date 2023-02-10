<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@include file="includes/header.jsp" %>
<link rel="stylesheet" href="/resources/css/security.css" />
<div class="subpage_banner wrap z-index relative">
	<div class="container">
		<h1 class="page-header">로그아웃</h1>
	</div>
</div>
<div class="wrap wrap1">
	<div class="login-layout">
		<div class="row">
			<div class="col-md4 col-md-offser-4">
				<div class="login-panel panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">로그아웃 하시겠습니까??</h3>
					</div>
					<div class="panel-body">
						<form role="form" method="post" action="/customLogout">
							<!-- <fieldset>
								<a href="/customLogout" class="btn-lg btn-lg btn-success btn-block">로그아웃</a>
							</fieldset> -->
							<button class="btn-lg btn-lg btn-success btn-block">로그아웃</button>
							<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token}" />
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
	<%-- <script src="/resources/vendor/jquery/jquery.min.js"></script>
	<script>
		$(".btn-success").on("click", function(e){
				e.preventDefault();
				$("form").submit();
			alert("로그아웃하였습니다. ");
			self.location = "/customLogin";
			});
	</script> --%>
<%@include file="includes/footer.jsp" %>