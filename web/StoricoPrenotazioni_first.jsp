<%--
  Created by IntelliJ IDEA.
  User: mattia
  Date: 23/06/18
  Time: 16.52
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="Control.Controller" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Bean.AccademicYearBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Si dichiara la variabile loginBean e istanzia un oggetto LoginBean -->
<jsp:useBean id="accademiYear" class="Bean.AccademicYearBean" scope="session"/>


<!-- Mappa automaticamente tutti gli attributi dell'oggetto loginBean e le proprietà JSP -->
<jsp:setProperty name="accademiYear" property="*"/>

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
                            <th class="cell100 column2">Data Inizio</th>
                            <th class="cell100 column2">Data Fine</th>
                            <th class="cell100 column2"></th>
                        </tr>
                        </thead>
                    </table>
                </div>

                <div class="table100-body js-pscroll" style="overflow:auto;">
                    <table id="table" style="table-layout: fixed">
                        <tbody>

                        <%

                            Controller controller = new Controller();
                            ArrayList<AccademicYearBean> y = controller.showYears();

                            for (AccademicYearBean aY : y) {%>

                        <tr>
                            <td><%=aY.getNome()%></td>
                            <td><%=aY.getDataInizio()%></td>
                            <td><%=aY.getDataFine()%></td>
                            <td>
                                <button class="login100-form-btn" name="" type="submit"
                                        onclick="window.location.href='/StoricoPrenotazioni_second.jsp?anno=<%=aY.getDataInizio()+"/"+aY.getDataFine()%>'">
                                    Prenotazioni
                                </button>
                            </td>
                        </tr>

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

<%--QUANDO SI CLICCA SULLA TEXTFIELD 'ALTRO' VENGONO DISATTIVATI I RADIO BUTTON--%>
<script>
    $('#textInput').click(function () {
        $('input[type=radio]').removeAttr("checked");

    });
</script>

</body>
</html>
