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

<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.util.Enumeration" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Si dichiara la variabile loginBean e istanzia un oggetto LoginBean -->
<jsp:useBean id="Prenotation_Room" scope="request"
             class="Bean.PrenotationRoom"/>

<!-- Mappa automaticamente tutti gli attributi dell'oggetto loginBean e le proprietà JSP -->
<jsp:setProperty name="Prenotation_Room" property="*"/>

<%
    /*if (request.getParameter("aula") == null) {
        //out.println("Please enter your aula.");
    } else {
        //out.println("Aula: <b>"+request.getParameter("aula")+"</b>!");
    }*/

    String name;

    //@TODO Finire interfaccia segretaria

    if (request.getParameter("submit_prenotation") != null){
        name = request.getParameter("aula");

        boolean b = true;
        boolean Response;

        String typePR;
        LocalTime startPR;
        LocalTime endPR;
        String date;

        if(request.getParameter("typePR") == null){

            //@TODO Gestire il valore nullo dei dati inseriti per la prenotazione
            typePR = request.getParameter("altroPRtext");
            String start = request.getParameter("startPR");
            String end = request.getParameter("endPR");
            date = request.getParameter("datePR");

            startPR = LocalTime.parse(start);
            endPR = LocalTime.parse(end);
            //String datePR = date.format(String.valueOf(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            Controller controller = new Controller();
            Response = controller.newP(name, typePR, date, startPR, endPR, b);
            System.out.println(typePR + " " + start + " " + end + " " + date);
        }else{
            typePR = request.getParameter("typePR");
            String start = request.getParameter("startPR");
            String end = request.getParameter("endPR");
            date = request.getParameter("datePR");

            startPR = LocalTime.parse(start);
            endPR = LocalTime.parse(end);
            //String datePR = date.format(String.valueOf(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            Controller controller = new Controller();
            Response = controller.newP(name, typePR, date, startPR, endPR, b);

            System.out.println(typePR + " " + start + " " + end + " " + date);

        }

        if (Response) {

            String info = "alert('Prenotata " + name + "');";
            out.println("<script type=\"text/javascript\">");
            out.println(info);
            out.println("location='profPage.jsp';");
            out.println("</script>");


        }else {
 %>
            <script type="text/javascript">
                showDiv();
            </script>
<% } %>


       <%     /*out.println("<script type=\"text/javascript\">");
            out.println("alert('Esiste già una prenotazione per i dati inseriti');");
            out.println("location='profPage.jsp';");
            out.println("</script>");*/

    }

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
                <!-- The popup -->
                <div id="myModal" class="modal">

                    <!-- Modal content -->
                    <div class="modal-content-login">
                        <span class="close">&times;</span>


                        <div class="login-right">
                            <h2>Prenotazione</h2>
                            <br>
                        <%-- <p><b>I'm already an ESHOP user</b><br>Enter your e-mail address and password to log into the website.</p>--%>
                            <form>
                                <div class="contact-right">
                                    <input class="login100-form-btn" type="submit"  name="submit_delete" value="Cancella">
                                </div>
                                <div class="contact-right">
                                    <input class="login100-form-btn" type="submit"  name="submit_notdelete" value="Non cancellare">
                                </div>

                            </form>
                        </div>
                    </div>
                </div>
                <script>
                    // Get the modal
                    var modal = document.getElementById('myModal');

                    // Get the <span> element that closes the modal
                    var span = document.getElementsByClassName("close")[0];

                    // When the user clicks on <span> (x), close the modal
                    span.onclick = function () {
                        modal.style.display = "none";

                    };

                    function showDiv() {

                        document.getElementById("foo").value = "some value";
                        modal.style.display = "block";
                        //console.log(parameter);

                    }
                    // When the user clicks anywhere outside of the modal, close it
                    window.onclick = function (event) {
                        if (event.target == modal) {
                            modal.style.display = "none";
                        }
                    }
                </script>
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

                <div class="table100-body js-pscroll">
                    <table id="table" >
                        <tbody>

                        <%
                            if (request.getParameter("submit_search") != null){

                                Disponible_RoomBean r;
                                String StartSearch = request.getParameter("start");
                                String EndSearch = request.getParameter("end");
                                String Date = request.getParameter("data");
                                String DateSearch = Date.format(String.valueOf(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                                LocalTime timeInizio = LocalTime.parse(StartSearch);
                                LocalTime timeFine = LocalTime.parse(EndSearch);
                                Controller controller = new Controller();
                                r = controller.show_p(timeInizio, timeFine, DateSearch);
                                for (int i = 0; i < r.getNome().size(); i++ ){%>
<%--
                        <tr><td><%=r.getNome().get(i)%></td><td><button name="" type="submit" onclick="window.location.href='/newPrenotationPage.jsp'">Prenota <%=r.getNome().get(i)%></button></td></tr>
--%>

                        <%--
                                <tr><td><%=r.getNome().get(i)%></td></tr><td><button name="" type="submit" onclick="window.location.href='/newPrenotationPage.jsp?aula=<%=r.getNome().get(i)%>'">Prenota <%=r.getNome().get(i)%></button></td>
--%>

                        <%--reply_click(this.id)--%>
                        <%--/*c = r.getNome().get(i);
                                    String s = "<td><form><a href=\"javascript:showDiv();\">Prenota</a>\n</form></td>";
                                    out.print("<tr class=\"row100 body\">");
                                    out.print("<td class=\"cell100 column1\">" + c + s + "</td>");*/--%>
                        <%
                                }
                            }

                            if(request.getParameter("submit_show") != null){



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
                <button class="login100-form-btn" type="submit"<%-- onclick="showBtn()"--%> name="submit_search" value="Search">
                    Search
                </button>
                <button class="login100-form-btn" type="submit" name="submit_show" value="Show">
                    Mostra Mie Prenotazioni
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
<script>function showBtn() {
    document.getElementById('btnPrenotation').style.display = "block";
}
</script>
<script>
    function reply_click(clicked_id)
    {

        alert(clicked_id);
    }
</script>
<%--<script>

    function f1(objButton){
        alert(objButton.value);
        return value;
    }


</script>--%>
<script>$
("#table tr").click(function(){
    $(this).addClass('selected').siblings().removeClass('selected');
    var value=$(this).find('td:first').html();
    console.log(value)
    //alert(value);
});

/*$('.ok').on('click', function(e){
    alert($("#table tr.selected td:first").html());
});*/
</script>
</body>
</html>