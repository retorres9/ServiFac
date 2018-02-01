package Dat;

import Clases.Venta;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DATVenta {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Venta venta = new Venta();

    public void crearVenta(Venta venta) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "INSERT INTO venta (Total_Venta, Valor_Cancelado, Fecha, Cedula_Usuario)"
                    + "VALUES (?,?,?,?)";
            ps = con.prepareStatement(sentencia);
            ps.setDouble(1, venta.getDblTotalVenta());
            ps.setDouble(2, venta.getDblValCancelado());
            ps.setString(3, venta.getStrFecha());
            ps.setString(4, venta.getCedulaUser());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATVenta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public ResultSet vistaVenta(String fecha) throws SQLException, ClassNotFoundException {
        String sentencia = "SELECT DISTINCT v.Id_Venta,dv.Cedula, c.Nombres, v.Total_Venta, v.Valor_Cancelado, v.Fecha"
                + " FROM detalle_venta dv, venta v, clientes c "
                + "WHERE dv.Id_Venta = v.Id_Venta AND dv.Cedula=c.Cedula AND v.Fecha = ?";
        PreparedStatement ps = c.getConnection().prepareStatement(sentencia);
        ps.setString(1, fecha);
        return ps.executeQuery();
    }
    
    public ArrayList<Venta> ConsultarComprasCL(String nombre) {
        ArrayList<Venta> listadoVentas = new ArrayList<Venta>();
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String Sentencia = "SELECT DISTINCT dv.Id_Venta, c.Deuda, v.Total_Venta, v.Valor_Cancelado, v.Fecha,c.Nombres "
                + "FROM clientes c, detalle_venta dv, venta v "
                + "WHERE c.cedula_cliente = dv.cedula_cliente AND v.Id_Venta = dv.Id_Venta AND v.Valor_Cancelado<v.Total_Venta AND c.Nombres = ? ORDER BY c.Nombres";
            ps = con.prepareStatement(Sentencia);
            ps.setString(1, nombre);
            rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt(1);
                double deuda = rs.getDouble(2);
                double totalVenta = rs.getDouble(3);
                double valorCancelado = rs.getDouble(4);
                String fecha = rs.getString(5);
                String nombreCliente = rs.getString(6);
                venta = new Venta(id, deuda, fecha, valorCancelado, fecha, totalVenta, nombreCliente);
                listadoVentas.add(venta);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DATVenta.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATVenta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listadoVentas;
    }

    public ArrayList<Venta> CuentaVentas() {        
        ArrayList<Venta> cantVenta = new ArrayList<Venta>();
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa","root","ticowrc2017");
            String sentencia = "SELECT MAX(Id_Venta)+1 FROM venta";
            ps = con.prepareStatement(sentencia);
            rs = ps.executeQuery();
            while(rs.next()){
                int cant = rs.getInt(1);
                venta = new Venta(cant);
                cantVenta.add(venta);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DATVenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cantVenta;
    }

    public ResultSet cargaVentasNoCalcel() throws SQLException, ClassNotFoundException {
        String sentencia = "SELECT DISTINCT dv.Id_Venta, v.Total_Venta, v.Valor_Cancelado FROM venta v, detalle_venta dv "
                + "WHERE dv.Id_Venta=v.Id_Venta AND Total_Venta>Valor_Cancelado ORDER BY Id_Venta";
        PreparedStatement ps = c.getConnection().prepareStatement(sentencia);
        return ps.executeQuery();
    }

    public int actualizaPago(int venta, double valCancel) throws SQLException, ClassNotFoundException {
        String sentencia = "UPDATE venta SET Valor_Cancelado = ? WHERE Id_Venta= ?";
        PreparedStatement ps = c.getConnection().prepareStatement(sentencia);
        ps.setDouble(1, valCancel);
        ps.setInt(2, venta);
        return ps.executeUpdate();
    }
}
