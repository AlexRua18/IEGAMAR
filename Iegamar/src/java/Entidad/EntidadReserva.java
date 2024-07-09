/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidad;

import java.util.ArrayList;

/**
 *
 * @author EDU
 */
public class EntidadReserva {

    private String Fechareserva = "";
    private String Horareserva = "";
    private String Fechaentrega = "";
    private String Identificacion = "";
    private String Estado = "";
    private String Observaciones ="";
    private String Notificacion = "";
    private ArrayList Seriales = null;
    private int id_reserva = 0;
    private int id_cuenta = 0;
    /**
     * @return the Fechareserva
     */
    public String getFechareserva() {
        return Fechareserva;
    }

    /**
     * @param Fechareserva the Fechareserva to set
     */
    public void setFechareserva(String Fechareserva) {
        this.Fechareserva = Fechareserva;
    }

    /**
     * @return the Horareserva
     */
    public String getHorareserva() {
        return Horareserva;
    }

    /**
     * @param Horareserva the Horareserva to set
     */
    public void setHorareserva(String Horareserva) {
        this.Horareserva = Horareserva;
    }

    /**
     * @return the Fechaentrega
     */
    public String getFechaentrega() {
        return Fechaentrega;
    }

    /**
     * @param Fechaentrega the Fechaentrega to set
     */
    public void setFechaentrega(String Fechaentrega) {
        this.Fechaentrega = Fechaentrega;
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
     * @return the Observaciones
     */
    public String getObservaciones() {
        return Observaciones;
    }

    /**
     * @param Observaciones the Observaciones to set
     */
    public void setObservaciones(String Observaciones) {
        this.Observaciones = Observaciones;
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
     * @return the id_reserva
     */
    public int getId_reserva() {
        return id_reserva;
    }

    /**
     * @param id_reserva the id_reserva to set
     */
    public void setId_reserva(int id_reserva) {
        this.id_reserva = id_reserva;
    }

    /**
     * @return the id_cuenta
     */
    public int getId_cuenta() {
        return id_cuenta;
    }

    /**
     * @param id_cuenta the id_cuenta to set
     */
    public void setId_cuenta(int id_cuenta) {
        this.id_cuenta = id_cuenta;
    }

    /**
     * @return the Notificacion
     */
    public String getNotificacion() {
        return Notificacion;
    }

    /**
     * @param Notificacion the Notificacion to set
     */
    public void setNotificacion(String Notificacion) {
        this.Notificacion = Notificacion;
    }
}
