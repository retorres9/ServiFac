package Dat;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DATVenta {
    Dat.DATConexion c = new DATConexion();
    public int crearVenta(int idVenta, double totalVenta, double valorCancelado, String fecha, int CedulaUser) throws ClassNotFoundException, SQLException{
        String sentencia = "INSERT INTO venta (Id_Venta, Total_Venta, Valor_Cancelado, Fecha, Cedula_Usuario)"
                + "VALUES (?,?,?,?,?)";
        PreparedStatement ps = c.getConnection().prepareStatement(sentencia);
        ps.setInt(1, idVenta);
        ps.setDouble(2, totalVenta);
        ps.setDouble(3, valorCancelado);
        ps.setString(4, fecha);
        ps.setInt(5, CedulaUser);
        return ps.executeUpdate();
    }
    
    public ResultSet vistaVenta(String fecha) throws SQLException, ClassNotFoundException{
        String sentencia = "SELECT DISTINCT v.Id_Venta,dv.Cedula, c.Nombres, v.Total_Venta, v.Valor_Cancelado, v.Fecha"
                + " FROM detalle_venta dv, venta v, clientes c "
                + "WHERE dv.Id_Venta = v.Id_Venta AND dv.Cedula=c.Cedula AND v.Fecha = ?";
        PreparedStatement ps = c.getConnection().prepareStatement(sentencia);
        ps.setString(1, fecha);
        return ps.executeQuery();
    }
    
    public ResultSet CuentaVentas() throws ClassNotFoundException, SQLException{
        String sentencia = "SELECT MAX(Id_Venta)+1 FROM venta";
        PreparedStatement pst = c.getConnection().prepareStatement(sentencia);
        return pst.executeQuery();
    }
    
    public ResultSet cargaVentasNoCalcel() throws SQLException, ClassNotFoundException{
        String sentencia = "SELECT DISTINCT dv.Id_Venta, v.Total_Venta, v.Valor_Cancelado FROM venta v, detalle_venta dv "
                + "WHERE dv.Id_Venta=v.Id_Venta AND Total_Venta>Valor_Cancelado ORDER BY Id_Venta";
        PreparedStatement ps = c.getConnection().prepareStatement(sentencia);
        return ps.executeQuery();
    }
    
    public int actualizaPago( int venta, double valCancel) throws SQLException, ClassNotFoundException{
        String sentencia = "UPDATE venta SET Valor_Cancelado = ? WHERE Id_Venta= ?";
        PreparedStatement ps = c.getConnection().prepareStatement(sentencia);
        ps.setDouble(1, valCancel);
        ps.setInt(2, venta);
        return ps.executeUpdate();
    }
}
