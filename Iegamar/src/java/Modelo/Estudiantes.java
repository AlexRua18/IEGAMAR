/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Entidad.entidadEstudiantes;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Gamani Sai
 */
public class Estudiantes extends ConexionBD {

    entidadEstudiantes ent = new entidadEstudiantes();
    private PreparedStatement statement;


     public ResultSet traerGrado(){
        ResultSet rsp = null;
        conectarse();

        String traergrado = "select Id_grado,Grado from tbl_grado where Estado = 'Habilitado'";
        try {
            consulta = conector.createStatement();
            rsp = consulta.executeQuery(traergrado);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return rsp;
    }
    
    public boolean inserEstudiantes(entidadEstudiantes datosestudiantes) {
        conectarse();
        boolean retornarObj = false;
        String regEstudiante = "insert into tbl_estudiante (Identificacion,Nombre,Apellido,Direccion,Telefono,Id_grado,Estado) values (?,?,?,?,?,?,?)";

        try {
            Stmp();
            statement = conector.prepareStatement(regEstudiante);
            statement.setString(1, datosestudiantes.getIdentificacion());
            statement.setString(2, datosestudiantes.getNombre());
            statement.setString(3, datosestudiantes.getApellido());
            statement.setString(4, datosestudiantes.getDireccion());
            statement.setString(5, datosestudiantes.getTelefono());
            statement.setString(6, datosestudiantes.getId_grado());
            statement.setString(7, datosestudiantes.getEstado());

            int cont = statement.executeUpdate();
            if (cont > 0) {
                retornarObj = true;
            }
        } catch (Exception e) {
        }
        return retornarObj;
    }

    public ResultSet consultarEstudiante()  {
        ResultSet rsp = null;
        conectarse();
        String consultaestudiante = "select e.Identificacion,e.Nombre,e.Apellido,e.Direccion,e.Telefono,g.Grado,g.Id_Grado,e.Estado  from tbl_estudiante e join tbl_grado g\n"
                + "on (g.Id_grado = e.Id_grado)";
        try {
            consulta = conector.createStatement();
            rsp = consulta.executeQuery(consultaestudiante);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return rsp;
    }
    
     public boolean modificarEstudiante(entidadEstudiantes datosestudiantes)  {
         conectarse();
        boolean retornarObj = false;
        String modEstudiante = "update tbl_estudiante set  Nombre = ?,Apellido = ?, Direccion = ?, Telefono = ?,  Id_grado = ? where Identificacion = ?";
         try {
             Stmp();
            statement = conector.prepareStatement(modEstudiante);
            statement.setString(1, datosestudiantes.getNombre());
            statement.setString(2, datosestudiantes.getApellido());
            statement.setString(3, datosestudiantes.getDireccion());
            statement.setString(4, datosestudiantes.getTelefono());
            statement.setString(5, datosestudiantes.getId_grado());
            statement.setString(6, datosestudiantes.getIdentificacion());
            
            int cont = statement.executeUpdate();
            if (cont > 0) {
                retornarObj = true;
            }
         } catch (Exception e) {
         }

        return retornarObj;
    }
    public boolean cambiar_estado(entidadEstudiantes datosestudiantes)
    {
    conectarse();
        boolean retornarObj = false;
        String cambio_est = "update tbl_estudiante set  Estado = ? where Identificacion = ?";
        try {
             Stmp();
            statement = conector.prepareStatement(cambio_est);
            statement.setString(1, datosestudiantes.getEstado());
            statement.setString(2, datosestudiantes.getIdentificacion());
        
     int cont = statement.executeUpdate();
            if (cont > 0) {
                retornarObj = true;
            }
         } catch (Exception e) {
         }

        return retornarObj;
    }
    
}
