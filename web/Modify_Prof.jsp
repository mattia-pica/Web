<%@ page import="Control.Controller" %>
<%@ page import="Entity.Room" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.time.LocalTime" %><%--
  Created by IntelliJ IDEA.
  User: mattia
  Date: 04/04/18
  Time: 12.46
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
                            <th class="cell100 column2">Nome</th>
                            <th class="cell100 column2">Data</th>
                            <th class="cell100 column2">Start</th>
                            <th class="cell100 column2">End</th>
                            <th class="cell100 column2">Type</th>
                        </tr>
                        </thead>
                    </table>
                </div>

                <div class="table100-body js-pscroll" style="height:300px;overflow:auto;">
                    <table id="table" >
                        <tbody>

                        <%
                            Controller controller = new Controller();
                            ArrayList<Room> r = controller.showComplete_DB();
                            for (int i = 0; i < r.size(); i++){%>
                        <tr><td><%=r.get(i).getNome()%></td><td><%=r.get(i).getDatapr()%></td><td><%=r.get(i).getInizio()%></td><td><%=r.get(i).getFine()%></td><td><%=r.get(i).getTipopr()%></td><td><%=r.get(i).getID()%></td></tr>
                        <%
                            }

                            if (request.getParameter("submit_modify") != null) {

                                boolean Response = false;

                                String ID;
                                String typePR;
                                LocalTime startPR;
                                LocalTime endPR;
                                String date;

                                if (request.getParameter("typePR") == null) {

                                    ID = request.getParameter("ID");
                                    typePR = request.getParameter("altroPRtext");
                                    String start = request.getParameter("startPR");
                                    String end = request.getParameter("endPR");
                                    date = request.getParameter("datePR");

                                    if (ID.isEmpty() || start.isEmpty() || end.isEmpty() || typePR.isEmpty() || date.isEmpty()) {

                                        String info = "alert('Completare tutti i campi!');";
                                        out.println("<script type=\"text/javascript\">");
                                        out.println(info);
                                        out.println("location='Modify_Prof.jsp';");
                                        out.println("</script>");

                                    } else {

                                        startPR = LocalTime.parse(start);
                                        endPR = LocalTime.parse(end);
                                        Response = controller.modify(ID, startPR, endPR, date, typePR);
                                    }
                                } else {

                                    ID = request.getParameter("ID");
                                    typePR = request.getParameter("typePR");
                                    String start = request.getParameter("startPR");
                                    String end = request.getParameter("endPR");
                                    date = request.getParameter("datePR");

                                    if (ID.isEmpty() || start.isEmpty() || end.isEmpty() || typePR.isEmpty() || date.isEmpty()) {

                                        String info = "alert('Completare tutti i campi!');";
                                        out.println("<script type=\"text/javascript\">");
                                        out.println(info);
                                        out.println("location='Modify_Prof.jsp';");
                                        out.println("</script>");
                                    } else {

                                        startPR = LocalTime.parse(start);
                                        endPR = LocalTime.parse(end);
                                        Response = controller.modify(ID, startPR, endPR, date, typePR);
                                    }
                                }

                                if (Response) {

                                    out.println("<script type=\"text/javascript\">");
                                    out.println("alert('Prenotazione Modificata');");
                                    out.println("location='Modify_Prof.jsp';");
                                    out.println("</script>");

                                } else {

                                    String info = "alert('Ci sono prenotazione già attive con i criteri inseriti!');";
                                    out.println("<script type=\"text/javascript\">");
                                    out.println(info);
                                    out.println("location='Modify_Prof.jsp';");
                                    out.println("</script>");
                                }
                            }

                        %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <form action="" method="post" class="login100-form validate-form">

            <div class="wrap-input100 validate-input m-b-18" data-validate ="id">
                <span class="label-input100">ID</span>
                <input class="input100" type="text" name="ID" placeholder="Prenotation ID">
                <span class="focus-input100"></span>
            </div>

            <div class="wrap-input100 validate-input m-b-18" data-validate ="Start">
                <span class="label-input100">Start</span>
                <input class="input100" type="text" name="startPR" placeholder="Start">
                <span class="focus-input100"></span>
            </div>


            <div class="wrap-input100 validate-input m-b-18" data-validate ="End">
                <span class="label-input100">End</span>
                <input class="input100" type="text" name="endPR" placeholder="End">
                <span class="focus-input100"></span>
            </div>

            <div class="wrap-input100 validate-input m-b-18" data-validate ="Date">
                <span class="label-input100">Date</span>
                <input class="input100" type="text" name="datePR" placeholder="Date">
                <span class="focus-input100"></span>
            </div>
            <div class="wrap-input100 validate-input m-b-18">
                <fieldset>
                    <span class="label-input100">Esame</span>
                    <input style="margin-top: 15px" type="radio" name="typePR" value="Esame"/>
                </fieldset>
            </div>
            <div class="wrap-input100 validate-input m-b-18">
                <fieldset>
                    <span class="label-input100">Conferenza</span>
                    <input  style="margin-top: 15px" type="radio" name="typePR"  value="Conferenza"/>

                </fieldset>
            </div>
            <div class="wrap-input100 validate-input m-b-18" data-validate ="Altro">
                <span class="label-input100">Altro</span>
                <input class="input100" type="text" id="textInput" name="altroPRtext" placeholder="Altro">
                <span class="focus-input100"></span>
            </div>
            <div class="container-login100-form-btn">
                <button class="login100-form-btn" type="submit" name="submit_modify" value="Modify">
                    Modifica Prenotazione
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

</body>
</html>