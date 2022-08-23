<%@ page language="java" %>
<%@page import="java.sql.*"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<% Class.forName("com.mysql.cj.jdbc.Driver");%>
<html>
<head>
<title>USER</title>
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
if(resultset.getInt(1)!=3){
response.sendRedirect("Redirect.jsp");
	}
	}
catch(Exception e){
//response.sendRedirect("Login.jsp");
}

%>
<center><H1> Employee Details </H1>
       <%
          
 String query="select e.empid,e.EmpFirstName,e.EmpLastName,e.age,e.city,e.EmpMobno,e.EmpEmailID,r.role,c.category from employeedb as e left join cat as c on c.id = e.cid left join rol as r on e.rid=r.id where e.EmpID=?" ;
statement=connection.prepareStatement(query);
statement.setInt(1,id2);
resultset=statement.executeQuery();
       %>
      <TABLE BORDER="2" cellpadding="8" cellspacing="6">
      <TR>
      <TH>ID</TH>
      <TH>First Name</TH>
      <TH>Last Name</TH>
      <TH>Age</TH>
      <TH>City</TH>
      <TH>Mobile</TH>
	<TH>Email</TH>
	<TH>Role</TH>
	<TH>Category</TH>
      </TR>
      <% while(resultset.next()){ %>
      <TR>
       <TD> <%= resultset.getString(1) %></td>
       <TD> <%= resultset.getString(2) %></TD>
       <TD> <%= resultset.getString(3) %></TD>
       <TD> <%= resultset.getString(4) %></TD>
       <TD> <%= resultset.getString(5) %></TD>
       <TD> <%= resultset.getString(6) %></TD>
       <TD> <%= resultset.getString(7) %></TD>
       <TD> <%= resultset.getString(8) %></td>
       <TD> <%= resultset.getString(9) %></td>
      </TR>
      <% } %></center><br>
<center><table cellspacing="18">
<tr>
<h3><b><a href="Chpass.jsp">1.Change Password</a></b></tr></h3><tr>
<h3><b><a href="Logout.jsp">2.LogOut</a></tr></b></h3>
</table></center>
</body>
</html>