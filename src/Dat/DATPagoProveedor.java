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
            String sentencia = "SELECT p.Empresa, p.Deuda, ac.Monto_Cancelado, ac.Usuario, ac.Fecha "
                    + "FROM proveedores p, pago_proveedor pp "
                    + "WHERE p.Empresa = pp.Empresa AND p.Empresa = ? AND Tipo ='Credito'";
            ps = con.prepareStatement(sentencia);
            ps.setString(1, empresa);
            rs = ps.executeQuery();
            while (rs.next()) {
                String strEmpresa = rs.getString(1);
                double deuda = rs.getDouble(2);
                double montoCancel = rs.getDouble(3);
                String usuario = rs.getString(4);
                String fecha = rs.getString(5);
                PagoProveedorClase objPago = new PagoProveedorClase(strEmpresa, montoCancel, deuda, usuario, fecha);
                listadoPagos.add(objPago);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DATPagoProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listadoPagos;
        
    }

//    public ResultSet verPagosPorFecha(String strFecha) throws SQLException, ClassNotFoundException {
//        String sentencia = "SELECT p.Empresa, p.Deuda, pp.Monto_Cancelado, pp.Usuario, pp.Fecha "
//                + "FROM proveedores p, pago_proveedor pp "
//                + "WHERE p.Empresa = pp.Empresa AND pp.Fecha = ? AND Tipo ='Pago'";
//        PreparedStatement ps = c.getConnection().prepareStatement(sentencia);
//        ps.setString(1, strFecha);
//        return ps.executeQuery();
//    }
}
