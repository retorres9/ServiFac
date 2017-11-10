package Dat;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DATPagoProveedor {

    Dat.DATConexion c = new DATConexion();
    public int pagoProveedor(String strEmpresa, String strUsuario, double dblMonto, String strFecha, String strTipo)
    throws SQLException, ClassNotFoundException{
        String sentencia = "INSERT INTO pago_proveedor (Empresa, Usuario, Monto_Cancelado, Fecha, Tipo) "
                + "VALUES (?,?,?,?,?)";
        PreparedStatement ps = c.getConnection().prepareStatement(sentencia);
        ps.setString(1, strEmpresa);
        ps.setString(2, strUsuario);
        ps.setDouble(3, dblMonto);
        ps.setString(4, strFecha);
        ps.setString(5, strTipo);
        return ps.executeUpdate();
    }
    
    public ResultSet verPagos(String empresa) throws SQLException, ClassNotFoundException{
        String sentencia = "SELECT p.Empresa, p.Deuda, ac.Monto_Cancelado, ac.Usuario, ac.Fecha "
                + "FROM proveedores p, pago_proveedor pp "
                + "WHERE p.Empresa = pp.Empresa AND p.Empresa = ? AND Tipo ='Credito'";
        PreparedStatement ps = c.getConnection().prepareStatement(sentencia);
        ps.setString(1, empresa);
        return ps.executeQuery();
    }
    
    public ResultSet verPagosPorFecha(String strFecha) throws SQLException, ClassNotFoundException{
        String sentencia = "SELECT p.Empresa, p.Deuda, pp.Monto_Cancelado, pp.Usuario, pp.Fecha "
                + "FROM proveedores p, pago_proveedor pp "
                + "WHERE p.Empresa = pp.Empresa AND pp.Fecha = ? AND Tipo ='Pago'";
        PreparedStatement ps = c.getConnection().prepareStatement(sentencia);
        ps.setString(1, strFecha);
        return ps.executeQuery();
    }
    
}
