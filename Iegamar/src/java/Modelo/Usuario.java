/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Entidad.entidadCuenta;
import java.sql.ResultSet;
import Entidad.entidadUsuario;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Gamani
 */
public class Usuario extends ConexionBD {

    entidadUsuario ent = new entidadUsuario();
    private PreparedStatement estament;
    private PreparedStatement statement;

    public ResultSet validar(entidadUsuario datosUsuario) {
        conectarse();
        ResultSet rs = null;
        String consulta_tipo = "select  Id_cuenta ,Tipo_rol from tbl_cuentas_usuario where Login= ? and Password= ? and Estado='Habilitado'";

        try {
            Stmp();

            estament = conector.prepareStatement(consulta_tipo);
            estament.setString(1, datosUsuario.getLogin());
            estament.setString(2, datosUsuario.getPassword());
            rs = estament.executeQuery();

        } catch (Exception er) {

        }
        return rs;
    }

    public ResultSet Ingreso(entidadUsuario datosUsuario) {
        conectarse();
        ResultSet rs = null;
        String consulta_tipo = "select  Id_cuenta from tbl_cuentas_usuario ";

        try {
            consulta = conector.createStatement();
            rs = consulta.executeQuery(consulta_tipo);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rs;
    }

      public boolean modificarCuentaAdmin(entidadCuenta datosCuenta) {
        conectarse();
        boolean retornarObj = false;
        String regDevolucion = "UPDATE tbl_cuentas_usuario SET Login = ? , PASSWORD = ?  ,Id_cuenta = '1' where Id_cuenta = '0'";
        try {
            Stmp();
            statement = conector.prepareStatement(regDevolucion);
            statement.setString(1, datosCuenta.getLogin());
            statement.setString(2, datosCuenta.getPassword());

            int cont = statement.executeUpdate();
            if (cont > 0) {
                retornarObj = true;
            }
        } catch (Exception e) {
        }

        return retornarObj;
    }

}
