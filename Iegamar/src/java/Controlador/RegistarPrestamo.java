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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Modelo.Prestamo;
import Entidad.entidadPrestamo;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Gamani Sai
 */
@WebServlet(name = "RegistarPrestamo", urlPatterns = {"/RegistarPrestamo"})
public class RegistarPrestamo extends HttpServlet {

    Prestamo Pres = new Prestamo();
    entidadPrestamo datosPrestamo = new entidadPrestamo();

    public ArrayList llenararray(String seriales) {

        String[] rrecorrercadena = seriales.split("-");
        ArrayList listar = new ArrayList();
        for (int i = 0; i < rrecorrercadena.length; i++) {
            listar.add(rrecorrercadena[i]);
        }
        return listar;
    }

    public int traerId_prestamo() throws SQLException {
        int id_prestamo = 0;
        ResultSet Rs = null;

        Rs = Pres.consulId_prestamo();

        while (Rs.next()) {
            id_prestamo = Rs.getInt("Id_prestamo") != 0 ? Rs.getInt("Id_prestamo") : 1;
        }

        return id_prestamo;

    }

    public String Seriales() throws SQLException {
        String Recorrer = "";
        ResultSet Rs = null;

        Rs = Pres.traerSeriales();
        while (Rs.next()) {
            Recorrer += "<option Value='" + Rs.getString("Seriales") + "' >" + Rs.getString("Seriales") + "</option>";
        }

        return Recorrer;

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        HttpSession sesionOk = request.getSession();
        if (sesionOk.getAttribute("usuario") != null) {
            String alert = "";
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {

                String evento = request.getParameter("guardar");

                if (evento != null) {
                    if (evento.equals("insertar")) {
                        String seriales = request.getParameter("serial");
                        ArrayList arraySeri = llenararray(seriales);
                        String estado = "Prestamo";
                        String Identificacion = request.getParameter("identificacion");
                        String Fecha_prestamo = request.getParameter("Fecha");
                        String Hora_prestamo = request.getParameter("Hora");
                        String Fecha_devolucion = "No Asinado";
                        String Hora_Devolucion = "No Asinado";
                        String Tipo = request.getParameter("opciones");
                        String tipoCuenta = "Prestador";

                        int Cuenta = Integer.parseInt(sesionOk.getAttribute("Id_cuenta").toString());

                        datosPrestamo.setSeriales(arraySeri);
                        datosPrestamo.setEstado(estado);
                        datosPrestamo.setIdentificacion(Identificacion);
                        datosPrestamo.setFecha_prestamo(Fecha_prestamo);
                        datosPrestamo.setHora_prestamo(Hora_prestamo);
                        datosPrestamo.setTipo(Tipo);
                        datosPrestamo.setId_cuenta(Cuenta);
                        datosPrestamo.setTipocuenta(tipoCuenta);
                        datosPrestamo.setFecha_devolucion(Fecha_devolucion);
                        datosPrestamo.setHora_devolucion(Hora_Devolucion);

                        boolean objejecutar = false;

                        if (seriales.equals("")) {
                            alert += "<script type=\"text/javascript\">";
                            alert += "alertify.alert(\"No a ingresado seriales\");";
                            alert += "</script>";
                            request.setAttribute("alert", alert);
                            getServletConfig().getServletContext().getRequestDispatcher("/registarprestamo.jsp").forward(request, response);

                        } else {
                            if (Pres.insertPrestamo(datosPrestamo)) {
                                //ele.registrarElemento(datos_elemento);

                                alert += "<script type=\"text/javascript\">";
                                alert += "alertify.alert(\"Registro Exitoso\");";
                                alert += "</script>";
                                request.setAttribute("alert", alert);
                                getServletConfig().getServletContext().getRequestDispatcher("/registarprestamo.jsp").forward(request, response);

                                datosPrestamo.setId_prestamo(traerId_prestamo());
                                if (Tipo.equals("Profesores")) {
                                    objejecutar = Pres.insertIdentificacionPro(datosPrestamo);
                                } else if (Tipo.equals("Estudiante")) {
                                    objejecutar = Pres.insertIdentificacionEst(datosPrestamo);
                                }
                                Pres.insertId_cuentaxId_cuenta(datosPrestamo);
                                Pres.registrarSeriales(datosPrestamo);
                                Pres.cambiarestadoSeriales(datosPrestamo);
                            } else {
                                alert += "<script type=\"text/javascript\">";
                                alert += "alertify.alert(\"Error\");";
                                alert += "</script>";
                                request.setAttribute("alert", alert);
                                getServletConfig().getServletContext().getRequestDispatcher("/registarprestamo.jsp").forward(request, response);
                            }
                        }
                    } else   if (evento.equals("insertar_rev")) {
                        String seriales = request.getParameter("serial");
                        ArrayList arraySeri = llenararray(seriales);
                        String estado = "Prestamo";
                        String Identificacion = request.getParameter("identificacion");
                        String Fecha_prestamo = request.getParameter("Fecha");
                        String Hora_prestamo = request.getParameter("Hora");
                        String Fecha_devolucion = "No Asinado";
                        String Hora_Devolucion = "No Asinado";
                        String Tipo = "Profesores";
                        String tipoCuenta = "Prestador";

                        int Cuenta = Integer.parseInt(sesionOk.getAttribute("Id_cuenta").toString());

                        datosPrestamo.setSeriales(arraySeri);
                        datosPrestamo.setEstado(estado);
                        datosPrestamo.setIdentificacion(Identificacion);
                        datosPrestamo.setFecha_prestamo(Fecha_prestamo);
                        datosPrestamo.setHora_prestamo(Hora_prestamo);
                        datosPrestamo.setTipo(Tipo);
                        datosPrestamo.setId_cuenta(Cuenta);
                        datosPrestamo.setTipocuenta(tipoCuenta);
                        datosPrestamo.setFecha_devolucion(Fecha_devolucion);
                        datosPrestamo.setHora_devolucion(Hora_Devolucion);

                        boolean objejecutar = false;

                        if (seriales.equals("")) {
                            alert += "<script type=\"text/javascript\">";
                            alert += "alertify.alert(\"No a ingresado seriales\");";
                            alert += "</script>";
                            request.setAttribute("alert", alert);
                            getServletConfig().getServletContext().getRequestDispatcher("/consultarreserva.jsp").forward(request, response);

                        } else {
                            if (Pres.insertPrestamo(datosPrestamo)) {
                                //ele.registrarElemento(datos_elemento);

                                alert += "<script type=\"text/javascript\">";
                                alert += "alertify.alert(\"Registro Exitoso\");";
                                alert += "</script>";
                                request.setAttribute("alert", alert);
                                getServletConfig().getServletContext().getRequestDispatcher("/consultarreserva.jsp").forward(request, response);

                                datosPrestamo.setId_prestamo(traerId_prestamo());
                                if (Tipo.equals("Profesores")) {
                                    objejecutar = Pres.insertIdentificacionPro(datosPrestamo);
                                } 
                                Pres.insertId_cuentaxId_cuenta(datosPrestamo);
                                Pres.registrarSeriales(datosPrestamo);
                                Pres.cambiarestadoSeriales(datosPrestamo);
                            } else {
                                alert += "<script type=\"text/javascript\">";
                                alert += "alertify.alert(\"Error\");";
                                alert += "</script>";
                                request.setAttribute("alert", alert);
                                getServletConfig().getServletContext().getRequestDispatcher("/consultarreserva.jsp").forward(request, response);
                            }
                        }
                    }
                }
                response.sendRedirect("registarprestamo.jsp");
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
            Logger.getLogger(RegistarPrestamo.class
                    .getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RegistarPrestamo.class
                    .getName()).log(Level.SEVERE, null, ex);
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
