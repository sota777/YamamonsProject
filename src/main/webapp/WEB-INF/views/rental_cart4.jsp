<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>カート内容確認</title>
<style>
	td{
		text-align:center;
	}
</style>
</head>
<body>
<div id="wrapper">
	<header><h1></h1></header>
	<main>
		<table border="3">


		</table>
		<p>
			<%=request.getAttribute("message") %>
		</p>
		<form action="form" method="post">
			<p>
				<input type="submit" name="clear" value="カートを空にする">
			</p>
		</form>
		<p>
			<a href="rental_confirmation">レ</a>
		</p>
	</main>
	</div>
</body>
</html>