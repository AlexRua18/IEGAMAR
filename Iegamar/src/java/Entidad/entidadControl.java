/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidad;

/**
 *
 * @author Cleiman
 */
public class entidadControl {
    
    
    private String fecha;
    private String hora; 
    private String identificacion;
    //private String mes; Esta variable sirve para los metodos de tiempo de consulta
    private int id_control;
    private String Estado;

   
       
       
       
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    /**
     * @return the id_control
     */
    public int getId_control() {
        return id_control;
    }

    /**
     * @param id_control the id_control to set
     */
    public void setId_control(int id_control) {
        this.id_control = id_control;
    }

    /**
     * @return the Estado
     */
    public String getEstado() {
        return Estado;
    }

    /**
     * @param Estado the Estado to set
     */
    public void setEstado(String Estado) {
        this.Estado = Estado;
    }
    
    
    
    
}
