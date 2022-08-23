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
import java.util.*;

public class SearchID extends HttpServlet{
    static Connection con=null;
    static int id2;
    static String id=null;
    static String cat=null;
    static int cid;
    static String role=null;
    static int rid;
    public static void connection()  {
        try {
           Class.forName("com.mysql.cj.jdbc.Driver");
           con= DriverManager.getConnection("jdbc:mysql://localhost:3306/employeedb","root","Vimal@2002");
             
       } catch (Exception e) {
           System.out.println(e);
       }
  }
  protected void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException , IOException{
    if(req.getParameter("Id")!=null){
        id=req.getParameter("Id");
        id2=Integer.parseInt(id);
        SerID(res);
    }    
    else if(req.getParameter("cat")!=null){
        cat=req.getParameter("cat");
        cid=Integer.parseInt(cat);
        SerCat(res);
    }
    else if(req.getParameter("rol")!=null){
        role=req.getParameter("rol");
        rid=Integer.parseInt(role);
        SerRol(res);
    }
  }
static void SerID(HttpServletResponse res){
            PreparedStatement smt=null;
            try{
            res.setContentType("text/html");
            PrintWriter out = res.getWriter();   
			connection();
			String query="select e.empid,e.EmpFirstName,e.EmpLastName,e.age,e.city,e.EmpMobno,e.EmpEmailID,r.role,c.category from employeedb as e left join cat as c on c.id = e.cid left join rol as r on e.rid=r.id where e.EmpID=?;";
			smt=con.prepareStatement(query);
			smt.setInt(1, id2);			
			ResultSet resultset=smt.executeQuery();
			int j=0;
			while(resultset.next()) {
				j=1;
				out.print("<html><body><center><h2>Details</h2><TABLE border =\"4\" cellpadding =\"4\" cellspacing=\"4\"><TR>");
                out.print("<TH>ID</TH>");
                out.print("<TH>First Name</TH>");
                out.print("<TH>Last Name</TH>");
                out.print("<TH>Age</TH>");
                out.print("<TH>City</TH>");
                out.print("<TH>Mobile</TH>");
                out.print("<TH>Email</TH>");
                out.print("<TH>Role</TH>");
                out.print("<TH>Category</TH>");
                out.print("</TR>    <TR>");
                out.print(" <TD> " +resultset.getString(1)+" </td>");
                out.print(" <TD>" +resultset.getString(2) +"</td>");
                out.print(" <TD> "+ resultset.getString(3)+" </td>");
                out.print(" <TD> "+resultset.getString(4) +"</td>");
                out.print(" <TD> "+ resultset.getString(5) +"</td>");
                out.print(" <TD> "+ resultset.getString(6) +"</td>");
                out.print(" <TD> "+resultset.getString(7) +"</td>");
                out.print(" <TD> "+ resultset.getString(8) +"</td>");
                out.print(" <TD>"+  resultset.getString(9) +"</td>");
                out.print("</td> </TR></TABLE></center></BODY></HTML>");
            }
			if(j==0) {
				out.print("<html><body><center><h1>-----Invalid ID-----</h1></center></body></html>");
			}
			out.print("<form action=\"Redirect\" method=\"post\"><h1><center>Go Back</center>");
            out.print("</h1><center>To Go Back to the Main Menu !!!<table><tr><td><input type=\"submit\" value=\"__\"></td>");
		    out.print("</tr></center></table></form>");
		
		}
		catch (Exception e) {
	         System.out.println(e);
	     }
        }

