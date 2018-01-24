package Dat;

import Clases.DetalleVenta;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DATReporte {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public void CreaReport(DetalleVenta detalle) throws ClassNotFoundException, SQLException {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "INSERT INTO detalle_venta (Cedula_Cliente, Cantidad, Codigo, Precio_Venta, Usuario, Id_Venta) "
                    + "VALUES (?,?,?,?,?,?)";
            ps = con.prepareStatement(sentencia);
            ps.setString(1, detalle.getStrCed());
            ps.setInt(2, detalle.getIntCant());
            ps.setString(3, detalle.getIntCod());
            ps.setDouble(4, precio_venta);
            ps.setString(5, usuario);
            ps.setDouble(6, id_Venta);
        }

        PreparedStatement ps = c.getConnection().prepareStatement(sentencia);

        return ps.executeUpdate();
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
