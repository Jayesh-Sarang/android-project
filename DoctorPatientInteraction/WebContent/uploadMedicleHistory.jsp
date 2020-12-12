<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
.iconDetails {
	margin-left: 2%;
	float: left;
	height: 40px;
	width: 40px;
}

.container2 {
	width: 270px;
	height: auto;
	padding: 1%;
	float: left;
}

h4 {
	margin: 0
}

.left {
	float: left;
	width: 45px;
}

.right {
	float: left;
	margin: 0 0 0 5px;
	width: 215px;
}
</style>

<title>Funky Ui kit Flat Resposive Website Template | Home ::
	w3layouts</title>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery.min.js"></script>
<!-- Custom Theme files -->
<!--theme-style-->
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<!--//theme-style-->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords"
	content="Funky Ui kit Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript">
	
	
	
	
	
	
	
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 







</script>
<!---->
<link rel="stylesheet" href="css/flexslider.css" type="text/css"
	media="screen" />
<script type="text/javascript" src="js/Chart.js"></script>
<!---->
<!--Calender -->
<link rel="stylesheet" href="css/clndr.css" type="text/css" />
<script src="js/underscore-min.js"></script>
<script src="js/moment-2.2.1.js"></script>
<script src="js/clndr.js"></script>
<script src="js/site.js"></script>
<!--End Calender -->
<script src="js/responsiveslides.min.js"></script>
<script>
	$(function() {
		$("#slider").responsiveSlides({
			auto : true,
			nav : true,
			speed : 500,
			namespace : "callbacks",
			pager : true,
		});
	});
</script>
<script>
	$(function() {
		$("#slider1").responsiveSlides({
			auto : true,
			nav : true,
			speed : 500,
			namespace : "callbacks",
			pager : true,
		});
	});
</script>
<!----video -------->
<link href="css/jplayer.blue.monday.min.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="js/jquery.jplayer.min.js"></script>
<!----//video -------->

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
String username=(String)session.getAttribute("user");


%>
	<%
		if (session.getAttribute("user") != null) {
	%>
	<div class="wrap">

		<jsp:directive.include file="afterHeader.jsp" />




		<!--//header-->
		<!--content-->
		<!--content-top-->

		<!--//content-top-->
		<!--content-mid-->
		<!--content-midddle-->
		<center>

			<div class="content-middle">
	<jsp:directive.include file="fileupload.jsp" />

				<div class="clear"></div>
			</div>
		</center>
		<!--//content-midddle-->
		<!--content-middle-top-->

		<!--//content-middle-top-->
		<!--pricing-->


		<!--//pricing-->
		<!--comments-->

		<!--//comments-->
		<div class="footer">
			<p>
				© 2015 All rights reserved | Template by <a href="#" target="_blank">INBOTICS</a>
			</p>
		</div>
		<!--//content-->
	</div>
<%} %>
</body>
</html>