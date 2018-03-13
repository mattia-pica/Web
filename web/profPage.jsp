<%@ page import="java.sql.SQLException" %>
<%@ page import="Control.Controller" %>
<%@ page import="java.time.LocalTime" %>
<%@ page import="Bean.Disponible_RoomBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="Bean.LoginBean" %>
<%@ page import="java.lang.reflect.Method" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--<%@ page import="Entity.AbstractUser" %>
<%@ page import="Control.BookingController" %>
<%@ page import="DAO.Print_Teacher" %>
<%@ page import="DAO.Print_Secretary" %>--%>

<%--<jsp:useBean id="loginBean" scope="request"
             class="Bean.LoginBean"/>--%>



<%

    /*Disponible_RoomBean r = null;
    LocalTime timeInizio = null;
    LocalTime timeFine = null;
    String DateSearch = null;
    if (request.getParameter("submit_search") != null){
        String StartSearch = request.getParameter("start");
        String EndSearch = request.getParameter("end");
        DateSearch = request.getParameter("data");

        timeInizio = LocalTime.parse(StartSearch);
        timeFine = LocalTime.parse(EndSearch);

        for (int i = 0; i < r.getNome().size(); i++ ){
            String c = r.getNome().get(i);
            System.out.println(c);

            out.print("<tr class=\"row100 body\">");
            out.print("<td class=\"cell100 column1\">" + c + "</td>");

            out.print("<td class=\"cell100 column2\">" + rs1.getString(3) + "</td>");
            out.print("<td class=\"cell100 column3\">" + rs1.getString(4) + "</td>");
            out.print("<td class=\"cell100 column4\">" + rs1.getString(5) + "</td>");
            out.print("<td class=\"cell100 column5\">" + rs1.getString(6) + "</td>");
            out.print("<td class=\"cell100 column6\">" + rs1.getString(2) + "</td>");

        }
    }*/
%>

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
                            <th class="cell100 column1">Nome</th>
                            <%--<th class="cell100 column2">Data</th>
                            <th class="cell100 column3">Inizio</th>
                            <th class="cell100 column4">Fine</th>
                            <th class="cell100 column5">Da:</th>
                            <th class="cell100 column6">Per:</th>--%>
                        </tr>
                        </thead>
                    </table>
                </div>

                <div class="table100-body js-pscroll">
                        <table>
                            <tbody>
                        <%!
                            /*public void fill(Disponible_RoomBean r, boolean a){
                                if (a) {
                                    System.out.println("fermo");
                                }else{
                                }
                            }*/
		            %>
                            <%

                                if (request.getParameter("submit_search") != null){
                                    String c = null;
                                    Disponible_RoomBean r;
                                    String StartSearch = request.getParameter("start");
                                    String EndSearch = request.getParameter("end");
                                    String Date = request.getParameter("data");
                                    //@TODO Gestire il valore nullo dei dati inseriti
                                    String DateSearch = Date.format(String.valueOf(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

                                    LocalTime timeInizio = LocalTime.parse(StartSearch);
                                    LocalTime timeFine = LocalTime.parse(EndSearch);
                                    Controller controller = new Controller();
                                    r = controller.show_p(timeInizio, timeFine, DateSearch);
                                    for (int i = 0; i < r.getNome().size(); i++ ){
                                        c = r.getNome().get(i);
                                        String s = "<td><form><input type=submit name=\"submit_prenota\" value=\"Prenota\" position: absolute;\n" +
                                                "right: 40%;></form></td>";
                                        out.print("<tr class=\"row100 body\">");

                                        out.print("<td class=\"cell100 column1\">" + c + s + "</td>");
                                    }
                                }

                                if (request.getParameter("submit_prenota") != null){
                                    //@TODO Poupoup con interfaccia per prenotare aule
                                    System.out.println("CLiccato");
                                }
                            %>
                            </tbody>
                        </table>
                </div>
            </div>
        </div>
        <form action="" method="post" class="login100-form validate-form">

            <div class="wrap-input100 validate-input m-b-18" data-validate ="Data">
                <span class="label-input100">Data</span>
                <input class="input100" type="text" name="data" placeholder="Enter Room's data in format dd/MM/yyyy">
                <span class="focus-input100"></span>
            </div>


            <div class="wrap-input100 validate-input m-b-18" data-validate ="Start">
                <span class="label-input100">Start</span>
                <input class="input100" type="text" name="start" placeholder="Enter Room's start hour">
                <span class="focus-input100"></span>
            </div>

            <div class="wrap-input100 validate-input m-b-18" data-validate ="End">
                <span class="label-input100">End</span>
                <input class="input100" type="text" name="end" placeholder="Enter Room's end hour">
                <span class="focus-input100"></span>
            </div>

            <%--<div class="wrap-input100 validate-input m-b-18" data-validate ="Type">
                <span class="label-input100">Type</span>
                <input class="input100" type="text" name="type" placeholder="Enter Room's type">
                <span class="focus-input100"></span>
            </div>--%>

            <div class="container-login100-form-btn">
                <button class="login100-form-btn" type="submit" name="submit_search" value="Search">
                    Search
                </button>
                <%--<button class="login100-form-btn" type="submit" name="submit_booking" value="Booking">
                    Book it
                </button>--%>
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

</body>
</html>