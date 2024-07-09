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
public class entidadUsuario {

    private String Login = "";
    private String Password = "";
    private String Tipo_rol = "";

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
}
