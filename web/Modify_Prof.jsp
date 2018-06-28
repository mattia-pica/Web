<%@ page import="Control.Controller" %>
<%@ page import="Entity.Room" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.time.LocalTime" %>
<%@ page import="java.time.format.DateTimeParseException" %>
<%@ page import="java.util.regex.Pattern" %>
<%@ page import="Bean.SessionBean" %><%--
  Created by IntelliJ IDEA.
  User: mattia
  Date: 04/04/18
  Time: 12.46
  To change this template use File | Settings | File Templates.
--%>

<!-- Si dichiara la variabile loginBean e istanzia un oggetto LoginBean -->
<jsp:useBean id="sessionBean" class="Bean.SessionBean" scope="session"/>
<jsp:useBean id="roomBean" class="Bean.RoomBean" scope="session"/>

<!-- Mappa automaticamente tutti gli attributi dell'oggetto loginBean e le proprietà JSP -->
<jsp:setProperty name="sessionBean" property="*"/>
<jsp:setProperty name="roomBean" property="*"/>

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
                            <th class="cell100 column2">ID</th>
                        </tr>
                        </thead>
                    </table>
                </div>

                <div class="table100-body js-pscroll" style="height:300px;overflow:auto;">
                    <table id="table" style="table-layout: fixed">
                        <tbody>

                        <%
                            Controller controller = new Controller();
                            ArrayList<Room> r = controller.showComplete_DB();
                            if (r.isEmpty()){
                                String info = "alert('Non ci sono aule prenotabili');";
                                out.println("<script type=\"text/javascript\">");
                                out.println(info);
                                out.println("location='profPage.jsp';");
                                out.println("</script>");
                                return;
                            }
                            for (Room aR : r) {%>
                        <tr>
                            <td><%=aR.getNome()%>
                            </td>
                            <td><%=aR.getDatapr()%>
                            </td>
                            <td><%=aR.getInizio()%>
                            </td>
                            <td><%=aR.getFine()%>
                            </td>
                            <td><%=aR.getTipopr()%>
                            </td>
                            <td><%=aR.getID()%>
                            </td>
                        </tr>
                        <%
                            }

                            if (request.getParameter("submit_mod") != null) {

                                if (request.getParameter("startPR") == null || request.getParameter("ID") == null ||
                                        request.getParameter("endPR") == null || request.getParameter("datePR") == null ||
                                        (request.getParameter("typePR") == null && request.getParameter("altroPRtext") == null)) {
                                    String info = "alert('Completare tutti i campi!');";
                                    out.println("<script type=\"text/javascript\">");
                                    out.println(info);
                                    out.println("location='Modify_Prof.jsp';");
                                    out.println("</script>");

                                } else {

                                    Pattern p = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
                                    if (p.matcher(request.getParameter("datePR")).matches()) {

                                        SessionBean s = controller.trovaSessione(request.getParameter("datePR"));

                                        if (s.getDataInizio() == null){
                                            String info = "alert('La data inserita è fuori da ogni sessione');";
                                            out.println("<script type=\"text/javascript\">");
                                            out.println(info);
                                            out.println("location='Modify_Prof.jsp';");
                                            out.println("</script>");
                                            return;
                                        }
                                        try {
                                            roomBean.setID(request.getParameter("ID"));
                                            roomBean.setInizio(LocalTime.parse(request.getParameter("startPR")));
                                            roomBean.setFine(LocalTime.parse(request.getParameter("endPR")));
                                            roomBean.setDatapr(request.getParameter("datePR"));
                                        } catch (DateTimeParseException e1) {

                                            String info = "alert('I campi inseriti non sono corretti');";
                                            out.println("<script type=\"text/javascript\">");
                                            out.println(info);
                                            out.println("location='Modify_Prof.jsp';");
                                            out.println("</script>");
                                            return;
                                        }
                                    }

                                    if (request.getParameter("typePR") == null) {
                                        roomBean.setTipopr(request.getParameter("altroPRtext"));
                                    } else {
                                        roomBean.setTipopr(request.getParameter("typePR"));
                                    }

                                    if (controller.modify(roomBean.getID(), roomBean.getInizio(), roomBean.getFine(),
                                            roomBean.getDatapr(), roomBean.getTipopr())) {

                                        out.println("<script type=\"text/javascript\">");
                                        out.println("alert('Prenotazione Modificata');");
                                        out.println("location='profPage.jsp';");
                                        out.println("</script>");

                                    } else {

                                        out.println("<script type=\"text/javascript\">");
                                        out.println("alert('La modifica crea conflitti con le prenotazioni già esistenti');");
                                        out.println("location='profPage.jsp';");
                                        out.println("</script>");
                                    }
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
                    <input  style="margin-top: 15px" type="radio" name="typePR" value="Conferenza"/>
                </fieldset>
            </div>
            <div class="wrap-input100 validate-input m-b-18" data-validate ="Altro">
                <span class="label-input100">Altro</span>
                <input class="input100" type="text" id="textInput" name="altroPRtext" placeholder="Altro">
                <span class="focus-input100"></span>
            </div>
            <div class="container-login100-form-btn">
                <button class="login100-form-btn" type="submit" name="submit_mod" value="Modify">
                    Modifica
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