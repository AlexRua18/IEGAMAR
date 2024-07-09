/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var mapear = {
    grados: function (id_graxgru, grado) {
        $("#id_grado").val(id_graxgru);
        $("#grado").val(grado);
        $('#grado').attr("required","required");
         $('#guardar').val("modificar");
        $('#guardar').removeClass('btn-success').addClass('btn-primary');
    },
    profesores: function (Identificacion, Nombre, Apellido, Direccion, Telefono, Fecha_Nacimiento, Perfil_Profesional)
    {
        $("#Identificacion").val(Identificacion);
        $("#Nombre").val(Nombre);
        $("#Apellido").val(Apellido);
        $("#Direccion").val(Direccion);
        $("#Telefono").val(Telefono);
        $("#Fecha_Nacimiento").val(Fecha_Nacimiento);
        $("#Perfil_Profesional").val(Perfil_Profesional);
    },
    devolucion: function (NombrePrestador,Id_prestamo){
        $("#NombrePrestador").val(NombrePrestador);
        $("#Id_prestamo").val(Id_prestamo);
    },
    Cuenta: function (Id_cuenta,nombre,Cuenta){
        $("#Id_cuenta").val(Id_cuenta);
        $("#nombre").val(nombre);
        $("#Cuenta").val(Cuenta);
    },
    CuentaEst: function (Id_cuenta,nombre,Cuenta){
        $("#Id_cuenta").val(Id_cuenta);
        $("#nombre").val(nombre);
        $("#Cuenta").val(Cuenta);
    },
    serialAnom: function (NombrePrestador,Id_prestamo){
        $("#Serial1").val(NombrePrestador);
        $("#Id_prestamo").val(Id_prestamo);
    },
      estudiantes: function (Identificacion, Nombre, Apellido, Direccion, Telefono,Grado)
    {
        $("#identificacion").val(Identificacion);
        $("#nombre").val(Nombre);
        $("#apellido").val(Apellido);
        $("#direccion").val(Direccion);
        $("#telefono").val(Telefono);
        $("#grado").val(Grado);
    },
    Elementos: function(Codigo,nombre_elem,decripcion)
    {
         $("#codigo").val(Codigo);
          $("#nom_elemento").val(nombre_elem);
           $("#descripcion").val(decripcion);
        
    },
    serial: function (codigo){
        $("#id_codigo").val(codigo);
        
    },
    anomalia: function (serial,anomalia)
    {
      $("#Serial").val(serial);
      $("#Anomalia").val(anomalia);
    }, 
    Prestamo: function(nombre,identificacion,fechaentrega,id_reserva){
       $("#Nombre").val(nombre);
       $("#identificacion").val(identificacion);
       $("#FechaEntrega").val(fechaentrega);
//       
       ListSerialesPres(id_reserva);
    }
};

