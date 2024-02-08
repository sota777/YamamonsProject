<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<style>
	.errors{
		color: red;
	}
</style>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>ログイン</title>
<link href="/yamamons/resources/img/style.css" type="text/css"
	rel="stylesheet" />
<link href="/yamamons/resources/img/login.css" type="text/css"
	rel="stylesheet" />

</head>
<body style="background-image: url('/yamamons/resources/img/グレー2.jpg');"
	class="body">
	<jsp:include page="headerkari.jsp"></jsp:include>

	<a href="registration"> <input type="submit" value="新規登録へ"
		class="btn btn--green btn--emboss btn--cubic"></a>
	<br>

	<form:form modelAttribute="loginModel">
		<br>

		<div class="container">
			<form action="rental_form3.jsp" method="get">
				<p class="fsize">
					<img src="/yamamons/resources/img/signin.png">
				</p>
				<div class="errors">${errorMessage }</div>
				<form:input path="loginMail" placeholder="MailAddress" />
				<form:password path="password" placeholder="Password" />
				<a href="form">
					<button type="submit">Login</button>
				</a>
			</form>
		</div>
		<!--  <table>
			<tr>
				<td>メールアドレス</td>
				<td>
					<form:input path="loginMail" />
				</td>
				<td>
					<form:errors path="loginMail" element="div" cssClass="error" />
				</td>
			</tr>
			<tr>
				<td>パスワード</td>
				<td>
					<form:password path="password" />
				</td>
				<td>
					<form:errors path="password" element="div" cssClass="error" />
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<input type="submit" value="ログイン" />
				</td>
			</tr>
		</table>-->
	</form:form>
	<hr />
	<footer>
		<jsp:include page="footerkari.jsp"></jsp:include>

	</footer>
</body>
</html>