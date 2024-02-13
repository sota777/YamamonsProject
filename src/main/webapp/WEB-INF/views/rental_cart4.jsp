<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>カート内容確認</title>
<link href="/yamamons/resources/img/style.css" type="text/css" rel="stylesheet" />
<link href="/yamamons/resources/img/rental.css" type="text/css" rel="stylesheet" />

<style>
td {
	text-align: center;
}
    table {
        width: 100%; /* テーブルを利用可能な全幅に設定 */
        border-collapse: collapse; /* オプション: セル間の境界線を折り畳む */
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
<body style="background-image: url('/yamamons/resources/img/グレー2.jpg');" class="body">
	<div class="sample" >CART</div><br>
		<form action="clear" method="get">
					<input type="submit" name="clear" value="カートを空にする" class="btn btn--green btn--emboss btn--cubic">
		</form>
		<a href="form" >
			<input type="submit" value="レンタルサイト" class="btn btn--green btn--emboss btn--cubic"></a><br>
<br>
	<div id="wrapper">
		<main>


			<table border="1">
				<tr>
					<th>商品画像</th>
					<th>商品名</th>
					<th>商品点数</th>

				</tr>
				<c:forEach var="cartItems" items="${cartItems }" begin="0" end="100"
					step="1" varStatus="status">
					<form:form modelAttribute="dModel">
						<tr>
							<td>
							<img src="resources/img/<c:out value="${cartItems.itemPicture }"  />"
								width="96" height="128" alt="${cartItems.itemName }"></td>
							<th><strong><c:out value="${cartItems.itemName }" /></strong></th>
							<td><input type="submit" value="削除">
								<input type="hidden" name="index" value="${status.count }"></td>
						</tr>
					</form:form>
				</c:forEach>
			</table>
			<br>
			<p>
				<c:out value="${message }" />
			</p>

			<c:if test="${!empty alertMessage}">
			<p>
			<font color="red">
				<c:out value="${alertMessage }" />
			</font>
			</p>
			</c:if>


			<c:if test="${!empty errormessage}">
			<p>
				<c:out value="${errormessage }" />
			</p>
				<p>
							</p>
			</c:if>
<br>
				 <form action="orderComplete" method="get">
        			<input type="submit" class="btn btn--green btn--emboss btn--cubic"
        			value="RENTAL"  style="font-size: 24px;">
				</form>
    			<br>
    			<br>
    			<br>

		<a href="history"  >
			<input type="submit" value="レンタル履歴" class="btn btn--green btn--emboss btn--cubic"></a><br>

		</main>
	</div>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<footer>

			<jsp:include page="footerkari.jsp"></jsp:include>

	</footer>
</body>
</html>
