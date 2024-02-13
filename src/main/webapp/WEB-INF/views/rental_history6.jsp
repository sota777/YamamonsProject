<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>レンタル履歴確認</title>
</head>
<body>
	<h1>レンタル履歴確認</h1>

	<table border="1">
		<tr>
			<!-- <th>会員名</th> -->
			<th>商品名</th>
			<th>イメージ</th>
		</tr>

		<c:forEach var="rentalHistory" items="${rentalHistories }" >
						<tr>
							<td>
							<c:out value="${rentalHistory.itemName }"  />
							</td>
							<td>
							<img src="resources/img/<c:out value="${rentalHistory.itemPicture }"  />"
								width="96" height="128" alt="${rentalHistory.itemName }">
							</td>
						</tr>

		</c:forEach>
	</table>




</body>
</html>