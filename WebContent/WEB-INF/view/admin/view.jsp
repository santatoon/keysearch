<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title></title>
<!-- Framework CSS -->
<link rel="stylesheet" href="../../../blueprint/screen.css"
	type="text/css" media="screen, projection">
<link rel="stylesheet" href="../../../blueprint/print.css"
	type="text/css" media="print">
<!--[if lt IE 8]><link rel="stylesheet" href="../../../blueprint/ie.css" type="text/css" media="screen, projection"><![endif]-->
</head>
<body>
<div id="header">
	<%@ include file= "../header.jsp" %>
</div>
	<div class="container">
		<h2>관리자조회</h2>
		<a href="../edit/${admin.id}">[정보수정]</a> <a href="../delete/${admin.id}">[관리자삭제]</a>
		<a href="../list">[돌아가기]</a>

		<hr />
		<table>
			<tr>
				<td class="span-2">이름:</td>
				<td class="span-10 value">${admin.name}</td>
			</tr>
			<tr>
				<td class="span-2">아이디:</td>
				<td class="span-10 value">${admin.id}</td>
			</tr>
			<tr>
				<td class="span-2">전화번호:</td>
				<td class="span-10 value">${admin.phone}</td>
			</tr>
			<tr>
				<td class="span-2">email:</td>
				<td class="span-10 value">${admin.email}</td>
			</tr>
		</table>
	</div>

</body>
</html>
