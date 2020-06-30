<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정</title>
<style>
.cls1 {
	text-decoration: none;
}

.cls2 {
	text-align: center;
	font-size: 30px;
}
a{
	text-align: center;

}


</style>
 <c:set var="contextPath"  value="${pageContext.request.contextPath}"/>    

<jsp:include page="../inc/header.jsp"/>

</head>
<body>
	<h1 class="cls1">회원 정보 수정창</h1>
<form method="post" action="${contextPath }/member/modMember.do? id=${memInfo.id}">
	<table align="center">
		<tr>
			<td width="200"><p align="right">아이디</td>
			<td width="400"><input type="text" name="id" value="${memInfo.id }" disabled></td>
		</tr>
		<tr>
			<td width="200"><p align="right">비밀번호</td>
			<td width="400"><input type="password" name="passwd" value="${memInfo.passwd }">
		</tr>
		<tr>
			<td width="200"><p align="right">이름</td>
			<td width="400"><input type="text" name="name" value="${memInfo.passwd }">
		</tr>
		<tr>
			<td width="200"><p align="right">주소</td>
			<td width="400"><input type="text" name="address" value="${memInfo.address }">
		</tr>
		<tr>
			<td width="200"><p align="right">생일</td>
			<td width="400"><input type="text" name="birth" value="${memInfo.birth }">
		</tr>
		<tr>
			<td width="200"><p align="right">휴대전화</td>
			<td width="400"><input type="text" name="phone" value="${memInfo.phone }">
		</tr>
		<tr>
			<td width="200"><p align="right">이메일</td>
			<td width="400"><input type="text" name="email" value="${memInfo.email }">
		</tr>
	</table>
</form>
</body>
</html>