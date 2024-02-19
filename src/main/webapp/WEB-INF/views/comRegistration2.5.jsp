<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登録完了画面</title>
<link href="/yamamons/resources/img/style.css" type="text/css" rel="stylesheet" />
<link href="/yamamons/resources/img/login.css" type="text/css" rel="stylesheet" />

</head>
<body style="background-image: url('/yamamons/resources/img/雲.jpg');" class="body">
	<jsp:include page="headerkari.jsp"></jsp:include>
		<a href="login" >
			<input type="submit" value="ログイン" class="btn btn--green btn--emboss btn--cubic"></a><br>

<br>
	<div class="sample" >ご登録ありがとうございました</div><br>

	<section class="zoom-out">
    <div class="zoom-out-img">
  <a href="https://g-men-movie.com/">
    <img src="resources/img/GMEN.jpg" alt="写真" width="400" height="250"/><br>
  </a>
</div>

   <div class="zoom-out-img">
  <a href="https://kinro.ntv.co.jp/lineup/20231222">
    <img src="resources/img/ホームアローン.jpg" alt="写真" width="500" height="250"/>
  </a>
</div>
<br>
   <div class="zoom-out-img">
  <a href="https://www.parasite-mv.jp/">
    <img src="resources/img/パラサイト.jpg" alt="写真" width="400" height="250"/>
  </a>
	</div>
	 <div class="zoom-out-img">
  <a href="https://b-no-senjou.official-movie.com/">
    <img src="resources/img/良子.jpg" alt="写真" width="400" height="250"/>
  </a>
	</div>

</section><br>


<footer>

			<jsp:include page="footerkari.jsp"></jsp:include>

	</footer>
	</body>
</html>