/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ConexionBD;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Modelo.Profesores;
import Entidad.entidadProfesores;
import javax.servlet.http.HttpSession;

/**
 *
 * @author gamani
 */
@WebServlet(name = "RegistrarProfesores", urlPatterns = {"/RegistrarProfesores"})
public class RegistrarProfesores extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    Profesores Pro = new Profesores();
    entidadProfesores datosProfesores = new entidadProfesores();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        HttpSession sesionOk = request.getSession();
        if (sesionOk.getAttribute("usuario") != null) {
            response.setContentType("text/html;charset=UTF-8");
            String alert = "";
            try (PrintWriter out = response.getWriter()) {

                String Identificacion = request.getParameter("Identificacion");
                String Nombre = request.getParameter("Nombre");
                String Apellido = request.getParameter("Apellido");
                String Direccion = request.getParameter("Direccion");
                String Telefono = request.getParameter("Telefono");
                String Perfil_Profesional = request.getParameter("Perfil_Profesional");
                String Estado = "Habilitado";

                datosProfesores.setIdentificacion(Identificacion);
                datosProfesores.setNombre(Nombre);
                datosProfesores.setApellido(Apellido);
                datosProfesores.setDireccion(Direccion);
                datosProfesores.setTelefono(Telefono);
                datosProfesores.setPerfil_Profesional(Perfil_Profesional);
                datosProfesores.setEstado(Estado);

                if (Pro.insertProfesores(datosProfesores)) {

                    alert += "<script type=\"text/javascript\">";
                    alert += "alertify.alert(\"Registro Exitoso\");";
                    alert += "</script>";
                    request.setAttribute("alert", alert);
                    getServletConfig().getServletContext().getRequestDispatcher("/registarprofesores.jsp").forward(request, response);
                } else {
                    alert += "<script type=\"text/javascript\">";
                    alert += "alertify.alert(\"Ya Existe\");";
                    alert += "</script>";
                    request.setAttribute("alert", alert);
                    getServletConfig().getServletContext().getRequestDispatcher("/registarprofesores.jsp").forward(request, response);
                }
                //response.sendRedirect("registarprofesores.jsp");
            } catch (Exception ex) {
                alert += "<script type=\"text/javascript\">";
                alert += "alertify.alert(\"Error\");";
                alert += "</script>";
                request.setAttribute("alert", alert);
                getServletConfig().getServletContext().getRequestDispatcher("/registarprofesores.jsp").forward(request, response);

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
            Logger.getLogger(RegistrarProfesores.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RegistrarProfesores.class.getName()).log(Level.SEVERE, null, ex);
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
