<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Main</title>
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
<jsp:include page="header.jsp" />
<table >
	<td>
		<jsp:include page="left.jsp" />
	</td>
	<td>
			<div class="container">
				<div class="row">
					<div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3">
						<div class="card card-signup">
							<form class="form" action="main" method="post" enctype="multipart/form-data">
								<h3 class="text-divider"><b><i>Upload video</i></b></h3>
								<img src="/Youlose/static/img/upload.ico" alt="upload" style="width: 75px; height:;">
								<div class="content">
								<input type="file" id="video" name="video" accept="video/*"><br>
									<div class="input-group">
										<center><input type="text" class="form-control" placeholder="Name..." name = "videoName" required="required"></center>
									</div>
									<div class="input-group">
										<center><input type="text" placeholder="Description..." name="description" required="required" class="form-control" /></center>
									</div>
									<center><input type="submit" value="Upload"></center>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
	</td>
	</table>
</body>
</html>