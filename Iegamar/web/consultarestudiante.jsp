<%-- 
    Document   : registarcuentas
    Created on : 11-ago-2014, 11:14:32
    Author     : MAÑANA
--%>

<%@page import="Controlador.ConReserva"%>
<%@page import="Controlador.ControladorElemento"%>
<%@page import="Controlador.ConsultarEstudiantes"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%
    ControladorElemento crt = new ControladorElemento();
    ConReserva Res = new ConReserva();

    HttpSession sesionOk = request.getSession();
    String usuario = "";
    String tipo = "";

    String OGrado = "<li><a href=\"registargradoygrupo.jsp\" style=\"margin-top: -46px;\">Grados</a></li>";

    String OEstudiante = "<li class=\"active\"><a href=\"consultarestudiante.jsp\" style=\"margin-top: -46px;\">Estudiantes</a></li>";

    String OProfesores = "<li><a href=\"consultarprofesores.jsp\" style=\"margin-top: -46px;\">Profesores</a></li>";

    String OPrestamo = " <li><a href=\"consultarprestamo.jsp\" style=\"margin-top: -46px;\">Préstamo</a></li>";

    String OReserva = "<li><a href=\"consultarreserva.jsp\" style=\"margin-top: -46px;\">Reserva</a></li>  ";

    String OControl = "<li><a href=\"consultarcontrol.jsp\" style=\"margin-top: -46px;\">Control de llegadas</a></li> ";

    String OElementos = "<li><a href=\"consultarelemento.jsp\" style=\"margin-top: -46px;\">Elementos</a></li>";

    String OCuenta = "<li><a href=\"consultarcuentas.jsp\" style=\"margin-left: 55px; margin-top: -42px;\">Administar Cuentas</a></li>";

    String Anomaliaseriales = "<div id='actualizar' style='margin-top: 8px;' >" + crt.anomaliacont() + " </div> ";

    String Anomaliareserva = "<div id='actualizar2' style=' margin-left: 60px; margin-top: -42px; '>" + Res.resercount() + "</div>";

    if (sesionOk.getAttribute("usuario") != null && sesionOk.getAttribute("Rol") != null) {
        usuario = sesionOk.getAttribute("usuario").toString();
        tipo = sesionOk.getAttribute("Rol").toString();

        if (tipo.equals("Super")) {
            OPrestamo = "";
            OReserva = "";
            Anomaliareserva = "";
        }

        if (tipo.equals("Control")) {
            OCuenta = "";
            OElementos = "";
            OPrestamo = "";
            OReserva = "";
        }

        if (tipo.equals("Estudiante")) {

            response.sendRedirect("index.jsp");
        }

        if (tipo.equals("Profesores")) {

            response.sendRedirect("index.jsp");
        }

    } else {
        response.sendRedirect("index.jsp");
    }
%>


