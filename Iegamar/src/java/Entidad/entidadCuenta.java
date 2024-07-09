/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidad;

/**
 *
 * @author Gamani
 */
public class entidadCuenta {

    private int Id_cuenta = 0;
    private String Login = "";
    private String Password = "";
    private String Tipo_rol = "";
    private String Estado = "";
    private String Identificacion = "";

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
     * @return the Login
     */
    public String getLogin() {
        return Login;
    }

    /**
     * @param Login the Login to set
     */
    public void setLogin(String Login) {
        this.Login = Login;
    }

    /**
     * @return the Password
     */
    public String getPassword() {
        return Password;
    }

    /**
     * @param Password the Password to set
     */
    public void setPassword(String Password) {
        this.Password = Password;
    }

    /**
     * @return the Tipo_rol
     */
    public String getTipo_rol() {
        return Tipo_rol;
    }

    /**
     * @param Tipo_rol the Tipo_rol to set
     */
    public void setTipo_rol(String Tipo_rol) {
        this.Tipo_rol = Tipo_rol;
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
}
