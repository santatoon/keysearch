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
		<h2>WAND조회</h2>
		<a href="../edit/${device.id}">[정보수정]</a> <a href="../delete/${device.id}">[삭제]</a>
		<a href="../list">[돌아가기]</a>

		<hr />
		<table>
			<tr>
				<td class="span-2">장비ID:</td>
				<td class="span-10 value">${device.id}</td>
			</tr>
			<tr>
				<td class="span-2">고객ID:</td>
				<td class="span-10 value">${device.customerid}</td>
			</tr>
			<tr>
				<td class="span-2">상태:</td>
				<td class="span-10 value">${device.status.name}</td>
			</tr>
			<tr>
				<td class="span-2">생성 날짜:</td>
				<td class="span-10 value">${device.created}</td>
			</tr>
			<tr>
				<td class="span-2">수정 날짜:</td>
				<td class="span-10 value">${device.modified}</td>
			</tr>
		</table>
	</div>

</body>
</html>
