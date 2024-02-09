<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>検索画面</title>
<link href="/yamamons/resources/img/style.css" type="text/css" rel="stylesheet" />
<link href="/yamamons/resources/img/rental.css" type="text/css" rel="stylesheet" />

<style>
    table {
        width:100%; /* テーブルを利用可能な全幅に設定 */
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
<body style="background-image: url('/yamamons/resources/img/グレー2.jpg');" class="body">
	<div class="container">
		<jsp:include page="headerkari.jsp"></jsp:include>
		<main>
			<form:form modelAttribute="items">
				<div class="form-row">
					作品名を入力してください
				</div>
				<div class="form-row">
					<label for="itemName"></label>
					<form:input path="itemName" placeholder="作品名"/>
					<input type="submit" value="検索する"  class="btn btn--green btn--emboss btn--cubic">
				</div>
				<div class="form-row errors">
					<c:out value="${message }"></c:out>
				</div>
			</form:form>
			<!-- memberListにデータ（検索結果があった場合）表示される -->
			<c:if test="${ !empty itemsList}">
				<table>
					
					
			<tr>
				<th>商品画像</th>
				<th>商品名</th>
				<th>監督名</th>
				<th></th>
				</tr>
		<c:forEach var="itemsList" items="${itemsList }">
				<tr>
					<td>
						<img src="resources/img/<c:out value="${itemsList.itemPicture }" />"
						width="150" height="250" alt="${itemsList.itemName }"/></td>
					<td width="100" height="250"><strong><c:out value="${itemsList.itemName }" /></strong></td>
					<td><c:out value="${itemsList.director }" /></td>
				</tr>
					</c:forEach>
				</table>
			</c:if>
		</main>
		<jsp:include page="footerkari.jsp"></jsp:include>
	</div>
</body>
</html>