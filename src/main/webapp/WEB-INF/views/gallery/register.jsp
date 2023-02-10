<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<link rel="stylesheet" href="/resources/css/get.css" />

<%@include file="../includes/header.jsp"%>
<div class="subpage_banner wrap z-index relative">
	<div class="container">
		<h1 class="page-header">갤러리</h1>
	</div>
</div>
<!-- subpage banner -->
<div class="wrap wrap1 wrap2 z-index relative">
	<div class="container">
		<!--복붙 대상-->
		<div class="article-layout">
			<form role="form" action="/gallery/register" method="post">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<div class="article-top">
					<div class="article-reg-top">
						<div class="field1 get-th field-style">
							<label>작성자</label>
						</div>
						<div class="field2 get-td field-style">
							<input type="text" class="form-stye" name="writer" value='<sec:authentication property="principal.username"/>' readonly>
						</div>
					</div>
					<div class="article-reg-top">
						<div class="field1 get-th field-style">
							<label>제목</label>
						</div>
						<div class="field2 get-td field-style">
							<input type="text" class="form-stye" name="title" id="title" required>
						</div>
					</div>

				</div>
				<div class="get-content">
					<textarea name="content" id="content-editor" rows="10" class="content-txtarea" required></textarea>
				</div>
				<div class="article-bottom">
					<div class="field1 get-th field-style">
						<label><b>첨부파일</b></label>
					</div>
					<div class="field2 get-td">
						<input type="file" name="uploadFile" class="file-input" multiple />
					</div>
					<div class="upload-result">
						<ul></ul>
					</div>
					<div class="bottom_button">
						<button class="button button-submit" type="submit">게시물 등록</button>
						<a class="button button-comm" href="/gallery/list">목록</a>
					</div>
					<!--복붙 대상-->
				</div>
			</form>
		</div>
	</div>
	<!-- /.end container -->
</div>
<!-- /.end wrap-->



<script src="/resources/js/fileupload.js"></script>

<script type="text/javascript">
	$(document)
			.ready(
					function() {

						var result = '<c:out value="${result}"/>';

						checkModal(result);

						history.replaceState({}, null, null);

						function checkModal(result) {

							if (result === '' || history.state) {
								return;
							}

							if (parseInt(result) > 0) {
								$(".modal-body").html(
										"게시글 " + parseInt(result)
												+ " 번이 등록되었습니다.");
							}

							$("#myModal").modal("show");
						}

						$("#regBtn").on("click", function() {

							self.location = "/gallery/register";

						});

						var actionForm = $("#actionForm");

						$(".paginate_button a").on(
								"click",
								function(e) {

									e.preventDefault();

									console.log('click');

									actionForm.find("input[name='pageNum']")
											.val($(this).attr("href"));
									actionForm.submit();
								});

						$(".move")
								.on(
										"click",
										function(e) {

											e.preventDefault();
											actionForm
													.append("<input type='hidden' name='p_bno' value='"
															+ $(this).attr(
																	"href")
															+ "'>");
											actionForm.attr("action",
													"/gallery/get");
											actionForm.submit();

										});

						var searchForm = $("#searchForm");

						$("#searchForm button").on(
								"click",
								function(e) {

									if (!searchForm.find("option:selected")
											.val()) {
										alert("검색종류를 선택하세요");
										return false;
									}

									if (!searchForm.find(
											"input[name='keyword']").val()) {
										alert("키워드를 입력하세요");
										return false;
									}

									searchForm.find("input[name='pageNum']")
											.val("1");
									e.preventDefault();

									searchForm.submit();

								});
					});
</script>
<script>
$(document).ready(function(){
	//fileupload - 2022-12-29
	var formObj = $("form[role='form']");
	var regex = new RegExp("(.*)\.(exe | sh | zip | alz)$");
	var addFiles = $("input[type='file']");
						
	/*(.*) : 임의의 문자가 0개 이상, \은 그냥 dot, exe또는 sh또는....
	어떤 문자로 시작하든지, 몇 글자이든 상관없다. 확장자가 exe, sh, zip, alz , jpg찾겠다. 
	(1.exe), (a.sh), (123.zip), (ab_1234.alz)..... */
					
	var maxSize = 5242880; //5 * 1024 * 1024 - 5mb
	function checkExtension(fileName, fileSize){
		if(fileSize >= maxSize){
			alert("파일 사이즈 초과되어 \n파일 업로드를 할 수 없습니다. (최대 5mb까지 가능)");
			addFiles.val("");
			return false;
		}
		if(regex.test(fileName)){
			alert("해당 종류의 파일은 업로드 할 수 없습니다. ");
			addFiles.val("");
			return false;
		}
		return true;
	}
					
	var csrfHeaderName = "${_csrf.headerName}";
	var csrfTokenValue = "${_csrf.token}";
					
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
				data : formData,
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
					uploadUL.append(str);
				});
			}
		$(".upload-result").on("click", "button", function(e){
			console.log("delete file");
			var targetFile = $(this).data("file");
			var type = $(this).data("file");
			var targetLi = $(this).closest("li");
			
			$.ajax({
				url : '/deleteFile',
				data : {fileName : targetFile, type:type},
				beforeSend : function(xhr){
					xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
				},
				dataType : 'text',
				type : 'POST',
				success : function(result){
					alert(result);
					targetLi.remove();
				}
			});//$.ajax
		});
		$("button[type='submit']").on("click", function(e){
			e.preventDefault();
			console.log("submit clicked");
			var str = "";
			var title = $("#title").val();
			var ir1 = $("#content-editor").val();
			
			/*//제목 입력 유효성 검사
			if(title == "" || title== null || title == "<p>&nbsp;</p>"){
				alert("제목을 입력하세요. ");
				return;
			}
			
			//내용 입력 유효성 검사
			if(ir1 == "" || ir1== null || ir1 == "<p>&nbsp;</p>"){
				alert("내용을 입력하세요. ");
				return;
			}*/
			
			$(".upload-result ul li").each(
					function(i, obj){
						var jobj = $(obj);
						console.dir(jobj);
						console.log("------------");
						console.log(jobj.data("filename"));
						str +="<input type='hidden' name='attachList["+i+"].fileName' value='"+jobj.data("filename")+"'>";
						str +="<input type='hidden' name='attachList["+i+"].uuid' value='"+jobj.data("uuid")+"'>";
						str +="<input type='hidden' name='attachList["+i+"].uploadPath' value='"+jobj.data("path")+"'>";
						str +="<input type='hidden' name='attachList["+i+"].fileType' value='"+jobj.data("type")+"'>";
					});
			console.log(str);
			formObj.append(str).submit();
			//alert(formObj.html());
		});
});

</script>
<!-- <script>
ClassicEditor
.create( document.querySelector( '#content-editor' ),{
	language : 'ko'
	})
</script> -->
   
<%@include file="../includes/footer.jsp"%>
