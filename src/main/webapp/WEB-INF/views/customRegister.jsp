<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="includes/header.jsp" %>
<link rel="stylesheet" href="/resources/css/get.css" />
<!-- 다음주소 api -->
<script src="http://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<style>
/*.id-ok, .id-already{display:none;}*/
.red{color:red;}
.blue{color:blue;}
.join_wrapper .article-reg-top{border-bottom:1px solid #d7d7d7;}
.join_wrapper .article-reg-top:nth-child(2){border-top:0;}
.join_wrapper .article-reg-top:nth-child(4) .field-style:nth-child(1){padding:43px 0 51px 15px}
.join_wrapper .article-reg-top:nth-child(5){border-bottom:0;}
.join_wrapper .article-reg-top:nth-child(6){border-bottom:0;}
.join_wrapper .article-reg-top:nth-child(7){border-bottom:2px;}

/*responsive*/
@media(max-width:1210px){
	.bottom_button{width:20%;}
	.button{width:48%; margin-right:2%;}
	.button:last-child{margin-right:0;}
}
@media(max-width:900px){
	#zipCode{width:50%;}
	#address{width:68%;}
	#addressDetail{width:68%;}
}
@media(max-width:790px){
	.bottom_button{width:35%;}
}
@media(max-width:470px){
	.bottom_button{width:50%;}
	.field1{width:100%;}
	.field2{width:100%;}
	.form-stye{width:100%;}
	#zipCode{width:70%;}
	#address{width:100%;}
	#addressDetail{width:100%;}
	.field-style{padding-left:0;}
	.article-layout label{padding-left:4%;}
	.join_wrapper .article-reg-top:nth-child(4) .field-style:nth-child(1){padding:15px 0;}
}
@media(max-width:414px){
	#zipCode{width:60%;}
}
@media(max-width:360px){
	#zipCode{width:55%;}
	.bottom_button{width:80%;}
}
@media(max-width:305px){
	#zipCode{width:50%;}
}

</style>

<!-- subpage banner -->
<div class="subpage_banner wrap z-index relative">
	<div class="container">
		<h1 class="page-header">회원가입</h1>
	</div>
</div>
<div class="wrap wrap1">
	<div class="join_wrapper">
		<div class="container">
			<span><span class="red">*</span>은 필수 입력 항목입니다. </span>
			<div class="article-layout">
				<form role="form" action="/customRegister" method="post">
					<fieldset>
						<div class="article-top">
							<div class="article-reg-top">
								<div class="field1 get-th field-style">
									<label>이름</label><span class="red">*</span>
								</div>
								<div class="field2 get-td field-style">
									<input type="text" class="form-stye" id="userName" name="userName" required="required">
								</div>
							</div>
							<div class="article-reg-top">
								<div class="field1 get-th field-style">
									<label>아이디</label><span class="red">*</span>
								</div>
								<div class="field2 get-td field-style">
									<input class="form-stye" name="userId" id="userId"  type="text" required="required">
									<!-- 아이디 중복 체크 -->
									<!-- <button type="button" id="idCheck" onclick="fn_idCheck();" value="N">아이디 중복 확인</button>
									<p id="idckeckMessage"></p> -->
								</div>
							</div>
							<div class="article-reg-top">
								<div class="field1 get-th field-style">
									<label>비밀번호</label><span class="red">*</span>
								</div>
								<div class="field2 get-td field-style">
									<input class="form-stye" name="userPw" id="userPw" type="password" value="" required="required">
								</div>
							</div>
							<div class="article-reg-top">
								<div class="field1 get-th field-style">
									<label>주소</label><span class="red">*</span>
								</div>
								<div class="field2 get-td field-style">
									<input class="address-input readonly" placeholder="우편번호" id="zipCode" name="zipCode" type="text" required="required" readonly/>
									<button type="button" onclick="execDaumPostcode();">우편번호 찾기</button><br />
									<input class="address-input readonly" placeholder="회원 주소" id="address" name="address" type="text" readonly/> <br />
									<input class="address-input" placeholder="상세주소" id="addressDetail" name="addressDetail" type="text" />
								</div>
							</div>
							<div class="article-reg-top">
								<div class="field1 get-th field-style">
									<label>전화번호</label><span class="red">*</span>
								</div>
								<div class="field2 get-td field-style">
									<input class="form-stye" placeholder="ex)010-1234-5678" id="phone" name="phone" type="text" required>
								</div>
							</div>
							<div class="article-reg-top">
								<div class="field1 get-th field-style">
									<label>이메일</label><span class="red">*</span>
								</div>
								<div class="field2 get-td field-style">
									<input class="form-stye" placeholder="ex)id@mail.com" id="email" name="email" type="text" required>
								</div>
							</div>
							<div class="article-reg-top">
								<div class="field1 get-th field-style">
									<label>성별</label>
								</div>
								<div class="field2 get-td field-style">
									<input name="gender" type="radio" value="남" id="gender">&nbsp;&nbsp;남&nbsp;&nbsp; 
									<input name="gender" type="radio" value="여" id="gender">&nbsp;&nbsp;여
								</div>
							</div>
						</div>
						<div class="bottom_button">
							<button type="submit" id="customRegister" class="button button-comm">가입하기</button>
							<button type="reset" class="button btn-warning">재작성</button>
						</div>
					</fieldset>
					<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token}" />
				</form>
			</div>
		</div>
	</div>
