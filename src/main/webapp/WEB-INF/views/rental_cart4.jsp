<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>カート内容確認</title>
<style>
td {
	text-align: center;
}
</style>
</head>
<body>
	<div id="wrapper">
		<main>
			<h1>レンタル確認画面</h1>

			<table border="1">
				<tr>
					<th>商品画像</th>
					<th>商品名</th>
					<th>商品点数</th>

				</tr>
				<c:forEach var="cartItems" items="${cartItems }" begin="0" end="100" step="1" varStatus="status">
					<form:form modelAttribute="dModel">
						<tr>
							<td><img src="resources/img/<c:out value="${cartItems.itemPicture }"  />"
						width="96" height="128" alt="${cartItems.itemName }"></td>
							<td><c:out value="${cartItems.itemName }" /></td>
							<td><input type="submit" value="削除">
							<input type="hidden" name="index" value="${status.count }"></td>
						</tr>
					</form:form>
				</c:forEach>
			</table>
			<form action="clear" method="get">
				<input type="submit" name="clear" value="カート消去">
			</form>
			<br>
			<form action="orderComplete" method="get">
				<input type="submit" class="btn" value="レンタル完了へ">
			</form>
			<a href="home">ホームへ </a>
		</main>
	</div>
</body>
</html>
