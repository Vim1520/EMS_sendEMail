
<!DOCTYPE html>
<html>
<head>
<title>success</title>

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
	<h3>Success !!! Your Password was changed --- !!!!!</h3>
	<form action="Redirect">
		<h4>To Go BACK !!!!!
		<table><tr><td><input type="submit" value="Go Back"></td>
		</tr>
		</h4>
		</table>

	</form>
</body>
</html>




