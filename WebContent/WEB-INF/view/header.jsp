<%@ page language="java" contentType="text/html; charset=EUC-KR"
     pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>WAND 앱 관리자 모드</title>
</head>
<body>
<table border=1 align="center" style="border-collapse:collapse" bordercolor="black"  width="70%">

	<tr>
		<td colspan="4" align="center"><img src="<%=request.getContextPath() %>/images/wand_ci.jpg" name=wand_ci align="middle"></td>
	</tr>
	<tr>
		<td align="center"><a href="<%=request.getContextPath() %>/server/customers/list">고객 관리</a></td>
		<td align="center"><a href="<%=request.getContextPath() %>/server/devices/list">WAND 관리</a></td>
		<td align="center"><a href="<%=request.getContextPath() %>/server/admin/list">관리자 관리</a></td>
	</tr>
	<tr>
		<td align="center"><a href="<%=request.getContextPath() %>/server/customers/list"> - 고객 목록보기</a></td>
		<td align="center"><a href="<%=request.getContextPath() %>/server/devices/list"> - WAND 목록보기</a></td>
		<td align="center"><a href="<%=request.getContextPath() %>/server/admin/list"> - 관리자 목록보기</a></td>
	</tr>
	<tr>
		<td align="center"><a href="<%=request.getContextPath() %>/server/customers/register"> - 고객 추가하기</a></td>
		<td align="center"><a href="<%=request.getContextPath() %>/server/devices/register"> - WAND 추가하기</a></td>
		<td align="center"><a href="<%=request.getContextPath() %>/server/admin/register"> - 관리자 추가하기</a></td>
	</tr>
	
</table>
<br><br><br>

</body>
</html>