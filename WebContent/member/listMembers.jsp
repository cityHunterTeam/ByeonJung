<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<c:choose>
	<c:when test='${msg=="addMember" }'>
		<script>
			window.onload=function(){
				alery("회원 등록");
			}
		</script>
	</c:when>
</c:choose>
<meta charset="UTF-8">
<title>회원 정보창 출력</title>
<style>
.cls1 {
	text-decoration: none;
}

.cls2 {
	text-align: center;
	font-size: 30px;
}

a {
	text-align: center;
}
</style>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<jsp:include page="../inc/header.jsp" />
</head>
<body>
	<p class="cls1" align="center">회원정보</p>
	<table align="center" border="1">
		<tr align="center" bgcolor="lighthreen">
			<td width="7%"><b>아이디</b></td>
			<td width="7%"><b>비밀번호</b></td>
			<td width="7%"><b>이름</b></td>
			<td width="7%"><b>생일</b></td>
			<td width="7%"><b>이메일</b></td>
			<td width="7%"><b>휴대전화</b></td>
			<td width="7%"><b>주소</b></td>
		</tr>

		<c:choose>
			<c:when test="${  membersList==null}">
				<tr>
					<td colspan="5"><b>등록된 회원이 없습니다.</b></td>
				</tr>
			</c:when>
			<c:when test="${membersList != null}">
				<c:forEach var="mem" items="${membersList }">
				<c:url var="url2" value="/mem/memberView.do">
					<c:param name="id" value="${mem.id}"/>
				</c:url>
					<tr align="center" onclick="location.href='${url2}'"> 
						<td>${mem.id }</td>
						<td>${mem.passwd }</td>
						<td>${mem.name }</td>
						<td>${mem.birth }</td>
						<td>${mem.email }</td>
						<td>${mem.phone }</td>
						<td>${mem.address }</td>
						<td><a href="delMembers.do?id=${mem.id}">삭제</a></td>
						<td><a href="memberView.do?id=${mem.id}">수정</a></td>
					</tr>
				</c:forEach>
			</c:when>
		</c:choose>
	</table>
	
		<c:url var="url1" value="/mem/modMemberForm.do">
				<c:param name="id" value="${mem.id}"/>
		</c:url>
</body>
<jsp:include page="../inc/footer.jsp" />
</html>