package Dat;

import Clases.Clientes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DATClientes {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Clientes cliente = new Clientes();

    public ArrayList<Clientes> ConsultarPagosGui() {
        ArrayList<Clientes> listadoClientes = new ArrayList<Clientes>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "SELECT Nombres, Cedula_Cliente, Telefono, Deuda, Direccion FROM clientes WHERE Deuda>0";
            ps = con.prepareStatement(sentencia);
            rs = ps.executeQuery();
            while (rs.next()) {
                String nombre = rs.getString(1);
                String cedula = rs.getString(2);
                int telf = rs.getInt(3);
                double deuda = rs.getDouble(4);
                String direccion = rs.getString(5);
                cliente = new Clientes(nombre, cedula, telf, deuda, direccion);
                listadoClientes.add(cliente);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DATClientes.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATClientes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listadoClientes;
    }

    public ArrayList<Clientes> ConsultarxNombre(String nombre) throws ClassNotFoundException, SQLException {
        ArrayList<Clientes> listadoClientes = new ArrayList<Clientes>();
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String Sentencia = "SELECT DISTINCT dv.Id_Venta, c.Deuda, v.Total_Venta, v.Valor_Cancelado, v.Fecha,c.Nombres "
                + "FROM clientes c, detalle_venta dv, venta v "
                + "WHERE c.Cedula_Cliente = dv.Cedula AND v.Id_Venta = dv.Id_Venta AND v.Valor_Cancelado<v.Total_Venta AND c.Nombres = ? ORDER BY c.Nombres Asc, dv.Id_Venta";
            ps = con.prepareStatement(Sentencia);
            rs = ps.executeQuery();
            while(rs.next()){
                
            }
        }
        
        PreparedStatement ps = c.getConnection().prepareStatement(Sentencia);
        ps.setString(1, nombre);
        return ps.executeQuery();
    }

    public ResultSet Consultar() throws ClassNotFoundException, SQLException {
        Statement st = c.getConnection().createStatement();
        String Sentencia = "SELECT DISTINCT dv.Id_Venta, c.Deuda, v.Total_Venta, v. Valor_Cancelado, v.Fecha "
                + "FROM clientes c, detalle_venta dv, venta v "
                + "WHERE c.Cedula = dv.Cedula AND v.Id_Venta = dv.Id_Venta ORDER BY c.Nombres Asc, dv.Id_Venta";
        ResultSet re = st.executeQuery(Sentencia);
        return re;
    }

    public ArrayList<Clientes> ConsultarPorNombre(String nombre) {
        ArrayList<Clientes> listadoClientes = new ArrayList<Clientes>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "SELECT Nombres, Cedula_Cliente, Telefono, Deuda, Direccion FROM clientes WHERE Deuda> 0 AND Nombres REGEXP CONCAT('^',?) ORDER BY Nombres";
            ps = con.prepareStatement(sentencia);
            ps.setString(1, nombre);
            rs = ps.executeQuery();
            while (rs.next()) {
                String nombreCliente = rs.getString(1);
                String cedula = rs.getString(2);
                int telf = rs.getInt(3);
                double deuda = rs.getDouble(4);
                String direccion = rs.getString(5);
                cliente = new Clientes(nombreCliente, cedula, telf, deuda, direccion);
                listadoClientes.add(cliente);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                rs.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATClientes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listadoClientes;
    }

    public ArrayList<Clientes> ConsultarCedula(int cedulaCli) {
        ArrayList<Clientes> listadoClientes = new ArrayList<Clientes>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String Sentencia = "SELECT Nombres, Cedula_Cliente, Telefono, Deuda, Direccion FROM clientes WHERE Deuda> 0 AND Cedula_Cliente REGEXP CONCAT('^',?) ORDER BY Nombres";
            ps = con.prepareStatement(Sentencia);
            ps.setInt(1, cedulaCli);
            rs = ps.executeQuery();
            while (rs.next()) {
                String nombreCliente = rs.getString(1);
                String cedula = rs.getString(2);
                int telf = rs.getInt(3);
                double deuda = rs.getDouble(4);
                String direccion = rs.getString(5);
                cliente = new Clientes(nombreCliente, cedula, telf, deuda, direccion);
                listadoClientes.add(cliente);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                rs.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATClientes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listadoClientes;
    }

    public boolean InsertarCliente(Clientes cliente) throws SQLException {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "INSERT INTO clientes (Nombres, Cedula_Cliente, Telefono, Deuda, Direccion)"
                    + "VALUES (?,?,?,?,?)";
            ps = con.prepareStatement(sentencia);
            ps.setString(1, cliente.getStrNombre());
            ps.setString(2, cliente.getStrCedula());
            ps.setInt(3, cliente.getIntTelf());
            ps.setDouble(4, cliente.getDblDeuda());
            ps.setString(5, cliente.getStrDireccion());
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "El nombre o la cédula ya están asignados a otro cliente");
            return false;
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATClientes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }

    public void agregarDeuda(Clientes cliente) {
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "UPDATE clientes SET Deuda = ? WHERE Cedula_Cliente = ? ";
            ps = con.prepareStatement(sentencia);
            ps.setDouble(1, cliente.getDblDeuda());
            ps.setString(2, cliente.getStrCedula());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DATClientes.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATClientes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void actualizarCliente(Clientes cliente) {
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa","root","ticowrc2017");
            String sentencia = "UPDATE clientes SET Nombres = ?, Telefono = ?, Direccion = ? WHERE Cedula_Cliente = ?";
            ps = con.prepareStatement(sentencia);
            ps.setString(1, cliente.getStrNombre());
            ps.setInt(2, cliente.getIntTelf());
            ps.setString(3, cliente.getStrDireccion());
            ps.setString(4, cliente.getStrCedula());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATClientes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void actualizarCedulaCliente(Clientes cliente){
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa","root","ticowrc2017");
            String sentencia = "UPDATE clientes SET Cedula_Cliente = ? WHERE Nombres = ?";
            ps = con.prepareStatement(sentencia);
            ps.setString(1, cliente.getStrCedula());
            ps.setString(2, cliente.getStrNombre());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DATClientes.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATClientes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public int eliminarCliente(String strNombre) throws ClassNotFoundException, SQLException {
        String sentencia = "DELETE FROM clientes WHERE Nombres = ?";
        PreparedStatement ps = c.getConnection().prepareStatement(sentencia);
        ps.setString(1, strNombre);
        return ps.executeUpdate();
    }

    public ResultSet FacturaCliente(String nombre) throws ClassNotFoundException, SQLException {
        //Statement st = c.getConnection().createStatement();
        String Sentencia = "SELECT Nombres, Direccion, Deuda FROM clientes WHERE Cedula = ?";
        PreparedStatement ps = c.getConnection().prepareStatement(Sentencia);
        ps.setString(1, nombre);
        return ps.executeQuery();
    }
}
