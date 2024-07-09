/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Entidad.entidadGrados;
import Modelo.Grados;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 519649
 */
@WebServlet(name = "controladorGrados", urlPatterns = {"/controladorGrados"})
public class controladorGrados extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    entidadGrados datos_grados = new entidadGrados();
    Grados gra = new Grados();

    private static boolean esnumero(String cadena) {
        Integer.parseInt(cadena);
        return true;
    }

    public boolean validargrado(String Grado) {

        boolean correcto = false;

        String[] rrecorregrado = Grado.split("-");
        // for (int i = 0; i < rrecorregrado.length; i++) {}
        try {
            if (esnumero(rrecorregrado[0]) && esnumero(rrecorregrado[1]) && Integer.parseInt(rrecorregrado[0]) <= 12 && Integer.parseInt(rrecorregrado[1]) <= 5) {

                char[] x = rrecorregrado[0].toCharArray();
                char[] y = rrecorregrado[1].toCharArray();

                if (x.length <= 2 && y.length <= 2) {

                    correcto = true;
                } else {
                    correcto = false;
                }
            } else {
                correcto = false;
            }
        } catch (Exception ex) {
            correcto = false;
        }

        return correcto;

    }

    public String listar_grados() throws SQLException {
        ResultSet list_gra = gra.condultarGrados();
        String tbl_gra = "";
        String colorEstado = "";
        String iconoEstado = "";
        String nomFuncion = "";

        try {
            while (list_gra.next()) {
                tbl_gra += "<tr>";
                tbl_gra += "<td Style='display:none'>" + list_gra.getString("Id_grado").toString().trim() + "</td>";
                tbl_gra += "<td><center>" + list_gra.getString("Grado").toString().trim() + "</center></td>";
                tbl_gra += " <td><center><button  class='btn btn-primary glyphicon glyphicon-pencil' data-toggle='modal' data-target='#myModal' onclick = 'mapear.grados(" + list_gra.getString("Id_grado").toString().trim() + "," + '\"' + list_gra.getString("Grado").toString().trim() + '\"' + ")' ></button></center></td>";
                if (list_gra.getString("Estado").toString().trim().equals("Habilitado")) {
                    colorEstado = "success";
                    iconoEstado = "ok-circle";
                    nomFuncion = "Estado_habilitado(" + '"' + list_gra.getString("Id_grado").toString().trim() + '"' + ")";
                } else if (list_gra.getString("Estado").toString().trim().equals("Inhabilitado")) {
                    colorEstado = "danger";
                    iconoEstado = "remove-circle";
                    nomFuncion = "Estado_inhabilitado(" + '"' + list_gra.getString("Id_grado").toString().trim() + '"' + ")";
                }
                tbl_gra += "<td><center><div id='cambio_est'><button  class='btn btn-" + colorEstado + " glyphicon glyphicon-" + iconoEstado + "' onclick ='" + nomFuncion + "'></button></center></div></center></td>";

            }
        } catch (Exception e) {
            tbl_gra = "error" + e.getMessage();
        }
        return tbl_gra;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesionOk = request.getSession();
        if (sesionOk.getAttribute("usuario") != null) {
            response.setContentType("text/html;charset=UTF-8");
            String alert = "";
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                String evento = request.getParameter("Guardar");
                String estado_mod = request.getParameter("estado_mod");
                String recargar = request.getParameter("recargar");

                if (evento != null) {
                    String Grado = request.getParameter("grado");
                    String estado = "Habilitado";
                    datos_grados.setGrado(Grado);
                    datos_grados.setEstado(estado);

                    if (validargrado(Grado)) {

                        if (evento.equals("insertar")) {
                            if (gra.RegistroGrupo(datos_grados)) {
                                alert += "<script type=\"text/javascript\">";
                                alert += "alertify.alert(\"Registro exitoso\");";
                                alert += "</script>";
                                request.setAttribute("alert", alert);
                                getServletConfig().getServletContext().getRequestDispatcher("/registargradoygrupo.jsp").forward(request, response);
                            } else {
                                alert += "<script type=\"text/javascript\">";
                                alert += "alertify.alert(\"Ya existe\");";
                                alert += "</script>";
                                request.setAttribute("alert", alert);
                                getServletConfig().getServletContext().getRequestDispatcher("/registargradoygrupo.jsp").forward(request, response);
                            }
                        } else if (evento.equals("modificar")) {
                            String id_grado = request.getParameter("id_grado");
                            datos_grados.setId_grado(Integer.parseInt(id_grado));
                            gra.modificarGrupoYGrado(datos_grados);
                            alert += "<script type=\"text/javascript\">";
                            alert += "alertify.alert(\"modificacion exitosa\");";
                            alert += "</script>";
                            request.setAttribute("alert", alert);
                            getServletConfig().getServletContext().getRequestDispatcher("/registargradoygrupo.jsp").forward(request, response);

                        }

                    } else {
                        alert += "<script type=\"text/javascript\">";
                        alert += "alertify.alert(\"Formato incorrecto\");";
                        alert += "</script>";
                        request.setAttribute("alert", alert);
                        getServletConfig().getServletContext().getRequestDispatcher("/registargradoygrupo.jsp").forward(request, response);

                    }
                }
                if (estado_mod != null) {
                    String id_grado = request.getParameter("idGrado_mod");
                    datos_grados.setId_grado(Integer.parseInt(id_grado));
                    datos_grados.setEstado(estado_mod);
                    gra.cambiar_estado(datos_grados);

                } else if (recargar != null) {
                    out.println(listar_grados());
                }

            } catch (Exception ex) {

                alert += "<script type=\"text/javascript\">";
                alert += "alertify.alert(\"Ya existe\");";
                alert += "</script>";
                request.setAttribute("alert", alert);
                getServletConfig().getServletContext().getRequestDispatcher("/registargradoygrupo.jsp").forward(request, response);
            }

            response.sendRedirect("registargradoygrupo.jsp");
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
