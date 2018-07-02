<%--
  Created by IntelliJ IDEA.
  User: mattia
  Date: 23/03/18
  Time: 10.09
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="Control.Controller" %>
<%@ page import="java.time.LocalTime" %>
<%@ page import="Bean.Disponible_RoomBean" %>
<%@ page import="java.time.format.DateTimeParseException" %>
<%@ page import="java.util.regex.Pattern" %>
<%@ page import="Bean.SessionBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Si dichiara la variabile loginBean e istanzia un oggetto LoginBean -->
<jsp:useBean id="sessionBean" class="Bean.SessionBean" scope="session"/>

<!-- Mappa automaticamente tutti gli attributi dell'oggetto loginBean e le proprietà JSP -->
<jsp:setProperty name="sessionBean" property="*"/>

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
                        </tr>
                        </thead>
                    </table>
                </div>

                <div class="table100-body js-pscroll" style="overflow:auto;">
                    <table id="table" >
                        <tbody>

                        <%
                            Controller controller = new Controller();

                            if (request.getParameter("submit_search") != null){

                                Disponible_RoomBean r;
                                String StartSearch = request.getParameter("start");
                                String EndSearch = request.getParameter("end");
                                String DateSearch = request.getParameter("data");

                                if (StartSearch.isEmpty() || EndSearch.isEmpty() || DateSearch.isEmpty()) {

                                    String info = "alert('Completare tutti i campi!');";
                                    out.println("<script type=\"text/javascript\">");
                                    out.println(info);
                                    out.println("location='secretaryPage.jsp';");
                                    out.println("</script>");
                                }else {

                                    Pattern p = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
                                    //Pattern.compile("\\d{4}(/)\\d{2}(/)\\d{2}")
                                    if (p.matcher(request.getParameter("data")).matches()){
                                        try {

                                        //String DateSearch = Date.format(String.valueOf(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                                        LocalTime timeInizio = LocalTime.parse(StartSearch);
                                        LocalTime timeFine = LocalTime.parse(EndSearch);
                                        r = controller.show_Secretary(timeInizio, timeFine, DateSearch);

                                        if (r.getNome().isEmpty()){
                                            String info = "alert('Non ci sono aule prenotabili');";
                                            out.println("<script type=\"text/javascript\">");
                                            out.println(info);
                                            out.println("location='secretaryPage.jsp';");
                                            out.println("</script>");
                                            return;
                                        }

                                        SessionBean s = controller.trovaSessione(DateSearch);

                                        if (s.getDataInizio() == null){
                                            String info = "alert('La data inserita è fuori da ogni sessione');";
                                            out.println("<script type=\"text/javascript\">");
                                            out.println(info);
                                            out.println("location='secretaryPage.jsp';");
                                            out.println("</script>");
                                            return;
                                        }
                                        sessionBean.setDataInizio(s.getDataInizio());
                                        sessionBean.setDataFine(s.getDataFine());
                                        sessionBean.setTipo(s.getTipo());

                                        String sessione = sessionBean.getDataInizio()+"/"+sessionBean.getDataFine();

                                        controller.createPrenotationBean(timeInizio, timeFine, DateSearch,sessione);
                                        for (int i = 0; i < r.getNome().size(); i++){%>
                                        <tr><td><%=r.getNome().get(i)%></td><td><button class="login100-form-btn" name="" type="submit" onclick="window.location.href='/Secr_Prenotation.jsp?aula=<%=r.getNome().get(i)%>'">Prenota <%=r.getNome().get(i)%></button>

                            <%
                                            }

                                            }catch (DateTimeParseException e){

                                            String info = "alert('Formato dati inseriti errato');";
                                            out.println("<script type=\"text/javascript\">");
                                            out.println(info);
                                            out.println("location='secretaryPage.jsp';");
                                            out.println("</script>");
                                            return;

                                            }
                                    }else {

                                        String info = "alert('Formato data inseriti errato');";
                                            out.println("<script type=\"text/javascript\">");
                                            out.println(info);
                                            out.println("location='secretaryPage.jsp';");
                                            out.println("</script>");
                                            return;
                                    }
                                }
                            }

                            if(request.getParameter("submit_show_prenotation") != null){  //Mostra tutte le prenotazioni
                                response.sendRedirect("Show_ALL.jsp");
                            }

                            if(request.getParameter("submit_modify") != null){            //Modifica Prenotazione
                                response.sendRedirect("Modify_Secr.jsp");
                            }

                            if (request.getParameter("submit_DELETE") != null){           //Elimina Prenotazione
                                response.sendRedirect("Delete_Secretary.jsp");

                            }

                            if (request.getParameter("submit_forced") != null){           //Prenotazione Forzata
                                response.sendRedirect("All_Rooms.jsp");

                            }

                            if (request.getParameter("submit_annoAcc") != null){          //Nuovo anno accademico
                                response.sendRedirect("New_Acc.jsp");

                            }

                            if (request.getParameter("submit_session") != null){          //Nuova sessione
                                response.sendRedirect("New_Session_First.jsp");

                            }

                            if(request.getParameter("submit_ModSession") != null){        //Modifica Sessione
                                response.sendRedirect("Show_Session_Secr.jsp");
                            }

                            if(request.getParameter("submit_show_aule_secr") != null){    //Mostra prenotazioni segretaria
                                response.sendRedirect("ShowAule_Secretary.jsp");

                            }

                            if (request.getParameter("submit_ModAccYear") != null){       //Modifica anno accademico
                                response.sendRedirect("Modify_AccYear_first.jsp");
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
            <div class="container-login100-form-btn">
                <button class="login100-form-btn" type="submit" name="submit_search" value="Search">
                    Cerca
                </button>
                <button class="login100-form-btn" type="submit" name="submit_forced" value="Forced">
                    Inserisci
                </button>
                <button class="login100-form-btn" type="submit" name="submit_show_prenotation" value="Show">
                    Mostra Prenotazioni
                </button>
                <button class="login100-form-btn" type="submit" name="submit_show_aule_secr" value="Show">
                Mie Prenotazioni
            </button>
                <button class="login100-form-btn" type="submit" name="submit_modify" value="Modify">
                    Modifica Prenotazione
                </button>
                <button class="login100-form-btn" type="submit" name="submit_DELETE" value="Delete">
                    Elimina Prenotazione
                </button>
                <button class="login100-form-btn" type="submit" name="submit_annoAcc" value="AnnoAcc">
                    Nuovo Anno Accademico
                </button>
                <button class="login100-form-btn" type="submit" name="submit_session" value="Session">
                    Nuova Sessione
                </button>
                <button class="login100-form-btn" type="submit" name="submit_ModSession" value="ModSession">
                    Modifica Sessione
                </button>
                <button class="login100-form-btn" type="submit" name="submit_ModAccYear" value="ModAccYear">
                    Modifica Anno Accademico
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