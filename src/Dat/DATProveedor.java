package Dat;

import Clases.Proveedor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al consultar en la base de datos\nError 051", "Error!", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                rs.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATProveedor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listaProveedor;
    }

    public ArrayList<Proveedor> obtenerTODOempresa() {
        ArrayList<Proveedor> listaProveedor = new ArrayList<Proveedor>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "SELECT * FROM proveedores WHERE estado = true AND empresa NOT LIKE '(Vacio)'";
            ps = con.prepareStatement(sentencia);
            rs = ps.executeQuery();
            while (rs.next()) {
                String empresa = rs.getString(1);
                String ruc = rs.getString(2);
                String nombreCta = rs.getString(3);
                String tipo = rs.getString(4);
                String numeroCta = rs.getString(5);
                double deuda = rs.getDouble(6);
                String telf = rs.getString(7);
                Proveedor prov = new Proveedor(empresa, ruc, nombreCta, tipo, numeroCta, deuda, telf);
                listaProveedor.add(prov);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al consultar en la base de datos\nError 052", "Error!", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                rs.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATProveedor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listaProveedor;
    }

    public void actualizaProveedor(Proveedor prov){
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "UPDATE proveedores SET nombre_cuenta = ?, tipo_cuenta = ?,"
                    + "numero_cuenta = ?, telefono = ? WHERE RUC = ?";
            ps = con.prepareStatement(sentencia);
            ps.setString(1, prov.getStrNombreCuenta());
            ps.setString(2, prov.getStrTipo());
            ps.setString(3, prov.getStrNumCuenta());
            ps.setString(4, prov.getStrTelf());
            ps.setString(5, prov.getRuc());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Proveedor actualizado correctamente");
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al actualizar los datos del proveedor\nen la base de datos\nError 053", "Error!", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATProveedor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public ArrayList<Proveedor> buscarEmpresa(String nombre) {
        ArrayList<Proveedor> listadoBusq = new ArrayList<Proveedor>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "SELECT Empresa, ruc, Nombre_Cuenta, Tipo_Cuenta,"
                    + "Numero_Cuenta, Deuda, Telefono FROM proveedores WHERE estado = true AND Empresa REGEXP CONCAT ('^',?)";
            ps = con.prepareStatement(sentencia);
            ps.setString(1, nombre);
            rs = ps.executeQuery();
            while (rs.next()) {
                String empresa = rs.getString(1);
                String ruc = rs.getString(2);
                String nombreCta = rs.getString(3);
                String tipo = rs.getString(4);
                String numeroCta = rs.getString(5);
                double deuda = rs.getDouble(6);
                String telf = rs.getString(7);
                Proveedor prov = new Proveedor(empresa, ruc, nombreCta, tipo, numeroCta, deuda, telf);
                listadoBusq.add(prov);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al consultar en la base de datos\nError 054", "Error!", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATProveedor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listadoBusq;
    }

    public ArrayList<Proveedor> buscarNombreCuenta(String nombreEmpresa) {
        ArrayList<Proveedor> listadoBusq = new ArrayList<Proveedor>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "SELECT Empresa, ruc, Nombre_Cuenta, Tipo_Cuenta,"
                    + "Numero_Cuenta, Deuda, Telefono FROM proveedores WHERE estado = true AND Nombre_Cuenta REGEXP CONCAT ('^',?)";
            ps = con.prepareStatement(sentencia);
            ps.setString(1, nombreEmpresa);
            rs = ps.executeQuery();
            while (rs.next()) {
                String empresa = rs.getString(1);
                String ruc = rs.getString(2);
                String nombreCta = rs.getString(3);
                String tipo = rs.getString(4);
                String numeroCta = rs.getString(5);
                double deuda = rs.getDouble(6);
                String telf = rs.getString(7);
                Proveedor prov = new Proveedor(empresa, ruc, nombreCta, tipo, numeroCta, deuda, telf);
                listadoBusq.add(prov);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al consultar en la base de datos\nError 055", "Error!", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATProveedor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listadoBusq;
    }

    public void updateDeuda(Proveedor proveedor) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "UPDATE proveedores SET Deuda = ? WHERE ruc = ?";
            ps = con.prepareStatement(sentencia);
            ps.setDouble(1, proveedor.getDblDeuda());
            ps.setString(2, proveedor.getRuc());
            System.out.println(ps.toString());
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al actualizar la deuda con el proveedor en la base de datos\nError 056", "Error!", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATProveedor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void eliminarProveedor(String empresa) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "ticowrc2017");
            String sentencia = "UPDATE proveedores SET estado = false WHERE  Empresa = ?";
            ps = con.prepareStatement(sentencia);
            ps.setString(1, empresa);
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se puede dar de baja el proveedor\nError 057", "Error!", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DATProveedor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
