<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ホーム</title>
</head>
<header>
	<a href="login">ログイン</a>
	<form action="/login" method="post">
		<input type="submit" value="ログイン" class="btn">
	</form>
	<form action="/registration2" method="get">
		<input type="submit" value="新規登録" class="btn">
	</form>
	</header>

<body>
	<center><h1>MUSIC LIFE AGENCY</h1></center>
		<h1>おすすめ商品</h1>

</body>
</html>

