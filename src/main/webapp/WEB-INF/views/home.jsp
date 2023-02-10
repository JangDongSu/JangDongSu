<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page session="false" %>
<%@include file="includes/header.jsp" %>

<div class="wrap" id="mainbanner">
	<!-- <div class="container">
		<div id="banner_area" class="relative">
			<h1 class="page-header">Welcome to JAFA Photo Traveller!</h1>
		</div>
	</div>-->
	<div class="slide-contents">
		<img src="/resources/images/img01.jpg" alt="slide-img1" />
		<img src="/resources/images/img03.jpg" alt="slide-img2" />
		<img src="/resources/images/img02.jpg" alt="slide-img3" />
	</div>
</div>
<script>
$(document).ready(function(){
    $('.slide-contents').bxSlider({
    	auto: true,
		speed: 1000,
		//pause: 4000,
		mode:'fade',
		autoControls: false,
		pager:true,
    });
  });
</script>
<div class="wrap contents">
	<div class="container">
		<div id="content_wrap">
			<div class="content latest">
				<div class="content-title">
					<h2>공지사항</h2>
					<a href="/notice/list" class="viewmore">more</a>
				</div>
				<div class="content-area">
					<ul class="notice-latest">
						<c:forEach items="${notice}" var="notice">
							<li><a href="<c:url value="/notice/get?pageNum=1&amount=10&type=&keyword=&n_bno=${notice.n_bno}" />" class="move"> <span class="latest-title"><c:out value="${notice.n_title}" /></span> <span class="latest-date"><fmt:formatDate pattern="yyyy-MM-dd" value="${notice.regdate}" /></span>
							</a></li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<div class="content bg2">
				<div class="content-title">
					<h2>포토갤러리</h2>
					<a href="/gallery/list" class="viewmore">more</a>
				</div>
				<div class="content-area">
					<ul class="img-latest-list">
						<c:forEach items="${gallery}" var="gallery">
							<li>
								<a href="gallery/get?pageNum=1&amount=16&type=&keyword=&p_bno=<c:out value="${gallery.p_bno}" />">
									<span id='<c:out value="${gallery.p_bno}"/>'>
									</span> 
									<span class="gallery-title">
										<span class="gal-object"><c:out value="${gallery.title}" /></span>
										<span class="gal-writer"><c:out value="${gallery.writer}" /></span>
										<span class="gal-regdate"><fmt:formatDate pattern="yyyy-MM-dd" value="${gallery.regdate}" /></span>
									</span>
								</a>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<div class="content bg3">
				<div class="content-title">
					<h2>추천 사진여행지</h2>
					<a href="/travell/list" class="viewmore">more</a>
				</div>
				<div class="content-area">
					<ul class="img-latest-list2">
						<c:forEach items="${travell}" var="travell">
						<li>
							<a href="travell/get?pageNum=1&amount=16&type=&keyword=&t_bno=<c:out value="${travell.t_bno}" />">
								<span id='<c:out value="${travell.t_bno}"/>'>
								</span> 
								<!-- <img src="https://via.placeholder.com/640x480" alt="gallery-latest" /> -->
								<span class="gallery-title">
									<span class="gal-object"><c:out value="${travell.title}" /></span>
									<span class="gal-writer"><c:out value="${travell.area}" />/<c:out value="${travell.kind}" /></span>
									<span class="gal-regdate"><fmt:formatDate pattern="yyyy-MM-dd" value="${travell.regdate}" /></span>
								</span>
							</a>
						</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
		<script>
				$(document).ready(function(){
					var list = new Array();
					<c:forEach items="${gallery}" var="gallery">
						list.push(<c:out value="${gallery.p_bno}"/>);
					</c:forEach>
					$.getJSON("/gallery/getAttachListOnList", {list:list}, function(data){
						console.log(data);
						var html="";
						$.map(data, function(attach, p_bno){
							if(attach[0] != null){
								if(attach[0].fileType){
									var fileCallPath = encodeURIComponent(attach[0].uploadPath + "/" + attach[0].uuid + "_" + attach[0].fileName);
									html ="<img src='/display?fileName="+fileCallPath+"' alt='gallery-latest' />";
								}else{
									html = "<img src='/resources/images/attach.png' alt='gallery-latest' />";
								}
								$("#"+p_bno).html(html);
							}
						});
					});
				});
			</script>
			<script>
				$(document).ready(function(){
					var list = new Array();
					<c:forEach items="${travell}" var="travell">
						list.push(<c:out value="${travell.t_bno}"/>);
					</c:forEach>
					$.getJSON("/travell/getAttachListOnList", {list:list}, function(data){
						console.log(data);
						var html="";
						$.map(data, function(attach, t_bno){
							if(attach[0] != null){
								if(attach[0].fileType){
									var fileCallPath = encodeURIComponent(attach[0].uploadPath + "/" + attach[0].uuid + "_" + attach[0].fileName);
									html ="<img src='/display?fileName="+fileCallPath+"' alt='travell-latest' />";
								}else{
									html = "<img src='/resources/images/attach.png' alt='travell-latest' />";
								}
								$("#"+t_bno).html(html);
							}
						});
					});
				});
			</script>
	</div>
</div>

<%@include file="includes/footer.jsp" %>