</div>
	<!-- <script>
		function fn_idCheck(){
			$.ajax({
				url:'/idCheck',
				type:'post',
				dataType:"json",
				data:{"userId":$("#userId").val()},
				success:function(data){
// 					if(data == 1){
// 						alert("이미 사용중인 아이디입니다. ");
// 					}else if(data == 0){
// 						alert("사용 가능한 아이디입니다. ");
// 					}
					console.log(data);
				}
				error : function(error) {
					console.log(error);
					alert("이미 사용중인 아이디입니다");
				}
		})
	}
	</script>-->
	<script>	
		function execDaumPostcode() {
			new daum.Postcode({
				oncomplete : function(data) {
				/* 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
				 각 주소의 노출 규칙에 따라 주소를 조합한다.*/

				// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.

				var addr = ''; // 주소 변수
				var extraAddr = ''; // 참고항목 변수

				//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
				if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
					addr = data.roadAddress;
				}else { // 사용자가 지번 주소를 선택했을 경우(J)
					addr = data.jibunAddress;
				}

				// 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.

				if (data.userSelectedType === 'R') {
				/* 법정동명이 있을 경우 추가한다. (법정리는 제외)
				 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.*/
				if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
					extraAddr += data.bname;
					}
				// 건물명이 있고, 공동주택일 경우 추가한다.
				if (data.buildingName !== '' && data.apartment === 'Y') {
					extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
					}
				// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
				if (extraAddr !== '') {
					extraAddr = ' (' + extraAddr + ')';
					}
				// 조합된 참고항목을 해당 필드에 넣는다.
				document.getElementById("addressDetail").value = extraAddr;
				}else{
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
	<script src="/resources/vendor/jquery/jquery.min.js"></script>
	<script src="/resources/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script src="/resources/vendor/metisMenu/metisMenu.min.js"></script>
	<script src="/resources/dist/js/sb-admin-2.js"></script>
	<script>
		
		$("#customRegister").on("click", function(e){
				e.preventDefault();
				var userName = document.getElementById("userName");
				var userId = document.getElementById("userId");
				var userPw = document.getElementById("userPw");
				var zipCode = document.getElementById("zipCode");
				var phone = document.getElementById("phone");
				var email = document.getElementById("email");
				var gender = document.getElementById("gender");
				var idCheckVal = $("#idCkeck").val();
				
				/*if(idCheckVal == "N"){
					alert("중복확인 버튼 눌러주세요. ");
				}else if(idCheckVal == "Y"){
					
				}*/
				
				if(userName.value == ""){
					alert("이름을 입력해 주세요. ");
					userName.focus();
					return;
				}
				if(userId.value == ""){
					alert("아이디를 입력해 주세요. ");
					userId.focus();
					return;
				}
				if(userPw.value == ""){
					alert("비밀번호를 입력해 주세요. ");
					userPw.focus();
					return;
				}
				if(zipCode.value == ""){
					alert("주소를 입력해주세요. ");
					return;
				}
				if(phone.value == ""){
					alert("전화번호를 입력하세요. ");
					phone.focus();
					return false;
				}
				if(email.value == ""){
					alert("이메일 주소를 입력해 주세요. ");
					email.focus();
					return;
				}
				/*if(gender.value == "남" ||  gender.value == "여"){
					alert("성별을 선택해주세요.  ");
					return;
				}*/
				
				alert("가입 축하드립니다. ");
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