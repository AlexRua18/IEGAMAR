/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Entidad.entidadProfesores;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author hp
 */
public class Profesores extends ConexionBD {

    entidadProfesores ent = new entidadProfesores();
    private PreparedStatement statement;

    public boolean insertProfesores(entidadProfesores datosprofesores) {
        conectarse();
        boolean retornarObj = false;
        String regProfesores = "insert into tbl_empleado (Identificacion,Nombre,Apellido,Direccion,Telefono,Perfil_profesional,Estado) values (?,?,?,?,?,?,?)";

        try {
            Stmp();
            statement = conector.prepareStatement(regProfesores);
            statement.setString(1, datosprofesores.getIdentificacion());
            statement.setString(2, datosprofesores.getNombre());
            statement.setString(3, datosprofesores.getApellido());
            statement.setString(4, datosprofesores.getDireccion());
            statement.setString(5, datosprofesores.getTelefono());
            statement.setString(6, datosprofesores.getPerfil_Profesional());
            statement.setString(7, datosprofesores.getEstado());

            int cont = statement.executeUpdate();
            if (cont > 0) {
                retornarObj = true;
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return retornarObj;
    }

    public ResultSet consultarProfesores(){
        ResultSet rsp = null;
        conectarse();

        String consultapro = "select  Identificacion,Nombre,Apellido,Direccion,Telefono,Perfil_profesional,Estado from tbl_empleado ";
        try {
            consulta = conector.createStatement();
            rsp = consulta.executeQuery(consultapro);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return rsp;
    }

    public boolean modificarProfesores(entidadProfesores datosProfesores) {  
        conectarse();
        boolean retornarObj = false;
        String modProfesores = "update tbl_empleado set  Nombre = ?,Apellido = ?, Direccion = ?, Telefono = ?, Perfil_profesional = ? where Identificacion =  ?";
        try {
            Stmp();
            statement = conector.prepareStatement(modProfesores);
            statement.setString(1, datosProfesores.getNombre());
            statement.setString(2, datosProfesores.getApellido());
            statement.setString(3, datosProfesores.getDireccion());
            statement.setString(4, datosProfesores.getTelefono());
            statement.setString(5, datosProfesores.getPerfil_Profesional());
            statement.setString(6, datosProfesores.getIdentificacion());
            int cont = statement.executeUpdate();
            if (cont > 0) {
                retornarObj = true;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return retornarObj;
    }
    
        public boolean cambiar_estado(entidadProfesores datosProfesores)
    {
    conectarse();
        boolean retornarObj = false;
        String cambio_est = "update tbl_empleado set  Estado = ? where Identificacion = ?";
        try {
             Stmp();
            statement = conector.prepareStatement(cambio_est);
            statement.setString(1, datosProfesores.getEstado());
            statement.setString(2, datosProfesores.getIdentificacion());
        
     int cont = statement.executeUpdate();
            if (cont > 0) {
                retornarObj = true;
            }
         } catch (Exception e) {
         }

        return retornarObj;
    }
    
}
