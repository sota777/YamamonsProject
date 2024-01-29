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
	<h1>会員登録</h1>
		<form:form modelAttribute="validationModel">
	<div>
		<label>氏名</label>
		<form:input path="name" size="20" />
		<form:errors path="name" element="span" cssClass="errors" />
	</div>
	<div>
		<label>住所</label>
		<form:input path="email" size="30" />
		<form:errors path="email" element="span" cssClass="errors" />
	</div>
	<div>
		<label>携帯番号(ハイフンなし・半角数字)</label>
		<form:input path="phoneNumber" size="15" />
		<form:errors path="phoneNumber" element="span" cssClass="errors" />
	</div>
	<div>
		<label>生年月日</label>
				<form:select path="birthYear" items="${years}" itemLabel="label" itemValue="data" />&nbsp;年
					<form:select path="birthMonth" items="${months}" itemLabel="label" itemValue="data" />&nbsp;月
					<form:select path="birthDay" items="${days}" itemLabel="label" itemValue="data" />&nbsp;日
	</div>
	<div>
		<label>決済方法選択</label>
		<form:select path="payment" items="${paymentMap }" multipe="false"/>
		<form:errors path="payment" element="span" />
	</div>
	<div>
		<p><input type="submit" value="登録する"></p>
	</div>
	</form:form>
	</div>
</body>
</html>