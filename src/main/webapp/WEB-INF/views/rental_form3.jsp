<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>レンタル画面</title>
</head>
<body>
	<h1>レンタルサイト</h1>
	
	<img src="resources/img/ドラえもん.png">
	<input type ="submit" value="カートに入れる">
	
	
	
	
	<p>
		<input type ="submit" name="clear" value="カートを空にする">
	</p>
	<a href="confirm">カートを確認</a>
</body>
</html>