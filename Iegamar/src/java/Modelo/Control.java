/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.PreparedStatement;
import Entidad.entidadControl;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Control extends ConexionBD {

    private PreparedStatement statement;
    entidadControl datoscontrol = new entidadControl();

    public boolean insertControl(entidadControl datoscontrol) {
        conectarse();
        boolean retornarObj = false;
        String regcontrol = "INSERT INTO control_llegada_tarde (fecha,hora) VALUES (?,?)";

        try {
            Stmp();
            statement = conector.prepareStatement(regcontrol);
            statement.setString(1, datoscontrol.getFecha());
            statement.setString(2, datoscontrol.getHora());

            int cont = statement.executeUpdate();
            if (cont > 0) {
                retornarObj = true;
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return retornarObj;
    }

    public boolean insertIdentificacionPro(entidadControl datoscontrol) {
        conectarse();
        boolean retornarObj = false;
        String regPrestamo = "INSERT INTO tbl_dtlls_controlXempleado(identificacion,id_llegada_tarde,Estado) VALUES (?,?,?);";

        try {
            Stmp();
            statement = conector.prepareStatement(regPrestamo);
            statement.setString(1, datoscontrol.getIdentificacion());
            statement.setInt(2, datoscontrol.getId_control());
            statement.setString(3, datoscontrol.getEstado());

            int cont = statement.executeUpdate();
            if (cont > 0) {
                retornarObj = true;
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return retornarObj;
    }

    public boolean insertIdentificacionest(entidadControl datoscontrol) {
        conectarse();
        boolean retornarObj = false;
        String regPrestamo = "INSERT INTO tbl_dtlls_controlXestudiante(identificacion,id_llegada_tarde,Estado) VALUES (?,?,?)";

        try {
            Stmp();
            statement = conector.prepareStatement(regPrestamo);
            statement.setString(1, datoscontrol.getIdentificacion());
            statement.setInt(2, datoscontrol.getId_control());
            statement.setString(3, datoscontrol.getEstado());

            int cont = statement.executeUpdate();
            if (cont > 0) {
                retornarObj = true;
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return retornarObj;
    }

    public ResultSet consulId_control() {

        ResultSet rsp = null;
        conectarse();

        String consulId_control = "SELECT MAX(Id_llegada_tarde) as Id_control FROM  control_llegada_tarde";
        try {
            consulta = conector.createStatement();
            rsp = consulta.executeQuery(consulId_control);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rsp;
    }

    public ResultSet contEstadoEst(entidadControl datoscontrol) {
        ResultSet rs_conEstado = null;
        conectarse();
        String query = "SELECT COUNT(Estado) as 'Estado' from tbl_dtlls_controlxestudiante where Estado = 'sin verificar' and identificacion =  %s";
        String contEstado = String.format(query, datoscontrol.getIdentificacion());
        try {
            consulta = conector.createStatement();
            rs_conEstado = consulta.executeQuery(contEstado);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return rs_conEstado;

    }

    public ResultSet contEstadoPro(entidadControl datoscontrol) {
        ResultSet rs_conEstado = null;
        conectarse();
        String query = "SELECT COUNT(Estado) as 'Estado' from tbl_dtlls_controlxempleado where Estado = 'sin verificar' and identificacion =  %s";
        String contEstado = String.format(query, datoscontrol.getIdentificacion());
        try {
            consulta = conector.createStatement();
            rs_conEstado = consulta.executeQuery(contEstado);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return rs_conEstado;

    }

    public boolean cambio_estadoEst_control(entidadControl datoscontrol) {
        conectarse();
        boolean retornarObj = false;
        String cambio_est = "update tbl_dtlls_controlxestudiante set Estado = 'Verificado' where identificacion = ? and Estado = 'sin verificar'";
        try {
            Stmp();
            statement = conector.prepareStatement(cambio_est);
            statement.setString(1, datoscontrol.getIdentificacion());

            int cont = statement.executeUpdate();
            if (cont > 0) {
                retornarObj = true;
            }
        } catch (Exception e) {
        }
        return retornarObj;

    }

    public boolean cambio_estadoPro_control(entidadControl datoscontrol) {
        conectarse();
        boolean retornarObj = false;
        String cambio_est = "update tbl_dtlls_controlxempleado set Estado = 'Verificado' where identificacion = ? and Estado = 'sin verificar'";
        try {
            Stmp();
            statement = conector.prepareStatement(cambio_est);
            statement.setString(1, datoscontrol.getIdentificacion());

            int cont = statement.executeUpdate();
            if (cont > 0) {
                retornarObj = true;
            }
        } catch (Exception e) {
        }
        return retornarObj;

    }

    public ResultSet consultarcontrolEst() {
        ResultSet rs_control = null;
        conectarse();
        String consultatarde = "select e.Identificacion as identificacion, concat (e.Nombre , ' ' , e.Apellido) as nombre from  tbl_estudiante e   join tbl_dtlls_controlxestudiante ce on (e.Identificacion = ce.identificacion)\n"
                + "group by e.Identificacion,nombre";
        try {
            consulta = conector.createStatement();
            rs_control = consulta.executeQuery(consultatarde);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return rs_control;

    }
    
    public ResultSet consultarcontrolPro() {
        ResultSet rs_control = null;
        conectarse();
        String consultatarde = "select e.Identificacion as identificacion, concat (e.Nombre , ' ' , e.Apellido) as nombre from tbl_empleado e join tbl_dtlls_controlxempleado ce on (e.Identificacion = ce.identificacion)\n"
                + "group by e.Identificacion,nombre";
        try {
            consulta = conector.createStatement();
            rs_control = consulta.executeQuery(consultatarde);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return rs_control;

    }

    public ResultSet consultarllegadas_tarde_est(entidadControl datoscontrol) {
        ResultSet rs_llegadas = null;
        conectarse();
        String query = "select c.Fecha as Fecha, c.Hora as Hora from tbl_dtlls_controlxestudiante ce join control_llegada_tarde c on (ce.Id_llegada_tarde = c.Id_llegada_tarde) where ce.identificacion = %s";
        String consultaseriales = String.format(query, datoscontrol.getIdentificacion());
        try {
            consulta = conector.createStatement();
            rs_llegadas = consulta.executeQuery(consultaseriales);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return rs_llegadas;

    }
    public ResultSet consultarllegadas_tarde_pro(entidadControl datoscontrol) {
        ResultSet rs_llegadas = null;
        conectarse();
        String query = "select c.Fecha as Fecha, c.Hora as Hora from tbl_dtlls_controlxempleado ce join control_llegada_tarde c on (ce.Id_llegada_tarde = c.Id_llegada_tarde) where ce.identificacion = %s";
        String consultaseriales = String.format(query, datoscontrol.getIdentificacion());
        try {
            consulta = conector.createStatement();
            rs_llegadas = consulta.executeQuery(consultaseriales);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return rs_llegadas;

    }
}
