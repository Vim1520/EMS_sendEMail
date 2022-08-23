<%@ page language="java" %>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page import=" java.io.IOException"%>
<%@ page import=" java.sql.*"%>
<%@ page import=" java.io.*"%>
<% Class.forName("com.mysql.cj.jdbc.Driver");%>
<HTML>
<HEAD>
<TITLE> Clear Export </TITLE>
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

 <%  connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeedb","root","Vimal@2002"); 
   Statement stmt = connection.createStatement() ;
   resultset = stmt.executeQuery(" select EmpID from EmployeeDB ");
       %>
<%int j=0;
while(resultset.next()) {
        File file = new File("Employee_"+resultset.getInt(1)+".txt");
       if(file.exists())
       {
         if(file.delete());
         {
          j++;
         }
       }
    }%>
        <%if(j==0) {%>
            <h2>---- No Exports To Delete ---- </h2>
      <%  }
        else { %>
           <h2>------ Exports Cleared ------</h2>
        <%}
%>
<form action="Redirect" method="post"><h1><center>Go Back</center>
 </h1><center>Are You Sure You want to Logout?<table><tr><td><input type="submit" value="__"></td
</tr></center></table></form>
</BODY>
</HTML>