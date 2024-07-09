<%-- 
    Document   : registarcuentas
    Created on : 11-ago-2014, 11:14:32
    Author     : MAÑANA
--%>

<%@page import="Controlador.ConReserva"%>
<%@page import="Controlador.ControladorElemento"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%
    HttpSession sesionOk = request.getSession();
    String usuario = "";
    String tipo = "";

    String OGrado = "<li><a href=\"registargradoygrupo.jsp\" style=\"margin-top: -46px;\">Grados</a></li>";

    String OEstudiante = "<li><a href=\"consultarestudiante.jsp\" style=\"margin-top: -46px;\">Estudiantes</a></li>";

    String OProfesores = "<li><a href=\"consultarprofesores.jsp\" style=\"margin-top: -46px;\">Profesores</a></li>";

    String OPrestamo = " <li><a href=\"consultarprestamo.jsp\" style=\"margin-left: 65px; margin-top: -42px;\">Préstamo</a></li>";

    String OReserva = "<li  class=\"active\"><a href=\"consultarreserva.jsp\" style=\"margin-top: -46px;\">Reserva</a></li>  ";

    String OControl = "<li><a href=\"consultarcontrol.jsp\" style=\"margin-top: -46px;\">Control de llegadas</a></li> ";

    String OElementos = "<li><a href=\"consultarelemento.jsp\" style=\"margin-top: -46px;\">Elementos</a></li>";

    String OCuenta = "<li><a href=\"consultarcuentas.jsp\" style=\"margin-left: 122px; margin-top: -42px;\">Administar Cuentas </a></li>";

    if (sesionOk.getAttribute("usuario") != null && sesionOk.getAttribute("Rol") != null) {
        usuario = sesionOk.getAttribute("usuario").toString();
        tipo = sesionOk.getAttribute("Rol").toString();

        if (tipo.equals("Super")) {
            OPrestamo = "";
        }

        if (tipo.equals("Control")) {
            OCuenta = "";
            OElementos = "";
            OPrestamo = "";
            OReserva = "";
        }

        if (tipo.equals("Profesores")) {

            response.sendRedirect("index.jsp");
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
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/dashboard.css" rel="stylesheet">
        <link href="css/formulario.css" rel="stylesheet" type="text/css"/>
        <link href="css/pnotify.custom.min.css" rel="stylesheet" type="text/css"/>
        <link href="bootstrap/css/alertify.css" rel="stylesheet" type="text/css"/>
        <link href="bootstrap/css/themes/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="css/select2.css" rel="stylesheet" type="text/css"/>
        <link href="css/select2-bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="css/style_light.css" rel="stylesheet" type="text/css"/>

    </head>
    <body>

        <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">IEGAMAR</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
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

        <div class="container-fluid" id="formulario">
            <div class="row">
                <div class="col-sm-3 col-md-2 sidebar">
                    <ul class="nav nav-sidebar">
                        <li class="active"><a href="registarreserva.jsp">Registar Reserva</a></li>
                        <li><a href="consultarreserva.jsp">Consutar Reserva</a></li>
                    </ul>
                </div>



                <section class="container">
                    <form action="ConReserva" method="POST" class="payment-form">
                        <input type="hidden" name="proceso" value="Registrar">
                        <div class="container-page">        
                            <div class="col-md-6">
                                <h3 class="dark-grey">Registro De Reservas</h3>

                                <div class="form-group col-lg-12" id="Pro">

                                </div>

                                <div class="form-group col-lg-4">
                                    <label for="disabledSelect">Fecha De Reserva</label>
                                    <input type="text" id="disabledTextInput" class="form-control" placeholder="" name="Fecha" readonly="readonly" value="<%=Formato.format(Fecha)%>">
                                </div>

                                <div class="form-group col-lg-4">
                                    <label for="disabledSelect">Fecha entrega</label>
                                    <input class="form-control fechaESP" maxlength="10" minlength="10" name="Fechaentrega" placeholder="20-12-2020" value="">
                                </div>

                                <div class="form-group col-lg-4">
                                    <label for="disabledSelect">Hora entrega</label>
                                    <input type="time" id="disabledTextInput" class="form-control" name="Hora" placeholder="">
                                </div>      

                                <div class="form-group col-lg-12">
                                    <label for="disabledSelect">Observaciones</label>
                                    <textarea rows="2" maxlength="150" name="Observaciones" id="Observaciones" cols="50" onkeypress="return validarn(event)" class="form-control col-lg-12">
                                    </textarea>
                                </div>

                                <div class="form-group col-lg-12" id="traer">

                                </div>

                                <div class="form-group col-lg-12">
                                    <button type="button" class="btn btn-default preview-add-button">
                                        <span class="glyphicon glyphicon-plus"></span>Mas
                                    </button>

                                    <table class="table2 preview-table" id="tbl">
                                        <thead>
                                            <tr>
                                                <th>Seriales</th>
                                            </tr>
                                        </thead>
                                        <tbody></tbody> <!-- preview content goes here-->
                                    </table>
                                </div>
                                <button type="submit" id="Guardar" name="guardar" value="insertar" onclick="xd();" class="btn btn-success" style="margin-left: 360px;">Guardar</button>
                                <input type="hidden"  class="form-control2" id="serial" name="serial" placeholder="">
                                <button type="Reset" class="btn btn-default">Cancelar</button>
                            </div>
                        </div>
                    </form>
                </section>

            </div>
            <script type="text/javascript" src="js/jquery-1.6.min.js"></script>
            <script src="js/jquery.validate.min.js" type="text/javascript"></script>
            <script src="bootstrap/js/bootstrap.js"></script>
            <script src="bootstrap/js/alertify.js" type="text/javascript"></script>       
            <script src="js/select2.js" type="text/javascript"></script>      
            <script src="js/pnotify.custom.min.js" type="text/javascript"></script>
            <script src="js/mapeomod.js" type="text/javascript"></script>
           <%
                String alerte = (String) request.getAttribute("alert");
                if (alerte != null) {
                    out.print(alerte);
                }
            %>






            <script>


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
                                            if (($("#Cuenta").val()) == "") {

                                                new PNotify({
                                                    title: 'Campo requerido',
                                                    text: 'El nombre de usuario es necesario.',
                                                    type: 'error'
                                                });


                                                return false;
                                            } else {
                                                if (($("#Contra").val()) == "") {
                                                    new PNotify({
                                                        title: 'Campo requerido',
                                                        text: 'La contraseña es necesario.',
                                                        type: 'error'
                                                    });
                                                    return false;
                                                }

                                            }
                                        });
                                    });



            </script>

            <script>

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
                        if (($("#Cuenta").val()) == "") {

                            new PNotify({
                                title: 'Campo requerido',
                                text: 'El nombre de usuario es necesario.',
                                type: 'error'
                            });


                            return false;
                        } else {
                            if (($("#Contra").val()) == "") {
                                new PNotify({
                                    title: 'Campo requerido',
                                    text: 'La contraseña es necesario.',
                                    type: 'error'
                                });
                                return false;
                            }

                        }
                    });
                });



            </script>

            <script>
                alertify.defaults.theme.ok = "btn btn-success";
                alertify.defaults.theme.cancel = "btn btn-danger";
                alertify.defaults.theme.input = "form-control";
            </script>



            <script>


                $(document).on('click', '.input-remove-row', function () {
                    var tr = $(this).closest('tr');
                    tr.fadeOut(200, function () {
                        tr.remove();

                    });
                });
                var mySerialArray = new Array();
                //var row = "";
                $(function () {
                    $('.preview-add-button').click(function () {
                        //var form_data = {};
                        //var miArray = new Array ();

                        if ($('.payment-form input[name="Seriales"]').val() != "")
                        {
                            /*
                             
                             form_data["Seriales"] = $('.payment-form input[name="Seriales"]').val();
                             
                             form_data["remove-row"] = '<span class="glyphicon glyphicon-remove"></span>';
                             var row = $('<tr></tr>');
                             $.each(form_data, function (type, value) {
                             
                             $('<td class="input-' + type + '"></td>').html(value).appendTo(row);
                             } */
                            //ESTA VARIABLE DEBE SER GLOBAL NO DEBE ESTAR DENTRO DEL CLICK

                            $('.payment-form').find('[name="Seriales"]').each(function (index, element) {
                                //AQUI LE DICES QUE TE BUSQUE TODO LOS OBJETOS CON EL ATRIBUTO NAME Y SU VALOR SERIALES
                                //por cada objeto con atributo name y valor SERIALES va a buscar su valor 

                                //aqui podemos obtener el valor

                                var serialDelCampo = $('[name="Seriales"]').val();
                                link = $('<a href="javascript:void(0)" class="input-remove-row glyphicon glyphicon-remove"></a>').html(serialDelCampo);
                                // serialDelCampo.appendTo(link);
                                //SI NO ESTAS SEGURO DEL VALOR DE LA VARIABLE ENTONCES DESCOMENTA ESTA LINEA Y FIJATE EN LA CONSOLA
                                // console.log(serialDelCampo);

                                miSerial = validarSerial(serialDelCampo);
                                console.log(miSerial);
                                // console.log(miSerial);
                                if (miSerial == true)
                                {
                                    if(serialDelCampo != null){
                                    var newtd = $('<tr><td class="input-' + index + '"></td></tr>').html(link);
                                    // $("#serial").val($("#serial").val() + serialDelCampo + "-");
                                    $('.preview-table tbody').append(newtd);
                                }else {
                                    new PNotify({
                                        title: 'Campo requerido',
                                        text: 'Ingrese Otro Serial.',
                                        type: 'error'
                                    });
                                }
                                } else {
                                    new PNotify({
                                        title: 'Campo requerido',
                                        text: 'El serial ya esta.',
                                        type: 'error'
                                    });
                                }

                            });


                        }
                        document.getElementById("Seriales").value = "";
                    });

                });
                function validarSerial(numSerial) {
                    // console.log(currentArray.length);
                    var bandera = true;
                    $("#tbl tbody tr").each(function () {
                        if ($(this).find("a").text() == numSerial) {
                            bandera = false;
                        }
                    });


                    return bandera;
                }

                function xd() {
                    var valor = '';
                    $("#tbl tbody tr").each(function () {
                        valor += $(this).find("a").text() + "-";
                    });
                    $("#serial").val(valor);
                }

            </script>

            <script>

                $(function () {
                    recibirPro();

                });

                function recibirPro() {
                    $.ajax({
                        dataType: "html",
                        data: {
                        },
                        type: "POST",
                        url: "ajaxProfesoresRes",
                        statusCode: {
                            404: function () {
                                alert("page not found");
                            }
                        }
                    }).done(function (datos) {

                        $("#Pro").empty();
                        $("#Pro").append(datos);

                        $("#ConPros").select2({
                            minimumInputLength: 2
                        });

                    });
                }

                $(function () {
                    recibir();

                });

                function recibir() {
                    $.ajax({
                        dataType: "html",
                        data: {
                        },
                        type: "POST",
                        url: "ajaxReserva",
                        statusCode: {
                            404: function () {
                                alert("page not found");
                            }
                        }
                    }).done(function (datos) {

                        $("#traer").empty();
                        $("#traer").append(datos);

                        $("#Seriales").select2({
                            minimumInputLength: 2
                        });

                    });
                }


            </script>

            <script>
                $(document).ready(function ()
                {
                    $(".payment-form").validate();
                    jQuery.validator.addMethod("fechaESP", function (value, element)
                    {
                        var validator = this;
                        var datePat = /^(\d{1,2})(\/|-)(\d{1,2})(\/|-)(\d{4})$/;
                        var fechaCompleta = value.match(datePat);
                        if (fechaCompleta == null) {
                            new PNotify({
                                title: 'Campo requerido',
                                text: 'Ingrese Fecha De Reserva.',
                                type: 'error'
                            });
                            return false;
                        }

                        dia = fechaCompleta[1];
                        mes = fechaCompleta[3];
                        anio = fechaCompleta[5];

                        if (dia < 1 || dia > 31) {
                            new PNotify({
                                title: 'Dia Invalido',
                                text: 'El valor del día debe estar comprendido entre 1 y 31.',
                                type: 'error'
                            });
                            return false;
                        }
                        if (mes < 1 || mes > 12) {
                            new PNotify({
                                title: 'Mes Invalido',
                                text: 'El valor del mes debe estar comprendido entre 1 y 12.',
                                type: 'error'
                            });
                            return false;
                        }
                        if ((mes == 4 || mes == 6 || mes == 9 || mes == 11) && dia == 31) {
                            new PNotify({
                                title: 'Mes Invalido',
                                text: 'El mes' + mes + 'no tiene 31 días!',
                                type: 'error'
                            });
                            return false;
                        }
                        if (mes == 2) { // bisiesto
                            var bisiesto = (anio % 4 == 0 && (anio % 100 != 0 || anio % 400 == 0));
                            if (dia > 29 || (dia == 29 && !bisiesto)) {
                                new PNotify({
                                    title: 'Mes Invalido',
                                    text: 'Febrero del' + anio + 'no contiene' + dia + 'dias!',
                                    type: 'error'
                                });
                                return false;
                            }
                        }
                        return true;
                    }, "");
                });
            </script>

    </body>
</html>


