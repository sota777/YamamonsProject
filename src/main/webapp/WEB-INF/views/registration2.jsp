<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規会員登録</title>
</head>
<body>
	<div class="container">
	<jsp:include page="headerkari.jsp"/>
	<h1><c:out value="${headline }"/></h1>
		<form:form modelAttribute="membersModel">
	<div>
		<label>氏名</label>
		<form:input path="name" size="20" />
		<form:errors path="name" element="span" cssClass="errors" />
	</div>
	<div>
		<label>住所</label>
		<form:input path="address" size="30" />
		<form:errors path="address" element="span" cssClass="errors" />
	</div>
	<div>
		<label>携帯番号(ハイフン・半角数字)</label>
		<form:input path="phoneNumber" size="15" placeholder="000-0000-0000"/>
		<form:errors path="phoneNumber" element="span" cssClass="errors" />
	</div>

	<div>
		<label>決済方法選択</label>
		<form:radiobutton path="credit" label="楽天" value="rakuten"/>
		<form:radiobutton path="credit" label="JCB" value="jcb"/>
		<form:radiobutton path="credit" label="VISA" value="visa"/>
		<form:radiobutton path="credit" label="MASTER" value="master"/>
		<form:errors path="credit" element="span" />
	</div>
	<div>
		<p><input type="submit" value="登録する"></p>
	</div>
	<div class="form-row errors">
		<c:out value="${message }"/>
	</div>
	</form:form>
	<div>
	<jsp:include page="footerkari.jsp"/>
	</div>
</body>
</html>