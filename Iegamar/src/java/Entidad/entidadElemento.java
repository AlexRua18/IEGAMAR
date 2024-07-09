/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidad;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author 519649
 */
public class entidadElemento {
    private String nombreEle = "";
    private String descripcion = "";
    private ArrayList Seriales = null;
    private String Serial = "";
    private String estado = "";
    private int codigo = 0;

    /**
     * @return the nombreEle
     */
    public String getNombreEle() {
        return nombreEle;
    }

    /**
     * @param nombreEle the nombreEle to set
     */
    public void setNombreEle(String nombreEle) {
        this.nombreEle = nombreEle;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the Seriales
     */
    public ArrayList getSeriales() {
        return Seriales;
    }

    /**
     * @param Seriales the Seriales to set
     */
    public void setSeriales(ArrayList Seriales) {
        this.Seriales = Seriales;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the Serial
     */
    public String getSerial() {
        return Serial;
    }

    /**
     * @param Serial the Serial to set
     */
    public void setSerial(String Serial) {
        this.Serial = Serial;
    }

    /**
     * @return the Seriales
     */
   
}
