/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidad;

import java.util.ArrayList;

/**
 *
 * @author Gamani Sai
 */
public class entidadPrestamo {

    private int id_prestamo = 0;
    private String Fecha_prestamo = "";
    private String Hora_prestamo = "";
    private String Fecha_devolucion = "";
    private String Hora_devolucion = "";
    private String Tipo = "";
    private String Identificacion = "";
    private int Id_cuenta = 0;
    private String tipocuenta = "";
    private ArrayList Seriales = null;
    private String Estado = "";
    private String descricion_anomalia="";
    private String SerialesUP="";
    private String estaAnomali ="";

    /**
     * @return the Fecha_prestamo
     */
    public String getFecha_prestamo() {
        return Fecha_prestamo;
    }

    /**
     * @param Fecha_prestamo the Fecha_prestamo to set
     */
    public void setFecha_prestamo(String Fecha_prestamo) {
        this.Fecha_prestamo = Fecha_prestamo;
    }

    /**
     * @return the Hora_prestamo
     */
    public String getHora_prestamo() {
        return Hora_prestamo;
    }

    /**
     * @param Hora_prestamo the Hora_prestamo to set
     */
    public void setHora_prestamo(String Hora_prestamo) {
        this.Hora_prestamo = Hora_prestamo;
    }

    /**
     * @return the Fecha_devolucion
     */
    public String getFecha_devolucion() {
        return Fecha_devolucion;
    }

    /**
     * @param Fecha_devolucion the Fecha_devolucion to set
     */
    public void setFecha_devolucion(String Fecha_devolucion) {
        this.Fecha_devolucion = Fecha_devolucion;
    }

    /**
     * @return the Hora_devolucion
     */
    public String getHora_devolucion() {
        return Hora_devolucion;
    }

    /**
     * @param Hora_devolucion the Hora_devolucion to set
     */
    public void setHora_devolucion(String Hora_devolucion) {
        this.Hora_devolucion = Hora_devolucion;
    }

    /**
     * @return the Tipo
     */
    public String getTipo() {
        return Tipo;
    }

    /**
     * @param Tipo the Tipo to set
     */
    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    /**
     * @return the Identificacion
     */
    public String getIdentificacion() {
        return Identificacion;
    }

    /**
     * @param Identificacion the Identificacion to set
     */
    public void setIdentificacion(String Identificacion) {
        this.Identificacion = Identificacion;
    }

    /**
     * @return the Id_cuenta
     */
    public int getId_cuenta() {
        return Id_cuenta;
    }

    /**
     * @param Id_cuenta the Id_cuenta to set
     */
    public void setId_cuenta(int Id_cuenta) {
        this.Id_cuenta = Id_cuenta;
    }

    /**
     * @return the id_prestamo
     */
    public int getId_prestamo() {
        return id_prestamo;
    }

    /**
     * @param id_prestamo the id_prestamo to set
     */
    public void setId_prestamo(int id_prestamo) {
        this.id_prestamo = id_prestamo;
    }

    /**
     * @return the tipocuenta
     */
    public String getTipocuenta() {
        return tipocuenta;
    }

    /**
     * @param tipocuenta the tipocuenta to set
     */
    public void setTipocuenta(String tipocuenta) {
        this.tipocuenta = tipocuenta;
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
     * @return the descricion_anomalia
     */
    public String getDescricion_anomalia() {
        return descricion_anomalia;
    }

    /**
     * @param descricion_anomalia the descricion_anomalia to set
     */
    public void setDescricion_anomalia(String descricion_anomalia) {
        this.descricion_anomalia = descricion_anomalia;
    }

    /**
     * @return the SerialesUP
     */
    public String getSerialesUP() {
        return SerialesUP;
    }

    /**
     * @param SerialesUP the SerialesUP to set
     */
    public void setSerialesUP(String SerialesUP) {
        this.SerialesUP = SerialesUP;
    }

    /**
     * @return the estaAnomali
     */
    public String getEstaAnomali() {
        return estaAnomali;
    }

    /**
     * @param estaAnomali the estaAnomali to set
     */
    public void setEstaAnomali(String estaAnomali) {
        this.estaAnomali = estaAnomali;
    }

}
