/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Reserva;
import Modelo.Elemento;
import Entidad.EntidadReserva;
import Entidad.entidadElemento;
import java.text.ParseException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import javax.print.attribute.standard.DateTimeAtCompleted;

/**
 *
 * @author EDU
 */
@WebServlet(name = "ConReserva", urlPatterns = {"/ConReserva"})
public class ConReserva extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    EntidadReserva datos_reserva = new EntidadReserva();
    Reserva Res = new Reserva();
    entidadElemento datos_ser = new entidadElemento();
    Elemento ele = new Elemento();
    java.util.Date Fecha = new java.util.Date();
    LocalDate FechaGlobal = LocalDate.now();
    SimpleDateFormat Formato = new SimpleDateFormat("dd-MM-yyyy");

    public String resercount() throws SQLException {
        String Fecha_actual = Formato.format(Fecha);
        datos_reserva.setFechaentrega(Fecha_actual);
        int num_reserva = 0;
        String pintar = "";
        ResultSet cnt_reserv = Res.contreservas(datos_reserva);
        try {
            while (cnt_reserv.next()) {
                num_reserva = cnt_reserv.getInt("conteo");
            }
            if (num_reserva <= 0) {
                pintar += "<li><a class=\"btn btn-default\" id=\"mostrar1\"  ><img src=\"css/calendar.png\"></a>  </li>";
            } else {
                pintar += "<li><a  class=\"btn btn-danger\" id=\"mostrar1\"  ><img src=\"css/calendar.png\"></a> </li>";
            }
        } catch (Exception e) {
            pintar = "error" + e.getMessage();
        }
        return pintar;
    }

    public ArrayList EstadoSerIni() {
        ResultSet ser_res = null;
        ser_res = Res.ConResrvaNot(datos_reserva);
        ArrayList SerArray = new ArrayList();
        try {
            while (ser_res.next()) {
                SerArray.add(ser_res.getString("d.Seriales").trim());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return SerArray;
    }

    //----------------------continuar-------------//
    public String estadoserialres() {
        String Imprimir = "";
        String comprobarSer = "";
        ArrayList Seriales = EstadoSerIni();
        String estado = "";
        for (int i = 0; i < Seriales.size(); i++) {
            datos_reserva.setEstado(Seriales.get(i).toString());
            estado = Res.ConEstadoSer(datos_reserva);
            if (estado.equals("Disponible")) {
                datos_ser.setSerial(Seriales.get(i).toString());
                datos_ser.setEstado("Reservado");
                ele.cambio_estadoSerial(datos_ser);
            } else if (Seriales.size() == 1) {
                comprobarSer = "Estos seriales no estaban Disponibles:";
                comprobarSer += "," + Seriales.get(i).toString();
            } else {
                comprobarSer += "," + Seriales.get(i).toString();

            }
        }

        if (comprobarSer == "") {
            Imprimir = "Reserva Sin Novedad";
        } else if (comprobarSer != "") {
            Imprimir = comprobarSer;
        }

        return Imprimir;

    }

    public void observacion(int id_reserva, String notificacion) {
        datos_reserva.setId_reserva(id_reserva);
        datos_reserva.setNotificacion(notificacion);
        Res.Notificacionreserva(datos_reserva);
    }
    //---------------------------sigo------------------------

    public ArrayList llenararray(String seriales) {

        String[] rrecorrercadena = seriales.split("-");
        ArrayList listar = new ArrayList();
        for (int i = 0; i < rrecorrercadena.length; i++) {
            listar.add(rrecorrercadena[i]);
        }
        return listar;
    }

    public String listarserial() throws SQLException {
        String Recorrertbl = "";
        ResultSet listSeriales = Res.consultarSeriales(datos_reserva);
        try {
            while (listSeriales.next()) {
                Recorrertbl += "<tr>";
                Recorrertbl += "<td><center>" + listSeriales.getString("Seriales").toString().trim() + "</center></td>";
                Recorrertbl += "</tr>";
            }
        } catch (Exception e) {
            Recorrertbl = "error" + e.getMessage();
        }

        return Recorrertbl;
    }

    public String cambioEstadoSer(String Serial, String Estado) {
        String popap = "Ok";
        datos_ser.setSerial(Serial);
        datos_ser.setEstado(Estado);
        ele.cambio_estadoSerial(datos_ser);

        return popap;
    }

    public String cambioEstadores(String IdReserva, String Estado) {
        String popap = "Ok";
        datos_reserva.setId_reserva(Integer.parseInt(IdReserva));
        datos_reserva.setEstado(Estado);
        Res.cambio_estadoreserva(datos_reserva);

        return popap;
    }

    public String listarserialPres() throws SQLException {
        String Recorrertblpre = "";
        ResultSet listSerialesp = Res.consultarSeriales(datos_reserva);
        try {
            while (listSerialesp.next()) {
                Recorrertblpre += "<tr>";
                Recorrertblpre += "<td><center>" + listSerialesp.getString("Seriales").toString().trim() + "<center></td>";
                Recorrertblpre += "</tr>";
            }
        } catch (Exception e) {
            Recorrertblpre = "error" + e.getMessage();
        }

        return Recorrertblpre;
    }

    public String ListarReservasHoy() throws SQLException {
        String Recorrertbl = "";

        ResultSet listreshoy = Res.ConReservasHoy(datos_reserva);
        try {
            while (listreshoy.next()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate parsedDate = LocalDate.parse(listreshoy.getString("r.Fecha_entrega"), formatter);

                //ACA HACER LA ACTUALIZACION
                if (parsedDate.isEqual(FechaGlobal)) {
                    observacion(Integer.parseInt(listreshoy.getString("r.Id_reserva").trim()), estadoserialres());
                    Recorrertbl += "<tr>";
                    Recorrertbl += "<td><center>" + listreshoy.getString("r.Hora_reserva").trim() + "</center></td>";
                    Recorrertbl += "<td><center>" + listreshoy.getString("Nombre").trim() + "</center></td>";
                    Recorrertbl += "<td><center>" + listreshoy.getString("r.Notificacion").trim() + "</center></td>";
                    Recorrertbl += " <td><center><a href='consultarreserva.jsp'><button  class='btn btn-default glyphicon glyphicon-eye-open'  ></button></a></center></td>";
                    Recorrertbl += "</tr>";
                } //else if (listreshoy.getDate("r.Fecha_entrega").before(Fecha)){
                else if (parsedDate.isBefore(FechaGlobal)) {

                    String IdReserva = listreshoy.getString("r.Id_reserva").trim();
                    String Estado = "Vencida";
                    String Estadoser = "Disponible";
                    String ejecutarCambio = "";
                    String serial = "";

                    cambioEstadores(IdReserva, Estado);
                    ResultSet listSerialeven = Res.consultarSerialesVen(datos_reserva);

                    try {
                        while (listSerialeven.next()) {
                            serial = listSerialeven.getString("r.Seriales").trim();
                            datos_ser.setSerial(serial);
                            datos_ser.setEstado(Estadoser);
                            ele.cambio_estadoSerial(datos_ser);
                            serial = "";
                        }
                    } catch (Exception e) {

                    }

                }
            }
        } catch (Exception e) {
            Recorrertbl = "error" + e.getMessage();
        }

        return Recorrertbl;
    }

    public String listarcontrol() throws SQLException {
        String Recorrertbl = "";
        String fecha = "";

        ResultSet listReservas = Res.consultarReservas();
        try {
            while (listReservas.next()) {
                fecha = listReservas.getString("Fecha_entrega").toString().trim();
                if (fecha.equals(Formato.format(Fecha))) {

                    Recorrertbl += "<tr class='well'>";
                    Recorrertbl += "<td Style='display:none'><center>" + listReservas.getString("Id_reserva").toString().trim() + "</center></td>";
                    Recorrertbl += "<td><center>" + listReservas.getString("Nombre monitor").toString().trim() + "</center></td>";
                    Recorrertbl += "<td><center>" + listReservas.getString("Nombre Profesor").toString().trim() + "</center></td>";
                    Recorrertbl += "<td><center>" + listReservas.getString("Identificacion").toString().trim() + "</center></td>";
                    Recorrertbl += "<td><center>" + listReservas.getString("Fecha_reserva").toString().trim() + "</center></td>";
                    Recorrertbl += "<td><center>" + listReservas.getString("Hora_reserva").toString().trim() + "</center></td>";
                    Recorrertbl += "<td><center>" + listReservas.getString("Fecha_entrega").toString().trim() + "</center></td>";
                    Recorrertbl += "<td><center>" + listReservas.getString("Observaciones").toString().trim() + "</center></td>";
                    Recorrertbl += " <td><center><button  class='btn btn-default glyphicon glyphicon-eye-open' data-toggle='modal' data-target='#myModal1' onclick = 'ListSeriales(" + listReservas.getString("Id_reserva").toString().trim() + ")' ></button></center></td>";
                    Recorrertbl += " <td><center><button  class='btn btn-primary glyphicon glyphicon-share' data-toggle='modal' data-target='#myModal2' onclick = 'mapear.Prestamo(" + '\"' + listReservas.getString("Nombre Profesor").toString().trim() + '\"' + "," + '\"' + listReservas.getString("identificacion").toString().trim() + '\"' + "," + '\"' + listReservas.getString("Fecha_entrega").toString().trim() + '\"' + "," + listReservas.getString("Id_reserva").toString().trim() + ")' ></button></center></td>";
                    Recorrertbl += "</tr>";
                } else {
                    Recorrertbl += "<tr>";
                    Recorrertbl += "<td Style='display:none'><center>" + listReservas.getString("Id_reserva").toString().trim() + "</center></td>";
                    Recorrertbl += "<td><center>" + listReservas.getString("Nombre monitor").toString().trim() + "</center></td>";
                    Recorrertbl += "<td><center>" + listReservas.getString("Nombre Profesor").toString().trim() + "</center></td>";
                    Recorrertbl += "<td><center>" + listReservas.getString("Identificacion").toString().trim() + "</center></td>";
                    Recorrertbl += "<td><center>" + listReservas.getString("Fecha_reserva").toString().trim() + "</center></td>";
                    Recorrertbl += "<td><center>" + listReservas.getString("Hora_reserva").toString().trim() + "</center></td>";
                    Recorrertbl += "<td><center>" + listReservas.getString("Fecha_entrega").toString().trim() + "</center></td>";
                    Recorrertbl += "<td><center>" + listReservas.getString("Observaciones").toString().trim() + "</center></td>";
                    Recorrertbl += " <td><center><button  class='btn btn-default glyphicon glyphicon-eye-open' data-toggle='modal' data-target='#myModal1' onclick = 'ListSeriales(" + listReservas.getString("Id_reserva").toString().trim() + ")' ></button></center></td>";
                    Recorrertbl += " <td><center><button  class='btn btn-primary glyphicon glyphicon-share' data-toggle='modal' data-target='#myModal2' onclick = 'mapear.Prestamo(" + '\"' + listReservas.getString("Nombre Profesor").toString().trim() + '\"' + "," + '\"' + listReservas.getString("identificacion").toString().trim() + '\"' + "," + '\"' + listReservas.getString("Fecha_entrega").toString().trim() + '\"' + "," + listReservas.getString("Id_reserva").toString().trim() + ")' ></button></center></td>";
                    Recorrertbl += "</tr>";

                }
            }
        } catch (Exception e) {
            Recorrertbl = "error" + e.getMessage();
        }

        return Recorrertbl;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession sesionOk = request.getSession();
            if (sesionOk.getAttribute("usuario") != null) {

                String alert = "";

                String proceso = request.getParameter("proceso");
                if (proceso.equals("Registrar")) {

                    String evento = request.getParameter("guardar");
                    if (evento.equals("insertar")) {
                        String identificacion = request.getParameter("identificacion");
                        String fecha = request.getParameter("Fecha");
                        String fechaentrega = request.getParameter("Fechaentrega");
                        String hora = request.getParameter("Hora");
                        String Observaciones = request.getParameter("Observaciones").trim();
                        String estado = "Reserva";
                        String seriales = request.getParameter("serial");
                        ArrayList arraySeri = llenararray(seriales);
                        int Cuenta = Integer.parseInt(sesionOk.getAttribute("Id_cuenta").toString());

                        datos_reserva.setSeriales(arraySeri);
                        datos_reserva.setIdentificacion(identificacion);
                        datos_reserva.setFechareserva(fechaentrega);
                        datos_reserva.setFechaentrega(fechaentrega);
                        datos_reserva.setHorareserva(hora);
                        datos_reserva.setObservaciones(Observaciones);
                        datos_reserva.setEstado(estado);
                        datos_reserva.setId_cuenta(Cuenta);;

                        if (seriales.equals("")) {
                            alert += "<script type=\"text/javascript\">";
                            alert += "alertify.alert(\"No a ingresado seriales\");";
                            alert += "</script>";
                            request.setAttribute("alert", alert);
                            getServletConfig().getServletContext().getRequestDispatcher("/registarreserva.jsp").forward(request, response);

                        } else if (Res.insertReserva(datos_reserva)) {

                            Res.registrarSeriales(datos_reserva);

                            alert += "<script type=\"text/javascript\">";
                            alert += "alertify.alert(\"Registro exito\");";
                            alert += "</script>";
                            request.setAttribute("alert", alert);
                            getServletConfig().getServletContext().getRequestDispatcher("/registarreserva.jsp").forward(request, response);
                        }
                    }

                } else if (proceso.equals("listar_seriales")) {
                    String IdReserva = request.getParameter("IdReserva");
                    datos_reserva.setId_reserva(Integer.parseInt(IdReserva));
                    out.print(listarserial());
                } else if (proceso.equals("traer_seriales")) {
                    String IdReserva = request.getParameter("IdReservap");
                    datos_reserva.setId_reserva(Integer.parseInt(IdReserva));
                    out.print(listarserialPres());
                } else if (proceso.equals("Cambio_estado_serial")) {
                    String Serial = request.getParameter("Serial");
                    String Estado = "Disponible";
                    out.print(cambioEstadoSer(Serial, Estado));

                } else if (proceso.equals("Cambio_estado_serial_dis")) {
                    String Serial = request.getParameter("Serial");
                    String Estado = "Reservado";
                    out.print(cambioEstadoSer(Serial, Estado));

                } else if (proceso.equals("Cambio_est_rev")) {
                    String IdReserva = request.getParameter("Idreserva");
                    String Estado = "Hecho";
                    cambioEstadores(IdReserva, Estado);
                } else if (proceso.equals("Reservas_hoy")) {

                    out.print(resercount());
                } else if (proceso.equals("Comprobar_reservas")) {
                    //seguir ---------------------------------------------
                    String Fecha_actual = Formato.format(Fecha);
                    datos_reserva.setFechaentrega(Fecha_actual);
                    out.print(ListarReservasHoy());
                }

            } else {
                response.sendRedirect("index.jsp");
            }

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ConReserva.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ConReserva.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
