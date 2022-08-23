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
import java.util.*;

public class Exp extends HttpServlet{
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
    ExpEmp(res);
    
}

static void ExpEmp(HttpServletResponse res) {
		
    PreparedStatement smt=null;
    
    
    try{
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();   
       	 connection();
        String query="select e.empid,e.EmpFirstName,e.EmpLastName,e.age,e.city,e.EmpMobno,e.EmpEmailID,r.role,c.category from employeedb as e left join cat as c on c.id = e.cid left join rol as r on e.rid=r.id where e.EmpID=?;";
        smt=con.prepareStatement(query);
        smt.setInt(1, id2);			
        ResultSet rs=smt.executeQuery();
        int j=0;
        while(rs.next()) {
            j=1;
                File f1=new File("Employee_"+id2+".txt");
                if(f1.createNewFile()){
                    FileWriter myWriter = new FileWriter("Employee_"+id2+".txt");
                    myWriter.write("------------------------------\n\t Employee Details\n------------------------------"+"\n"+
                    "Employee ID : "+rs.getInt(1)+"\n"+"First Name  : "+rs.getString(2)+"\n"+
                    "Last Name   : "+rs.getString(3)+"\n"+"Age         : "+rs.getInt(4)+"\n"+
                    "City        : "+rs.getString(5)+"\n"+"Mobile No.  : "+rs.getString(6)+"\n"+
                    "Email Id    : "+rs.getString(7)+"\n"+"Role        : "+rs.getString(8)+"\nCategory    : "+rs.getString(9)+"\n");
                    myWriter.close();
                    out.println("<h2>Employee Details Exported</h2>");

                }
                else {
                    out.println("<h2>Details of The Employee Already Exported </h2>");
                }
        }
        if(j==0) {
            out.println("<h2>-----Invalid ID-----</h2>");
        }
        out.print("<form action=\"Redirect\" method=\"post\"><h1><center>Go Back</center>");
        out.print("</h1><center>To Go Back to the Main Menu !!!<table><tr><td><input type=\"submit\" value=\"__\"></td>");
        out.print("</tr></center></table></form>");
        
    }
    catch (Exception e) {
          e.printStackTrace();
    }
   
		
  }

  
}