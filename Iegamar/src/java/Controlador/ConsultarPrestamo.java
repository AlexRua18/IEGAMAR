/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Entidad.entidadPrestamo;
import Modelo.Prestamo;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Gamani
 */
@WebServlet(name = "ConsultarPrestamo", urlPatterns = {"/ConsultarPrestamo"})
public class ConsultarPrestamo extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    entidadPrestamo datosPrestamo = new entidadPrestamo();
    Prestamo Pres = new Prestamo();

    public String listarPrestamo() throws SQLException {
        String Recorrertbl = "";
        ResultSet listPrestamo = Pres.consultarPrestamo();
        ResultSet listPrestamo1 = Pres.consultarPrestamo1();
        try {
            while (listPrestamo.next()) {
                Recorrertbl += "<tr>";
                Recorrertbl += "<td Style='display:none'><center>" + listPrestamo.getString("Id_prestamo").toString().trim() + "</center></td>";
                Recorrertbl += "<td><center>" + listPrestamo.getString("Prestador").toString().trim() + "</center></td>";
                Recorrertbl += "<td><center>" + listPrestamo.getString("p.Fecha_prestamo").toString().trim() + "</center></td>";
                Recorrertbl += "<td><center>" + listPrestamo.getString("p.Hora_prestamo").toString().trim() + "</center></td>";
                Recorrertbl += "<td><center>" + listPrestamo.getString("NombrePrestador").toString().trim() + "</center></td>";
                Recorrertbl += "<td><center>" + listPrestamo.getString("p.Fecha_devolucion").toString().trim() + "</center></td>";
                Recorrertbl += "<td><center>" + listPrestamo.getString("p.Hora_devolucion").toString().trim() + "</center></td>";
                Recorrertbl += "<td><center>" + listPrestamo.getString("devolucion").toString().trim() + "</center></td>";
                Recorrertbl += " <td><center><button data-toggle='modal' data-target='#myModal1' onclick ='recibir(" + listPrestamo.getString("Id_prestamo").toString().trim() + ")' class='btn btn-default glyphicon glyphicon-eye-open'  data-toggle='modal' data-target='#myModal1'></button></center></td>";
                if (listPrestamo.getString("devolucion").toString().trim().equals("No Asignado")) {
                    Recorrertbl += "<td><center><button  class='btn btn-danger glyphicon glyphicon-share' data-toggle='modal' data-target='#myModal' onclick='mapear.devolucion(" + '\"' + listPrestamo.getString("NombrePrestador").toString().trim() + '\"' + "," + listPrestamo.getString("Id_prestamo").toString().trim() + ")' ></button></center></td>";
                } else {
                    Recorrertbl += "<td><center><button  class='btn btn-success  glyphicon glyphicon-check'></button></center></td>";
                }
                Recorrertbl += "</tr>";
            }

            while (listPrestamo1.next()) {
                Recorrertbl += "<tr>";
                Recorrertbl += "<td Style='display:none'><center>" + listPrestamo1.getString("Id_prestamo").toString().trim() + "</center></td>";
                Recorrertbl += "<td><center>" + listPrestamo1.getString("Prestador").toString().trim() + "</center></td>";
                Recorrertbl += "<td><center>" + listPrestamo1.getString("p.Fecha_prestamo").toString().trim() + "</center></td>";
                Recorrertbl += "<td><center>" + listPrestamo1.getString("p.Hora_prestamo").toString().trim() + "</center></td>";
                Recorrertbl += "<td><center>" + listPrestamo1.getString("NombrePrestador").toString().trim() + "</center></td>";
                Recorrertbl += "<td><center>" + listPrestamo1.getString("p.Fecha_devolucion").toString().trim() + "</center></td>";
                Recorrertbl += "<td><center>" + listPrestamo1.getString("p.Hora_devolucion").toString().trim() + "</center></td>";
                Recorrertbl += "<td><center>" + listPrestamo1.getString("devolucion").toString().trim() + "</center></td>";
                Recorrertbl += " <td><center><button data-toggle='modal' data-target='#myModal1' onclick ='recibir(" + listPrestamo1.getString("Id_prestamo").toString().trim() + ")' class='btn btn-default glyphicon glyphicon-eye-open'  data-toggle='modal' data-target='#myModal1'></button></center></td>";
                if (listPrestamo1.getString("devolucion").toString().trim().equals("No Asignado")) {
                    Recorrertbl += "<td><center><button  class='btn btn-danger glyphicon glyphicon-share' data-toggle='modal' data-target='#myModal' onclick='mapear.devolucion(" + '\"' + listPrestamo1.getString("NombrePrestador").toString().trim() + '\"' + "," + listPrestamo1.getString("Id_prestamo").toString().trim() + ")' ></button></center></td>";
                } else {
                    Recorrertbl += "<td><center><button  class='btn btn-success  glyphicon glyphicon-check'></button></center></td>";
                }
                Recorrertbl += "</tr>";
            }

        } catch (Exception e) {
            Recorrertbl = "error" + e.getMessage();
        }

        return Recorrertbl;
    }

    public String listarSeriales() {
        String Recorrer_seriales = "";
        ResultSet list_SER = Pres.consultarSeriales(datosPrestamo);
        String colorEstado = "";
        String iconoEstado = "";
        String nomFuncion = "";

        try {

            while (list_SER.next()) {
                Recorrer_seriales += "<tr>";
                Recorrer_seriales += "<td><center>" + list_SER.getString("Seriales").toString().trim() + "</center></td>";
                Recorrer_seriales += "<td><center><button type='button' class='btn btn-primary glyphicon glyphicon-bell' data-toggle='modal' data-target='.bs-example-modal-sm1' onclick='mapear.serialAnom(" + '\"' + list_SER.getString("Seriales").toString().trim() + '\"' + ")' ></button></center></td>";
                Recorrer_seriales += "</tr>";

            }
        } catch (Exception e) {
            Recorrer_seriales = "error" + e.getMessage();
        }

        return Recorrer_seriales;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesionOk = request.getSession();
        if (sesionOk.getAttribute("usuario") != null) {
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */

                String Evento = request.getParameter("Guardar");
                String proceso = request.getParameter("proceso");
                if (Evento != null) {
                    if (Evento.equals("modificar")) {

                        String Fecha_Devolucion = request.getParameter("Fecha");
                        String Hora_Devolucion = request.getParameter("Hora");
                        String id_prestamo = request.getParameter("Id_prestamo");
                        String tipoCuenta = "Devolucion";
                        String estado = "Disponible";

                        int Cuenta = Integer.parseInt(sesionOk.getAttribute("Id_cuenta").toString());
                        datosPrestamo.setFecha_devolucion(Fecha_Devolucion);
                        datosPrestamo.setHora_devolucion(Hora_Devolucion);
                        datosPrestamo.setId_prestamo(Integer.parseInt(id_prestamo));
                        datosPrestamo.setId_cuenta(Cuenta);
                        datosPrestamo.setTipocuenta(tipoCuenta);
                        datosPrestamo.setEstado(estado);

                        boolean objejecutar = false;
                        objejecutar = Pres.registarDevolucion(datosPrestamo);
                        Pres.insertId_cuentaxId_cuenta(datosPrestamo);
                        Pres.estadoSeriales(datosPrestamo);
                        response.sendRedirect("consultarprestamo.jsp");
                    }
                } else if (proceso != null) {
                    if (proceso.equals("listar_ser")) {
                        String id_prestamo = request.getParameter("id_prestamo");
                        datosPrestamo.setId_prestamo(Integer.parseInt(id_prestamo));
                        out.println(listarSeriales());
                    } else if (proceso.equals("agregar_anomalia")) {
                        String Serial = request.getParameter("serial");
                        String Descricion_anomalia = request.getParameter("anomalia").trim();
                        String estAnomalia = "Ver";
                        datosPrestamo.setEstaAnomali(estAnomalia);

                        datosPrestamo.setSerialesUP(Serial);
                        datosPrestamo.setDescricion_anomalia(Descricion_anomalia);
                        boolean objejecutar = false;
                        objejecutar = Pres.registarAnomalia(datosPrestamo);

                    }
                }

            }
        } else {
            response.sendRedirect("index.jsp");
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
        processRequest(request, response);
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
        processRequest(request, response);
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
