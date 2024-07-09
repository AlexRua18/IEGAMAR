/*
 * To change this template, choose Tools | Templates
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
import javax.servlet.http.HttpSession;
import Entidad.entidadUsuario;
import Modelo.Usuario;

/**
 *
 * @author Jader
 */
public class ValidarUsuario extends HttpServlet {

    entidadUsuario datosUsuario = new entidadUsuario();
    Usuario Usu = new Usuario();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String alert = "";

            //Declaramos las dos variables de  usuario y contra
            String tipo = "";
            String usuario = "";
            String contra = "";
            String id = "";
            String cuenta = "";

            //validamos que el usuario haya ingresado la información
            if (request.getParameter("usuario") != null) {
                usuario = request.getParameter("usuario");
            }
            if (request.getParameter("Contra") != null) {
                contra = request.getParameter("Contra");
            }

            datosUsuario.setLogin(usuario);
            datosUsuario.setPassword(contra);

            ResultSet rs = Usu.validar(datosUsuario);
            ResultSet rsidcuenta = Usu.Ingreso(datosUsuario);

            //Verificamos que el usuario corresponda a uno registrado
            while (rs.next()) {

                tipo = rs.getString("Tipo_rol").toString().trim();
                id = rs.getString(1).toString().trim();

                HttpSession sesionOk = request.getSession();
                sesionOk.setAttribute("usuario", usuario);
                sesionOk.setAttribute("Id_cuenta", id);
                sesionOk.setAttribute("Rol", tipo);
            }

            while (rsidcuenta.next()) {
                cuenta = rsidcuenta.getString("Id_cuenta").toString();

            }

            if (cuenta.equals("0")) {
                response.sendRedirect("Menu.jsp");
            } else if (tipo.equals("Super")) {
                response.sendRedirect("consultarcuentas.jsp");
            } else if (tipo.equals("Estudiante")) {
                response.sendRedirect("consultarprestamo.jsp");
            } else if (tipo.equals("Profesores")) {
                response.sendRedirect("registarcuentas.jsp");
            } else if (tipo.equals("Control")) {
                response.sendRedirect("registargradoygrupo.jsp");
            } else {
                alert += "<script type=\"text/javascript\">";
                alert += "alertify.alert(\"Nombre de usuario y/o contraseña incorrectos\");";
                alert += "</script>";
                request.setAttribute("alert", alert);
                getServletConfig().getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            }

        } catch (SQLException errsql) {
            out.println("Error SQL " + errsql.getMessage());
        } finally {
            out.close();
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
