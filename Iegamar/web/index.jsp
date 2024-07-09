

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" language="java" import="java.util.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IEGAMAR</title>
        <link href="css/signin.css" rel="stylesheet">
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="bootstrap/css/alertify.css" rel="stylesheet" type="text/css"/>
        <link href="bootstrap/css/themes/bootstrap.css" rel="stylesheet" type="text/css"/>


    </head>
    <body>        
                          
        <form class="form-signin" role="form" action="ValidarUsuario" method="Post">
            <h2 class="form-signin-heading">Inicio de Sesión</h2>
            <input type="text" class="form-control" placeholder="Usuario"  name="usuario" required autofocus>
            <input type="password" class="form-control" name="Contra" placeholder="Contraseña"  required>

            <button class="btn btn-lg btn-primary btn-block" type="submit">Ingresar</button>

        </form>


        <script src="js/jquery-1.6.min.js" type="text/javascript"></script>
        <script src="bootstrap/js/alertify.js" type="text/javascript"></script>

        <script type="text/javascript">
            // override defaults
            // alertify.defaults.transition = "slide";
            alertify.defaults.theme.ok = "btn btn-danger";
            alertify.defaults.theme.cancel = "btn btn-danger";
            alertify.defaults.theme.input = "form-control";
        </script>

        <%
            String alerte = (String) request.getAttribute("alert");
            if (alerte != null) {
                out.print(alerte);
            }

        %>


    </body>
</html>