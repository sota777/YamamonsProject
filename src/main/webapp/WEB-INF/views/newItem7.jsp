<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新しい商品の追加</title>
</head>
<body>
<div class="container">

		<main>


			<form:form modelAttribute="adminModel">
				<div class="form-row">
					<label for="itemName" class="disp-block">作品名</label>
					<form:input path="itemName" />
					<form:errors path="itemName" element="span" cssClass="errors" />
				</div>
				<div class="form-row">
					<label for="itemQuanity" class="disp-block">入荷数</label>
					<form:input path="itemQuanity" />
					<form:errors path="itemQuanity" element="span" cssClass="errors" />
				</div>
				<div class="form-row">
					<label for="genreNo" class="disp-block">ジャンル</label>
					<form:input path="genreNo" />
					<form:errors path="genreNo" element="span" cssClass="errors" />
				</div>
				<div class="form-row">
					<label for="director" class="disp-block">監督名</label>
					<form:input path="director" />
					<form:errors path="director" element="span" cssClass="errors" />
				</div>
				<div class="form-row">
					<label for="typeNo" class="disp-block">タイプナンバー</label>
					<form:input path="typeNo" />
					<form:errors path="typeNo" element="span" cssClass="errors" />
				</div>

				<div></div>
				<div></div>
				<div class ="form-row">
					<input type="submit" value="登録する" class="btn">
				</div>
				<div class="form-row errors">
					<c:out value="${message }"/>
				</div>
				</form:form>
	</main>
	</div>
</body>
</html>