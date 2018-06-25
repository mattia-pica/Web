<%--
  Created by IntelliJ IDEA.
  User: mattia
  Date: 23/06/18
  Time: 17.42
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="Control.Controller" %>
<%@ page import="Entity.Room" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.regex.Pattern" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="accYear" class="Bean.Accademic_Year" scope="session"/>

<jsp:setProperty name="accYear" property="*"/>

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
                            <th class="cell100 column2">Nuova sessione per l'anno <%=request.getParameter("anno")%></th>
                        </tr>
                        </thead>
                    </table>
                </div>
                <div class="table100-body js-pscroll">
                    <%--style="height:300px;overflow:auto;" Scroll nella table--%>
                    <table id="table" style="table-layout: fixed"> <%-- TODO style="table-layout: fixed" SERVE A CENTRAREEEEE--%>
                        <tbody>

                        <%

                            accYear.setNome(request.getParameter("anno"));
                            Controller controller = new Controller();

                            if (request.getParameter("submit_session") != null){

                                if (request.getParameter("inizio") == null || request.getParameter("inizio").equals("") ||
                                        request.getParameter("fine") == null || request.getParameter("fine").equals("") ||
                                        request.getParameter("typeSess") == null){%>

                        <script type="text/javascript">
                            var msg = "<%="Completare tutti i campi"%>";
                            alert(msg);
                            location='New_Session_Second.jsp?anno=<%=accYear.getNome()%>';
                        </script>

                        <%
                                    return;

                                }else {

                                    Pattern p = Pattern.compile("\\d{2}(/)\\d{2}(/)\\d{4}");
                                    if (p.matcher(request.getParameter("inizio")).matches() &&
                                            p.matcher(request.getParameter("fine")).matches()){

                                        //TODO nuova sessione

                                    }else {%>


                        <script type="text/javascript">
                            var msg = "<%="Inserire i dati nel formato corretto"%>";
                            alert(msg);
                            location='New_Session_Second.jsp?anno=<%=accYear.getNome()%>';
                            <%System.out.println(accYear.getNome());%>
                        </script>

                            <%
                                        return;
                                    }
                                }
                            }
                        %>

                        </tbody>
                    </table>
                </div>

            </div>
            <form action="" method="post" class="login100-form validate-form">

                <div class="wrap-input100 validate-input m-b-18" data-validate ="inizio">
                    <span class="label-input100">Data Inizio</span>
                    <input class="input100" type="text" name="inizio" placeholder="Data Inizio">
                    <span class="focus-input100"></span>
                </div>
                <div class="wrap-input100 validate-input m-b-18" data-validate ="inizio">
                    <span class="label-input100">Data Fine</span>
                    <input class="input100" type="text" name="fine" placeholder="Data Fine">
                    <span class="focus-input100"></span>
                </div>
                <div class="wrap-input100 validate-input m-b-18">
                    <fieldset>
                        <span class="label-input100">Invernale</span>
                        <input style="margin-top: 15px" type="radio" name="typeSess" value="Sess"/>
                    </fieldset>
                </div>
                <div class="wrap-input100 validate-input m-b-18">
                    <fieldset>
                        <span class="label-input100">Estiva</span>
                        <input style="margin-top: 15px" type="radio" name="typeSess" value="Sess"/>
                    </fieldset>
                </div>
                <div class="wrap-input100 validate-input m-b-18">
                    <fieldset>
                        <span class="label-input100">Autunnale</span>
                        <input style="margin-top: 15px" type="radio" name="typeSess" value="Sess"/>
                    </fieldset>
                </div>
                <div class="container-login100-form-btn">
                    <button class="login100-form-btn" type="submit" name="submit_session" value="InserSession">
                        Inserisci
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>

</body>
</html>