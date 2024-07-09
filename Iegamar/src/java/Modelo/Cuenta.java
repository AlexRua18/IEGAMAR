/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Entidad.entidadCuenta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Gamani
 */
public class Cuenta extends ConexionBD {

    entidadCuenta ent = new entidadCuenta();
    private PreparedStatement statement;

    public boolean insertIdentificacionEst(entidadCuenta datosCuenta) {
        conectarse();
        boolean retornarObj = false;
        String regPrestamo = "insert into tbl_dtlls_estudiantesxcuentas (Identificacion,Id_cuenta ) values (?,?)";

        try {
            Stmp();
            statement = conector.prepareStatement(regPrestamo);
            statement.setString(1, datosCuenta.getIdentificacion());
            statement.setInt(2, datosCuenta.getId_cuenta());

            int cont = statement.executeUpdate();
            if (cont > 0) {
                retornarObj = true;
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return retornarObj;
    }

    public boolean insertIdentificacionPro(entidadCuenta datosCuenta) {
        conectarse();
        boolean retornarObj = false;
        String regPrestamo = "insert into tbl_dtlls_empleadosxcuentas (Identificacion,Id_cuenta ) values (?,?)";

        try {
            Stmp();
            statement = conector.prepareStatement(regPrestamo);
            statement.setString(1, datosCuenta.getIdentificacion());
            statement.setInt(2, datosCuenta.getId_cuenta());

            int cont = statement.executeUpdate();
            if (cont > 0) {
                retornarObj = true;
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return retornarObj;
    }

    public ResultSet consulCuenta() {

        ResultSet rsp = null;
        conectarse();

        String consulId_prestamo = "SELECT MAX(Id_cuenta) AS Id_cuenta FROM  tbl_cuentas_usuario";
        try {
            consulta = conector.createStatement();
            rsp = consulta.executeQuery(consulId_prestamo);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rsp;
    }

    public boolean insertCuenta(entidadCuenta datosCuenta) {
        conectarse();
        boolean retornarObj = false;
        String regPrestamo = "INSERT INTO tbl_cuentas_usuario (Login,PASSWORD,Tipo_rol,Estado ) VALUES (?,?,?,?)";

        try {
            Stmp();
            statement = conector.prepareStatement(regPrestamo);
            statement.setString(1, datosCuenta.getLogin());
            statement.setString(2, datosCuenta.getPassword());
            statement.setString(3, datosCuenta.getTipo_rol());
            statement.setString(4, datosCuenta.getEstado());

            int cont = statement.executeUpdate();
            if (cont > 0) {
                retornarObj = true;
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return retornarObj;
    }

    public ResultSet consultarCuentaEst() {
        ResultSet rsPrestamo = null;
        conectarse();
        String consultaCuenta = "SELECT cu.Id_cuenta,CONCAT (es.Nombre, ' ' , es.Apellido) AS Estudiante,es.Identificacion,cu.Login ,cu.Estado FROM `tbl_cuentas_usuario` cu \n"
                + "INNER JOIN tbl_dtlls_estudiantesxcuentas esc ON( cu.`Id_cuenta` = esc.`Id_cuenta`) INNER JOIN tbl_estudiante es ON (es.Identificacion = esc.Identificacion)";
        try {
            consulta = conector.createStatement();
            rsPrestamo = consulta.executeQuery(consultaCuenta);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return rsPrestamo;

    }

    public ResultSet consultarCuentaPro() {
        ResultSet rsPrestamo = null;
        conectarse();
        String consultaCuenta = "SELECT cu.Id_cuenta,CONCAT (es.Nombre, ' ' , es.Apellido) AS Empleado,es.Identificacion,cu.Login,cu.Estado FROM `tbl_cuentas_usuario` cu \n"
                + "INNER JOIN tbl_dtlls_empleadosxcuentas esc ON( cu.`Id_cuenta` = esc.`Id_cuenta`) INNER JOIN tbl_empleado es ON (es.Identificacion = esc.Identificacion)";
        try {
            consulta = conector.createStatement();
            rsPrestamo = consulta.executeQuery(consultaCuenta);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return rsPrestamo;

    }

        public boolean modificarCuenta(entidadCuenta datosCuenta) {
        conectarse();
        boolean retornarObj = false;
        String regDevolucion = "UPDATE tbl_cuentas_usuario SET Login = ? , PASSWORD = ?  WHERE Id_cuenta = ?";
        try {
            Stmp();
            statement = conector.prepareStatement(regDevolucion);
            statement.setString(1, datosCuenta.getLogin());
            statement.setString(2, datosCuenta.getPassword());
            statement.setInt(3, datosCuenta.getId_cuenta());

            int cont = statement.executeUpdate();
            if (cont > 0) {
                retornarObj = true;
            }
        } catch (Exception e) {
        }

        return retornarObj;
    }
        
            public boolean cambiar_estado(entidadCuenta datosCuenta )
    {
    conectarse();
        boolean retornarObj = false;
        String cambio_est = "update tbl_cuentas_usuario set  Estado = ? where Id_cuenta = ?";
        try {
             Stmp();
            statement = conector.prepareStatement(cambio_est);
            statement.setString(1, datosCuenta.getEstado());
            statement.setInt(2, datosCuenta.getId_cuenta());
        
     int cont = statement.executeUpdate();
            if (cont > 0) {
                retornarObj = true;
            }
         } catch (Exception e) {
         }

        return retornarObj;
    }
    
}