        static void SerCat(HttpServletResponse res) {
            PreparedStatement smt=null;
            try{
            connection();
            res.setContentType("text/html");
            PrintWriter out = res.getWriter();   
			connection();
			String query="select e.empid,e.EmpFirstName,e.EmpLastName,e.age,e.city,e.EmpMobno,e.EmpEmailID,r.role,c.category from employeedb as e left join cat as c on c.id = e.cid left join rol as r on e.rid=r.id where e.Cid=?;";
			smt=con.prepareStatement(query);
			smt.setInt(1, cid);			
			ResultSet resultset=smt.executeQuery();
			int j=0;
            out.print("<html><body><center><h2>Details</h2><TABLE border =\"4\" cellpadding =\"4\" cellspacing=\"4\"><TR>");
            out.print("<TH>ID</TH>");
            out.print("<TH>First Name</TH>");
            out.print("<TH>Last Name</TH>");
            out.print("<TH>Age</TH>");
            out.print("<TH>City</TH>");
            out.print("<TH>Mobile</TH>");
            out.print("<TH>Email</TH>");
            out.print("<TH>Role</TH>");
            out.print("<TH>Category</TH></TR>");

			while(resultset.next()) {
				j++;
                
                out.print("<TR><TD> " +resultset.getString(1)+" </td>");
                out.print(" <TD>" +resultset.getString(2) +"</td>");
                out.print(" <TD> "+ resultset.getString(3)+" </td>");
                out.print(" <TD> "+resultset.getString(4) +"</td>");
                out.print(" <TD> "+ resultset.getString(5) +"</td>");
                out.print(" <TD> "+ resultset.getString(6) +"</td>");
                out.print(" <TD> "+resultset.getString(7) +"</td>");
                out.print(" <TD> "+ resultset.getString(8) +"</td>");
                out.print(" <TD>"+  resultset.getString(9) +"</td>");
            }
            out.print("</td> </TR></TABLE></center></BODY></HTML>");
			if(j==0) {
				out.print("<html><body><center><h1>----- Empty -----</h1></center></body></html>");
			}
            out.print("<form action=\"Redirect\" method=\"post\"><h1><center>Go Back</center>");
            out.print("</h1><center>To Go Back to the Main Menu !!!<table><tr><td><input type=\"submit\" value=\"__\"></td>");
		    out.print("</tr></center></table></form>");
		  }   
            catch(Exception e){
               System.out.println(e);
            }  
        }  


        static void SerRol(HttpServletResponse res) {
            PreparedStatement smt=null;
            try{
            connection();
            res.setContentType("text/html");
            PrintWriter out = res.getWriter();   
            connection();
            String query="select e.empid,e.EmpFirstName,e.EmpLastName,e.age,e.city,e.EmpMobno,e.EmpEmailID,r.role,c.category from employeedb as e left join cat as c on c.id = e.cid left join rol as r on e.rid=r.id where e.Rid=?;";
            smt=con.prepareStatement(query);
            smt.setInt(1, rid);         
            ResultSet resultset=smt.executeQuery();
            int j=0;
            out.print("<html><body><center><h2>Details</h2><TABLE border =\"4\" cellpadding =\"4\" cellspacing=\"4\"><TR>");
            out.print("<TH>ID</TH>");
            out.print("<TH>First Name</TH>");
            out.print("<TH>Last Name</TH>");
            out.print("<TH>Age</TH>");
            out.print("<TH>City</TH>");
            out.print("<TH>Mobile</TH>");
            out.print("<TH>Email</TH>");
            out.print("<TH>Role</TH>");
            out.print("<TH>Category</TH></TR>");

            while(resultset.next()) {
                j++;
                
                out.print("<TR><TD> "+resultset.getString(1)+" </td>");
                out.print(" <TD>" +resultset.getString(2) +"</td>");
                out.print(" <TD> "+ resultset.getString(3)+" </td>");
                out.print(" <TD> "+resultset.getString(4) +"</td>");
                out.print(" <TD> "+ resultset.getString(5) +"</td>");
                out.print(" <TD> "+ resultset.getString(6) +"</td>");
                out.print(" <TD> "+resultset.getString(7) +"</td>");
                out.print(" <TD> "+ resultset.getString(8) +"</td>");
                out.print(" <TD>"+  resultset.getString(9) +"</td>");
            }
            out.print("</td> </TR></TABLE></center></BODY></HTML>");
            if(j==0) {
                out.print("<html><body><center><h1>----- Empty -----</h1></center></body></html>");
            }
            out.print("<form action=\"Redirect\" method=\"post\"><h1><center>Go Back</center>");
            out.print("</h1><center>To Go Back to the Main Menu !!!<table><tr><td><input type=\"submit\" value=\"__\"></td>");
            out.print("</tr></center></table></form>");
          }   
            catch(Exception e){
               System.out.println(e);
            }  
        }  
}