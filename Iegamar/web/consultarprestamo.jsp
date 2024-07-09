<%-- 
    Document   : registarcuentas
    Created on : 11-ago-2014, 11:14:32
    Author     : MAÑANA
--%>

<%@page import="Controlador.ConReserva"%>
<%@page import="Controlador.ControladorElemento"%>
<%@page import="Controlador.ConsultarPrestamo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page session="true" %>
<%
    HttpSession sesionOk = request.getSession();
    String usuario = "";
    String tipo = "";

    String OGrado = "<li><a href=\"registargradoygrupo.jsp\" style=\"margin-top: -46px;\">Grados</a></li>";

    String OEstudiante = "<li><a href=\"consultarestudiante.jsp\" style=\"margin-top: -46px;\">Estudiantes</a></li>";

    String OProfesores = "<li><a href=\"consultarprofesores.jsp\" style=\"margin-top: -46px;\">Profesores</a></li>";

    String OPrestamo = " <li class=\"active\" ><a href=\"consultarprestamo.jsp\" style=\"margin-left: 65px; margin-top: -46px;\">Préstamo</a></li>";

    String OReserva = "<li><a href=\"consultarreserva.jsp\" style=\"margin-top: -46px;\">Reserva</a></li>  ";

    String OControl = "<li><a href=\"consultarcontrol.jsp\" style=\"margin-top: -46px;\">Control de llegadas</a></li> ";

    String OElementos = "<li><a href=\"consultarelemento.jsp\" style=\"margin-top: -46px;\">Elementos</a></li>";

    String OCuenta = "<li ><a href=\"consultarcuentas.jsp\" style=\"margin-left: 122px; margin-top: -42px;\">Administar Cuentas</a></li>";

    if (sesionOk.getAttribute("usuario") != null && sesionOk.getAttribute("Rol") != null) {
        usuario = sesionOk.getAttribute("usuario").toString();
        tipo = sesionOk.getAttribute("Rol").toString();

        if (tipo.equals("Super")) {

            response.sendRedirect("index.jsp");
        }

        if (tipo.equals("Control")) {
            OCuenta = "";
            OElementos = "";
            OPrestamo = "";
            OReserva = "";
        }

        if (tipo.equals("Profesores")) {
            OGrado = "";
            OEstudiante = "";
            OProfesores = "";
            OPrestamo = "";
            OReserva = "";
            OControl = "";
        }

        if (tipo.equals("Estudiante")) {

            OGrado = "";
            OEstudiante = "";
            OProfesores = "";
            OCuenta = "";
            OControl = "";
            OElementos = "";
        }

    } else {
        response.sendRedirect("index.jsp");
    }
%>

<%
    java.util.Date Fecha = new java.util.Date();
    SimpleDateFormat Formato = new SimpleDateFormat("dd-MM-yyyy");
%>

