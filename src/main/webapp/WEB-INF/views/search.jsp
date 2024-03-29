<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>検索画面</title>
<link href="/yamamons/resources/img/style.css" type="text/css"
	rel="stylesheet" />
<link href="/yamamons/resources/img/rental.css" type="text/css"
	rel="stylesheet" />
<link href="/yamamons/resources/img/search.css" type="text/css"
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

th, td {
	padding: 10px; /* オプション: セルに余白を追加してスペーシングを改善 */
	text-align: center; /* オプション: セル内のテキストを中央寄せに設定 */
}

strong {
	background-color: white;
}
</style>

</head>
<body
	style="background-image: url('/yamamons/resources/img/雲.jpg');"
	class="body">
	<div class="container">
		<jsp:include page="headerkari.jsp"></jsp:include>
		<main>

			<form:form modelAttribute="items">
				<div class="form-row">
					<label for="itemName"></label>
					<form:input path="itemName" placeholder="作品名" />

					<form:form modelAttribute="genres">
						<form:select path="genreNo" items="${genresList}" size="1"
							multiple="false" itemLabel="genre" itemValue="genreNo">
							<form:option value="-1" label="選択してください" />
						</form:select>
						<input type="submit" value="検索する"
							class="btn btn--green btn--emboss btn--cubic">
						<br>
						<br>
					</form:form>
				</div>
				<div class="form-row errors">
					<c:out value="${message }"></c:out>
				</div>
			</form:form>
			<!-- memberListにデータ（検索結果があった場合）表示される -->
			<c:if test="${ !empty itemsList}">
				<table border="1">


					<tr>
						<th><img src="/yamamons/resources/img/movie.png"></th>
						<th><img src="/yamamons/resources/img/name.png"></th>
						<th><img src="/yamamons/resources/img/director.png"></th>
						<th></th>
					</tr>
					<c:forEach var="itemsList" items="${itemsList }">
						<tr>
							<td><img
								src="resources/img/<c:out value="${itemsList.itemPicture }" />"
								width="150" height="250" alt="${itemsList.itemName }" /></td>
							<td><c:out value="${itemsList.itemName }" /></td>
							<td><c:out value="${itemsList.director }" /></td>
							<td><a href="login"> <input type="submit"
									value="カートに入れる"> <input type="hidden"
									name="itemNo" value="${itemsList.itemNo }"></a></td>
						</tr>
					</c:forEach>

				</table>
			</c:if>
		</main>
		<jsp:include page="footerkari.jsp"></jsp:include>
	</div>
</body>
</html>