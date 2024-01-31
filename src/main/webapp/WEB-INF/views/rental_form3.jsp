<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>レンタル画面</title>
</head>
<body>
	<h1>レンタルサイト</h1>

	<img src="resources/img/ドラえもん.png">

	<table border="1">

		<tr>
			<th>商品画像</th>
			<th>商品名</th>
			<th>監督名</th>
			<th></th>
		</tr>
		<c:forEach var="itemsList" items="${itemsList }">
			<tr>
				<td><img src="resources/img/<c:out value="${itemsList.itemPicture }"  />"width="96" height="128" alt="${itemsList.itemName }"></td>
				<td><c:out value="${itemsList.itemName }" /></td>
				<td><c:out value="${itemsList.director }" /></td>
				<td><input type="submit" value="カートに入れる">
					<input type="hidden" name="itemNo" value="${itemsList.itemNo }"></td>

		</c:forEach>

	</table>


	<p>
		<input type ="submit" name="clear" value="カートを空にする">
	</p>
	<a href="confirm">カートを確認</a>
</body>
</html>