<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>カート内容確認</title>
<style>
	td{
		text-align:center;
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
		<c:forEach var="itemsList" items="${itemsList }">
			<form:form modelAttribute="cModel">
			<tr>
				<td><img
				src="resources/img/<c:out value="${itemsList.itemPicture }"  />"
						width="96" height="128" alt="${itemsList.itemName }"></td>
				<td><c:out value="${itemsList.itemName }" /></td>
				<td><c:out value="${cModel.getCart().size()}" /></td>


			</form:form>
		</c:forEach>
	</table>
	<form action="clear" method="get">
		<input type="submit" name="clear" value="カート消去">
	</form>
	<br>
	<form action="clear" method="get">
				<input type="submit" class="btn" value="レンタル完了へ">
	</form>
				 <a href="/homepage1">ホームへ
				 </a>
	</main>
	</div>
</body>
</html>
