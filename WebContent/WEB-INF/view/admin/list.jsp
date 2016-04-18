<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>	
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title></title>
	<!-- Framework CSS -->
    <link rel="stylesheet" href="../../blueprint/screen.css" type="text/css" media="screen, projection">
    <link rel="stylesheet" href="../../blueprint/print.css" type="text/css" media="print">
    <!--[if lt IE 8]><link rel="stylesheet" href="../../blueprint/ie.css" type="text/css" media="screen, projection"><![endif]-->
</head>
<body>  
<div id="header">
	<%@ include file= "../header.jsp" %>
</div>
<div class="container">
	<h2>관리자 목록</h2>
	<div class="prepend-6 span-8 append-8 last">
		<b>${currentUser.name}</b>님으로 로그인 하셨습니다.  <a href="../logout">[로그아웃]</a>
	</div>
	<hr />
	<div class="span-14 append-10 last">
		<table>
			<tr>
				<th>이름</th>
				<th>전화번호</th>
				<th>email</th>
			</tr>
			<c:forEach var="admin" items="${adminList}" >
			<tr>
				<td><a href="view/${admin.id}">${admin.name}</a></td>
				<td>${admin.phone}</td>
				<td>${admin.email}</td> 
			</tr>
			</c:forEach>
		</table>
	</div>
	
</div>

</body>
</html>