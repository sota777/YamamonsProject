<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>在庫管理</title>
<link href="/yamamons/resources/img/style.css" type="text/css"
	rel="stylesheet" />
</head>
<style>
table {
	width: 60%;
	margin-left: 20%;
	margin-right: 50%;
	border-collapse: collapse; /* オプション: セル間の境界線を折り畳む */
	text-align: center;
	background-image: url("/yamamons/resources/img/白ピンク背景.jpg");
}

.text-right {
	text-align: right;
	margin-right: 350px; /* 調整したい余白の幅を指定 */
}
</style>
<body style="background-image: url('/yamamons/resources/img/雲.jpg');"
	class="body">
	<jsp:include page="headerkari.jsp"></jsp:include>
	<br>
	<div class="sample">在庫管理</div>
	<p>
	<div class="text-right">
		<a href="newItem"> <input type="submit" value="作品追加"
			class="btn btn--green btn--emboss btn--cubic">
		</a>
	</div>
	<p>
	<div class="text-right">
		<a href="rentalStatus"> <input type="submit" value="返却処理"
			class="btn btn--green btn--emboss btn--cubic">
		</a>
	</div>
	<br>
	<br>
	<table border="1">
		<tr>
			<th>商品画像</th>
			<th>商品名</th>
			<th>在庫数</th>
			<th>貸出数</th>
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

	<jsp:include page="footerkari.jsp" />

</body>
</html>