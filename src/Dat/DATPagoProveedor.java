package Dat;

import Clases.PagoProveedorClase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DATPagoProveedor {

    Connection con;
    ResultSet rs;
    PreparedStatement ps;
    PagoProveedorClase pago = new PagoProveedorClase();

    public void pagoProveedor(PagoProveedorClase pago) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "INSERT INTO pago_proveedor (ruc, cedula_usuario, Monto_Cancelado, Fecha, Tipo, descripcion) "
                    + "VALUES (?,?,?,?,?,?)";
            ps = con.prepareStatement(sentencia);
            ps.setString(1, pago.getStrRuc());
            ps.setString(2, pago.getStrCedUsuario());
            ps.setDouble(3, pago.getDblMontoCancelado());
            ps.setString(4, pago.getStrFecha());
            ps.setString(5, pago.getStrTipo());
            ps.setString(6, pago.getStrDescripcion());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DATPagoProveedor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATPagoProveedor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public ArrayList<PagoProveedorClase> verPagos(String empresa) {
        ArrayList<PagoProveedorClase> listadoPagos = new ArrayList<PagoProveedorClase>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "SELECT pp.monto_cancelado, u.usuario, pp.fecha, pp.descripcion "
                    + "FROM proveedores p, pago_proveedor pp , usuario u "
                    + "WHERE p.RUC = pp.RUC AND u.cedula_usuario = pp.cedula_usuario AND pp.RUC = ?";
            ps = con.prepareStatement(sentencia);
            ps.setString(1, empresa);
            rs = ps.executeQuery();
            while (rs.next()) {
                double montoCancel = rs.getDouble(1);
                String usuario = rs.getString(2);
                String fecha = rs.getString(3);
                String desc = rs.getString(4);
                PagoProveedorClase objPago = new PagoProveedorClase(montoCancel, usuario, fecha, desc);
                listadoPagos.add(objPago);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DATPagoProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listadoPagos;

    }

    public ArrayList<PagoProveedorClase> verPagosPorFecha(String strFecha) {
        ArrayList<PagoProveedorClase> listadoPagos = new ArrayList<PagoProveedorClase>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "SELECT p.Empresa, p.Deuda, pp.Monto_Cancelado, U.usuario "
                    + "FROM proveedores p, pago_proveedor pp, usuario u "
                    + "WHERE u.cedula_usuario=pp.cedula_usuario AND p.ruc = pp.ruc AND pp.Fecha = ? AND Tipo ='Pago'";
            ps = con.prepareStatement(sentencia);
            ps.setString(1, strFecha);
            rs = ps.executeQuery();
            while (rs.next()) {
                String empresa = rs.getString(1);
                double deuda = rs.getDouble(2);
                double monto = rs.getDouble(3);
                String usuario = rs.getString(4);
                pago = new PagoProveedorClase(monto, deuda, usuario, empresa);
                listadoPagos.add(pago);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DATPagoProveedor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATPagoProveedor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listadoPagos;
    }
}
