<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>レンタル画面</title>
<link href="/yamamons/resources/img/style.css" type="text/css"
	rel="stylesheet" />
<link href="/yamamons/resources/img/rental.css" type="text/css"
	rel="stylesheet" />

<style>
table {
	width: 100%; /* テーブルを利用可能な全幅に設定 */
	border-collapse: collapse; /* オプション: セル間の境界線を折り畳む */
	text-align: center;
}

th, td {
	padding: 10px; /* オプション: セルに余白を追加してスペーシングを改善 */
	text-align: center; /* オプション: セル内のテキストを中央寄せに設定 */
}

strong {
	background-color: white;
}
</style>

</head>
<body style="background-image: url('/yamamons/resources/img/グレー2.jpg');"
	class="body">
	<div class="sample">RENTAL SITE</div>
	<br>
	<h2>いらっしゃいませ、${CusData.customerName }さん</h2>
	<p>
		<c:out value="${message }" />
	</p>

	<form action="clear" method="get">
		<input type="submit" name="clear" value="カートを空にする"
			class="btn btn--green btn--emboss btn--cubic">
	</form>
	<a href="confirm"> <input type="submit" value="カートを確認"
		class="btn btn--green btn--emboss btn--cubic"></a>
	<br>
	<br>


	<table border="1">
		<tr>
			<th>商品画像</th>
			<th>商品名</th>
			<th>監督名</th>
			<th></th>
		</tr>
		<c:forEach var="itemsList" items="${itemsList }">
			<form:form modelAttribute="cModel">
				<tr>
					<td><img
						src="resources/img/<c:out value="${itemsList.itemPicture }" />"
						width="150" height="250" alt="${itemsList.itemName }" /></td>
					<td height="250"><strong><c:out
								value="${itemsList.itemName }" /></strong></td>
					<td><c:out value="${itemsList.director }" /></td>
					<td><input type="submit" value="カートに入れる"> <input
						type="hidden" name="itemNo" value="${itemsList.itemNo }"></td>
				</tr>

			</form:form>
		</c:forEach>
	</table>
	<br>

	<footer>
		<jsp:include page="footerkari.jsp"></jsp:include>
	</footer>
	<br>
</body>
</html>