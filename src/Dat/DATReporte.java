package Dat;

import Clases.DetalleVenta;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DATReporte {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public void creaReporte(DetalleVenta detalle) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "INSERT INTO detalle_venta (Cedula_Cliente, Cantidad, Codigo, Precio_Venta, Usuario, Id_Venta) "
                    + "VALUES (?,?,?,?,?,?)";
            ps = con.prepareStatement(sentencia);
            ps.setString(1, detalle.getStrCed());
            ps.setInt(2, detalle.getIntCant());
            ps.setString(3, detalle.getStrCod());
            ps.setDouble(4, detalle.getDblPrecioVenta());
            ps.setString(5, detalle.getStrUsuario());
            ps.setInt(6, detalle.getId_Venta());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DATReporte.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATReporte.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ResultSet detalleVenta(int idVenta) throws SQLException, ClassNotFoundException {
        String sentencia = "SELECT dv.Cantidad, p.Nombre_Producto, p.Precio, v.Fecha , v.Id_Venta "
                + "FROM detalle_venta dv, producto p, venta v "
                + "WHERE dv.Codigo = p.Codigo AND dv.Id_Venta = v.Id_Venta AND v.Id_Venta = ?";
        PreparedStatement ps = c.getConnection().prepareStatement(sentencia);
        ps.setInt(1, idVenta);
        return ps.executeQuery();
    }

    public ResultSet reporte(String fecha) throws ClassNotFoundException, SQLException {
        String sentencia = "SELECT Cantidad, Producto, Costo, Costo_Total, Fecha FROM detalle_venta WHERE Fecha = ?";
        PreparedStatement st = c.getConnection().prepareStatement(sentencia);
        st.setString(1, fecha);
        return st.executeQuery();
    }

    public ResultSet suma(String fecha) throws ClassNotFoundException, SQLException {
        String sentencia = "SELECT SUM (Costo_Total) As total FROM detalle_venta WHERE Fecha = ?";
        PreparedStatement ps = c.getConnection().prepareStatement(sentencia);
        ps.setString(1, fecha);
        return ps.executeQuery();
    }
}
