<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.*"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<% Class.forName("com.mysql.cj.jdbc.Driver");%>
<!DOCTYPE html>
<html>
<head>
<title>Employee Management</title>
</head>
<body>

<%		int j=0;
		out.print(request.getRemoteUser());
      String id=request.getRemoteUser();
      out.print(id);
      session.setAttribute("EmpID",id);
           Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeedb","root","Vimal@2002"); 
           String pg=null;
           try{
                 if(request.isUserInRole("1")){
                    pg="/SuperAdmin.jsp";
                 }
                 else if(request.isUserInRole("2")){
                    pg="/Admin.jsp";
                 }
                 else if(request.isUserInRole("3")){
                    pg="/User.jsp";
                 } 
           }			
           catch(Exception e){
              System.out.println(e);
           }
           //res.sendRedirect(pg);
           request.getRequestDispatcher(pg).include(request, response);
       %>
	
	
</div>
</body>
</html>