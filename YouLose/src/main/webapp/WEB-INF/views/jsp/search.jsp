<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search results</title>
</head>
<body>
<body>
<jsp:include page="header.jsp" />
<table>
	<td>
		<jsp:include page="left.jsp" />
	</td>
	<td>
		<h1>Results for ${ user.searchedWord }</h1>
	</td>
</table>

</body>
</body>
</html>