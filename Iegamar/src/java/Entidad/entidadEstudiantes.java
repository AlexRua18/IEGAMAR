/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidad;

/**
 *
 * @author Gamani Sai
 */
public class entidadEstudiantes {

    private String Identificacion = "";
    private String Nombre = "";
    private String Apellido = "";
    private String Direccion = "";
    private String Telefono = "";
    private String huella = "";
    private String id_grado = "";
    private  String Fecha="";
    private String Estado = "";

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
     * @return the Nombre
     */
    public String getNombre() {
        return Nombre;
    }

    /**
     * @param Nombre the Nombre to set
     */
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    /**
     * @return the Apellido
     */
    public String getApellido() {
        return Apellido;
    }

    /**
     * @param Apellido the Apellido to set
     */
    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    /**
     * @return the Direccion
     */
    public String getDireccion() {
        return Direccion;
    }

    /**
     * @param Direccion the Direccion to set
     */
    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    /**
     * @return the Telefono
     */
    public String getTelefono() {
        return Telefono;
    }

    /**
     * @param Telefono the Telefono to set
     */
    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    /**
     * @return the huella
     */
    public String getHuella() {
        return huella;
    }

    /**
     * @param huella the huella to set
     */
    public void setHuella(String huella) {
        this.huella = huella;
    }

    /**
     * @return the id_grado
     */
    public String getId_grado() {
        return id_grado;
    }

    /**
     * @param id_grado the id_grado to set
     */
    public void setId_grado(String id_grado) {
        this.id_grado = id_grado;
    }

    /**
     * @return the Fecha
     */
    public String getFecha() {
        return Fecha;
    }

    /**
     * @param Fecha the Fecha to set
     */
    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
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
