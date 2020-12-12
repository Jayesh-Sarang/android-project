<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="js/autocompleter.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$(function() {
			$("#search").autocomplete({

				source : function(request, response) {
					$.ajax({
						url : "Controller",
						type : "POST",
						data : {
							term : request.term
						},
						dataType : "json",
						success : function(data) {
							response(data);
						}
					});
				}
			});
		});
	});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1 class="new">AArogyam User</h1>
	<div class="header">
		<div class=" nav">
			<span class="menu"><img src="images/menu.png" alt=""> </span>
			<ul>

			</ul>
			<script>
				$("span.menu").click(function() {
					$(".nav ul").slideToggle(500, function() {
					});
				});
			</script>
		</div>
		<div class="header-top">
			<div class=" top-menu">
				<span class="menu1">Menu </span>
				<ul>
					<li><a href="Userfile.jsp">Home</a></li>
					<li><a href="logout.jsp">Log Out</a></li>

					<%
						if (session.getAttribute("type") != null) {
					%>
					<li><a href="Auditing.jsp">Auditing</a></li>

					<%
						}
					%>
<li><a href="ViewMedicleHistory.jsp">View Medical History</a></li>
<li><a href="uploadMedicleHistory.jsp">Upload Medical History</a></li>
<li><a href="ViewDoctorslist.jsp">View Doctors Details</a></li>
<li><a href="ViewMyappointment.jsp">View Appointment Details</a></li>
					<li><a href="#">Contact</a></li>
				</ul>
			</div>
			<div class=" search">
				<form>
					<input type="text" id="search" placeholder="Search File here"
						onkeyup="autocomplete(this.value)" /> <input type="submit"
						value="">
				</form>
			</div>
			<div class="clear"></div>

		</div>
		<div class="clear"></div>
	</div>
</body>
</html>