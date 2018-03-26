<%--
  Created by IntelliJ IDEA.
  User: mattia
  Date: 23/03/18
  Time: 17.42
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="Control.Controller" %>
<%@ page import="java.time.LocalTime" %>
<%@ page import="Bean.Disponible_RoomBean" %>

<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="Entity.Room" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Si dichiara la variabile loginBean e istanzia un oggetto LoginBean -->
<jsp:useBean id="Prenotation_Room" scope="request"
             class="Bean.PrenotationRoom"/>

<!-- Mappa automaticamente tutti gli attributi dell'oggetto loginBean e le proprietà JSP -->
<jsp:setProperty name="Prenotation_Room" property="*"/>

<%

    /*if (request.getParameter("aula_" + "") != null){

        if(request.getParameter("typePR") == null){

            //@TODO Gestire il valore nullo dei dati inseriti per la prenotazione
            *//*String typePR = request.getParameter("altroPRtext");
            String start = request.getParameter("startPR");
            String end = request.getParameter("endPR");
            String date = request.getParameter("datePR");

            LocalTime startPR = LocalTime.parse(start);
            LocalTime endPR = LocalTime.parse(end);
            String datePR = date.format(String.valueOf(DateTimeFormatter.ofPattern("dd/MM/yyyy")));*//*

           // System.out.println(typePR + " " + start + " " + end + " " + date);
        }else{
            *//*String typePR = request.getParameter("typePR");
            String start = request.getParameter("startPR");
            String end = request.getParameter("endPR");
            String date = request.getParameter("datePR");

            LocalTime startPR = LocalTime.parse(start);
            LocalTime endPR = LocalTime.parse(end);
            String datePR = date.format(String.valueOf(DateTimeFormatter.ofPattern("dd/MM/yyyy")));*//*

           // System.out.println(typePR + " " + start + " " + end + " " + date);

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
                            <th class="cell100 column1">Nome</th>
                            <th class="cell100 column1">Data</th>
                            <th class="cell100 column1">Inizio</th>
                            <th class="cell100 column1">Fine</th>
                            <th class="cell100 column1">Tipo</th>
                        </tr>
                        </thead>
                    </table>
                </div>

                <div class="table100-body js-pscroll" style="height:300px;overflow:auto;">
                    <%--style="height:300px;overflow:auto;" Scroll nella table--%>
                    <table id="table" >
                        <tbody>

                        <%
                            //@TODO Centrare i risultati nelle colonne
                            Controller controller = new Controller();
                            ArrayList<Room> r = controller.showCompleteDB_Prof();

                            for (int i = 0; i < r.size(); i++){%>

                            <tr><td><%=r.get(i).getNome()%></td><td><%=r.get(i).getDatapr()%></td><td><%=r.get(i).getInizio()%></td><td><%=r.get(i).getFine()%></td><td><%=r.get(i).getFine()%></td><td><%=r.get(i).getTipopr()%></td></tr>

                        <%
                        }
                        %>
                        </tbody>
                    </table>
                </div>
            </div>
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<%--<script>
    $('#textInput').click(function () {
        $('input[name=typePR]').prop("checked", false);

    });
</script>--%>


<%--QUANDO SI CLICCA SULLA TEXTFIELD ALTRO VENGONO DISATTIVATI I RADIO BUTTON--%>
<script>
    $('#textInput').click(function () {
        $('input[type=radio]').removeAttr("checked");

    });
</script>
<%--<script>

    function f1(objButton){
        alert(objButton.value);
        return value;
    }


</script>--%>
</body>
</html>