<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>レンタル履歴確認</title>
<link href="/yamamons/resources/img/style.css" type="text/css" rel="stylesheet" />
<link href="/yamamons/resources/img/rental.css" type="text/css" rel="stylesheet" />

<style>
td {
	text-align: center;
}
table {
	width: 60%;
	margin-left: 20%;
	margin-right: 50%;
	border-collapse: collapse; /* オプション: セル間の境界線を折り畳む */
	text-align: center;
	background-image: url("/yamamons/resources/img/白ピンク背景.jpg");
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
<body style="background-image: url('/yamamons/resources/img/雲.jpg');" class="body">
	<div class="sample"><h5>レンタル履歴確認</h5></div>


	<table border="1">
		<tr>
			<th>商品画像</th>
			<th>商品名</th>
			<th>監督名</th>
			<th>レンタル日</th>
			<th>貸出状況</th>
		</tr>

		<c:forEach var="rentalHistory" items="${rentalHistories }" >
						<tr>
							<td><img src="resources/img/<c:out value="${rentalHistory.itemPicture }"  />"
								width="96" height="128" alt="${rentalHistory.itemName }"></td>
								<td><c:out value="${rentalHistory.itemName }"  /></td>
								<td><c:out value="${rentalHistory.director }"  /></td>
								<td><c:out value="${rentalHistory.orderDate }"  /></td>
								<td><c:if test="${rentalHistory.rentalStatusNo == 0}">
									返却済</c:if>
									<c:if test="${rentalHistory.rentalStatusNo == 1}">
									貸出中</c:if></td>
						</tr>

		</c:forEach>
	</table>
	<br>
	<br>
	<a href="confirm" >
			<input type="submit" value="カート確認画面へ戻る" class="btn btn--green btn--emboss btn--cubic"></a><br>
	<br>
	<br>


		<jsp:include page="footerkari.jsp"/>

</body>
</html>