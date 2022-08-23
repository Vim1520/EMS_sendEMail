
<!DOCTYPE html>
<html>
<head>
<title>login</title>

</head>
<body>

<%

response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setHeader("Expires", "0");
	
if(session.getAttribute("EmpID")==null)
	{
		response.sendRedirect("login.jsp");
	}
%>
	<form action="Chpass.jsp">
		<h1>
		<center>!!!!! ------ ERROR ------ !!!!!</center>
		</h1>
		<center>Sorry !!! Re-enter a Strong Password !!!!!
		<table><tr><td><input type="submit" value="Retry"></td>
		</tr>
		</center>
		</table>

	</form>
</body>
</html>




