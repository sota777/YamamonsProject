<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規会員登録</title>
<link href="/yamamons/resources/img/style.css" type="text/css" rel="stylesheet" />
<link href="/yamamons/resources/img/login.css" type="text/css" rel="stylesheet" />


</head>
<body style="background-image: url('/yamamons/resources/img/グレー2.jpg');" class="body">

	<jsp:include page="headerkari.jsp"/>
<a href="login" >
			<input type="submit" value=ログイン class="btn btn--green btn--emboss btn--cubic"></a><br>


		<div class="container">
    <p>新規登録</p>

    		<form:form modelAttribute="membersModel">

<div>
	<form:input path="name" size="20" placeholder="name" />
    <form:errors path="name" element="span" cssClass="errors" />
</div>
<div>
    <form:input path="address" placeholder="address"  />
    <form:errors path="address" element="span" cssClass="errors" />
</div>
<div>
    <form:input path="mail" placeholder="mail"  />
    <form:errors path="mail" element="span" cssClass="errors" />
</div>
<div>
    <form:password path="password" placeholder="Password" />
	<form:errors path="password" element="span" cssClass="errors" />
</div>
<div>
	<form:input path="phoneNumber"  size="15" placeholder="000-0000-0000" />
	 <form:errors path="phoneNumber" element="span" cssClass="errors" />
</div>
<div>
	<form:radiobutton path="credit" label="楽天" value="0"/>
	<form:radiobutton path="credit" label="JCB" value="1"/>
	<form:radiobutton path="credit" label="VISA" value="2"/>
	<form:radiobutton path="credit" label="MASTER" value="3"/>
	<form:errors path="credit" element="span" />
</div>

    <button type="submit">ragistration</button>

    <div class="form-row errors">
    	<c:out value="${message }"></c:out>
    </div>
    </form:form>

    <div>
	<jsp:include page="footerkari.jsp"/>
	</div>


    </div>

</body>
</html>

