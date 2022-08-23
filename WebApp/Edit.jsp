<%@ page language="java" %>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page import=" java.io.IOException"%>
<%@ page import=" java.sql.*"%>
<% Class.forName("com.mysql.cj.jdbc.Driver");%>
<!DOCTYPE html>
<html>
<head>
<title>Add</title>

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
<%! String First=null; %>
<%! String Last=null; %>
<%! String age=null; %>
<%! String city=null; %>
<%! String mob=null; %>
<%! String email=null; %>
<%! String role=null; %>
<%! String cat=null; %>
<%! int cid; %>
<%! int rid; %>
<%String idd=null;
idd= request.getParameter("EID");
 int eid=0;
eid=Integer.parseInt(idd);
 connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeedb","root","Vimal@2002"); 
statement = connection.prepareStatement(" select e.EmpFirstName,e.EmpLastName,e.age,e.city,e.EmpMobno,e.EmpEmailID,r.Role,c.category,e.cid,e.rid from employeedb as e left join cat as c on c.id = e.cid left join rol as r on e.rid=r.id where e.EmpID=?") ;
statement.setInt(1, eid);
resultset = statement.executeQuery() ;
while(resultset.next()){%>
<%First=resultset.getString(1);%>
<% Last=resultset.getString(2);%>
<% age=resultset.getString(3) ;%>
<% city=resultset.getString(4);%>
<% mob=resultset.getString(5) ;%>
<% email=resultset.getString(6);%>
<% role=resultset.getString(7);%>
<%cat=resultset.getString(8);%>
<%cid=resultset.getInt(9);%>
<%rid=resultset.getInt(10);%>
<%}%>


	<form method="post" action="Edit">
		<h1>
			<center>Edit Details</center><br>
		</h1>
<h3> Enter the                                     </h3>
		<table>
			<input type="hidden" name="eid" value=<%=eid%>>
			<tr>
				<td>First Name:</td>
				<td><input type="text" name="FirstName" value=<%=First%>></td>
			</tr>
			<tr>
				<td>Last Name:</td>
				<td><input type="text" name="LastName" value=<%=Last%>></td>
			</tr>
			<tr>
				<td>Age</td>
				<td><input type="text" name="Age" value=<%=age%>></td>
			</tr>
			<tr>
				<td>City</td>
				<td><input type="text" name="city" value=<%=city%>></td>
			</tr>
			<tr>
				<td>Mobile Number</td>
				<td><input type="text" name="Mobile"  value=<%=mob%>></td>
			</tr>
			<tr>
				<td>Email ID</td>
				<td><input type="text"  name="Email" value=<%=email%>></td>
			</tr>
			<tr>
				<td>Category</td>
				
				<td><select name="cat">
				<option value=<%=cid%>><%=cat%></option>
				<option value=1>1.Manager</option>
				<option value=2>2.TL</option>
				<option value=3>3.Tester</option>	
				<option value=4>4.Developer</option>
				</select>	
			</tr>
			<tr>
				<td>Role</td>
				
				<td><select name="rol">
				<option value=<%=rid%>><%=role%></option>
				<option value=1>1.SuperAdmin</option>
				<option value=2>2.Admin</option>
				<option value=3>3.User</option>
				</select>	
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Update"></td>
			</tr>

		</table>

	</form>
<form action="SuperAdmin.jsp"><h1><center>Go Back</center>
 </h1><center>To Go Back ......!!!!!<table><tr><td><input type="submit" value="__"></td
</tr></center></table></form>

 
</body>
</html>