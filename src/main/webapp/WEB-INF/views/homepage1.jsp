<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang=ja>
<head>
<meta charset="UTF-8">
<title>ホーム</title>
<link href="/yamamons/resources/img/style.css" type="text/css" rel="stylesheet" />

</head>

<body>
	<div class="sample" >MUSIC LIFE AGENCY</div>

<header>
<a href="login"  class="btn btn--green btn--emboss btn--cubic">ログイン</a>
<a href="registration"  class="btn btn--green btn--emboss btn--cubic">新規登録</a>

	<!--  ボタン処理した場合のJSP
		 <form action="/login" method="post">
			<input type="submit" value="ログイン" class="btn btn--green btn--emboss btn--cubic">
		</form>
		<form action="/registration2" method="get">
			<input type="submit" value="新規登録" class="btn btn--green btn--emboss btn--cubic">
		</form>-->



	</header>

		<h3>おすすめ商品</h3>
		<h1>金曜日の5時</h1>
		<img src="resources/img/しんちゃん.jpg" alt="写真" width="350" height="250"/> <img src="resources/img/ドラえもん.png" alt="写真" width="350" height="250"/> <img src="resources/img/忍たま乱太郎.jpg" alt="写真" width="350" height="250"/>


</body>
</html>

