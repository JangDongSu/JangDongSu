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
		<h1 class="page-header">Board Page</h1>
	</div>
</div>
<!-- subpage banner -->
<div class="wrap wrap1 wrap2 z-index relative">
	<div class="container">
		<!--복붙 대상-->
		<div class="article-layout">
			<form role="form" action="/board/modify/" method="post">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
				<input type="hidden" name="pageNum" value='<c:out value="${criteria.pageNum}" />' /> 
				<input type="hidden" name="amount" value='<c:out value="${criteria.amount}" />' /> 
				<input type="hidden" name="type" value='<c:out value="${criteria.type}" />' /> 
				<input type="hidden" name="keyword" value='<c:out value="${criteria.keyword}" />' />
				<input type="hidden" name="bno" value='<c:out value="${board.bno}" />'/>
				<div class="article-top">
					<div class="article-reg-top">
						<div class="field1 get-th field-style">
							<label>작성자</label>
						</div>
						<div class="field2 get-td field-style">
							<input type="text" class="form-stye" name="writer" value='<c:out value="${board.writer}" />' readonly>
						</div>
					</div>
					<div class="article-reg-top">
						<div class="field1 get-th field-style">
							<label>제목</label>
						</div>
						<div class="field2 get-td field-style">
							<input type="text" class="form-stye" name="title" value="<c:out value="${board.title}" />">
						</div>
					</div>

				</div>
				<div class="get-content">
					<textarea name="content" rows="10" id="content-editor" class="content-txtarea"><c:out value="${board.content}" /></textarea>
				</div>
				<div class="article-bottom">
					<div class="field1 get-th field-style">
						<label><b>첨부파일</b></label>
					</div>
					<div class="field2 get-td field-style uploadDiv">
						<input type="file" name="uploadFile" class="file-input" multiple />
					</div>
					<div class="upload-result">
						<ul></ul>
					</div>
					<div class="bottom_button1">
						<sec:authentication property="principal" var="pinfo" />
						<sec:authorize access="isAuthenticated()">
							<c:if test="${pinfo.username eq board.writer}">
								<button type="submit" data-oper="modify" class="button button-submit" onclick="location.href='/board/modify?bno=<c:out value="${board.bno}"/>'">수정</button>
								<button type="submit" data-oper="remove" class="button button-comm">삭제</button>
								<!-- <button type="submit" class="button button-comm">삭제</button>
								<button class="button button-submit" type="submit">게시물 등록</button>-->
							</c:if>
						</sec:authorize>
						<button type="submit" data-oper="list" class="button button-comm" onclick="location.href='/board/list'" style="margin-left:10px;">List</button>
						<!--<a class="button button-comm" href="/board/list" style="margin-left:10px;">목록</a> -->
					</div>
					<!--복붙 대상-->
				</div>
			</form>
		</div>
	</div>
	<!-- /container -->
</div>
<!-- /.wrap -->
<script type="text/javascript">
	$(document).ready(function() {
		var formObj = $("form");
		
		//검색타입, 검색어 전달 - 변수선언
		var typeTag = $("input[name='type']").clone();
        var keywordTag = $("input[name='keyword']").clone();
				
		$('button').on("click", function(e){
			e.preventDefault();
			var operation = $(this).data("oper");
			console.log(operation);
			if(operation === 'remove'){
				formObj.attr("action", "/board/remove");
			}else if(operation === 'list'){
					//move to list
					formObj.attr("action", "/board/list").attr("method", "get");
					var pageNumTag = $("input[name='pageNum']").clone();
					var amountTag = $("input[name='amount']").clone();
					formObj.empry();
					formObj.append(pageNumTag);
					formObj.append(amountTag);
					
					//검색타입, 검색어 전달
					formObj.append(typeTag);
        			formObj.append(keywordTag);
        			
				/*var location = false;
				if(location){
					self.location = "/board/list";
					return;
				}else{
					formObj.attr("action", "/board/list").attr("method", "get");
					formObj.empry();
				}*/
			}else if(operation === 'modify'){
				console.log("submit clicked");
				var str = "";
				
				$(".upload-result ul li").each(function(i, obj){
					var jobj = $(obj);
					console.dir(jobj);
					str +="<input type='hidden' name='attachList["+i+"].fileName' value='"+jobj.data("filename")+"'>";
					str +="<input type='hidden' name='attachList["+i+"].uuid' value='"+jobj.data("uuid")+"'>";
					str +="<input type='hidden' name='attachList["+i+"].uploadPath' value='"+jobj.data("path")+"'>";
					str +="<input type='hidden' name='attachList["+i+"].fileType' value='"+jobj.data("type")+"'>";
				});
				formObj.append(str).submit();
			}
			formObj.submit();
		});
	});
