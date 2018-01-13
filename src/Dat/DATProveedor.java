package Dat;

import Clases.Proveedor;
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

public class DATProveedor {

    Dat.DATConexion c = new DATConexion();
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public boolean ingresoProveedor(Proveedor prov) throws SQLException {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "INSERT INTO proveedores (empresa, ruc, nombre_cuenta, tipo_cuenta,"
                    + "numero_cuenta, deuda, telefono)" + "VALUES(?,?,?,?,?,?,?)";
            ps = con.prepareStatement(sentencia);
            ps.setString(1, prov.getStrEmpresa());
            ps.setString(2, prov.getRuc());
            ps.setString(3, prov.getStrNombreCuenta());
            ps.setString(4, prov.getStrTipo());
            ps.setString(5, prov.getStrNumCuenta());
            ps.setDouble(6, prov.getDblDeuda());
            ps.setString(7, prov.getStrTelf());
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "El proveedor ya está registrado o uno de los campos\n"
                    + "que ha llenado han sido ingresados en otro proveedor");
            return false;
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATProveedor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }

    ////////////////////////////////////////////////////////////////////////////
    public ArrayList<Proveedor> obtenerEmpresa() {
        ArrayList<Proveedor> listaProveedor = new ArrayList<Proveedor>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "SELECT ruc, empresa FROM proveedores ORDER BY empresa";
            ps = con.prepareStatement(sentencia);
            rs = ps.executeQuery();

            while (rs.next()) {
                String ruc = rs.getString(1);
                String empresa = rs.getString(2);
                Proveedor prov = new Proveedor(empresa, ruc);
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
