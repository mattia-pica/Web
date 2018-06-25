<%--
  Created by IntelliJ IDEA.
  User: mattia
  Date: 23/06/18
  Time: 15.21
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="Control.Controller" %>
<%@ page import="java.time.LocalTime" %>
<%@ page import="Bean.Disponible_RoomBean" %>
<%@ page import="java.time.format.DateTimeParseException" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.ParseException" %>
<%@ page import="java.util.regex.Pattern" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Si dichiara la variabile loginBean e istanzia un oggetto LoginBean -->


<!-- Mappa automaticamente tutti gli attributi dell'oggetto loginBean e le proprietà JSP -->

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Classroom's Handler</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="table/images/icons/favicon.ico"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="table/vendor/bootstrap/css/bootstrap.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="table/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="table/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="table/vendor/animate/animate.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="table/vendor/css-hamburgers/hamburgers.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="table/vendor/animsition/css/animsition.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="table/vendor/select2/select2.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="table/vendor/daterangepicker/daterangepicker.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="table/css/util.css">
    <link rel="stylesheet" type="text/css" href="table/css/main.css">
    <link rel="stylesheet" type="text/css" href="login/css/main.css">
    <link rel="stylesheet" type="text/css" href="login/css/popup.css">

    <!--===============================================================================================-->
</head>
<body>

<div class="limiter">
    <div class="container-table100">
        <div class="wrap-table100">
            <div class="login100-form-title" style="background-image: url(login/images/bg-01.jpg);">

                <span class="login100-form-title-1">
						University of Tor Vergata
					</span>
            </div>
            <div class="table100 ver1 m-b-110">
                <div class="table100-head">
                    <table>
                        <thead>
                        <tr class="row100 head">
                            <th class="cell100 column1"></th>
                        </tr>
                        </thead>
                    </table>
                </div>

                <div class="table100-body js-pscroll" style="overflow:auto;">
                    <table id="table" >
                        <tbody>

                        <%

                            Controller controller = new Controller();

                            if(request.getParameter("submit_open") != null){  //Apri nuovo anno accademico

                                Pattern p = Pattern.compile("\\d{2}(/)\\d{2}(/)\\d{4}");

                                if (p.matcher(request.getParameter("dataInizio")).matches() &&
                                        p.matcher(request.getParameter("dataFine")).matches()){

                                    //Controllo che tra l'inizio e la fine passi un anno

                                    if ((Integer.parseInt(request.getParameter("dataInizio").substring(6)) !=
                                            (Integer.parseInt(request.getParameter("dataFine").substring(6)) - 1))){

                                        String info = "alert('Date inserite errate');";
                                        out.println("<script type=\"text/javascript\">");
                                        out.println(info);
                                        out.println("location='New_Acc.jsp';");
                                        out.println("</script>");
                                        return;
                                    }


                                    if (controller.newYear(request.getParameter("dataInizio"), request.getParameter("dataFine"))){

                                        String info = "alert('Nuovo anno accademico inserito');";
                                        out.println("<script type=\"text/javascript\">");
                                        out.println(info);
                                        out.println("location='secretaryPage.jsp';");
                                        out.println("</script>");

                                    }

                                }else {

                                    String info = "alert('Inserire i dati nel formato corretto!');";
                                    out.println("<script type=\"text/javascript\">");
                                    out.println(info);
                                    out.println("location='New_Acc.jsp';");
                                    out.println("</script>");
                                    return;

                                }

                            }

                        %>
                        </tbody>
                    </table>
                </div>
            </div>

        </div>

        <form action="" method="post" class="login100-form validate-form">

            <div class="wrap-input100 validate-input m-b-18" data-validate ="DataStart">
                <span class="label-input100">Data Inizio</span>
                <input class="input100" type="text" name="dataInizio" placeholder="Enter Accademic year start data in format dd/MM/yyyy">
                <span class="focus-input100"></span>
            </div>

            <div class="wrap-input100 validate-input m-b-18" data-validate ="DataEnd">
                <span class="label-input100">Data Fine</span>
                <input class="input100" type="text" name="dataFine" placeholder="Enter Accademic year end data in format dd/MM/yyyy">
                <span class="focus-input100"></span>
            </div>

            <div class="container-login100-form-btn">
                <button class="login100-form-btn" type="submit" name="submit_open" value="Open">
                    Apri Anno Accademico
                </button>
            </div>
        </form>
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>

<%--QUANDO SI CLICCA SULLA TEXTFIELD 'ALTRO' VENGONO DISATTIVATI I RADIO BUTTON--%>
<script>
    $('#textInput').click(function () {
        $('input[type=radio]').removeAttr("checked");

    });
</script>

</body>
</html>
