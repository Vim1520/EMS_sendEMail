<%@ page language="java" %>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page import=" java.io.IOException"%>
<%@ page import=" java.sql.*"%>
<% Class.forName("com.mysql.cj.jdbc.Driver");%>
<html>
<head>
<title>SuperAdmin</title>
</head>
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
if(resultset.getInt(1)!=1){
response.sendRedirect("Redirect.jsp");
	}
	}
catch(Exception e){
//response.sendRedirect("Login.jsp");
}

%>

<center> <h2>Employee Management </h2></center>
<ol>
<h3>
<li><a href="Add.jsp">Add</a></li>
<li><a href="SearchID.jsp">Search by ID</a></li>
<li><a href="SearchCat.jsp">Search by Category</a></li>
<li><a href="SearchRole.jsp">Search by Role</a></li>
<li><a href="EditDet.jsp">Edit</a></li>
<li><a href="Delete.jsp">Delete</a></li>
<li><a href="Display.jsp">Display</a></li>
<li><a href="Exp.jsp">Export</a></li>
<li><a href="ClrExp.jsp">Clear Exports</a></li>
<li><a href="Chpass.jsp">Change Password</a></li>
<li><a href="Logout.jsp">LogOut</a></li>
</h3>
</ol>
 





</body>
</html>