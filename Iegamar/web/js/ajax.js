/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/*        $(document).ready(function () {
 $("#serial").click(function () {
 recibir();
 })
 });*/

function recibir() {
    var valor = $("input[name='opciones']:checked").val();
    $.ajax({
        dataType: "html",
        data: {
            opciones: valor
        },
        type: "POST",
        url: "ajaxPrestamo",
        statusCode: {
            404: function () {
                alert("page not found");
            }
        }
    }).done(function (datos) {

        $("#traer").empty();
        $("#traer").append(datos);

        $("#ConProEsT").select2({
            minimumInputLength: 2
        });

    });
}
 function recibir2() {

  var valor = $("input[name='opciones']:checked").val();
  
    $.ajax({
        dataType: "html",
        data: {
            opciones: valor
        },
        type: "POST",
        url: "ajaxPrestamo",
        statusCode: {
            404: function () {
                alert("page not found");
            }
        }
    }).done(function (datos) {

        $("#traer").empty();
        $("#traer").append(datos);

        $("#ConProEsT").select2({
            minimumInputLength: 2
        });

    });
    
 }