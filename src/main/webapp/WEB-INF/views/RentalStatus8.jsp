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
table {
	width: 60%;
	margin-left: 20%;
	margin-right: 50%;
	border-collapse: collapse; /* オプション: セル間の境界線を折り畳む */
	text-align: center;
	background-image: url("/yamamons/resources/img/白ピンク背景.jpg");
}
</style>
</head>
<body style="background-image: url('/yamamons/resources/img/雲.jpg');">

	<header>
		<a href="admin"> <input type="submit" value="戻る"
			class="btn btn--green btn--emboss btn--cubic"></a>
	</header>
	<br>
	<div class="sample">返却処理</div>
	<br>

	<table border="1">
		<tr>
			<th>商品画像</th>
			<th>商品名</th>
			<th>在庫数</th>
			<th>貸出数</th>
			<th>返却</th>
		</tr>
		<c:forEach begin="1" end="${itemsList.size()}" step="1" var="i">
			<form:form modelAttribute="adminModel">
				<tr>
					<td><img
						src="resources/img/<c:out value="${itemsList[i].itemPicture }"  />"
						width="96" height="128" alt="${itemsList[i].itemName }"></td>
					<td><c:out value="${itemsList[i].itemName }" /></td>
					<td><c:out value="${itemsList[i].itemQuantity }" /></td>
					<td><c:out value="${orderList[i]}" /></td>
					<td><form:radiobutton path="itemNunber" value="uoo" /></td>
			</form:form>
		</c:forEach>
		<tr>
			<td colspan="5"><br>
				<form method="post">
					<button type="submit" class="btn btn--green btn--emboss btn--cubic"
						style="font-size: 30px;">送信</button><br>
				</form></td>
		</tr>
	</table>
	<jsp:include page="footerkari.jsp" />

</body>
</html>