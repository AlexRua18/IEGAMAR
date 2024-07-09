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
import Entidad.entidadProfesores;
import Modelo.Profesores;
import javax.servlet.http.HttpSession;

/**
 *
 * @author gamani
 */
@WebServlet(name = "ConsultarProfesores", urlPatterns = {"/ConsultarProfesores"})
public class ConsultarProfesores extends HttpServlet {

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

    public String listar() throws SQLException {

        String tbl = "";
        ResultSet list = null;
        String colorEstado = "";
        String iconoEstado = "";
        String nomFuncion = "";
        try {
            list = Pro.consultarProfesores();
            while (list.next()) {
                tbl += "<tr>";
                tbl += "<td><center>" + list.getString("Identificacion").toString().trim() + "</center></td>";
                tbl += "<td><center>" + list.getString("Nombre").toString().trim() + "</center></td>";
                tbl += "<td><center>" + list.getString("Apellido").toString().trim() + "</center></td>";
                tbl += "<td><center>" + list.getString("Direccion").toString().trim() + "</center></td>";
                tbl += "<td><center>" + list.getString("Telefono").toString().trim() + "</center></td>";
                tbl += "<td><center>" + list.getString("Perfil_profesional").toString().trim() + "</center></td>";
                tbl += " <td><center><button  class='btn btn-primary glyphicon glyphicon-pencil' data-toggle='modal' data-target='#myModal' onclick = 'mapear.profesores(" + '\"' + list.getString("Identificacion").toString().trim() + '\"' + "," + '\"' + list.getString("Nombre").toString().trim() + '\"'
                        + "," + '\"' + list.getString("Apellido").toString().trim() + '\"' + "," + '\"' + list.getString("Direccion").toString().trim() + '\"' + "," + '\"' + list.getString("Telefono").toString().trim() + '\"'
                        + "," + '\"' + list.getString("Perfil_profesional").toString().trim() + '\"' + ")' ></button></center></td>";
                if (list.getString("Estado").toString().trim().equals("Habilitado")) {
                    colorEstado = "success";
                    iconoEstado = "ok-circle";
                    nomFuncion = "Estado_habilitado(" + '"' + list.getString("Identificacion").toString().trim() + '"' + ")";
                } else if (list.getString("Estado").toString().trim().equals("Inhabilitado")) {
                    colorEstado = "danger";
                    iconoEstado = "remove-circle";
                    nomFuncion = "Estado_inhabilitado(" + '"' + list.getString("Identificacion").toString().trim() + '"' + ")";
                }
                tbl += "<td><center><div id='cambio_est'><button  class='btn btn-" + colorEstado + " glyphicon glyphicon-" + iconoEstado + "' onclick ='" + nomFuncion + "'></button></center></div></center></td>";
                tbl += "</tr>";
            }
        } catch (Exception e) {
            tbl = "error" + e.getMessage();
        }
        return tbl;
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

                        String Identificacion = request.getParameter("Identificacion");
                        String Nombre = request.getParameter("Nombre");
                        String Apellido = request.getParameter("Apellido");
                        String Direccion = request.getParameter("Direccion");
                        String Telefono = request.getParameter("Telefono");
                        String Perfil_Profesional = request.getParameter("Perfil_Profesional");

                        datosProfesores.setIdentificacion(Identificacion);
                        datosProfesores.setNombre(Nombre);
                        datosProfesores.setApellido(Apellido);
                        datosProfesores.setDireccion(Direccion);
                        datosProfesores.setTelefono(Telefono);
                        datosProfesores.setPerfil_Profesional(Perfil_Profesional);

                        if (Pro.modificarProfesores(datosProfesores)) {

                            alert += "<script type=\"text/javascript\">";
                            alert += "alertify.alert(\"Modificado con exitoso\");";
                            alert += "</script>";
                            request.setAttribute("alert", alert);
                            getServletConfig().getServletContext().getRequestDispatcher("/consultarprofesores.jsp").forward(request, response);
                        } else {
                            alert += "<script type=\"text/javascript\">";
                            alert += "alertify.alert(\"Ya Existe\");";
                            alert += "</script>";
                            request.setAttribute("alert", alert);
                            getServletConfig().getServletContext().getRequestDispatcher("/consultarprofesores.jsp").forward(request, response);
                        }
                        //response.sendRedirect("consultarprofesores.jsp");
                    }
                } else if (estado != null) {
                    String Identificacion = request.getParameter("identificacion_mod");
                    datosProfesores.setIdentificacion(Identificacion);
                    datosProfesores.setEstado(estado);
                    Pro.cambiar_estado(datosProfesores);

                } else if (recargar != null) {
                    out.println(listar());
                }
            } catch (Exception ex) {
                alert += "<script type=\"text/javascript\">";
                alert += "alertify.alert(\"Error\");";
                alert += "</script>";
                request.setAttribute("alert", alert);
                getServletConfig().getServletContext().getRequestDispatcher("/consultarprofesores.jsp").forward(request, response);
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
            Logger.getLogger(ConsultarProfesores.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ConsultarProfesores.class.getName()).log(Level.SEVERE, null, ex);
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
