<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>WAND �� ������ ���</title>
	<!-- Framework CSS -->
    <link rel="stylesheet" href="../blueprint/screen.css" type="text/css" media="screen, projection">
    <link rel="stylesheet" href="../blueprint/print.css" type="text/css" media="print">
    <!--[if lt IE 8]><link rel="stylesheet" href="../blueprint/ie.css" type="text/css" media="screen, projection"><![endif]-->
</head>
<body> 

<div class="container">
	<h2>WAND �� ������ ���</h2>
	<hr />
	<div class="span-12 append-12 last">
		<h4>WAND �� ������ �������Դϴ�.</h4>
	</div>
	<div class="span-12 append-12 last">
	<form:form modelAttribute="login">
		<fieldset>
			<p>
				<form:errors cssClass="error" path="" />
			</p>
			<legend> ������ �α��� </legend>
			<p>
				<form:label path="id">���̵�</form:label><br/> 
				<form:input path="id" size="20" maxlength="16"/>
				<form:errors cssClass="error" path="id" />
			</p>
			<p>
				<form:label path="password">��й�ȣ</form:label><br/>
				<form:password path="password" size="20" maxlength="16"/>
				<form:errors cssClass="error" path="password" />
			</p>
			<p>
				<input type="submit" value="  �α���   " />
			</p>
		</fieldset>
	</form:form>
	</div>
</div>

</body>
</html>