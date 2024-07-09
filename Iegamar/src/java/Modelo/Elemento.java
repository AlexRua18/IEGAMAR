/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Entidad.entidadElemento;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author 519649
 */
public class Elemento extends ConexionBD{
    entidadElemento ent = new entidadElemento();
    private PreparedStatement statement;
    
    public boolean registrarElemento(entidadElemento datos_elementos){
   conectarse();
        boolean retornarObj = false;
        String reg_elemento = "insert into tbl_elementos(Nombre_elemento,Descripcion) values(?,?)";
 try {
            Stmp();
            statement = conector.prepareStatement(reg_elemento);
            statement.setString(1,datos_elementos.getNombreEle());
            statement.setString(2, datos_elementos.getDescripcion());
         

            int cont = statement.executeUpdate();
            if (cont > 0) {
                retornarObj = true;
            }
        } catch (Exception e) {
        }
        return retornarObj;
    
    } 
    public boolean actualizarElemento(entidadElemento datos_elementos)
    {
        conectarse();
        boolean retornarObj = false;
        String mod_element = "update tbl_elementos set Nombre_elemento = ?,Descripcion = ? where Codigo = ?";
    try {
            Stmp();
            statement = conector.prepareStatement(mod_element);
            statement.setString(1,datos_elementos.getNombreEle());
            statement.setString(2, datos_elementos.getDescripcion());
            statement.setInt(3, datos_elementos.getCodigo());
         

            int cont = statement.executeUpdate();
            if (cont > 0) {
                retornarObj = true;
            }
        } catch (Exception e) {
        }
        return retornarObj;
    
    
    }
    
    public boolean varAnomalia(entidadElemento datos_elementos)
    {
        conectarse();
        boolean retornarObj = false;
        String est_anomalia = "update tbl_seriales set Estado_anomalia = ? where Seriales = ?";
    try {
            Stmp();
            statement = conector.prepareStatement(est_anomalia);
            statement.setString(1,datos_elementos.getEstado());
            statement.setString(2, datos_elementos.getSerial());

            int cont = statement.executeUpdate();
            if (cont > 0) {
                retornarObj = true;
            }
        } catch (Exception e) {
        }
        return retornarObj;
    
    
    }
    public int traercodigo(){
    int codigo = 0;
        ResultSet rstraCod = null;
        conectarse();
        String traer_codigo = "select max(codigo) as codigo from tbl_elementos";
         try {
            consulta = conector.createStatement();
            rstraCod = consulta.executeQuery(traer_codigo);
            
             while(rstraCod.next()){
         codigo = rstraCod.getInt("codigo");
         }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return codigo;
    }
    
    public ResultSet notificAnomalia(){
    
        ResultSet rs_cntAnm = null;
        conectarse();
        String traer_anomalia = "select count(Seriales) as anomalias from tbl_seriales where  Estado_anomalia = 'Ver'";
         try {
            consulta = conector.createStatement();
            rs_cntAnm = consulta.executeQuery(traer_anomalia);
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rs_cntAnm;
    }
    
    public ResultSet mostraranomolias(){
    
        ResultSet rs_mosAnm = null;
        conectarse();
        String cons_anomalia = "select Seriales,Anomalia  from tbl_seriales where  Estado_anomalia = 'Ver'";
         try {
            consulta = conector.createStatement();
            rs_mosAnm = consulta.executeQuery(cons_anomalia);
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rs_mosAnm;
    }
    
    
    public ResultSet consultarElementos(){
   ResultSet rs_elementos = null;
        conectarse();
        String consultaelementos = "select Codigo,Nombre_elemento,Descripcion from tbl_elementos";
        try {
            consulta = conector.createStatement();
            rs_elementos = consulta.executeQuery(consultaelementos);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return rs_elementos;
    
    }
       public ResultSet consultarSeriales(entidadElemento datos_elementos){
       ResultSet rs_seriales = null;
        conectarse();
        String query = "select Seriales,Estado from tbl_seriales where Codigo = %s";
        String consultaseriales = String.format(query, datos_elementos.getCodigo());
        try {
            consulta = conector.createStatement();
            rs_seriales = consulta.executeQuery(consultaseriales);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return rs_seriales;
       
       }     
     public boolean registrarSeriales(entidadElemento datos_elementos){
   conectarse();
        boolean retornarObj = false;
        String regser = "insert into tbl_seriales (Seriales,Estado,Codigo) values(?,?,?)";
        ArrayList seriales_recorrer = datos_elementos.getSeriales();
        String estado = datos_elementos.getEstado();
        int codigo = traercodigo();
 try 
 {
     int cont = 0;
     for( int i = 0 ; i < seriales_recorrer.size(); i++ ){
               Stmp();
               
            statement = conector.prepareStatement(regser);
            statement.setString(1,seriales_recorrer.get(i).toString().trim());
            statement.setString(2, estado);
            statement.setInt(3,codigo);
            cont = statement.executeUpdate();
     }
            if (cont > 0) {
                retornarObj = true;
            }
        } catch (Exception e) {
        }
        return retornarObj;
    
    }
     public boolean agregarSeriales(entidadElemento datos_elementos){
      conectarse();
        boolean retornarObj = false;
        String agraga_ser = "insert into tbl_seriales (Seriales,Estado,Codigo) values(?,?,?)";
           try {
            Stmp();
            statement = conector.prepareStatement(agraga_ser);
            statement.setString(1,datos_elementos.getSerial());
            statement.setString(2, datos_elementos.getEstado());
            statement.setInt(3, datos_elementos.getCodigo());
         

            int cont = statement.executeUpdate();
            if (cont > 0) {
                retornarObj = true;
            }
        } catch (Exception e) {
        }
        return retornarObj;
     }
    public boolean cambio_estadoSerial(entidadElemento datos_elementos)
    {
        conectarse();
        boolean retornarObj = false;
        String cambio_est = "update tbl_seriales set Estado = ? where Seriales = ?";
    try {
            Stmp();
            statement = conector.prepareStatement(cambio_est);
            statement.setString(1,datos_elementos.getEstado());
            statement.setString(2, datos_elementos.getSerial());
         

            int cont = statement.executeUpdate();
            if (cont > 0) {
                retornarObj = true;
            }
        } catch (Exception e) {
        }
        return retornarObj;
    
    
    }
    
    
    
    
}
