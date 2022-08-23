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

public class Delete extends HttpServlet{
    static Connection con=null;
    static int id2;
    static String id=null;
    public static void connection()  {
        try {
           Class.forName("com.mysql.cj.jdbc.Driver");
           con= DriverManager.getConnection("jdbc:mysql://localhost:3306/employeedb","root","Vimal@2002");
             
       } catch (Exception e) {
           System.out.println(e);
       }
  }
  protected void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException , IOException{
    id=req.getParameter("Id");
    id2=Integer.parseInt(id);
    DelData(res);

}
static void DelData(HttpServletResponse res){
    
    PreparedStatement smt=null;
		Statement smt1=null;
		try{
            res.setContentType("text/html");
            PrintWriter out = res.getWriter();   
       		connection();
			String query1="select count(*) rid from employeedb where rid=1";
			smt1=con.createStatement();
			ResultSet rs=smt1.executeQuery(query1);
			rs.next();
            int cnt=0;
			int cnt1=rs.getInt(1);
			//System.out.println(cnt1);
			if(cnt1==1 ) {			
			String query="DELETE FROM EMPLOYEEDB WHERE EmpID=? And rid!=1";
			smt=con.prepareStatement(query);
			smt.setInt(1, id2);
			 cnt = smt.executeUpdate();
			File file = new File("Employee_"+id2+".txt");
		      if(file.exists())
		       {
		         if(file.delete());
		         {
		           out.print("----------Employee has been removed Successfully----------------");
		         }
		       }
			}
			else if(cnt1>1){
			String query="DELETE FROM EMPLOYEEDB WHERE EmpID=?";
			smt=con.prepareStatement(query);
			smt.setInt(1, id2);
			cnt = smt.executeUpdate();
			File file = new File("Employee_"+id2+".txt");
		      if(file.exists())
		       {
		         if(file.delete());
		         {cnt++;
		            //out.println("\nEmployee has been removed Successfully");
		         }
		       }
			}
			else{
				out.println("----- Cannot delete -----");
			}
            
			if(cnt==0) {
				out.println("------ Cannot delete------\n");
			}else {
				// out.println("\t-----Delete Success-----");
			}
			out.print("<form action=\"SuperAdmin.jsp\" method=\"post\"><h1><center>Go Back</center>");
            out.print("</h1><center>To Go Back to the Main Menu !!!<table><tr><td><input type=\"submit\" value=\"__\"></td>");
		    out.print("</tr></center></table></form>");
		
			}
		catch (Exception e) {
	         e.printStackTrace();
	    }
		
}

}

