<%@page import="admin.adminVO"%>
<%@page import="admin.adminDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
table {
	font-family: arial, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

td, th {
	border: 1px solid #dddddd;
	text-align: left;
	padding: 8px;
}

tr:nth-child(even) {
	background-color: #dddddd;
}
</style>
</head>
<body>
	<jsp:include page="../member/header.jsp" />

	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<h2 align="center">관리자 페이지</h2>
	<p align="center">관리자 페이지 입니다.</p>
	<table id="adminTable">
		<%
			String pageNum = request.getParameter("pageNum");
			if (pageNum == null) {
				pageNum = "0";
			}
			int currentPage = Integer.parseInt(pageNum);

			adminDAO aDao = new adminDAO();
			int adminSize = aDao.adminSizeCheck();
		%>
		<tr>
			<td colspan="4">공지글 수 : <%=adminSize%>
			</td>
		</tr>
		<tr>
			<th class="tno">No.</th>
			<th class="ttitle">Title</th>
			<th class="twrite">Writer</th>
			<th class="tdate">Date</th>
			<th class="tread">Read</th>
		</tr>
		<%
					//currentPage = 전달받는 페이지 쪽 수 
					//noticeSize = 공지사항 모든 글 수
					//size = 페이지에 출력할 글 수
					//transPage = 게시판 총 글 수의 size로 나눈 값 (SQL 검색 시 시작 번호를 지정할 값)
					int size = 10;
					int transPage = 0;
					if(currentPage > 1){
						transPage = (adminSize / size);
						for(int i = 0 ; i < transPage ; i++){
							currentPage += 9;
						}
					}
					else{
						currentPage = (currentPage > 0 ? +10 : 0) ;
					}
					ArrayList<NoticeBean> noticeArr = aDao.adminListCheck(currentPage , size);	
				if(adminArr.size() > 0){ 
					for ( int i = 0 ; i < adminArr.size() ; i++){
					adminVO aVO = adminArr.get(i);
				%>
	</table>

	<jsp:include page="../member/footer.jsp" />
</body>
</html>
