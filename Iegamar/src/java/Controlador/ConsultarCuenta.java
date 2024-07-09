/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Entidad.entidadCuenta;
import Modelo.Cuenta;
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

/**
 *
 * @author Gamani
 */
@WebServlet(name = "ConsultarCuenta", urlPatterns = {"/ConsultarCuenta"})
public class ConsultarCuenta extends HttpServlet {

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

    public String listarCuenta() throws SQLException {
        String Recorrertbl = "";
        String colorEstado = "";
        String iconoEstado = "";
        String nomFuncion = "";
        ResultSet listCuentaPro = Cuen.consultarCuentaPro();
        ResultSet listCuentaest = Cuen.consultarCuentaEst();
        try {
            while (listCuentaPro.next()) {
                Recorrertbl += "<tr>";
                Recorrertbl += "<td Style='display:none'><center>" + listCuentaPro.getString("cu.Id_cuenta").toString().trim() + "</center></td>";
                Recorrertbl += "<td><center>" + listCuentaPro.getString("Empleado").toString().trim() + "</center></td>";
                Recorrertbl += "<td><center>" + listCuentaPro.getString("es.Identificacion").toString().trim() + "</center></td>";
                Recorrertbl += "<td><center>" + listCuentaPro.getString("cu.Login").toString().trim() + "</center></td>";
                Recorrertbl += "<td><center><button  class='btn btn-primary glyphicon glyphicon-pencil' data-toggle='modal' data-target='#myModal' onclick='mapear.Cuenta(" + '\"' + listCuentaPro.getString("cu.Id_cuenta").toString().trim() + '\"' + "," + '\"' + listCuentaPro.getString("Empleado").toString().trim() + '\"' + "," + '\"' + listCuentaPro.getString("cu.Login").toString().trim() + '\"' + ")' ></button></center></td>";
                if (listCuentaPro.getString("cu.Estado").toString().trim().equals("Habilitado")) {
                    colorEstado = "success";
                    iconoEstado = "ok-circle";
                    nomFuncion = "Estado_habilitado(" + listCuentaPro.getString("cu.Id_cuenta").toString().trim() + ")";
                } else if (listCuentaPro.getString("cu.Estado").toString().trim().equals("Inhabilitado")) {
                    colorEstado = "danger";
                    iconoEstado = "remove-circle";
                    nomFuncion = "Estado_inhabilitado(" + listCuentaPro.getString("cu.Id_cuenta").toString().trim() + ")";
                }
                Recorrertbl += "<td><center><div id='cambio_est'><button  class='btn btn-" + colorEstado + " glyphicon glyphicon-" + iconoEstado + "' onclick =" + nomFuncion + "></button></center></div></center></td>";
                Recorrertbl += "</tr>";
            }

            while (listCuentaest.next()) {
                Recorrertbl += "<tr>";
                Recorrertbl += "<td Style='display:none'><center>" + listCuentaest.getString("cu.Id_cuenta").toString().trim() + "</center></td>";
                Recorrertbl += "<td><center>" + listCuentaest.getString("Estudiante").toString().trim() + "</center></td>";
                Recorrertbl += "<td><center>" + listCuentaest.getString("es.Identificacion").toString().trim() + "</center></td>";
                Recorrertbl += "<td><center>" + listCuentaest.getString("cu.Login").toString().trim() + "</center></td>";
                Recorrertbl += "<td><center><button  class='btn btn-primary glyphicon glyphicon-pencil' data-toggle='modal' data-target='#myModal' onclick='mapear.Cuenta(" + '\"' + listCuentaest.getString("cu.Id_cuenta").toString().trim() + '\"' + "," + '\"' + listCuentaest.getString("Estudiante").toString().trim() + '\"' + "," + '\"' + listCuentaest.getString("cu.Login").toString().trim() + '\"' + ")' ></button></center></td>";
                if (listCuentaest.getString("cu.Estado").toString().trim().equals("Habilitado")) {
                    colorEstado = "success";
                    iconoEstado = "ok-circle";
                    nomFuncion = "Estado_habilitado(" + listCuentaest.getString("cu.Id_cuenta").toString().trim() + ")";
                } else if (listCuentaest.getString("cu.Estado").toString().trim().equals("Inhabilitado")) {
                    colorEstado = "danger";
                    iconoEstado = "remove-circle";
                    nomFuncion = "Estado_inhabilitado(" + listCuentaest.getString("cu.Id_cuenta").toString().trim() + ")";
                }
                Recorrertbl += "<td><center><div id='cambio_est'><button  class='btn btn-" + colorEstado + " glyphicon glyphicon-" + iconoEstado + "' onclick =" + nomFuncion + "></button></center></div></center></td>";
                Recorrertbl += "</tr>";
            }

        } catch (Exception e) {
            Recorrertbl = "error" + e.getMessage();
        }
        return Recorrertbl;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        HttpSession sesionOk = request.getSession();
    
        if (sesionOk.getAttribute("usuario") != null) {
        String alert="";
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String Evento = request.getParameter("Guardar");
            String estado = request.getParameter("estado_mod");
            String recargar = request.getParameter("recargar");

            if (Evento != null) {
                if (Evento.equals("modificar")) {
                    int Id_cuenta = Integer.parseInt(request.getParameter("Id_cuenta").toString());
                    String Cuenta = request.getParameter("Cuenta");
                    String Contra = request.getParameter("Contra");
                    datosCuenta.setLogin(Cuenta);
                    datosCuenta.setPassword(Contra);
                    datosCuenta.setId_cuenta(Id_cuenta);

                    //boolean objejecutar = false;
                    //objejecutar = Cuen.modificarCuenta(datosCuenta);
                    if (Cuen.modificarCuenta(datosCuenta)) {
                        alert += "<script type=\"text/javascript\">";
                        alert += "alertify.alert(\"Modificación Exitosa\");";
                        alert += "</script>";
                        request.setAttribute("alert", alert);
                        getServletConfig().getServletContext().getRequestDispatcher("/consultarcuentas.jsp").forward(request, response);

                    } else {
                        alert += "<script type=\"text/javascript\">";
                        alert += "alertify.alert(\"Ya Existe\");";
                        alert += "</script>";
                        request.setAttribute("alert", alert);
                        getServletConfig().getServletContext().getRequestDispatcher("/consultarcuentas.jsp").forward(request, response);
                    }
                    //response.sendRedirect("consultarcuentas.jsp");
                }
            } else if (estado != null) {
                int id_cuenta_mod = Integer.parseInt(request.getParameter("idcuenta_mod"));
                datosCuenta.setId_cuenta(id_cuenta_mod);
                datosCuenta.setEstado(estado);
                Cuen.cambiar_estado(datosCuenta);

            } else if (recargar != null) {
                out.println(listarCuenta());
            }

        }
        }
        else {
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
            Logger.getLogger(ConsultarCuenta.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ConsultarCuenta.class.getName()).log(Level.SEVERE, null, ex);
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
