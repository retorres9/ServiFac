package dat;

import clases.DetalleVenta;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import entitymanager.Entity;
import entitymanager.EntityException;

public class DATReporte {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String database;
    String user;
    String password;
    Entity entity = new Entity();
    String url = "jdbc:mysql://localhost:3306/";

    public DATReporte() {
        try {
            this.database = entity.getEntity("database");
            this.user = entity.getEntity("user");
            this.password = entity.getEntity("password");
        } catch (EntityException ex) {
            Logger.getLogger(DATMaterial.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void creaReporte(DetalleVenta detalle) {
        try {
            con = DriverManager.getConnection(url + database, user, password);
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
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al agregar la venta en la base de datos\nError 058", "Error!", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATReporte.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ArrayList<DetalleVenta> detalleVenta(int idVenta) {
        ArrayList<DetalleVenta> factura = new ArrayList<DetalleVenta>();
        try {
            con = DriverManager.getConnection(url + database, user, password);
            String sentencia = "SELECT dv.Cantidad, p.Nombre_Producto, p.Precio, v.Fecha , dv.Id_Venta "
                    + "FROM detalle_venta dv, producto p, venta v "
                    + "WHERE dv.Codigo = p.Codigo AND dv.Id_Venta = v.Id_Venta AND v.Id_Venta = ?";
            ps = con.prepareStatement(sentencia);
            ps.setInt(1, idVenta);
            rs = ps.executeQuery();
            while (rs.next()) {
                int cant = rs.getInt(1);
                String prod = rs.getString(2);
                double precio = rs.getDouble(3);
                String fecha = rs.getString(4);
                int id = rs.getInt(5);
                DetalleVenta detalle = new DetalleVenta(cant, id, prod, precio, fecha);
                factura.add(detalle);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al consultar en la base de datos\nError 059", "Error!", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                rs.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATReporte.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return factura;
    }
}
