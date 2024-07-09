/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class ConexionBD {

    public Connection conector;
    public Statement consulta;
    public ResultSet resultado;

    public Connection conectarse() {

        try {
            Class.forName("com.mysql.jdbc.Driver");

            conector = DriverManager.getConnection("jdbc:mysql://localhost:3306/iegamar", "root", "");

        } catch (ClassNotFoundException mnj) {
            //SQLException
        } catch (SQLException mnj) {

        }
        return conector;
    }

    public Statement Stmp() {
        return this.consulta;
    }
}
