package Dat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DATClientes {

    Dat.DATConexion c = new DATConexion();
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public ResultSet ConsultarPagosGui() throws SQLException, ClassNotFoundException {
        Statement st = c.getConnection().createStatement();
        String sentencia = "SELECT Nombres, Cedula_Cliente, Telefono, Deuda, Direccion FROM clientes WHERE Deuda>0";
        ResultSet rs = st.executeQuery(sentencia);
        return rs;
    }

    public ResultSet ConsultarxNombre(String nombre) throws ClassNotFoundException, SQLException {

        String Sentencia = "SELECT DISTINCT dv.Id_Venta, c.Deuda, v.Total_Venta, v.Valor_Cancelado, v.Fecha,c.Nombres "
                + "FROM clientes c, detalle_venta dv, venta v "
                + "WHERE c.Cedula = dv.Cedula AND v.Id_Venta = dv.Id_Venta AND v.Valor_Cancelado<v.Total_Venta AND c.Nombres = ? ORDER BY c.Nombres Asc, dv.Id_Venta";
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

    public ResultSet Consultar2(String nombre) throws ClassNotFoundException, SQLException {
        //Statement st = c.getConnection().createStatement();
        String Sentencia = "SELECT Nombres, Cedula, Telefono, Deuda, Direccion FROM clientes WHERE Nombres REGEXP CONCAT('^',?)";
        PreparedStatement ps = c.getConnection().prepareStatement(Sentencia);
        //ResultSet re = st.executeQuery(Sentencia);
        ps.setString(1, nombre);
        return ps.executeQuery();
    }

    public ResultSet ConsultarCedula(String nombre) throws ClassNotFoundException, SQLException {
        //Statement st = c.getConnection().createStatement();
        String Sentencia = "SELECT Nombres, Cedula, Telefono, Deuda, Direccion FROM clientes WHERE Cedula REGEXP CONCAT('^',?)";
        PreparedStatement ps = c.getConnection().prepareStatement(Sentencia);
        //ResultSet re = st.executeQuery(Sentencia);
        ps.setString(1, nombre);
        return ps.executeQuery();
    }

    public void InsertarCliente(String nombre, int cedula, int telf, double deuda, String direccion) throws SQLException {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "INSERT INTO clientes (Nombres, Cedula_Cliente, Telefono, Deuda, Direccion)"
                    + "VALUES (?,?,?,?,?)";
            ps = con.prepareStatement(sentencia);
            ps.setString(1, nombre);
            ps.setInt(2, cedula);
            ps.setInt(3, telf);
            ps.setDouble(4, deuda);
            ps.setString(5, direccion);
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

    public int agregarDeuda(double deuda, String nombre) throws ClassNotFoundException, SQLException {
        String sentencia = "UPDATE clientes SET Deuda = ? WHERE Nombres = ? ";
        PreparedStatement ps = c.getConnection().prepareStatement(sentencia);
        ps.setDouble(1, deuda);
        ps.setString(2, nombre);
        return ps.executeUpdate();
    }

    public int updateCliente(String nombre, int cedula, int telf, String n) throws ClassNotFoundException, SQLException {
        String sentencia = "UPDATE clientes SET Nombres = ?, Cedula = ?, Telefono = ? WHERE Nombres = ?";
        PreparedStatement ps = c.getConnection().prepareStatement(sentencia);
        ps.setString(1, nombre);
        ps.setInt(2, cedula);
        ps.setInt(3, telf);
        ps.setString(4, n);
        return ps.executeUpdate();
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
