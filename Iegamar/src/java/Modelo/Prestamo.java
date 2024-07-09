/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Entidad.entidadPrestamo;
import Entidad.entidadPrestamo;
import java.util.ArrayList;

/**
 *
 * @author Gamani Sai
 */
public class Prestamo extends ConexionBD {

    private PreparedStatement statement;
    entidadPrestamo ent = new entidadPrestamo();

    public boolean registrarSeriales(entidadPrestamo datosPrestmo) {
        conectarse();
        boolean retornarObj = false;
        String regser = "insert into tbl_dtlls_prestamo (Id_prestamo,Seriales) values(?,?)";
        ArrayList seriales_recorrer = datosPrestmo.getSeriales();
        String estado = datosPrestmo.getEstado();

        try {
            int cont = 0;
            for (int i = 0; i < seriales_recorrer.size(); i++) {
                Stmp();

                statement = conector.prepareStatement(regser);
                statement.setInt(1, datosPrestmo.getId_prestamo());
                statement.setString(2, seriales_recorrer.get(i).toString().trim());
                cont = statement.executeUpdate();
            }
            if (cont > 0) {
                retornarObj = true;
            }
        } catch (Exception e) {
        }
        return retornarObj;

    }

        public boolean cambiarestadoSeriales(entidadPrestamo datosPrestmo) {
        conectarse();
        boolean retornarObj = false;
        String regser = "update tbl_seriales set Estado = ?   where Seriales = ?";
        ArrayList seriales_recorrer = datosPrestmo.getSeriales();
        String estado = datosPrestmo.getEstado();

        try {
            int cont = 0;
            for (int i = 0; i < seriales_recorrer.size(); i++) {
                Stmp();

                statement = conector.prepareStatement(regser);
                
                statement.setString(1, datosPrestmo.getEstado().toString().trim());
                statement.setString(2, seriales_recorrer.get(i).toString().trim());
                cont = statement.executeUpdate();
            }
            if (cont > 0) {
                retornarObj = true;
            }
        } catch (Exception e) {
        }
        return retornarObj;

    }
     public boolean estadoSeriales(entidadPrestamo datosPrestmo) {
        conectarse();
        boolean retornarObj = false;
        String devo_estado = "update tbl_seriales set Estado = ?   where Seriales = ?";
        ResultSet seriales_devo = consultarSeriales(datosPrestmo);
        String estado = datosPrestmo.getEstado();

        try {
          int cont = 0;
          while(seriales_devo.next())
          {
              statement = conector.prepareStatement(devo_estado);
                
                statement.setString(1, datosPrestmo.getEstado().toString().trim());
                statement.setString(2, seriales_devo.getString("s.Seriales").toString().trim());
                cont = statement.executeUpdate();
          
          
          }
          
            if (cont > 0) {
                retornarObj = true;
            }
        } catch (Exception e) {
        }
        return retornarObj;

    }
    public ResultSet traerProfesor() {

        ResultSet rsp = null;
        conectarse();

        String profesor = "select concat (Nombre, ' ' , Apellido)  as nombre_completo , Identificacion  from tbl_empleado where Estado = 'Habilitado'";
        try {
            consulta = conector.createStatement();
            rsp = consulta.executeQuery(profesor);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rsp;
    }

    public ResultSet traerEstudiante() {

        ResultSet rsp = null;
        conectarse();

        String estudiante = "select concat (Nombre, ' ' , Apellido) as nombre_completo , Identificacion  from tbl_estudiante where Estado = 'Habilitado'";
        try {
            consulta = conector.createStatement();
            rsp = consulta.executeQuery(estudiante);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rsp;
    }

    public ResultSet traerSeriales() {

        ResultSet rsp = null;
        conectarse();

        String seriales = "SELECT Seriales  FROM tbl_seriales where Estado = 'Disponible'";
        try {
            consulta = conector.createStatement();
            rsp = consulta.executeQuery(seriales);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rsp;
    }

    public boolean insertPrestamo(entidadPrestamo datosprestamo) {
        conectarse();
        boolean retornarObj = false;
        String regPrestamo = "insert into tbl_prestamo (Fecha_prestamo,Hora_prestamo,Tipo,Fecha_devolucion,Hora_devolucion) values (?,?,?,?,?)";

        try {
            Stmp();
            statement = conector.prepareStatement(regPrestamo);
            statement.setString(1, datosprestamo.getFecha_prestamo());
            statement.setString(2, datosprestamo.getHora_prestamo());
            statement.setString(3, datosprestamo.getTipo());
            statement.setString(4, datosprestamo.getFecha_devolucion());
            statement.setString(5, datosprestamo.getHora_devolucion());

            int cont = statement.executeUpdate();
            if (cont > 0) {
                retornarObj = true;
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return retornarObj;
    }

    public ResultSet consulId_prestamo() {

        ResultSet rsp = null;
        conectarse();

        String consulId_prestamo = "SELECT MAX(Id_prestamo) as Id_prestamo FROM  tbl_prestamo";
        try {
            consulta = conector.createStatement();
            rsp = consulta.executeQuery(consulId_prestamo);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rsp;
    }

    public boolean insertIdentificacionPro(entidadPrestamo datosprestamo) {
        conectarse();
        boolean retornarObj = false;
        String regPrestamo = "insert into tbl_dtlls_empleadxprestamo (Identificacion,Id_prestamo) values (?,?)";

        try {
            Stmp();
            statement = conector.prepareStatement(regPrestamo);
            statement.setString(1, datosprestamo.getIdentificacion());
            statement.setInt(2, datosprestamo.getId_prestamo());

            int cont = statement.executeUpdate();
            if (cont > 0) {
                retornarObj = true;
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return retornarObj;
    }

    public boolean insertIdentificacionEst(entidadPrestamo datosprestamo) {
        conectarse();
        boolean retornarObj = false;
        String regPrestamo = "insert into tbl_dtlls_estudiantexprestamo (Identificacion,Id_prestamo) values (?,?)";

        try {
            Stmp();
            statement = conector.prepareStatement(regPrestamo);
            statement.setString(1, datosprestamo.getIdentificacion());
            statement.setInt(2, datosprestamo.getId_prestamo());

            int cont = statement.executeUpdate();
            if (cont > 0) {
                retornarObj = true;
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return retornarObj;
    }

    public boolean insertId_cuentaxId_cuenta(entidadPrestamo datosprestamo) {
        conectarse();
        boolean retornarObj = false;
        String regId_cuenta = "insert into tbl_dtlls_cunetaxprestamo (Id_cuenta,Id_prestamo,Tipo) values (?,?,?)";

        try {
            Stmp();
            statement = conector.prepareStatement(regId_cuenta);
            statement.setInt(1, datosprestamo.getId_cuenta());
            statement.setInt(2, datosprestamo.getId_prestamo());
            statement.setString(3, datosprestamo.getTipocuenta());

            int cont = statement.executeUpdate();
            if (cont > 0) {
                retornarObj = true;
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return retornarObj;
    }

    public ResultSet consultarPrestamo() {
        ResultSet rsPrestamo = null;
        conectarse();
        String consultaPrestamo = "SELECT p.Id_prestamo,CONCAT (es.Nombre, ' ' , es.Apellido) AS Prestador,p.Fecha_prestamo,p.Hora_prestamo,CONCAT (e.Nombre, ' ' , e.Apellido) AS NombrePrestador , p.Fecha_devolucion, p.Hora_devolucion, IFNULL((SELECT CONCAT (est.Nombre, ' ' , est.Apellido) FROM tbl_estudiante est INNER JOIN tbl_dtlls_estudiantesxcuentas exc ON(est.Identificacion = exc.Identificacion) INNER JOIN tbl_cuentas_usuario cs \n"
                + "ON (exc.Id_cuenta = cs.Id_cuenta) INNER JOIN tbl_dtlls_cunetaxprestamo cxp ON (cs.Id_cuenta = cxp.Id_cuenta) WHERE cxp.Tipo = 'Devolucion' AND cxp.Id_prestamo = p.Id_prestamo),'No Asignado') AS devolucion  \n"
                + "FROM tbl_prestamo p INNER JOIN tbl_dtlls_estudiantexprestamo ep \n"
                + "ON (p.Id_prestamo = ep.Id_prestamo) INNER JOIN tbl_estudiante e ON (ep.Identificacion = e.Identificacion) JOIN tbl_dtlls_cunetaxprestamo cp\n"
                + "ON (p.Id_prestamo = cp.Id_prestamo) INNER JOIN tbl_cuentas_usuario cu ON (cp.Id_cuenta = cu.Id_cuenta) INNER JOIN tbl_dtlls_estudiantesxcuentas ec \n"
                + "ON (cu.Id_cuenta = ec.Id_cuenta)INNER JOIN tbl_estudiante es ON (ec.Identificacion = es.Identificacion) WHERE cp.Tipo = 'Prestador' ORDER BY  devolucion DESC";
        try {
            consulta = conector.createStatement();
            rsPrestamo = consulta.executeQuery(consultaPrestamo);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return rsPrestamo;

    }

    public ResultSet consultarPrestamo1() {
        ResultSet rsPrestamo = null;
        conectarse();
        String consultaPrestamo = "SELECT p.Id_prestamo,CONCAT (es.Nombre, ' ' , es.Apellido) AS Prestador,p.Fecha_prestamo,p.Hora_prestamo,CONCAT (em.Nombre, ' ' , em.Apellido) AS NombrePrestador , p.Fecha_devolucion, p.Hora_devolucion, IFNULL((SELECT CONCAT (est.Nombre, ' ' , est.Apellido) FROM tbl_estudiante est INNER JOIN tbl_dtlls_estudiantesxcuentas exc ON(est.Identificacion = exc.Identificacion) INNER JOIN tbl_cuentas_usuario cs \n"
                + "ON (exc.Id_cuenta = cs.Id_cuenta) INNER JOIN tbl_dtlls_cunetaxprestamo cxp ON (cs.Id_cuenta = cxp.Id_cuenta) WHERE cxp.Tipo = 'Devolucion' AND cxp.Id_prestamo = p.Id_prestamo),'No Asignado') AS devolucion  \n"
                + "FROM tbl_prestamo p INNER JOIN tbl_dtlls_empleadxprestamo ep\n"
                + "ON (p.Id_prestamo = ep.Id_prestamo) INNER JOIN tbl_empleado em ON (ep.Identificacion = em.Identificacion) JOIN tbl_dtlls_cunetaxprestamo cp\n"
                + "ON (p.Id_prestamo = cp.Id_prestamo) INNER JOIN tbl_cuentas_usuario cu ON (cp.Id_cuenta = cu.Id_cuenta) INNER JOIN tbl_dtlls_estudiantesxcuentas ec \n"
                + "ON (cu.Id_cuenta = ec.Id_cuenta)INNER JOIN tbl_estudiante es ON (ec.Identificacion = es.Identificacion) WHERE cp.Tipo = 'Prestador' ORDER BY  devolucion DESC";
        try {
            consulta = conector.createStatement();
            rsPrestamo = consulta.executeQuery(consultaPrestamo);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return rsPrestamo;

    }

    public ResultSet consultarSeriales(entidadPrestamo datosPrestamo) {
        ResultSet rs_seriales = null;
        conectarse();
        String query = "SELECT s.Seriales  FROM tbl_seriales s INNER JOIN tbl_dtlls_prestamo dp ON (s.Seriales = dp.Seriales) where dp.Id_prestamo = %s";
        String seriales_prestamo = String.format(query, datosPrestamo.getId_prestamo());
        try {
            consulta = conector.createStatement();
            rs_seriales = consulta.executeQuery(seriales_prestamo);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return rs_seriales;

    }

    public boolean registarDevolucion(entidadPrestamo datosPrestamo) {
        conectarse();
        boolean retornarObj = false;
        String regDevolucion = "update tbl_prestamo set Fecha_devolucion = ? , Hora_devolucion = ?  where Id_prestamo = ?";
        try {
            Stmp();
            statement = conector.prepareStatement(regDevolucion);
            statement.setString(1, datosPrestamo.getFecha_devolucion());
            statement.setString(2, datosPrestamo.getHora_devolucion());
            statement.setInt(3, datosPrestamo.getId_prestamo());

            int cont = statement.executeUpdate();
            if (cont > 0) {
                retornarObj = true;
            }
        } catch (Exception e) {
        }

        return retornarObj;
    }

    public boolean registarAnomalia(entidadPrestamo datosPrestamo) {
        conectarse();
        boolean retornarObj = false;
        String regDevolucion = "update tbl_seriales set Anomalia = ? , Estado_anomalia = ?   where Seriales = ?";
        try {
            Stmp();
            statement = conector.prepareStatement(regDevolucion);
            statement.setString(1, datosPrestamo.getDescricion_anomalia());
            statement.setString(2, datosPrestamo.getEstaAnomali());
            statement.setString(3, datosPrestamo.getSerialesUP());

            int cont = statement.executeUpdate();
            if (cont > 0) {
                retornarObj = true;
            }
        } catch (Exception e) {
        }

        return retornarObj;
    }
    

}
