<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="includes/header.jsp" %>
<!-- content -->
<div class="admin-content">
	<p>관리자 페이지</p>
	<p>/admin/index.jsp</p>
	<table border="1">
		<thead>
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<!-- <th colspan="2">주소</th> -->
				<th>주소</th>
				<th>이메일</th>
				<th>전화번호</th>
				<!-- <th>권한</th> -->
			</tr>
		</thead>
		<tbody>
			<c:forEach var="member" items="${member}">
				<tr>
					<td>${member.userId}</td>
					<td>${member.userName}</td>
					<%--<td colspan="2">${member.address}&nbsp; ${member.addressDetail}</td>--%>
					<td>${member.address}</td>
					<td>${member.email}</td>
					<td>${member.phone}</td>
					<%--<c:forEach var="auth" items="${auth}">
						<td>${auth.auth}</td>
					</c:forEach>--%> 
				</tr>
				</c:forEach> 
		</tbody>
	</table>
	</div>
	<!-- content -->
<%@include file="includes/footer.jsp" %>