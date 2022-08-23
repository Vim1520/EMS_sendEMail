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
       <TITLE> Display </TITLE>
       </HEAD><body>
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


 <center><H1> Employee Details </H1>
 <%
connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/employeedb","root","Vimal@2002"); 
           Statement stmt = connection.createStatement() ;
          resultset = stmt.executeQuery(" select e.empid,e.EmpFirstName,e.EmpLastName,e.age,e.city,e.EmpMobno,e.EmpEmailID,r.role,c.category from employeedb as e left join cat as c on c.id = e.cid left join rol as r on e.rid=r.id ") ;
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
      <% } %></center>
     </TABLE>
 <form action="Redirect" method="post"><h1><center>Go Back</center>
 </h1><center>To Go Back !!!!!<table><tr><td><input type="submit" value="__"></td
</tr></center></table></form>
     </BODY>
</HTML>