import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.text.*;


public class Edit extends HttpServlet{
    
    static int id;
	static String FirstName=null;
	static String LastName=null;												
	static String mobNum=null;
	static String email_id=null;
	static String cat=null;
	static String Age=null;
	static String city=null;
	static String Role=null;
    static int cid;
    static int rid;
    static Connection con;
    
    public static void connection()  {
        try {
           Class.forName("com.mysql.cj.jdbc.Driver");
           con= DriverManager.getConnection("jdbc:mysql://localhost:3306/employeedb","root","Vimal@2002");
             
       } catch (Exception e) {
           System.out.println(e);
       }
  }



    protected void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException , IOException{
        
        res.setContentType("text/html");
		PrintWriter out = res.getWriter();
        String id2=null;
		int j=0;
        int flag=0;
		//id2=(String)req.getAttribute("eid");
        id2= req.getParameter("eid");
        id=Integer.parseInt(id2);

        if(isalid(id)){
        try{
        String query;
        FirstName=(String)req.getParameter("FirstName");
        LastName=(String)req.getParameter("LastName");
        Age=(String)req.getParameter("Age");
        city=(String)req.getParameter("city");
        mobNum=(String)req.getParameter("Mobile");
        email_id=(String)req.getParameter("Email");
     
        connection(); 
        if(req.getParameter("FirstName")!=null){
          
          FN(res);
           }
        
        if(req.getParameter("LastName")!=null){
            
            LN(res);
            }
         
         if(req.getParameter("Age")!=null){
            A(res);
            }
        if(req.getParameter("city")!=null){
            C(res);
        }
        if(req.getParameter("Mobile")!=null){
            Mob(res);
        }
        if(req.getParameter("Email")!=null){
            EM(res);
        }
        if(req.getParameter("cat")!=null){
            cat=req.getParameter("cat");
            cid=Integer.parseInt(cat);
            ct(res);
        }
        if(req.getParameter("rol")!=null){
            Role=req.getParameter("rol");
            rid=Integer.parseInt(Role);
            rl(res);
        }
       
   	}
		catch(Exception e){
			System.out.println(e);
		}
        }
        else{
            out.print("<h1>Sorry No ID exists. . . . .</h1>");
            out.print("<br><form>");
            out.print("<input type=\"submit\" value=\"Retry\" formaction=\"Edit.jsp\">");
            out.print("</form>");
            
        }
        res.setContentType("text/html");
		PrintWriter o = res.getWriter();
        o.print("<form action=\"SuperAdmin.jsp\" method=\"post\"><h1><center>Go Back</center>");
        o.print("</h1><center>To Go Back to the Main Menu !!!<table><tr><td><input type=\"submit\" value=\"__\"></td>");
        o.print("</tr></center></table></form>");
    }
static void FN(HttpServletResponse res){
    PreparedStatement smt=null;
    try{
    connection();
    res.setContentType("text/html");
		PrintWriter out = res.getWriter();
    smt=con.prepareStatement("Update EMployeedb set EmpFirstName = ? where EmpID=?");
    smt.setString(1, FirstName);
    smt.setInt(2, id);
    if(smt.executeUpdate()==0) {
    
        out.print("Error Updating First name!!!!");
    }
    else{
        out.println("<br>");
        out.println("FirstName UPDATED");
    }
}
catch(Exception e){
    System.out.println(e);
}
}

static void LN(HttpServletResponse res){
    PreparedStatement smt=null;
    try{
    connection();
    res.setContentType("text/html");
		PrintWriter out = res.getWriter();
    smt=con.prepareStatement("Update EMployeedb set EmpLastName = ? where EmpID=?");
    smt.setString(1, LastName);
    smt.setInt(2, id);
    if(smt.executeUpdate()==0) {
    
        out.print("Error Updating Last name!!!!");
    }
    else{
        out.println("<br>");
        out.println("LastName UPDATED");
    }
}
catch(Exception e){
    System.out.println(e);
}
}

static void A(HttpServletResponse res){
    PreparedStatement smt=null;
    try{
    connection();
    res.setContentType("text/html");
		PrintWriter out = res.getWriter();
    smt=con.prepareStatement("Update EMployeedb set Age = ? where EmpID=?");
    smt.setString(1, Age);
    smt.setInt(2, id);
    if(smt.executeUpdate()==0) {
    
        out.print("Error Updating Age!!!!");
    }
    else{
        out.println("<br>");
        out.println("Age UPDATED");
    }
    
}
catch(Exception e){
    System.out.println(e);
}
}


static void C(HttpServletResponse res){
    PreparedStatement smt=null;
    try{
    connection();
    res.setContentType("text/html");
		PrintWriter out = res.getWriter();
    smt=con.prepareStatement("Update EMployeedb set city = ? where EmpID=?");
    smt.setString(1, city);
    smt.setInt(2, id);
    if(smt.executeUpdate()==0) {
    
        out.print("Error Updating City!!!!");
    }else{
        out.println("<br>");
        out.println("City UPDATED");
    }
}

catch(Exception e){
    System.out.println(e);
}
}


static void Mob(HttpServletResponse res){
    PreparedStatement smt=null;
    try{
    connection();
    res.setContentType("text/html");
		PrintWriter out = res.getWriter();
    smt=con.prepareStatement("Update EMployeedb set EmpMobno = ? where EmpID=?");
    smt.setString(1, mobNum);
    smt.setInt(2, id);
    if(smt.executeUpdate()==0) {
    
        out.print("Error Updating Mobile number!!!!");
    }else{
        out.println("<br>");
        out.println("Mobile number UPDATED");
    }
    }
    
catch(Exception e){
    System.out.println(e);
}
}

static void EM(HttpServletResponse res){
    PreparedStatement smt=null;
    try{
    connection();
    res.setContentType("text/html");
		PrintWriter out = res.getWriter();
    smt=con.prepareStatement("Update EMployeedb set EmpEmailID = ? where EmpID=?");
    smt.setString(1, email_id);
    smt.setInt(2, id);
    if(smt.executeUpdate()==0) {
    
        out.print("Error Updating Email ID!!!!");
    }else{
        out.println("<br>");
        out.println("Email ID UPDATED");
    }
}    
catch(Exception e){
    System.out.println(e);
}
}

static void ct(HttpServletResponse res){
    PreparedStatement smt=null;
    try{
    connection();
    res.setContentType("text/html");
		PrintWriter out = res.getWriter();
        out.print("<html><body>");
    smt=con.prepareStatement("Update EMployeedb set Cid = ? where EmpID=?");
    smt.setInt(1, cid);
    smt.setInt(2, id);
    if(smt.executeUpdate()==0) {
    
        out.print("Error Updating Category!!!!");
    }
    else{
        out.println("<br>");
        out.println("Category UPDATED");
    }
}

catch(Exception e){
    System.out.println(e);
}
}

static void rl(HttpServletResponse res){
    PreparedStatement smt=null;
    try{
    connection();
    res.setContentType("text/html");
		PrintWriter out = res.getWriter();
    smt=con.prepareStatement("Update EMployeedb set Rid = ? where EmpID=?");
    smt.setInt(1, rid);
    smt.setInt(2, id);
    if(smt.executeUpdate()==0) {
    
        out.print("Error Updating Role!!!!");
    }
    else{
        out.println("<br>");
        out.println("Role UPDATED");
    }
}
catch(Exception e){
    System.out.println(e);
}
}
    static boolean isalid(int eid) {
        PreparedStatement smt=null;
        try{
            connection();
            String query="SELECT * FROM EMPLOYEEDB WHERE EmpID="+"?";
            smt=con.prepareStatement(query);
            smt.setInt(1, eid);
            
            ResultSet rs=smt.executeQuery();
            int j=0;
            while(rs.next()) {
                j=1;
            }
            if(j!=0) {
                return true;
            }
            
        }
        catch (Exception e) {
             System.out.println(e);
         }
        return false;
        }	
}