<!DOCTYPE html>
<!DOCTYPE>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="keywords" content="jquery,ui,easy,easyui,web">
        <meta name="description" content="easyui help you build your web page easily!">
        <meta http-equiv="Cache-control" content="no-cache">
        <meta http-equiv="Cache-control" content="no-store">
        <title>IEGAMAR</title>
        <link rel="stylesheet" type="text/css" href="css/easyui.css">
        <link rel="stylesheet" type="text/css" href="css/icon.css">
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="css/dashboard.css" rel="stylesheet">
        <link href="bootstrap/css/dataTables.bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="css/style_light.css" rel="stylesheet" type="text/css"/>

    </head>
    <body onload="mueveReloj()">

        <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" >IEGAMAR</a>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav"> 

                       

                        <!------------------------------------------------------------------------>
                         <div id="actualizar2" style="margin-top: 8px;">
                            <%
                                ConReserva Res = new ConReserva();
                                out.println(Res.resercount());
                            %>
                        </div>
                        <!-------------------------------------------------------------------------->


                        <%=OPrestamo%>
                        <%=OReserva%>                            
                    </ul>
                    <ul class="nav navbar-nav navbar-right">


                        <li class="active"><a href="CerrarSesion.jsp">Cerrar Sesión</a></li>

                    </ul>
                </div><!--/.nav-collapse -->
            </div>
        </nav>

        

        <!------------------------------------------------------------------------------------------------------------------------------->
        <div hidden="true" class="notification-list-wrapper" id="objetivo2" style="z-index: 10000; top: 65px; left: 100px;  opacity: 1;">

            <ul class="notification-list-menu">
            </ul>

            <ul class="notification-list" data-type="unread">

                <table class="table2 table-hover" cellspacing="0" width="100%">
                    <thead>
                        <tr>

                            <th class="text-center">Hora</th>
                            <th class="text-center">Profesor</th>
                            <th class="text-center">Notificacion</th>
                            <th class="text-center">Ver</th
                        </tr>
                    </thead>
                    <tbody id="cualquiera">
                        <%
                            out.println(Res.ListarReservasHoy());
                        %>
                    </tbody>
                </table>



            </ul>
        </div>
        <!--------------------------------------------------------------------------------------------------------------------------------------------->


        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-3 col-md-2 sidebar">
                    <ul class="nav nav-sidebar">
                        <li><a href="registarprestamo.jsp">Registar Préstamo</a></li>
                        <li class="active"><a href="consultarprestamo.jsp">Consutar Préstamo</a></li>
                    </ul>
                </div>
                <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                    <h1 class="page-header">Consultar Préstamo</h1>
                </div>
            </div>
        </div>
        <div class="tab-content">
            <div class="tab-pane active" id="area">
                <table id="tblArea" class="table table-hover" cellspacing="0">
                    <thead>
                        <tr>
                            <th class="text-center" Style="display:none"></th>
                            <th class="text-center">Monitor Prestamo</th> 
                            <th class="text-center">Fecha Prestamo</th>
                            <th class="text-center">Hora Prestamo</th>
                            <th class="text-center">Nombre Prestador</th> 
                            <th class="text-center">Fecha Devolución</th>
                            <th class="text-center">Hora Devolución</th>
                            <th class="text-center">Monitor Devolución</th> 
                            <th class="text-center">Seriales</th>                           
                            <th class="text-center">Editar</th>
                        </tr>
                    </thead>
                    <tbody>

                        <%
                            ConsultarPrestamo ls = new ConsultarPrestamo();
                            out.println(ls.listarPrestamo());
                        %>

                    </tbody>
                </table>
            </div>
        </div>


        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        <h4 class="modal-title" id="myModalLabel">Registar Devolucion</h4>
                    </div>
                    <form name="form_reloj" action="ConsultarPrestamo" method="POST" >

                        <div class="modal-body">
                            <div class="form-group">
                                <label for="disabledSelect">Nombre Prestador</label>
                                <input type="hidden"  class="form-control" name="Id_prestamo" id="Id_prestamo">
                                <input type="text"  class="form-control" name="NombrePrestador" id="NombrePrestador" readonly="readonly" placeholder="">
                            </div>

                            <div class="form-group ">
                                <label for="disabledSelect">Fecha De Devolución</label>
                                <input type="text" id="Fecha" class="form-control" placeholder="" name="Fecha" readonly="readonly" value="<%=Formato.format(Fecha)%>">
                            </div>

                            <div class="form-group ">
                                <label for="disabledSelect">Hora De Devolución</label>
                                <input type="text" id="Hora" class="form-control" placeholder="" name="Hora" readonly="readonly">
                            </div>    
                        </div>
                        <div class="modal-footer">
                            <button type="submit" name="Guardar" id="Guardar" value="modificar" class="btn btn-success">Guardar</button>
                            <button type="reset" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header" id="agg">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        <h4 class="modal-title" id="myModalLabel">Seriales</h4>
                    </div>
                    <div class="modal-body">
                        <table id="tblArea" class="table2 table-hover" cellspacing="0" width="100%">
                            <thead>
                                <tr>
                                    <th class="text-center">Seriales</th>
                                    <th class="text-center">Anomalía</th>
                                </tr>
                            </thead>
                            <tbody id="traer">

                            </tbody>
                        </table>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                    </div>

                </div>
            </div>
        </div> 

        <div class="modal fade bs-example-modal-sm1"  tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm">
                <div class="modal-content">
                    <div class="modal-header" >
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        <h4 class="modal-title" id="myModalLabel">Registrar Anomalia</h4>
                    </div>

                    <div class="modal-body"  >
                        <div class="form-group">
                            <label for="disabledSelect">Serial</label>
                            <input type="text"  class="form-control" name="Serial" id="Serial1" readonly="readonly" placeholder="">
                        </div>
                        <div class="form-group">
                            <label for="disabledSelect">Descrición</label>

                            <textarea rows="4" maxlength="120" name="Anomalia" id="Anomalia1" cols="50" onkeypress="return validarn(event)" class="form-control">

                            </textarea>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" data-dismiss="modal" onclick="anomalia()" class="btn btn-success">Guardar</button>
                        <button type="reset" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                    </div>
                </div>

            </div>
        </div>



        <script type="text/javascript" src="js/jquery-1.6.min.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="bootstrap/js/bootstrap.js"></script>
        <!-- En esta parte incluyo la libreria general del dataTables -->
        <script src="bootstrap/js/jquery.dataTables.min.js"></script>
        <!--       <script src="bootstrap/js/bootstrap.min.js" type="text/javascript"></script> -->
        <script src="bootstrap/js/dataTables.bootstrap.js" type="text/javascript"></script>
        <script src="js/mapeomod.js" type="text/javascript"></script>
        <script src="bootstrap/js/alertify.js" type="text/javascript"></script>


        <script language="JavaScript">

//---------------------------------------------------------------------------------------------------------------------------------
                            $(document).ready(function () {
                                conReserva();
                            });

                            function conReserva() {



                                $.ajax({
                                    dataType: "html",
                                    data: {
                                        proceso: "Comprobar_reservas"


                                    },
                                    type: "POST",
                                    url: "ConReserva",
                                    statusCode: {
                                        404: function () {
                                            alert("page not found");
                                        }
                                    }
                                }).done(function (datos) {
                                    $("#cualquiera").empty();
                                    $("#cualquiera").append(datos);

                                });
                            }
                            //-----------------------------------------------------------------------------------------------------------------------------


