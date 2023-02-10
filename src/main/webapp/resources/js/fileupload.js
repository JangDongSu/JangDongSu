$(document).ready(function(){
	//fileupload - 2022-12-29
	var formObj = $("form[role='form']");
	var regex = new RegExp("(.*)\.(exe | sh | zip | alz)$");// | jpg
						
	/*(.*) : 임의의 문자가 0개 이상, \은 그냥 dot, exe또는 sh또는....
	어떤 문자로 시작하든지, 몇 글자이든 상관없다. 확장자가 exe, sh, zip, alz , jpg찾겠다. 
	(1.exe), (a.sh), (123.zip), (ab_1234.alz)..... */
					
	var maxSize = 5 * 1024 * 1024/*5242880*/; //5mb
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
					
	$("input[type='file']").change(
		function(e){
			alert("change");
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
			var uploadUL = $(".uploadResult ul");
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
});