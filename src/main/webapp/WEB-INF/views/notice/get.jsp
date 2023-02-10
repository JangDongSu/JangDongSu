<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@include file="../includes/header.jsp"%>
<link rel="stylesheet" href="/resources/css/get.css" />
  <!-- subpage banner -->
<div class="subpage_banner wrap z-index relative">
	<div class="container">
		<h1 class="page-header">공지사항</h1>
	</div>
</div>
<!-- subpage banner -->
<div class="wrap wrap1 wrap2 z-index relative">
<div class="container">
<!--복붙 대상-->
		<div class="article-layout">
			<div class="article-top">
				<div class="article-title">
					<label><c:out value="${notice.n_title}" /></label>
				</div>
				<div class="article-field">
					<div class="field get-th field-style">
						<p>작성일</p>
					</div>
					<div class="field get-td field-style">
						<p>
							<fmt:formatDate pattern="yyyy-MM-dd" value="${notice.regdate}" />
						</p>
					</div>
				</div>
				<div class="article-field">
					<div class="field get-th field-style">
						<p>작성자</p>
					</div>
					<div class="field get-td field-style">
						<p>
							<c:out value="${notice.n_writer}" />
						</p>
					</div>
				</div>
			</div>
			<div class="get-content">
				<pre class="pre_styler">
					<c:out value="${notice.n_content}" escapeXml="false"/>
				</pre>
			</div>
		</div>
		<div class="article-bottom">
			<div class="field3 get-th field-style">
				<p>
					<b>첨부파일</b>
				</p>
			</div>
			<div class="field3 get-td">
				<div class="upload-result">
					<ul>
					</ul>
				</div>
			</div>
		</div>
		<div class="bottom_button">
			<button data-oper="list" class="button button-comm">목록</button>
			<!-- 게시글 수정버튼시큐리티 / 시작 /-->
			<sec:authentication property="principal" var="pinfo" />
			<sec:authorize access="isAuthenticated()">
				<c:if test="${pinfo.username eq notice.n_writer}">
					<button class="button button-blue" data-oper='modify'>수정</button>
				</c:if>
			</sec:authorize>
			<!-- 게시글 수정버튼시큐리티 / 끝 /-->
		</div>
		<!--복붙 대상-->
		<form id='operForm' action="/notice/modify" method="get">
			<input type='hidden' id='n_bno' name='n_bno' value='<c:out value="${notice.n_bno}"/>'>
			<input type='hidden' name='pageNum' value='<c:out value="${criteria.pageNum}"/>'>
			<input type='hidden' name='amount' value='<c:out value="${criteria.amount}"/>'>
			<input type="hidden" name="type" value="<c:out value="${criteria.type}"/>"/>
			<input type="hidden" name="keyword" value="<c:out value="${criteria.keyword}"/>"/>
		</form>
	</div>
</div>
<!--  /.row -->
<div class="bigPictureWrapper">
	<div class="bigPicture">
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	var operForm = $("#operForm");
	$("button[data-oper='modify']").on("click",
		function(e){
			operForm.attr("action","/notice/modify").submit();
	});
	$("button[data-oper='list']").on("click",
		function(e){
		operForm.find("#n_bno").remove();
		operForm.attr("action","/notice/list");
		operForm.submit();
	});
	
	
	
});
</script>
<script>
	$(document).ready(function(){
		//file attach - 2022-12-30
		var n_bno = '<c:out value="${notice.n_bno}"/>';
		$.getJSON("/notice/getAttachList", {n_bno:n_bno},
		function(arr){
			 console.log(arr);
			 var str = "";
			 	$(arr).each(
			 	function(i, attach){
			 		//image type
			 		if(attach.fileType){
			 			var fileCallPath = encodeURIComponent(attach.uploadPath + "/s_" + attach.uuid + "_" + attach.fileName);
						str +="<li data-path='"+attach.uploadPath+"'data-uuid='"+attach.uuid+"' data-filename='"+attach.fileName+"' data-type='"+attach.fileType+"' ><div>";
						str +="<img src='/display?fileName="+fileCallPath+"'>";
						str +="</div>";
						str +"</li>";
					}else{
						str +="<li data-path='"+attach.uploadPath+"' data-uuid='"+attach.uuid+"' data-filename='"+attach.fileName+"' data-type='"+attach.fileType+"' ><div>";
						str +="<span>" + attach.fileName+"</span>,<br>";
						str +="<img src='/resources/images/attach.png'></a>";
						str +="</div>";
						str +"</li>";
					}
				});
			$(".upload-result ul").html(str);
		 });
		
		//SHOW or Download 2022-12-30
		function showImage(fileCallPath){
			//alert(fileCallPath);
			$(".bigPictureWrapper").css("display","flex").show();
			$(".bigPicture").html("<img src='/display?fileName="+fileCallPath+"'>").animate({width:'100%', top:'0'/*, height:'100%'*/}, 600);
		}
		$(".upload-result").on("click", "li", function(e){
			console.log("view image");
			var liObj = $(this);
			var path = encodeURIComponent(liObj.data("path") + "/" + liObj.data("uuid") + "_" + liObj.data("filename"));
			if(liObj.data("type")){
				showImage(path.replace(new RegExp(/\\/g), "/"));
			}else{
				//download
				self.location = "/download?fileName="+path;
			}
		});
		$(".bigPictureWrapper").on("click", function(e){
			$(".bigPictureWrapper")/*.css("display","none")*/.hide();
			$(".bigPicture").css("top", "15%");
			/*$(".bigPictureWrapper").animate({width:'0%',height:'0'},1000);
			setTimeout(function(){
				$('.bigPictureWrapper').hide();
			}, 1000);*/
		});
	});
</script>

<%@include file="../includes/footer.jsp"%>
