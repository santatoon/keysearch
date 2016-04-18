<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title></title>
	<!-- Framework CSS -->
    <link rel="stylesheet" href="../../blueprint/screen.css" type="text/css" media="screen, projection">
    <link rel="stylesheet" href="../../blueprint/print.css" type="text/css" media="print">
    <!--[if lt IE 8]><link rel="stylesheet" href="../blueprint/ie.css" type="text/css" media="screen, projection"><![endif]-->
</head>
<body> 
<div id="header">
	<%@ include file= "../header.jsp" %>
</div>
<div class="container">
	<h2>관리자 등록</h2>
	<hr />	
	<div class="span-14 append-10 last">
	<form:form modelAttribute="admin">	
		<fieldset>
			<legend> 관리자 정보 </legend>
			<p>
				<form:label path="id">아이디:</form:label><br/>
				<form:input path="id" size="20" maxlength="16" />
				<form:errors cssClass="error" path="id" />
			</p>
			<p>
				<form:label path="password">비밀번호:</form:label><br/>
				<form:password path="password" showPassword="true" size="20" maxlength="16" />
				<form:errors cssClass="error" path="password" />
			</p>
			<p>
				<form:label path="name">이름:</form:label><br/>
				<form:input path="name" size="20" maxlength="16" /> 
				<form:errors cssClass="error" path="name" />
			</p>
			<p>
				<form:label path="phone">휴대폰:</form:label><br/>
				<form:input path="phone" size="20" maxlength="16" /> 
				<form:errors cssClass="error" path="phone" />
			</p>
			<p>
				<form:label path="email">email:</form:label><br/>
				<form:input path="email" size="30" maxlength="30" /> 
				<form:errors cssClass="error" path="email" />
			</p>
			<p>
				<input type="submit" value="  등록   " />
			</p>
		</fieldset>
	</form:form>
	</div>
</div>

</body>
</html>