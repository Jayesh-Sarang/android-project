<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
  <html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Include</title>
    </head>
    <body>
        <a href="#Afterheaderlogin.jsp">Link1</a>
        <a href="#link2">Link2</a>
        <a href="#link3">Link3</a>
        <a href="#link4">Link4</a>


        <div id="include"></div>

        <script type="text/javascript" src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
        <script type="text/javascript"> 
            var includeDiv = $("#include");
            $(window).on('hashchange', function() {
                var href = location.hash.slice(1) ;
                includeDiv.load( href);
            });
        </script> 
    </body>
</html>