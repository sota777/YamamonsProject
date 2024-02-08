<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<style>
	p{
		letter-spacing:0.12em;
		font-size: 25px;
		display: block;
	}
	.errors{
		color:red;
	}

</style>
<meta charset="UTF-8">
<title>新規会員登録</title>
<link href="/yamamons/resources/img/style.css" type="text/css"
	rel="stylesheet" />
<link href="/yamamons/resources/img/login.css" type="text/css"
	rel="stylesheet" />


</head>
<body style="background-image: url('/yamamons/resources/img/グレー2.jpg');"
	class="body">

	<jsp:include page="headerkari.jsp" />
	<a href="login"> <input type="submit" value=ログイン
		class="btn btn--green btn--emboss btn--cubic"></a>
	<br>


	<div class="container">
		<p >
			<font face='Palatino Linotype' ><strong>～Ragistration～</strong></font>
		</p>

		<form:form modelAttribute="membersModel">

			<div>
				<form:errors path="name" element="span" cssClass="errors" />
				<form:input path="name" size="20" placeholder="name" />
			</div>
			<div>
				<form:errors path="address" element="span" cssClass="errors" />
				<form:input path="address" placeholder="address" />
			</div>
			<div>
				<form:errors path="mail" element="span" cssClass="errors" />
				<form:input path="mail" placeholder="mail" />
			</div>
			<div>
				<form:errors path="password" element="span" cssClass="errors" />
				<form:password path="password" placeholder="Password" />
			</div>
			<div>
				<form:errors path="phoneNumber" element="span" cssClass="errors" />
				<form:input path="phoneNumber" size="15" placeholder="000-0000-0000" />
			</div>
			<div>
				<form:errors path="credit" element="span" cssClass="errors" /><br>
				<label><form:radiobutton path="credit"  value="0" style="transform:scale(1.5);"/> <img src="/yamamons/resources/img/R.png" ></label>
				<label><form:radiobutton path="credit"  value="1" style="transform:scale(1.5);"/> <img src="/yamamons/resources/img/jcb.png" ></label>
				<label><form:radiobutton path="credit"  value="2" style="transform:scale(1.5);"/> <img src="/yamamons/resources/img/visa.png" ></label>
				<form:radiobutton path="credit"  value="3" style="transform:scale(1.5);"/> <img src="/yamamons/resources/img/master.png" >
			</div>



    <button type="submit">ragistration</button>


    <div class="form-row errors">
    	<c:out value="${message }"></c:out>
    </div>
    </form:form>


    <div>
	<jsp:include page="footerkari.jsp"/>
	</div>


	</div>

</body>
</html>

