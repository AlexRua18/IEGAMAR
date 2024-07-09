<%-- 
    Document   : Menu
    Created on : 11-ago-2014, 10:56:47
    Author     : MAÑANA
--%>

<%@page import="Controlador.ControladorElemento"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="true" %>


<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Cache-control" content="no-cache">
        <meta http-equiv="Cache-control" content="no-store">

        <title>IEGAMAR</title>

        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="css/navbar-fixed-top.css" rel="stylesheet">
        <link href="css/formulario.css" rel="stylesheet" type="text/css"/>

    </head>

    <body>
        <div class="container-fluid" id="formulario">

            <div class="row">

                <section class="container">
                    <form role="form" action="NewAdmin" method="POST" class="payment-form">
                        <div class="container-page">        
                            <div class="col-md-6">
                                <h3 class="dark-grey">Registro De Cuentas </h3>
                                <div class="form-group col-lg-6">
                                    <label>Nombre de usuario</label>
                                    <input type="" name="nombreUsuario" class="form-control" id="nombreU" onkeypress="return validarn(event)">
                                </div>

                                <div class="form-group col-lg-6">
                                    <label>Contraseña</label>
                                    <input type="password" name="contrasena" class="form-control" id="contra" value="">
                                </div>   

                                <button type="submit" id="Guardar" name="guardar" value="insertar" class="btn btn-success" style="margin-left: 446px;">Guardar</button>
                            </div>


                        </div>
                    </form>
                </section>

            </div>
        </div>



        <script type="text/javascript" src="js/jquery-1.6.min.js"></script>
        <script src="bootstrap/js/bootstrap.js"></script>
        <script src="bootstrap/js/alertify.js" type="text/javascript"></script>       
        <script src="js/pnotify.custom.min.js" type="text/javascript"></script>


        <script>
                                        alertify.defaults.theme.ok = "btn btn-success";
                                        
        </script>
        <%            String alerte = (String) request.getAttribute("alert");
            if (alerte != null) {
                out.print(alerte);
            }

        %>

        <script>


            function validarn(e) {
                tecla = (document.all) ? e.keyCode : e.which;
                if (tecla == 8)
                    return true;
                if (tecla == 9)
                    return true;
                if (tecla == 11)
                    return true;
                patron = /[0-9A-Za-zñÑáéíóúÁÉÍÓÚàèìòùÀÈÌÒÙâêîôûÂÊÎÔÛÑñäëïöüÄËÏÖÜ\s\t]/;
                te = String.fromCharCode(tecla);
                return patron.test(te);
            }

            $(document).ready(function validar() {

                $("#Guardar").click(function () {
                    if (($("#nombreU").val()) == "") {

                        new PNotify({
                            title: 'Campo requerido',
                            text: 'El nombre de usuario es necesario.',
                            type: 'error'
                        });


                        return false;
                    } else {
                        if (($("#contra").val()) == "") {
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



    </body>
</html>

