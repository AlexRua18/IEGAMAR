/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Entidad.entidadControl;
import Modelo.Control;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Familia Mazo
 */
@WebServlet(name = "ContControl", urlPatterns = {"/ContControl"})
public class ContControl extends HttpServlet {

    entidadControl datoscontrol = new entidadControl();
    Control cont = new Control();

    public String listarcontrolest() throws SQLException {
        String Recorrertbl = "";
        ResultSet listcontrol = cont.consultarcontrolEst();
        try {
            while (listcontrol.next()) {
                Recorrertbl += "<tr>";
                Recorrertbl += "<td><center>" + listcontrol.getString("identificacion").toString().trim() + "</center></td>";
                Recorrertbl += "<td><center>" + listcontrol.getString("nombre").toString().trim() + "</center></td>";
                Recorrertbl += " <td><center><button  class='btn btn-primary glyphicon glyphicon-pencil' data-toggle='modal' data-target='#myModal1' onclick = 'estTardeest("+ listcontrol.getString("identificacion").toString().trim() +")' ></button></center></td>";
                Recorrertbl += "</tr>";
            }
        } catch (Exception e) {
            Recorrertbl = "error" + e.getMessage();
        }

        return Recorrertbl;
    }
    
    public String listarcontrolpro() throws SQLException {
        String Recorrertbl = "";
        ResultSet listcontrol = cont.consultarcontrolPro();
        try {
            while (listcontrol.next()) {
                Recorrertbl += "<tr>";
                Recorrertbl += "<td><center>" + listcontrol.getString("identificacion").toString().trim() + "</center></td>";
                Recorrertbl += "<td><center>" + listcontrol.getString("nombre").toString().trim() + "</center></td>";
                Recorrertbl += " <td><center><button  class='btn btn-primary glyphicon glyphicon-pencil' data-toggle='modal' data-target='#myModal1' onclick = 'estTardepro("+ listcontrol.getString("identificacion").toString().trim() +")' ></button></center></td>";
                Recorrertbl += "</tr>";
            }
        } catch (Exception e) {
            Recorrertbl = "error" + e.getMessage();
        }

        return Recorrertbl;
    }

    public String listarllegadasest() throws SQLException {
        String Recorrertbl1 = "";
        ResultSet listcontrol1 = cont.consultarllegadas_tarde_est(datoscontrol);
        try {
            while (listcontrol1.next()) {
                Recorrertbl1 += "<tr>";
                Recorrertbl1 += "<td><center>" + listcontrol1.getString("Fecha").toString().trim() + "</center></td>";
                Recorrertbl1 += "<td><center>" + listcontrol1.getString("Hora").toString().trim() + "</center></td>";
                Recorrertbl1 += "</tr>";
            }
        } catch (Exception e) {
            Recorrertbl1 = "error" + e.getMessage();
        }

        return Recorrertbl1;
    }
    public String listarllegadaspro() throws SQLException {
        String Recorrertbl1 = "";
        ResultSet listcontrol1 = cont.consultarllegadas_tarde_pro(datoscontrol);
        try {
            while (listcontrol1.next()) {
                Recorrertbl1 += "<tr>";
                Recorrertbl1 += "<td><center>" + listcontrol1.getString("Fecha").toString().trim() + "</center></td>";
                Recorrertbl1 += "<td><center>" + listcontrol1.getString("Hora").toString().trim() + "</center></td>";
                Recorrertbl1 += "</tr>";
            }
        } catch (Exception e) {
            Recorrertbl1 = "error" + e.getMessage();
        }

        return Recorrertbl1;
    }

    public int traerId_control() throws SQLException {
        int id_control = 0;
        ResultSet Rs = null;

        Rs = cont.consulId_control();

        while (Rs.next()) {
            id_control = Rs.getInt("Id_control") != 0 ? Rs.getInt("Id_control") : 1;
        }

        return id_control;

    }

    public int traerconEstadoEst() throws SQLException {
        int numEstado = 0;
        ResultSet Rs = null;

        Rs = cont.contEstadoEst(datoscontrol);

        while (Rs.next()) {
            numEstado = Rs.getInt("Estado");
        }

        return numEstado;

    }

    public int traerconEstadoPro() throws SQLException {
        int numEstado = 0;
        ResultSet Rs = null;

        Rs = cont.contEstadoPro(datoscontrol);

        while (Rs.next()) {
            numEstado = Rs.getInt("Estado");
        }

        return numEstado;

    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String proceso = request.getParameter("proceso");

            if (proceso.equals("Registrar")) {
                String alert = "";
                String evento = request.getParameter("guardar");

                if (evento.equals("insertar")) {
                    String opcion = request.getParameter("opciones");
                    String identificacion = request.getParameter("identificacion");
                    String fecha = request.getParameter("Fecha");
                    String hora = request.getParameter("Hora");
                    String estado = "sin verificar";
                    datoscontrol.setIdentificacion(identificacion);
                    datoscontrol.setFecha(fecha);
                    datoscontrol.setHora(hora);
                    datoscontrol.setEstado(estado);
                    boolean objejecutar = false;
                    if (cont.insertControl(datoscontrol)) {
                        datoscontrol.setId_control(traerId_control());
                        if (opcion.equals("Profesores")) {
                            objejecutar = cont.insertIdentificacionPro(datoscontrol);
                            if (traerconEstadoPro() >= 3) {
                                alert += "<script type=\"text/javascript\">";
                                alert += "alertify.alert(\"El profesor lleva 3 llegadas tardesen consecutivas\");";
                                alert += "</script>";
                                request.setAttribute("alert", alert);
                                getServletConfig().getServletContext().getRequestDispatcher("/registarcontrol.jsp").forward(request, response);
                                cont.cambio_estadoPro_control(datoscontrol);
                            } else {
                                alert += "<script type=\"text/javascript\">";
                                alert += "alertify.alert(\"Registro exito\");";
                                alert += "</script>";
                                request.setAttribute("alert", alert);
                                getServletConfig().getServletContext().getRequestDispatcher("/registarcontrol.jsp").forward(request, response);
                            }
                        } else if (opcion.equals("Estudiante")) {
                            objejecutar = cont.insertIdentificacionest(datoscontrol);
                            if (traerconEstadoEst() >= 3) {
                                alert += "<script type=\"text/javascript\">";
                                alert += "alertify.alert(\"El estudiante lleva 3 llegadas tardesen consecutivas\");";
                                alert += "</script>";
                                request.setAttribute("alert", alert);
                                getServletConfig().getServletContext().getRequestDispatcher("/registarcontrol.jsp").forward(request, response);
                                cont.cambio_estadoEst_control(datoscontrol);
                            } else {
                                alert += "<script type=\"text/javascript\">";
                                alert += "alertify.alert(\"Registro exito\");";
                                alert += "</script>";
                                request.setAttribute("alert", alert);
                                getServletConfig().getServletContext().getRequestDispatcher("/registarcontrol.jsp").forward(request, response);
                            }
                        }
                    }

                }
            }else if (proceso.equals("listar_tarde_est")) {
                String identificacion = request.getParameter("Identificacion");
                datoscontrol.setIdentificacion(identificacion);
                out.print(listarllegadasest());
                

            }else if (proceso.equals("listar_tarde_pro")) {
                String identificacion = request.getParameter("Identificacion");
                datoscontrol.setIdentificacion(identificacion);
                out.print(listarllegadaspro());
                

            }
          
        }
          response.sendRedirect("registarcontrol.jsp");
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
            Logger.getLogger(ContControl.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ContControl.class.getName()).log(Level.SEVERE, null, ex);
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
