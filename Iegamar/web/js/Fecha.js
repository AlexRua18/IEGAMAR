
function FechaInterfaz()
{
var f = new Date();
var fecha= (f.getDate() + "/" + (f.getMonth() +1 ) + "/" + f.getFullYear());
 document.getElementById("Fechatxt").value= fecha;
 
var hora= (f.getHours() + ":" + (f.getMinutes()) );
 document.getElementById("Horatxt").value= hora;
}