package Dat;

import Clases.DetalleVenta;
import Clases.Venta;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DATVenta {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Venta venta = new Venta();
    DetalleVenta detalle = new DetalleVenta();

    public void crearVenta(Venta venta, DetalleVenta detalle) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            con.setAutoCommit(false);
            String sentencia = "INSERT INTO venta (Total_Venta, Valor_Cancelado, Fecha, Cedula_Usuario)"
                    + "VALUES (?,?,?,?)";
            ps = con.prepareStatement(sentencia);
            ps.setDouble(1, venta.getDblTotalVenta());
            ps.setDouble(2, venta.getDblValCancelado());
            ps.setString(3, venta.getStrFecha());
            ps.setString(4, venta.getCedulaUser());
            ps.executeUpdate();
            
            
            String sentencia2 = "INSERT INTO detalle_venta (Cedula_Cliente, Cantidad, Codigo, Precio_Venta, Usuario, Id_Venta) "
                    + "VALUES (?,?,?,?,?,?)";
            ps = con.prepareStatement(sentencia2);
            ps.setString(1, detalle.getStrCed());
            ps.setInt(2, detalle.getIntCant());
            ps.setString(3, detalle.getStrCod());
            ps.setDouble(4, detalle.getDblPrecioVenta());
            ps.setString(5, detalle.getStrUsuario());
            ps.setInt(6, detalle.getId_Venta());
            ps.executeUpdate();
            con.commit();
        } catch (SQLException ex) {
            Logger.getLogger(DATVenta.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error", "Ha ocurrio un error al prosesar la transaccion", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATVenta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public ArrayList<Venta> vistaVenta(String fecha) {
        ArrayList<Venta> listadoVentas = new ArrayList<Venta>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "SELECT DISTINCT v.Id_Venta,dv.Cedula_Cliente, c.Nombres, v.Total_Venta, v.Valor_Cancelado"
                    + " FROM detalle_venta dv, venta v, clientes c "
                    + "WHERE dv.Id_Venta = v.Id_Venta AND dv.Cedula_cliente=c.Cedula_cliente AND v.Fecha = ?";
            ps = con.prepareStatement(sentencia);
            ps.setString(1, fecha);
            rs = ps.executeQuery();
            while (rs.next()) {
                int idVenta = rs.getInt(1);
                String cedula = rs.getString(2);
                String nombre = rs.getString(3);
                double totVenta = rs.getDouble(4);
                double valCancelado = rs.getDouble(5);
                System.out.println(idVenta);
                venta = new Venta(idVenta, totVenta, valCancelado, nombre,cedula);
                System.out.println(idVenta);    
                listadoVentas.add(venta);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DATVenta.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATVenta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listadoVentas;
    }
    
    public ArrayList<Venta> vistaVentaFiltrada(String fecha, String cliente) {
        ArrayList<Venta> listadoVentas = new ArrayList<Venta>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "SELECT DISTINCT v.Id_Venta,dv.Cedula_Cliente, c.Nombres, v.Total_Venta, v.Valor_Cancelado "
                    + "FROM detalle_venta dv, venta v, clientes c "
                    + "WHERE dv.Cedula_Cliente LIKE '%"+cliente+"%' OR c.Nombres LIKE '%"+cliente+"%' AND dv.Id_Venta = v.Id_Venta AND dv.Cedula_cliente=c.Cedula_cliente AND v.Fecha = ?";
            ps = con.prepareStatement(sentencia);
            ps.setString(1, fecha);
            rs = ps.executeQuery();
            while (rs.next()) {
                int idVenta = rs.getInt(1);
                String cedula = rs.getString(2);
                String nombre = rs.getString(3);
                double totVenta = rs.getDouble(4);
                double valCancelado = rs.getDouble(5);
                System.out.println(idVenta);
                venta = new Venta(idVenta, totVenta, valCancelado, nombre,cedula);
                System.out.println(idVenta);    
                listadoVentas.add(venta);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DATVenta.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATVenta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listadoVentas;
    }
    
    public ArrayList<Venta> cargaCompras(String cliente){
        ArrayList <Venta> compras = new ArrayList<Venta>();
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa","root","ticowrc2017");
            String sentencia = "SELECT Distinct v.id_venta, v.fecha, c.cedula_cliente FROM venta v, usuario u, detalle_venta dv, clientes c WHERE v.id_Venta = dv.id_venta AND c.cedula_cliente=dv.cedula_cliente AND c.nombres='CONSUMIDOR FINAL'";
            ps = con.prepareStatement(sentencia);
            ps.executeQuery();
            rs = ps.executeQuery();
            while (rs.next()) {
                String fecha = rs.getString(2);
                String nombrecliente = rs.getString(3);
                Venta venta = new Venta(fecha, nombrecliente);
                compras.add(venta);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DATVenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return compras;
        
    }

    public ArrayList<Venta> ConsultarComprasCL(String nombre) {
        ArrayList<Venta> listadoVentas = new ArrayList<Venta>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String Sentencia = "SELECT DISTINCT dv.Id_Venta, v.Total_Venta, v.Valor_Cancelado, v.Fecha,c.Nombres "
                    + "FROM clientes c, detalle_venta dv, venta v "
                    + "WHERE c.cedula_cliente = dv.cedula_cliente AND v.Id_Venta = dv.Id_Venta AND v.Valor_Cancelado<v.Total_Venta AND c.Nombres = ? ORDER BY c.Nombres";
            ps = con.prepareStatement(Sentencia);
            ps.setString(1, nombre);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                double totalVenta = rs.getDouble(2);
                double valorCancelado = rs.getDouble(3);
                String fecha = rs.getString(4);
                String nombreCliente = rs.getString(5);
                venta = new Venta(id, totalVenta, fecha, valorCancelado, fecha, nombreCliente);
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
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "SELECT MAX(Id_Venta)+1 FROM venta";
            ps = con.prepareStatement(sentencia);
            rs = ps.executeQuery();
            while (rs.next()) {
                int cant = rs.getInt(1);
                venta = new Venta(cant);
                cantVenta.add(venta);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DATVenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cantVenta;
    }

    public ArrayList<Venta> cargaVentasNoCancel(String cedula) {
        ArrayList<Venta> listadoVentas = new ArrayList<Venta>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "SELECT DISTINCT dv.Id_Venta, v.Total_Venta, v.Valor_Cancelado FROM venta v, detalle_venta dv "
                    + "WHERE dv.Id_Venta=v.Id_Venta AND Total_Venta>Valor_Cancelado AND cedula_cliente = ? ORDER BY Id_Venta";
            ps = con.prepareStatement(sentencia);
            ps.setString(1, cedula);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                double totalVenta = rs.getDouble(2);
                double cancelado = rs.getDouble(3);
                Venta venta = new Venta(id, totalVenta, cancelado);
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

    public void actualizaPago(int venta, double valCancel) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "UPDATE venta SET Valor_Cancelado = ? WHERE Id_Venta= ?";
            ps = con.prepareStatement(sentencia);
            ps.setInt(1, venta);
            ps.setDouble(2, valCancel);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DATVenta.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATVenta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
