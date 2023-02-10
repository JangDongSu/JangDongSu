<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@include file="../includes/header.jsp"%>
<link rel="stylesheet" href="/resources/css/get.css" />
 <style>
	/*.bx-viewport{height:672px!important;}*/
	.border-bottom{border-bottom:1px solid #d5d5d5;}
	.article-field{border-bottom:1px solid #d5d5d5; border-top:0;}
	.article-reg-top:last-child{border-top:0;}
	.dotted{border-bottom:1px solid #d5d5d5;}
	.border-zero{border-bottom:0;}
</style>
  <!-- subpage banner -->
<div class="subpage_banner wrap z-index relative">
	<div class="container">
		<h1 class="page-header">추천 사진 여행지</h1>
	</div>
</div>
<!-- subpage banner -->
<div class="wrap wrap1 wrap2 z-index relative">
	<div class="container">
		<!--복붙 대상-->
		<div class="article-layout">
			<div class="article-top">
				<div class="article-title border-bottom">
					<label><c:out value="${travell.title}" /></label>
				</div>
				<div class="article-field">
					<div class="field get-th field-style">
						<p>작성일</p>
					</div>
					<div class="field get-td field-style">
						<p>
							<fmt:formatDate pattern="yyyy-MM-dd" value="${travell.regdate}" />
						</p>
					</div>
				</div>
				<div class="article-field border-top">
					<div class="field get-th field-style">
						<p>작성자</p>
					</div>
					<div class="field get-td field-style">
						<p>
							<c:out value="${travell.writer}" />
						</p>
					</div>
				</div>
				<div class="article-field">
					<div class="field get-th field-style">
						<p>지역</p>
					</div>
					<div class="field get-td field-style">
						<p>
							<c:out value="${travell.area}" />
						</p>
					</div>
				</div>
				<div class="article-field dotted">
					<div class="field get-th field-style">
						<p>여행지분류</p>
					</div>
					<div class="field get-td field-style">
						<p>
							<c:out value="${travell.kind}" />
						</p>
					</div>
				</div>
				<div class="article-reg-top border-zero">
					<div class="field-address get-th field-style">
						<p>주소</p>
					</div>
					<div class="field-address1 get-td field-style">
						<p><c:out value="${travell.address}" /></p>
					</div>
				</div>
			</div>
			<div class="upload-image-list">
					<ul>
					</ul>
				</div>
			<div class="get-content">
				<pre class="pre_styler">
					<c:out value="${travell.content}" escapeXml="false"/>
				</pre>
			</div>
		</div>
		<div class="article-bottom">
			<div class="field3 get-th field-style">
				<p>
					<i class="fa fa-comments fa-fw"></i><b>댓글 목록</b>
				</p>
				<sec:authorize access="isAuthenticated()">
					<button id='addReplyBtn' class='button button-comm pull-right'>댓글 작성하기</button>
				</sec:authorize>
			</div>
			<div class="field3 get-td">
				<div class="reply-list">
					<ul class="chat">
						<li class="left clearfix" data-t_rno="30">
							<div>
								<div class="header">
									<strong class="primary-font">부산불꽃축제</strong> <small class="pull-right text-muted">2022-12-17 19:00</small>
								</div>
								<p>2022 Busan Firework Festival!!</p>
							</div>
						</li>
					</ul>
				</div>
				<div class="panel-footer">
					<!-- javascript로 html코드 동적으로 생성 -->
				</div>
			</div>
		</div>
		
		<div class="bottom_button">
			<button type="submit" data-oper="list" onclick="location.href='/travell/list'" class="button button-comm">목록</button>
			<!-- 게시글 수정버튼시큐리티 / 시작 /-->
			<sec:authentication property="principal" var="pinfo" />
			<sec:authorize access="isAuthenticated()">
				<c:if test="${pinfo.username eq travell.writer}">
					<button class="button button-blue" data-oper='modify'>수정</button>
				</c:if>
			</sec:authorize>
			<!-- 게시글 수정버튼시큐리티 / 끝 /-->

		</div>
		<!--복붙 대상-->
		<form action="/travell/modify" id="operForm" method="get">
			<input type="hidden" name="t_bno" id="t_bno" value="<c:out value="${travell.t_bno}"/>" />
			 <input type="hidden" name="pageNum" value="<c:out value="${galleryPaging.pageNum}"/>" /> 
			 <input type="hidden" name="amount" value="<c:out value="${galleryPaging.amount}"/>" /> 
			 <input type="hidden" name="type" value="<c:out value="${galleryPaging.type}"/>" /> 
			 <input type="hidden" name="keyword" value="<c:out value="${galleryPaging.keyword}"/>" />
		</form>
	</div>
</div>
<div class="bigPictureWrapper">
	<div class="bigPicture">
	</div>
</div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">REPLY MODAL</h4>
			</div>
			<div class="modal-body">
				<div class="form-group">
					<label>Reply</label>
					<input class="form-control" name='reply' value='New Reply!!!!'>
				</div>
				<div class="form-group">
					<label>Replyer</label>
					<input class="form-control" name='replyer' value='replyer' readonly="readonly">
				</div>
				<div class="form-group">
					<label>Reply Date</label>
					<input class="form-control" name='replyDate' value='2022-12-25 00:00'>
				</div>
			</div>
			<div class="modal-footer">
				<button id='modalModBtn' type="button" class="btn btn-warning">Modify</button>
				<button id='modalRemoveBtn' type="button" class="btn btn-danger">Remove</button>
				<button id='modalRegisterBtn' type="button" class="btn btn-primary">Register</button>
				<button id='modalCloseBtn' type="button" class="btn btn-default">Close</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<script src="/resources/js/travellreply.js"></script>
<script>
	//console.log(galleryReplyService);
	console.log("=================");
	console.log("JS ajax TEST");
	
	/*var t_bnoValue = '<c:out value="${travell.t_bno}"/>';
	//댓글 추가
	travellReplyService.add(
			{reply:"댓글 내용입니다. ", replyer:"댓글러", t_bno:t_bnoValue},
			function(result){
				alert("RESULT : " + result);
			}
	);*/
	
	//게시물 읽기 페이지 - 댓글리스트
	/*travellReplyService.getList(
			{t_bno:t_bnoValue, page:1},
			function(list){
				for(var i=0, len=list.length||0; i<len; i++){
					console.log(list[i]);
				}
			}
		);*/
	//댓글 삭제
	/*travellReplyService.remove(67, function(count){
				console.log(count);
				
				if(count === "success"){
					alert("Removed");
				}
			}, function(err){
				alert('Remove Error');
			});*/
	
	//댓글 수정
	/*travellReplyService.update({
		t_rno : 69,
		t_bno : t_bnoValue, 
		reply : "intercoller"
		//replyer : "댓글 작성자"
	}, function(result){
		alert("댓글이 수정되었습니다. ");
	});*/
			
	//댓글 조회하기
	/*travellReplyService.get(44, function(data){
		console.log(data);
	});*/
	
	$(document).ready(function(){
		var t_bnoValue = '<c:out value="${travell.t_bno}"/>';
		var replyUL = $(".chat");
		showList(1);
		function showList(page){
			console.log("show list"+ page);
			travellReplyService.getList(
				{t_bno:t_bnoValue,page: page|| 1},
				function(replyCnt, list){
					console.log("replyCnt: "+ replyCnt);
					console.log("list: "+ list);
					console.log(list);
					
					if(page == -1){
						pageNum = Math.ceil(replyCnt/10.0);
						showList(pageNum);
						return;
					}
					
					var str ="";
					if(list == null || list.length == 0){
						replyUL.html("");
						return;
					}
					for (var i = 0, len = list.length || 0; i< len; i++){
						str +="<li class='left clearfix' data-t_rno='"+list[i].t_rno+"'>";
						str +=" <div><div class='header'><strong class='primary-font'>"+list[i].replyer+"</strong>";
						str +="    <small class='pull-right text-muted'>"+travellReplyService.displayTime(list[i].replyDate)+"</small></div>";
						str +="    <p>"+list[i].reply+"</p></div></li>";
					}
					replyUL.html(str);
					showReplyPage(replyCnt);
				}); //end getList
		} // end showList
		
		var pageNum = 1;
		var replyPageFooter = $(".panel-footer");
		
		function showReplyPage(replyCnt){
			var endNum = Math.ceil(pageNum / 10.0) * 10;
			var startNum = endNum - 9;
			var prev = startNum != 1;
			var next = false;
			
			if(endNum * 10 >= replyCnt){
				endNum = Math.ceil(replyCnt/10.0);
			}
			if(endNum * 10 < replyCnt){
				next = true;
			}
			var str = "<ul class='pagination pull-right'>";
			if(prev){
				str += "<li class='page-item'><a class='page-link' href='"+(startNum - 1)+"'>Previous</a></li>";
			}
			for(var i = startNum; i <= endNum; i++){
				var active = pageNum == i? "active":"";
				str += "<li class='page-item "+active+"'><a class='page-link' href='"+i+"'>"+i+"</a></li>";
			}
			if(next){
				str += "<li class='page-item'><a class='page-link' href='"+(endNum + 1)+"'>Next</a></li>";
			}
			str += "<ul></div>";
			console.log(str);
			replyPageFooter.html(str);
		} //end showReplyPage
		
		replyPageFooter.on("click","li a",
			function(e){
				e.preventDefault();
				console.log("page click");
				var targetPageNum = $(this).attr("href");
				console.log("targetPageNum: "+targetPageNum);
				pageNum = targetPageNum;
				showList(pageNum);
		});
		
		var modal = $(".modal");
		var modalInputReply = modal.find("input[name='reply']");
		var modalInputReplyer = modal.find("input[name='replyer']");
		var modalInputReplyDate = modal.find("input[name='replyDate']");
		
		 var modalModBtn = $("#modalModBtn");
		 var modalRemoveBtn = $("#modalRemoveBtn");
		 var modalRegisterBtn = $("#modalRegisterBtn");
		 
		 //댓글 추가 - 회원제(2022.12.26)
		 var replyer = null;
		 
		 <sec:authorize access="isAuthenticated()">
			replyer = '<sec:authentication property="principal.username"/>';
		</sec:authorize>
		
		var csrfHeaderName = "${_csrf.headerName}";
		var csrfTokenValue = "${_csrf.token}";
		$(document).ajaxSend(function(e, xhr, options){
			xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
		});
		 
		 $("#modalCloseBtn").on("click",
			function(e){
			 	modal.modal('hide');
		 });
		 
		 $("#addReplyBtn").on("click",
			function(e){
			 	modal.find("input").val("");
			 	modalInputReplyer.val(replyer);	// 댓글추가 - 회원제
			 	//modalInputReplyer.removeAttr("readonly");
			 	modalInputReplyDate.closest("div").hide();
			 	modal.find("button[id !='modalCloseBtn']").hide();
			 	modalRegisterBtn.show();
			 	$(".modal").modal("show");
		 });
		 
		 modalRegisterBtn.on("click",
			function(e){
			  var reply = {
				reply: modalInputReply.val(),
				replyer:modalInputReplyer.val(),
				t_bno:t_bnoValue
			  };
			  travellReplyService.add(reply,
				function(result){
				  alert(result);
				  modal.find("input").val("");
				  modal.modal("hide");
				  showList(-1);
			  });
		 });
		 
		 modalModBtn.on("click",
			function(e){
			 	var originalReplyer = modalInputReplyer.val();
			 	console.log("OriginalReplyer : " + originalReplyer);
			 	var reply = {t_rno:modal.data("t_rno"), reply: modalInputReply.val(),
			 			replyer: originalReplyer};
			 	if(!replyer){
			 		alert("로그인후 수정이 가능합니다. \n먼저 로그인을 해주세요. ");
					 modal.modal("hide");
					 return;
				 }
				 if(replyer != originalReplyer){
					 alert("본인이 작성한 댓글만 수정이 가능합니다. ");
					 modal.modal("hide");
					 return;
				 }
			 	travellReplyService.update(reply,
			 		function(result){
			 			alert(result);
			 			modal.modal("hide");
			 			showList(pageNum);
			 	});
		 });
		 
		 modalRemoveBtn.on("click",
			function(e){
			 var t_rno = modal.data("t_rno");
			 var originalReplyer = modalInputReplyer.val();
			 console.log("T_RNO : " + t_rno);
			 console.log("Replyer : " + replyer);
			 console.log("OriginalReplyer : " + originalReplyer);
			 if(!replyer){
				 alert("로그인후 삭제가 가능합니다. \n먼저 로그인을 해주세요. ");
				 modal.modal("hide");
				 return;
			 }
			 if(replyer != originalReplyer){
				 alert("본인이 작성한 댓글만 삭제가 가능합니다. ");
				 modal.modal("hide");
				 return;
			 }
			 travellReplyService.remove(t_rno, originalReplyer, 
				function(result){
				 	alert(result);
		 			modal.modal("hide");
		 			showList(pageNum);
			 });
		 });
		 
		 replyUL.on("click", "li",
			function(e){
			 	var t_rno = $(this).data("t_rno");
			 	travellReplyService.get(t_rno,
			 		function(reply){
			 			modalInputReply.val(reply.reply);
			 			modalInputReplyer.val(reply.replyer).attr("readonly", "readonly");
			 			modalInputReplyDate.val(travellReplyService.displayTime(reply.replyDate)).attr("readonly", "readonly");
			 			modal.data("t_rno", reply.t_rno);
			 			modal.find("button[id != 'modalCloseBtn']").hide();
			 			modalModBtn.show();
			 			modalRemoveBtn.show();
			 			$(".modal").modal("show");
			 	});
		 });
}); //end ready
</script>
<script>
	//console.log(travellReplyService);
	
	
	$(document).ready(function(){
		var operForm = $("#operForm");
		var removeForm = $("#removeForm");
		$("button[data-oper='modify']").on("click", function(e){
			//alert("modify...");
			operForm.attr("action", "/travell/modify").submit();
		});
		
		/*$("button[data-oper='remove']").on("click", function(e){
			alert("remove");
			removeForm.find("#t_bno").remove();
			removeForm.attr("method", "post");
			removeForm.submit();
		});*/
		
		$("button[data-oper='list']").on("click", function(e){
			operForm.find("#t_bno").remove();
			operForm.attr("action", "/travell/list");
			operForm.submit();
		});
		
		//file attach - 2022-12-30
		var t_bno = '<c:out value="${travell.t_bno}"/>';
		$.getJSON("/travell/getAttachList", {t_bno:t_bno},
		function(arr){
			 console.log(arr);
			 var str = "";
			 	$(arr).each(
			 	function(i, attach){
			 		//image type
			 		if(attach.fileType){
			 			var fileCallPath = encodeURIComponent(attach.uploadPath + "/" + attach.uuid + "_" + attach.fileName);
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
			$(".upload-image-list ul").html(str);
		 });
		
		//SHOW or Download 2022-12-30
		function showImage(fileCallPath){
			//alert(fileCallPath);
			$(".bigPictureWrapper").css("display","flex").show();
			$(".bigPicture").html("<img src='/display?fileName="+fileCallPath+"'>").animate({width:'100%', top:'0'}, 600);
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
		/*$(".bigPictureWrapper").on("click", function(e){
			$(".bigPictureWrapper").hide();
			$(".bigPicture").css("top", "15%");
		});*/
	});
</script>

<!-- <script>
$(document).ready(function(){
    $('.upload-image-list').bxSlider({
    	mode:'horizontal'
    });
    
  });
</script> -->

<%@include file="../includes/footer.jsp"%>
