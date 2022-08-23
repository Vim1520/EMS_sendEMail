
<!DOCTYPE html>
<html>
<head>
<title>Add</title>

</head>
<body >

<%

response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setHeader("Expires", "0");



%>


	<form method='post' action="CRUD">
		<h1>
			<center>Add Employee</center>
		</h1>
		<table>
			<tr>
				<td>Employee ID</td>
				<td><input type="text" name="Employee_ID"></td>
			</tr>
			<tr>
				<td>First Name:</td>
				<td><input type="text" name="FirstName"></td>
			</tr>
			<tr>
				<td>Last Name:</td>
				<td><input type="text" name="LastName"></td>
			</tr>
			<tr>
				<td>Age</td>
				<td><input type="text" name="Age"></td>
			</tr>
			<tr>
				<td>City</td>
				<td><input type="text" name="city"></td>
			</tr>
			<tr>
				<td>Mobile Number</td>
				<td><input type="text" name="Mobile" pattern="[7-9]{1}[0-9]{9}"></td>
			</tr>
			<tr>
				<td>Email ID</td>
				<td><input type="text"  name="Email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td>Category</td>
				
				<td><select name="cat">
				<option>----- ------</option>
				<option value=1>1.Manager</option>
				<option value=2>2.TL</option>
				<option value=3>3.Tester</option>	
				<option value=4>4.Developer</option>
				</select>	
			</tr>
			<tr>
				<td>Role</td>
				
				<td><select name="rol">
				<option>----- ------</option>
				<option value=1>1.SuperAdmin</option>
				<option value=2>2.Admin</option>
				<option value=3>3.User</option>
				</select>	
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="ADD"></td>
			</tr>

		</table>

	</form>
</body>
</html>