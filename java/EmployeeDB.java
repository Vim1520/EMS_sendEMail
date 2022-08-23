import java.io.*;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EmployeeDB {
	 public static void connection()  {
		 	try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con= DriverManager.getConnection("jdbc:mysql://localhost:3306/employeedb","root","Vimal@2002");
			      
			} catch (Exception e) {
				System.out.println(e);
			}
	   }
	static Connection con;  
	static int id;
	static String pass;
	static String FirstName;
	static String lastName;												
	static String mobNum;
	static String email_id;
	static String cat;
	static String Age;
	static String city;
	static String Role;
    static int cid;
    static int rid;
	

	static void AddEmp(){

		PreparedStatement smt=null;
		String query;
		try{
		connection();
		query="INSERT INTO EMPLOYEEDB (EmpID,EmpFirstName,EmpLastName,Age,City,EmpMobno,EmpEmailID,Pass,Cid,Rid) VALUES(?,?,?,?,?,?,?,?,?,?)";
		smt=con.prepareStatement(query);
		smt.setInt(1, id);
		smt.setString(2, FirstName);
		smt.setString(3, lastName);
		smt.setString(4, Age);
		smt.setString(5, city);
		smt.setString(6, mobNum);
		smt.setString(7, email_id);
		smt.setString(8, pass);
		smt.setInt(9, cid);
		smt.setInt(10, rid);
		int cnt = smt.executeUpdate();
		
	
	
	
	
	}
		catch(Exception e){
			System.out.println(e);
		}

	}


	static void SerID(int eid){

		PreparedStatement smt=null;
		try{
			connection();
			String query="select e.empid,e.EmpFirstName,e.EmpLastName,e.age,e.city,e.EmpMobno,e.EmpEmailID,r.role,c.category from employeedb as e left join cat as c on c.id = e.cid left join rol as r on e.rid=r.id where e.EmpID=?;";
			smt=con.prepareStatement(query);
			smt.setInt(1, eid);	
			int j=0;
			ResultSet rs=con.executeUpdat();
			while(rs.next()){
				j++;

			}
			if(j==0){
			
			}
		}
		catch(Exception e){
			System.out.println(e);
		}

	}

	static void SerCat(){

		PreparedStatement smt=null;
		try{
			connection();
			String query="select e.empid,e.EmpFirstName,e.EmpLastName,e.age,e.city,e.EmpMobno,e.EmpEmailID,r.role,c.category from employeedb as e left join cat as c on c.id = e.cid left join rol as r on e.rid=r.id where e.cid=?;";
			smt=con.prepareStatement(query);
			smt.setInt(1, cid);			
			int j=0;
			ResultSet rs=con.executeUpdat();
			while(rs.next()){
				j++;

			}
			if(j==0){
			
			}
		}
		catch(Exception e){
			System.out.println(e);
		}

	}

	static void DispData() {
		
		PreparedStatement smt=null;
		try{
			connection();
			String query="select e.empid,e.EmpFirstName,e.EmpLastName,e.age,e.city,e.EmpMobno,e.EmpEmailID,r.role,c.category from employeedb as e left join cat as c on c.id = e.cid left join rol as r on e.rid=r.id";
			smt=con.prepareStatement(query);
			int j=0;
			ResultSet rs=smt.executeQuery();
			while(rs.next()) {
				j++;

			}
			if(j==0){

			}
		}
		catch(Exception e){
			System.out.println(e);
		}
	}

	static void DelData(){
		PreparedStatement smt=null;
		Statement smt1=null;
		try{
			connection();
			String query1="select count(*) rid from employeedb where rid=1";
			smt1=con.createStatement();
			ResultSet rs=smt1.executeQuery(query1);
			int cnt1=rs.getInt(1);
			//System.out.println(cnt1);
			if(cnt1==1) {			
			String query="DELETE FROM EMPLOYEEDB WHERE EmpID=? And Rid!=1";
			smt=con.prepareStatement(query);
			smt.setInt(1, id);
			int cnt = smt.executeUpdate();
		}
		else if(cnt1>1){
			String query="DELETE FROM EMPLOYEEDB WHERE EmpID=?";
		smt=con.prepareStatement(query);
		smt.setInt(1, eid);
		int cnt = smt.executeUpdate();
		File file = new File("Employee_"+eid+".txt");
		  if(file.exists())
		   {
			 if(file.delete());
			 {
			   System.out.println("\nEmployee has been removed Successfully");
			 }
		   }
		if(cnt==0) {
		
		}else {
	
		}
		}
		else{
		
		}
		}
		catch(Exception e){
			System.out.println(e);
		}



	}

	static int getCid(String cate) {
		try {
		
		PreparedStatement smt1=null;
		String query="SELECT ID FROM CAT WHERE CATEGORY=? ";
		smt1=con.prepareStatement(query);
		smt1.setString(1, cate);
		ResultSet rs=smt1.executeQuery();
		rs.next();
		cid=rs.getInt("ID");
		}
		catch (Exception e) {
			//System.out.println("Invalid category");
			//System.out.println(e);
		}
		return cid;
	}
	
	static int getRid(String role) {
		try {
		
		PreparedStatement smt1=null;
		String query="SELECT id FROM ROL WHERE Role=? ";
		smt1=con.prepareStatement(query);
		smt1.setString(1, role);
		ResultSet rs=smt1.executeQuery();
		rs.next();
		rid=rs.getInt(1);
		}
		catch (Exception e) {
			//System.out.println("Invalid Role");
			//System.out.println(e);
		}
		return rid;
	}
	

	static void ExpEmp(int eid) {
		
		PreparedStatement smt=null;
		
		
		try{
			connection();
			String query="select e.empid,e.EmpFirstName,e.EmpLastName,e.age,e.city,e.EmpMobno,e.EmpEmailID,r.role,c.category from employeedb as e left join cat as c on c.id = e.cid left join rol as r on e.rid=r.id where e.EmpID=?;";
			smt=con.prepareStatement(query);
			smt.setInt(1, eid);			
			ResultSet rs=smt.executeQuery();
			int j=0;
			while(rs.next()) {
				j=1;
		            File f1=new File("Employee_"+eid+".txt");
		            if(f1.createNewFile()){
		                FileWriter myWriter = new FileWriter("Employee_"+eid+".txt");
		                myWriter.write("------------------------------\n\t Employee Details\n------------------------------"+"\n"+
		                "Employee ID : "+rs.getInt(1)+"\n"+"First Name  : "+rs.getString(2)+"\n"+
		                "Last Name   : "+rs.getString(3)+"\n"+"Age         : "+rs.getInt(4)+"\n"+
		                "City        : "+rs.getString(5)+"\n"+"Mobile No.  : "+rs.getString(6)+"\n"+
		                "Email Id    : "+rs.getString(7)+"\n"+"Role        : "+rs.getString(8)+"\nCategory    : "+rs.getString(9)+"\n");
		                myWriter.close();
		                System.out.println("\nEmployee Details Exported\n");

		            }
		            else {
		           
					}
		    }
			if(j==0) {
			
			}
			
		}
		catch (Exception e) {
			  e.printStackTrace();
		}
	  }
	
	static void ClrExps() {
		
		Statement smt=null;
		
		try{
			connection();
			String query="SELECT EmpID FROM EMPLOYEEDB";
			smt=con.createStatement();
			ResultSet rs=smt.executeQuery(query);
			int j=0;
			while(rs.next()) {
			File file = new File("Employee_"+rs.getInt(1)+".txt");
	       if(file.exists())
	       {
	         if(file.delete());
	         {
	          j++;
	         }
	       }
		}
			if(j==0) {
				
			}
			else {
			
			}
	}
		catch(Exception e) {
			System.out.println(e);
		}
	}

	static void chpass(int eid) {
		Scanner sc=new Scanner(System.in);
		PreparedStatement smt=null;
		try {
			connection();
			String query = "UPDATE EMPLOYEEDB SET PASS=? WHERE EMPID=?";
			smt=con.prepareStatement(query);
			System.out.println("Enter the New Password :");
			pass=sc.next();
			smt.setString(1, pass);
			smt.setInt(2, eid);	
			smt.executeUpdate();
		//	sc.close();
		}
		catch (Exception e) {
			System.out.println(e);
			}
	}


}

	