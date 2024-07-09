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
 * @author 519649
 */
@WebServlet(name = "RegistrarEstudiante", urlPatterns = {"/RegistrarEstudiante"})
public class RegistrarEstudiante extends HttpServlet {

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
    entidadEstudiantes datosEstudiantes = new entidadEstudiantes();

    public String Traer() throws SQLException {
        ResultSet Rs = null;
        String Recorrer = "";
        Rs = Est.traerGrado();

        while (Rs.next()) {

            Recorrer += "<option Value='" + Rs.getString("Id_grado") + "' >" + Rs.getString("Grado") + "</option>";

        }
        return Recorrer;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        HttpSession sesionOk = request.getSession();
        if (sesionOk.getAttribute("usuario") != null) {
            response.setContentType("text/html;charset=UTF-8");
            String alert = "";
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                String Identificacion = request.getParameter("identificacion");
                String Nombre = request.getParameter("nombre");
                String Apellido = request.getParameter("apellido");
                String Direccion = request.getParameter("direccion");
                String Telefono = request.getParameter("Telefono");
                String Fecha = request.getParameter("Fecha_Nacimiento");
                String id_grado = request.getParameter("grado");
                String Estado = "Habilitado";
                datosEstudiantes.setIdentificacion(Identificacion);
                datosEstudiantes.setNombre(Nombre);
                datosEstudiantes.setApellido(Apellido);
                datosEstudiantes.setDireccion(Direccion);
                datosEstudiantes.setTelefono(Telefono);
                datosEstudiantes.setFecha(Fecha);
                datosEstudiantes.setId_grado(id_grado);
                datosEstudiantes.setEstado(Estado);

            //boolean objejecutar = false;
                //   objejecutar = Est.inserEstudiantes(datosEstudiantes);
                if (Est.inserEstudiantes(datosEstudiantes)) {
                    alert += "<script type=\"text/javascript\">";
                    alert += "alertify.alert(\"Registro Exitoso\");";
                    alert += "</script>";
                    request.setAttribute("alert", alert);
                    getServletConfig().getServletContext().getRequestDispatcher("/registarestudiante.jsp").forward(request, response);

                } else {
                    alert += "<script type=\"text/javascript\">";
                    alert += "alertify.alert(\"Ya Existe\");";
                    alert += "</script>";
                    request.setAttribute("alert", alert);
                    getServletConfig().getServletContext().getRequestDispatcher("/registarestudiante.jsp").forward(request, response);
                }
                // response.sendRedirect("registarestudiante.jsp");
            } catch (Exception ex) {
                alert += "<script type=\"text/javascript\">";
                alert += "alertify.alert(\"Error\");";
                alert += "</script>";
                request.setAttribute("alert", alert);
                getServletConfig().getServletContext().getRequestDispatcher("/registarestudiante.jsp").forward(request, response);
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
            Logger.getLogger(RegistrarEstudiante.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RegistrarEstudiante.class.getName()).log(Level.SEVERE, null, ex);
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
