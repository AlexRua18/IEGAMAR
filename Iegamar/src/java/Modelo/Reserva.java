/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Entidad.EntidadReserva;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.spi.DirStateFactory;

/**
 *
 * @author EDU
 */
public class Reserva extends ConexionBD {

    private PreparedStatement statement;

    public ResultSet traerSeriales() {

        ResultSet rsp = null;
        conectarse();

        String estudiante = "select seriales   from tbl_seriales where Estado = 'Disponible' ";
        try {
            consulta = conector.createStatement();
            rsp = consulta.executeQuery(estudiante);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rsp;
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

    public boolean insertReserva(EntidadReserva datosreserva) {
        conectarse();
        boolean retornarObj = false;
        String regreserva = "INSERT INTO tbl_reserva (Fecha_reserva,Hora_reserva,Fecha_entrega,Estado,Identificacion,Observaciones,Id_cuenta_reserva) VALUES (?,?,?,?,?,?,?)";

        try {
            Stmp();
            statement = conector.prepareStatement(regreserva);
            statement.setString(1, datosreserva.getFechareserva());
            statement.setString(2, datosreserva.getHorareserva());
            statement.setString(3, datosreserva.getFechaentrega());
            statement.setString(4, datosreserva.getEstado());
            statement.setString(5, datosreserva.getIdentificacion());
            statement.setString(6, datosreserva.getObservaciones());
            statement.setInt(7, datosreserva.getId_cuenta());

            int cont = statement.executeUpdate();
            if (cont > 0) {
                retornarObj = true;
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return retornarObj;
    }

    public int traerIdreserva() {
        int codigo = 0;
        ResultSet rstraCod = null;
        conectarse();
        String traer_codigo = "select max(Id_reserva) as Id_reserva from tbl_reserva";
        try {
            consulta = conector.createStatement();
            rstraCod = consulta.executeQuery(traer_codigo);

            while (rstraCod.next()) {
                codigo = rstraCod.getInt("Id_reserva");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return codigo;
    }

    public boolean registrarSeriales(EntidadReserva datosreserva) {
        conectarse();
        boolean retornarObj = false;
        String regser = "insert into tbl_dtlls_reserva (Id_reserva,Seriales) values(?,?)";
        ArrayList seriales_recorrer = datosreserva.getSeriales();
        int idreserva = traerIdreserva();
        try {
            int cont = 0;
            for (int i = 0; i < seriales_recorrer.size(); i++) {
                Stmp();

                statement = conector.prepareStatement(regser);
                statement.setInt(1, idreserva);
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

    public ResultSet consultarReservas() {
        ResultSet rsp = null;
        conectarse();
        String consultareserva = "select r.Id_reserva,concat (es.Nombre, ' ' , es.Apellido) as 'Nombre monitor' ,concat (e.Nombre, ' ', e.Apellido) as 'Nombre Profesor' ,e.Identificacion,r.Fecha_reserva,r.Hora_reserva,r.Fecha_entrega,r.Observaciones from  tbl_reserva r \n"
                + "inner join tbl_empleado e on (r.Identificacion = e.Identificacion) \n"
                + "inner join tbl_cuentas_usuario cu on (r.Id_cuenta_reserva = cu.Id_cuenta) \n"
                + "inner join tbl_dtlls_estudiantesxcuentas ec on (cu.Id_cuenta = ec.Id_cuenta) \n"
                + "inner join tbl_estudiante es on (ec.Identificacion = es.Identificacion) where r.Estado = 'Reserva'";
        try {
            consulta = conector.createStatement();
            rsp = consulta.executeQuery(consultareserva);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return rsp;
    }

    public ResultSet ConReservasHoy(EntidadReserva datosreserva) {
        ResultSet crh = null;
        conectarse();
        String consultarreserva = "select r.Id_reserva, r.Hora_reserva,  concat(e.Nombre, \" \", e.Apellido) as Nombre, r.Notificacion, r.Fecha_entrega from tbl_reserva r \n"
                + "join tbl_empleado e on (e.Identificacion = r.Identificacion) where  r.Estado = 'Reserva'";
        
        try {
            consulta = conector.createStatement();
            crh = consulta.executeQuery(consultarreserva);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return crh;
    }

    public ResultSet consultarSeriales(EntidadReserva datosreserva) {
        ResultSet rs_seriales = null;
        conectarse();
        String query = "select r.Seriales from tbl_dtlls_reserva r join tbl_seriales s \n"
                + "on (r.Seriales = s.Seriales) where r.Id_reserva = %s and s.Estado = 'Reservado'";
        String consultaseriales = String.format(query, datosreserva.getId_reserva());
        try {
            consulta = conector.createStatement();
            rs_seriales = consulta.executeQuery(consultaseriales);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return rs_seriales;

    }
    
    public ResultSet consultarSerialesVen(EntidadReserva datosreserva) {
        ResultSet rs_seriales = null;
        
        conectarse();
        String query = "select r.Seriales from tbl_dtlls_reserva r join tbl_seriales s \n"
                + "on (r.Seriales = s.Seriales) where r.Id_reserva = %s ";
        String consultaseriales = String.format(query, datosreserva.getId_reserva());
        try {
            consulta = conector.createStatement();
            rs_seriales = consulta.executeQuery(consultaseriales);
            
          
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return rs_seriales;

    }

    public boolean cambio_estadoreserva(EntidadReserva datosreserva) {
        conectarse();
        boolean retornarObj = false;
        String cambio_est = "update tbl_reserva set Estado = ? where Id_reserva = ?";
        try {
            Stmp();
            statement = conector.prepareStatement(cambio_est);
            statement.setString(1, datosreserva.getEstado());
            statement.setInt(2, datosreserva.getId_reserva());

            int cont = statement.executeUpdate();
            if (cont > 0) {
                retornarObj = true;
            }
        } catch (Exception e) {
        }
        return retornarObj;

    }

    public ResultSet contreservas(EntidadReserva datosreserva) {
        ResultSet rs_reser = null;
        conectarse();
        String query = "select count(Id_reserva) as conteo from tbl_reserva  where Fecha_entrega = '%s' and Estado = 'Reserva'";
        String contreservas = String.format(query, datosreserva.getFechaentrega());
        try {
            consulta = conector.createStatement();
            rs_reser = consulta.executeQuery(contreservas);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return rs_reser;

    }
    public ResultSet ConResrvaNot(EntidadReserva datosreserva){
        ResultSet re_CamEst = null;
        conectarse();
        String query = "select d.Seriales from tbl_dtlls_reserva d join tbl_reserva r \n" +
"on (d.Id_reserva = r.Id_reserva) where r.Fecha_entrega = '%s' and r.Estado = 'Reserva'";
        String consultarnot = String.format(query, datosreserva.getFechaentrega());
         try {
            consulta = conector.createStatement();
            re_CamEst = consulta.executeQuery(consultarnot);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return re_CamEst;
    }
    public String ConEstadoSer(EntidadReserva datosreserva){
     ResultSet rs_Estser = null;
     String Estado = "";
        conectarse();
        String query = "select Estado from tbl_seriales where Seriales = '%s'";
        String Conestado = String.format(query, datosreserva.getEstado());
        try {
            consulta = conector.createStatement();
            rs_Estser = consulta.executeQuery(Conestado);
            while(rs_Estser.next()){
            Estado = rs_Estser.getString("Estado").trim();
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        
        return Estado;
    }
    
    /*---------------------------------------------------------------------------continuar ---------------*/
    public boolean cambio_observacion(EntidadReserva datosreserva) {
        conectarse();
        boolean retornarObj = false;
        String cambio_est = "update tbl_reserva set Estado = ? where Id_reserva = ?";
        try {
            Stmp();
            statement = conector.prepareStatement(cambio_est);
            statement.setString(1, datosreserva.getEstado());
            statement.setInt(2, datosreserva.getId_reserva());

            int cont = statement.executeUpdate();
            if (cont > 0) {
                retornarObj = true;
            }
        } catch (Exception e) {
        }
        return retornarObj;

    }
    public boolean Notificacionreserva(EntidadReserva datosreserva) {
        conectarse();
        boolean retornarObj = false;
        String cambio_Not = "update tbl_reserva set Notificacion = ? where Id_reserva = ?";
        try {
            Stmp();
            statement = conector.prepareStatement(cambio_Not);
            statement.setString(1, datosreserva.getNotificacion());
            statement.setInt(2, datosreserva.getId_reserva());

            int cont = statement.executeUpdate();
            if (cont > 0) {
                retornarObj = true;
            }
        } catch (Exception e) {
        }
        return retornarObj;

    }
}
