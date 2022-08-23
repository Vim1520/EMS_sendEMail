import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.net.*;  
import java.io.*;  
import java.net.URL;
import java.io.IOException;  
import java.io.PrintWriter;     
import javax.servlet.http.HttpSession;  
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
import java.util.Properties;  
import javax.mail.*;  
import javax.mail.internet.*;  
import javax.mail.Authenticator;

 
public class Chpass extends HttpServlet{


    static Connection con=null;
    static String emailid=null;
    static String pass=null;
    static String id = null;
    static int id2;
    public static void connection()  {
        try {
           Class.forName("com.mysql.cj.jdbc.Driver");
           con= DriverManager.getConnection("jdbc:mysql://localhost:3306/employeedb","root","Vimal@2002");
             
       } catch (Exception e) {
           System.out.println(e);
       }
  }

    public void doPost(HttpServletRequest req,HttpServletResponse res){
        HttpSession session=req.getSession();
        id = (String) session.getAttribute("EmpID");
        id2=Integer.parseInt(id);
        pass=req.getParameter("pass");
        int j=getUrlContents(encryptThisString(pass));
   if(j==0){
        chpass(res);
    }
    else{try
    {     res.setContentType("text/plain");
         PrintWriter out = res.getWriter();
         out.print(0);
         // out.println("<h3>The PassWord is not Safe !!!....!!!! </h3> ");
         // out.println(" <h4>To Retry!!!</h4><form action=\"Chpass.jsp\" method=\"post\"><input type=\"submit\" value=\"Retry\"></form> ");
     }
     catch(Exception e){
        
     }
    }
}

    public static String encryptThisString(String input)
    {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }    
           return hashtext;  
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        
    }
    public static int getUrlContents(String hashtext)  
  {  
    String content = new String();  
    int res=0;
    try  
    {  String hashSub = (String)hashtext.substring(0,5).toUpperCase(); 
    System.out.println(hashtext);
    System.out.println(hashSub);
    System.out.println((hashtext).substring(5).toUpperCase());
    System.out.println((hashtext).substring(5,40).toUpperCase());
            URL URL = new URL("https://api.pwnedpasswords.com/range/" + hashSub); 
            BufferedReader br = new BufferedReader(new InputStreamReader(URL.openStream()));

            while ((content = br.readLine()) != null) {
                /* read each line */
                //System.out.println(content);
                 if(content.substring(0,35).equals((hashtext).substring(5,40).toUpperCase()))
                 {
                    res=1;
                    System.out.println("Oh Shot You were Breached !!!..");
                    break;
                }
            }
            br.close(); 
    }  
    catch(Exception e)  
    {   
     e.printStackTrace();  
    }  
    return res;  
  }  

static void chpass(HttpServletResponse res) {
        PreparedStatement smt=null,smt1=null;
        try {
            res.setContentType("text/plain");
            PrintWriter out = res.getWriter(); 
            connection();
            String query = "UPDATE EMPLOYEEDB SET PASS=? WHERE EMPID=?";
            smt=con.prepareStatement(query);
            smt.setString(1, pass);
            smt.setInt(2, id2); 
            int cnt=0;
            cnt=smt.executeUpdate();
            if(cnt!=0){
                System.out.println("PassWord changed Successfully");

            }


        try{
            connection();
            query="SELECT EmpEmailID FROM EMPLOYEEDB WHERE EmpID="+"?";
            smt1=con.prepareStatement(query);
            smt1.setInt(1, id2);
            
            ResultSet rs=smt1.executeQuery();
            rs.next();
            emailid=rs.getString("EmpEmailID");
            
        }
        catch (Exception e) {
             System.out.println(e);
         }
           


        String host="mail.gmail.com";  
        final String user="vimal200302@gmail.com";//change accordingly  
        final String password="kwakpiiofekipyya";//change accordingly 
        String to=emailid; 
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // Host for gmail is smtp.gmail
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true"); // We have to set it true as gmail requires authentication
        props.put("mail.smtp.port", "465");
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                        return new javax.mail.PasswordAuthentication(user,password);
                    }
                });
        try {
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.setSubject("PassWord Changed");
            message.setText("PassWord Changed Successfully !!!1");
            Transport.send(message);
            System.out.println("message sent successfully");
        } catch (MessagingException e) {
         throw new RuntimeException(e);
         }

            // out.print("<form action=\"Redirect\" method=\"post\"><h1><center>Go Back</center>");
            // out.print("</h1><center>To Go Back to the Main Menu !!!<table><tr><td><input type=\"submit\" value=\"__\"></td>");
            // out.print("</tr></center></table></form>");
        out.println(1);

        }
        catch (Exception e) {
            System.out.println(e);
            }
    }

 }