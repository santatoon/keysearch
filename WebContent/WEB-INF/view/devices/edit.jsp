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
	<h2>WAND 정보수정</h2>
	<hr />	
	<div class="span-14 append-10 last">
	<form:form modelAttribute="device">	
		<fieldset>
			<legend> WAND 정보 </legend>
			<p>
				<form:label path="id">아이디:</form:label><br/>
				<form:input path="id" size="20" maxlength="16" disabled="true" value="${device.id}" /> 
				<form:errors cssClass="error" path="id" />
			</p>
			<p>
				<form:label path="customerid">고객ID:</form:label><br/>
				<form:input path="customerid" size="20" maxlength="16" value="${device.customerid}" />
				<form:errors cssClass="error" path="customerid" />
			</p>
			<p>
				<label>상태:</label><br/>
				<form:select path="status" items="${statusList}" itemLabel="name" itemValue="value" />
			</p>
			<p>
				<form:label path="pushid">Push ID:</form:label><br/>
				<form:textarea path="pushid" value="${device.pushid}" maxlength="500"/> 
				<form:errors cssClass="error" path="pushid" />
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