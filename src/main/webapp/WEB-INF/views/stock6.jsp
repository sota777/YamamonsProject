<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>在庫管理</title>
</head>
<body>
	<h1>在庫管理</h1>

	<table border="1">
		<tr>
			<th>商品画像</th>
			<th>商品名</th>
			<th>在庫数</th>
			<th>貸出状況</th>
		</tr>
		<c:forEach begin="1" end="${itemsList.size()}" step="1" var="i">
			<form:form modelAttribute="cModel,orderModel,orderList">
				<tr>
					<td><img
						src="resources/img/<c:out value="${itemsList[i].itemPicture }"  />"
						width="96" height="128" alt="${itemsList[i].itemName }"></td>
					<td><c:out value="${itemsList[i].itemName }" /></td>
					<td><c:out value="${itemsList[i].itemQuantity }" /></td>
					 <td><c:out value="${orderList[i]}" /></td>

			</form:form>
		</c:forEach>
	</table>
	<a href="home">ホームへ </a>
</body>
</html>