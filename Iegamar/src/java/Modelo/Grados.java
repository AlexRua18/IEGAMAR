/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Entidad.entidadGrados;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author 519649
 */
public class Grados extends ConexionBD {
    entidadGrados ent = new entidadGrados();
    private PreparedStatement statement;
    
    public boolean RegistroGrupo(entidadGrados datos_grado) {
        conectarse();
        boolean retornarObj = false;
        String regGrado = "insert into tbl_grado(Grado,Estado) values(?,?)";

   try {
            Stmp();
            statement = conector.prepareStatement(regGrado);
            statement.setString(1,datos_grado.getGrado());
            statement.setString(2, datos_grado.getEstado());
         

            int cont = statement.executeUpdate();
            if (cont > 0) {
                retornarObj = true;
            }
        } catch (Exception e) {
        }
        return retornarObj;
    }
    public ResultSet condultarGrados(){
    ResultSet rsG = null;
        conectarse();
        String consultaGrados = "select  Id_grado,Grado,Estado from tbl_grado";
        try {
            consulta = conector.createStatement();
            rsG = consulta.executeQuery(consultaGrados);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return rsG;
    
    }
     public boolean modificarGrupoYGrado(entidadGrados datos_grado){
     conectarse();
        boolean retornarObj = false;
        String modEstudiante = "update tbl_grado set Grado = ?  where Id_grado = ?";
 try {
             Stmp();
            statement = conector.prepareStatement(modEstudiante);
            statement.setString(1, datos_grado.getGrado());
            statement.setInt(2, datos_grado.getId_grado());
           
            
            int cont = statement.executeUpdate();
            if (cont > 0) {
                retornarObj = true;
            }
         } catch (Exception e) {
         }

        return retornarObj;
     }
     public boolean cambiar_estado(entidadGrados datos_grado){
         conectarse();
        boolean retornarObj = false;
        String estado_gra = "update tbl_grado set Estado = ?  where Id_grado = ?";
        try {
             Stmp();
            statement = conector.prepareStatement(estado_gra);
              statement.setString(1, datos_grado.getEstado());
            statement.setInt(2, datos_grado.getId_grado());
          
           
            
            int cont = statement.executeUpdate();
            if (cont > 0) {
                retornarObj = true;
            }
         } catch (Exception e) {
         }

        return retornarObj;
     
     }
}
