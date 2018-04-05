<%@ page import="Entity.User" %>
<%@ page import="Bean.LoginBean" %>
<%@ page import="Utils.UserSingleton" %><%--
  Created by IntelliJ IDEA.
  User: mattia
  Date: 12/03/18
  Time: 14.23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Si dichiara la variabile loginBean e istanzia un oggetto LoginBean -->
<jsp:useBean id="loginBean" scope="request"
             class="Bean.LoginBean"/>

<jsp:setProperty name="loginBean" property="*"/>


<%

  if (request.getParameter("submit_"+"1") != null){

      String username = request.getParameter("username");
      String password = request.getParameter("password");

      if(username.isEmpty() || password.isEmpty()){
        String info = "alert('Completare tutti i campi!');";
        out.println("<script type=\"text/javascript\">");
        out.println(info);
        out.println("location='index.jsp';");
        out.println("</script>");
      }else {

        if(loginBean.validate()) {

          User user = UserSingleton.getInstance().getUser();
          if(user.getType().equals("1")){

            response.sendRedirect("secretaryPage.jsp");
            System.out.println("Segretaria");

          }else{

            response.sendRedirect("profPage.jsp");
          }

        }else {
          String info = "alert('Username o password incorretti!');";
          out.println("<script type=\"text/javascript\">");
          out.println(info);
          out.println("location='index.jsp';");
          out.println("</script>");
        }
      }
  }
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Classroom's Handler</title>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!--===============================================================================================-->
  <link rel="icon" type="image/png" href="login/images/icons/favicon.ico"/>
  <!--===============================================================================================-->
  <link rel="stylesheet" type="text/css" href="login/vendor/bootstrap/css/bootstrap.min.css">
  <!--===============================================================================================-->
  <link rel="stylesheet" type="text/css" href="loginfonts/font-awesome-4.7.0/css/font-awesome.min.css">
  <!--===============================================================================================-->
  <link rel="stylesheet" type="text/css" href="login/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
  <!--===============================================================================================-->
  <link rel="stylesheet" type="text/css" href="login/vendor/animate/animate.css">
  <!--===============================================================================================-->
  <link rel="stylesheet" type="text/css" href="login/vendor/css-hamburgers/hamburgers.min.css">
  <!--===============================================================================================-->
  <link rel="stylesheet" type="text/css" href="login/vendor/animsition/css/animsition.min.css">
  <!--===============================================================================================-->
  <link rel="stylesheet" type="text/css" href="login/vendor/select2/select2.min.css">
  <!--===============================================================================================-->
  <link rel="stylesheet" type="text/css" href="login/vendor/daterangepicker/daterangepicker.css">
  <!--===============================================================================================-->
  <link rel="stylesheet" type="text/css" href="login/css/util.css">
  <link rel="stylesheet" type="text/css" href="login/css/main.css">
  <!--===============================================================================================-->
</head>
<body>

<div class="limiter">
  <div class="container-login100">
    <div class="wrap-login100">
      <div class="login100-form-title" style="background-image: url(login/images/bg-01.jpg);">
					<span class="login100-form-title-1">
						University of Tor Vergata
					</span>
      </div>

      <form action="" method="post" class="login100-form validate-form">
        <div class="wrap-input100 validate-input m-b-26" data-validate="Username is required">
          <span class="label-input100">Username</span>
          <input class="input100" type="text" name="username" placeholder="Enter username">
          <span class="focus-input100"></span>
        </div>

        <div class="wrap-input100 validate-input m-b-18" data-validate = "Password is required">
          <span class="label-input100">Password</span>
          <input class="input100" type="password" name="password" placeholder="Enter password">
          <span class="focus-input100"></span>
        </div>

        <div class="flex-sb-m w-full p-b-30">
        </div>

        <div class="container-login100-form-btn">
          <button class="login100-form-btn" type="submit" style="margin-left: 50px;" name="submit_1" value="Login">
            Login
          </button>
        </div>

      </form>
    </div>
  </div>
</div>

<!--===============================================================================================-->
<script src="vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/bootstrap/js/popper.js"></script>
<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/daterangepicker/moment.min.js"></script>
<script src="vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
<script src="vendor/countdowntime/countdowntime.js"></script>
<!--===============================================================================================-->
<script src="js/main.js"></script>

</body>
</html>
