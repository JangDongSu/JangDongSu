<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="../includes/header.jsp" %>
<link rel="stylesheet" href="/resources/css/board-list.css" />
<style>
/*.modal{background-color:rgba(0,0,0,0.5); z-index:15;}
.modal-backdrop{z-index:4}
.modal-content{z-index}*/

</style>
<!-- subpage banner -->
<div class="subpage_banner wrap z-index relative">
	<div class="container">
		<h1 class="page-header">게시판</h1>
	</div>
</div>
<!-- subpage banner -->
<div class="wrap wrap1 wrap2 z-index relative">
	<div class="container">            
            <div class="bo-table">
                <table class="table table-hover">
                    <thead>
						<tr>
							<th>No.</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일</th>
							<th>조회수</th>
						</tr>
					</thead>
					<c:forEach items="${list}" var="board">
						<tr>
							<td><c:out value="${board.bno}" /></td>
							<td><a href='<c:out value="${board.bno}"/>' class="move" >
								<c:out value="${board.title}"/> <b>[<c:out value="${board.replyCnt}"/>]</b></a>
							</td>
							<td><c:out value="${board.writer}" /></td>
							<td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.regdate}" /></td>
							<td><c:out value="${board.viewCount}" /></td>
						</tr>
					</c:forEach>
                </table>
            </div>
            <div class="searchform">
                <form action="/board/list" method="get" id="searchForm">
                    <select name="type" class="select-style">
                        <option value="" <c:out value="${pageMaker.criteria.type == null?'selected' : ''}"/>>--</option>
                        <option value="T" <c:out value="${pageMaker.criteria.type eq'T'?'selected' : ''}"/>>제목</option>
                        <option value="C" <c:out value="${pageMaker.criteria.type eq'C'?'selected' : ''}"/>>내용</option>
                        <option value="W" <c:out value="${pageMaker.criteria.type eq'W'?'selected' : ''}"/>>작성자</option>
                        <option value="TC" <c:out value="${pageMaker.criteria.type eq'TC'?'selected' : ''}"/>>제목 or 내용</option>
                        <option value="TW" <c:out value="${pageMaker.criteria.type eq'TW'?'selected' : ''}"/>>제목 or 작성자</option>
                        <option value="TWC" <c:out value="${pageMaker.criteria.type eq'TWC'?'selected' : ''}"/>>제목 or 내용 or 작성자</option>
                    </select>
                    <input type="text" class="select-style" name="keyword" value="<c:out value="${pageMaker.criteria.keyword}"/>"/>
                    <input type="hidden" name="pageNum" value="<c:out value="${pageMaker.criteria.pageNum}"/>"/>
                    <input type="hidden" name="amount" value="<c:out value="${pageMaker.criteria.amount}"/>"/>
                    <button class="button">검색</button>
            </form>
            <button id="regBtn" type="button" class="button" style="float:right;">
						게시물 작성
					</button>
        </div>
            <div class="page-wrap">
                <ul class="page">
                    <c:if test="${pageMaker.prev}">
                        <li class="paginate_button previous">
                            <a href="${pageMaker.startPage -1}">Prev</a>
                        </li>
                    </c:if>
                    <c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
                        <li class='paginate_button ${pageMaker.criteria.pageNum == num ? "active":""}'>
                            <a href="${num}">${num}</a>
                        </li>
                    </c:forEach>
                    <c:if test="${pageMaker.next}">
                        <li class="paginate_button next">
                            <a href="${pageMaker.endPage+1}">Next</a>
                        </li>
                    </c:if>
                </ul>
            </div>
				<!-- end pagination -->
				<!-- 특정 page 번호 클릭시 특정 페이지 번호로 넘어가는 링크 구현  / 시작 / -->
				<form action="/board/list" method="get" id="actionForm">
					<input type="hidden" name="pageNum" value='${pageMaker.criteria.pageNum}' />
					<input type="hidden" name="amount" value='${pageMaker.criteria.amount}' />
					<input type="hidden" name="type" value='${pageMaker.criteria.type}' />
					<input type="hidden" name="keyword" value='${pageMaker.criteria.keyword}' />
				</form>

</div>
<!-- /container -->
</div>
<!-- /.wrap -->
<script type="text/javascript">
    $(document).ready(function() {
    	var result = '<c:out value="${result}"/>';
    	checkModal(result);
    	history.replaceState({}, null, null);
    	function checkModal(result) {
    		if (result === '' || history.state) {
    			return;
    		}
    		if (parseInt(result) > 0) {
    			$(".modal-body").html("게시글 " + parseInt(result) + 
    					" 번이 등록되었습니다.");
    		}
    		$("#myModal").modal("show");
    	}
    	$("#regBtn").on("click", function() {
    		self.location = "/board/register";
    	});
    	
    	//특정 page번호 클릭시 특정 페이지로 넘어가는 링크 구현 - 제이쿼리
    	var actionForm = $("#actionForm");	//actionform 변수선언
    	$(".paginate_button a").on("click", function(e){
    		//alert("pagenum");
    		e.preventDefault();
    		actionForm.find("input[name='pageNum']").val($(this).attr("href"));
    		actionForm.attr("action", "/board/list");
    		actionForm.submit();
    	});
    	
    	//게시물 클릭시 읽기페이지로 이동
    	$(".move").on("click", function(e){
    		e.preventDefault();
    		if(actionForm.find("input[name='bno']").val()!=''){
    			actionForm.find("input[name='bno']").remove();
    		}
    		actionForm.append("<input type='hidden' name='bno' value='" + $(this).attr("href") + "'>");
    		actionForm.attr("action", "/board/get");
    		actionForm.submit();
    	});
    	
    	//검색 & 예외처리 - (데이터 유효성 검사 형태로...)
    	 var searchForm = $("#searchForm");	//searchForm = 변수 선언(form id = searchForm)
     	$("#searchForm button").on("click", function(e){	
     		
     		//검색 종류 선택 안하고 키워드 입력해서 검색할때 나오는 기능.. 
     		if(!searchForm.find("option:selected").val()){
                 alert("검색종류를 선택하세요. ");
                 return false;
             }
     		//검색 종류 선택 하고 키워드 입력 없이 검색할때 나오는 기능.. 
             if(!searchForm.find("input[name='keyword']").val()){
                 alert("키워드를 입력하세요. ");
                 return false;
             }
     		
             searchForm.find("input[name='pageNum']").val("1");	//검색결과 - 첫페이지가 1페이지로 지정.. 
             e.preventDefault();
             searchForm.submit();
     	});
    	 
    	 /*var count = board.replyCnt;
    	 if(count > 0){
    		 $(".move b").css("display", "block");
    	 }else{
    		 $(".move b").css("display", "none");
    	 }*/
    });
</script>

<%@include file="../includes/footer.jsp" %>
