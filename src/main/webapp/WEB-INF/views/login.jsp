<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
<style>
.error {
	color: #ff0000;
	}
table {
	border-collapse: separate;
	border-spacing: 10px;
}
</style>
</head>
<body>
		<jsp:include page="header.jsp"></jsp:include>
	<h1>ログイン画面</h1>
	<hr />
	<form:form modelAttribute="loginModel">
		<div class="error">${errorMessage }</div>
		<table>
			<tr>
				<td>ログインID</td>
				<td>
					<form:input path="loginId" />
				</td>
				<td>
					<form:errors path="loginId" element="div" cssClass="error" />
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
		</table>
	</form:form>
	<hr />
	<footer>
			<jsp:include page="footer.jsp"></jsp:include>

	</footer>
			</body>
</html>