<%@ page language="java" %>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page import=" java.io.IOException"%>
<%@ page import=" java.sql.*"%>
<% Class.forName("com.mysql.cj.jdbc.Driver");%>
<HTML>
<HEAD>
<TITLE> Search By ID </TITLE>
</HEAD>
<body>
<% 
response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setHeader("Expires", "0");
int id2=0;
ResultSet resultset;
Connection connection=null;
PreparedStatement statement=null;
if(session.getAttribute("EmpID")==null)
{	
response.sendRedirect("login.jsp");
}

else{
String id=(String)session.getAttribute("EmpID");
id2=Integer.parseInt(id);
}try{
connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeedb","root","Vimal@2002"); 
statement = connection.prepareStatement(" select Rid from EmployeeDB where EmpID =?");
statement.setInt(1, id2);
 resultset = statement.executeQuery();
resultset.next();
if(resultset.getInt(1)!=1 && resultset.getInt(1)!=2){
response.sendRedirect("Redirect.jsp");
	}
	}
catch(Exception e){
//response.sendRedirect("Login.jsp");
}
%>


<h3>-------- Enter ID to Search --------</h3>
<form method="post" action="SearchID">
<table>
<tr>
<td>Select Role :</td>
<td><select name="rol">
<option>----- ------</option>
<option value=1>1.SuperAdmin</option>
<option value=2>2.Admin</option>
<option value=3>3.User</option>	
</select>	
</td></tr>
</TABLE><br>
<input type="submit" value="Search">
</form>
</BODY>
</HTML>