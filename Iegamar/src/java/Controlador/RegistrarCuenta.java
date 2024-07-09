/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Entidad.entidadCuenta;
import Modelo.Cuenta;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Gamani
 */
@WebServlet(name = "RegistrarCuenta", urlPatterns = {"/RegistrarCuenta"})
public class RegistrarCuenta extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    Cuenta Cuen = new Cuenta();
    entidadCuenta datosCuenta = new entidadCuenta();

    public int traerId_Cuenta() throws SQLException {
        int id_Cuenta = 0;
        ResultSet Rs = null;

        Rs = Cuen.consulCuenta();

        while (Rs.next()) {
            id_Cuenta = Rs.getInt("Id_cuenta");
        }

        return id_Cuenta;

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        HttpSession sesionOk = request.getSession();
        if (sesionOk.getAttribute("usuario") != null) {
            response.setContentType("text/html;charset=UTF-8");
            String alert = "";

            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */

                String evento = request.getParameter("guardar");
                if (evento != null) {

                    if (evento.equals("insertar")) {
                        String Nombre = request.getParameter("nombreU");
                        String Contra = request.getParameter("contra");
                        String Tipo = request.getParameter("opciones");
                        String Estado = "Habilitado";
                        String Identificacion = request.getParameter("identificacion");
                        boolean objejecutar = false;
                        datosCuenta.setLogin(Nombre);
                        datosCuenta.setPassword(Contra);
                        datosCuenta.setTipo_rol(Tipo);
                        datosCuenta.setEstado(Estado);

                        if (Cuen.insertCuenta(datosCuenta)) {
                            datosCuenta.setIdentificacion(Identificacion);
                            datosCuenta.setId_cuenta(traerId_Cuenta());

                            if (Tipo.equals("Profesores")) {
                                objejecutar = Cuen.insertIdentificacionPro(datosCuenta);
                            } else if (Tipo.equals("Estudiante")) {
                                objejecutar = Cuen.insertIdentificacionEst(datosCuenta);
                            }
                            alert += "<script type=\"text/javascript\">";
                            alert += "alertify.alert(\"Registro Exitoso\");";
                            alert += "</script>";
                            request.setAttribute("alert", alert);
                            getServletConfig().getServletContext().getRequestDispatcher("/registarcuentas.jsp").forward(request, response);
                        } else {
                            alert += "<script type=\"text/javascript\">";
                            alert += "alertify.alert(\"Ya Existe\");";
                            alert += "</script>";
                            request.setAttribute("alert", alert);
                            getServletConfig().getServletContext().getRequestDispatcher("/registarcuentas.jsp").forward(request, response);
                        }

                    }
                }
                // response.sendRedirect("registarcuentas.jsp");
            } catch (Exception ex) {
                alert += "<script type=\"text/javascript\">";
                alert += "alertify.alert(\"Error\");";
                alert += "</script>";
                request.setAttribute("alert", alert);
                getServletConfig().getServletContext().getRequestDispatcher("/registarcuentas.jsp").forward(request, response);
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
            Logger.getLogger(RegistrarCuenta.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RegistrarCuenta.class.getName()).log(Level.SEVERE, null, ex);
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
