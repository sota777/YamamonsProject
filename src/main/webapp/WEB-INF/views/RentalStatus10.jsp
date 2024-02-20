<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>返却処理</title>
<link href="/yamamons/resources/img/style.css" type="text/css"
	rel="stylesheet" />
<style>
body {
	background-image: url("/yamamons/resources/img/雲.jpg");
	width: 100%;
}

table {
	width: 60%;
	margin-left: 20%;
	margin-right: 50%;
	border-collapse: collapse; /* オプション: セル間の境界線を折り畳む */
	text-align: center;
	background-image: url("/yamamons/resources/img/白ピンク背景.jpg");
}
.custom-font-size {
        font-size: 40px; /* お好みのサイズに変更してください */
    }
</style>
</head>
<body>

	<header>
		<a href="admin"> <input type="submit" value="戻る"
			class="btn btn--green btn--emboss btn--cubic"></a> <br> <br>
	</header>
	<div class="sample" >返却処理</div>
	<br>
	<p>
<span class="custom-font-size"><c:out value="${message}" /></span>
	</p>
	<form:form modelAttribute="checkboxForm">
		<br>
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
					<td><form:checkbox path="selectedOrderNos"
							value="${notReturnItem.orderNo}" /></td>
				</tr>

			</c:forEach>
		</table>
	<br>
		<input type="submit" value="返却処理"
			class="btn btn--green btn--emboss btn--cubic">
			<br>

	</form:form>

		<jsp:include page="footerkari.jsp"></jsp:include>
	<br>
</body>
</html>