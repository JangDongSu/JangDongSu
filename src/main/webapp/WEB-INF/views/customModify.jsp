<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<!-- 임시 적용(기본적 include - header.jsp) / 시작 /  -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>SB Admin 2 - Bootstrap Admin Theme</title>
    <!-- Bootstrap Core CSS -->
    <link href="/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="/resources/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">
    
    <!-- Custom CSS -->
    <link href="/resources/dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="/resources/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="/resources/css/responsive.css" />
    
    <!-- 다음주소 api -->
	<script src="http://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</head>
<body>
<!-- 임시 적용(기본적 include - header.jsp) / 끝 /  -->
	<div class="container">
		<div class="row">
			<div class="col-md4 col-md-offser-4">
				<div class="login-panel panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">회원정보수정</h3>
					</div>
					<div class="panel-body">
						<!--  c:if 추가-->
						<form role="form" method="post" action="/customModify">
							<fieldset>
								<div class="form-group">
									<input class="form-control" placeholder="이름" name="userName" type="text" value="<c:out value='${member.userName}'/>" readonly>
								</div>
								<div class="form-group">
									<input class="form-control" placeholder="아이디" name="userId" type="text" value="<c:out value='${member.userId}'/>" readonly>
								</div>
								<div class="form-group">
									<input class="form-control" placeholder="비밀번호" name="userPw" type="password" value="" required>
								</div>
								<div class="form-group">
									<input placeholder="우편번호"  id="zipCode" name="zipCode" type="text" value="<c:out value='${member.zipCode}'/>" readonly />
									<button type="button" onclick="execDaumPostcode();">우편번호 찾기</button><br>
									<input placeholder="회원 주소"  id="address" name="address" type="text" value="<c:out value='${member.address}'/>" readonly/><br>
									<input placeholder="상세주소" id="addressDetail"name="addressDetail" value="<c:out value='${member.addressDetail}'/>" type="text" />
								</div>
								<div class="form-group">
									<input class="form-control" placeholder="전화번호" name="phone" value="<c:out value='${member.phone}'/>" type="text">
								</div>
								<div class="form-group">
									<input class="form-control" placeholder="이메일" name="email" value="<c:out value='${member.email}'/>" type="text">
								</div>
								<div class="form-group">
									<button type="submit" class="btn btn-default">수정하기</button>
									<button type="reset" class="btn btn-warning">재작성</button>
								</div>
							</fieldset>
							<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token}" />
						</form>
						
					</div>
					<script>
						function execDaumPostcode() {
        					new daum.Postcode({
            					oncomplete: function(data) {
	                				// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
	                				
	                				// 각 주소의 노출 규칙에 따라 주소를 조합한다.
	                				
	                				// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	                				
	                				var addr = ''; // 주소 변수
	                				var extraAddr = ''; // 참고항목 변수
	                				
	                				//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
	                				if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
	                					addr = data.roadAddress;
	                				} else { // 사용자가 지번 주소를 선택했을 경우(J)
	                				addr = data.jibunAddress;
	                				}
	                				
	                				// 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
	                				
	                				if(data.userSelectedType === 'R'){
	                					/* 법정동명이 있을 경우 추가한다. (법정리는 제외)
	                					 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.*/
	                					 if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
	                						 extraAddr += data.bname;
	                						 }
	                					// 건물명이 있고, 공동주택일 경우 추가한다.
	                					if(data.buildingName !== '' && data.apartment === 'Y'){
	                						extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	                						}
	                					// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
	                					if(extraAddr !== ''){
	                						extraAddr = ' (' + extraAddr + ')';
	                						}
	                					// 조합된 참고항목을 해당 필드에 넣는다.
	                					document.getElementById("addressDetail").value = extraAddr;
	                					}else {
	                						document.getElementById("addressDetail").value = '';
	                					}
	                				// 우편번호와 주소 정보를 해당 필드에 넣는다.
	                				document.getElementById('zipCode').value = data.zonecode;
	                				document.getElementById("address").value = addr;
	                				
	                				// 커서를 상세주소 필드로 이동한다.
	                				document.getElementById("addressDetail").focus();
	                				}
        					}).open();
        				}
					</script>
				</div>
			</div>
		</div>
	</div>
	<script src="/resources/vendor/jquery/jquery.min.js"></script>
	<script src="/resources/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script src="/resources/vendor/metisMenu/metisMenu.min.js"></script>
	<script src="/resources/dist/js/sb-admin-2.js"></script>
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
	<script>
		function check(){
			if(document.form.userPw.value ==""){
				alert("비밀번호를 입력해주세요. ");
				document.form.userPw.focus();
				return false;
			}
		}
	</script>
</body>
</html>