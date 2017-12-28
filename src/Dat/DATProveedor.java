package Dat;

import Clases.Proveedor;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DATProveedor {

    Dat.DATConexion c = new DATConexion();
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public void ingresoProveedor(String empresa, String nombreCuenta, String tipo,
            String numCuenta, double deuda, int telefono) throws MySQLIntegrityConstraintViolationException {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "INSERT INTO proveedores (Empresa, Nombre_Cuenta, Tipo_Cuenta,"
                    + "Numero_Cuenta, Deuda, Telefono)" + "VALUES(?,?,?,?,?,?)";
            ps = con.prepareStatement(sentencia);
            ps.setString(1, empresa);
            ps.setString(2, nombreCuenta);
            ps.setString(3, tipo);
            ps.setString(4, numCuenta);
            ps.setDouble(5, deuda);
            ps.setInt(6, telefono);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATProveedor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    ////////////////////////////////////////////////////////////////////////////
    public ArrayList<Proveedor> obtenerEmpresa() {
        ArrayList<Proveedor> listaProveedor = new ArrayList<Proveedor>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "SELECT Empresa FROM proveedores";
            ps = con.prepareStatement(sentencia);
            rs = ps.executeQuery();

            while (rs.next()) {
                String empresa = rs.getString(1);
                Proveedor prov = new Proveedor(empresa);
                listaProveedor.add(prov);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                rs.close();
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return listaProveedor;
    }

    ////////////////////////////////////////////////////////////////////////////
    public ResultSet cargarProveedores() throws SQLException, ClassNotFoundException {
        Statement ps = c.getConnection().createStatement();
        String sentencia = "SELECT Empresa FROM proveedores";
        ResultSet rs = ps.executeQuery(sentencia);
        return rs;
    }

    public ResultSet cargarTabla() throws ClassNotFoundException, SQLException {
        Statement ps = c.getConnection().createStatement();
        String sentencia = "SELECT Empresa, Nombre_Cuenta, Tipo_Cuenta,"
                + "Numero_Cuenta, Deuda, Telefono FROM proveedores";
        ResultSet re = ps.executeQuery(sentencia);
        return re;
    }

    public ResultSet buscarNombre(String nombre) throws ClassNotFoundException, SQLException {
        String sentencia = "SELECT Empresa, Nombre_Cuenta, Tipo_Cuenta,"
                + "Numero_Cuenta, Deuda, Telefono FROM proveedores WHERE Empresa REGEXP CONCAT ('^',?)";
        PreparedStatement ps = c.getConnection().prepareStatement(sentencia);
        ps.setString(1, nombre);
        return ps.executeQuery();
    }

    public ResultSet buscarNombreCuenta(String nombreEmpresa) throws ClassNotFoundException, SQLException {
        String sentencia = "SELECT Empresa, Nombre_Cuenta, Tipo_Cuenta,"
                + "Numero_Cuenta, Deuda, Telefono FROM proveedores WHERE Nombre_Cuenta REGEXP CONCAT ('^',?)";
        PreparedStatement ps = c.getConnection().prepareStatement(sentencia);
        ps.setString(1, nombreEmpresa);
        return ps.executeQuery();
    }

    public int updateDeuda(double deuda, String empresa) throws ClassNotFoundException, SQLException {
        String sentencia = "UPDATE proveedores SET Deuda = ? WHERE Empresa = ?";
        PreparedStatement ps = c.getConnection().prepareStatement(sentencia);
        ps.setDouble(1, deuda);
        ps.setString(2, empresa);
        return ps.executeUpdate();
    }

    public int eliminarProveedor(String empresa) throws ClassNotFoundException, SQLException {
        String sentencia = "DELETE FROM proveedores WHERE  Empresa = ?";
        PreparedStatement ps = c.getConnection().prepareStatement(sentencia);
        ps.setString(1, empresa);
        return ps.executeUpdate();
    }
}