</script>
<script>
	$(document).ready(function(){
		var addFiles = $("input[type='file']");				
		addFiles.change(
			function(e){
				//alert("change");
				//alert($("input[type='file']").val());
				var formData = new FormData();
				var inputFile = $("input[name='uploadFile']");
				var files = inputFile[0].files;
				for(var i=0; i<files.length; i++){
					if(!checkExtension(files[i].name, files[i].size)){
						return false;
					}
					formData.append("uploadFile", files[i]);
				}
				$.ajax({
					url:'/uploadAjaxAction',
					processData : false,
					contentType : false,data:
						formData,type:'POST',
					beforeSend : function(xhr){
					xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
					},
					dataType : 'json',
					success : function(result){
						console.log(result);
						showUploadResult(result);
					}
				});//$.ajax
			});
		//file attach - 2022-12-30 - modify
		var bno = '<c:out value="${board.bno}"/>';
		$.getJSON("/board/getAttachList", {bno:bno},
		function(arr){
			 console.log(arr);
			 //alert("getJSON");
			 var str = "";
			 	$(arr).each(
			 	function(i, attach){
			 		//image type
			 		if(attach.fileType){
							var fileCallPath = encodeURIComponent(attach.uploadPath + "/s_" + attach.uuid + "_" + attach.fileName);
							str +="<li data-path='"+attach.uploadPath+"'data-uuid='"+attach.uuid+"'"
							str +="data-filename='"+attach.fileName+"' data-type='"+attach.fileType+"' ><div>";
							str +="<span>" + attach.fileName+"</span>";
							str +="<button type='button' data-file=\'"+fileCallPath+"\'data-type='image'"
							str +="class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
							str +="<img src='/display?fileName="+fileCallPath+"'>";
							str +="</div>";
							str +"</li>";
						}else{
							str +="<li data-path='"+attach.uploadPath+"' data-uuid='"+attach.uuid+"'"
							str += "data-filename='"+attach.fileName+"' data-type='"+attach.fileType+"' ><div>";
							str +="<span>" + attach.fileName+"</span><br>";
							str +="<button type='button' data-file=\'"+fileCallPath+"\' data-type='file' "
							str +="class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
							str +="<img src='/resources/images/attach.png'></a>";
							str +="</div>";
							str +"</li>";
						}
				});
			$(".upload-result ul").html(str);
		 });//end getjson
		 $(".upload-result").on("click", "button", function(e){
			 console.log("delete file");
			 if(confirm("선택한 파일을 삭제하시겠습니까?")){
				 var targetLi = $(this).closest("li");
				 targetLi.remove();
			 }
		 });
		 
		 var regex = new RegExp("(.*)\.(exe | sh | zip | alz)$");				
			var maxSize = 5242880; //5 * 1024 * 1024 - 5mb
			function checkExtension(fileName, fileSize){
				if(fileSize >= maxSize){
					alert("파일 사이즈 초과되어 \n파일 업로드를 할 수 없습니다. (최대 5mb까지 가능)");
					return false;
				}
				if(regex.test(fileName)){
					alert("해당 종류의 파일은 업로드 할 수 없습니다. ");
					return false;
				}
				return true;
			}
							
			var csrfHeaderName = "${_csrf.headerName}";
			var csrfTokenValue = "${_csrf.token}";
			
			var addFiles = $("input[type='file']");				
			addFiles.change(
				function(e){
					//alert("change");
					//alert($("input[type='file']").val());
					var formData = new FormData();
					var inputFile = $("input[name='uploadFile']");
					var files = inputFile[0].files;
					for(var i=0; i<files.length; i++){
						if(!checkExtension(files[i].name, files[i].size)){
							return false;
						}
						formData.append("uploadFile", files[i]);
					}
					$.ajax({
						url:'/uploadAjaxAction',
						processData : false,
						contentType : false,	
						beforeSend : function(xhr){
						xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
						},
						dataType : formData,
						type : 'POST',
						dataType : 'json',
						success : function(result){
							console.log(result);
							showUploadResult(result);
						}
					});//$.ajax
				});
					function showUploadResult(uploadResultArr){
						if(!uploadResultArr || uploadResultArr.length == 0){return;}
						var uploadUL = $(".upload-result ul");
						var str = "";
						$(uploadResultArr).each(
							function(i, obj){
								if(obj.image){
									var fileCallPath = encodeURIComponent(obj.uploadPath + "/s_" + obj.uuid + "_" + obj.fileName);
									str +="<li data-path='"+obj.uploadPath+"'";
									str +=" data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.image+"'";
									str +" ><div>";
									str +="<span>" + obj.fileName+"</span>";
									str +="<button type='button' data-file=\'"+fileCallPath+"\'"
									str +="data-type='image' class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
									str +="<img src='/display?fileName="+fileCallPath+"'>";
									str +="</div>";
									str +"</li>";
								}else{
									var fileCallPath = encodeURIComponent(obj.uploadPath + "/" + obj.uuid + "_" + obj.fileName);
									var fileLink = fileCallPath.replace(new RegExp(/\\/g), "/");
										
									str +="<li ";
									str +=" data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.image+"' ><div>";
									str +="<span>" + obj.fileName+"</span>";
									str +="<button type='button' data-file=\'"+fileCallPath+"\' data-type='file' "
									str +="class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
									str +="<img src='/resources/images/attach.png'></a>";
									str +="</div>";
									str +"</li>";
								}
							});
			uploadUL.append(str);
					}		
	});
	
	/*ClassicEditor.create( document.querySelector( '#content-editor' ), {
		language : 'ko'
	})*/
</script>






<%@include file="../includes/footer.jsp"%>
