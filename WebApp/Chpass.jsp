<HTML>
<HEAD>
<TITLE> Change Password </TITLE>
</HEAD>
<body>
<%
response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setHeader("Expires", "0");
	
if(session.getAttribute("EmpID")==null)
	{
		response.sendRedirect("login.jsp");
	}
%>
<script  src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
  $(document).ready(function(){
    var res=0;
    $('#frm').on('submit', function(e){ 
      e.preventDefault();
      var pas=$("#pass").val();
      
      var cfpas=$("#cfpass").val();
      if(pas==cfpas){
        res=true;
       formSubmit();
        return true;
      }
      else{
        $("#error").text("Password doesnt match!!!!");
        res=false;
        return false;
      }
      
    });     
  });
  function formSubmit(){
    var pass = document.getElementById("pass").value;
    $.ajax({
      url: 'Chpass',
      method: 'post',
      data: { pass: pass},
      success:
      function (response) {
        console.log("response->"+response);
       if(response==1){
        alert('Success Your Password was Changed !!!!  Press OK to Continue to the Menu Page');
        window.location.href = "Redirect";
       }
       else if(response==0){
        alert('Error in changing the Password....!!!....!!!! Click OK to Retry !!!');
        window.location.href = "Chpass.jsp";
       }

 },

      error: function (httpRequest, textStatus, errorThrown) {  
                alert("Error: " + textStatus + " " + errorThrown + " " + httpRequest);
                
            }
      });
}

</script>

 


<h3>------ Change Password ------</h3>
<form   id="frm">
<table>
<tr>
<td>Enter New Password :</td>
<td><input type="text" class="form-control" name="pass" id="pass" required></td></tr>
<tr>
<td>Confirm New Password :</td>
<td><input type="text" class="form-control" name="cfpass" id="cfpass" required></td></tr>
<td><span id="error" style="color:red"></span> </td>
</TABLE><br>
<input type="submit" value="Change">
</form>

</BODY>
</HTML>