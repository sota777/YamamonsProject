<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang=ja>
<head>
<meta charset="UTF-8">
<title>ホーム</title>
<link href="/yamamons/resources/img/style.css" type="text/css"
	rel="stylesheet" />

<style>
body {
	background-image: url("/yamamons/resources/img/雲.jpg");
	width: 100%;
}
.text-right {
      text-align: right;
      margin-right: 350px; /* 調整したい余白の幅を指定 */
      }
</style>
</head>

<body>
	<div class="sample">MUSIC LIFE AGENCY</div>
	<br>

	<header>
		<a href="login"> <input type="submit" value="ログイン"
			class="btn btn--green btn--emboss btn--cubic"></a>
			<br>
		<a href="registration"> <input type="submit" value="新規登録"
			class="btn btn--green btn--emboss btn--cubic"></a>
			<br>
	 	<a href="search"> <input type="submit" value="映画検索"
			class="btn btn--green btn--emboss btn--cubic"></a>
		<br>
		<br>
	</header>
	<section class="zoom-out">
		<div class="zoom-out-img">
			<a href="https://www.shinchan-movie.com/2024/"> <img
				src="resources/img/しんちゃん食.jpg" alt="写真" width="400" height="250" /><br>
			</a>
		</div>

		<div class="zoom-out-img">
			<a href="https://doraeiga.com/2024/"> <img
				src="resources/img/ドラえもん2.jpg" alt="写真" width="500" height="250" />
			</a>
		</div>
		<br>
		<div class="zoom-out-img">
			<a href="https://minions.jp/"> <img src="resources/img/ミニオン.jpg"
				alt="写真" width="400" height="250" />
			</a>
		</div>
		<div class="zoom-out-img">
			<a href="https://www.shinchan-movie.com/2024/"> <img src="resources/img/しんちゃん.jpg"
				alt="写真" width="400" height="250" />
			</a>
		</div>
		<div class="zoom-out-img">
			<a href="https://doraeiga.com/2024/"> <img src="resources/img/ドラえもん.png"
				alt="写真" width="400" height="250" />
			</a>
		</div>
		<div class="zoom-out-img">
			<a href="https://minions.jp/"> <img src="resources/img/ミニオン.jpg"
				alt="写真" width="400" height="250" />
			</a>
		</div>
		<div class="zoom-out-img">
			<a href="https://minions.jp/"> <img src="resources/img/ミニオン.jpg"
				alt="写真" width="400" height="250" />
			</a>
		</div>
	</section>
	<br>
	<br>
	<br>
	<br>
		<jsp:include page="footerkari.jsp"/>

</body>
</html>

