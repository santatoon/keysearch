<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title></title>
	<!-- Framework CSS -->
    <link rel="stylesheet" href="../../../blueprint/screen.css" type="text/css" media="screen, projection">
    <link rel="stylesheet" href="../../../blueprint/print.css" type="text/css" media="print">
    <!--[if lt IE 8]><link rel="stylesheet" href="../../../blueprint/ie.css" type="text/css" media="screen, projection"><![endif]-->
</head>
<body> 
<div id="header">
	<%@ include file= "../header.jsp" %>
</div>
<div class="container">
	<h2>고객 정보수정</h2>
	<hr />	
	<div class="span-14 append-10 last">
	<form:form modelAttribute="customer">	
		<fieldset>
			<legend> 고객 정보 </legend>
			<p>
				<form:label path="id">아이디:</form:label><br/>
				<form:input path="id" size="20" maxlength="16" disabled="true" value="${customer.id}" /> 
				<form:errors cssClass="error" path="id" />
			</p>
			<p>
				<form:label path="name">이름:</form:label><br/>
				<form:input path="name" size="20" maxlength="16" value="${customer.name}" />
				<form:errors cssClass="error" path="name" />
			</p>
			<p>
				<form:label path="phone">휴대폰:</form:label><br/>
				<form:input path="phone" size="20" maxlength="16" value="${customer.phone}" />
				<form:errors cssClass="error" path="phone" />
			</p>
			<p>
				<form:label path="ref">REF:</form:label><br/>
				<form:textarea path="ref" value="${customer.ref}" maxlength="500"/> 
				<form:errors cssClass="error" path="ref" />
			</p>
			<p>
				<input type="submit" value="  수정   " />
			</p>
		</fieldset>
	</form:form>
	</div>
</div>

</body>
</html>