<!DOCTYPE html>
<!DOCTYPE>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="keywords" content="jquery,ui,easy,easyui,web">
        <meta name="description" content="easyui help you build your web page easily!">
        <title>IEGAMAR</title>
        <link rel="stylesheet" type="text/css" href="css/easyui.css">
        <link rel="stylesheet" type="text/css" href="css/icon.css">
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="css/dashboard.css" rel="stylesheet">
        <link href="bootstrap/css/dataTables.bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="bootstrap/css/alertify.css" rel="stylesheet" type="text/css"/>
        <link href="bootstrap/css/themes/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="css/pnotify.custom.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/style_light.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>

        <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" >IEGAMAR</a>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">      


                        <%=Anomaliaseriales%>
                        <%=Anomaliareserva%>
                        <%=OCuenta%>
                        <%=OGrado%>
                        <%=OEstudiante%>
                        <%=OProfesores%>
                        <%=OElementos%>
                        <%=OPrestamo%>
                        <%=OControl%>
                        <%=OReserva%>                            
                    </ul>
                    <ul class="nav navbar-nav navbar-right">


                        <li class="active"><a href="CerrarSesion.jsp">Cerrar Sesión</a></li>

                    </ul>
                </div><!--/.nav-collapse -->
            </div>

            <div hidden="true" class="notification-list-wrapper" id="objetivo" style="top: 65px; left: 100px; opacity: 1;">

                <ul class="notification-list-menu">
                </ul>

                <ul class="notification-list" data-type="unread">

                    <table class="table2 table-hover" cellspacing="0" width="100%">
                        <thead>
                            <tr>
                                <th class="text-center">Seriales</th>
                                <th class="text-center">Anomalia</th>
                            </tr>
                        </thead>
                        <tbody id="traer1">
                            <%
                                out.println(crt.listaranom());
                            %>
                        </tbody>
                    </table>



                </ul>
            </div>

            <!------------------------------------------------------------------------------------------------------------------------------->
            <div hidden="true" class="notification-list-wrapper" id="objetivo2" style="top: 65px; left: 100px;  opacity: 1;">

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


            <div class="modal fade bs-example-modal-sm"  tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-sm">
                    <div class="modal-content">
                        <div class="modal-header" >
                            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                            <h4 class="modal-title" id="myModalLabel">Anomalia</h4>
                        </div>

                        <div class="modal-body"  >
                            <div class="form-group">
                                <label for="disabledSelect">Serial</label>
                                <input type="text"  class="form-control" name="Serial" id="Serial" readonly="readonly" placeholder="">
                            </div>
                            <div class="form-group">
                                <label for="disabledSelect">Descripción</label>

                                <textarea rows="4" name="Anomalia" id="Anomalia" cols="50" class="form-control" readonly="readonly">

                                </textarea>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" data-dismiss="modal" onclick="list_anomalias();"  class="btn btn-success">Guardar</button>
                        </div>
                    </div>

                </div>
            </div>

        </nav>

        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-3 col-md-2 sidebar">
                    <ul class="nav nav-sidebar">
                        <li><a href="registarestudiante.jsp">Registar Estudiantes</a></li>
                        <li class="active"><a href="consultarestudiante.jsp">Consutar Estudiantes</a></li>
                    </ul>
                </div>
                <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                    <h1 class="page-header">Consultar Estudiante</h1>
                </div>
            </div>
        </div>
        <div class="tab-content">
            <div class="tab-pane active" id="area">
                <table id="tblArea" class="table table-hover" cellspacing="0" >
                    <thead>
                        <tr>
                            <th class="text-center">Identificación</th>
                            <th class="text-center">Nombre</th>
                            <th class="text-center">Apellido</th>
                            <th class="text-center">Direccion</th>
                            <th class="text-center">Telefono</th>
                            <th class="text-center">Grado</th>                           
                            <th class="text-center">Editar</th>
                            <th class="text-center">Estado</th>
                        </tr>
                    </thead>
                    <tbody id="traer">
                        <%
                            ConsultarEstudiantes list_Est = new ConsultarEstudiantes();
                            out.println(list_Est.listarEst());

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
                        <h4 class="modal-title" id="myModalLabel">Modificar Estudiantes</h4>
                    </div>
                    <form action="ConsultarEstudiantes" method="POST" >
                        <div class="modal-body">
                            <div class="form-group">
                                <label for="disabledSelect">Nombre</label>
                                <input type="text" id="nombre" class="form-control" name="nombre" placeholder="" onkeypress="sololetras()">
                            </div>
                            <div class="form-group">
                                <label for="disabledSelect">Apellido</label>
                                <input type="text" id="apellido" class="form-control" name="apellido" placeholder="" onkeypress="sololetras()">
                            </div>
                            <div class="form-group">
                                <label for="disabledSelect">Identificación</label>
                                <input type="text" id="identificacion" class="form-control" name="identificacion" placeholder="" onkeypress="ValidaSoloNumeros()">
                            </div>
                            <div class="form-group">
                                <label for="disabledSelect">Dirección</label>
                                <input type="text" id="direccion" class="form-control" name="direccion" placeholder="">
                            </div>
                            <div class="form-group">
                                <label for="disabledSelect">Telefono</label>
                                <input type="text" id="telefono" class="form-control" name="telefono" placeholder="" onkeypress="ValidaSoloNumeros()">
                            </div>

                            <div class="form-group">
                                <label for="disabledSelect">Grado</label>
                                <select id="grado" name="grado" class="form-control">
                                    <%                                        ConsultarEstudiantes gra = new ConsultarEstudiantes();
                                        out.println(gra.Traer());
                                    %>
                                </select>
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



        <script type="text/javascript" src="js/jquery-1.6.min.js"></script>
        <script src="bootstrap/js/bootstrap.js"></script>
        <script src="bootstrap/js/jquery.dataTables.min.js"></script>
        <script src="bootstrap/js/dataTables.bootstrap.js" type="text/javascript"></script>
        <script src="js/mapeomod.js" type="text/javascript"></script>
        <script src="bootstrap/js/alertify.js" type="text/javascript"></script>
        <script src="js/pnotify.custom.min.js" type="text/javascript"></script>

        <script type="text/javascript">
                                    alertify.defaults.theme.ok = "btn btn-success";
                                    alertify.defaults.theme.cancel = "btn btn-danger";
                                    alertify.defaults.theme.input = "form-control";

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


                                    $(document).ready(function validar() {

                                        $("#Guardar").click(function () {
                                            if (($("#nombre").val()) == "") {

                                                new PNotify({
                                                    title: 'Campo requerido',
                                                    text: 'El nombre es necesario.',
                                                    type: 'error'
                                                });


                                                return false;
                                            } else {
                                                if (($("#apellido").val()) == "") {
                                                    new PNotify({
                                                        title: 'Campo requerido',
                                                        text: 'El apellido es necesario.',
                                                        type: 'error'
                                                    });
                                                    return false;
                                                } else {

                                                    if (($("#identificacion").val()) == "") {
                                                        new PNotify({
                                                            title: 'Campo requerido',
                                                            text: 'La identificacion es necesaria.',
                                                            type: 'error'
                                                        });
                                                        return false;
                                                    } else {

                                                        if (($("#direccion").val()) == "") {
                                                            new PNotify({
                                                                title: 'Campo requerido',
                                                                text: 'La dirección es necesaria.',
                                                                type: 'error'
                                                            });
                                                            return false;
                                                        } else {

                                                            if (($("#telefono").val()) == "") {
                                                                new PNotify({
                                                                    title: 'Campo requerido',
                                                                    text: 'El telefono es necesario.',
                                                                    type: 'error'
                                                                });
                                                                return false;
                                                            }
                                                        }

                                                    }
                                                }
                                            }

                                        });

                                    });
                                    function ValidaSoloNumeros() {
                                        if ((event.keyCode < 48) || (event.keyCode > 57))
                                            event.returnValue = false;
                                    }
                                    function sololetras() {
                                        if ((event.keyCode != 32) && (event.keyCode < 65) || (event.keyCode > 90) && (event.keyCode < 97) || (event.keyCode > 122))
                                            event.returnValue = false;
                                    }
                                    function pulsar(e) {
                                        tecla = (document.all) ? e.keyCode : e.which;
                                        return (tecla != 13);
                                    }
                                    function validarLetrasNumeros() {
                                        if ((event.keyCode < 48) || (event.keyCode > 57))
                                        {
                                            event.returnValue = false;
                                        }
                                        if ((event.keyCode < 41) || (event.keyCode > 90))
                                        {
                                            event.returnValue = false;
                                        }
                                    }
        </script>

        <script>



            function cargar() {



                $.ajax({
                    dataType: "html",
                    data: {
                        recargar: "recargue"

                    },
                    type: "POST",
                    url: "ConsultarEstudiantes",
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
            function Estado_habilitado(identi) {


                $.ajax({
                    dataType: "html",
                    data: {
                        identificacion_mod: identi,
                        estado_mod: "Inhabilitado"
                    },
                    type: "POST",
                    url: "ConsultarEstudiantes",
                    statusCode: {
                        404: function () {
                            alert("page not found");
                        }
                    }
                }).done(function (datos) {
                    $("#cambio_est").empty();
                    $("#cambio_est").append(datos);
                    cargar();
                });

            }
            function Estado_inhabilitado(identi) {


                $.ajax({
                    dataType: "html",
                    data: {
                        identificacion_mod: identi,
                        estado_mod: "Habilitado"
                    },
                    type: "POST",
                    url: "ConsultarEstudiantes",
                    statusCode: {
                        404: function () {
                            alert("page not found");
                        }
                    }
                }).done(function (datos) {
                    $("#cambio_est").empty();
                    $("#cambio_est").append(datos);
                    cargar();
                });

            }
            $(document).ready(function validar() {
                $("#validaNombre").hide();
                $("#validaApellido").hide();
                $("#validaIdentificacion").hide();
                $("#validaDirecion").hide();
                $("#validaTelefono").hide();
                $("#Guardar").click(function () {
                    if (($("#nombre").val()) == "") {
                        $("#validaNombre").show();
                        return false;
                    } else {
                        $("#validaNombre").hide();
                        if (($("#apellido").val()) == "") {
                            $("#validaApellido").show();
                            return false;
                        } else {
                            $("#validaApellido").hide();
                            if (($("#identificacion").val()) == "") {
                                $("#validaIdentificacion").show();
                                return false;
                            } else {
                                $("#validaIdentificacion").hide();
                                if (($("#direccion").val()) == "") {
                                    $("#validaDirecion").show();
                                    return false;
                                } else {
                                    $("#validaDirecion").hide();
                                    if (($("#telefono").val()) == "") {
                                        $("#validaTelefono").show();
                                        return false;
                                    }
                                }

                            }
                        }

                    }





                });

            });
            function ValidaSoloNumeros() {
                if ((event.keyCode < 48) || (event.keyCode > 57))
                    event.returnValue = false;
            }
            function sololetras() {
                if ((event.keyCode != 32) && (event.keyCode < 65) || (event.keyCode > 90) && (event.keyCode < 97) || (event.keyCode > 122))
                    event.returnValue = false;
            }
            function pulsar(e) {
                tecla = (document.all) ? e.keyCode : e.which;
                return (tecla != 13);
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

        <%            String alerte = (String) request.getAttribute("alert");
            if (alerte != null) {
                out.print(alerte);
            }

        %>
    </body>
</html>