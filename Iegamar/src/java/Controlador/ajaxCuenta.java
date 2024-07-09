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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Gamani
 */
@WebServlet(name = "ajaxCuenta", urlPatterns = {"/ajaxCuenta"})
public class ajaxCuenta extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
     Prestamo Pres = new Prestamo();
    entidadPrestamo datosPrestamo = new entidadPrestamo();

    public String Datos(String Radio) throws SQLException {
        String Recorrer = "";
        ResultSet Rs = null;

        if (Radio.equals("Profesores")) {

            Recorrer += "<select name=\"identificacion\"  id=\"ConProEsT\">";
            Rs = Pres.traerProfesor();
            while (Rs.next()) {
                Recorrer += "<option  Value='" + Rs.getString("Identificacion") + "' >" + Rs.getString("nombre_completo") + "</option>";
            }
            Recorrer += "</select>";

        } else if (Radio.equals("Estudiante")) {
            Recorrer += "<select name=\"identificacion\" id=\"ConProEsT\">";
            Rs = Pres.traerEstudiante();
            while (Rs.next()) {
                Recorrer += "<option Value='" + Rs.getString("Identificacion") + "' >" + Rs.getString("nombre_completo") + "</option>";
            }
            Recorrer += "</select>";
        }

        return Recorrer;

    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           
            String radio = request.getParameter("opciones");
            if (radio != null) {
                out.println(Datos(radio));

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
             Logger.getLogger(ajaxCuenta.class.getName()).log(Level.SEVERE, null, ex);
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
             Logger.getLogger(ajaxCuenta.class.getName()).log(Level.SEVERE, null, ex);
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
