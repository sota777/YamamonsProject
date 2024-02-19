<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>返却処理</title>
</head>
<body>
	<h1>未返却注文一覧</h1>

	<p><c:out value="${message }"/></p>
	<form:form modelAttribute="checkboxForm">
	<table border="1">
	<tr>
		<th>顧客Id</th>
		<th>顧客名</th>
		<th>商品名</th>
		<th>商品画像</th>
		<th>レンタル日</th>
		<th>返却</th>
	</tr>


	<c:forEach var="notReturnItem" items="${notReturnItems }">
		<tr>
			<td><c:out value="${notReturnItem.customerId }" /></td>
			<td><c:out value="${notReturnItem.customerName }" /></td>
			<td><c:out value="${notReturnItem.itemName }" /></td>
			<td><img
				src="resources/img/<c:out value="${notReturnItem.itemPicture }"  />"
				width="96" height="128" alt="${notReturnItem.itemName }"></td>
			<td><c:out value="${notReturnItem.orderDate }" /></td>
			<td> <form:checkbox path="selectedOrderNos" value="${notReturnItem.orderNo}" /></td>
		</tr>

	</c:forEach>
	</table>

	<input type="submit" value="返却処理">
	</form:form>


</body>
</html>