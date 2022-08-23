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

public class Redirect extends HttpServlet{

     public void doGet(HttpServletRequest req,HttpServletResponse res){

        doPost(req,res);

     }
   
    public void doPost(HttpServletRequest req,HttpServletResponse res){
            String page=null;
            try{
            if(req.isUserInRole("1")){
                page="SuperAdmin.jsp";
            }
            else if(req.isUserInRole("2")){
                 page="Admin.jsp";
            }
            else if(req.isUserInRole("3")){
                page="User.jsp";
            } 
            
           //req.getRequestDispatcher(page).forward(req,res);
             res.sendRedirect(page);
        }
             catch(Exception e){
                System.out.println(e);
             }
     }

}