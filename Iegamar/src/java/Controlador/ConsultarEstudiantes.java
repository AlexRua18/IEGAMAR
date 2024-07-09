/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ConexionBD;
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
import Modelo.Estudiantes;
import Entidad.entidadEstudiantes;
import javax.servlet.http.HttpSession;

/**
 *
 * @author gamani
 */
@WebServlet(name = "ConsultarEstudiantes", urlPatterns = {"/ConsultarEstudiantes"})
public class ConsultarEstudiantes extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    Estudiantes Est = new Estudiantes();
    entidadEstudiantes datosEstudiante = new entidadEstudiantes();

    public String Traer() throws SQLException {
        ResultSet Rs = null;
        String Recorrer = "";
        Rs = Est.traerGrado();

        while (Rs.next()) {

            Recorrer += "<option Value='" + Rs.getString("Id_grado") + "' >" + Rs.getString("Grado") + "</option>";

        }
        return Recorrer;
    }

    public String listarEst() throws SQLException {
        ResultSet list_est = null;
        String tbl_est = "";
        String colorEstado = "";
        String iconoEstado = "";
        String nomFuncion = "";

        try {
            list_est = Est.consultarEstudiante();
            while (list_est.next()) {
                tbl_est += "<tr>";

                tbl_est += "<td><center>" + list_est.getString("e.Identificacion").toString().trim() + "</center></td>";
                tbl_est += "<td><center>" + list_est.getString("e.Nombre").toString().trim() + "</center></td>";
                tbl_est += "<td><center>" + list_est.getString("e.Apellido").toString().trim() + "</center></td>";
                tbl_est += "<td><center>" + list_est.getString("e.Direccion").toString().trim() + "</center></td>";
                tbl_est += "<td><center>" + list_est.getString("e.Telefono").toString().trim() + "</center></td>";
                tbl_est += "<td><center>" + list_est.getString("g.Grado").toString().trim() + "</center></td>";
                tbl_est += " <td><center><button  class='btn btn-primary glyphicon glyphicon-pencil' data-toggle='modal' data-target='#myModal' onclick = 'mapear.estudiantes(" + '\"' + list_est.getString("e.Identificacion").toString().trim() + '\"' + "," + '\"' + list_est.getString("e.Nombre").toString().trim() + '\"' + "," + '\"' + list_est.getString("e.Apellido").toString().trim() + '\"' + "," + '\"' + list_est.getString("e.Direccion").toString().trim() + '\"' + "," + '\"' + list_est.getString("e.Telefono").toString().trim() + '\"' + "," + '\"' + list_est.getString("g.Id_Grado").toString().trim() + '\"' + ")'></button></center></td>";
                if (list_est.getString("e.Estado").toString().trim().equals("Habilitado")) {
                    colorEstado = "success";
                    iconoEstado = "ok-circle";
                    nomFuncion = "Estado_habilitado(" + '"' + list_est.getString("e.Identificacion").toString().trim() + '"' + ")";
                } else if (list_est.getString("e.Estado").toString().trim().equals("Inhabilitado")) {
                    colorEstado = "danger";
                    iconoEstado = "remove-circle";
                    nomFuncion = "Estado_inhabilitado(" + '"' + list_est.getString("e.Identificacion").toString().trim() + '"' + ")";
                }
                tbl_est += "<td><center><div id='cambio_est'><button  class='btn btn-" + colorEstado + " glyphicon glyphicon-" + iconoEstado + "' onclick ='" + nomFuncion + "'></button></center></div></center></td>";
            }
        } catch (Exception e) {
            tbl_est = "error" + e.getMessage();
        }
        return tbl_est;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        HttpSession sesionOk = request.getSession();

        if (sesionOk.getAttribute("usuario") != null) {
            response.setContentType("text/html;charset=UTF-8");
            String alert = "";
            try (PrintWriter out = response.getWriter()) {

                String evento = request.getParameter("Guardar");
                String estado = request.getParameter("estado_mod");
                String recargar = request.getParameter("recargar");
                if (evento != null) {
                    if (evento.equals("modificar")) {
                        String Identificacion = request.getParameter("identificacion");
                        String Nombre = request.getParameter("nombre");
                        String Apellido = request.getParameter("apellido");
                        String Direccion = request.getParameter("direccion");
                        String Telefono = request.getParameter("telefono");
                        String Grado = request.getParameter("grado");

                        datosEstudiante.setIdentificacion(Identificacion);
                        datosEstudiante.setNombre(Nombre);
                        datosEstudiante.setApellido(Apellido);
                        datosEstudiante.setDireccion(Direccion);
                        datosEstudiante.setTelefono(Telefono);
                        datosEstudiante.setId_grado(Grado);

                        if (Est.modificarEstudiante(datosEstudiante)) {
                            alert += "<script type=\"text/javascript\">";
                            alert += "alertify.alert(\"Modificación Exitosa\");";
                            alert += "</script>";
                            request.setAttribute("alert", alert);
                            getServletConfig().getServletContext().getRequestDispatcher("/consultarestudiante.jsp").forward(request, response);
                        } else {
                            alert += "<script type=\"text/javascript\">";
                            alert += "alertify.alert(\"Ya Existe\");";
                            alert += "</script>";
                            request.setAttribute("alert", alert);
                            getServletConfig().getServletContext().getRequestDispatcher("/consultarestudiante.jsp").forward(request, response);
                        }
                    }
                } else if (estado != null) {
                    String Identificacion = request.getParameter("identificacion_mod");
                    datosEstudiante.setIdentificacion(Identificacion);
                    datosEstudiante.setEstado(estado);
                    Est.cambiar_estado(datosEstudiante);

                } else if (recargar != null) {
                    out.println(listarEst());
                }

            } catch (Exception ex) {
                alert += "<script type=\"text/javascript\">";
                alert += "alertify.alert(\"Error\");";
                alert += "</script>";
                request.setAttribute("alert", alert);
                getServletConfig().getServletContext().getRequestDispatcher("/consultarestudiante.jsp").forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ConsultarEstudiantes.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ConsultarEstudiantes.class.getName()).log(Level.SEVERE, null, ex);
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
