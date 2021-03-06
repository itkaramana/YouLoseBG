<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>left</title>
<meta charset="utf-8" />
	<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />

	<!--     Fonts and icons     -->
	<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons" />
    <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700" />
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" />

	<!-- CSS Files -->
    <link href="/Youlose/static/css/bootstrap.min.css" rel="stylesheet" />
    <link href="/Youlose/static/css/material-kit.css" rel="stylesheet"/>

</head>
<body>

	<c:set var="home" scope="session" value="login" />
	<c:set var="profile" scope="session" value="login" />
	<c:set var="forLater" scope="session" value="login" />
	<c:set var="watched" scope="session" value="login" />
	<c:set var="liked" scope="session" value="login" />
	<c:set var="subscriptions" scope="session" value="login" />

		<c:if test="${user != null}">
			<c:set var="home" scope="session" value="index" />
			<c:set var="profile" scope="session" value="${user.name}/profile" />
			<c:set var="forLater" scope="session" value="forLater" />
			<c:set var="watched" scope="session" value="watched" />
			<c:set var="liked" scope="session" value="liked" />
			<c:set var="subscriptions" scope="session" value="subscriptions" />
	</c:if>
<table>
		<td>
		<form action="${home} " method="get">
				<input type="submit" value="Home" class="btn btn-simple btn-primary btn-lg" style="color:white">
		</form>
		
		<form action="${profile} " method="get">
				<input type="submit" value="My profile" class="btn btn-simple btn-primary btn-lg" style="color:white">
		</form>
		<form action="${forLater} " method="get">
				<input type="submit" value="Watch later" class="btn btn-simple btn-primary btn-lg" style="color:white">
		</form>
		<form action="${watched} " method="get">
				<input type="submit" value="Watched" class="btn btn-simple btn-primary btn-lg" style="color:white">
		</form>
		<form action="${liked} " method="get">
				<input type="submit" value="Liked" class="btn btn-simple btn-primary btn-lg" style="color:white">
		</form>
		<form action="${ subscriptions}" method="get">
				<input type="submit" value="Subscriptions" class="btn btn-simple btn-primary btn-lg" style="color:white">
		</form>
		</td>
</table>
</body>
</html>