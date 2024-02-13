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
	<table border="1">
		<tr>
			<th>商品画像</th>
			<th>商品名</th>
			<th>在庫数</th>
			<th>貸出数</th>
			<th>返却</th>
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
					 <td>
						<a href="rentalStatus">
							<input type="submit" value="返却" name="${itemList[i].itemNo }">
						</a>
					</td>

			</form:form>
		</c:forEach>
	</table>
	<a href="home">ホームへ </a>
</body>
</html>