package Dat;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DATReporte {
    Dat.DATConexion c = new DATConexion();
    public int CreaReport(int ced, int cant, int cod, double precio_venta, String usuario, int id_Venta) throws ClassNotFoundException, SQLException{
        String sentencia = "INSERT INTO detalle_venta (Cedula_Cliente, Cantidad, Codigo, Precio_Venta, Usuario, Id_Venta) "
                + "VALUES (?,?,?,?,?,?)";
        PreparedStatement ps = c.getConnection().prepareStatement(sentencia);
        ps.setInt(1, ced);
        ps.setInt(2, cant);
        ps.setInt(3, cod);
        ps.setDouble(4, precio_venta);
        ps.setString(5, usuario);
        ps.setDouble(6, id_Venta);
        return ps.executeUpdate();
    }
    
    public ResultSet detalleVenta(int idVenta) throws SQLException, ClassNotFoundException{
        String sentencia = "SELECT dv.Cantidad, p.Nombre_Producto, p.Precio, v.Fecha , v.Id_Venta "
                + "FROM detalle_venta dv, producto p, venta v "
                + "WHERE dv.Codigo = p.Codigo AND dv.Id_Venta = v.Id_Venta AND v.Id_Venta = ?";
        PreparedStatement ps = c.getConnection().prepareStatement(sentencia);
        ps.setInt(1, idVenta);
        return ps.executeQuery();
    }
    
    public ResultSet reporte(String fecha)throws ClassNotFoundException, SQLException{
        String sentencia = "SELECT Cantidad, Producto, Costo, Costo_Total, Fecha FROM detalle_venta WHERE Fecha = ?";
        PreparedStatement st = c.getConnection().prepareStatement(sentencia);
        st.setString(1, fecha);
        return st.executeQuery();
    }
    
    public ResultSet suma(String fecha) throws ClassNotFoundException, SQLException{
        String sentencia = "SELECT SUM (Costo_Total) As total FROM detalle_venta WHERE Fecha = ?";
        PreparedStatement ps = c.getConnection().prepareStatement(sentencia);
        ps.setString(1, fecha);
        return ps.executeQuery();
    }
}
