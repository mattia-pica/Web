<%--
  Created by IntelliJ IDEA.
  User: mattia
  Date: 25/06/18
  Time: 18.15
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="Control.Controller" %>
<%@ page import="Entity.Room" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.time.LocalTime" %>
<%@ page import="java.time.format.DateTimeParseException" %>
<%@ page import="java.util.regex.Pattern" %>

<!-- Si dichiara la variabile loginBean e istanzia un oggetto LoginBean -->
<jsp:useBean id="sessionBean" class="Bean.SessionBean" scope="session"/>

<!-- Mappa automaticamente tutti gli attributi dell'oggetto loginBean e le proprietà JSP -->
<jsp:setProperty name="sessionBean" property="*"/>

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
                            <th class="cell100 column2"></th>
                        </tr>
                        </thead>
                    </table>
                </div>

                <div class="table100-body js-pscroll" style="height:300px;overflow:auto;">
                    <table id="table" style="table-layout: fixed">
                        <tbody>

                        <%

                            Controller controller = new Controller();
                            String s = request.getParameter("sessione");
                            String n[] = s.split(",");
                            String inizio = n[0];
                            String fine = n[1];

                            sessionBean.setDataInizio(inizio);
                            sessionBean.setDataFine(fine);

                            if (request.getParameter("submit_mod") != null){

                                if (request.getParameter("newInizio") == null || request.getParameter("newFine") == null ||
                                        request.getParameter("newInizio").equals("") || request.getParameter("newFine").equals("") ||
                                        request.getParameter("typeSess") == null){
                        %>

                        <script type="text/javascript">
                            var msg = "<%="Completare tutti i campi"%>";
                            alert(msg);
                            location='Modify_Session.jsp?anno=<%=sessionBean.getDataInizio()+","+sessionBean.getDataFine()%>';
                        </script>

                        <%
                                    return;
                                }

                                Pattern p = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
                                if (p.matcher(request.getParameter("newInizio")).matches() &&
                                        p.matcher(request.getParameter("newFine")).matches()){

                                    if (controller.modifySession(request.getParameter("newInizio"),
                                                    request.getParameter("newFine"),
                                            (sessionBean.getDataInizio()+"/"+sessionBean.getDataFine()),
                                            request.getParameter("typeSess"))){

                                        String info = "alert('Sessione modificata');";
                                        out.println("<script type=\"text/javascript\">");
                                        out.println(info);
                                        out.println("location='secretaryPage.jsp';");
                                        out.println("</script>");
                                        return;
                                    }else{

                                        String info = "alert('Le date inserite si sovrappongono con sessioni esistenti');";
                                        out.println("<script type=\"text/javascript\">");
                                        out.println(info);
                                        out.println("location='Show_Session_Secr.jsp';");
                                        out.println("</script>");
                                        return;

                                    }
                                }else {

                        %>

                        <script type="text/javascript">
                            var msg = "<%="Inserire i dati nel formato corretto"%>";
                            alert(msg);
                            location='Modify_Session.jsp?anno=<%=sessionBean.getDataInizio()+","+sessionBean.getDataFine()%>';
                        </script>

                        <%
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

            <div class="wrap-input100 validate-input m-b-18" data-validate ="inizio">
                <span class="label-input100">Data Inizio</span>
                <input class="input100" type="text" name="newInizio" placeholder="Formato data yyyy-MM-dd">
                <span class="focus-input100"></span>
            </div>
            <div class="wrap-input100 validate-input m-b-18" data-validate ="inizio">
                <span class="label-input100">Data Fine</span>
                <input class="input100" type="text" name="newFine" placeholder="Formato data yyyy-MM-dd">
                <span class="focus-input100"></span>
            </div>
            <div class="wrap-input100 validate-input m-b-18">
                <fieldset>
                    <span class="label-input100">Invernale</span>
                    <input style="margin-top: 15px" type="radio" name="typeSess" value="Invernale"/>
                </fieldset>
            </div>
            <div class="wrap-input100 validate-input m-b-18">
                <fieldset>
                    <span class="label-input100">Estiva</span>
                    <input style="margin-top: 15px" type="radio" name="typeSess" value="Estiva"/>
                </fieldset>
            </div>
            <div class="wrap-input100 validate-input m-b-18">
                <fieldset>
                    <span class="label-input100">Autunnale</span>
                    <input style="margin-top: 15px" type="radio" name="typeSess" value="Autunnale"/>
                </fieldset>
            </div>
            <div class="container-login100-form-btn">
                <button class="login100-form-btn" type="submit" name="submit_mod" value="ModSession">
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