//------------------------------------------------
                            var x, y;
                            y = $(document);
                            x = $(document);
                            x.ready(inicializar);
                            y.ready(inicializar2);
                            function inicializar() {
                                var x = $("#mostrar");
                                x.click(muestrame);

                            }
                            function inicializar2() {
                                var x = $("#mostrar1");
                                x.click(muestrame2);

                            }

                            function muestrame() {
                                var x = $("#objetivo");
                                x.slideToggle("slow");
                            }
                            function muestrame2() {
                                var x = $("#objetivo2");
                                x.slideToggle("slow");
                            }




                            function actualizar_anomalias_resev() {



                                $.ajax({
                                    dataType: "html",
                                    data: {
                                        proceso: "Reservas_hoy"


                                    },
                                    type: "POST",
                                    url: "ConReserva",
                                    statusCode: {
                                        404: function () {
                                            alert("page not found");
                                        }
                                    }
                                }).done(function (datos) {
                                    $("#actualizar").empty();
                                    $("#actualizar").append(datos);

                                });
                            }
//-------------------------------------------------------

                            function addZero(i) {
                                if (i < 10) {
                                    i = "0" + i;
                                }
                                return i;
                            }

                            function mueveReloj() {
                                momentoActual = new Date();
                                hora = momentoActual.getHours();
                                minuto = addZero(momentoActual.getMinutes())

                                horaImprimible = hora + ":" + minuto;
                                document.form_reloj.Hora.value = horaImprimible;

                                setTimeout("mueveReloj()", 1000);
                            }
        </script> 


        <script type="text/javascript" charset="utf-8">


            function validarn(e) {
                tecla = (document.all) ? e.keyCode : e.which;
                if (tecla == 8)
                    return true;
                if (tecla == 9)
                    return true;
                if (tecla == 11)
                    return true;
                if (tecla == 13)
                    return false;
                patron = /[0-9A-Za-zñÑáéíóúÁÉÍÓÚàèìòùÀÈÌÒÙâêîôûÂÊÎÔÛÑñäëïöüÄËÏÖÜ\s\t]/;
                te = String.fromCharCode(tecla);
                return patron.test(te);
            }

            var actualizacion = setInterval(function () {
                actualizar_anomalias()
            }, 3000);

            function actualizar_anomalias() {

                var serial = $("#Serial").val();

                $.ajax({
                    dataType: "html",
                    data: {
                        proceso: "actualizar_anom"


                    },
                    type: "POST",
                    url: "ControladorElemento",
                    statusCode: {
                        404: function () {
                            alert("page not found");
                        }
                    }
                }).done(function (datos) {
                    $("#actualizar").empty();
                    $("#actualizar").append(datos);
                    inicializar()
                });
            }

            function list_anomalias() {

                var serial = $("#Serial").val();

                $.ajax({
                    dataType: "html",
                    data: {
                        proceso: "listar_anom",
                        Serial: serial

                    },
                    type: "POST",
                    url: "ControladorElemento",
                    statusCode: {
                        404: function () {
                            alert("page not found");
                        }
                    }
                }).done(function (datos) {
                    $("#traer1").empty();
                    $("#traer1").append(datos);
                });
            }

            var x;
            x = $(document);
            x.ready(inicializar);

            function inicializar() {
                var x = $("#mostrar");
                x.click(muestrame);

            }

            function muestrame() {
                var x = $("#objetivo");
                x.slideToggle("slow");
            }


            function recibir(id_pres) {

                var valor = id_pres;
                localStorage.setItem("codigoElm", valor);
                $.ajax({
                    dataType: "html",
                    data: {
                        proceso: "listar_ser",
                        id_prestamo: valor

                    },
                    type: "POST",
                    url: "ConsultarPrestamo",
                    statusCode: {
                        404: function () {
                            alert("page not found");
                        }
                    }
                }).done(function (datos) {
                    $("#traer").empty();
                    $("#traer").append(datos);
                });
            }
            function anomalia() {



                $.ajax({
                    dataType: "html",
                    data: {
                        proceso: "agregar_anomalia",
                        serial: $("#Serial1").val(),
                        anomalia: $("#Anomalia1").val()

                    },
                    type: "POST",
                    url: "ConsultarPrestamo",
                    statusCode: {
                        404: function () {
                            alert("page not found");
                        }
                    }
                }).done(function (datos) {

                    recibir(localStorage.getItem("codigoElm"));
                });
                $("#Anomalia1").val("");
            }




        </script>
        <script type="text/javascript" charset="utf-8">
            $(document).ready(function () {
                $('#tblArea').dataTable({
                    "oLanguage": {
                        "sUrl": "bootstrap/js/Spanish.json"
                    }
                });
            });
        </script>

    </body>
</html>





